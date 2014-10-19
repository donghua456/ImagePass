/**
 * 
 */
package com.dongxin.image.password.panel;

import java.io.File;

/**
 * @author Wang Donghua Oct 16, 2014
 * 
 */
public class PanelUtil {

	public static String getFileExtension(String fileName) {
		String extension = null;
		int lastDot = fileName.lastIndexOf(".");
		if (lastDot != -1) {
			extension = fileName.substring(lastDot + 1);
		}
		return extension;
	}
	
	public static String createPassImageFileName(String fullFilePath){
		String passImageFilePath = null;
		
		int lastFileSeparator = fullFilePath.lastIndexOf(File.separator);
		String filePath = fullFilePath.substring(0, lastFileSeparator);
		String fullFileName = fullFilePath.substring(lastFileSeparator + 1);
		String fileName = getFileName(fullFileName);
		passImageFilePath = filePath + File.separator + fileName + PanelConstants.Pass_Image_FileName_Suffix + "." + PanelConstants.BMP_File_Type;
		
		return passImageFilePath;
	}
	
	public static String createDecryptionTxtFileName(String fullFilePath){
		String decryptionTxtPath = null;
		
		int lastFileSeparator = fullFilePath.lastIndexOf(File.separator);
		String filePath = fullFilePath.substring(0, lastFileSeparator);
		String fullFileName = fullFilePath.substring(lastFileSeparator + 1);
		String fileName = getFileName(fullFileName);
		decryptionTxtPath = filePath + File.separator + fileName + PanelConstants.Pass_Txt_FileName_Suffix + "." + PanelConstants.Txt_File_Type;
		
		return decryptionTxtPath;		
	}
	
	private static String getFileName(String fullFileName) {
		String fileName = null;
		int lastDot = fullFileName.lastIndexOf(".");
		if (lastDot != -1) {
			fileName = fullFileName.substring(0, lastDot);
		}
		return fileName;
	}
}
