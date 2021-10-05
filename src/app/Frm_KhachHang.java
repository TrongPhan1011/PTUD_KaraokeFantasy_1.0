package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Frm_KhachHang extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;

	public Panel getFrmKH() {
		return this.pMain;
	}
	public Frm_KhachHang(String sHeaderTenNV, String sHeaderMaNV) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(25, 11, 116, 23);
		pMain.add(btnNewButton);
		
		
		
	}
}
