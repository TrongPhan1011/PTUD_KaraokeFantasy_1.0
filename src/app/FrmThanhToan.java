package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.ConnectDB;
import dao.DAOCTDDP;
import dao.DAOCTHD;
import dao.DAODonDatPhong;
import dao.DAOHoaDon;
import dao.DAOKhachHang;
import dao.DAOLoaiMH;
import dao.DAOLoaiPhong;
import dao.DAOMatHang;
import dao.DAONhanVien;
import dao.DAOPhatSinhMa;
import dao.DAOPhong;
import dao.Regex;
import entity.CTDDP;
import entity.CTHD;
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiKH;
import entity.LoaiMatHang;
import entity.LoaiPhong;
import entity.MatHang;
import entity.NhanVien;
import entity.Phong;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmThanhToan extends JPanel implements ActionListener, MouseListener,ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private JFrame frm;
	private Panel pMain;
	
	private JTextField txtTim;
	private JTextField txtSoLuong;
	
	private DefaultTableModel modelMatHang;
	private JTable tbMatHang;
	private JButton btnDSHD,btnTim;
	private DAOLoaiMH daoLoaiMH;
	private JComboBox<String> cbbTenMH;
	private JComboBox<String> cbbLoaiMH;
	private DAOMatHang daoMatHang;
	private JButton btnThemMH;
	private JButton btnXoaMH;
	private JButton btnLamMoiMH;
	private JButton btnThanhToan;
	private JButton btnLamMoiHD;
	private DAOCTDDP daoCTDDP;
	private JPanel pPhong;
	private DAOLoaiPhong daoLoaiPhong;
	private JLabel lblMaPhong;
	private DAODonDatPhong daoDDP;
	private DAOPhong daoPhong;
	private JLabel lblMaKH;
	private DAOKhachHang daoKhachHang;
	private JLabel lblTenKH;
	private JLabel lblGioVao;
	private JLabel lblPhutVao;
	private JLabel lblThanhTien;
	private JLabel lblPhuThu;
	private JLabel lblThoiGian;
	private JLabel lblGiaPhong;
	private JComboBox<String> cbbPhutRa;
	private JComboBox<String> cbbGioRa;
	private JComboBox<String> cbbPhuThu;
	private JLabel lblNhanVienLap;
	private DAOHoaDon daoHD;
	private DAOPhatSinhMa daoMa;
	private DAONhanVien daoNhanVien;
	private JRadioButton rdbtnGiamSL;
	private DecimalFormat df;
	private DecimalFormat dfTable;
	private Regex regex;
	private JLabel lblThanhToanLoaiKH;
	private JLabel lblGiamGia;
	private JButton btnInHoaDon;
	private DAOCTHD daoCTHD;
	private double giamGia = 0;
	
	public Panel getFrmQLBH() {
		return this.pMain;
	}
	
	public FrmThanhToan(JFrame frm,String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai)  {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		this.frm = frm;
		
//connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//khai bao dao
		daoLoaiMH = new DAOLoaiMH();
		daoCTHD = new DAOCTHD();
		daoMatHang = new DAOMatHang();
		daoCTDDP = new DAOCTDDP();
		daoLoaiPhong = new DAOLoaiPhong();
		daoDDP = new DAODonDatPhong();
		daoPhong = new DAOPhong();
		daoKhachHang =  new DAOKhachHang();
		daoHD = new DAOHoaDon();
		daoNhanVien = new DAONhanVien();
		daoMa = new DAOPhatSinhMa();
		regex = new Regex();
		
		
		
//Main UI
		// get font
		IconFontSwing.register(FontAwesome.getIconFont());
		
		setLayout(null);
		
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lbbTitle = new JLabel("Qu???n l?? thanh to??n");
		lbbTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
		lbbTitle.setBounds(24, 10, 268, 33);
		pMain.add(lbbTitle);
		
		JLabel lblSubTimKiem = new JLabel("T??m ki???m:");
		lblSubTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSubTimKiem.setBounds(374, 13, 90, 35);
		pMain.add(lblSubTimKiem);
		
		txtTim = new JTextField();
		txtTim.setBounds(474, 12, 281, 33);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 14));
		txtTim.setText("T??m ph??ng ??ang thu?? theo m?? ph??ng.");
		txtTim.setForeground(Color.lightGray);

		pMain.add(txtTim);
		txtTim.setColumns(10);
		

		
		
		btnTim = new FixButton("T??m"); 
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(786, 11, 98, 33);
		
		Icon iconTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, new Color(255, 255, 255));
		btnTim.setIcon(iconTim);
		
		pMain.add(btnTim);
		
		
		
		
		btnDSHD = new FixButton("Xem danh s??ch h??a ????n");
		
		btnTim.setBackground(new Color(114, 23 ,153));
		btnDSHD.setForeground(Color.WHITE);
		btnDSHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDSHD.setBackground(new Color(114, 23, 153));
		btnDSHD.setBounds(1015, 13, 232, 33);
		

		Icon iconDSHD = IconFontSwing.buildIcon(FontAwesome.LIST_ALT, 20, new Color(57, 210, 247));
		btnDSHD.setIcon(iconDSHD);
		
		pMain.add(btnDSHD);
	
		
		JLabel lblHeaderPhong = new JLabel("Ph??ng ??ang h??t");
		lblHeaderPhong.setFont(new Font("SansSerif", Font.BOLD, 18));
	
		lblHeaderPhong.setBounds(551, 47, 162, 26);
		pMain.add(lblHeaderPhong);
		
		pPhong = new JPanel();
		
		pPhong.setBackground(Color.white);		//new Color(164, 44, 167,20)

		
		JScrollPane scrollPane = new JScrollPane(pPhong);
		scrollPane.setViewportView(pPhong);
		scrollPane.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pPhong.setLayout(new GridLayout(0, 4, 0, 0));
		
		
		scrollPane.setBounds(24, 72, 1223, 102);
		pMain.add(scrollPane);
		
		JLabel lblSubPhong = new JLabel("Ph??ng : ");
		lblSubPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhong.setBounds(315, 185, 56, 26);
		pMain.add(lblSubPhong);
		
		lblMaPhong = new JLabel("");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaPhong.setBounds(370, 185, 71, 26);
		pMain.add(lblMaPhong);
		
		JLabel lblSubTenKH = new JLabel("Kh??ch h??ng: ");
		lblSubTenKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenKH.setBounds(504, 185, 90, 26);
		pMain.add(lblSubTenKH);
		
		lblMaKH = new JLabel("");
		lblMaKH.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaKH.setBounds(604, 185, 71, 26);
		pMain.add(lblMaKH);
		
		lblTenKH = new JLabel("");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenKH.setBounds(648, 185, 201, 26);
		pMain.add(lblTenKH);
		
		
		JLabel lblSubGioVao = new JLabel("Gi??? v??o: ");
		lblSubGioVao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioVao.setBounds(887, 185, 61, 26);
		pMain.add(lblSubGioVao);
		
		lblGioVao = new JLabel("");
		lblGioVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGioVao.setBounds(948, 185, 27, 26);
		pMain.add(lblGioVao);
		
		lblPhutVao = new JLabel("");
		lblPhutVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhutVao.setBounds(985, 185, 27, 26);
		pMain.add(lblPhutVao);
		
	
		
		JPanel pDichVu = new JPanel();
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "D???ch v??? ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(new Color(238,239,243,90));
		pDichVu.setBounds(24, 217, 281, 379);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		JLabel lblSubLMH = new JLabel("Lo???i m???t h??ng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(10, 27, 102, 26);
		pDichVu.add(lblSubLMH);
		
		cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(112, 25, 159, 30);
		cbbLoaiMH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		pDichVu.add(cbbLoaiMH);
		
		JLabel lblSubTenMH = new JLabel("T??n m???t h??ng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(10, 78, 102, 26);
		pDichVu.add(lblSubTenMH);
		
		cbbTenMH = new JComboBox<String>();
		cbbTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTenMH.setBackground(Color.WHITE);
		cbbTenMH.setBounds(112, 76, 159, 30);
		cbbTenMH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		pDichVu.add(cbbTenMH);
		
		JLabel lblSoluongMH = new JLabel("S??? l?????ng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(10, 130, 84, 26);
		pDichVu.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));;
		txtSoLuong.setBounds(112, 129, 159, 30);
		pDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		rdbtnGiamSL = new JRadioButton("Gi???m s??? l?????ng");
		rdbtnGiamSL.setBackground(new Color(228,210,239));
		rdbtnGiamSL.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnGiamSL.setBounds(112, 170, 147, 35);
		pDichVu.add(rdbtnGiamSL);
		
		btnThemMH = new FixButton("Th??m m???t h??ng");
		btnThemMH.setForeground(Color.black);
		btnThemMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		btnThemMH.setBackground(new Color(57, 210, 247));
		btnThemMH.setBounds(56, 231, 176, 33);
		
		Icon iconThemMH = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 20, new Color(255, 255 ,255));
		btnThemMH.setIcon(iconThemMH);
		
		pDichVu.add(btnThemMH);
		
		btnXoaMH = new FixButton("X??a");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		btnXoaMH.setBackground(new Color(0xE91940));
		btnXoaMH.setBounds(56, 278, 176, 33);
		
		Icon iconXoaMH = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 20, new Color(255, 255 ,255));
		btnXoaMH.setIcon(iconXoaMH);
		pDichVu.add(btnXoaMH);
		
		btnLamMoiMH = new FixButton("L??m m???i");
		btnLamMoiMH.setForeground(Color.WHITE);
		btnLamMoiMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiMH.setBackground(new Color(114, 23, 153));
		btnLamMoiMH.setBounds(56, 322, 176, 33);
		
		Icon iconLamMoiMH = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, new Color(255, 255 ,255));
		btnLamMoiMH.setIcon(iconLamMoiMH);
		
		pDichVu.add(btnLamMoiMH);
		
		String col [] = {"T??n m???t h??ng", "T??n lo???i", "S??? l?????ng", "????n gi??","T???ng ti???n"};
		modelMatHang = new DefaultTableModel(col,0);
		
		tbMatHang = new JTable(modelMatHang);
		tbMatHang.setShowHorizontalLines(true);
		tbMatHang.setShowGrid(true);
		tbMatHang.setBackground(Color.WHITE);
		tbMatHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTableHeader tbHeader = tbMatHang.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tbMatHang.setSelectionBackground(new Color(164, 44, 167,30));
		tbMatHang.setSelectionForeground(new Color(114, 23, 153));
		tbMatHang.setRowHeight(30);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		tbMatHang.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tbMatHang.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tbMatHang.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		JScrollPane spMatHang = new JScrollPane(tbMatHang);
		spMatHang.setBounds(315, 217, 697, 305);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		pMain.add(spMatHang);
		
		JLabel lblSubGiaPhong = new JLabel("Gi?? ph??ng: ");
		lblSubGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGiaPhong.setBounds(551, 533, 77, 26);
		
		pMain.add(lblSubGiaPhong);
		
		lblGiaPhong = new JLabel("");
		lblGiaPhong.setForeground(Color.RED);
		lblGiaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaPhong.setBounds(640, 533, 109, 26);
		pMain.add(lblGiaPhong);
		
		lblThoiGian = new JLabel("");
		lblThoiGian.setForeground(Color.RED);
		lblThoiGian.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblThoiGian.setBounds(836, 533, 176, 26);
		pMain.add(lblThoiGian);
		
		JLabel lblSubThoiGian = new JLabel("Th???i gian: ");
		lblSubThoiGian.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubThoiGian.setBounds(759, 533, 77, 26);
		pMain.add(lblSubThoiGian);
		
		JLabel lblpSubPhuThu = new JLabel("Ph??? thu: ");
		lblpSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblpSubPhuThu.setBounds(315, 533, 90, 26);
		pMain.add(lblpSubPhuThu);
		
		lblPhuThu = new JLabel("");
		lblPhuThu.setForeground(Color.RED);
		lblPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhuThu.setBounds(381, 533, 169, 26);
		pMain.add(lblPhuThu);
		
		JLabel lblSubThanhTien = new JLabel("Th??nh ti???n: ");
		lblSubThanhTien.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblSubThanhTien.setBounds(739, 570, 90, 26);
		pMain.add(lblSubThanhTien);
		
		lblThanhTien = new JLabel("");
		lblThanhTien.setForeground(Color.RED);
		lblThanhTien.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblThanhTien.setBounds(825, 569, 187, 26);
		pMain.add(lblThanhTien);
		
		JPanel pLine = new JPanel();
		pLine.setBackground(Color.BLACK);
		pLine.setBounds(736, 560, 276, 2);
		pMain.add(pLine);
		
		JPanel pThanhToan = new JPanel();
		pThanhToan.setBackground(new Color(238,239,243,90));
		pThanhToan.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Thanh to??n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pThanhToan.setBounds(1024, 217, 223, 379);
		pMain.add(pThanhToan);
		pThanhToan.setLayout(null);
		
		JLabel lblSubNhanVien = new JLabel("Nh??n vi??n l???p H??:");
		lblSubNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNhanVien.setBounds(10, 25, 161, 26);
		pThanhToan.add(lblSubNhanVien);
		
		lblNhanVienLap = new JLabel(sHeaderMaNV);
		lblNhanVienLap.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblNhanVienLap.setBounds(145, 25, 68, 26);
		pThanhToan.add(lblNhanVienLap);
		
		
		
		btnThanhToan = new FixButton("Thanh to??n");
		btnThanhToan.setForeground(Color.black);
		btnThanhToan.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnThanhToan.setBackground(new Color(57, 210, 247));   //new Color(114, 23, 153)   new Color(57, 210, 247)
		btnThanhToan.setBounds(25, 267, 176, 44);
		pThanhToan.add(btnThanhToan);
		
		Icon iconThanhToan = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 25, new Color(255, 255 ,255));
		btnThanhToan.setIcon(iconThanhToan);
		
		btnLamMoiHD = new FixButton("L??m m???i");
		btnLamMoiHD.setForeground(Color.WHITE);
		btnLamMoiHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiHD.setBackground(new Color(114, 23, 153));
		btnLamMoiHD.setBounds(25, 322, 176, 33);
		
		
		Icon iconLamMoiTT = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, new Color(255, 255 ,255));
		btnLamMoiHD.setIcon(iconLamMoiTT);
		
		
		pThanhToan.add(btnLamMoiHD);
		
		JLabel lblSubPhuThu = new JLabel("Ph??? thu: ");
		lblSubPhuThu.setBounds(10, 71, 61, 26);
		pThanhToan.add(lblSubPhuThu);
		lblSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		cbbPhuThu = new JComboBox<String>();
		cbbPhuThu.setBounds(74, 68, 126, 29);
		pThanhToan.add(cbbPhuThu);
		cbbPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhuThu.setBackground(Color.WHITE);
		cbbPhuThu.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
		JLabel lblSubGioRa = new JLabel("Gi??? ra: ");
		lblSubGioRa.setBounds(11, 122, 61, 26);
		pThanhToan.add(lblSubGioRa);
		lblSubGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		 cbbGioRa = new JComboBox<String>();
		cbbGioRa.setBounds(75, 116, 56, 29);
		pThanhToan.add(cbbGioRa);
		cbbGioRa.setBackground(Color.WHITE);
		cbbGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbGioRa.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
		JLabel blbSubAfterGioRa = new JLabel(":");
		blbSubAfterGioRa.setBounds(133, 125, 6, 14);
		pThanhToan.add(blbSubAfterGioRa);
		blbSubAfterGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		cbbPhutRa = new JComboBox<String>();
		cbbPhutRa.setBounds(140, 116, 60, 29);
		pThanhToan.add(cbbPhutRa);
		cbbPhutRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhutRa.setBackground(Color.WHITE);
		cbbPhutRa.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
