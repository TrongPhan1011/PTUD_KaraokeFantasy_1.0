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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.ConnectDB;
import dao.DAOLoaiMH;
import dao.DAOMatHang;
import entity.LoaiMatHang;
import entity.MatHang;

public class Frm_QLBH extends JPanel implements ActionListener, MouseListener,ItemListener {

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
	private JButton btnDSHD;
	private DAOLoaiMH daoLoaiMH;
	private JComboBox<String> cbbTenMH;
	private JComboBox<String> cbbLoaiMH;
	private DAOMatHang daoMatHang;
	
	public Panel getFrmQLBH() {
		return this.pMain;
	}
	
	public Frm_QLBH(JFrame frm,String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai)  {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		this.frm = frm;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lbbTitle = new JLabel("Quản lý bán hàng");
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
		
		btnDSHD = new JButton("Xem danh sách hóa đơn");
		btnDSHD.setForeground(Color.WHITE);
		btnDSHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDSHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnDSHD.setBackground(new Color(114, 23, 153));
		btnDSHD.setBounds(1043, 13, 213, 33);
		pMain.add(btnDSHD);
		btnDSHD.addActionListener(this);
		
		JLabel lblHeaderPhong = new JLabel("Phòng");
		lblHeaderPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
	
		lblHeaderPhong.setBounds(575, 51, 71, 26);
		pMain.add(lblHeaderPhong);
		
		JPanel pPhong = new JPanel();
		
		pPhong.setBackground(Color.white);		//new Color(164, 44, 167,20)

		
		JScrollPane scrollPane = new JScrollPane(pPhong);
		scrollPane.setViewportView(pPhong);
		scrollPane.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pPhong.setLayout(new GridLayout(0, 4, 0, 0));
		
		
		scrollPane.setBounds(24, 78, 1232, 108);
		pMain.add(scrollPane);
		
		JLabel lblSubPhong = new JLabel("Phòng : ");
		lblSubPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhong.setBounds(37, 201, 56, 26);
		pMain.add(lblSubPhong);
		
		JLabel lblMaPhong = new JLabel("P001");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblMaPhong.setBounds(92, 201, 56, 26);
		pMain.add(lblMaPhong);
		
		JLabel lblSubTenKH = new JLabel("Tên khách hàng : ");
		lblSubTenKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenKH.setBounds(158, 201, 120, 26);
		pMain.add(lblSubTenKH);
		
		JLabel lblTenKH = new JLabel("KH001- Phan Hữu Trọng");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenKH.setBounds(276, 201, 177, 26);
		pMain.add(lblTenKH);
		
		JLabel lblSubGioVao = new JLabel("Giờ vào: ");
		lblSubGioVao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioVao.setBounds(487, 201, 61, 26);
		pMain.add(lblSubGioVao);
		
		JLabel lblGioVao = new JLabel("15h : 30");
		lblGioVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGioVao.setBounds(548, 201, 83, 26);
		pMain.add(lblGioVao);
		
		JLabel lblSubGioRa = new JLabel("Giờ ra: ");
		lblSubGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioRa.setBounds(641, 201, 61, 26);
		pMain.add(lblSubGioRa);
		
		JComboBox<String> cbbGioRa = new JComboBox<String>();
		cbbGioRa.setBackground(Color.WHITE);
		cbbGioRa.setBounds(697, 205, 47, 22);
		cbbGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		for(int i=0 ; i <24;i++ ) {
			cbbGioRa.addItem(""+i);
		}
		pMain.add(cbbGioRa);
		
		JLabel blbSubAfterGioRa = new JLabel(":");
		blbSubAfterGioRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blbSubAfterGioRa.setBounds(746, 209, 6, 14);
		pMain.add(blbSubAfterGioRa);
		
		JComboBox<String> cbbPhutRa = new JComboBox<String>();
		cbbPhutRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhutRa.setBackground(Color.WHITE);
		cbbPhutRa.setBounds(753, 205, 47, 22);
		for(int i =0; i<60; i++) {
			cbbPhutRa.addItem(""+i);
		}
		pMain.add(cbbPhutRa);
		
		JLabel lblSubPhuThu = new JLabel("Phụ thu: ");
		lblSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhuThu.setBounds(855, 201, 61, 26);
		pMain.add(lblSubPhuThu);
		
		JComboBox<String> cbbPhuThu = new JComboBox<String>();
		cbbPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhuThu.setBackground(Color.WHITE);
		cbbPhuThu.setBounds(919, 205, 90, 22);
	
		String sPhuThu [] = {"Không","Ngày lễ","Cuối tuần"};
		for(int i =0; i< sPhuThu.length;i++) {
			cbbPhuThu.addItem(sPhuThu[i]);
		}
		pMain.add(cbbPhuThu);
		
		JLabel lblNgayLap = new JLabel("10/11/2021");
		lblNgayLap.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblNgayLap.setBounds(1173, 201, 83, 26);
		pMain.add(lblNgayLap);
		
		JLabel lblSubNgayLap = new JLabel("Ngày lập hóa đơn:");
		lblSubNgayLap.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNgayLap.setBounds(1032, 201, 131, 26);
		pMain.add(lblSubNgayLap);
		
		JPanel pDichVu = new JPanel();
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Dịch vụ ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(new Color(238,239,243,90));
		pDichVu.setBounds(24, 235, 281, 322);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(10, 38, 102, 26);
		pDichVu.add(lblSubLMH);
		
		cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(112, 36, 159, 30);
		pDichVu.add(cbbLoaiMH);
		
		JLabel lblSubTenMH = new JLabel("Tên mặt hàng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(10, 90, 102, 26);
		pDichVu.add(lblSubTenMH);
		
		cbbTenMH = new JComboBox<String>();
		cbbTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTenMH.setBackground(Color.WHITE);
		cbbTenMH.setBounds(112, 88, 159, 30);
		pDichVu.add(cbbTenMH);
		
		JLabel lblSoluongMH = new JLabel("Số lượng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(10, 142, 84, 26);
		pDichVu.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(0, 146, 182), 1, true));
		txtSoLuong.setBounds(112, 141, 146, 30);
		pDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JRadioButton rdbtnGiamSL = new JRadioButton("Giảm số lượng");
		rdbtnGiamSL.setBackground(new Color(238,239,243));
		rdbtnGiamSL.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnGiamSL.setBounds(58, 179, 147, 35);
		pDichVu.add(rdbtnGiamSL);
		
		JButton btnThemMH = new JButton("Thêm mặt hàng");
		btnThemMH.setForeground(Color.WHITE);
		btnThemMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemMH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true)); //new Color(57, 210, 247)
		btnThemMH.setBackground(new Color(57, 210, 247));
		btnThemMH.setBounds(68, 221, 166, 33);
		pDichVu.add(btnThemMH);
		
		JButton btnXoaMH = new JButton("Xóa");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaMH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaMH.setBackground(new Color(114, 23, 153));
		btnXoaMH.setBounds(10, 274, 98, 33);
		pDichVu.add(btnXoaMH);
		
		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.setForeground(Color.WHITE);
		btnLmMi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLmMi.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLmMi.setBackground(new Color(114, 23, 153));
		btnLmMi.setBounds(160, 274, 98, 33);
		pDichVu.add(btnLmMi);
		
		String col [] = {"Tên mặt hàng", "Tên loại", "Số lượng", "Đơn giá","Tổng tiền"};
		modelMatHang = new DefaultTableModel(col,0);
		
		tbMatHang = new JTable(modelMatHang);
		tbMatHang.setShowHorizontalLines(false);
		tbMatHang.setShowGrid(false);
		tbMatHang.setBackground(Color.WHITE);
		tbMatHang.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JTableHeader tbHeader = tbMatHang.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tbMatHang.setSelectionBackground(new Color(164, 44, 167,30));
		tbMatHang.setRowHeight(30);
		
		tbMatHang.setOpaque(false);
		
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
		

		
		JScrollPane spMatHang = new JScrollPane(tbMatHang);
		spMatHang.setBounds(315, 245, 697, 266);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		pMain.add(spMatHang);
		

		
		JLabel lblSubGiaPhong = new JLabel("Giá phòng: ");
		lblSubGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGiaPhong.setBounds(390, 522, 77, 26);
		
		pMain.add(lblSubGiaPhong);
		
		JLabel lblGiaPhong = new JLabel("100 000 vnđ");
		lblGiaPhong.setForeground(Color.RED);
		lblGiaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaPhong.setBounds(466, 522, 109, 26);
		pMain.add(lblGiaPhong);
		
		JLabel lblThoiGian = new JLabel("2h : 100 000 vnđ");
		lblThoiGian.setForeground(Color.RED);
		lblThoiGian.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblThoiGian.setBounds(661, 522, 152, 26);
		pMain.add(lblThoiGian);
		
		JLabel lblSubThoiGian = new JLabel("Thời gian: ");
		lblSubThoiGian.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubThoiGian.setBounds(585, 522, 77, 26);
		pMain.add(lblSubThoiGian);
		
		JLabel lblpSubPhuThu = new JLabel("Phụ thu: ");
		lblpSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblpSubPhuThu.setBounds(831, 522, 61, 26);
		pMain.add(lblpSubPhuThu);
		
		JLabel lblPhuThu = new JLabel("50 000 vnđ");
		lblPhuThu.setForeground(Color.RED);
		lblPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhuThu.setBounds(904, 522, 90, 26);
		pMain.add(lblPhuThu);
		
		JLabel lblSubThanhTien = new JLabel("Thành tiền: ");
		lblSubThanhTien.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblSubThanhTien.setBounds(804, 559, 90, 26);
		pMain.add(lblSubThanhTien);
		
		JLabel lblThanhTien = new JLabel("50 000 vnđ");
		lblThanhTien.setForeground(Color.RED);
		lblThanhTien.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 17));
		lblThanhTien.setBounds(904, 559, 90, 26);
		pMain.add(lblThanhTien);
		
		JPanel pLine = new JPanel();
		pLine.setBackground(Color.BLACK);
		pLine.setBounds(729, 555, 276, 2);
		pMain.add(pLine);
		
		JPanel pThanhToan = new JPanel();
		pThanhToan.setBackground(new Color(238,239,243));
		pThanhToan.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Thanh toán", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pThanhToan.setBounds(1024, 238, 232, 319);
		pMain.add(pThanhToan);
		pThanhToan.setLayout(null);
		
		JLabel lblSubNhanVien = new JLabel("Nhân viên lập hóa đơn :");
		lblSubNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNhanVien.setBounds(10, 21, 161, 26);
		pThanhToan.add(lblSubNhanVien);
		
		JLabel lblPhanHuTrng = new JLabel("QL001");
		lblPhanHuTrng.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhanHuTrng.setBounds(163, 21, 59, 26);
		pThanhToan.add(lblPhanHuTrng);
		
		JLabel lblSubTTHD = new JLabel("Trạng thái hóa đơn:");
		lblSubTTHD.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTTHD.setBounds(10, 51, 131, 26);
		pThanhToan.add(lblSubTTHD);
		
		JRadioButton rdbtnChoThanhToan = new JRadioButton("Chờ thanh toán");
		rdbtnChoThanhToan.setSelected(true);
		rdbtnChoThanhToan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnChoThanhToan.setBackground(new Color(238,239,243));
		rdbtnChoThanhToan.setBounds(37, 77, 153, 33);
		pThanhToan.add(rdbtnChoThanhToan);
		
		JRadioButton rdbtnDaThanhToan = new JRadioButton("Đã thanh toán");
		rdbtnDaThanhToan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnDaThanhToan.setBackground(new Color(238,239,243));
		rdbtnDaThanhToan.setBounds(38, 113, 152, 33);
		pThanhToan.add(rdbtnDaThanhToan);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnChoThanhToan);
		bg.add(rdbtnDaThanhToan);
		
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnThanhToan.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThanhToan.setBackground(new Color(57, 210, 247));
		btnThanhToan.setBounds(27, 166, 176, 53);
		pThanhToan.add(btnThanhToan);
		
		JButton btnSuaHD = new JButton("Sửa hóa đơn");
		btnSuaHD.setForeground(Color.WHITE);
		btnSuaHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaHD.setBackground(new Color(114, 23, 153));
		btnSuaHD.setBounds(10, 275, 105, 33);
		pThanhToan.add(btnSuaHD);
		
		JButton btnLamMoiHD = new JButton("Làm mới");
		btnLamMoiHD.setForeground(Color.WHITE);
		btnLamMoiHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiHD.setBackground(new Color(114, 23, 153));
		btnLamMoiHD.setBounds(132, 275, 90, 33);
		pThanhToan.add(btnLamMoiHD);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1281, 606);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));
		
		pMain.add(lblBackground);
		
		//connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//khai bao dao
		daoLoaiMH = new DAOLoaiMH();
		daoMatHang = new DAOMatHang();
		
		//Load tên loại mặt hàng
		ArrayList<LoaiMatHang> lsLoaiMH = daoLoaiMH.getAllLoaiMatHang();
		for(LoaiMatHang lmh : lsLoaiMH) {
			cbbLoaiMH.addItem(lmh.getTenLoaiMatHang());
		}
		
		// Load ten mat hang mac dinh :
		String tenMH = (String) cbbLoaiMH.getSelectedItem();
		String maLoaiMatHang = daoLoaiMH.getMaLoaiMHTheoTen(tenMH);
		ArrayList<MatHang> lsMH = daoMatHang.getMatHangTheoMaLoai(maLoaiMatHang);
		cbbTenMH.removeAllItems();
		for(MatHang mh : lsMH) {
			cbbTenMH.addItem(mh.getTenMatHang());
		}
		
		
		

		
		for(int i =0; i< 15; i++) {
			JPanel pn = new JPanel();
			
			JButton btnPhong = new JButton("P001");
			pn.add(btnPhong);
			btnPhong.setBackground(new Color(57, 210, 247));
			btnPhong.setPreferredSize(new Dimension(70,70));
			btnPhong.setBorder(new LineBorder(Color.white,10));
			
			JLabel lblTenPhong = new JLabel("P001");
			lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 15));
			pn.setBackground(new Color(164, 44, 167,20));
		
			
			pn.add(lblTenPhong);
			pPhong.add(pn);
		
		}
		
		
		
		
		//action 
		cbbLoaiMH.addItemListener(this);
		cbbTenMH.addItemListener(this);
		
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDSHD)) {
			Frm_DanhSachHoaDon frm_DanhSachHoaDon = new Frm_DanhSachHoaDon(frm);
			frm_DanhSachHoaDon.setVisible(true);
			frm.setVisible(false);
			
			
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getItem() == cbbLoaiMH.getSelectedItem()) {
			String tenMH = (String) cbbLoaiMH.getSelectedItem();
			String maLoaiMatHang = daoLoaiMH.getMaLoaiMHTheoTen(tenMH);
			ArrayList<MatHang> lsMH = daoMatHang.getMatHangTheoMaLoai(maLoaiMatHang);
			cbbTenMH.removeAllItems();
			for(MatHang mh : lsMH) {
				cbbTenMH.addItem(mh.getTenMatHang());
			}
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
}
