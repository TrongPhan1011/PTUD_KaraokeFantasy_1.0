package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import com.mindfusion.drawing.Colors;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.*;
import entity.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmDonDatPhong extends JPanel implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV, sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JLabel lblQLDDP, lblTim, lblTenKH, lblLoaiKH, lblNgayDen, lblSDT, lblGioDen, lblTinhTrangDDP, lblDiaChi, lblChonPhong, lblBackGround;
	private JTextField txtTim, txtTenKH, txtSDT, txtGioDen, txtDiaChi;
	private JComboBox<Object> cboLoaiKH, cboTrangThaiDDP, cboSapXep;
	private JComboBox<String> cboGio, cboPhut;
	private JTable tblPhong, tblDDP;
	private DefaultTableModel modelPhong, modelDDP;
	private JButton btnTim, btnThemDDP, btnSuaDDP, btnHuyDDP, btnLamMoiDDP;
	private JCheckBox chkTatCa;
	private JRadioButton rdoTheoMaPhong, rdoTheoLoaiPhong, rdoTheoGiaPhong;
	private ButtonGroup bg;
	private SimpleDateFormat dfNgay=new SimpleDateFormat("dd/MM/yyyy"), dfHienGio=new SimpleDateFormat("HH:mm a");
	//	private DateTimeFormatter dftxtGioPhut= DateTimeFormatter.ofPattern("HH:mm");
	private DecimalFormat dfGiaPhong=new DecimalFormat("###,###"), dftxtGio=new DecimalFormat("HH:mm");
	private Date dNow;
	private LocalDate now;
	private int ngay, thang, nam;
	private JDateChooser chooserNgayDen;

	private DAOPhong daoPhong;
	private DAOLoaiPhong daoLoaiPhong;
	private DAODonDatPhong daoDonDatPhong;
	private DAOKhachHang daoKhachHang;
	private DAOLoaiKH daoLoaiKH;
	private DAONhanVien daoNhanVien;
	private DAOPhatSinhMa daoPhatSinhMa;
	private Regex regex;

	private DonDatPhong ddp;
	
	public static void main(String[] args) {
		String tenNVTamThoi = "Trần Thanh Thiện";
		String maNVTamThoi = "NV002";
		LocalDate now = LocalDate.now();
		int nam = now.getYear() - 1900,  
			thang = now.getMonthValue(), 
			ngay = now.getDayOfMonth();
		Date ngayLap = new Date(nam, thang, ngay);
		new FrmDonDatPhong(tenNVTamThoi, maNVTamThoi, ngayLap).setVisible(true);
		
	}

	public Panel getFrmDDP() {
		return this.pMain;
	}
	public FrmDonDatPhong(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {

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
		daoPhong=new DAOPhong();
		daoLoaiPhong=new DAOLoaiPhong();
		daoDonDatPhong=new DAODonDatPhong();
		daoKhachHang=new DAOKhachHang();
		daoLoaiKH=new DAOLoaiKH();
		daoNhanVien=new DAONhanVien();
		daoPhatSinhMa=new DAOPhatSinhMa();
		regex=new Regex();

		//Entity
		Phong p=new Phong();
		DonDatPhong ddp=new DonDatPhong();
		KhachHang kh=new KhachHang();

		//frameDDP
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);

		//lblDDP
		lblQLDDP = new JLabel("Quản lý đơn đặt phòng");
		lblQLDDP.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLDDP.setBounds(50, 10, 255, 33);
		pMain.add(lblQLDDP);

		//lblTim
		lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 13, 90, 35);
		pMain.add(lblTim);

		//txtTim
		txtTim = new JTextField();
		txtTim.setText("Tìm đơn đặt phòng theo tên khách hàng, sđt khách hàng.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(455, 12, 408, 33);
		txtTim.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTim.getText().equals("Tìm đơn đặt phòng theo tên khách hàng, sđt khách hàng.")) {
					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTim.setForeground(Color.BLACK);
					txtTim.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTim.getText().equals("")) {
					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTim.setForeground(Colors.LightGray);
					txtTim.setText("Tìm đơn đặt phòng theo tên khách hàng, sđt khách hàng.");
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
		btnTim.setBounds(878, 12, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);

		//lblTenKH
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTenKH.setBounds(35, 65, 133, 19);
		pMain.add(lblTenKH);

		//txtTenKH
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		txtTenKH.setBounds(170, 59, 175, 28);
		pMain.add(txtTenKH);

		//lblLoaiKH
		lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(35, 105, 133, 19);
		pMain.add(lblLoaiKH);

		//cbbLoaiKH
		cboLoaiKH = new JComboBox<Object>(new Object[] {"Thường", "Thành viên", "VIP"});
		cboLoaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboLoaiKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboLoaiKH.setBackground(new Color(235, 235, 235));
		cboLoaiKH.setBounds(170, 100, 175, 27);
		pMain.add(cboLoaiKH);

		//lblNgayDen
		lblNgayDen = new JLabel("Ngày đến:");
		lblNgayDen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgayDen.setBounds(374, 65, 74, 19);
		pMain.add(lblNgayDen);

		//chooserNgayDen
		chooserNgayDen = new JDateChooser();
		chooserNgayDen.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgayDen.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgayDen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgayDen.setDateFormatString("dd/MM/yyyy");
		chooserNgayDen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgayDen.setBounds(455, 60, 158, 27);
		pMain.add(chooserNgayDen);

		//lblSDT
		lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(374, 105, 46, 19);
		pMain.add(lblSDT);

		//txtSDT
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSDT.setBounds(455, 100, 158, 28);
		pMain.add(txtSDT);

		//lblGioDen
		lblGioDen = new JLabel("Giờ đến:");
		lblGioDen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioDen.setBounds(640, 65, 74, 19);
		pMain.add(lblGioDen);

		//txtGioDen
		cboGio=new JComboBox<String>();
		cboGio.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboGio.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboGio.setBackground(new Color(235, 235, 235));
		cboGio.setBounds(715, 59, 62, 27);
		for(int i=0; i<24; i++)
			cboGio.addItem(""+i);
		pMain.add(cboGio);

		JLabel lblHaiCham = new JLabel(" :");
		lblHaiCham.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblHaiCham.setBounds(778, 54, 21, 33);
		pMain.add(lblHaiCham);

		cboPhut = new JComboBox<String>();
		cboPhut.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboPhut.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboPhut.setBackground(new Color(235, 235, 235));
		cboPhut.setBounds(801, 59, 62, 27);
		for(int i=0; i<60; i++)
			cboPhut.addItem(""+i);
		pMain.add(cboPhut);

		//				txtGioDen = new JTextField("00:00");
		//				txtGioDen.setColumns(10);
		//				txtGioDen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		//				txtGioDen.setForeground(Colors.LightGray);
		//				txtGioDen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		//				txtGioDen.setBounds(762, 59, 74, 28);
		//				txtGioDen.addFocusListener(new FocusAdapter() {
		//					@Override
		//					public void focusGained(FocusEvent e) {
		//						if(txtGioDen.getText().equals("00:00")) {
		//							txtGioDen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		//							txtGioDen.setForeground(Color.BLACK);
		//							txtGioDen.setText("");
		//						}
		//					}
		//					@Override
		//					public void focusLost(FocusEvent e) {
		//						if(txtGioDen.getText().equals("")) {
		//							txtGioDen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		//							txtGioDen.setForeground(Colors.LightGray);
		//							txtGioDen.setText("00:00");
		//						}
		//					}
		//				});
		//				try {
		//					dfGio.parse(txtGioDen.getText());
		//				} catch (ParseException e1) {
		//					e1.printStackTrace();
		//				}
		//				pMain.add(txtGioDen);

		//lblDiaChi
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiaChi.setBounds(640, 105, 61, 20);
		pMain.add(lblDiaChi);

		//txtDiaChi
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDiaChi.setBounds(715, 99, 512, 28);
		pMain.add(txtDiaChi);

		//lblTinhTrangDDP
		lblTinhTrangDDP = new JLabel("Trạng thái đơn đặt phòng:");
		lblTinhTrangDDP.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTinhTrangDDP.setBounds(878, 65, 195, 19);
		pMain.add(lblTinhTrangDDP);

		//cbbTinhTrangDDP
		cboTrangThaiDDP = new JComboBox<Object>(new Object[]{"Đã xác nhận", "Chờ xác nhận", "Hủy", "Đã trả phòng"});
		cboTrangThaiDDP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboTrangThaiDDP.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cboTrangThaiDDP.setBackground(new Color(235, 235, 235));
		cboTrangThaiDDP.setBounds(1072, 59, 155, 27);
		pMain.add(cboTrangThaiDDP);

		//lblChonPhong
		lblChonPhong = new JLabel("Chọn phòng:");
		lblChonPhong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChonPhong.setBounds(35, 145, 98, 19);
		pMain.add(lblChonPhong);

		//bangthongtinPhong
		JScrollPane scrollPaneChonPhong = new JScrollPane(tblPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneChonPhong.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneChonPhong.setBackground(new Color(164, 44, 167));
		scrollPaneChonPhong.setBounds(170, 142, 693, 131);
		scrollPaneChonPhong.getHorizontalScrollBar();
		pMain.add(scrollPaneChonPhong);

		String colPhong[] = {"Mã phòng", "Mã loại phòng", "Loại phòng", "Giá phòng", "Tình trạng phòng"};
		modelPhong=new DefaultTableModel(colPhong, 0);

		tblPhong=new JTable(modelPhong);
		tblPhong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblPhong.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblPhong.setShowHorizontalLines(true);
		tblPhong.setShowGrid(true);
		tblPhong.setBackground(Color.white);
		tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblPhong.setSelectionBackground(new Color(164, 44, 167, 30));
		tblPhong.setSelectionForeground(new Color(114, 23, 153));
		tblPhong.setRowHeight(30);
		tblPhong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTableHeader tbHeaderPhong = tblPhong.getTableHeader();
		tbHeaderPhong.setBackground(new Color(164, 44, 167));
		tbHeaderPhong.setForeground(Color.white);
		tbHeaderPhong.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblPhong.getColumnModel().getColumn(0).setPreferredWidth(100);//maphong
		tblPhong.getColumnModel().getColumn(1).setPreferredWidth(180);//maloaiphong
		tblPhong.getColumnModel().getColumn(2).setPreferredWidth(130);//loaiphong
		tblPhong.getColumnModel().getColumn(3).setPreferredWidth(120);//giaphong
		tblPhong.getColumnModel().getColumn(4).setPreferredWidth(170);//tinhtrangphong

		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tblPhong.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		scrollPaneChonPhong.setViewportView(tblPhong);

		//ngay thang nam lap DDP
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		dNow = new Date(nam, thang, ngay);

		JPanel pChucNang = new JPanel();
		pChucNang.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Chức năng", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pChucNang.setBackground(new Color(197, 194, 237));
		pChucNang.setBounds(881, 134, 346, 140);
		pMain.add(pChucNang);
		pChucNang.setLayout(null);
		
		//btnthem,sua,xoa,lammoiDDP
		btnThemDDP = new FixButton("Thêm");
		btnThemDDP.setForeground(Color.white);
		btnThemDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemDDP.setBackground(new Color(114, 23, 153));
		btnThemDDP.setBounds(118, 30, 110, 35);
		Image imgThemDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemDDP = imgThemDDP.getScaledInstance(25, 25, 0);
		btnThemDDP.setIcon(new ImageIcon(resizeImgThemDDP));
		pChucNang.add(btnThemDDP);

		btnSuaDDP = new FixButton("Sửa");
		btnSuaDDP.setForeground(Color.white);
		btnSuaDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaDDP.setBackground(new Color(114, 23, 153));
		btnSuaDDP.setBounds(37, 84, 110, 35);
		Image imgSuaDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaDDP = imgSuaDDP.getScaledInstance(25, 25, 0);
		btnSuaDDP.setIcon(new ImageIcon(resizeImgSuaDDP));
		pChucNang.add(btnSuaDDP);

//		btnHuyDDP = new FixButton("Hủy");
//		btnHuyDDP.setForeground(Color.white);
//		btnHuyDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnHuyDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
//		btnHuyDDP.setBackground(new Color(114, 23, 153));
//		btnHuyDDP.setBounds(900, 218, 110, 35);
//		Image imgXoaDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
//		Image resizeImgXoaDDP = imgXoaDDP.getScaledInstance(25, 25, 0);
//		btnHuyDDP.setIcon(new ImageIcon(resizeImgXoaDDP));
//		pMain.add(btnHuyDDP);

		btnLamMoiDDP = new FixButton("Làm mới");
		btnLamMoiDDP.setForeground(Color.white);
		btnLamMoiDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiDDP.setBackground(new Color(114, 23, 153));
		btnLamMoiDDP.setBounds(200, 84, 110, 35);
		Image imgLamMoiDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiDDP = imgLamMoiDDP.getScaledInstance(25, 25, 0);
		btnLamMoiDDP.setIcon(new ImageIcon(resizeImgLamMoiDDP));
		pChucNang.add(btnLamMoiDDP);

		//sapxep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(207, 195, 237));
		pSapXep.setBounds(168, 280, 890, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);

		cboSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cboSapXep.setBounds(60, 14, 102, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		pSapXep.add(cboSapXep);

		chkTatCa = new JCheckBox("Tất cả");
		chkTatCa.setFont(new Font("SansSerif", Font.BOLD, 14));
		chkTatCa.setBackground(new Color(207, 195, 237));
		chkTatCa.setBounds(215, 15, 95, 27);
		chkTatCa.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1)
					loadDanhSachDDP(ddp);
				else
					removeDanhSachDDP(modelDDP);
			}
		});
		pSapXep.add(chkTatCa);

		rdoTheoMaPhong = new JRadioButton("Theo mã phòng");
		rdoTheoMaPhong.setBounds(330, 15, 133, 27);
		rdoTheoMaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoMaPhong);

		rdoTheoLoaiPhong = new JRadioButton("Theo loại phòng");
		rdoTheoLoaiPhong.setBounds(515, 15, 139, 27);
		rdoTheoLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLoaiPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoLoaiPhong);

		rdoTheoGiaPhong = new JRadioButton("Theo giá phòng");
		rdoTheoGiaPhong.setBounds(700, 15, 135, 27);
		rdoTheoGiaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoGiaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoGiaPhong);

		bg=new ButtonGroup();
		bg.add(rdoTheoMaPhong); bg.add(rdoTheoLoaiPhong); bg.add(rdoTheoGiaPhong);

		//bangthongtinDDP
		JScrollPane scrollPaneDDP = new JScrollPane();
		scrollPaneDDP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneDDP.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneDDP.setBackground(new Color(164, 44, 167));
		scrollPaneDDP.setBounds(26, 336, 1212, 259);
		scrollPaneDDP.getHorizontalScrollBar();
		pMain.add(scrollPaneDDP);

		String colDDP[] = {"Mã DDP", "Mã phòng", "Tên KH", "SĐT", "Ngày đến", "Giờ đến" , "Tên NV lập", "Ngày lập", "Trạng thái DDP"};
		modelDDP=new DefaultTableModel(colDDP, 0);

		tblDDP=new JTable(modelDDP);
		tblDDP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblDDP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblDDP.setShowHorizontalLines(true);
		tblDDP.setShowGrid(true);
		tblDDP.setBackground(Color.white);
		tblDDP.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblDDP.setSelectionBackground(new Color(164, 44, 167, 30));
		tblDDP.setSelectionForeground(new Color(114, 23, 153));
		tblDDP.setRowHeight(30);
		//		tblDDP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTableHeader tbHeaderDDP = tblDDP.getTableHeader();
		tbHeaderDDP.setBackground(new Color(164, 44, 167));
		tbHeaderDDP.setForeground(Color.white);
		tbHeaderDDP.setFont(new Font("SansSerif", Font.BOLD, 14));

		tblDDP.getColumnModel().getColumn(0).setPreferredWidth(20);//maDDP
		tblDDP.getColumnModel().getColumn(1).setPreferredWidth(20);//maPhong
		tblDDP.getColumnModel().getColumn(2).setPreferredWidth(100);//tenKH
		tblDDP.getColumnModel().getColumn(3).setPreferredWidth(30);//sdt
		tblDDP.getColumnModel().getColumn(4).setPreferredWidth(40);//ngayDen
		tblDDP.getColumnModel().getColumn(5).setPreferredWidth(40);//gioDen
		tblDDP.getColumnModel().getColumn(6).setPreferredWidth(100);//tenNVLap
		tblDDP.getColumnModel().getColumn(7).setPreferredWidth(50);//ngayLap
		tblDDP.getColumnModel().getColumn(8).setPreferredWidth(100);//trangThaiDDP

		DefaultTableCellRenderer rightRenderer2=new DefaultTableCellRenderer();
		rightRenderer2.setHorizontalAlignment(JLabel.RIGHT);
		tblDDP.getColumnModel().getColumn(3).setCellRenderer(rightRenderer2);
		tblDDP.getColumnModel().getColumn(4).setCellRenderer(rightRenderer2);
		tblDDP.getColumnModel().getColumn(5).setCellRenderer(rightRenderer2);
		tblDDP.getColumnModel().getColumn(7).setCellRenderer(rightRenderer2);

		//		tableDDP.setOpaque(false);
		scrollPaneDDP.setViewportView(tblDDP);

		//background
		lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);

		//test data nhanh
