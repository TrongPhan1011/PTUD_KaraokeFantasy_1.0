package app;

import java.awt.Color;

import java.awt.Panel;


import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class Frm_NhanVien extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private Panel pMain;
	private String sHeaderTenNV;
	private String sHeaderMaNV;
	private JTextField textField;
	private JButton btnNewButton_1;
	
	public Panel getPanel() {
		return pMain;
	}
	
	public  Frm_NhanVien(String sHeaderTenNV, String sHeaderMaNV) {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);

		
		textField = new JTextField();
		textField.setBounds(321, 1, 96, 20);
		pMain.add(textField);
		textField.setColumns(10);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(222, 0, 89, 23);
		pMain.add(btnNewButton_1);
		
		
		btnNewButton_1.addActionListener(this);
		
		
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object  object = e.getSource();
		if(object.equals(btnNewButton_1)) {
			System.exit(0);
		}
		
	}
	



}
