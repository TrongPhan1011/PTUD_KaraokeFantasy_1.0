package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Frm_PhongMatHang extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JTextField textFieldTK;
	private JTextField textField;
	private JLabel lblGiaBan;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtSoLuong;
	private JTextField txtTenMH;
	private JTextField txtDonGia;
	private JTextField txtGiaP;
	private JRadioButton rdQLMH;
	private JRadioButton rdQLP;
	private JTable tableMH;
	private DefaultTableModel modelMatHang;
	private JTable tbPhong;
	private DefaultTableModel modelPhong;
	private JPanel pRadio;
	private JPanel pSX;
	private Box b;
	
	
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
		////////////////
		//Tim Kiem//////
		////////////////
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
		//--- 
		////////////////
		//QLBH//////
		////////////////
		JPanel pDichVu = new JPanel();
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Quản lý bán hàng ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(new Color(238,239,243,90));
		pDichVu.setBounds(37, 60, 468, 170);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		//---
		
		JLabel lblSubTenMH = new JLabel("Tên mặt hàng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(20, 20, 102, 26);
		pDichVu.add(lblSubTenMH);
		
		txtTenMH = new JTextField();
		txtTenMH.setBackground(new Color(255, 255, 255));
		txtTenMH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenMH.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenMH.setBounds(120, 20, 250, 26);
		pDichVu.add(txtTenMH);
		txtTenMH.setColumns(30);
		
		//-----
		JLabel lblDonGia = new JLabel("Đơn giá: ");
		lblDonGia.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDonGia.setBounds(20, 50, 130, 26);
		pDichVu.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setBackground(new Color(255, 255, 255));
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtDonGia.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDonGia.setBounds(120, 50, 250, 26);
		pDichVu.add(txtDonGia);
		txtDonGia.setColumns(20);
//		//------
		JLabel lblSoluongMH = new JLabel("Số lượng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(20, 80, 84, 26);
		pDichVu.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoLuong.setBounds(120, 80, 250, 26);
		pDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(20);
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(20, 110, 102, 26);
		pDichVu.add(lblSubLMH);
		
		JComboBox<String> cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(120, 110, 250, 26);
		pDichVu.add(cbbLoaiMH);
		//----
		pRadio = new JPanel();
		rdQLMH = new JRadioButton("Quản lý mặt hàng");
		rdQLP = new JRadioButton("Quản lý phòng");
		rdQLMH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		rdQLP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		rdQLMH.setBackground(new Color(220,210,239));
		rdQLP.setBackground(new Color(220,210,239));
		pRadio.setBackground(new Color(220,210,239));
		pRadio.setBounds(550, 110, 150, 55);
//		rdQLP.setBounds(550, 130, 150, 20);
		pRadio.add(rdQLMH);
		pRadio.add(rdQLP);
		pMain.add(pRadio);
//		pRadio.setBackground(new Color(0,0,0,0));
		//---------------------------------
		//QLP
		//----
		JPanel pPhong = new JPanel();
		pPhong.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Quản lý phòng ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pPhong.setBackground(new Color(238,239,243,90));
		pPhong.setBounds(730, 60, 500, 170);
		pMain.add(pPhong);
		pPhong.setLayout(null);
		
		//---
		
		JLabel lblSubMaPhong = new JLabel("Mã phòng: ");
		lblSubMaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubMaPhong.setBounds(20, 20, 102, 26);
		pPhong.add(lblSubMaPhong);
		
		txtTenMH = new JTextField();
		txtTenMH.setBackground(new Color(255, 255, 255));
		txtTenMH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenMH.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenMH.setBounds(145, 20, 250, 26);
		pPhong.add(txtTenMH);
		txtTenMH.setColumns(30);
		///-----
		//Button
		///----
		JLabel lblLoaiP = new JLabel("Loại phòng: ");
		lblLoaiP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblLoaiP.setBounds(20, 50, 130, 26);
		pPhong.add(lblLoaiP);
		
		JComboBox<String> cbbLoaiP = new JComboBox<String>();
		cbbLoaiP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiP.setBackground(Color.WHITE);
		cbbLoaiP.setBounds(145, 50, 250, 26);
		String cbbLoaiKH [] = {"Thường", "Thành viên", "VIP"};
		for(int i = 0;i < cbbLoaiKH.length; i++) {
			cbbLoaiP.addItem(cbbLoaiKH[i]);
		}
		pPhong.add(cbbLoaiP);
		
		JLabel lblTTP = new JLabel("Tình trạng phòng:");
		lblTTP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTTP.setBounds(20, 80, 130, 26);
		pPhong.add(lblTTP);
		
		JComboBox<String> cbbTTP = new JComboBox<String>();
		cbbTTP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTTP.setBackground(Color.WHITE);
		cbbTTP.setBounds(145, 80, 250, 26);
		pPhong.add(cbbTTP);
		
		JLabel lblGiaP = new JLabel("Giá phòng: ");
		lblGiaP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGiaP.setBounds(20, 110, 102, 26);
		pPhong.add(lblGiaP);

		txtGiaP = new JTextField();
		txtGiaP.setBackground(new Color(255, 255, 255));
		txtGiaP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtGiaP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtGiaP.setBounds(145, 110, 250, 26);
		pPhong.add(txtGiaP);
		txtGiaP.setColumns(30);
		/////
		////////////////
		//Buttons//////
		////////////////
		/////
		FixButton btnThemKH = new FixButton("Thêm");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnThemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemKH.setBackground(new Color(114, 43, 153));
		btnThemKH.setBounds(387, 245, 118, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		FixButton btnSuaKH = new FixButton("Sửa");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 43, 153));
		btnSuaKH.setBounds(550, 245, 118, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaKH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaKH);
		
		FixButton btnXoaKH = new FixButton("Xóa");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 43, 153));
		btnXoaKH.setBounds(715, 245, 125, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaKH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaKH);
		
		FixButton btnReset = new FixButton("Làm mới");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnReset.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(881, 245, 144, 35);
		Image imgLamMoiKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiKH = imgLamMoiKH.getScaledInstance(25, 25, 0);
		btnReset.setIcon(new ImageIcon(resizeImgLamMoiKH));
		pMain.add(btnReset);
		//////////////////////////////////////////////
		////////////////
		//Sap Xep//////
		////////////////
		pSX = new JPanel();
		pSX.setBackground(new Color(220,210,239));
		JLabel lblSort = new JLabel("Sắp xếp:");
		lblSort.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSort.setBounds(20, 10, 65, 19);
		pSX.add(lblSort);
		
		JComboBox<String> cbbSort = new JComboBox<String>();
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(new Color(220,210,239));
		String cbSort [] = {"Tăng dần", "Giảm dần"};
		for(int i = 0; i< cbSort.length; i++) {
			cbbSort.addItem(cbSort[i]);
		}
		cbbSort.setBounds(50, 10, 65, 19);
		pSX.add(cbbSort);
		
		JRadioButton rdbtnTheoMaKH = new JRadioButton("Theo mã");
		rdbtnTheoMaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoMaKH.setBackground(new Color(220,210,239));
		rdbtnTheoMaKH.setBounds(100, 100, 179, 23);
		pSX.add(rdbtnTheoMaKH);
		
		JRadioButton rdbtnTheoTenKH = new JRadioButton("Theo tên");
		rdbtnTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoTenKH.setBackground(new Color(220,210,239));
		rdbtnTheoTenKH.setBounds(10, 0, 189, 23);
		pSX.add(rdbtnTheoTenKH);
		
		JRadioButton rdbtnTheoLoaiKH = new JRadioButton("Theo loại");
		rdbtnTheoLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoLoaiKH.setBackground(new Color(220,210,239));
		rdbtnTheoLoaiKH.setBounds(10, 0, 179, 23);
		pSX.add(rdbtnTheoLoaiKH);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTheoMaKH);
		bg.add(rdbtnTheoTenKH);
		bg.add(rdbtnTheoLoaiKH);
		rdbtnTheoMaKH.setSelected(true);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdQLMH);
		bg1.add(rdQLP);
		rdQLMH.setSelected(true);
		
		pSX.setBounds(295, 285, 750, 33);
		//pSX.setBackground(new Color(0,0,0,0));
		pMain.add(pSX);
		/////
		// Table down
		String mh [] = {"Mã MH","Tên mặt hàng", "Loại MH", "Số lượng", "Giá gốc","Giá bán"};
		modelMatHang = new DefaultTableModel(mh,0);
		
		tableMH = new JTable(modelMatHang);
		tableMH.setShowHorizontalLines(false);
		tableMH.setShowGrid(false);
		tableMH.setBackground(Color.WHITE);
		tableMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JTableHeader tbHeader = tableMH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableMH.setSelectionBackground(new Color(164, 44, 167,30));
		tableMH.setRowHeight(30);
		
		tableMH.setOpaque(false);
		
