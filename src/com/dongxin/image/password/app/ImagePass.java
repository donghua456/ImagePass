package com.dongxin.image.password.app;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.dongxin.image.password.app.ImagePasswordConstants.ImagePassMode;
import com.dongxin.image.password.panel.DecryptionPanel;
import com.dongxin.image.password.panel.EncryptionPanel;

/**
 * @author Wang Donghua Oct 9, 2014
 * 
 */
@SuppressWarnings("serial")
public class ImagePass extends JFrame implements ModeChangeListener {

	private static ImagePass instance = null;

	private EncryptionPanel encryptionPanel = null;
	private DecryptionPanel decryptionPanel = null;

	public static ImagePass getInstance() {
		if (instance == null) {
			instance = new ImagePass();
		}
		return instance;
	}

	private ImagePass() {
		initMainFrame();
		initMenu();
		initContentPanel();
	}

	private void initMainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(ImagePasswordConstants.Frame_Title);
		this.setSize(new Dimension(800, 600));
	}

	private void initMenu() {
		ImagePassMenuBar menu = new ImagePassMenuBar(this);
		this.setJMenuBar(menu);
	}

	private void initContentPanel() {
		encryptionPanel = new EncryptionPanel();
		this.setContentPane(encryptionPanel);
	}

	private void initDecryptionPanel() {
		decryptionPanel = new DecryptionPanel();
		this.setContentPane(decryptionPanel);
	}

	public static void main(String[] args) {
		ImagePass mianFrame = getInstance();
		mianFrame.setVisible(true);
	}

	@Override
	public void modeChanged(ImagePassMode mode) {
		if (ImagePassMode.Encryption == mode) {
			if (decryptionPanel != null) {
				decryptionPanel.setVisible(false);
				this.remove(decryptionPanel);
			}
			encryptionPanel.setVisible(true);
			this.setContentPane(encryptionPanel);
//			System.err.println("Change to encryption");
		} else if (ImagePassMode.Decryption == mode) {
			if (encryptionPanel != null) {
				encryptionPanel.setVisible(false);
				this.remove(encryptionPanel);
			}
			if (decryptionPanel == null) {
				initDecryptionPanel();
			}
			decryptionPanel.setVisible(true);
			this.setContentPane(decryptionPanel);

//			System.err.println("Change to decryption");
		}

	}
}
