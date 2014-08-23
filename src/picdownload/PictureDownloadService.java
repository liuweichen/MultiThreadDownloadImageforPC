package picdownload;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import picdownload.util.PictureUrls;

class PictureDownloadService {
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
	private AtomicInteger number;
	private Set<Integer> currentRunning=new HashSet<Integer>();
	
	public void startDownload() {	
		String[] urls=PictureUrls.getUrls();
		number=new AtomicInteger(urls.length);
		for(int i=0;i<urls.length;i++){
			Runnable task=new PictureDownloadTask(urls[i],i,this);
			executor.execute(task);
		}		
	}

	public boolean isFinished(){
		return number!=null&&number.get()<=0;
	}

	public Set<Integer> getCurrentRunning() {
		return currentRunning;
	}

	public void decrToProcess() {
		number.decrementAndGet();
	}
	
}
