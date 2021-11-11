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
import java.util.ArrayList;

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
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiMatHang;
import entity.LoaiPhong;
import entity.MatHang;
import entity.NhanVien;
import entity.Phong;

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
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lbbTitle = new JLabel("Quản lý thanh toán");
		lbbTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
		lbbTitle.setBounds(24, 10, 268, 33);
		pMain.add(lbbTitle);
		
		JLabel lblSubTimKiem = new JLabel("Tìm kiếm:");
		lblSubTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSubTimKiem.setBounds(374, 13, 90, 35);
		pMain.add(lblSubTimKiem);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTim.setBounds(474, 12, 281, 33);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));

		pMain.add(txtTim);
		txtTim.setColumns(10);
		

		
		
		btnTim = new FixButton("Tìm"); 
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(786, 11, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		
		pMain.add(btnTim);
		
		
		
		
		btnDSHD = new FixButton("Xem danh sách hóa đơn");
		
		btnTim.setBackground(new Color(114, 23 ,153));
		btnDSHD.setForeground(Color.WHITE);
		btnDSHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDSHD.setBackground(new Color(114, 23, 153));
		btnDSHD.setBounds(1015, 13, 232, 33);
		
		Image imgListHD = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconList.png");
		Image resizeImgListHD = imgListHD.getScaledInstance(25, 25, 0);
		btnDSHD.setIcon(new ImageIcon(resizeImgListHD));
		
		pMain.add(btnDSHD);
	
		
		JLabel lblHeaderPhong = new JLabel("Phòng đang hát");
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
		
		JLabel lblSubPhong = new JLabel("Phòng : ");
		lblSubPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhong.setBounds(315, 185, 56, 26);
		pMain.add(lblSubPhong);
		
		lblMaPhong = new JLabel("");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaPhong.setBounds(370, 185, 71, 26);
		pMain.add(lblMaPhong);
		
		JLabel lblSubTenKH = new JLabel("Khách hàng: ");
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
		
		
		JLabel lblSubGioVao = new JLabel("Giờ vào: ");
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
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Dịch vụ ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(new Color(238,239,243,90));
		pDichVu.setBounds(24, 217, 281, 305);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(10, 27, 102, 26);
		pDichVu.add(lblSubLMH);
		
		cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(112, 25, 159, 30);
		cbbLoaiMH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		pDichVu.add(cbbLoaiMH);
		
		JLabel lblSubTenMH = new JLabel("Tên mặt hàng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(10, 68, 102, 26);
		pDichVu.add(lblSubTenMH);
		
		cbbTenMH = new JComboBox<String>();
		cbbTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTenMH.setBackground(Color.WHITE);
		cbbTenMH.setBounds(112, 66, 159, 30);
		cbbTenMH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		pDichVu.add(cbbTenMH);
		
		JLabel lblSoluongMH = new JLabel("Số lượng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(10, 106, 84, 26);
		pDichVu.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));;
		txtSoLuong.setBounds(112, 105, 159, 30);
		pDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		rdbtnGiamSL = new JRadioButton("Giảm số lượng");
		rdbtnGiamSL.setBackground(new Color(228,210,239));
		rdbtnGiamSL.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnGiamSL.setBounds(69, 147, 147, 35);
		pDichVu.add(rdbtnGiamSL);
		
		btnThemMH = new FixButton("Thêm mặt hàng");
		btnThemMH.setForeground(Color.black);
		btnThemMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		 //new Color(57, 210, 247)
		btnThemMH.setBackground(new Color(57, 210, 247));
		btnThemMH.setBounds(55, 189, 176, 33);
		
		Image imgThemMH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconGrayThem.png");
		Image resizeImgThemMH = imgThemMH.getScaledInstance(25, 25, 0);
		btnThemMH.setIcon(new ImageIcon(resizeImgThemMH));
		
		pDichVu.add(btnThemMH);
		
		btnXoaMH = new FixButton("Xóa");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		btnXoaMH.setBackground(new Color(114, 23, 153));
		btnXoaMH.setBounds(10, 244, 114, 33);
		
		Image imgXoaMH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconRemove.png");
		Image resizeImgXoaMH = imgXoaMH.getScaledInstance(25, 25, 0);
		btnXoaMH.setIcon(new ImageIcon(resizeImgXoaMH));
		pDichVu.add(btnXoaMH);
		
		btnLamMoiMH = new FixButton("Làm mới");
		btnLamMoiMH.setForeground(Color.WHITE);
		btnLamMoiMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiMH.setBackground(new Color(114, 23, 153));
		btnLamMoiMH.setBounds(134, 244, 137, 33);
		
		Image imgLamMoiMH = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconReset.png");
		Image resizeImgLamMoiMH = imgLamMoiMH.getScaledInstance(25, 25, 0);
		btnLamMoiMH.setIcon(new ImageIcon(resizeImgLamMoiMH));
		
		pDichVu.add(btnLamMoiMH);
		
		String col [] = {"Tên mặt hàng", "Tên loại", "Số lượng", "Đơn giá","Tổng tiền"};
		modelMatHang = new DefaultTableModel(col,0);
		
		tbMatHang = new JTable(modelMatHang);
		tbMatHang.setShowHorizontalLines(true);
		tbMatHang.setShowGrid(true);
		tbMatHang.setBackground(Color.WHITE);
		tbMatHang.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
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
		
		JLabel lblSubGiaPhong = new JLabel("Giá phòng: ");
		lblSubGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGiaPhong.setBounds(325, 533, 77, 26);
		
		pMain.add(lblSubGiaPhong);
		
		lblGiaPhong = new JLabel("");
		lblGiaPhong.setForeground(Color.RED);
		lblGiaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaPhong.setBounds(401, 533, 109, 26);
		pMain.add(lblGiaPhong);
		
		lblThoiGian = new JLabel("");
		lblThoiGian.setForeground(Color.RED);
		lblThoiGian.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblThoiGian.setBounds(604, 533, 214, 26);
		pMain.add(lblThoiGian);
		
		JLabel lblSubThoiGian = new JLabel("Thời gian: ");
		lblSubThoiGian.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubThoiGian.setBounds(532, 533, 77, 26);
		pMain.add(lblSubThoiGian);
		
		JLabel lblpSubPhuThu = new JLabel("Phụ thu: ");
		lblpSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblpSubPhuThu.setBounds(836, 533, 61, 26);
		pMain.add(lblpSubPhuThu);
		
		lblPhuThu = new JLabel("");
		lblPhuThu.setForeground(Color.RED);
		lblPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhuThu.setBounds(909, 533, 90, 26);
		pMain.add(lblPhuThu);
		
		JLabel lblSubThanhTien = new JLabel("Thành tiền: ");
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
		pThanhToan.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Thanh toán", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pThanhToan.setBounds(1024, 217, 223, 305);
		pMain.add(pThanhToan);
		pThanhToan.setLayout(null);
		
		JLabel lblSubNhanVien = new JLabel("Nhân viên lập HĐ:");
		lblSubNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNhanVien.setBounds(10, 21, 161, 26);
		pThanhToan.add(lblSubNhanVien);
		
		lblNhanVienLap = new JLabel(sHeaderMaNV);
		lblNhanVienLap.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblNhanVienLap.setBounds(145, 21, 68, 26);
		pThanhToan.add(lblNhanVienLap);
		
		
		
		btnThanhToan = new FixButton("Thanh toán");
		btnThanhToan.setForeground(Color.black);
		btnThanhToan.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnThanhToan.setBackground(new Color(57, 210, 247));   //new Color(114, 23, 153)   new Color(57, 210, 247)
		btnThanhToan.setBounds(24, 179, 176, 53);
		pThanhToan.add(btnThanhToan);
		
		Image imgThanhToan = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconGrayThem.png");
		Image resizeImgThanhToan = imgThanhToan.getScaledInstance(30, 30, 0);
		btnThanhToan.setIcon(new ImageIcon(resizeImgThanhToan));
		
		btnLamMoiHD = new FixButton("Làm mới");
		btnLamMoiHD.setForeground(Color.WHITE);
		btnLamMoiHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiHD.setBackground(new Color(114, 23, 153));
		btnLamMoiHD.setBounds(34, 243, 151, 33);
		
		Image imgLamMoiHD = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconReset.png");
		Image resizeImgLamMoiHD = imgLamMoiHD.getScaledInstance(25, 25, 0);
		btnLamMoiHD.setIcon(new ImageIcon(resizeImgLamMoiHD));
		
		
		pThanhToan.add(btnLamMoiHD);
		
		JLabel lblSubPhuThu = new JLabel("Phụ thu: ");
		lblSubPhuThu.setBounds(10, 61, 61, 26);
		pThanhToan.add(lblSubPhuThu);
		lblSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		cbbPhuThu = new JComboBox<String>();
		cbbPhuThu.setBounds(74, 58, 126, 29);
		pThanhToan.add(cbbPhuThu);
		cbbPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhuThu.setBackground(Color.WHITE);
		cbbPhuThu.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
		JLabel lblSubGioRa = new JLabel("Giờ ra: ");
		lblSubGioRa.setBounds(10, 104, 61, 26);
		pThanhToan.add(lblSubGioRa);
		lblSubGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		 cbbGioRa = new JComboBox<String>();
		cbbGioRa.setBounds(74, 98, 56, 29);
		pThanhToan.add(cbbGioRa);
		cbbGioRa.setBackground(Color.WHITE);
		cbbGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbGioRa.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
		JLabel blbSubAfterGioRa = new JLabel(":");
		blbSubAfterGioRa.setBounds(132, 107, 6, 14);
		pThanhToan.add(blbSubAfterGioRa);
		blbSubAfterGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		cbbPhutRa = new JComboBox<String>();
		cbbPhutRa.setBounds(139, 98, 60, 29);
		pThanhToan.add(cbbPhutRa);
		cbbPhutRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhutRa.setBackground(Color.WHITE);
		cbbPhutRa.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1281, 606);
