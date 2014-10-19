/**
 * 
 */
package com.dongxin.image.password.app;

import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.dongxin.image.password.app.ImagePasswordConstants.ImagePassMode;

/**
 * @author Wang Donghua Oct 9, 2014
 * 
 */
@SuppressWarnings("serial")
public class ImagePassMenuBar extends JMenuBar {

	private final String Function_Menu = "Function";
	private final String Encryption_MenuItem = "Encryption";
	private final String Decryption_MenuItem = "Decryption";

	private JMenu functionMenu = null;
	private JCheckBoxMenuItem encryptionMenu = null;
	private JCheckBoxMenuItem decryptionMenu = null;

//	private boolean oldEncryptionState = false;
//	private boolean oldDecryptionState = false;

	private ModeChangeListener modeChangeListener = null;

	public ImagePassMenuBar(ModeChangeListener modeChangeListener) {
		super();
		this.modeChangeListener = modeChangeListener;
		initMenu();
	}

	private void initMenu() {
		initMenuItems();

		functionMenu = new JMenu(Function_Menu);
		functionMenu.add(encryptionMenu);
		functionMenu.add(decryptionMenu);

		this.add(functionMenu);
	}

//	private void initMenuItems() {
//		encryptionMenu = new JCheckBoxMenuItem(Encryption_MenuItem) {
//			protected void fireActionPerformed(ActionEvent event) {
//				super.fireActionPerformed(event);
//				if (decryptionMenu.getState()) {
//					decryptionMenu.setState(false);
//				}
//				if (!oldEncryptionState && this.getState()) {
//					modeChangeListener.modeChanged(ImagePassMode.Encryption);
//					oldEncryptionState = true;
//					oldDecryptionState = false;
//				}
//			}
//
//			public void doClick(int pressTime) {
//				if (oldEncryptionState) {
//					return;
//				}
//				super.doClick(pressTime);
//				System.err.println("Change to encryption");
//			}
//		};
//		encryptionMenu.setState(true);
//		oldEncryptionState = true;
//
//		decryptionMenu = new JCheckBoxMenuItem(Decryption_MenuItem) {
//			protected void fireActionPerformed(ActionEvent event) {
//				super.fireActionPerformed(event);
//				if (encryptionMenu.getState()) {
//					encryptionMenu.setState(false);
//				}
//				if (!oldDecryptionState && this.getState()) {
//					modeChangeListener.modeChanged(ImagePassMode.Decryption);
//					oldDecryptionState = true;
//					oldEncryptionState = false;
//				}
//			}
//
//			public void doClick(int pressTime) {
//				if (oldDecryptionState) {
//					return;
//				}
//				super.doClick(pressTime);
//				System.err.println("Change to decryption");
//			}
//		};
//	}
	
//	private void initMenuItems() {
//		encryptionMenu = new JCheckBoxMenuItem(Encryption_MenuItem) {
//			protected void fireActionPerformed(ActionEvent event) {
//				super.fireActionPerformed(event);
//				if (!oldEncryptionState && this.getState()) {
//					modeChangeListener.modeChanged(ImagePassMode.Encryption);
//					oldEncryptionState = true;
//					oldDecryptionState = false;
//				}
//			}
//		};
//		encryptionMenu.setState(true);
//		oldEncryptionState = true;
//
//		decryptionMenu = new JCheckBoxMenuItem(Decryption_MenuItem) {
//			protected void fireActionPerformed(ActionEvent event) {
//				super.fireActionPerformed(event);
//				if (!oldDecryptionState && this.getState()) {
//					modeChangeListener.modeChanged(ImagePassMode.Decryption);
//					oldDecryptionState = true;
//					oldEncryptionState = false;
//				}
//			}
//		};
//		
//		ButtonGroup group = new ButtonGroup();
//		group.add(encryptionMenu);
//		group.add(decryptionMenu);
//	}
	
	private void initMenuItems() {
		encryptionMenu = new ImagePassMenuItem(Encryption_MenuItem, true);
		decryptionMenu = new ImagePassMenuItem(Decryption_MenuItem, false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(encryptionMenu);
		group.add(decryptionMenu);
	}

	private class ImagePassMenuItem extends JCheckBoxMenuItem {
		private boolean oldState;

		public ImagePassMenuItem(String name, boolean state) {
			super(name);
			this.setState(state);
			this.oldState = state;
		}

//		public boolean getOldState() {
//			return oldState;
//		}
//
//		public void setOldState(boolean oldState) {
//			this.oldState = oldState;
//		}

		protected void fireActionPerformed(ActionEvent event) {
			super.fireActionPerformed(event);
			if (!oldState && this.getState()) {
				modeChangeListener.modeChanged(getMode());
				oldState = true;
			}
		}

		private ImagePassMode getMode() {
			ImagePassMode mode = null;
			if (ImagePassMode.Encryption.name().equals(this.getText())) {
				mode = ImagePassMode.Encryption;
			} else if (ImagePassMode.Decryption.name().equals(this.getText())) {
				mode = ImagePassMode.Decryption;
			}
			return mode;
		}

		public void doClick(int pressTime) {
			if (oldState && !this.getState()) {
				oldState = false;
			}
			super.doClick(pressTime);
		}
	}

}
