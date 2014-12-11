package com.pikia.app.component.task;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

public class TaskEngine {
	private static final TaskEngine instance = new TaskEngine();
	// 引入定时器类
	private Timer timer;
	private ExecutorService executor;
	private Map<Runnable, TimerTaskWrapper> wrappedTasks = new ConcurrentHashMap();

	public static TaskEngine getInstance() {
		return instance;
	}

	private TaskEngine() {
		// 创建一个新的计时器，其关联的线程具有指定的名称，并指明作为守护程序来运行。
		this.timer = new Timer("timer-pikia", true);
		this.executor = Executors.newCachedThreadPool(new ThreadFactory() {
			final AtomicInteger threadNumber = new AtomicInteger(1);

			public Thread newThread(Runnable runnable) {
				Thread thread = new Thread(Thread.currentThread()
						.getThreadGroup(), runnable, "pool-pikia"
						+ this.threadNumber.getAndIncrement(), 0L);
				thread.setDaemon(true);
				if (thread.getPriority() != 5) {
					thread.setPriority(5);
				}
				return thread;
			}
		});
	}

	public Future<?> submit(Runnable task) {
		return this.executor.submit(task);
	}

	/**
	 * 延迟执行时间
	 * 
	 * @param task
	 * @param delay
	 *            毫秒
	 */
	public void schedule(Runnable task, Long delay) {
		this.timer.schedule(new TimerTaskWrapper(task), delay);
	}

	/**
	 * 可以指定任务的执行时间,毫秒
	 * 
	 * @param task
	 * @param time
	 */
	public void schedule(Runnable task, Date time) {
		this.timer.schedule(new TimerTaskWrapper(task), time);
	}

	/**
	 * 指定延迟后按周期执行
	 * 
	 * @param task
	 * @param delay
	 * @param period
	 */
	public void schedule(Runnable task, long delay, long period) {
		TimerTaskWrapper taskWrapper = new TimerTaskWrapper(task);
		this.wrappedTasks.put(task, taskWrapper);
		this.timer.schedule(taskWrapper, delay, period);
	}

	/**
	 * 指定时间后,按周期循环执行
	 * 
	 * @param task
	 * @param firstTime
	 * @param period
	 */
	public void schedule(Runnable task, Date firstTime, Long period) {
		TimerTaskWrapper taskWrapper = new TimerTaskWrapper(task);
		this.wrappedTasks.put(task, taskWrapper);
		this.timer.schedule(taskWrapper, firstTime, period);
	}

	public void cancleScheduledTask(Runnable task) {
		TimerTaskWrapper taskWrapper = (TimerTaskWrapper) this.wrappedTasks
				.remove(task);
		if (taskWrapper != null)
			taskWrapper.cancel();
	}

	public void shutdown() {
		if (this.executor != null) {
			this.executor.shutdownNow();
			this.executor = null;
		}

		if (this.timer != null) {
			this.timer.cancel();
			this.timer = null;
		}
	}

	private class TimerTaskWrapper extends TimerTask {
		private Runnable task;

		public TimerTaskWrapper(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			TaskEngine.this.executor.submit(this.task);
		}

	}
}