package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frm_DonDatPhong extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	
	
	public Panel getFrmDDP() {
		return this.pMain;
	}
	public Frm_DonDatPhong(String sHeaderTenNV, String sHeaderMaNV) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(161, 7, 109, 23);
		pMain.add(rdbtnNewRadioButton);
	}
}
