package picdownload;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

import picdownload.util.DateString;

public class PictureDownloadTask implements Runnable {
	String urlString;
	private int retrycount=0;
	private int id;
	private PictureDownloadService service;

	public PictureDownloadTask(String url,int id,PictureDownloadService service) {
		// 设置属性
		urlString = url;
		this.id=id;
		this.service=service;
	}

	public void run() {
		try{
			downLoadFile();
		}
		catch(IOException e){
			e.printStackTrace();
			if((++retrycount)<3){
				System.out.println("下载出错，正在重试,id="+id);
				run();
			}
			else{
				System.out.println("下载出错，超过最大重试次数,id="+id);
			}
		}
	}

	public synchronized void downLoadFile() throws IOException {
		service.getCurrentRunning().add(id);
		System.out.println("开始下载,id="+id);
		
		// 网络文件的相关信息
		// 网络文件的URL
		URL url = new URL(urlString);
		// 打开该网络文件的URL连接
		URLConnection urlConn = url.openConnection();

		// 添加网络文件的相关信息
		/*StringBuffer info = new StringBuffer();
		info.append("主机：" + url.getHost() + "\n");
		info.append("端口：" + url.getDefaultPort() + "\n");
		info.append("网络文件的类型：" + urlConn.getContentType() + "\n");
		info.append("长度：" + urlConn.getContentLength() + "\n");
		info.append("正在下载···");
		System.out.println(info.toString());*/
		// 创建网络文件的输入流
		InputStream is = urlConn.getInputStream();

		
		String localFileName = DateString.strAdd6Hours + "_"
				+ url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
		
		// 创建本地文件输出流
		File f = new File(DateString.strAdd6Hours + "/");
		f.mkdirs();
		FileOutputStream fos = new FileOutputStream(DateString.strAdd6Hours + "/" + localFileName);

		// 读取网络文件到本地文件
		int data;
		while ((data = is.read()) != -1) {
			fos.write(data);
		}

		// 关闭流
		is.close();
		fos.close();
		
		System.out.println("下载完成,id="+id);
		service.getCurrentRunning().remove(id);
		service.decrToProcess();
	}
}
