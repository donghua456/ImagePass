/**
 * 
 */
package com.dong.password.hiden.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

/**
 * @author Z0034NBS
 * 
 */
public class JPG2BMP {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		FileImageInputStream fiis = new FileImageInputStream(new File("d:/ImagePassFile/Aug2012.bmp"));
		FileImageOutputStream fios = new FileImageOutputStream(new File("d:/ImagePassFile/Aug2012.jpeg"));

		ImageReader jpegReader = null;
		Iterator<ImageReader> it1 = ImageIO.getImageReadersByFormatName("bmp");
		if (it1.hasNext()) {
			jpegReader = it1.next();
		}
		jpegReader.setInput(fiis);

		ImageWriter bmpWriter = null;
		Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName("jpg");
		if (it2.hasNext()) {
			bmpWriter = it2.next();
		}
		bmpWriter.setOutput(fios);
		BufferedImage br = jpegReader.read(0);
		bmpWriter.write(br);
		fiis.close();
		fios.close();
		System.out.println("Jpeg到bmp图片转换完成.");
	}
}
