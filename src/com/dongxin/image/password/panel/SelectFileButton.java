/**
 * 
 */
package com.dongxin.image.password.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.dongxin.image.password.app.ImagePass;

/**
 * @author Wang Donghua Oct 15, 2014
 * 
 */
@SuppressWarnings("serial")
public class SelectFileButton extends JButton {

	private String fileFullPath = null;

	private FileSelectedListener fileSelectedListener = null;

	public SelectFileButton(String btnName) {
		super(btnName);
		this.addActionListener(new FileChooseListener());
	}

	public SelectFileButton(String btnName, Icon icon) {
		super(btnName, icon);
		this.addActionListener(new FileChooseListener());
	}

	public void setFileSelectedListener(FileSelectedListener fileSelectedListener) {
		this.fileSelectedListener = fileSelectedListener;
	}

	@Override
	protected void fireActionPerformed(ActionEvent event) {
		super.fireActionPerformed(event);
		if (fileSelectedListener != null) {
			fileSelectedListener.fileSelected(fileFullPath);
		}
	}

	private class FileChooseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setFileFilter(new BMPFileFilter());

			int result = fileChooser.showOpenDialog(ImagePass.getInstance());
			if (result == JFileChooser.CANCEL_OPTION) {
				return;
			} else if (result == JFileChooser.APPROVE_OPTION) {
				fileFullPath = fileChooser.getSelectedFile().getPath();
			}
		}

	}

	private class BMPFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			if(f.isDirectory())
				return true;
			boolean result = false;
			String extension = PanelUtil.getFileExtension(f.getName());
			if (extension != null
					&& (PanelConstants.BMP_File_Type.equals(extension) || PanelConstants.Txt_File_Type
							.equals(extension))) {
				result = true;
			}
			return result;
		}

		@Override
		public String getDescription() {
			return "*.bmp, *.txt";
		}

	}

}