//		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));
		
		
		JLabel blbSubAfterGioRa_1 = new JLabel(":");
		blbSubAfterGioRa_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blbSubAfterGioRa_1.setBounds(971, 191, 12, 14);
		pMain.add(blbSubAfterGioRa_1);
		
		pMain.add(lblBackground);
		
//		Load cbb gio phut ra 
		for(int i=0 ; i <24;i++ ) {
			cbbGioRa.addItem(""+i);
		}
		
		for(int i =0; i<60; i++) {
			cbbPhutRa.addItem(""+i);
		}
	
		String sPhuThu [] = {"Không","Buổi tối","Ngày lễ","Cuối tuần"};
		for(int i =0; i< sPhuThu.length;i++) {
			cbbPhuThu.addItem(sPhuThu[i]);
		}
		
//		mouse click for table
	
		
//		Load tên loại mặt hàng
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

//		Định dạng tiền: 
		dfTable = new DecimalFormat("###,###");
		df = new DecimalFormat("###,### VNĐ");
		
//		action 
		cbbLoaiMH.addItemListener(this);
		cbbTenMH.addItemListener(this);
		cbbGioRa.addItemListener(this);
		cbbPhutRa.addItemListener(this);
		cbbPhuThu.addItemListener(this);
		
		tbMatHang.addMouseListener(this);
		
		
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
	