//		btnLamMoiHD_1 = new FixButton("L??m m???i");
//		btnLamMoiHD_1.setBounds(24, 236, 176, 33);
//		pThanhToan.add(btnLamMoiHD_1);
//		
		btnInHoaDon = new FixButton("In h??a ????n");
		btnInHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnInHoaDon.setBackground(new Color(114, 23, 153));
		btnInHoaDon.setBounds(25, 223, 176, 33);
		
		Icon iconInHD = IconFontSwing.buildIcon(FontAwesome.PRINT, 20, new Color(255, 255 ,255));
		btnInHoaDon.setIcon(iconInHD);
		pThanhToan.add(btnInHoaDon);

		
		
		JLabel blbSubAfterGioRa1 = new JLabel(":");
		blbSubAfterGioRa1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blbSubAfterGioRa1.setBounds(971, 191, 12, 14);
		pMain.add(blbSubAfterGioRa1);
		
	
		
		lblThanhToanLoaiKH = new JLabel("");
		lblThanhToanLoaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblThanhToanLoaiKH.setBounds(315, 560, 90, 26);
		pMain.add(lblThanhToanLoaiKH);
		
		lblGiamGia = new JLabel("");
		lblGiamGia.setForeground(Color.RED);
		lblGiamGia.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiamGia.setBounds(401, 560, 149, 26);
		pMain.add(lblGiamGia);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1281, 606);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));	
		pMain.add(lblBackground);
		
