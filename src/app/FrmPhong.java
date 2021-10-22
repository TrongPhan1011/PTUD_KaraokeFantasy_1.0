package app;

import java.awt.Color;
import java.awt.Panel;
import java.sql.Date;

import javax.swing.JPanel;


public class FrmPhong extends JPanel {

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
	public FrmPhong(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		
	}
}
