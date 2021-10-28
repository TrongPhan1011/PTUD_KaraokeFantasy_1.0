package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.ConnectDB;
import dao.DAOKhachHang;
import dao.DAOLoaiKH;
import dao.DAOPhatSinhMa;
import entity.KhachHang;
import entity.LoaiKH;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
 
public class FrmKhachHang extends JPanel implements ActionListener, MouseListener,ItemListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private Panel pMain;
	private JTextField textFieldTK;
	private JButton btnTim;
	private JButton btnThemKH;
	private JButton btnSuaKH;
	private JButton btnXoaKH;
	private JButton btnReset;
	private JTextField textFieldHoTen;
	private JTextField textFieldSDT;
	private JTextField textFieldCccd;
	private JTextField textFieldPoint;
	private JTextArea textAreaDiaChi;
	private JTable tableKH;
	private DAOLoaiKH daoLoaiKH;
	private DAOKhachHang daoKhachHang;
	private DAOPhatSinhMa daoMaKH;
	private DefaultTableModel modelKhachHang;
	private JComboBox<String> cbbloaiKH;
	private JComboBox<String> cbbgioiTinh;
	private JComboBox<String> cbbNgayDangKy;
	private JComboBox<String> cbbThangDangKy;
	private JComboBox<String> cbbNamDangKy;
	private JComboBox<String> cbbNgaySinh;
	private JComboBox<String> cbbThangSinh;
	private JComboBox<String> cbbNamSinh;
	
	public Panel getFrmKH()  {
		return this.pMain;
	}
	public FrmKhachHang(String sHeaderTenNV, String sHeaderMaNV,Date dNgayHienTai) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		//connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//khai bao dao
		daoLoaiKH = new DAOLoaiKH();
		daoKhachHang = new DAOKhachHang();
		daoMaKH = new DAOPhatSinhMa();
		
		
		//Giao dien
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		
		JLabel lblQuanLyKH = new JLabel("Quản lý khách hàng");
		lblQuanLyKH.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQuanLyKH.setBounds(31, 11, 251, 33);
		pMain.add(lblQuanLyKH);
		

		JLabel lblTimKiem = new JLabel("Tìm Kiếm:");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiem.setBounds(374, 6, 90, 35);
		pMain.add(lblTimKiem);
		
		textFieldTK = new JTextField();
		textFieldTK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textFieldTK.getText().equals("Tìm khách hàng theo tên hoặc số điện thoại hoặc loại khách hàng")) {
					textFieldTK.setText("");
					textFieldTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					textFieldTK.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textFieldTK.getText().equals("")) {
					textFieldTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					textFieldTK.setText("Tìm khách hàng theo tên hoặc số điện thoại hoặc loại khách hàng");
					textFieldTK.setForeground(Color.lightGray);
				}
			}
		});
		textFieldTK.setText("Tìm khách hàng theo tên hoặc số điện thoại hoặc loại khách hàng");
		textFieldTK.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldTK.setForeground(Color.LIGHT_GRAY);
		textFieldTK.setBounds(474, 5, 281, 33);
		textFieldTK.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldTK);
		textFieldTK.setColumns(10);
		
		btnTim = new FixButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(786, 4, 98, 33);
		btnTim.setBackground(new Color(114, 23 ,153));
		//btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		//btnTim.setForeground(Color.WHITE);
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

		textFieldHoTen.setBounds(230, 62, 189, 28);
		textFieldHoTen.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		textFieldHoTen.setBounds(239, 62, 189, 28);
		textFieldHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(textFieldHoTen);
		textFieldHoTen.setColumns(10);
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(146, 106, 46, 14);
		pMain.add(lblSDT);
		
		textFieldSDT = new JTextField();
		textFieldSDT.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldSDT.setBounds(239, 100, 189, 28);
		textFieldSDT.setBorder(new LineBorder(new Color(114, 23 ,153),2 , true));
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
		
		cbbloaiKH = new JComboBox<String>();
		cbbloaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbloaiKH.setBounds(610, 60, 170, 27);
		cbbloaiKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		//loaiKH.setForeground(new Color(255, 255, 255));
		cbbloaiKH.setBackground(new Color(255, 255, 255));
		/*
		 * String cbbLoaiKH [] = {"Thường", "Thành viên", "VIP"}; for(int i = 0;i <
		 * cbbLoaiKH.length; i++) { loaiKH.addItem(cbbLoaiKH[i]); }
		 */

		pMain.add(cbbloaiKH);
		
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCccd.setBounds(473, 108, 65, 14);
		pMain.add(lblCccd);
		
		textFieldCccd = new JTextField();
		textFieldCccd.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldCccd.setBounds(610, 100, 170, 27);
		
		pMain.add(textFieldCccd);
		textFieldCccd.setColumns(10);
		textFieldCccd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioiTinh.setBounds(473, 149, 72, 14);
		pMain.add(lblGioiTinh);

		cbbgioiTinh = new JComboBox<String>();
		cbbgioiTinh.setBounds(610, 140, 170, 28);
		cbbgioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbgioiTinh.setBackground(Color.WHITE);
		cbbgioiTinh.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		String cbbGioiTinh [] = {"Nam", "Nữ"};
		for(int i = 0;i < cbbGioiTinh.length; i++) {
			cbbgioiTinh.addItem(cbbGioiTinh[i]);
		}
		pMain.add(cbbgioiTinh);
		
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
		textFieldPoint.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldPoint.setBounds(945, 145, 202, 28);
		textFieldPoint.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		textFieldPoint.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(textFieldPoint);
		textFieldPoint.setColumns(10);
		
		
		cbbNgaySinh = new JComboBox<String>();
		cbbNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNgaySinh.setBackground(Color.white);
		cbbNgaySinh.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbNgaySinh.setBounds(945, 61, 56, 26);
		for(int i = 1;i <=31; i++) {
			cbbNgaySinh.addItem(""+i);
		}
		pMain.add(cbbNgaySinh);
		
		cbbThangSinh = new JComboBox<String>();
		cbbThangSinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThangSinh.setBackground(Color.white);
		cbbThangSinh.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbThangSinh.setBounds(1009, 61, 56, 26);
		for(int i = 1; i <= 12;i++) {
			cbbThangSinh.addItem(""+i);
		}
		pMain.add(cbbThangSinh);
		
		cbbNamSinh = new JComboBox<String>();
		cbbNamSinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamSinh.setBackground(Color.white);
		cbbNamSinh.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbNamSinh.setBounds(1075, 61, 72, 27);
		for(int i = 2004; i > 1900; i--) {
			cbbNamSinh.addItem(""+i);
		}
		pMain.add(cbbNamSinh);

		btnThemKH = new FixButton("Thêm");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemKH.setBackground(new Color(114, 23, 153));
		btnThemKH.setBounds(319, 202, 108, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		btnSuaKH = new FixButton("Sửa");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 23, 153));
		btnSuaKH.setBounds(489, 202, 108, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaKH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaKH);
		
		btnXoaKH = new FixButton("Xóa");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 23, 153));
		btnXoaKH.setBounds(647, 202, 108, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaKH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaKH);
		
		btnReset = new FixButton("Làm mới");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnReset.setBackground(new Color(114, 23, 153));
		btnReset.setBounds(821, 202, 108, 35);
		Image imgLamMoiKH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconReset.png");
		Image resizeImgLamMoiKH = imgLamMoiKH.getScaledInstance(25, 25, 0);
		btnReset.setIcon(new ImageIcon(resizeImgLamMoiKH));
		
		pMain.add(btnReset);
		
		
		
		JScrollPane scrollPaneKH = new JScrollPane();
		scrollPaneKH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneKH.setBackground(new Color(164, 44, 167));
		scrollPaneKH.setBounds(31, 310, 1212, 285);
		pMain.add(scrollPaneKH);
		
		String col [] = {"Mã KH", "Họ và tên KH", "Loại KH", "Giới tính","Ngày sinh","Địa chỉ", "SĐT", "CCCD","Ngày đăng ký","Điểm tích lũy"};
		modelKhachHang = new DefaultTableModel(col,0);
		tableKH = new JTable(modelKhachHang);
		
		
		
		JTableHeader tbHeader = tableKH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		
		tableKH.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKH.getColumnModel().getColumn(1).setPreferredWidth(96);
		
		tableKH.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableKH.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableKH.getColumnModel().getColumn(5).setPreferredWidth(240);
		tableKH.getColumnModel().getColumn(6).setPreferredWidth(45);
		tableKH.getColumnModel().getColumn(7).setPreferredWidth(60);
		tableKH.getColumnModel().getColumn(8).setPreferredWidth(65);
		tableKH.getColumnModel().getColumn(9).setPreferredWidth(65);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableKH.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tableKH.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		tableKH.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tableKH.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		
		tableKH.setShowGrid(true);
		tableKH.setShowHorizontalLines(true);
		tableKH.setBackground(Color.WHITE);
		tableKH.setSelectionBackground(new Color(164, 44, 167,30));
		tableKH.setSelectionForeground(new Color(114, 23, 153));
		tableKH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableKH.setRowHeight(30);
		tableKH.setGridColor(getBackground());
		//tableKH.setOpaque(false);
		tableKH.setSelectionBackground(new Color(164, 44, 167,30));
		scrollPaneKH.setViewportView(tableKH);
		
