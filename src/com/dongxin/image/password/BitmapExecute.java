/**
 * 
 */
package com.dongxin.image.password;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * @author Wang Donghua Jun 25, 2014
 * 
 */
public class BitmapExecute {

	private static int Data_Start_Flag = 91;
	private static int Data_End_Flag = 93;
	private static int Data_Size_Error = -1;
	private static int Data_Size_Bits = 32;

	/**
	 * 将数据文件隐藏入bmp文件
	 * 
	 * @param dataFileName
	 * @param bmpFileName
	 * @param outFileName
	 * @return
	 * @throws IOException
	 */
	public static boolean DataSourceToBMP(String dataFileName, String bmpFileName, String outFileName)
			throws IOException {
		return DataSourceToBMP(new File(dataFileName), new File(bmpFileName), outFileName);
	}

	/**
	 * 将数据文件隐藏入bmp文件
	 * 
	 * @param dataFileName
	 * @param bmpFileName
	 * @param outFileName
	 * @return
	 * @throws IOException
	 */
	private static boolean DataSourceToBMP(File dataFile, File bmpFile, String outFileName) throws IOException {
		FileInputStream dataStream = new FileInputStream(dataFile);
		BufferedImage bmp;
		try {
			bmp = ImageIO.read(bmpFile);

			if (dataStream.available() == 0) {
				return false;
			}
			int maxByteStorage = (bmp.getHeight() * bmp.getWidth() * 3) / 8;

			// bmp文件必须较要隐藏的文件为大，否则无法注入文件
			if (maxByteStorage < dataStream.available() + 500) {
				System.err.println("bmp文件太小");
				return false;
			}
			BitmapOutput bmpWriter = new BitmapOutput(bmp);

			dataSourceToBMP(dataStream, bmpWriter);
			
			saveBMP(bmpWriter, outFileName);
		} catch (Exception ex) {
			ex.getStackTrace();
			return false;
		} finally {
			if (dataStream != null) {
				dataStream.close();
			}
		}
		return true;
	}
	
	private static void dataSourceToBMP(FileInputStream dataStream, BitmapOutput bmpWriter) throws Exception {
		int dataSize = dataStream.available();
		System.out.println("data size: " + dataSize);

		dataSizeToBMP(dataSize, bmpWriter);

		// 标记出完整数
		System.out.print("read a byte: ");
		bmpWriter.writeByte(Data_Start_Flag);
		for (int u = 0; u < dataSize; u++) {
			int result = dataStream.read();

			System.out.print(result + ", ");

			if (result == Data_Start_Flag) {
				bmpWriter.writeByte(123);
			} else if (result == Data_End_Flag) {
				bmpWriter.writeByte(125);
			} else {
				bmpWriter.writeByte(result);
			}
		}
		bmpWriter.writeByte(Data_End_Flag);

	}
	
	private static void dataSizeToBMP(int dataSize, BitmapOutput bmpWriter) {
		int eightNum = 0; //8 bit int value: 255
		if (dataSize <= 255) {
			bmpWriter.writeByte(dataSize);
		} else {
			eightNum = dataSize / 255;
			for (int i = 0; i < eightNum; i++) {
				bmpWriter.writeByte(255);
			}
			int lastNum = (dataSize % 255);
			bmpWriter.writeByte(lastNum);
		}
		
		for(;(eightNum + 1) <= Data_Size_Bits; eightNum++ ){
			bmpWriter.writeByte(0);
		}
	}
	
	private static void saveBMP(BitmapOutput bmpWriter, String fileName) throws Exception {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}

		saveBMP(bmpWriter.getBufferedImage(), new File(fileName));
	}
	
	/**
	 * Save BufferedImage转化为bmp文件保存在指定位
	 * 
	 * @param image
	 * @param file
	 * @return
	 */
	private static void saveBMP(BufferedImage image, File file) throws Exception {
		Iterator writers = ImageIO.getImageWritersByFormatName("bmp");
		ImageWriter writer = (ImageWriter) writers.next();
		ImageOutputStream ios = null;

		ios = ImageIO.createImageOutputStream(new FileOutputStream(file));

		writer.setOutput(ios);

		writer.write(image);
	}

	/**
	 * 从bmp文件中导出隐藏数由于隐藏数据的方式不同，只对此类隐藏的有
	 * 
	 * @param bmpFileName
	 * @param outFName
	 * @return
	 * @throws IOException
	 */
	public static boolean BMPToDataSource(String bmpFileName, String outFName) throws IOException {
		return BMPToDataSource(new File(bmpFileName), outFName);
	}

	/**
	 * 从bmp文件中导出隐藏数由于隐藏数据的方式不同，只对此类隐藏的有
	 * 
	 * @param bmpFile
	 * @param outFName
	 * @return
	 * @throws IOException
	 */
	private static boolean BMPToDataSource(File bmpFile, String outFName) throws IOException {
		BufferedImage image = ImageIO.read(bmpFile);
		BitmapInput bmpReader;
		try {
			bmpReader = new BitmapInput(image);
		} catch (Exception ex) {
			return false;
		}
		FileOutputStream outStream;
		try {
			File file = new File(outFName);
			if (!file.exists()) {
				file.createNewFile();
			}
			outStream = new FileOutputStream(file);
		} catch (Exception ex) {
			return false;
		}
		int dataSize = 0;
		int outByte = 0;
		int count = 0;
		try {

///////////////////////////////////////////////////////////////////////////////////////////////////
			dataSize = getDataSize(bmpReader);
//			for (int u = 0; u < 500; u++) {
//				// 以对象数组返回body和验证布尔�?
//
//				Object[] object = bmpReader.readByte(outByte);
//				boolean header = Boolean.parseBoolean((String) object[0]);
//				outByte = Integer.parseInt((String) object[1]);
//				if (!header) {
//					throw new Exception();
//				}
//				dataSize |= (int) (outByte << 8 * 3);
//				System.err.println("one step data size: " + dataSize);
//				if (u != 3) {
//					dataSize >>= 8;
//				}
//			}
///////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			System.out.println();			
			System.out.println("bmp to txt, data size: " + dataSize);
			dataSize = dataSize + 2;//2 byte for data star and end flag
			
			for (int u = 0; u < dataSize; u++) {
				Object[] object = bmpReader.readByte(outByte);
				boolean header = Boolean.parseBoolean((String) object[0]);
				outByte = Integer.parseInt((String) object[1]);
				if (!header) {
					throw new Exception();
				}
				if (outByte == Data_End_Flag) {
					return true;
				}
				if (outByte == Data_Start_Flag) {
					count += 1;
				}
				if (count > 0) {
					if (outByte == 123) {
						outStream.write(91);
					} else if (outByte != Data_Start_Flag) {
						outStream.write(outByte);
					}
				}
			}
		} catch (Exception ex) {
			return false;
		} finally {
			try {
				outStream.flush();
				outStream.close();
				outStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private static int getDataSize(BitmapInput bmpReader) {
		int dataSize = 0;
		for (int i = 0; i < Data_Size_Bits; i++) {
			Object[] object = bmpReader.readByte(dataSize);
			boolean header = Boolean.parseBoolean((String) object[0]);
			dataSize += Integer.parseInt((String) object[1]);
			if (!header) {
				dataSize = Data_Size_Error;
				break;
			}
		}
		return dataSize;
	}
}
