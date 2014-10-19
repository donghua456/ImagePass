package com.dongxin.image.password;

import java.io.IOException;

/**
 * @author Wang Donghua Jun 25, 2014
 * 
 */
public class BMPHiedData {
	public static void main(String[] args) {
		try {
			BitmapExecute.DataSourceToBMP("C:/Users/Z0034NBS/Desktop/LSCUI/txthiden.txt",
					"C:/Users/Z0034NBS/Desktop/LSCUI/txthiden.bmp", "C:/Users/Z0034NBS/Desktop/LSCUI/txthiden2.bmp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 导出bmp中隐藏的数据

		try {
			BitmapExecute.BMPToDataSource("C:/Users/Z0034NBS/Desktop/LSCUI/txthiden2.bmp",
					"C:/Users/Z0034NBS/Desktop/LSCUI/txthiden2.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
	}
}
