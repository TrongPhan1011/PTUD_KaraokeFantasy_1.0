package app;

import java.awt.Color;
import java.awt.Dimension;
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
import java.sql.Connection;
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
import javax.swing.JCheckBox;
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

import com.mindfusion.drawing.Colors;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOKhachHang;
import dao.DAOLoaiKH;
import dao.DAONhanVien;
import dao.DAOPhatSinhMa;
import dao.Regex;
import entity.KhachHang;
import entity.LoaiKH;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class FrmKhachHang extends JPanel implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private Panel pMain;
	private JTextField txtTK;
	private JButton btnTim;
	private JButton btnThemKH;
	private JButton btnSuaKH;
	private JButton btnXoaKH;
	private JButton btnReset;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtCccd;
	private JTextField txtPoint;
	private JTextField txtDiaChi;
	private JTable tableKH;
	private DAOLoaiKH daoLoaiKH;
	private DAOKhachHang daoKhachHang;
	private DAOPhatSinhMa daoMaKH;
	private DefaultTableModel modelKhachHang;
	private JComboBox<String> cbbloaiKH;
	private JComboBox<String> cbbgioiTinh;
	private JComboBox<String> cbbSort;
	private JDateChooser dateChooserNgaySinh;
	private JDateChooser dateChooserNgayDangKy;
	private SimpleDateFormat dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat dfNgayDangKy = new SimpleDateFormat("dd/MM/yyyy");
	private JRadioButton rdoTheoMaKH;
	private JRadioButton rdoTheoLoaiKH;
	private JRadioButton rdoTheoTenKH;
	private JCheckBox chkAll = new JCheckBox("T???t c???");
	private Regex regex;
	private KhachHang kh;
	private ButtonGroup bg;

	public Panel getFrmKH() {
		return this.pMain;
	}

	public FrmKhachHang(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;

		// connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// khai bao dao
		daoLoaiKH = new DAOLoaiKH();
		daoKhachHang = new DAOKhachHang();
		daoMaKH = new DAOPhatSinhMa();
		regex = new Regex();

		// Giao dien
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);

		JLabel lblQuanLyKH = new JLabel("Qu???n l?? kh??ch h??ng");
		lblQuanLyKH.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQuanLyKH.setBounds(31, 11, 251, 33);
		pMain.add(lblQuanLyKH);

		// lblTim
		JLabel lblTim = new JLabel("T??m ki???m:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(310, 13, 90, 35);
		pMain.add(lblTim);

		// txtTK
		txtTK = new JTextField();
		txtTK.setText("T??m kh??ch h??ng theo m??, t??n, s??t v?? lo???i kh??ch h??ng.");
		txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTK.setForeground(Colors.LightGray);
		txtTK.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTK.setBounds(400, 12, 526, 33);
		txtTK.addFocusListener(new FocusAdapter() { // place holder
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTK.getText().equals("T??m kh??ch h??ng theo m??, t??n, s??t v?? lo???i kh??ch h??ng.")) {
					txtTK.setText("");
					txtTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTK.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTK.getText().equals("")) {
					txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTK.setText("T??m kh??ch h??ng theo m??, t??n, s??t v?? lo???i kh??ch h??ng.");
					txtTK.setForeground(Colors.LightGray);
				}
			}
		});
		pMain.add(txtTK);

		// btnTim
		btnTim = new FixButton("T??m");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(942, 11, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);

		JLabel lblNhac1 = new JLabel("");

		lblNhac1.setBounds(31, 160, 147, 146);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);

		JLabel lblHoTen = new JLabel("H??? v?? t??n:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(146, 65, 90, 19);
		pMain.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("SansSerif", Font.PLAIN, 14));

		txtHoTen.setBounds(230, 62, 189, 28);
		txtHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtHoTen.setBounds(239, 62, 189, 28);
		txtHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(txtHoTen);
		txtHoTen.setColumns(10);

		JLabel lblSDT = new JLabel("S??T:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(146, 106, 46, 14);
		pMain.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSDT.setBounds(239, 100, 189, 28);
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtSDT.setBounds(239, 101, 189, 28);
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblAddress = new JLabel("?????a ch???:");
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAddress.setBounds(146, 144, 72, 20);
		pMain.add(lblAddress);

		JLabel lblLoaiKH = new JLabel("Lo???i kh??ch h??ng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(473, 65, 124, 19);
		pMain.add(lblLoaiKH);

		cbbloaiKH = new JComboBox<String>();
		cbbloaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbloaiKH.setBounds(610, 60, 170, 27);
		cbbloaiKH.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbloaiKH.setBackground(new Color(255, 255, 255));

		pMain.add(cbbloaiKH);

		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCccd.setBounds(473, 108, 65, 14);
		pMain.add(lblCccd);

		txtCccd = new JTextField();
		txtCccd.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtCccd.setBounds(610, 100, 170, 27);

		pMain.add(txtCccd);
		txtCccd.setColumns(10);
		txtCccd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));

		JLabel lblGioiTinh = new JLabel("Gi???i t??nh:");
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioiTinh.setBounds(473, 149, 72, 14);
		pMain.add(lblGioiTinh);

		cbbgioiTinh = new JComboBox<String>();
		cbbgioiTinh.setBounds(610, 140, 170, 28);
		cbbgioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbgioiTinh.setBackground(Color.WHITE);
		cbbgioiTinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		String cbbGioiTinh[] = { "Nam", "N???" };
		for (int i = 0; i < cbbGioiTinh.length; i++) {
			cbbgioiTinh.addItem(cbbGioiTinh[i]);
		}
		pMain.add(cbbgioiTinh);

		JLabel lblNgaySinh = new JLabel("Ng??y sinh:");
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgaySinh.setBounds(840, 63, 102, 18);
		pMain.add(lblNgaySinh);

		JLabel lblNgayDangKy = new JLabel("Ng??y ????ng k??:");
		lblNgayDangKy.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgayDangKy.setBounds(840, 103, 102, 20);
		pMain.add(lblNgayDangKy);

		JLabel lblDiem = new JLabel("??i???m t??ch l??y:");
		lblDiem.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiem.setBounds(840, 148, 102, 18);
		pMain.add(lblDiem);

		txtPoint = new JTextField();
		txtPoint.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtPoint.setBounds(945, 145, 191, 28);
		txtPoint.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtPoint.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pMain.add(txtPoint);
		txtPoint.setEditable(isDisplayable());
		txtPoint.setColumns(10);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		dateChooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dateChooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		dateChooserNgaySinh.setBounds(945, 61, 191, 28);
		pMain.add(dateChooserNgaySinh);

		btnThemKH = new FixButton("Th??m");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemKH.setBackground(new Color(114, 23, 153));
		btnThemKH.setBounds(319, 202, 108, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);

		btnSuaKH = new FixButton("S???a");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 23, 153));
		btnSuaKH.setBounds(489, 202, 108, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaKH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaKH);

		btnXoaKH = new FixButton("X??a");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 23, 153));
		btnXoaKH.setBounds(647, 202, 108, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaKH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaKH);

		btnReset = new FixButton("L??m m???i");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnReset.setBackground(new Color(114, 23, 153));
		btnReset.setBounds(821, 202, 108, 35);
		Image imgLamMoiKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiKH = imgLamMoiKH.getScaledInstance(25, 25, 0);
		btnReset.setIcon(new ImageIcon(resizeImgLamMoiKH));

		pMain.add(btnReset);

		JScrollPane scrollPaneKH = new JScrollPane();
		scrollPaneKH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneKH.setBackground(new Color(164, 44, 167));
		scrollPaneKH.setBounds(31, 310, 1212, 285);
		pMain.add(scrollPaneKH);

		String col[] = { "M?? KH", "H??? v?? t??n KH", "Lo???i KH", "Gi???i t??nh", "Ng??y sinh", "?????a ch???", "S??T", "CCCD",
				"Ng??y ????ng k??", "??i???m t??ch l??y" };
		modelKhachHang = new DefaultTableModel(col, 0);
		tableKH = new JTable(modelKhachHang);

		JTableHeader tbHeader = tableKH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));

		tableKH.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableKH.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableKH.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableKH.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableKH.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableKH.getColumnModel().getColumn(5).setPreferredWidth(400);
		tableKH.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableKH.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableKH.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableKH.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableKH.setAutoResizeMode(tableKH.AUTO_RESIZE_OFF);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableKH.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tableKH.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		tableKH.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tableKH.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);

		tableKH.setShowGrid(true);
		tableKH.setShowHorizontalLines(true);
		tableKH.setBackground(Color.WHITE);
		tableKH.setSelectionBackground(new Color(164, 44, 167, 30));
		tableKH.setSelectionForeground(new Color(114, 23, 153));
		tableKH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableKH.setRowHeight(30);
		tableKH.setGridColor(getBackground());
		// tableKH.setOpaque(false);
		tableKH.setSelectionBackground(new Color(164, 44, 167, 30));
		scrollPaneKH.setViewportView(tableKH);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtDiaChi.setBounds(342, 145, 189, 51);
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDiaChi.setBounds(239, 140, 189, 51);
		pMain.add(txtDiaChi);

		dateChooserNgayDangKy = new JDateChooser();
		dateChooserNgayDangKy.setDateFormatString("dd/MM/yyyy");
		dateChooserNgayDangKy.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		dateChooserNgayDangKy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dateChooserNgayDangKy.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserNgayDangKy.getCalendarButton().setBackground(new Color(102, 0, 153));
		dateChooserNgayDangKy.setBounds(945, 102, 191, 28);
		pMain.add(dateChooserNgayDangKy);

		JPanel pSapXep = new JPanel();
		pSapXep.setBackground(new Color(238, 239, 243, 90));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "S\u1EAFp x\u1EBFp",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(239, 248, 897, 51);
		pMain.add(pSapXep);
		// pSapXep.setLayout(null);

		cbbSort = new JComboBox<String>();
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(Color.WHITE);
		cbbSort.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));

		String cbSort[] = { "T??ng d???n", "Gi???m d???n" };
		for (int i = 0; i < cbSort.length; i++) {
			cbbSort.addItem(cbSort[i]);
		}
		pSapXep.setLayout(null);
		cbbSort.setBounds(26, 14, 136, 28);
		pSapXep.add(cbbSort);

		chkAll.setFont(new Font("SansSerif", Font.BOLD, 14));
		chkAll.setBackground(new Color(220, 210, 239));
		chkAll.setBounds(185, 15, 109, 27);
		pSapXep.add(chkAll);
		chkAll.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==1) {
					loadDanhSachKH();
				}
				else
					clearTable();
			}
		});


		// rdoTheoMaKH.setSelected(true);
		rdoTheoMaKH = new JRadioButton("Theo m?? kh??ch h??ng");
		rdoTheoMaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaKH.setBackground(new Color(220, 210, 239));
		rdoTheoMaKH.setBounds(309, 15, 167, 27);
		pSapXep.add(rdoTheoMaKH);

		rdoTheoTenKH = new JRadioButton("Theo t??n kh??ch h??ng");
		rdoTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoTenKH.setBackground(new Color(220, 210, 239));
		rdoTheoTenKH.setBounds(491, 15, 171, 27);
		pSapXep.add(rdoTheoTenKH);

		rdoTheoLoaiKH = new JRadioButton("Theo lo???i kh??ch h??ng");
		rdoTheoLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLoaiKH.setBackground(new Color(220, 210, 239));
		rdoTheoLoaiKH.setBounds(682, 15, 171, 27);
		pSapXep.add(rdoTheoLoaiKH);

		bg = new ButtonGroup();
		bg.add(rdoTheoMaKH);
		bg.add(rdoTheoTenKH);
		bg.add(rdoTheoLoaiKH);
		bg.clearSelection();
		// rdoTheoMaKH.setSelected(true);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1281, 606);
		Image imgBackground = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackground);

		// end giao dien

		// Load t??n lo???i KH
		ArrayList<LoaiKH> lsLoaiKH = daoLoaiKH.getAllLoaiKH();
		for (LoaiKH lkh : lsLoaiKH) {
			cbbloaiKH.addItem(lkh.getTenLoaiKH());
		}

		btnThemKH.addActionListener(this);
		btnXoaKH.addActionListener(this);
		btnSuaKH.addActionListener(this);
		tableKH.addMouseListener(this);
		btnReset.addActionListener(this);
		//chkAll.addActionListener(this);
		btnTim.addActionListener(this);
		rdoTheoMaKH.addActionListener(this);
		rdoTheoTenKH.addActionListener(this);
		rdoTheoLoaiKH.addActionListener(this);
	}

