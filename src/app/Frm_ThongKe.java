package app;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Frm_ThongKe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	
	
	public Panel getFrmThongKe() {
		return this.pMain;
	}
	public Frm_ThongKe(String sHeaderTenNV, String sHeaderMaNV) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(123, 0, 99, 23);
		pMain.add(chckbxNewCheckBox);
	}
}