//		Load cbb gio phut ra 
		for(int i=0 ; i <24;i++ ) {
			cbbGioRa.addItem(""+i);
		}
		
		for(int i =0; i<60; i++) {
			cbbPhutRa.addItem(""+i);
		}
	
		String sPhuThu [] = {"Kh??ng","Bu???i t???i","Ng??y l???","Cu???i tu???n"};
		for(int i =0; i< sPhuThu.length;i++) {
			cbbPhuThu.addItem(sPhuThu[i]);
		}
		
//		mouse click for table
	
		
//		Load t??n lo???i m???t h??ng
		ArrayList<LoaiMatHang> lsLoaiMH = daoLoaiMH.getAllLoaiMatHang();
		for(LoaiMatHang lmh : lsLoaiMH) {
			cbbLoaiMH.addItem(lmh.getTenLoaiMatHang());
		}
		
//		 Load ten mat hang mac dinh :
		String tenMH = (String) cbbLoaiMH.getSelectedItem();
		String maLoaiMatHang = daoLoaiMH.getMaLoaiMHTheoTen(tenMH);
		ArrayList<MatHang> lsMH = daoMatHang.getMatHangTheoMaLoai(maLoaiMatHang);
		cbbTenMH.removeAllItems();
		for(MatHang mh : lsMH) {
			cbbTenMH.addItem(mh.getTenMatHang());
		}
		
