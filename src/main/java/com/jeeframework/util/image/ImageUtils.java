package com.jeeframework.util.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageUtils {

    /**
	 * 通过图片文件二进制判断图片类型
	 * @param imageContent
	 * @return
     */
	public static String getImageType(byte[] imageContent){
		String type = "";
		ByteArrayInputStream byteInput = null;
		MemoryCacheImageInputStream memoryInput = null;
		byteInput = new ByteArrayInputStream(imageContent);
		memoryInput = new MemoryCacheImageInputStream(byteInput);
		Iterator<?> itr = ImageIO.getImageReaders(memoryInput);
		while (itr.hasNext()) {
			ImageReader reader = (ImageReader) itr.next();
			try {
				type = reader.getFormatName();
			} catch (IOException e) {			
				//e.printStackTrace();
			}
		}

		return type;
	}

	/**
	 * 检查图片类型是否是 imageType
	 * @param imageContent
	 * @param imageType
     * @return
     */
	public static boolean checkImageType(byte[] imageContent, String imageType){
		return imageType.equalsIgnoreCase(getImageType(imageContent));
	}
}
