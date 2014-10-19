/**
 * 
 */
package com.dong.password.hiden.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Wang Donghua Sep 11, 2014
 * 
 */
public class TxtFileTest {

	public static void main(String[] args) {
		File txtFile = new File("D:/privateproject/ImageHiden/test1.txt");
		FileInputStream dataStream = null;
		try {
			dataStream = new FileInputStream(txtFile);
			int dataSize = dataStream.available();
			System.out.println("datasize bits: " + Integer.bitCount(dataSize));
			System.out.println("max integer bits: " + Integer.bitCount(Integer.MAX_VALUE));
			System.out.println("file size: " + dataSize);
			for (int u = 0; u < dataSize; u++) {
				int result = dataStream.read();
				System.out.println("Byte(" + u + ") is: " + result);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dataStream != null) {
					dataStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