//		txtTenKH.setText("Đoàn Phạm Bích Hợp");
//		txtSDT.setText("0708985897");
//		txtDiaChi.setText("1358 Quang Trung, P.14, Q.Gò Vấp");
		txtTenKH.setText("Đinh Quang Tuấn");
		txtSDT.setText("0944302210");
		txtDiaChi.setText("118 Hoàng Văn Thụ, P9, Q.Phú Nhuận");

		//su kien
		cboGio.addItemListener(this);

		loadDSPhongTrongVaDaDat(p);

		btnTim.addActionListener(this);
		btnThemDDP.addActionListener(this);
		btnSuaDDP.addActionListener(this);
//		btnHuyDDP.addActionListener(this);
		btnLamMoiDDP.addActionListener(this);

		cboSapXep.addActionListener(this);
		chkTatCa.addActionListener(this);
		rdoTheoMaPhong.addActionListener(this);
		rdoTheoLoaiPhong.addActionListener(this);
		rdoTheoGiaPhong.addActionListener(this);

		tblPhong.addMouseListener(this);
	}

	//xoa het data trong tblPhong
	private void removeDanhSachPhong(DefaultTableModel defaultTableModel) {
		while(tblPhong.getRowCount() > 0)
			modelPhong.removeRow(0);
	}

	//xoa het data trong tblDDP
	private void removeDanhSachDDP(DefaultTableModel defaultTableModel) {
		while(tblDDP.getRowCount() > 0)
			modelDDP.removeRow(0);
	}

	//xoa trang txt
	private void xoaTrang() {
		txtTim.setText("Tìm đơn đặt phòng theo tên khách hàng, sđt khách hàng.");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);

		txtTenKH.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		
		cboGio.setSelectedIndex(0);
		cboPhut.setSelectedIndex(0);
		chkTatCa.setSelected(false);
	}

	//load phong trong va da dat
	private void loadDSPhongTrongVaDaDat(Phong p) {
		removeDanhSachPhong(modelPhong);
		ArrayList<Phong> lstP = daoPhong.getPhongTrongVaDaDat();
		for(Phong infoP : lstP) {
			LoaiPhong lp = daoLoaiPhong.getLoaiPhongTheoMa(infoP.getLoaiPhong().getMaLoaiPhong());
			modelPhong.addRow(new Object[] {
					infoP.getMaPhong(), lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), dfGiaPhong.format(infoP.getGiaPhong()), infoP.getTinhTrangPhong()
			});
		}
	}

	//load ds ddp
	private void loadDanhSachDDP(DonDatPhong ddp) {
		removeDanhSachDDP(modelDDP);
		ArrayList<DonDatPhong> lstDDP = daoDonDatPhong.getDanhSachDDPKhongHuy();
		for(DonDatPhong infoDDP : lstDDP) {
			KhachHang kh = daoKhachHang.getKHTheoMa(infoDDP.getKhachHang().getMaKhangHang());
			NhanVien nv = daoNhanVien.getMaVaSDTNV(infoDDP.getNhanVien().getMaNhanVien());
			modelDDP.addRow(new Object[] {
					infoDDP.getMaDDP(), infoDDP.getPhong().getMaPhong(), kh.getTenKH(), kh.getSdt(),
					dfNgay.format(infoDDP.getNgayDen()), dfHienGio.format(infoDDP.getGioDen()), nv.getTenNhanVien(), dfNgay.format(infoDDP.getNgayLap()), infoDDP.getTrangThaiDDP()
			});
		}
		//		for(int i=0; i<=tblPhong.getRowCount(); i++) {
		//			String trangThaiP = tblPhong.getValueAt(i, 4).toString();
		//			if(trangThaiP.equals("Đã đặt"))
		//				tblPhong.setBackground(Color.yellow);
		//		}
	}

	private void findKH() {
		String input = txtTim.getText();
//		KhachHang kh = daoKhachHang.getKHTheoSDT(sdt);
		KhachHang kh = daoKhachHang.getKHTheoSDT(input);
		
	}
	
	private void addDDP() {
		//		try {
		String phatSinhMaDDP = daoPhatSinhMa.getMaDDP();
		String hoTen = txtTenKH.getText();
		String loaiKH = cboLoaiKH.getSelectedItem().toString();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String trangThaiDDP = cboTrangThaiDDP.getSelectedItem().toString();
		
		java.util.Date date = chooserNgayDen.getDate();
		Date ngayDen = new Date(date.getYear(), date.getMonth(), date.getDate());

		Date ngayLap = dNgayHienTai;
		
		int gio = Integer.parseInt(cboGio.getSelectedItem().toString());
		int phut = Integer.parseInt(cboPhut.getSelectedItem().toString());
		Time gioDen = new Time(gio, phut, 0);

		int chonPhong = tblPhong.getSelectedRow(); //chọn phòng lấy info từ maPhong
		String maPhongChon = tblPhong.getValueAt(chonPhong, 0).toString();
		Phong p = daoPhong.getPhongTheoMa(maPhongChon);
		String tinhTrangPhong = tblPhong.getValueAt(chonPhong, 4).toString();
		
		
		KhachHang kh = daoKhachHang.getKHTheoSDT(sdt);	//lấy maKH, tenKH từ sđt
		//info new KH
		String phatSinhMaKH = daoPhatSinhMa.getMaKH();
//		LoaiKH lKH = daoKhachHang.getMaLoaiKHFromTen(loaiKH);
//		String maLoaiKH = lKH.getMaLoaiKH();
		String maLoaiKH = daoLoaiKH.getMaLoaiKHTheoTen(loaiKH);

		String maNV = sHeaderMaNV; //lấy info NV từ maNV
		NhanVien nv= daoNhanVien.getMaVaSDTNV(maNV);

		/*
		 * 1. Nếu là kh cũ thì nv sẽ hỏi sđt của kh cũ, rồi tìm kiếm --> thông tin kh  load lên các txt, cbb tương ứng
		 * 2. Trong TH là khách hàng mới, NV sẽ phải tự nhập toàn bộ thông tin
		 * 3. Sau khi nhập xong --> chọn phòng --> Load thông tin lên table DDP (1 PHƯƠNG THỨC RIÊNG) 
		 * --> add KH mới (sql): getKH theo SĐT ở TXT nếu get được 1 KH.maKH.lenght != 0 => đó là KH cũ --> không thực hiện lệnh add kh sql
		 * 												Nếu kh.na.lenght == 0 --> kh là mới --> thực hiện lệnh add kh sql từ các txt
		 *  --> add đơn đặt phòng (sql)
		 *  
		 *  Mà nhớ, ko cần regex trong mấy đoạn này đâu, trước hết phải thêm được nó vô đã, cứ mặc định là người dùng nhập đúng, đủ thông tin, thêm được thì bắt lỗi lafmn nhanh thôi
		 *  
		 *  Các phương thức có thể chia: kiểm tra kh cũ --> 1pt có thể trả về true false này co trong dao lúc đó m chỉ get lên thôi,  nó ko trả về true false lúc kiểm tra sđt đâu 
		 * 
		 *  giờ m push fix năm đi, oke, t mà push là mỗi lần chạy chương trình sẽ có phần đăng nhập đấy nha hmmmm, còn không thì m viết thêm cái main
		 *  và như áp dụng vô, thì tìm kiếm đó để sau, tính sau, m sẽ làm từ giai đonạ 2 trước, nhập xong kiểm tra thông tin này nọ, theo các cái quy trình t nói đso
		 * Đó quy trình nó sẽ như v, m phải tư duy theo kiểu này trước, sau đó mới thực thi code theo từng giao đoạn thì nó đỡ nhọc hơn nhiều
		 */
		
		
		if(regex.regexTen(txtTenKH) && regex.regexSDT(txtSDT)) {
				if(cboGio.getSelectedItem().toString()=="" || cboPhut.getSelectedItem().toString()=="")
					JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ đến!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				if(trangThaiDDP.equals("Đã đặt"))
					JOptionPane.showMessageDialog(null, "Phòng này đã đặt, vui lòng chọn phòng trống khác!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				else {
					//so khớp sdt nhập trong ddp
//					if(daoKhachHang.matchedSdtKH(sdt)==true) { //kq=true thì load từ sql
					if(daoKhachHang.getKHTheoSDT(sdt) != null) { //kq=true thì load từ sql
						//them vao data
						DonDatPhong ddp=new DonDatPhong();
						ddp.setMaDDP(phatSinhMaDDP);
						ddp.setNgayLap(ngayLap);
						ddp.setGioDen(gioDen);
						ddp.setNgayDen(ngayDen);
						ddp.setTrangThaiDDP(trangThaiDDP);
						ddp.setKhachHang(kh);
						ddp.setNhanVien(nv);
						ddp.setPhong(p);
						try {
							daoDonDatPhong.themDDP(ddp);
						}catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this, "Thêm đơn đặt phòng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
							/// lỡ làm 1 cái này thôi, mấy chức năng hay nút khác, nhớ tách ra
						xoaTrang();
						modelDDP.addRow(new Object[] {
								phatSinhMaDDP, maPhongChon, kh.getTenKH(), sdt, 
								dfNgay.format(ngayDen), dfHienGio.format(gioDen), nv.getTenNhanVien(), dfNgay.format(ngayLap), trangThaiDDP
						});
						JOptionPane.showMessageDialog(this, "Thêm đơn đặt phòng thành công!", "Thông báo", JOptionPane.OK_OPTION);
					}
//					else { //kq=false thì tạo mới KH
//						//tao moi KH
//						KhachHang newKH=new KhachHang(phatSinhMaKH, new LoaiKH(maLoaiKH), hoTen, sdt, diaChi);
////						KhachHang newKH=new KhachHang(phatSinhMaKH, hoTen, diaChi, sdt, null, null, null, 0, null, new LoaiKH(maLoaiKH));
////						KhachHang newKH=new KhachHang();
////						newKH.setMaKhangHang(phatSinhMaKH);
////						newKH.setLoaiKH(lKH.getMaLoaiKH());
////						newKH.setTenKH(hoTen);
////						newKH.setSdt(sdt);
////						newKH.setDiaChi(diaChi);
////						daoKhachHang.themDanhSachKH(newKH);
//						
//					
//						try {
//							daoKhachHang.themKHTheoDDP(kh);
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//						
//						DonDatPhong ddpNewKH=new DonDatPhong();
//						ddpNewKH.setMaDDP(phatSinhMaDDP);
//						ddpNewKH.setNgayLap(ngayLap);
//						ddpNewKH.setGioDen(gioDen);
//						ddpNewKH.setNgayDen(ngayDen);
//						ddpNewKH.setTrangThaiDDP(trangThaiDDP);
//						ddpNewKH.setKhachHang(newKH);
//						ddpNewKH.setNhanVien(nv);
//						ddpNewKH.setPhong(p);
//						try {
//							daoDonDatPhong.themDDP(ddp);
//						}catch (SQLException e) {
//							e.printStackTrace();
//							JOptionPane.showMessageDialog(this, "Thêm đơn đặt phòng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//						}
//
//						//them vao table
//						xoaTrang();
//						modelDDP.addRow(new Object[] {
//								phatSinhMaDDP, maPhongChon, newKH.getTenKH(), newKH.getSdt(), 
//								dfNgay.format(ngayDen), dfHienGio.format(gioDen), nv.getTenNhanVien(), dfNgay.format(ngayLap), trangThaiDDP
//						});
//						JOptionPane.showMessageDialog(this, "Thêm đơn đặt phòng thành công!", "Thông báo", JOptionPane.OK_OPTION);
//					}
				}
			}

		//		}catch (Exception e) {
		//			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		//		}
	}
	
	private void updateDDP() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnTim)) {
			findKH();
		}

		//thêm ddp
		if(o.equals(btnThemDDP)) {
			addDDP();
		}
		
		//sửa ddp
		if(o.equals(btnSuaDDP)) {
			updateDDP();
		}
		
		//reset
		if(o.equals(btnLamMoiDDP)) {
			xoaTrang();
			removeDanhSachDDP(modelDDP);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getItem();
		
	}
}