//		Load Phong dang hoat dong
		loadPhong();

//		?????nh d???ng ti???n: 
		dfTable = new DecimalFormat("###,###");
		df = new DecimalFormat("###,### VN??");
		
//		action 
		cbbLoaiMH.addItemListener(this);
		cbbTenMH.addItemListener(this);
		cbbGioRa.addItemListener(this);
		cbbPhutRa.addItemListener(this);
		cbbPhuThu.addItemListener(this);
		
		tbMatHang.addMouseListener(this);
		txtTim.addMouseListener(this);
		
		btnDSHD.addActionListener(this);
		btnLamMoiHD.addActionListener(this);
		btnThemMH.addActionListener(this);
		btnLamMoiMH.addActionListener(this);
		btnXoaMH.addActionListener(this);
		btnThanhToan.addActionListener(this);
		rdbtnGiamSL.addActionListener(this);
		btnTim.addActionListener(this);
		
		
		
	}
//	end main
	
//	Ph??ng
	public void loadPhong() {

	
		ArrayList<Phong> lsPhong = daoPhong.getPhongDangHoatDong();
		for(Phong p : lsPhong) {
			JPanel pn = new JPanel();
			LoaiPhong lp = daoLoaiPhong.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLoaiPhong());
			JButton btnPhong = new JButton(p.getMaPhong());
			pn.add(btnPhong);
			btnPhong.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object o = e.getSource();
					if(o.equals(btnPhong)) {
						resetAll();
						loadInfo(p);
					}
					
				}
			});
			
			btnPhong.setBackground(new Color(57, 210, 247));
			btnPhong.setPreferredSize(new Dimension(70,70));
			btnPhong.setBorder(new LineBorder(Color.white,10));
			
			JLabel lblTenPhong = new JLabel(lp.getTenLoaiPhong()+ " "+ p.getMaPhong());
			lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 15));
			pn.setBackground(new Color(248, 238, 248));
		
			pn.add(lblTenPhong);
			
			pPhong.add(pn);
		
		}
	}
	
	@SuppressWarnings("deprecation")
	public void loadInfo(Phong p) {
		lblMaPhong.setText(p.getMaPhong());
		DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(p.getMaPhong());
		
		KhachHang kh = daoKhachHang.getKHTheoMa(ddp.getKhachHang().getMaKhangHang());
		Time gioDen = ddp.getGioDen();
		lblMaKH.setText(kh.getMaKhangHang());
		lblTenKH.setText(" - "+kh.getTenKH());
		lblGioVao.setText(""+gioDen.getHours());
		lblPhutVao.setText(""+gioDen.getMinutes());
		loadTable(ddp);
	}
	
