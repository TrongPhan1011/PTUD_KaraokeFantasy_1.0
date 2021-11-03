package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mindfusion.common.HorizontalAlignment;
import com.mindfusion.drawing.Colors;
import com.toedter.calendar.JDateChooser;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import connection.ConnectDB;
import dao.*;
import entity.*;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThemNV, btnSuaNV, btnHuy, btnLamMoiNV;
	private Panel pMain;
	private String sHeaderTenNV, sHeaderMaNV;
	private Date dNgayHienTai;
	private LocalDate now;
	private Date dNow;
	private JLabel lblNVDaNghiViec, lblSubGioTheoCa;
	private JTextField txtTim, txtHoTen, txtSDT, txtCccd;
	private JTextArea txtDiaChi;
	private JComboBox<Object> cbbChucVu, cbbGioiTinh, cbbCaLamViec, cbbSapXep;
	private JRadioButton radTatCa, radTheoMaNV, radTheoTenNV, radTheoChucVuNV;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	private SimpleDateFormat dfNgaySinh=new SimpleDateFormat("dd/MM/yyyy"), dfSQLNgaySinh=new SimpleDateFormat("yyyy/MM/dd");
	private DecimalFormat dfLuong=new DecimalFormat("###,###"), dfPV=new DecimalFormat("PV"), dfTN=new DecimalFormat("TN"), dfQL=new DecimalFormat("QL");
	private JDateChooser dateChooserNgaySinh;
	
	private DAONhanVien daoNhanVien; 
	private DAOPhatSinhMa daoPhatSinhMa;
	private DAOTaiKhoan daoTaiKhoan;
	private Regex regex;
	
	private NhanVien nv;
	
	public Panel getPanel() {
		return pMain;
	}
	
	public  FrmNhanVien(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
	//connect db
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	//DAO
		daoNhanVien=new DAONhanVien();
		daoPhatSinhMa=new DAOPhatSinhMa();
		daoTaiKhoan=new DAOTaiKhoan();
		regex=new Regex();
		
	//Entity
		NhanVien nv=new NhanVien();
		
	//frameNV
		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
	//lblQLNV
		JLabel lblQLNV = new JLabel("Quản lý nhân viên");
		lblQLNV.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLNV.setBounds(37, 10, 255, 33);
		pMain.add(lblQLNV);
		
	//lblTim
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(310, 13, 90, 35);
		pMain.add(lblTim);
		
	//txtTim
		txtTim = new JTextField();
		txtTim.setText("Tìm nhân viên theo mã nhân viên, tên nhân viên, sđt, chức vụ, ca làm việc.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(400, 12, 526, 33);
		txtTim.addFocusListener(new FocusAdapter() {	//place holder
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTim.getText().equals("Tìm nhân viên theo mã nhân viên, tên nhân viên, sđt, chức vụ, ca làm việc.")) {
					txtTim.setText("");
					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTim.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTim.getText().equals("")) {
					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTim.setText("Tìm nhân viên theo mã nhân viên, tên nhân viên, sđt, chức vụ, ca làm việc.");
					txtTim.setForeground(Colors.LightGray);
				}
			}
		});
		pMain.add(txtTim);
		
	//btnTim
		btnTim = new FixButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(942, 11, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
	//imgNhac1
		JLabel lblNhac1=new JLabel("");
		lblNhac1.setBounds(25, 120, 120, 135);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
	//imgNhac2
		JLabel lblNhac2=new JLabel("");
		lblNhac2.setBounds(1100, 120, 105, 159);
		Image imgNhac2 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac2.png");
		Image resizeNhac2 = imgNhac2.getScaledInstance(lblNhac2.getWidth(), lblNhac2.getHeight(), 0);
		lblNhac2.setIcon(new ImageIcon(resizeNhac2));
		pMain.add(lblNhac2);
		
	//thongtinNV
		//hoten
		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(165, 65, 90, 19);
		pMain.add(lblHoTen);
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtHoTen.setBounds(265, 60, 177, 28);
		pMain.add(txtHoTen);
		
		//sdt
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(165, 103, 46, 19);
		pMain.add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSDT.setBounds(265, 98, 177, 28);
		pMain.add(txtSDT);
		
		//diachi
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiaChi.setBounds(165, 140, 72, 20);
		pMain.add(lblDiaChi);
		txtDiaChi = new JTextArea();
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDiaChi.setBounds(265, 137, 177, 37);
		pMain.add(txtDiaChi);
		
		//chucvu
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChucVu.setBounds(500, 65, 98, 19);
		pMain.add(lblChucVu);
		cbbChucVu = new JComboBox<Object>(new Object[] {"Quản lý", "Phục vụ", "Thu ngân"});
		cbbChucVu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbChucVu.setBackground(Color.WHITE);
		cbbChucVu.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbChucVu.setBounds(600, 60, 124, 25);
		pMain.add(cbbChucVu);
		
		//cccc
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCccd.setBounds(500, 103, 72, 19);
		pMain.add(lblCccd);
		txtCccd = new JTextField();
		txtCccd.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCccd.setColumns(10);
		txtCccd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtCccd.setBounds(600, 98, 124, 28);
		pMain.add(txtCccd);
		
		//gioitinh
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioiTinh.setBounds(500, 140, 88, 14);
		pMain.add(lblGioiTinh);
		cbbGioiTinh = new JComboBox<Object>(new Object[] {"Nam", "Nữ"});
		cbbGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbGioiTinh.setBackground(Color.WHITE);
		cbbGioiTinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbGioiTinh.setBounds(600, 137, 124, 25);
		pMain.add(cbbGioiTinh);
		
		//ngaysinh
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgaySinh.setBounds(790, 63, 90, 18);
		pMain.add(lblNgaySinh);
		
		//JDateChooser
		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		dateChooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dateChooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		dateChooserNgaySinh.setBounds(900, 58, 200, 25);
		pMain.add(dateChooserNgaySinh);
		
		//calamviec
		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCaLamViec.setBounds(790, 101, 90, 20);
		pMain.add(lblCaLamViec);
		cbbCaLamViec = new JComboBox<Object>(new Object[] {"1", "2", "3"});
		cbbCaLamViec.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbCaLamViec.setBackground(Color.WHITE);
		cbbCaLamViec.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbCaLamViec.setBounds(900, 98, 56, 25);
		pMain.add(cbbCaLamViec);
		
		//sub gio lam viec theo ca
		lblSubGioTheoCa = new JLabel("08:00 AM - 13:00 PM");
		lblSubGioTheoCa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioTheoCa.setBounds(965, 101, 156, 20);
		pMain.add(lblSubGioTheoCa);
		
		//lbl NV da nghi viec
		lblNVDaNghiViec = new JLabel();
		lblNVDaNghiViec.setForeground(Color.RED);
		lblNVDaNghiViec.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblNVDaNghiViec.setBounds(825, 140, 227, 20);
		pMain.add(lblNVDaNghiViec);
		
		//btnthem,sua,xoa,lammoiNV
		btnThemNV = new FixButton("Thêm");
		btnThemNV.setForeground(Color.WHITE);
		btnThemNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemNV.setBackground(new Color(114, 23, 153));
		btnThemNV.setBounds(372, 190, 110, 35);
		Image imgThemNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemNV = imgThemNV.getScaledInstance(25, 25, 0);
		btnThemNV.setIcon(new ImageIcon(resizeImgThemNV));
		pMain.add(btnThemNV);
		
		btnSuaNV = new FixButton("Sửa");
		btnSuaNV.setForeground(Color.WHITE);
		btnSuaNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaNV.setBackground(new Color(114, 23, 153));
		btnSuaNV.setBounds(513, 190, 110, 35);
		Image imgSuaNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaNV = imgSuaNV.getScaledInstance(25, 25, 0);
		btnSuaNV.setIcon(new ImageIcon(resizeImgSuaNV));
		pMain.add(btnSuaNV);
		
		btnHuy = new FixButton("Hủy");
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnHuy.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnHuy.setBackground(new Color(114, 23, 153));
		btnHuy.setBounds(653, 190, 110, 35);
		Image imgXoaNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaNV = imgXoaNV.getScaledInstance(25, 25, 0);
		btnHuy.setIcon(new ImageIcon(resizeImgXoaNV));
		pMain.add(btnHuy);
		
		btnLamMoiNV = new FixButton("Làm mới");
		btnLamMoiNV.setForeground(Color.WHITE);
		btnLamMoiNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiNV.setBackground(new Color(114, 23, 153));
		btnLamMoiNV.setBounds(793, 190, 110, 35);
		Image imgLamMoiNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiNV = imgLamMoiNV.getScaledInstance(25, 25, 0);
		btnLamMoiNV.setIcon(new ImageIcon(resizeImgLamMoiNV));
		pMain.add(btnLamMoiNV);
		
		//sapxep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(201, 194, 237));
		pSapXep.setBounds(165, 229, 936, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		cbbSapXep = new JComboBox<Object>(new Object[] {"Tăng dần", "Giảm dần"});
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSapXep.setBackground(Color.WHITE);
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbSapXep.setBounds(45, 14, 102, 28);
		pSapXep.add(cbbSapXep);
		
		radTatCa = new JRadioButton("Tất cả");
		radTatCa.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTatCa.setBackground(new Color(201, 194, 237));
		radTatCa.setBounds(195, 16, 113, 25);
		pSapXep.add(radTatCa);
		
		radTheoMaNV = new JRadioButton("Theo mã nhân viên");
		radTheoMaNV.setBounds(305, 16, 159, 25);
		radTheoMaNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoMaNV.setBackground(new Color(201, 194, 237));
		pSapXep.add(radTheoMaNV);
		
		radTheoTenNV = new JRadioButton("Theo tên nhân viên");
		radTheoTenNV.setBounds(508, 16, 161, 25);
		radTheoTenNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoTenNV.setBackground(new Color(201, 194, 237));
		pSapXep.add(radTheoTenNV);
		
		radTheoChucVuNV = new JRadioButton("Theo chức vụ nhân viên");
		radTheoChucVuNV.setBounds(705, 16, 195, 25);
		radTheoChucVuNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoChucVuNV.setBackground(new Color(201, 194, 237));
		pSapXep.add(radTheoChucVuNV);
		
		ButtonGroup bgRad=new ButtonGroup();
		bgRad.add(radTatCa);
		bgRad.add(radTheoMaNV);
		bgRad.add(radTheoTenNV);
		bgRad.add(radTheoChucVuNV);
		
		//bangthongtinNV
		JScrollPane scrollPaneNV = new JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneNV.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneNV.setBackground(new Color(164, 44, 167));
		scrollPaneNV.setBounds(21, 290, 1223, 305);
		scrollPaneNV.getHorizontalScrollBar();
		pMain.add(scrollPaneNV);
		
		String col[] = {"Mã NV", "Họ và tên nhân viên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "CCCD", "Lương", "Ca làm việc", "Trạng thái làm việc", "Mật khẩu"};
		modelNV = new DefaultTableModel(col, 0);
		
		tableNV = new JTable(modelNV);
		tableNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableNV.setShowHorizontalLines(true); 
		tableNV.setShowGrid(true);
		tableNV.setBackground(Color.white);
		tableNV.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableNV.setSelectionBackground(new Color(164, 44, 167, 30));
		tableNV.setSelectionForeground(new Color(114, 23, 153));
		tableNV.setRowHeight(30);
		tableNV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JTableHeader tbHeader = tableNV.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableNV.getColumnModel().getColumn(0).setPreferredWidth(60); //maNV
		tableNV.getColumnModel().getColumn(1).setPreferredWidth(155);//tenNV
		tableNV.getColumnModel().getColumn(2).setPreferredWidth(80); //chucvu
		tableNV.getColumnModel().getColumn(3).setPreferredWidth(75); //gioitinh
		tableNV.getColumnModel().getColumn(4).setPreferredWidth(80); //ngaysinh
		tableNV.getColumnModel().getColumn(5).setPreferredWidth(270); //diachi
		tableNV.getColumnModel().getColumn(6).setPreferredWidth(90); //sdt
		tableNV.getColumnModel().getColumn(7).setPreferredWidth(100); //cccd
		tableNV.getColumnModel().getColumn(8).setPreferredWidth(70); //luong
		tableNV.getColumnModel().getColumn(9).setPreferredWidth(90); //calamviec
		tableNV.getColumnModel().getColumn(10).setPreferredWidth(145);//trangthai
		tableNV.getColumnModel().getColumn(11).setPreferredWidth(120);//matkhau
		
		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableNV.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tableNV.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		tableNV.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tableNV.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		tableNV.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		
		//tableNV.setOpaque(false);
		scrollPaneNV.setViewportView(tableNV);
		
		//background 
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		
		
		//test data nhanh
		txtHoTen.setText("Đinh Quang Tuấn");
		txtSDT.setText("0944302210");
		txtCccd.setText("123456789012");
		txtDiaChi.setText("118 Hoàng Văn Thụ, Q.Phú Nhuận, Tp.HCM");
		
		//su kien
		cbbCaLamViec.addActionListener(this);
		
		radTatCa.addActionListener(this);
		
		btnTim.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnSuaNV.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoiNV.addActionListener(this);
		
		tableNV.addMouseListener(this);
	}
	
	//xoa het data trong table
	private void removeDanhSachNV(DefaultTableModel defaultTableModel) {
//		DefaultTableModel dtm = (DefaultTableModel) tableNV.getModel();
//		dtm.getDataVector().removeAllElements();
		
		while(tableNV.getRowCount() > 0){
			modelNV.removeRow(0);
		}
	}
	
	//xoa trang textfield va textarea
		private void xoaTrang() {
			txtTim.setText("Tìm nhân viên theo mã nhân viên, tên nhân viên, sđt, chức vụ, ca làm việc.");
			txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
			txtTim.setForeground(Colors.LightGray);
			
			txtHoTen.setText("");
			txtSDT.setText("");
			txtDiaChi.setText("");
			txtCccd.setText("");
//			dateChooserNgaySinh.setDate(new Date(0));
			lblNVDaNghiViec.setText("");
			
			radTatCa.setSelected(false);
			radTheoMaNV.setSelected(false);
			radTheoTenNV.setSelected(false);
			radTheoChucVuNV.setSelected(false);
		}
	
	//chon va sub gio theo ca
	private void subGioTheoCa() {
		if(cbbCaLamViec.getSelectedItem() == "1") {
			lblSubGioTheoCa.setText("08:00 AM - 13:00 PM");
		}
		if(cbbCaLamViec.getSelectedItem() == "2") {
			lblSubGioTheoCa.setText("13:00 PM - 18:00 PM");
		}
		if(cbbCaLamViec.getSelectedItem() == "3") {
			lblSubGioTheoCa.setText("18:00 PM - 24:00 PM");
		}
	}	
		
	//load dsNV
	private void loadDanhSachNV(NhanVien nv)  {
		//clearTable();
		removeDanhSachNV(modelNV);
		ArrayList<NhanVien> lstNV = daoNhanVien.getDanhSachNV();
		for(NhanVien infoNV : lstNV) {
			TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(infoNV.getMaNhanVien());
			modelNV.addRow(new Object[] {
					infoNV.getMaNhanVien(), infoNV.getTenNhanVien(), infoNV.getChucVu(), infoNV.getGioiTinh(), 
					dfNgaySinh.format(infoNV.getNgaySinh()), infoNV.getDiaChi(), infoNV.getSdt(), infoNV.getCccd(), 
					dfLuong.format(Math.round(infoNV.getLuong())), infoNV.getCaLamViec(), infoNV.getTrangThaiLamViec(), tk.getMatKhau()
			});
		}
	}
	
	//load 1 NV
	private void loadNV(NhanVien nv) {
			TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(nv.getMaNhanVien());
			modelNV.setRowCount(0);
			modelNV.addRow(new Object[] {
					nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getChucVu(), nv.getGioiTinh(), 
					dfNgaySinh.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getSdt(), nv.getCccd(), 
					dfLuong.format(Math.round(nv.getLuong())), nv.getCaLamViec(), nv.getTrangThaiLamViec(), tk.getMatKhau()	
			});
	}
	
	//load 1 NV da nghi viec
	private void loadNVDaNghiViec(NhanVien nvNghi) {
		TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(nvNghi.getMaNhanVien());
		modelNV.setRowCount(0);
		modelNV.addRow(new Object[] {
				nvNghi.getMaNhanVien(), nvNghi.getTenNhanVien(), nvNghi.getChucVu(), nvNghi.getGioiTinh(), 
				dfNgaySinh.format(nvNghi.getNgaySinh()), nvNghi.getDiaChi(), nvNghi.getSdt(), nvNghi.getCccd(), 
				dfLuong.format(Math.round(nvNghi.getLuong())), nvNghi.getCaLamViec(), nvNghi.getTrangThaiLamViec(), tk.getMatKhau()	
		});
	}
	
//////////
	private void loadDanhSachTenNV(NhanVien nv)  {
		removeDanhSachNV(modelNV);
		ArrayList<NhanVien> lstName = daoNhanVien.getTenNV(txtTim.getText());
		for(NhanVien infoNV : lstName) {
			TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(infoNV.getMaNhanVien());
			modelNV.addRow(new Object[] {
					infoNV.getMaNhanVien(), infoNV.getTenNhanVien(), infoNV.getChucVu(), infoNV.getGioiTinh(), 
					dfNgaySinh.format(infoNV.getNgaySinh()), infoNV.getDiaChi(), infoNV.getSdt(), infoNV.getCccd(), 
					dfLuong.format(Math.round(infoNV.getLuong())), infoNV.getCaLamViec(), infoNV.getTrangThaiLamViec(), tk.getMatKhau()
			});
		}
	}
	private void loadDanhSachChucVuNV(NhanVien nv)  {
		removeDanhSachNV(modelNV);
		ArrayList<NhanVien> lstName = daoNhanVien.getChucVuNV(txtTim.getText());
		for(NhanVien infoNV : lstName) {
			TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(infoNV.getMaNhanVien());
			modelNV.addRow(new Object[] {
					infoNV.getMaNhanVien(), infoNV.getTenNhanVien(), infoNV.getChucVu(), infoNV.getGioiTinh(), 
					dfNgaySinh.format(infoNV.getNgaySinh()), infoNV.getDiaChi(), infoNV.getSdt(), infoNV.getCccd(), 
					dfLuong.format(Math.round(infoNV.getLuong())), infoNV.getCaLamViec(), infoNV.getTrangThaiLamViec(), tk.getMatKhau()
			});
		}
	}
	
	private void loadDanhSachCaNV(NhanVien nv)  {
		removeDanhSachNV(modelNV);
		ArrayList<NhanVien> lstName = daoNhanVien.getCaNV(txtTim.getText());
		for(NhanVien infoNV : lstName) {
			TaiKhoan tk = daoTaiKhoan.getMatKhauTheoMaNV(infoNV.getMaNhanVien());
			modelNV.addRow(new Object[] {
					infoNV.getMaNhanVien(), infoNV.getTenNhanVien(), infoNV.getChucVu(), infoNV.getGioiTinh(), 
					dfNgaySinh.format(infoNV.getNgaySinh()), infoNV.getDiaChi(), infoNV.getSdt(), infoNV.getCccd(), 
					dfLuong.format(Math.round(infoNV.getLuong())), infoNV.getCaLamViec(), infoNV.getTrangThaiLamViec(), tk.getMatKhau()
			});
		}
	}
	
	//timNV
	private void findNV() {
		NhanVien nv1 = daoNhanVien.getNV(txtTim.getText());
		if(!txtTim.getText().equals("") && !txtTim.getText().equals("Tìm nhân viên theo mã nhân viên, tên nhân viên, sđt, chức vụ, ca làm việc.")) {
			String messTenNV = "\n - Họ tên. Ví dụ: Nguyễn Văn A";
			String messCV =    "\n - Tìm theo chức vụ: phục vụ, thu ngân, quản lý";
			String messSDT =   "\n - SĐT gồm 10 chữ số và bắt đầu bằng số 0";
			String messCa =    "\n - Tìm theo ca: 1, 2, 3";
				
			if(regex.regexTimKiemMaNV(txtTim)) { 
				if(nv1 != null) 
					loadNV(nv1);
			}
			else if(regex.regexTenNV(txtTim)) { 
				if(nv1 != null) 
					loadDanhSachTenNV(nv1);
			}
			else if(regex.regexTimKiemChucVu(txtTim)) { 
				if(nv1 != null) 
					loadDanhSachChucVuNV(nv1);
			}
			else if(regex.regexSDT(txtTim)) { 
				if(nv1 != null) 
					loadNV(nv1);
			}
			else if(regex.regexTimKiemCa(txtTim)) { 
				if(nv1 != null) 
					loadDanhSachCaNV(nv1);
			}
			else
				JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ!\nThông tin có thể tìm kiếm:\n - Mã nhân viên. Ví dụ: NV001" +messTenNV +messSDT +messCV +messCa, "Thông báo", JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
//////////	
	
	//themNV vao sql
	private void addNV() {
		try {
			//năm hien tai
			now = LocalDate.now();
			int nam = now.getYear();
			int thang = now.getMonthValue();
			int ngay = now.getDayOfMonth();
			dNow = new Date(nam, thang, ngay);
			
			String phatSinhMaNV = daoPhatSinhMa.getMaNV();
			String hoTen = txtHoTen.getText();
			String sdt = txtSDT.getText();
			String diaChi = txtDiaChi.getText();
			String chucVu = cbbChucVu.getSelectedItem().toString();
			String cccd = txtCccd.getText();
			String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
			//
//			int day = dateChooserNgaySinh.getDate().getDay()+24;  //vì ko +24 bên sql sẽ tự -24 @.@
//			int month = dateChooserNgaySinh.getDate().getMonth();
//			int year = dateChooserNgaySinh.getDate().getYear();
//			int age = nam - year;
//			java.util.Date ngaySinh = new Date(year, month, day);
			
			java.util.Date date = dateChooserNgaySinh.getDate();
//			System.out.println(date.getDate());
//			System.out.println(date.getMonth());
//			System.out.println(date.getYear());
			
			Date date1=new Date(date.getYear(), date.getMonth(), date.getDate());
//			System.out.println("======");
//			System.out.println(date1.getDate());
//			System.out.println(date1.getMonth());
//			System.out.println(date1.getYear());
			
			int age = nam - date1.getYear();
//			int age = now.getYear() - date1.getYear();
//			int age = dNow.getYear() - date1.getYear();
			//
			int caLamViec = Integer.parseInt((String) cbbCaLamViec.getSelectedItem());
			
			TaiKhoan tk=new TaiKhoan(phatSinhMaNV);
			String matKhau = phatSinhMaNV.concat(sdt); //String matKhau = ""+phatSinhMaNV +sdt;
			
			if(age>=18) {
			if(regex.regexTen(txtHoTen) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCccd)) {
				
//					if(day>0 && day<=31 && month>0 && month<=12 && year>0 && year<nam) { 
					if(date1.getDate()>0 && date1.getDate()<=31 && date1.getMonth()>0 && date1.getMonth()<=12 && date1.getYear()>0 && date1.getYear()<nam) { 
						Connection con1 = new ConnectDB().getConnection();
						TaiKhoan tk1=new TaiKhoan();
						tk1.setMaTK(phatSinhMaNV);
						tk1.setMatKhau(matKhau);
						try {
							new DAOTaiKhoan().createTK(tk1);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					
						//them vao data
						Connection con2 = new ConnectDB().getConnection();
						NhanVien nv=new NhanVien();
						nv.setMaNhanVien(phatSinhMaNV);
						nv.setTaiKhoan(tk);
						nv.setTenNhanVien(hoTen);
						nv.setChucVu(chucVu);
						nv.setGioiTinh(gioiTinh);
//						nv.setNgaySinh((Date) ngaySinh);
						nv.setNgaySinh(date1);
						nv.setDiaChi(diaChi);
						nv.setSdt(sdt);
						nv.setCccd(cccd);
						nv.setLuong(200000);
						nv.setCaLamViec(caLamViec);
						nv.setTrangThaiLamViec("Đang làm việc");
						try {
							new DAONhanVien().themNV(nv);
						}catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
					
						//them vao table
						xoaTrang();
						modelNV.addRow(new Object[] {
								phatSinhMaNV, hoTen, chucVu, gioiTinh, 
								dfNgaySinh.format(dateChooserNgaySinh.getDate()), diaChi, sdt, cccd,
								dfLuong.format(Math.round(200000)), caLamViec, "Đang làm việc", matKhau
							});
						String mkTK = "\nMật khẩu: "+matKhau;
						JOptionPane.showMessageDialog(this, "Thêm thành công!\nMã tài khoản: "+phatSinhMaNV +mkTK, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
//				}catch(Exception e1) {
//					e1.printStackTrace();
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Nhân viên làm việc phải trên 18 tuổi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
		
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//huyTaiKhoanNV_chuyen trangThaiLamViec dang lam viec thanh da nghi viec
	private boolean cancelNV() {
		int row = tableNV.getSelectedRow();
		if(row>=0) {
			int cancel = JOptionPane.showConfirmDialog(null, "Bạn muốn hủy tài khoản nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(cancel == JOptionPane.YES_OPTION) {
				Connection con = new ConnectDB().getConnection();
				NhanVien nv=new NhanVien();
				String maNV = (String) tableNV.getValueAt(row, 0);
				try {
					modelNV.removeRow(row);
					removeDanhSachNV(modelNV);
					new DAONhanVien().huyNV(maNV);
					loadDanhSachNV(nv);
					JOptionPane.showMessageDialog(null, "Đã hủy tài khoản!", "Thông báo", JOptionPane.OK_OPTION);
				}catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hủy tài khoản thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin tài khoản nhân viên cần hủy!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}

	//suaNV
	private void updateNV() {
		int row = tableNV.getSelectedRow();
		if(row>=0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(update == JOptionPane.YES_OPTION) {
				NhanVien nv=new NhanVien();
				String maNV = (String) tableNV.getValueAt(row, 0);
				java.util.Date date = dateChooserNgaySinh.getDate();
				Date date1=new Date(date.getYear(), date.getMonth(), date.getDate());
				int caLamViec = Integer.parseInt((String) cbbCaLamViec.getSelectedItem());
				try {
					if(regex.regexTen(txtHoTen) && regex.regexSDT(txtSDT) && regex.regexDiaChi(txtDiaChi) && regex.regexCCCD(txtCccd)) {
						nv.setTenNhanVien(txtHoTen.getText());
						nv.setChucVu((String) cbbChucVu.getSelectedItem());
						nv.setGioiTinh((String) cbbGioiTinh.getSelectedItem());
						nv.setNgaySinh(date1);
						nv.setDiaChi(txtDiaChi.getText());
						nv.setSdt(txtSDT.getText());
						nv.setCccd(txtCccd.getText());
						nv.setCaLamViec(caLamViec);
						
						new DAONhanVien().capNhatNV(nv, maNV);
						removeDanhSachNV(modelNV);
						loadDanhSachNV(nv);
					
						JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được sửa!", "Thông báo", JOptionPane.OK_OPTION);
					}
				}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin nhân viên cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  o = e.getSource();
		
		//sub giờ làm việc theo ca
		if(o.equals(cbbCaLamViec)) {
			subGioTheoCa();
		}
		
		//checkbox tatca
		if(o.equals(radTatCa)) {
			loadDanhSachNV(nv);
		}
		
		//tìm NV
		if(o.equals(btnTim)) {
			findNV();
		}
		
		//thêm NV
		if(o.equals(btnThemNV)) {
			addNV();
		}
		
		//sửa NV
		if(o.equals(btnSuaNV)) {
			updateNV();
		}
		
		//hủy
		if(o.equals(btnHuy)) {
			cancelNV();
		}
		
		//làm mới
		if(o.equals(btnLamMoiNV)) {
			xoaTrang();
			removeDanhSachNV(modelNV);
		}
		
		
	}
	///////////
	//chon row NV
	private void choose1NV() {
		int selectedRow = tableNV.getSelectedRow();
		if(selectedRow >= 0) {
			String maNV = (String) tableNV.getValueAt(selectedRow, 0);
			String trangThai = (String) tableNV.getValueAt(selectedRow, 10);
			ArrayList<NhanVien> lstNV = daoNhanVien.getAllDanhSachNV();
			for(NhanVien nv : lstNV) {
				if(maNV.equals(nv.getMaNhanVien())) {
					txtHoTen.setText(nv.getTenNhanVien());
					txtSDT.setText(nv.getSdt());
					txtDiaChi.setText(nv.getDiaChi());
					cbbChucVu.setSelectedItem(nv.getChucVu());
					txtCccd.setText(nv.getCccd());
					cbbGioiTinh.setSelectedItem(nv.getGioiTinh());
					dateChooserNgaySinh.setDate(nv.getNgaySinh());
					cbbCaLamViec.setSelectedItem(nv.getCaLamViec()+"");
					break;
				}
				if(trangThai.equals("Đã nghỉ việc")) {
					lblNVDaNghiViec.setText("ĐÃ NGHỈ VIỆC.");
				}
				if(trangThai.equals("Đang làm việc")) {
					lblNVDaNghiViec.setText("");
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		choose1NV();
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
}