//end main

	// Load danh sach tu du lieu
	public void loadDanhSachKH() {
		clearTable();
		ArrayList<KhachHang> lsKH = daoKhachHang.getDanhSachKH();
		for (KhachHang kh : lsKH) {
			LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(kh.getLoaiKH().getMaLoaiKH());
			modelKhachHang.addRow(new Object[] { kh.getMaKhangHang(), kh.getTenKH(), loaiKH.getTenLoaiKH(),
					kh.getGioiTinh(), dfNgaySinh.format(kh.getNgaySinh()), kh.getDiaChi(), kh.getSdt(), kh.getCccd(),
					dfNgayDangKy.format(kh.getNgayDangKy()), kh.getDiemTichLuy() });
		}
	}

	// load thong tin 1 nguoi
	public void loadThongTin(KhachHang kh) {
		LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(kh.getLoaiKH().getMaLoaiKH());
		modelKhachHang.setRowCount(0);
		modelKhachHang.addRow(new Object[] { kh.getMaKhangHang(), kh.getTenKH(), loaiKH.getTenLoaiKH(),
				kh.getGioiTinh(), dfNgaySinh.format(kh.getNgaySinh()), kh.getDiaChi(), kh.getSdt(), kh.getCccd(),
				dfNgayDangKy.format(kh.getNgayDangKy()), kh.getDiemTichLuy() });
	}

	// load theo ten kh
	private void loadDanhSachTenKH(ArrayList<KhachHang> kh1) {
		//clearTable();
		ArrayList<KhachHang> lstName = daoKhachHang.getTenKH(txtTK.getText());
		for (KhachHang lskh : lstName) {
			LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(lskh.getLoaiKH().getMaLoaiKH());
			modelKhachHang.addRow(new Object[] { lskh.getMaKhangHang(), lskh.getTenKH(), loaiKH.getTenLoaiKH(),
					lskh.getGioiTinh(), dfNgaySinh.format(lskh.getNgaySinh()), lskh.getDiaChi(), lskh.getSdt(),
					lskh.getCccd(), dfNgayDangKy.format(lskh.getNgayDangKy()), lskh.getDiemTichLuy() });
		}
	}
	private void loadDanhSachTenKHTheoLoai(ArrayList<KhachHang> kh2) {
		//clearTable();
		ArrayList<KhachHang> lstName = daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen(txtTK.getText().toString()));
		for (KhachHang lskh : lstName) {
			LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(lskh.getLoaiKH().getMaLoaiKH());
			modelKhachHang.addRow(new Object[] { lskh.getMaKhangHang(), lskh.getTenKH(), loaiKH.getTenLoaiKH(),
					lskh.getGioiTinh(), dfNgaySinh.format(lskh.getNgaySinh()), lskh.getDiaChi(), lskh.getSdt(),
					lskh.getCccd(), dfNgayDangKy.format(lskh.getNgayDangKy()), lskh.getDiemTichLuy() });
		}
	}

	// Lam moi danh sach
	public void clearTable() {
		while (tableKH.getRowCount() > 0) {
			modelKhachHang.removeRow(0);
		}
	}

	// Nut them
	public void themKHVaoDanhSach() {
		// int optThem = JOptionPane.showConfirmDialog(this, "B???n c?? ch???n ch???n mu???n th??m
		// kh??ch h??ng kh??ng?", "Th??ng b??o", JOptionPane.YES_NO_OPTION );

		if (regex.regexTen(txtHoTen) && regex.regexSDT(txtSDT) && regex.regexCCCD(txtCccd)
				&& regex.regexDiaChi(txtDiaChi)) {
			String maKH = daoMaKH.getMaKH();
			String tenKH = txtHoTen.getText().toString();
			String sdt = txtSDT.getText().toString();
			String diaChi = txtDiaChi.getText().toString();
			String cccd = txtCccd.getText().toString();
			String gioiTinh = cbbgioiTinh.getSelectedItem().toString();
			LoaiKH loaiKH = new LoaiKH(daoLoaiKH.getMaLoaiKHTheoTen(cbbloaiKH.getSelectedItem().toString()));

			int ngaySinh = dateChooserNgaySinh.getDate().getDate();
			int thangSinh = dateChooserNgaySinh.getDate().getMonth();
			int namSinh = dateChooserNgaySinh.getDate().getYear();

			int ngayDangKy = dateChooserNgayDangKy.getDate().getDate();
			int thangDangKy = dateChooserNgayDangKy.getDate().getMonth();
			int namDangKy = dateChooserNgayDangKy.getDate().getYear();
			int diemTichLuy = Integer.parseInt(txtPoint.getText().toString());

			@SuppressWarnings("deprecation")
			KhachHang kh = new KhachHang(maKH, tenKH, diaChi, sdt, cccd, new Date(namSinh, thangSinh, ngaySinh),
					gioiTinh, diemTichLuy, new Date(ngayDangKy, thangDangKy, namDangKy), loaiKH);
			daoKhachHang.themDanhSachKH(kh);
			// tableKH.getRowCount()
			loadThongTin(kh);
			resetAll();
			JOptionPane.showMessageDialog(this, "Th??m kh??ch h??ng th??nh c??ng");
		}
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p ?????y ????? th??ng tin");
		
	}

	// N??t s???a
	public void suaThongTin() {
		int row = tableKH.getSelectedRow();
		if (row >= 0) {
			int update = JOptionPane.showConfirmDialog(this, "B???n mu???n s???a th??ng tin kh??ch h??ng  n??y?", "Th??ng b??o",
					JOptionPane.YES_NO_OPTION);
			if (update == JOptionPane.YES_OPTION) {
				if (regex.regexTen(txtHoTen) && regex.regexSDT(txtSDT) && regex.regexCCCD(txtCccd)
						&& regex.regexDiaChi(txtDiaChi)) {
					String maKH = modelKhachHang.getValueAt(row, 0).toString();
					String tenKH = txtHoTen.getText().toString();
					String sdt = txtSDT.getText().toString();
					String diaChi = txtDiaChi.getText().toString();
					String cccd = txtCccd.getText().toString();
					String gioiTinh = cbbgioiTinh.getSelectedItem().toString();
					LoaiKH loaiKH = new LoaiKH(daoLoaiKH.getMaLoaiKHTheoTen(cbbloaiKH.getSelectedItem().toString()));
					int ngaySinh = dateChooserNgaySinh.getDate().getDate();
					int thangSinh = dateChooserNgaySinh.getDate().getMonth();
					int namSinh = dateChooserNgaySinh.getDate().getYear();
					int ngayDangKy = dateChooserNgayDangKy.getDate().getDate();
					int thangDangKy = dateChooserNgayDangKy.getDate().getMonth();
					int namDangKy = dateChooserNgayDangKy.getDate().getYear();
					int diemTichLuy = Integer.parseInt(txtPoint.getText().toString());
					try {
						KhachHang kh = new KhachHang(maKH, tenKH, diaChi, sdt, cccd,
								new Date(namSinh, thangSinh, ngaySinh), gioiTinh, diemTichLuy,
								new Date(ngayDangKy, thangDangKy, namDangKy), loaiKH);
						daoKhachHang.suaThongTinKhachHang(kh);
						loadDanhSachKH();
						resetAll();
						JOptionPane.showMessageDialog(this, "Th??ng tin kh??ch h??ng ???? ???????c s???a!", "Th??ng b??o",
								JOptionPane.OK_OPTION);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ch???nh s???a th??ng tin th???t b???i!", "Th??ng b??o",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n th??ng tin kh??ch h??ng c???n s???a!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// L??m m???i
	public void resetAll() {
		txtTK.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtCccd.setText("");
		txtDiaChi.setText("");
		dateChooserNgaySinh.setDate(new Date(0));
		dateChooserNgayDangKy.setDate(new Date(0));
		txtPoint.setText("");
	}

	// T??m ki???m
	private void findKH() {
		KhachHang kh = daoKhachHang.getKH(txtTK.getText().toLowerCase().trim());
		ArrayList<KhachHang>kh1 = daoKhachHang.getTenKH(txtTK.getText().toLowerCase().trim());
		/*
		 * String loai = ; JTextField tam = new JTextField(); tam.setText(loai);
		 */
		ArrayList<KhachHang>kh2 = daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen(txtTK.getText().toString()));
		if (!txtTK.getText().equals("") && !txtTK.getText().equals("T??m kh??ch h??ng theo m??, t??n, s??t v?? lo???i kh??ch h??ng.")) {
			String messTenKH = "\n - H??? t??n. V?? d???: Nguy???n V??n A";
			String messLKH = "\n - T??m theo lo???i kh??ch h??ng: kh??ch h??ng th?????ng, th??nh vi??n, VIP, kh??ng c??n l?? kh??ch h??ng";
			String messSDT = "\n - S??T g???m 10 ch??? s??? v?? b???t ?????u b???ng s??? 0";
			//System.out.println(regex.regexTimKiemMaLoaiKH(txtTK));
			//loadDanhSachKH();
			if (regex.regexTimKiemMaKH(txtTK)) {
				try {
					clearTable();
					loadThongTin(kh);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y m?? kh??ch h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
			} else if (regex.regexTimNV(txtTK)) {
				try {
					System.out.println("alo 123 5");
					clearTable();
					loadDanhSachTenKH(kh1);
				} catch (Exception e) {
					// TODO: handle exception

					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y t??n kh??ch h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
			} else if (regex.regexSDT(txtTK)) {
				try {
					System.out.println(regex.regexSDT(txtTK));
					clearTable();
					loadThongTin(kh);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y s??? ??i???n tho???i kh??ch h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
				}
			else if (regex.regexTimKiemMaLoaiKH(txtTK)) {

				System.out.println("alo 123 5");
				try {
					//System.out.println(regex.regexTimKiemMaLoaiKH(txtTK));
					//clearTable();
					loadDanhSachTenKHTheoLoai(kh2);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y lo???i kh??ch h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
				}
			
			  else JOptionPane.showMessageDialog(null,
			  "Th??ng tin t??m ki???m kh??ng h???p l???!\nTh??ng tin c?? th??? t??m ki???m:\n - M?? kh??ch h??ng. V?? d???: KH001"
			  + messTenKH + messSDT + messLKH, "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
			 
		} else {
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Xoa khach hang
	private boolean cancelKH() {
		int row = tableKH.getSelectedRow();
		if (row >= 0) {
			int cancel = JOptionPane.showConfirmDialog(null, "B???n mu???n x??a kh??ch h??ng n??y?", "Th??ng b??o",
					JOptionPane.YES_NO_OPTION);
			if (cancel == JOptionPane.YES_OPTION) {
				String maKH = tableKH.getValueAt(row, 0).toString();
				try {
					modelKhachHang.removeRow(row);
					clearTable();
					daoKhachHang.huyKH(maKH);
					loadDanhSachKH();
					JOptionPane.showMessageDialog(null, "???? x??a kh??ch h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "x??a kh??ch h??ng th???t b???i!", "Th??ng b??o",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "B???n ch??a ch???n th??ng tin kh??ch h??ng c???n h???y!", "Th??ng b??o",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	//Sap xep theo ma khach hang giam dan
	
	  public void sortMaKHGiamDan(KhachHang kh) {
					clearTable();
					ArrayList<KhachHang> lsKH = daoKhachHang.sortByMa();
					for (KhachHang khs : lsKH) {
						LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
						modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
								khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
								dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
					}
}
	  
	//Sap xep theo ten khach hang giam dan
	  public void sortTenKHGiamDan(KhachHang kh) {
			clearTable();
			ArrayList<KhachHang> lsKH = daoKhachHang.getDanhSachKH();		
			Collections.sort(lsKH, new Comparator<KhachHang>() {
				public int compare(KhachHang o1, KhachHang o2) {
					return o2.getTenKH().compareTo(o1.getTenKH());
				}
			});
			
			for (KhachHang khs : lsKH) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}
}
	  //Sap xep theo ten khach hang tang dan
	  public void sortTenKHTangDan(KhachHang kh) {
			clearTable();
			ArrayList<KhachHang> lsKH = daoKhachHang.getDanhSachKH();		
			Collections.sort(lsKH, new Comparator<KhachHang>() {
				public int compare(KhachHang o1, KhachHang o2) {
					return o1.getTenKH().compareTo(o2.getTenKH());
				}
			});
			
			for (KhachHang khs : lsKH) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}
}
	  //sap xep loaiKh giam dan
	  public void sortLoaiKHGiamDan(KhachHang kh) {
		  	clearTable();
			ArrayList<KhachHang> lsVip= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Th??nh vi??n VIP"));
			for (KhachHang khs : lsVip) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}

			ArrayList<KhachHang> lsThanhVien= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Th??nh vi??n th?????ng"));
			for (KhachHang khs : lsThanhVien) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}

			ArrayList<KhachHang> lsKhachHang= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Kh??ch h??ng th?????ng"));
			for (KhachHang khs : lsKhachHang) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}
	  }
	  
	  //sap xep loaiKH tang dan
	  public void sortLoaiKHTangDan(KhachHang kh) {
		  	clearTable();
			ArrayList<KhachHang> lsKhachHang= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Kh??ch h??ng th?????ng"));
			for (KhachHang khs : lsKhachHang) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}

			ArrayList<KhachHang> lsThanhVien= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Th??nh vi??n th?????ng"));
			for (KhachHang khs : lsThanhVien) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}


			
			ArrayList<KhachHang> lsVip= daoKhachHang.getKHTheoLoai(daoLoaiKH.getMaLoaiKHTheoTen("Th??nh vi??n VIP"));
			for (KhachHang khs : lsVip) {
				LoaiKH loaiKH = daoLoaiKH.getLoaiKHTheoMaLoai(khs.getLoaiKH().getMaLoaiKH());
				modelKhachHang.addRow(new Object[] { khs.getMaKhangHang(), khs.getTenKH(), loaiKH.getTenLoaiKH(),
						khs.getGioiTinh(), dfNgaySinh.format(khs.getNgaySinh()), khs.getDiaChi(), khs.getSdt(), khs.getCccd(),
						dfNgayDangKy.format(khs.getNgayDangKy()), khs.getDiemTichLuy() });
			}
	  }
	


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	// Hi???n th??? th??ng tin khi ch???n v??o b???ng
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableKH)) {
			int row = tableKH.getSelectedRow();
			txtHoTen.setText(modelKhachHang.getValueAt(row, 1).toString());
			cbbloaiKH.setSelectedItem(modelKhachHang.getValueAt(row, 2).toString());
			txtSDT.setText(modelKhachHang.getValueAt(row, 6).toString());
			txtCccd.setText(modelKhachHang.getValueAt(row, 7).toString());
			txtDiaChi.setText(modelKhachHang.getValueAt(row, 5).toString());
			cbbgioiTinh.setSelectedItem(modelKhachHang.getValueAt(row, 3).toString());
			txtPoint.setText(modelKhachHang.getValueAt(row, 9).toString());
			String ngaySinh = modelKhachHang.getValueAt(row, 4).toString();
			String ngayDangKy = modelKhachHang.getValueAt(row, 8).toString();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				java.util.Date d = df.parse(ngaySinh);
				java.util.Date d1 = df.parse(ngayDangKy);
				dateChooserNgaySinh.setDate(d);
				dateChooserNgayDangKy.setDate(d1);
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
		if (o.equals(btnThemKH)) {
			themKHVaoDanhSach();
		}
		if (o.equals(btnSuaKH)) {
			suaThongTin();
		}
		if (o.equals(btnReset)) {
			clearTable();
			resetAll();
		}
		if (o.equals(btnXoaKH)) {
			cancelKH();
		}  
		if (o.equals(btnTim)) {
			findKH();
		}
		if(cbbSort.getSelectedItem()=="T??ng d???n") {
			if(o.equals(rdoTheoMaKH)) {
				loadDanhSachKH();
			}
			if(o.equals(rdoTheoTenKH))
				sortTenKHTangDan(kh);
			if(o.equals(rdoTheoLoaiKH))
				sortLoaiKHTangDan(kh);
		}
		if(cbbSort.getSelectedItem()=="Gi???m d???n"){
			clearTable();
			if(o.equals(rdoTheoMaKH)) {
				sortMaKHGiamDan(kh);
			}
			if(o.equals(rdoTheoTenKH))
				sortTenKHGiamDan(kh);
			if(o.equals(rdoTheoLoaiKH))
				sortLoaiKHGiamDan(kh);
		}

	}
}
