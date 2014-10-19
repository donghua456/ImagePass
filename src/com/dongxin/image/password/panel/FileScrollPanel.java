/**
 * 
 */
package com.dongxin.image.password.panel;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dongxin.image.password.app.ImagePasswordConstants;

/**
 * @author Wang Donghua Oct 15, 2014
 * 
 */
@SuppressWarnings("serial")
public class FileScrollPanel extends JScrollPane implements FileSelectedListener {

	private JTextArea filePathArea = null;

	public FileScrollPanel() {
		super();
		filePathArea = new JTextArea();
		filePathArea.setEditable(false);
		getViewport().add(filePathArea);
		setPreferredSize(new Dimension(ImagePasswordConstants.Frame_Width, 250));
	}

	public String getFileFullPath() {
		return filePathArea.getText();
	}

	private void setFileFullPath(String fileName) {
		filePathArea.setText(fileName);
	}

	@Override
	public void fileSelected(String fileName) {
		setFileFullPath(fileName);
	}

}