//	Phòng
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
	
	public void clearTable() {
		while (tbMatHang.getRowCount() > 0) {
			modelMatHang.removeRow(0);
		}
	}
	
	
	
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
	
	public double tongThanhTien(double tienThuePhong) {
		double tong = tienThuePhong;
		
		for(int i=0;i<tbMatHang.getRowCount();i++) {
			tong  += Double.parseDouble(modelMatHang.getValueAt(i, 4).toString());
		}
		
		return tong;
	}

//	Tính thành tiền 
	public void loadThanhTien() {
		if(!lblMaPhong.getText().toString().equalsIgnoreCase("")) {
			Phong p = daoPhong.getPhongTheoMa(lblMaPhong.getText());
			double giaPhong =p.getGiaPhong();
			String phuThu = cbbPhuThu.getSelectedItem().toString();
			if(phuThu.equalsIgnoreCase("Buổi tối")) {
				giaPhong = p.getGiaPhong() + 20000;
			}
			if(phuThu.equalsIgnoreCase("Ngày lễ")) {
				giaPhong = p.getGiaPhong() + 50000;
			}
			if(phuThu.equalsIgnoreCase("Cuối tuần")) {
				giaPhong = p.getGiaPhong() + 30000;
			}
			lblPhuThu.setText(phuThu);
			
			lblGiaPhong.setText(df.format(giaPhong));
			
			double tongTienThue = tinhTienThue(giaPhong);
			
			if(tongTienThue > 0) {
				int tongGioThue = (int) ((tongTienThue)/giaPhong);
				int tongPhutThue = (int) (((tongTienThue*60)/giaPhong) % 60);
				lblThoiGian.setText(tongGioThue+"h : "+tongPhutThue +"'  "+ df.format(tongTienThue));
				
				lblThanhTien.setText(df.format(tongTienThue));
			}
			else 
				JOptionPane.showMessageDialog(this, "Thời gian ra không hợp lệ!\nThời gian ra phải lớn hơn thời gian vào");
		}
		else JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng để thay đổi thời gian và phụ thu phù hợp!");
	}
	
