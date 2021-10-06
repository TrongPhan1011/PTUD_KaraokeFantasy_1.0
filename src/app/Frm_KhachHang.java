package app;

import java.awt.Color;
import java.awt.Panel;

import java.awt.Toolkit;

import java.sql.Date;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class Frm_KhachHang extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private Panel pMain;
	private JTextField textFieldTK;
	private JTextField textFieldHoTen;
	private JTextField textFieldSDT;
	private JTextField textFieldAddress;

	public Panel getFrmKH() {
		return this.pMain;
	}
	public Frm_KhachHang(String sHeaderTenNV, String sHeaderMaNV,Date dNgayHienTai) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblTimKiem = new JLabel("Tìm Kiếm:");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiem.setBounds(374, 13, 90, 35);
		pMain.add(lblTimKiem);
		
		textFieldTK = new JTextField();
		textFieldTK.setBounds(474, 12, 281, 33);
		textFieldTK.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldTK);
		textFieldTK.setColumns(10);
		
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
		
		JLabel lblNhac1 = new JLabel("");

		lblNhac1.setBounds(42, 98, 179, 141);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(249, 98, 72, 14);
		pMain.add(lblHoTen);
		
		textFieldHoTen = new JTextField();
		textFieldHoTen.setBounds(342, 98, 189, 20);
		textFieldHoTen.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldHoTen);
		textFieldHoTen.setColumns(10);
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(249, 147, 46, 14);
		pMain.add(lblSDT);
		
		textFieldSDT = new JTextField();
		textFieldSDT.setBounds(342, 146, 189, 20);
		textFieldSDT.setBorder(new LineBorder(new Color(114, 23 ,153),2 , true));
		pMain.add(textFieldSDT);
		textFieldSDT.setColumns(10);
		
		JLabel lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAddress.setBounds(249, 195, 72, 14);
		pMain.add(lblAddress);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setForeground(Color.BLACK);
		textFieldAddress.setBackground(Color.WHITE);
		textFieldAddress.setBounds(342, 194, 189, 97);
		textFieldAddress.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(596, 98, 124, 14);
		pMain.add(lblLoaiKH);
		
		JComboBox loaiKH = new JComboBox();
		loaiKH.setBounds(741, 98, 124, 22);
		//loaiKH.setForeground(new Color(255, 255, 255));
		loaiKH.setBackground(new Color(255, 255, 255));
		loaiKH.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(loaiKH);
		
		loaiKH.addItem("Thường");
		loaiKH.addItem("Thành viên");
		loaiKH.addItem("VIP");
		loaiKH.setSelectedItem("");
		
		
		
	}
}
