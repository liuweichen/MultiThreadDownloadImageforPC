package picdownload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import picdownload.util.DateString;

public class Bootstrap {
   
   static boolean flag = false;
   public static void main(String arg[]) {	
	   
	   File fileName = new File("logFile/log.txt");
		File fileName1 = new File("logFile");
		if(!fileName.exists()) {
			try {
				fileName1.mkdir();
				fileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
			BufferedReader bufReader = new BufferedReader(reader);		
			String br;
			while((br = bufReader.readLine()) !=null) {
				if(br.equals(DateString.strAdd6Hours)) {
					flag = true;
				}				
			}
			
			bufReader.close();
			reader.close();
			
			if(flag) {				
				System.out.println("现在日期"+DateString.strAdd6Hours);				
				System.out.println("已经下载过了，无需再次下载，若希望再次下载，请改写log.txt文件");					flag = false;
			} else {
				PictureDownloadService mt = new PictureDownloadService();
				mt.startDownload();
				
				try {					
					OutputStreamWriter outWriter = new OutputStreamWriter(new FileOutputStream(fileName,true));
					BufferedWriter bufWriter = new BufferedWriter(outWriter);					
					bufWriter.append(DateString.strAdd6Hours+"\r\n");					
				    bufWriter.flush();
				    bufWriter.close();
				} catch (IOException e) {
					e.printStackTrace();	
				}
				while(!mt.isFinished()){
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("所有任务下载完毕！！");
				System.exit(0);
			}
		} catch (FileNotFoundException e1) {
				e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
   
}