// 	XỬ LÝ MẶT HÀNG : mã đơn đặt phòng, maMH, số lượng
	public boolean kiemTraMatHangTrongBang(CTDDP ctddp) {
		if(timRow() != -1) {
			daoCTDDP.suaSoluongMH(ctddp.getDonDatPhong().getMaDDP(), ctddp.getMatHang().getMaMatHang(), getSoLuongMH());
			return false;
		}
		else return true;
		
	}
	
	public int getSoLuongMH() {
		int soLuong = 0;
		if(timRow() != -1) {
			soLuong = Integer.parseInt(modelMatHang.getValueAt(timRow(), 2).toString()) + Integer.parseInt(txtSoLuong.getText());
			return soLuong;
		}
		else return Integer.parseInt(txtSoLuong.getText()); 
	}
	
	public int timRow() {		// tìm row trong bảng so sánh với cbb
		
		for(int i =0; i< tbMatHang.getRowCount(); i++) {
			if(modelMatHang.getValueAt(i, 0).toString().equalsIgnoreCase(cbbTenMH.getSelectedItem().toString())&&modelMatHang.getValueAt(i, 1).toString().equalsIgnoreCase(cbbLoaiMH.getSelectedItem().toString()))
				return i;
		}
		return -1;
	}
// Giam so luong
	public int giamSL() {
		int soLuong = 0;
		soLuong = Integer.parseInt(modelMatHang.getValueAt(timRow(), 2).toString()) - Integer.parseInt(txtSoLuong.getText());
		return soLuong;
		
	}
	public void kiemTraGiamSL(CTDDP ctddp) {
		
		
		if(timRow() != -1) {
			int row = giamSL();
			if(row > 0) {
				daoCTDDP.suaSoluongMH(ctddp.getDonDatPhong().getMaDDP(), ctddp.getMatHang().getMaMatHang(), row);
			}
			else {
				JOptionPane.showMessageDialog(this, "Số lượng cần giảm đã lớn hơn số lượng hiện có trong đơn đặt phòng!\nVui lòng nhập lại số lượng");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
			}
		}
		else JOptionPane.showMessageDialog(this, "Mặt hàng cần giảm số lượng không đúng!\nVui lòng chọn lại");
	}
	
	
//	Them
	public void themMHVaoCTDDP() {
			if(lblMaPhong.getText() != "") {
				if(regex.regexSoLuong(txtSoLuong)) {
					DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(lblMaPhong.getText());
					
					String tenMH = cbbTenMH.getSelectedItem().toString();
					String loaiMH = cbbLoaiMH.getSelectedItem().toString();
					MatHang mh = daoMatHang.getMHTheoTenMHVaLoaiMH(tenMH, loaiMH);
					int soLuongMH = Integer.parseInt(txtSoLuong.getText());
					CTDDP ctddp = new CTDDP(ddp, soLuongMH, mh);
					if(rdbtnGiamSL.isSelected()) {
						kiemTraGiamSL(ctddp);
					}
					else if(kiemTraMatHangTrongBang(ctddp))
							daoCTDDP.themCTDDP(ctddp);
					loadTable(ddp);
				}
			}
			else JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng sau đó nhập số lượng trước khi thêm mặt hàng!");
		}
