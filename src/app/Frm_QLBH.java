package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JLabel lblNewLabel = new JLabel("Quản lý bán hàng");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(22, 11, 255, 33);
		pMain.add(lblNewLabel);
		
		
		
	}
}
