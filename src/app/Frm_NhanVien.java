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
	private Panel panel;
	private String sHeaderTenNV;
	private String sHeaderMaNV;
	private JTextField textField;
	
	public Panel getPanel() {
		return panel;
	}
	
	public  Frm_NhanVien(String sHeaderTenNV, String sHeaderMaNV) {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1281, 630);
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(150, 192, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(150, 146, 89, 23);
		panel.add(btnNewButton_1);
		
		
		
		
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
	}
}