//	Xoa
	public void xoaCTDDP() {
		DonDatPhong ddp = daoDDP.getDDPTheoMaPhong(lblMaPhong.getText());
		
		String tenMH = cbbTenMH.getSelectedItem().toString();
		String loaiMH = cbbLoaiMH.getSelectedItem().toString();
		MatHang mh = daoMatHang.getMHTheoTenMHVaLoaiMH(tenMH, loaiMH);
		daoCTDDP.xoaCTDDP(ddp.getMaDDP(),mh.getMaMatHang());
		loadTable(ddp);
		resetDichVu();
	}
//	Thanh toán
//	themHD
	public void themHD() {
		int optThanhToan = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thanh toán không?", "Thông báo", JOptionPane.YES_NO_OPTION );
		
		if(optThanhToan == JOptionPane.YES_OPTION) {
			if(lblMaPhong.getText().toString() !="") {
				String maHD = daoMa.getMaHD();
				Phong p = daoPhong.getPhongTheoMa(lblMaPhong.getText());
				KhachHang kh = daoKhachHang.getKHTheoMa(lblMaKH.getText());
				NhanVien nv = daoNhanVien.getMaVaSDTNV(sHeaderMaNV);
				Date ngayLap = dNgayHienTai;
	
				int gioVao = Integer.parseInt(lblGioVao.getText()),
					phutVao = Integer.parseInt(lblPhutVao.getText());
				int gioRa = Integer.parseInt(cbbGioRa.getSelectedItem().toString()),
						phutRa = Integer.parseInt(cbbPhutRa.getSelectedItem().toString());
				String phuThu = cbbPhuThu.getSelectedItem().toString();
				String trangThaiHD = "Đã thanh toán";
				
				@SuppressWarnings("deprecation")
				HoaDon hd = new HoaDon(maHD, ngayLap, new Time(gioVao, phutVao, 0), new Time(gioRa, phutRa, 0), phuThu, trangThaiHD, nv, kh, p);
				daoHD.themHoaDon(hd);
				daoPhong.capnhatTrangThaiPhong(p.getMaPhong(), "Trống");
				resetAll();
			
			}
			else JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng trước khi thanh toán!");
		}
		
	}
	
//	tim kiem
	public void timKiem() {
		if(regex.regexTimKiemMaPhong(txtTim)) {
			Phong p1 = daoPhong.getPhongDangHoatDongTheoMaP(txtTim.getText().toString());
			if(p1!=null)
				loadInfo(p1);
			else 
				JOptionPane.showMessageDialog(this, "Không tìm thấy phòng đang hoạt động nào như yêu cầu!");
		}
	}
	
	

	
//	Làm mới 
	public void resetDichVu() {
		cbbLoaiMH.setSelectedIndex(0);
		cbbTenMH.setSelectedIndex(0);
		txtSoLuong.setText("");
	}
	
	public void resetAll() {
		resetDichVu();
		txtTim.setText("");
		pPhong.removeAll();
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
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDSHD)) {
			FrmDanhSachHoaDon frm_DanhSachHoaDon = new FrmDanhSachHoaDon(frm);
			frm_DanhSachHoaDon.setVisible(true);
			frm.setVisible(false);
		}
		if(o.equals(btnLamMoiMH)) {
			resetDichVu();
		}
		if(o.equals(btnLamMoiHD)) {
			resetAll();
		}
		if(rdbtnGiamSL.isSelected()) {
			btnThemMH.setText("Giảm mặt hàng");
		} else btnThemMH.setText("Thêm mặt hàng");
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
			String tenMH = (String) cbbLoaiMH.getSelectedItem();
			String maLoaiMatHang = daoLoaiMH.getMaLoaiMHTheoTen(tenMH);
			ArrayList<MatHang> lsMH = daoMatHang.getMatHangTheoMaLoai(maLoaiMatHang);
			cbbTenMH.removeAllItems();
			for(MatHang mh : lsMH) {
				cbbTenMH.addItem(mh.getTenMatHang());
			}
		}
		if(o == cbbGioRa.getSelectedItem()|| o== cbbPhutRa.getSelectedItem() || o == cbbPhuThu.getSelectedItem()) {
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