//		demo dữ liệu:
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		modelMatHang.addRow(new Object[] {"123","123"});
		

		
		JScrollPane spMatHang = new JScrollPane(tableMH);
		spMatHang.setBounds(37, 329, 618, 266);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		pMain.add(spMatHang);
		//
		//////////////////////////////////////
		String p [] = {"Mã phòng", "Loại phòng",  "Giá phòng","Tình trạng"};
		modelPhong = new DefaultTableModel(p,0);
		
		tbPhong = new JTable(modelPhong);
		tbPhong.setShowHorizontalLines(false);
		tbPhong.setShowGrid(false);
		tbPhong.setBackground(Color.WHITE);
		tbPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JTableHeader tbHeaderPhong = tbPhong.getTableHeader();
		tbHeaderPhong.setBackground(new Color(164, 44, 167));
		tbHeaderPhong.setForeground(Color.white);
		tbHeaderPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tbPhong.setSelectionBackground(new Color(164, 44, 167,30));
		tbPhong.setRowHeight(30);
		
		tbPhong.setOpaque(false);
		
//		demo dữ liệu:
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		modelPhong.addRow(new Object[] {"123","123"});
		

		
		JScrollPane spPhong = new JScrollPane(tbPhong);
		spPhong.setBounds(715, 329, 518, 266);
		spPhong.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spPhong.setBackground(new Color(164, 44, 167));
		pMain.add(spPhong);
		////
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
	}
}
