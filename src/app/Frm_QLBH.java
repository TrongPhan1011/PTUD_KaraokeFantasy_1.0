package app;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import entity.LoaiPhong;
import entity.Phong;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

public class Frm_QLBH extends JPanel implements ActionListener {

	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private JTextField textField;
	private JTextField txtTim;
	
	public Panel getFrmQLBH() {
		return this.pMain;
	}
	
	public Frm_QLBH(String sHeaderTenNV, String sHeaderMaNV)  {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý bán hàng");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNewLabel.setBounds(37, 10, 255, 33);
		pMain.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(374, 13, 90, 35);
		pMain.add(lblNewLabel_1);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTim.setBounds(474, 12, 281, 33);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(txtTim);
		txtTim.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(786, 11, 98, 33);
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setForeground(Color.WHITE);
		Image imgTim = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
		JButton btnDSHD = new JButton("Xem toàn bộ hóa đơn");
		btnDSHD.setForeground(Color.WHITE);
		btnDSHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDSHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnDSHD.setBackground(new Color(114, 23, 153));
		btnDSHD.setBounds(1013, 11, 194, 33);
		pMain.add(btnDSHD);
		
		JLabel lblPhong = new JLabel("Phòng");
		lblPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPhong.setBounds(575, 51, 71, 26);
		pMain.add(lblPhong);
		
		JPanel pPhong = new JPanel();
		pPhong.setBackground(Color.LIGHT_GRAY);
		pPhong.setBounds(37, 88, 1191, 121);
		pMain.add(pPhong);
		pPhong.setLayout(null);
		
//		JScrollBar scrollBar = new JScrollBar(pPhong);
//		scrollBar.setBounds(1174, 0, 17, 121);
//		pPhong.add(scrollBar);
		
//		JLabel lblSapXep = new JLabel("Sắp xếp:");
//		lblSapXep.setFont(new Font("SansSerif", Font.BOLD, 14));
//		lblSapXep.setBounds(494, 80, 90, 35);
//		pMain.add(lblSapXep);
//		
//		JComboBox cbSapXep = new JComboBox();
//		cbSapXep.setBackground(Color.WHITE);
//		cbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cbSapXep.setBorder(new LineBorder(new Color(0, 146, 182), 1, true));
//		cbSapXep.setBounds(610, 80, 145, 30);
//		String dsSX[] = {"Theo tên","Chờ thanh toán","Phòng trống"} ;
//		cbSapXep.addItem("Tất cả");
//		cbSapXep.setSelectedItem("Tất cả");
//		
//		
//		pMain.add(cbSapXep);
		
		Phong p1 = new Phong("P001", "Dang hoat dong",100000, new LoaiPhong("LP001","VIP"));
		
		
		
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
