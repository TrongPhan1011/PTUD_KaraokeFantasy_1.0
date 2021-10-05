package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Frm_PhongMatHang extends JPanel {

	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	
	
	public Panel getFrmPhong() {
		return this.pMain;
	}
	public Frm_PhongMatHang(String sHeaderTenNV, String sHeaderMaNV) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(197, 0, 48, 14);
		pMain.add(lblNewLabel);
	}
}
