package com.dongxin.image.password.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.dongxin.image.password.BitmapExecute;
import com.dongxin.image.password.app.ImagePasswordConstants;

/**
 * @author Wang Donghua Oct 15, 2014
 * 
 */
@SuppressWarnings("serial")
public class DecryptionPanel extends JPanel {

	private FileScrollPanel northPanel = null;

	private FileScrollPanel southPanel = null;

	public DecryptionPanel() {
		super();
		this.setLayout(new BorderLayout());
		init();
	}

	private void init() {
		northPanel = new FileScrollPanel();
		southPanel = new FileScrollPanel();

		JPanel buttonPanel = initButtonPanel();
		buttonPanel.setPreferredSize(new Dimension(ImagePasswordConstants.Frame_Width, 100));

		this.add(northPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	private JPanel initButtonPanel() {
		JPanel buttonPanel = new JPanel();

		SelectFileButton imageFileBtn = new SelectFileButton("Select Password Image");
		imageFileBtn.setFileSelectedListener(northPanel);

		// SelectFileButton txtFileBtn = new
		// SelectFileButton("Select Password File");
		// txtFileBtn.setFileSelectedListener(southPanel);

		JButton encryptionBtn = new JButton("Decryption");
		encryptionBtn.addActionListener(new DecryptionListener());

		buttonPanel.add(imageFileBtn);
		// buttonPanel.add(txtFileBtn);
		buttonPanel.add(encryptionBtn);

		return buttonPanel;
	}

	private class DecryptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (northPanel.getFileFullPath() == null) {
				System.err.println("Please select file");
				return;
			}
			try {
				BitmapExecute.BMPToDataSource(northPanel.getFileFullPath(),
						PanelUtil.createDecryptionTxtFileName(northPanel.getFileFullPath()));
			} catch (IOException exp) {
				exp.printStackTrace();
			}
		}
	}
}
