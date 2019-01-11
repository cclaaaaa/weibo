package com.weibo.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	public static String uploadPhoto(MultipartFile file,String originalFilename) throws IllegalStateException, IOException{
		if (file != null && originalFilename != null && originalFilename.length() > 0) {

			// 存储图片的物理路径
			String pic_path = "F:\\upload\\userface\\";

			// 新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 新图片
			File newFile = new File(pic_path + newFileName);

			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
			return newFileName;

		}
		return null;
		
	}
}
