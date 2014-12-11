//package com.pikia.app.service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import com.pikia.app.component.task.TaskEngine;
//import com.pikia.app.util.Const;
//import com.pikia.app.util.TaskExecutorConfig;
//import com.pikia.app.util.UploadAddress;
//
//import con.pikia.app.domain.ImageTaskDetail;
//
////对上传的图片自动进行生成指纹,生成特征等操作
//@Component
//public class AppAutoImageDealService implements ApplicationContextAware {
//	private final Log logger = LogFactory.getLog(AppAutoImageDealService.class);
//
//	@Resource
//	private UploadAddress uploadAddress;
//	@Resource
//	private AppImageFeatureService imageFeatureService;
//	private final ThreadPoolExecutor taskExecutor;
//
//	public static void main(String[] args) {
//		System.out.println("sdssd");
//	}
//
//	// new ThreadPoolExecutor(
//	//
//	// 2（核心线程数）, 4（最大线程数）, 30（最大线程闲置时间）, TimeUnit.SECONDS,
//	//
//	// new ArrayBlockingQueue<Runnable>(20),（队列容量）
//	//
//	// new RecorderThreadFactory("CookieRecorderPool")(这个这个你仿造taskengine写一个),
//	//
//	// new ThreadPoolExecutor.DiscardPolicy()（策略大概是这个名字）);
//	public AppAutoImageDealService(TaskExecutorConfig config) {
//		taskExecutor = new ThreadPoolExecutor(config.getCoreThreads(),
//				config.getMaxThreads(), config.getAliveTime(),
//				TimeUnit.SECONDS, config.getTaskQueue(),
//				config.getThreadFactory(),
//				new ThreadPoolExecutor.CallerRunsPolicy());
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext arg0)
//			throws BeansException {
//		TaskEngine.getInstance().schedule(new Runnable() {
//
//			@Override
//			public void run() {
//
//				int hessianErrorNum=0;//记录错误数量
//				while(true){
//					try{
//						Const.lastWhile=new Date();
//						if(Const.need2do>0){
//							//如果还有数据正在处理，则不拿新的要处理的
//							Thread.sleep(3000);
//							continue;
//						}
//						Const.allTaskLs.clear();
//						Const.taskDoneLs.clear();
//						List<ImageTaskDetail> details=imageFeatureService.getToDoImageList();
//						Const.lastQuery=new Date();
//						if(details!=null&&details.size()>0){
//							Const.allTaskLs.addAll(details);
//							hessianErrorNum=0;
//							
//							synchronized (Const.need2do) {
//								Const.need2do=details.size();
//							}
//							
//							for(ImageTaskDetail dt:details){
//								Const.taskDoneLs.add(dt);
//								final String picPath=uploadAddress.uploadPicPath
//										+ dt.getPicUrl();
//								final String fileName=dt.getPicUrl().substring(dt.getPicUrl().lastIndexOf("/")+1,dt.getPicUrl().lastIndexOf("."));
//								final String path=uploadAddress+dt.getPicUrl().substring(
//										0,
//										dt.getPicUrl().lastIndexOf(
//												"/") + 1);
//								
//								taskExecutor.submit(new Runnable(){
//
//									@Override
//									public void run() {
//										try{
//											int result=1;
//											if(Const.autoDeal){
//												
//												//自动生成图片的特征码
//												
//												
//											}
//											
//											//提交图片的状态
//											
//										}catch(Exception e){
//											logger.error(e,e);
//										}finally{
//											synchronized (Const.need2do) {
//												Const.need2do--;
//											}
//										}
//									}
//									
//								});
//								
//							}
//						}else{
//							Thread.sleep(3000);
//						}
//						
//					}catch(Exception e){
//						try {
//						Thread.sleep(30000);
//						logger.error(e,e);
//						hessianErrorNum++;
//						if (hessianErrorNum > 50) {
//							logger.error("----->exit auto image deal process");
//							Const.lastWhile = null;
//							break;// 错误太多，直接退出循环
//						}
//					} catch (InterruptedException e1) {
//						
//					}
//				}
//			}
//		}
//	}, new Long(5000));
//	}
//}
