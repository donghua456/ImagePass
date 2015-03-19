package com.dongxin.image.password.panel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dongxin.image.password.panel.PanelConstants.PasswordDlgMode;

/**
 * @author Wang Donghua Oct 29, 2014
 *
 */
@SuppressWarnings("serial")
public class PasswordDialog extends JDialog {
	
	private String password = null;
	
	private boolean isSetPassword = false;
	
	private JTextField tfPassword = null;
	
	private JCheckBox cbSetPassword = null;
	
	private JButton btnOk = null;
	
	private JButton btnCancel = null;

	public static PasswordDialog showPasswordDlg(JFrame parent, PasswordDlgMode dlgMode){
		return new PasswordDialog(parent, dlgMode);
	}
	
	private PasswordDialog(JFrame parent, PasswordDlgMode dlgMode){
		super(parent, true);
		initUI(dlgMode);
		this.setTitle(PanelConstants.PasswordDlg_Title);
		this.setSize(400, 132);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initUI(PasswordDlgMode dlgMode){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));

		JPanel passwordPanel = new JPanel(new FlowLayout());
		JLabel passLabel = new JLabel("Password: ");
		tfPassword = new JTextField();
		tfPassword.setPreferredSize(new Dimension(100, 20));
		passwordPanel.add(passLabel);
		passwordPanel.add(tfPassword);
		
		contentPane.add(passwordPanel);
		
		if(PasswordDlgMode.Encryption == dlgMode){
			cbSetPassword = new JCheckBox("is need password?");
			contentPane.add(cbSetPassword);
		}
		
		JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controlPane.setBackground(new Color(175, 175, 175));
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
		controlPane.add(btnOk);
		controlPane.add(btnCancel);
		contentPane.add(controlPane);
	}
	
	public static void main(String[] args){
		PasswordDialog.showPasswordDlg(null, PasswordDlgMode.Decryption);
	}
}
