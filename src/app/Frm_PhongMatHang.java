package app;

import java.awt.Color;
import java.awt.Panel;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Frm_PhongMatHang extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	
	
	public Panel getFrmPhong() {
		return this.pMain;
	}
	public Frm_PhongMatHang(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(197, 0, 48, 14);
		pMain.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(90, 43, 48, 14);
		pMain.add(lblNewLabel_1);
	}
}
