package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frm_QLBH extends JPanel {

	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private JTextField textField;
	
	public Panel getFrmQLBH() {
		return this.pMain;
	}
	
	public Frm_QLBH(String sHeaderTenNV, String sHeaderMaNV) {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(114, 0, 96, 20);
		pMain.add(textField);
		textField.setColumns(10);
		
	}
}
