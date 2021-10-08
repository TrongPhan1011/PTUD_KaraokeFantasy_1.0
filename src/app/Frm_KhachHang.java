package app;

import java.awt.Color;
import java.awt.Panel;

import java.awt.Toolkit;

import java.sql.Date;


import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	private JTextField textFieldCccd;
	private JTextField textFieldPoint;
	private JTable tableKH;



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
		
		
		JLabel lblQuanLyKH = new JLabel("Quản lý khách hàng");
		lblQuanLyKH.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQuanLyKH.setBounds(31, 11, 255, 33);
		pMain.add(lblQuanLyKH);
		

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

		lblNhac1.setBounds(31, 160, 147, 146);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(146, 65, 90, 19);
		pMain.add(lblHoTen);
		
		textFieldHoTen = new JTextField();
		textFieldHoTen.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldHoTen.setBounds(239, 62, 189, 28);
		textFieldHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(textFieldHoTen);
		textFieldHoTen.setColumns(10);
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(146, 106, 46, 14);
		pMain.add(lblSDT);
		
		textFieldSDT = new JTextField();
		textFieldSDT.setBounds(239, 101, 189, 28);
		textFieldSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(textFieldSDT);
		textFieldSDT.setColumns(10);
		
		JLabel lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAddress.setBounds(146, 144, 72, 20);
		pMain.add(lblAddress);
		
		JLabel lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(473, 65, 124, 19);
		pMain.add(lblLoaiKH);
		
		JComboBox <String> loaiKH = new JComboBox<String>();
		loaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		loaiKH.setBounds(631, 60, 124, 27);
		//loaiKH.setForeground(new Color(255, 255, 255));
		loaiKH.setBackground(new Color(255, 255, 255));
		String cbbLoaiKH [] = {"Thường", "Thành viên", "VIP"};
		for(int i = 0;i < cbbLoaiKH.length; i++) {
			loaiKH.addItem(cbbLoaiKH[i]);
		}

		pMain.add(loaiKH);
		
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCccd.setBounds(473, 108, 65, 14);
		pMain.add(lblCccd);
		
		textFieldCccd = new JTextField();
		textFieldCccd.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldCccd.setBounds(631, 98, 124, 28);
		
		pMain.add(textFieldCccd);
		textFieldCccd.setColumns(10);
		textFieldCccd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioiTinh.setBounds(473, 149, 72, 14);
		pMain.add(lblGioiTinh);
		
		JComboBox<String> gioiTinh = new JComboBox<String>();
		gioiTinh.setBounds(631, 136, 124, 28);
		gioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		gioiTinh.setBackground(Color.WHITE);
		String cbbGioiTinh [] = {"Nam", "Nữ"};
		for(int i = 0;i < cbbGioiTinh.length; i++) {
			gioiTinh.addItem(cbbGioiTinh[i]);
		}
		pMain.add(gioiTinh);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgaySinh.setBounds(840, 63, 102, 18);
		pMain.add(lblNgaySinh);
		
		JLabel lblNgayDangKy = new JLabel("Ngày đăng ký:");
		lblNgayDangKy.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgayDangKy.setBounds(840, 103, 102, 20);
		pMain.add(lblNgayDangKy);
		
		JLabel lblDiem = new JLabel("Điểm tích lũy:");
		lblDiem.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiem.setBounds(840, 148, 102, 18);
		pMain.add(lblDiem);
		
		
		textFieldPoint = new JTextField();
		textFieldPoint.setBounds(952, 145, 185, 28);
		textFieldPoint.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(textFieldPoint);
		textFieldPoint.setColumns(10);
		
		
		JComboBox<String> cbbNgaySinh = new JComboBox<String>();
		cbbNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNgaySinh.setBackground(Color.white);
		cbbNgaySinh.setBounds(952, 64, 47, 26);
		for(int i = 1;i <=31; i++) {
			cbbNgaySinh.addItem(""+i);
		}
		pMain.add(cbbNgaySinh);
		
		JComboBox<String> cbbThangSinh = new JComboBox<String>();
		cbbThangSinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThangSinh.setBackground(Color.white);
		cbbThangSinh.setBounds(1009, 63, 46, 27);
		for(int i = 1; i <= 12;i++) {
			cbbThangSinh.addItem(""+i);
		}
		pMain.add(cbbThangSinh);
		
		JComboBox<String> cbbNamSinh = new JComboBox<String>();
		cbbNamSinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamSinh.setBackground(Color.white);
		cbbNamSinh.setBounds(1065, 63, 72, 27);
		for(int i = 2004; i > 1900; i--) {
			cbbNamSinh.addItem(""+i);
		}
		pMain.add(cbbNamSinh);

		
		JButton btnThemKH = new JButton("Thêm");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemKH.setBackground(new Color(114, 23, 153));
		btnThemKH.setBounds(318, 222, 108, 35);
		pMain.add(btnThemKH);
		
		JButton btnSuaKH = new JButton("Sửa");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 23, 153));
		btnSuaKH.setBounds(486, 222, 108, 35);
		pMain.add(btnSuaKH);
		
		JButton btnXoaKH = new JButton("Xóa");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 23, 153));
		btnXoaKH.setBounds(651, 222, 108, 35);
		pMain.add(btnXoaKH);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnReset.setBackground(new Color(114, 23, 153));
		btnReset.setBounds(821, 222, 108, 35);
		pMain.add(btnReset);
		
		
		
		JScrollPane scrollPaneKH = new JScrollPane();
		scrollPaneKH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneKH.setBackground(new Color(164, 44, 167));
		scrollPaneKH.setBounds(31, 329, 1212, 266);
		pMain.add(scrollPaneKH);
		
		String col [] = {"Mã KH", "Họ và tên KH", "Loại KH", "Giới tính","Ngày sinh","Địa chỉ", "SĐT", "CCCD","Ngày đăng ký","Điểm tích lũy"};
		DefaultTableModel modelKhachHang = new DefaultTableModel(col,0);
		tableKH = new JTable(modelKhachHang);
		
		
		JTableHeader tbHeader = tableKH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		
		tableKH.getColumnModel().getColumn(0).setPreferredWidth(55);
		tableKH.getColumnModel().getColumn(1).setPreferredWidth(96);
		tableKH.getColumnModel().getColumn(3).setPreferredWidth(59);
		tableKH.getColumnModel().getColumn(4).setPreferredWidth(74);
		tableKH.setShowGrid(false);
		tableKH.setShowHorizontalLines(false);
		tableKH.setBackground(Color.WHITE);
		tableKH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableKH.setSelectionBackground(new Color(164, 44, 167,30));
		scrollPaneKH.setViewportView(tableKH);
		
		JTextArea textAreaDiaChi = new JTextArea();
		textAreaDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		textAreaDiaChi.setBounds(239, 140, 189, 51);
		pMain.add(textAreaDiaChi);
		
		JComboBox<String> cbbNgayDangKy = new JComboBox<String>();
		cbbNgayDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNgayDangKy.setBackground(Color.WHITE);
		cbbNgayDangKy.setBounds(952, 102, 47, 27);
		for(int i = 1; i <= 31; i++ ) {
			cbbNgayDangKy.addItem(""+i);
		}
		pMain.add(cbbNgayDangKy);
		
		JComboBox<String> cbbThangDangKy = new JComboBox<String>();
		cbbThangDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThangDangKy.setBackground(Color.WHITE);
		cbbThangDangKy.setBounds(1009, 102, 46, 27);
		for(int i =1; i <=12; i++) {
			cbbThangDangKy.addItem(""+i);
		}
		pMain.add(cbbThangDangKy);
		
		
		
		
		JComboBox<String> cbbNamDangKy = new JComboBox<String>();
		cbbNamDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamDangKy.setBackground(Color.WHITE);
		int namHienTai = dNgayHienTai.getYear();
		int namBatDau = namHienTai - 10;
		for(int i = namBatDau; i <= namHienTai; i++) {
			cbbNamDangKy.addItem(""+i);
		}
		cbbNamDangKy.setBounds(1065, 102, 72, 27);
		pMain.add(cbbNamDangKy);
		
		JPanel pSapXep = new JPanel();
		pSapXep.setBackground(new Color(255,255,255));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "S\u1EAFp x\u1EBFp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(239, 267, 816, 51);
		pMain.add(pSapXep);
		//pSapXep.setLayout(null);
		
		JComboBox<String> cbbSort = new JComboBox<String>();
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(Color.WHITE);
		String cbSort [] = {"Tăng dần", "Giảm dần"};
		for(int i = 0; i< cbSort.length; i++) {
			cbbSort.addItem(cbSort[i]);
		}
		pSapXep.setLayout(null);
		cbbSort.setBounds(43, 14, 136, 28);
		pSapXep.add(cbbSort);
		
		JRadioButton rdbtnTheoMaKH = new JRadioButton("Theo mã khách hàng");
		rdbtnTheoMaKH.setSelected(true);
		rdbtnTheoMaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoMaKH.setBackground(Color.WHITE);
		rdbtnTheoMaKH.setBounds(211, 15, 167, 27);
		pSapXep.add(rdbtnTheoMaKH);
		
		JRadioButton rdbtnTheoTenKH = new JRadioButton("Theo tên khách hàng");
		rdbtnTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoTenKH.setBackground(Color.WHITE);
		rdbtnTheoTenKH.setBounds(420, 15, 171, 27);
		pSapXep.add(rdbtnTheoTenKH);
		
		JRadioButton rdbtnTheoLoaiKH = new JRadioButton("Theo loại khách hàng");
		rdbtnTheoLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoLoaiKH.setBackground(Color.WHITE);
		rdbtnTheoLoaiKH.setBounds(616, 15, 171, 27);
		pSapXep.add(rdbtnTheoLoaiKH);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTheoMaKH);
		bg.add(rdbtnTheoTenKH);
		bg.add(rdbtnTheoLoaiKH);
		rdbtnTheoMaKH.setSelected(true);

		
		
		
		
	}
}