//		demo dữ liệu:
		/*
		 * modelKhachHang.addRow(new Object[] {"123","123"}); modelKhachHang.addRow(new
		 * Object[] {"123","123"}); modelKhachHang.addRow(new Object[] {"123","123"});
		 * modelKhachHang.addRow(new Object[] {"123","123"}); modelKhachHang.addRow(new
		 * Object[] {"123","123"}); modelKhachHang.addRow(new Object[] {"123","123"});
		 * modelKhachHang.addRow(new Object[] {"123","123"}); modelKhachHang.addRow(new
		 * Object[] {"123","123"}); modelKhachHang.addRow(new Object[] {"123","123"});
		 * modelKhachHang.addRow(new Object[] {"123","123"});
		 */
		
		textAreaDiaChi = new JTextArea();
		textAreaDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textAreaDiaChi.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		textAreaDiaChi.setBounds(342, 145, 189, 51);
		textAreaDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		textAreaDiaChi.setBounds(239, 140, 189, 51);
		pMain.add(textAreaDiaChi);
		
		cbbNgayDangKy = new JComboBox<String>();
		cbbNgayDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNgayDangKy.setBackground(Color.WHITE);
		cbbNgayDangKy.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbNgayDangKy.setBounds(945, 102, 56, 27);
		for(int i = 1; i <= 31; i++ ) {
			cbbNgayDangKy.addItem(""+i);
		}
		pMain.add(cbbNgayDangKy);
		
		cbbThangDangKy = new JComboBox<String>();
		cbbThangDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThangDangKy.setBackground(Color.WHITE);
		cbbThangDangKy.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbThangDangKy.setBounds(1009, 102, 56, 27);
		for(int i =1; i <=12; i++) {
			cbbThangDangKy.addItem(""+i);
		}
		pMain.add(cbbThangDangKy);
		
		cbbNamDangKy = new JComboBox<String>();
		cbbNamDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamDangKy.setBackground(Color.WHITE);
		cbbNamDangKy.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		int namHienTai = dNgayHienTai.getYear();
		int namBatDau = namHienTai + 100;
		for(int i = namHienTai; i <= namBatDau; i++) {
			cbbNamDangKy.addItem(""+i);
		}
		cbbNamDangKy.setBounds(1075, 101, 72, 27);
		pMain.add(cbbNamDangKy);
		
		JPanel pSapXep = new JPanel();
		pSapXep.setBackground(new Color(238,239,243,90));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "S\u1EAFp x\u1EBFp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(239, 248, 816, 51);
		pMain.add(pSapXep);
		//pSapXep.setLayout(null);
		
		JComboBox<String> cbbSort = new JComboBox<String>();
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(Color.WHITE);
		cbbSort.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
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
		rdbtnTheoMaKH.setBackground(new Color(220,210,239));
		rdbtnTheoMaKH.setBounds(211, 15, 167, 27);
		pSapXep.add(rdbtnTheoMaKH);
		
		JRadioButton rdbtnTheoTenKH = new JRadioButton("Theo tên khách hàng");
		rdbtnTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoTenKH.setBackground(new Color(220,210,239));
		rdbtnTheoTenKH.setBounds(420, 15, 171, 27);
		pSapXep.add(rdbtnTheoTenKH);
		
		JRadioButton rdbtnTheoLoaiKH = new JRadioButton("Theo loại khách hàng");
		rdbtnTheoLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoLoaiKH.setBackground(new Color(220,210,239));
		rdbtnTheoLoaiKH.setBounds(616, 15, 171, 27);
		pSapXep.add(rdbtnTheoLoaiKH);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTheoMaKH);
		bg.add(rdbtnTheoTenKH);
		bg.add(rdbtnTheoLoaiKH);
		rdbtnTheoMaKH.setSelected(true);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1281, 606);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackground);

		//end giao dien
		
		//Load tên loại KH
		ArrayList<LoaiKH> lsLoaiKH = daoLoaiKH.getAllLoaiKH();
		for(LoaiKH lkh : lsLoaiKH) {
			cbbloaiKH.addItem(lkh.getTenLoaiKH());
		}
		
		//load danh sach khach hang
		loadDanhSachKH();
		
		btnThemKH.addActionListener(this);
		tableKH.addMouseListener(this);
		btnReset.addActionListener(this);
	}

