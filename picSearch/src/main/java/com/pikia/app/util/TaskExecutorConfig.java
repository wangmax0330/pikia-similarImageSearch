package com.pikia.app.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutorConfig {
	private int coreThreads;
	private int maxThreads;
	private long aliveTime;
	// 由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序
	private ArrayBlockingQueue<Runnable> taskQueue;
	private int taskQueueSize = 100;
	private ThreadFactory threadFactory;

	public int getCoreThreads() {
		return coreThreads;
	}

	public void setCoreThreads(int coreThreads) {
		this.coreThreads = coreThreads;
	}

	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public long getAliveTime() {
		return aliveTime;
	}

	public void setAliveTime(long aliveTime) {
		this.aliveTime = aliveTime;
	}

	public ArrayBlockingQueue<Runnable> getTaskQueue() {
		taskQueue = new ArrayBlockingQueue<Runnable>(this.getTaskQueueSize());
		return taskQueue;
	}

	public void setTaskQueue(ArrayBlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public int getTaskQueueSize() {
		return taskQueueSize;
	}

	public void setTaskQueueSize(int taskQueueSize) {
		this.taskQueueSize = taskQueueSize;
	}

	public ThreadFactory getThreadFactory() {

		final AtomicInteger threadNumber = new AtomicInteger(1);

		return threadFactory;
	}

	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}

}
