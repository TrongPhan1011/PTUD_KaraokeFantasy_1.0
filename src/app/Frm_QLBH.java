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
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class Frm_QLBH extends JPanel implements ActionListener {

	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private Panel pMain;
	private JTextField textField;
	private JTextField txtTim;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel modelMatHang;
	private JTable tbMatHang;
	
	public Panel getFrmQLBH() {
		return this.pMain;
	}
	
	public Frm_QLBH(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai)  {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý bán hàng");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNewLabel.setBounds(37, 10, 255, 33);
		pMain.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(374, 13, 90, 35);
		pMain.add(lblNewLabel_1);
		
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
		
		JButton btnDSHD = new JButton("Xem danh sách hóa đơn");
		btnDSHD.setForeground(Color.WHITE);
		btnDSHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDSHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnDSHD.setBackground(new Color(114, 23, 153));
		btnDSHD.setBounds(1043, 13, 194, 33);
		pMain.add(btnDSHD);
		
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
		
		
		scrollPane.setBounds(37, 78, 1200, 108);
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
//		
//		int namHienTai = dNgayHienTai.getYear()+10;
//		int namBatDau = namHienTai -20;
//		for(int i =namBatDau; i <= namHienTai; i++) {
//			cbbPhuThu.addItem(""+i);
//		}
//		cbbPhuThu.setSelectedItem(dNgayHienTai.getYear()+"");
//		
		String sPhuThu [] = {"Không","Ngày lễ","Cuối tuần"};
		for(int i =0; i< sPhuThu.length;i++) {
			cbbPhuThu.addItem(sPhuThu[i]);
		}
		pMain.add(cbbPhuThu);
		
		JLabel lblNgayLap = new JLabel("10/11/2021");
		lblNgayLap.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblNgayLap.setBounds(1165, 201, 83, 26);
		pMain.add(lblNgayLap);
		
		JLabel lblSubNgayLap = new JLabel("Ngày lập hóa đơn:");
		lblSubNgayLap.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNgayLap.setBounds(1032, 201, 131, 26);
		pMain.add(lblSubNgayLap);
		
		JPanel pDichVu = new JPanel();
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Dịch vụ ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(new Color(0,0,0,0));
		pDichVu.setBounds(37, 235, 255, 344);
		pMain.add(pDichVu);
		pDichVu.setLayout(null);
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(10, 38, 102, 26);
		pDichVu.add(lblSubLMH);
		
		JComboBox<String> cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(112, 36, 133, 30);
		pDichVu.add(cbbLoaiMH);
		
		JLabel lblSubTenMH = new JLabel("Tên mặt hàng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(10, 90, 102, 26);
		pDichVu.add(lblSubTenMH);
		
		JComboBox<String> cbbTenMH = new JComboBox<String>();
		cbbTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTenMH.setBackground(Color.WHITE);
		cbbTenMH.setBounds(112, 88, 133, 30);
		pDichVu.add(cbbTenMH);
		
		JLabel lblSoluongMH = new JLabel("Số lượng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(10, 142, 84, 26);
		pDichVu.add(lblSoluongMH);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 141, 133, 30);
		pDichVu.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton rdbtnGiamSL = new JRadioButton("Giảm số lượng");
		rdbtnGiamSL.setBackground(new Color(0,0,0,0));
		rdbtnGiamSL.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnGiamSL.setBounds(62, 185, 147, 35);
		pDichVu.add(rdbtnGiamSL);
		
		JButton btnThemMH = new JButton("Thêm mặt hàng");
		btnThemMH.setForeground(Color.WHITE);
		btnThemMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemMH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true)); //new Color(57, 210, 247)
		btnThemMH.setBackground(new Color(57, 210, 247));
		btnThemMH.setBounds(57, 227, 152, 33);
		pDichVu.add(btnThemMH);
		
		JButton btnXoaMH = new JButton("Xóa");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaMH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaMH.setBackground(new Color(114, 23, 153));
		btnXoaMH.setBounds(14, 280, 98, 33);
		pDichVu.add(btnXoaMH);
		
		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.setForeground(Color.WHITE);
		btnLmMi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLmMi.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLmMi.setBackground(new Color(114, 23, 153));
		btnLmMi.setBounds(147, 280, 98, 33);
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
		
		JLabel lblSubNhanVien = new JLabel("Nhân viên lập hóa đơn :");
		lblSubNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNhanVien.setBounds(1032, 246, 161, 26);
		pMain.add(lblSubNhanVien);
		
		JLabel lblPhanHuTrng = new JLabel("QL001");
		lblPhanHuTrng.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhanHuTrng.setBounds(1187, 246, 61, 26);
		pMain.add(lblPhanHuTrng);
		
		JLabel lblSubTTHD = new JLabel("Trạng thái hóa đơn:");
		lblSubTTHD.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTTHD.setBounds(1032, 283, 131, 26);
		pMain.add(lblSubTTHD);
		
		JRadioButton rdbtnChoThanhToan = new JRadioButton("Chờ thanh toán");
		rdbtnChoThanhToan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnChoThanhToan.setBackground(new Color(0,0,0,0));
		rdbtnChoThanhToan.setBounds(1043, 316, 153, 33);
		pMain.add(rdbtnChoThanhToan);
		
		JRadioButton rdbtnDaThanhToan = new JRadioButton("Đã thanh toán");
		rdbtnDaThanhToan.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnDaThanhToan.setBackground(new Color(0,0,0,0));  //
		rdbtnDaThanhToan.setBounds(1043, 352, 120, 33);
		pMain.add(rdbtnDaThanhToan);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnChoThanhToan);
		bg.add(rdbtnDaThanhToan);
		rdbtnChoThanhToan.setSelected(true);
		
		
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnThanhToan.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThanhToan.setBackground(new Color(57, 210, 247));
		btnThanhToan.setBounds(1032, 410, 205, 53);
		pMain.add(btnThanhToan);
		
		JButton btnSuaHD = new JButton("Sửa hóa đơn");
		btnSuaHD.setForeground(Color.WHITE);
		btnSuaHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaHD.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaHD.setBackground(new Color(114, 23, 153));
		btnSuaHD.setBounds(1032, 515, 98, 33);
		pMain.add(btnSuaHD);
		
		JButton btnLmMi_1 = new JButton("Làm mới");
		btnLmMi_1.setForeground(Color.WHITE);
		btnLmMi_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLmMi_1.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLmMi_1.setBackground(new Color(114, 23, 153));
		btnLmMi_1.setBounds(1140, 515, 97, 33);
		pMain.add(btnLmMi_1);
		
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
		
		JLabel lblVn = new JLabel("50 000 vnđ");
		lblVn.setForeground(Color.RED);
		lblVn.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblVn.setBounds(904, 522, 90, 26);
		pMain.add(lblVn);
		
		JLabel lblThnhTin = new JLabel("Thành tiền: ");
		lblThnhTin.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblThnhTin.setBounds(804, 559, 90, 26);
		pMain.add(lblThnhTin);
		
		JLabel lblVn_1 = new JLabel("50 000 vnđ");
		lblVn_1.setForeground(Color.RED);
		lblVn_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 17));
		lblVn_1.setBounds(904, 559, 90, 26);
		pMain.add(lblVn_1);
		
		JPanel pLine = new JPanel();
		pLine.setBackground(Color.BLACK);
		pLine.setBounds(729, 555, 276, 2);
		pMain.add(pLine);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("data\\img\\background.png"));
		lblNewLabel_2.setBounds(0, 0, 1281, 606);
		pMain.add(lblNewLabel_2);
		
		
		

		
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
		
		

		
//		JButton btnPhong = new JButton("P001");
//		btnPhong.setBackground(new Color(57, 210, 247));
//		btnPhong.setBorder(new LineBorder(Color.white,5,true));
//		btnPhong.setBounds(30, 11, 88, 61);
//		pPhong.add(btnPhong);
//		
//		JLabel lblTenPhong = new JLabel("P001");
//		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 15));
//		lblTenPhong.setBounds(55, 83, 48, 14);
//		pPhong.add(lblTenPhong);
		
//		JLabel lblSapXep = new JLabel("Sắp xếp:");
//		lblSapXep.setFont(new Font("SansSerif", Font.BOLD, 14));
//		lblSapXep.setBounds(494, 80, 90, 35);
//		pMain.add(lblSapXep);
//		
//		JComboBox cbSapXep = new JComboBox();
//		cbSapXep.setBackground(Color.WHITE);
//		cbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
//		cbSapXep.setBorder(new LineBorder(new Color(0, 146, 182), 1, true));
//		cbSapXep.setBounds(610, 80, 145, 30);
//		String dsSX[] = {"Theo tên","Chờ thanh toán","Phòng trống"} ;
//		cbSapXep.addItem("Tất cả");
//		cbSapXep.setSelectedItem("Tất cả");
//		
//		
//		pMain.add(cbSapXep);
		
		
		
		
		
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