//	Table   
	
	public void loadTable(DonDatPhong ddp) {
		clearTable();
		ArrayList<CTDDP> lsCTDDP = daoCTDDP.getCTDDPTheoMaDDP(ddp.getMaDDP());
		for(CTDDP ctddp : lsCTDDP) {
			MatHang mh = daoMatHang.getMHTheoMaMH(ctddp.getMatHang().getMaMatHang());
			LoaiMatHang loaiMH = daoLoaiMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			double tongTien = mh.getGiaMatHang() * ctddp.getSoLuongMH();
			modelMatHang.addRow(new Object[] {
					mh.getTenMatHang(),loaiMH.getTenLoaiMatHang(),ctddp.getSoLuongMH(),dfTable.format(Math.round( mh.getGiaMatHang())),dfTable.format(Math.round(tongTien))
			});
		}
	}
	
	/**
	 * X??a to??n b??? c??c danh s??ch trong b???ng
	 */
	public void clearTable() {
		while (tbMatHang.getRowCount() > 0) {
			modelMatHang.removeRow(0);
		}
	}
	
	
	/**
	 * T??nh ti???n thu?? ph??ng, th???i gian h??t * gi?? ph??ng
	 * Th???i gian ???????c chuy???n sang ph??t tr?????c khi t??nh ti???n ph??ng
	 * @param giaPhong
	 * @return t???ng ti???n thu?? ph??ng
	 */
	public double tinhTienThue(double giaPhong) {
		int gioVao = Integer.parseInt(lblGioVao.getText()),
				phutVao = Integer.parseInt(lblPhutVao.getText());
			int gioRa = Integer.parseInt(cbbGioRa.getSelectedItem().toString()),
				phutRa = Integer.parseInt(cbbPhutRa.getSelectedItem().toString());
			
			int tongThoiGian = (gioRa*60 + phutRa) - (gioVao*60 + phutVao);
			double tongTienThuePhong = 0;
			if(tongThoiGian > 0) {
				if(tongThoiGian <= 60) {
					tongTienThuePhong = giaPhong;
					return tongTienThuePhong;
				}
				else {
					tongTienThuePhong = (tongThoiGian * giaPhong)/60;
					return tongTienThuePhong;
				}
			}
			
			 return -1;
	}
	
	/**
	 * T??nh t???ng ti???n chi ti???t h??a ????n c???a m???t h??a ????n
	 * @param tongTienThue
	 * @return t???ng ti???n trong CTHD
	 */
	public double tongTienCTHD(double tongTienThue) {
		double tong = tongTienThue;
		DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(lblMaPhong.getText());
		ArrayList<CTDDP> lsCTDDP = daoCTDDP.getCTDDPTheoMaDDP(ddp.getMaDDP());
		for(CTDDP ctddp : lsCTDDP) {
			MatHang mh = daoMatHang.getMHTheoMaMH(ctddp.getMatHang().getMaMatHang());
			tong += mh.getGiaMatHang() * ctddp.getSoLuongMH();
		}
		
		return tong;
	}

//	T??nh th??nh ti???n 
	public void loadThanhTien() {
		if(!lblMaPhong.getText().toString().equalsIgnoreCase("")) {
			Phong p = daoPhong.getPhongTheoMa(lblMaPhong.getText());
			double giaPhong =p.getGiaPhong();
			String phuThu = cbbPhuThu.getSelectedItem().toString();
			double giaPhuThu = 0;
			if(phuThu.equalsIgnoreCase("Bu???i t???i")) {
				giaPhuThu = 10000;
				
			}
			
			if(phuThu.equalsIgnoreCase("Ng??y l???")) {
				giaPhuThu = 30000;
				
			}
			if(phuThu.equalsIgnoreCase("Cu???i tu???n")) {
				giaPhuThu = 20000;
				
			}

			giaPhong = giaPhuThu + giaPhong;

			double tongTienThue = tinhTienThue(giaPhong);
	
			if(tongTienThue > 0) {
				int tongGioThue = (int) ((tongTienThue)/giaPhong);
				int tongPhutThue = (int) (((tongTienThue*60)/giaPhong) % 60);
				
				lblPhuThu.setText(phuThu+": "+ df.format(giaPhuThu));
				lblGiaPhong.setText(df.format(giaPhong));
				lblThoiGian.setText(tongGioThue+"h : "+tongPhutThue +"'  "+ df.format(tongTienThue));
				
				double thanhTien = tongTienCTHD(tongTienThue);
				
				KhachHang kh = daoKhachHang.getKHTheoMa(lblMaKH.getText());
				
				if(kh.getLoaiKH().getMaLoaiKH().equalsIgnoreCase("LKH002")) {
					lblThanhToanLoaiKH.setText("Th??nh vi??n: ");
					giamGia  = thanhTien* 0.03;
					lblGiamGia.setText("- "+df.format(giamGia));
					thanhTien = thanhTien - thanhTien*0.03;
				}
				else if(kh.getLoaiKH().getMaLoaiKH().equalsIgnoreCase("LKH003")) {
					lblThanhToanLoaiKH.setText("VIP: ");
					giamGia  = thanhTien* 0.1;
					lblGiamGia.setText("- "+df.format(giamGia));
					thanhTien = thanhTien - thanhTien*0.1;
				}
				
				lblThanhTien.setText(df.format(thanhTien));
			}
			else 
				JOptionPane.showMessageDialog(this, "Th???i gian ra kh??ng h???p l???!\nTh???i gian ra ph???i l???n h??n th???i gian v??o");
		}
		else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n ph??ng ????? thay ?????i th???i gian v?? ph??? thu ph?? h???p!");
	}
	