//end main

		// Load danh sach tu du lieu
		public void loadDanhSachKH() {
			clearTable();
			SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
			ArrayList<KhachHang> lsKH = daoKhachHang.getDanhSachKH();
			for(KhachHang kh : lsKH) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(kh.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] {
				kh.getMaKhangHang(), kh.getTenKH(),loaiKH.getTenLoaiKH() , kh.getGioiTinh(), dfDate.format(kh.getNgaySinh()), kh.getDiaChi(), kh.getSdt(), kh.getCccd(), dfDate.format(kh.getNgayDangKy()).toString(), kh.getDiemTichLuy()
			});
		}
	}
		
		//Lam moi danh sach
		public void clearTable() {
			while (tableKH.getRowCount() > 0) {
				modelKhachHang.removeRow(0);
			}
		}
		
		//Nut them
		public void themKHVaoDanhSach() {
			if(textFieldHoTen.getText()!= "") {
				
				
				String maKH = daoMaKH.getMaKH();
				String tenKH = textFieldHoTen.getText().toString();
				String sdt = textFieldSDT.getText().toString();
				String diaChi = textAreaDiaChi.getText().toString();
				//String  = cbbloaiKH.getSelectedItem().toString();
				String cccd = textFieldCccd.getText().toString();
				String gioiTinh = cbbgioiTinh.getSelectedItem().toString();
				LoaiKH loaiKH = new LoaiKH( daoLoaiKH.getMaLoaiKHTheoTen(cbbloaiKH.getSelectedItem().toString()));
				int ngaySinh = Integer.parseInt(cbbNgaySinh.getSelectedItem().toString());
				int thangSinh = Integer.parseInt(cbbThangSinh.getSelectedItem().toString());
				int namSinh = Integer.parseInt(cbbNamSinh.getSelectedItem().toString());
				int ngayDangKy = Integer.parseInt(cbbNgayDangKy.getSelectedItem().toString());
				int thangDangKy = Integer.parseInt(cbbThangDangKy.getSelectedItem().toString());
				int namDangKy = Integer.parseInt(cbbNamDangKy.getSelectedItem().toString());
				int diemTichLuy = Integer.parseInt(textFieldPoint.getText().toString());
				
				
				@SuppressWarnings("deprecation")
				KhachHang kh= new KhachHang(maKH, tenKH, diaChi, sdt, cccd, new Date(ngaySinh, thangSinh, namSinh), gioiTinh, diemTichLuy, new Date(ngayDangKy, thangDangKy, namDangKy), loaiKH);
				daoKhachHang.themDanhSachKH(kh);
				loadDanhSachKH();
				resetAll();
			}
			else
				JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra họ tên!");
		}
		//Nút sửa
		public void suaThongTin() {
			
		}
		//Làm mới
		public void resetAll() {
			textFieldHoTen.setText("");
			textFieldSDT.setText("");
			textFieldCccd.setText("");
			textAreaDiaChi.setText("");
			cbbloaiKH.setSelectedIndex(0);
			cbbgioiTinh.setSelectedIndex(0);
			cbbNgaySinh.setSelectedIndex(0);
			cbbThangSinh.setSelectedIndex(0);
			cbbNamSinh.setSelectedIndex(0);
			cbbNgayDangKy.setSelectedIndex(0);
			cbbThangDangKy.setSelectedIndex(0);
			cbbNamDangKy.setSelectedIndex(0);
			textFieldPoint.setText("");
		}
		//Tìm kiếm
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
		@SuppressWarnings("deprecation")
		@Override
		//Hiển thị thông tin khi chọn vào bảng
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(tableKH)) {
			
			//JTextField tam = new JTextField();
			//KhachHang kh = daoKhachHang.getKHTheoMa(tam);
			int row = tableKH.getSelectedRow();
			textFieldHoTen.setText(modelKhachHang.getValueAt(row, 1).toString());
			cbbloaiKH.setSelectedItem(modelKhachHang.getValueAt(row, 2).toString());
			textFieldSDT.setText(modelKhachHang.getValueAt(row, 6).toString());
			textFieldCccd.setText(modelKhachHang.getValueAt(row, 7).toString());
			textAreaDiaChi.setText(modelKhachHang.getValueAt(row, 5).toString());
			cbbgioiTinh.setSelectedItem(modelKhachHang.getValueAt(row, 3).toString());
			textFieldPoint.setText(modelKhachHang.getValueAt(row, 9).toString());
			String ngaySinh = modelKhachHang.getValueAt(row, 4).toString();
			String ngayDangKy = modelKhachHang.getValueAt(row, 8).toString();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				java.util.Date d =  df.parse(ngaySinh);
				java.util.Date d1 =  df.parse(ngayDangKy);
				cbbNgaySinh.setSelectedItem(d.getDate()+"");
				cbbThangSinh.setSelectedItem(d.getMonth()+1 +"");
				cbbNamSinh.setSelectedItem(d.getYear()+1900 +"");
				cbbNgayDangKy.setSelectedItem(d1.getDate()+"");
				cbbThangDangKy.setSelectedItem(d1.getMonth()+1 +"");
				cbbNamDangKy.setSelectedItem(d1.getYear()+ 1900+"");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			
			
			}
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(btnThemKH)) {
				themKHVaoDanhSach();
			}
			if(o.equals(btnReset)) {
				resetAll();
			}
			
		}

		
}
