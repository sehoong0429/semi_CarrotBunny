package com.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRename implements FileRenamePolicy {
	
	@Override
	public File rename(File oldFile) {
		
		File newFile = null;
		do {
			//rename된 규칙 설정하기 ! 
			//절대로 중복 되지 않는 값으로 설정해야한다. 
			long currentTime=System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum =(int)(Math.random()*1000+1);
			String oriName= oldFile.getName();//확장자를 포함한 파일명을 반환한다. 
			
			//파일명에서 확장자 빼내기 
			String ext="";
			int dot= oriName.lastIndexOf(".");
			if(dot!=-1) {
				ext=oriName.substring(dot); //dot을 기준으로 뒤에 Index 다 잘라낸다. 
			}
			//새로운 이름으로 설정하기 
			String newName = sdf.format(new Date(currentTime))+"_"+rndNum+ext; //import할때 sql로 import하면 생성이 안됨. 
			newFile = new File(oldFile.getParent(),newName); //파일객체 생성 
			
		}while(!createNewFile(newFile)); 
		return newFile;
		
	}
	
	private boolean createNewFile(File newFile) {
		try {
			return newFile.createNewFile();
		}catch(IOException e) {
			return false;
		}
	}
}