// 	X??? L?? M???T H??NG : m?? ????n ?????t ph??ng, maMH, s??? l?????ng
	public boolean kiemTraMatHangTrongBang(CTDDP ctddp, MatHang mh) {
		if(timRow() != -1) {
			daoCTDDP.suaSoluongMH(ctddp.getDonDatPhong().getMaDDP(), ctddp.getMatHang().getMaMatHang(), getSoLuongMH());
			modelMatHang.setValueAt(getSoLuongMH(),timRow(), 2);
			double giaMoi = mh.getGiaMatHang()* getSoLuongMH();
			modelMatHang.setValueAt(dfTable.format(giaMoi), timRow(), 4);
			
			
			return false;
		}
		return true;
		
	}
	
	public int getSoLuongMH() {
		int soLuong = 0;
		if(timRow() != -1) {
			soLuong = Integer.parseInt(modelMatHang.getValueAt(timRow(), 2).toString()) + Integer.parseInt(txtSoLuong.getText());
			return soLuong;
		}
		else return Integer.parseInt(txtSoLuong.getText()); 
	}
	
	public int timRow() {		// t??m row trong b???ng so s??nh v???i cbb
		
		for(int i =0; i< tbMatHang.getRowCount(); i++) {
			if(modelMatHang.getValueAt(i, 0).toString().equalsIgnoreCase(cbbTenMH.getSelectedItem().toString())&& modelMatHang.getValueAt(i, 1).toString().equalsIgnoreCase(cbbLoaiMH.getSelectedItem().toString()))
				return i;
		}
		return -1;
	}

	/**
	 * T??nh to??n gi???m s??? l?????ng m???t m???t h??ng trong b???ng
	 * @return Soluong
	 */
	public int giamSL() {
		int soLuong = 0;
		soLuong = Integer.parseInt(modelMatHang.getValueAt(timRow(), 2).toString()) - Integer.parseInt(txtSoLuong.getText());
		return soLuong;
		
	}
	/**
	 * T??m m???t h??ng trong b???ng v?? th???c hi???n gi???m s??? l?????ng
	 * N???u s??? l?????ng nh??? h??n ho???c b???ng 0 th?? th??ng b??o l???i v?? kh??ng th???c hi???n gi???m s??? l?????ng
	 * @param ctddp
	 */
	public void kiemTraGiamSL(CTDDP ctddp) {
		
		
		if(timRow() != -1) {
			int row = giamSL();
			if(row > 0) {
				daoCTDDP.suaSoluongMH(ctddp.getDonDatPhong().getMaDDP(), ctddp.getMatHang().getMaMatHang(), row);
				modelMatHang.setValueAt(giamSL(),timRow(), 2);
				double giaMoi = ctddp.getMatHang().getGiaMatHang()* ctddp.getSoLuongMH();
				modelMatHang.setValueAt(dfTable.format(giaMoi), timRow(), 4);
			}
			else {
				JOptionPane.showMessageDialog(this, "S??? l?????ng c???n gi???m ???? l???n h??n s??? l?????ng hi???n c?? trong ????n ?????t ph??ng!\nVui l??ng nh???p l???i s??? l?????ng");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
			}
		}
		else JOptionPane.showMessageDialog(this, "M???t h??ng c???n gi???m s??? l?????ng kh??ng ????ng!\nVui l??ng ch???n l???i");
	}
	
	
	/**
	 * Th??m m???t m???t h??ng v??o b???ng.
	 * N???u th??ng tin h???p l??? s??? t???o ?????i t?????ng chi ti???t h??a ????n v?? ki???m tra n??t gi???m s??? l?????ng c?? ???????c ch???n kh??ng
	 * N???u ???????c ch???n s??? th???c hi???n g???i ph????ng th???c gi???m s??? l?????ng.
	 * N???u kh??ng ???????c ch???n s??? ki???m tra trong b???ng c?? m???t h??ng ch??a, n???u c?? th?? c???p nh???t s??? l?????ng, kh??ng th?? s??? ???????c th??m m???i
	 */
	
	// nhap sl -> ktSLton -> sl <= slTon --> updateslTon --> themcthd
	public void themMHVaoCTDDP() {
			if(lblMaPhong.getText() != "") {
				if(regex.regexSoLuong(txtSoLuong)) {
					DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(lblMaPhong.getText());
					
					String tenMH = cbbTenMH.getSelectedItem().toString();
					String loaiMH = cbbLoaiMH.getSelectedItem().toString();
					MatHang mh = daoMatHang.getMHTheoTenMHVaLoaiMH(tenMH, loaiMH);
					int soLuongMH = Integer.parseInt(txtSoLuong.getText());
					int soLuongTon = mh.getSoLuongMatHang();
			//		if(soLuongMH <= mh.getSoLuongMatHang()) {
						CTDDP ctddp = new CTDDP(ddp, soLuongMH, mh);
						if(rdbtnGiamSL.isSelected()) {
							kiemTraGiamSL(ctddp);
							mh.setSoLuongMatHang(soLuongTon+soLuongMH);
							daoMatHang.updateMH(mh);
							
						}
						else if(soLuongMH <= soLuongTon) {
								if(kiemTraMatHangTrongBang(ctddp,mh)) {
									daoCTDDP.themCTDDP(ctddp);
									double tongTien = mh.getGiaMatHang() * soLuongMH;
									modelMatHang.addRow(new Object[] {
										tenMH,loaiMH,ctddp.getSoLuongMH(),dfTable.format(Math.round( mh.getGiaMatHang())),dfTable.format(Math.round(tongTien))
									});
								}
								
								mh.setSoLuongMatHang(soLuongTon-soLuongMH);
								daoMatHang.updateMH(mh);
						}
						else JOptionPane.showMessageDialog(this, "S??? l?????ng m???t h??ng t???n kh??ng ????? cung c???p!\nVui l??ng nh???p l???i s??? l?????ng.");

				}
			}
			else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n ph??ng sau ???? nh???p s??? l?????ng tr?????c khi th??m m???t h??ng!");
		}

	/**
	 * X??a 1 chi ti???t h??a ????n sau ???? c??c m???c s??? ???????c l??m m???i 
	 */
	public void xoaCTDDP() {
		if(!lblMaPhong.getText().equalsIgnoreCase("")) {
				if(timRow() != -1) {
				DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(lblMaPhong.getText());
				
				String tenMH = cbbTenMH.getSelectedItem().toString();
				String loaiMH = cbbLoaiMH.getSelectedItem().toString();
				MatHang mh = daoMatHang.getMHTheoTenMHVaLoaiMH(tenMH, loaiMH);
				daoCTDDP.xoaCTDDP(ddp.getMaDDP(),mh.getMaMatHang());
				
				int soLuongMH = Integer.parseInt(modelMatHang.getValueAt(timRow(), 2).toString());
				int soLuongTon = mh.getSoLuongMatHang();
				
				mh.setSoLuongMatHang(soLuongTon+soLuongMH);
				daoMatHang.updateMH(mh);
				loadTable(ddp);
				resetDichVu();
			}
			else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n m???t h??ng c???n x??a");
		}
		else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n ph??ng v?? ch???n m???t h??ng c???n x??a");
		
	}

	/**
	 * C???p nh???t l???i th??ng tin ??i???m t??ch l??y v?? lo???i kh??ch h??ng. 
	 * N???u ??i???m t??ch l??y l???n h??n 20 th?? c???p nh???t l???i th??ng tin lo???i kh??ch h??ng.
	 * @param kh
	 * @return KhachHang
	 */
	public KhachHang capNhatKHThanhToan(KhachHang kh) {
		if(!kh.getCccd().equalsIgnoreCase("")) {
			int diemTichLuy = kh.getDiemTichLuy() + 1;
			if(diemTichLuy >= 20) {
				kh.setLoaiKH(new LoaiKH("LKH003"));
				JOptionPane.showMessageDialog(this,"Kh??ch h??ng ???? ???????c n??ng l??n th??nh kh??ch h??ng VIP");
			}
			kh.setDiemTichLuy(diemTichLuy);

			
		}
		return kh;
	}
	

	
	public void themCTHD(HoaDon hd) {
		int row = tbMatHang.getRowCount();
		for(int i =0; i< row; i++) {
			String tenMH = modelMatHang.getValueAt(i, 0).toString();
			String loaiMH = modelMatHang.getValueAt(i, 1).toString();
			int soLuong = Integer.parseInt(modelMatHang.getValueAt(i, 2).toString());
			MatHang	mh = daoMatHang.getMHTheoTenMHVaLoaiMH(tenMH, loaiMH);
			daoCTHD.themCTHD(new CTHD(soLuong, mh, hd));
		}
		
	}
	
	/**
	 * Gi??p th??m m???t h??a ????n m???i.
	 * S??? ki???m tra th??ng tin ?????y ????? tr?????c khi th???c hi???n l??u th??ng tin l??n database.
	 * Sau khi th??m h??a ????n th??nh c??ng s??? c???p nh???t l???i tr???ng th??i ph??ng, ??i???m t??ch l??y n???u l?? th??nh vi??n
	 */
	public void themHD() {
		int optThanhToan = JOptionPane.showConfirmDialog(this, "B???n c?? ch???n ch???n mu???n thanh to??n kh??ng?", "Th??ng b??o", JOptionPane.YES_NO_OPTION );
		
		if(optThanhToan == JOptionPane.YES_OPTION) {
			if(lblMaPhong.getText().toString() !="") {
				if(!lblThanhTien.getText().equalsIgnoreCase("")) {
					String maHD = daoMa.getMaHD();
					Phong p = daoPhong.getPhongTheoMa(lblMaPhong.getText());
					KhachHang kh = daoKhachHang.getKHTheoMa(lblMaKH.getText());
					NhanVien nv = daoNhanVien.getNVTheoMa(sHeaderMaNV);
					Date ngayLap = dNgayHienTai;
		
					int gioVao = Integer.parseInt(lblGioVao.getText()),
						phutVao = Integer.parseInt(lblPhutVao.getText());
					int gioRa = Integer.parseInt(cbbGioRa.getSelectedItem().toString()),
							phutRa = Integer.parseInt(cbbPhutRa.getSelectedItem().toString());
					String phuThu = cbbPhuThu.getSelectedItem().toString();
					String trangThaiHD = "???? thanh to??n";
					double giamGiaThanhToan = this.giamGia;
						
					
					@SuppressWarnings("deprecation")
					HoaDon hd = new HoaDon(maHD, ngayLap, new Time(gioVao, phutVao, 0), new Time(gioRa, phutRa, 0), phuThu, trangThaiHD,giamGiaThanhToan, nv, kh, p);
					daoHD.themHoaDon(hd);
					themCTHD(hd);
					
					daoKhachHang.suaThongTinKhachHang(capNhatKHThanhToan(kh));
					daoPhong.capnhatTrangThaiPhong(p.getMaPhong(), "Tr???ng");
					resetAll();
				}
				else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n th???i gian h???p l??? tr?????c khi thanh to??n!");
			}
			else JOptionPane.showMessageDialog(this, "Vui l??ng ch???n ph??ng tr?????c khi thanh to??n!");
		}
		
	}
	

	
	/**
	 * gi??p t??m ki???m th??ng tin ph??ng theo m?? ph??ng ??ang ho???t ?????ng ????? thanh to??n
	 */
	public void timKiem() {
		if(regex.regexTimKiemMaPhong(txtTim)) {
			Phong p1 = daoPhong.getPhongDangHoatDongTheoMaP(txtTim.getText().toString());
			if(p1!=null)
				loadInfo(p1);
			else 
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y ph??ng ??ang ho???t ?????ng n??o nh?? y??u c???u!");
		}
	}
	
 
	/**
	 * gi??p l??m m???i l???i c??c m???c c???a d???ch v??? v??? m???c ?????nh
	 */
	public void resetDichVu() {
		cbbLoaiMH.setSelectedIndex(0);
		cbbTenMH.setSelectedIndex(0);
		txtSoLuong.setText("");
		rdbtnGiamSL.setSelected(false);
		
	}
	
	
	/**
	 * ResetAll gi??p l??m m???i l???i to??n b??? giao di???n c???a thanh to??n ngo???i
	 */
	public void resetAll() {
		resetDichVu();
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 14));
		txtTim.setText("T??m ph??ng ??ang thu?? theo m?? ph??ng.");
		txtTim.setForeground(Color.lightGray);

		pPhong.removeAll();
		pPhong.revalidate();
		pPhong.repaint();
		loadPhong();
		lblMaPhong.setText("");
		lblMaKH.setText("");
		lblTenKH.setText("");
		lblGioVao.setText("");
		lblPhutVao.setText("");
		clearTable();
		lblGiaPhong.setText("");
		lblPhuThu.setText("");
		lblThoiGian.setText("");
		lblThanhTien.setText("");
		cbbGioRa.setSelectedIndex(0);
		cbbPhutRa.setSelectedIndex(0);
		cbbPhuThu.setSelectedIndex(0);
		
		lblGiamGia.setText("");
		lblThanhToanLoaiKH.setText("");
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDSHD)) {
			FrmDanhSachHoaDon frmDanhSachHoaDon = new FrmDanhSachHoaDon(frm);
			frmDanhSachHoaDon.setVisible(true);
			frm.setVisible(false);
		}
		if(o.equals(btnLamMoiMH)) {
			resetDichVu();
			
			
		}
		if(o.equals(btnLamMoiHD)) {
			resetAll();
		}
		if(rdbtnGiamSL.isSelected()) {
			btnThemMH.setText("Gi???m m???t h??ng");
		} else btnThemMH.setText("Th??m m???t h??ng");
		if(o.equals(btnThemMH)) {
			themMHVaoCTDDP();
		}
		if(o.equals(btnXoaMH)) {
			xoaCTDDP();
		}
		if(o.equals(btnThanhToan)) {
			themHD();
		}
		if(o.equals(btnTim)) {
			timKiem();
		
		}
		
		
	}

	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getItem();
		if(o == cbbLoaiMH.getSelectedItem()) {
			/*
			 * N???u cbb lo???i m???t h??ng h??ng thay ?????i, th?? cbb t??n m???t h??ng s??? ???????c hi???n th??? danh s??ch l??n 
			 */
			String tenMH = (String) cbbLoaiMH.getSelectedItem();
			String maLoaiMatHang = daoLoaiMH.getMaLoaiMHTheoTen(tenMH);
			ArrayList<MatHang> lsMH = daoMatHang.getMatHangTheoMaLoai(maLoaiMatHang);
			cbbTenMH.removeAllItems();
			for(MatHang mh : lsMH) {
				cbbTenMH.addItem(mh.getTenMatHang());
			}
		}
		if(o == cbbGioRa.getSelectedItem()|| o== cbbPhutRa.getSelectedItem() || o == cbbPhuThu.getSelectedItem()) {
			/*
			 * N???u gi??? ra != 0 ho???c ph??t ra  != 0 th?? s??? load th??nh ti???n
			 */
			if(!cbbGioRa.getSelectedItem().toString().equalsIgnoreCase("0")||!cbbPhutRa.getSelectedItem().toString().equalsIgnoreCase("0"))
				loadThanhTien();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Object o = e.getSource();
		
		if(o.equals(tbMatHang)) {
			
		int row = tbMatHang.getSelectedRow();
		cbbTenMH.setSelectedItem(modelMatHang.getValueAt(row,0).toString());
		cbbLoaiMH.setSelectedItem(modelMatHang.getValueAt(row,1).toString());
		txtSoLuong.setText(modelMatHang.getValueAt(row,2).toString());
		}
		if(o.equals(txtTim)) {
			/*
			 *thay ?????i l???i font c???a txt t??m ki???m
			 */
			txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtTim.setText("");
			txtTim.setForeground(Color.black);
			
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
}
