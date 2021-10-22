package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class FrmMatHang extends JPanel {

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
	private JTable tableMH;
	private DefaultTableModel modelMatHang;
	private DefaultTableModel modelPhong;
	private JPanel pSX;
	private Box b;
	private JComboBox<String> cbbLoaiP;
	private FixButton btnTim;
	private JTextField txtTim;
	
	
	public Panel getFrmPhong() {
		return this.pMain;
	}
	public FrmMatHang(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		//lblQLNV
				JLabel lblQLNV = new JLabel("Quản lý mặt hàng");
				lblQLNV.setFont(new Font("SansSerif", Font.BOLD, 22));
				lblQLNV.setBounds(37, 10, 255, 33);
				pMain.add(lblQLNV);
		////////////////
		//Tim Kiem//////
		////////////////
		//txtTim
				txtTim = new JTextField();
				txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
				txtTim.setColumns(10);
				txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
				txtTim.setBounds(474, 15, 281, 33);
				pMain.add(txtTim);
		//lblTim
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 15, 90, 35);
		pMain.add(lblTim);
				
		btnTim = new FixButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(786, 15, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
	//imgNhac1
		JLabel lblNhac1=new JLabel("");
		lblNhac1.setBounds(25, 105, 120, 135);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
	//imgNhac2
		JLabel lblNhac2=new JLabel("");
		lblNhac2.setBounds(1100, 105, 105, 159);
		Image imgNhac2 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac2.png");
		Image resizeNhac2 = imgNhac2.getScaledInstance(lblNhac2.getWidth(), lblNhac2.getHeight(), 0);
		lblNhac2.setIcon(new ImageIcon(resizeNhac2));
		pMain.add(lblNhac2);
		//--- 
		////////////////
		//QLBH//////
		////////////////
		JPanel pDichVu = new JPanel();
//		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Quản lý bán hàng ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
//		pDichVu.setBackground(new Color(238,239,243,90));
//		pDichVu.setBounds(37, 54, 1194, 99);
//		pMain.add(pDichVu);
//		pDichVu.setLayout(null);
		
		//---
		
		JLabel lblSubTenMH = new JLabel("Tên mặt hàng: ");
		lblSubTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubTenMH.setBounds(217, 59, 102, 26);
		pMain.add(lblSubTenMH);
		
		txtTenMH = new JTextField();
		txtTenMH.setBackground(new Color(255, 255, 255));
		txtTenMH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenMH.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenMH.setBounds(329, 58, 310, 30);
		pMain.add(txtTenMH);
		txtTenMH.setColumns(30);
		
		//-----
		JLabel lblDonGia = new JLabel("Giá bán:");
		lblDonGia.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDonGia.setBounds(257, 99, 130, 26);
		pMain.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setBackground(new Color(255, 255, 255));
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtDonGia.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDonGia.setBounds(329, 98, 310, 30);
		pMain.add(txtDonGia);
		txtDonGia.setColumns(20);
//		//------
		JLabel lblSoluongMH = new JLabel("Số lượng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(685, 59, 84, 26);
		pMain.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoLuong.setBounds(766, 58, 310, 30);
		pMain.add(txtSoLuong);
		txtSoLuong.setColumns(20);
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(654, 99, 102, 26);
		pMain.add(lblSubLMH);
		
		JComboBox<String> cbbLoaiMH = new JComboBox<String>();
		cbbLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiMH.setBackground(Color.WHITE);
		cbbLoaiMH.setBounds(766, 97, 310, 30);
		pMain.add(cbbLoaiMH);
		String cbbLoaiMH1 [] = {"Đồ ăn", "Đồ uống", "Khác"};
		for(int i = 0;i < cbbLoaiMH1.length; i++) {
			cbbLoaiMH.addItem(cbbLoaiMH1[i]);
		}
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
		btnThemKH.setBounds(374, 150, 118, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		FixButton btnSuaKH = new FixButton("Sửa");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 43, 153));
		btnSuaKH.setBounds(537, 150, 118, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaKH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaKH);
		
		FixButton btnXoaKH = new FixButton("Xóa");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 43, 153));
		btnXoaKH.setBounds(702, 150, 125, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaKH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaKH);
		
		FixButton btnReset = new FixButton("Làm mới");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnReset.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(868, 150, 144, 35);
		Image imgLamMoiKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiKH = imgLamMoiKH.getScaledInstance(25, 25, 0);
		btnReset.setIcon(new ImageIcon(resizeImgLamMoiKH));
		pMain.add(btnReset);
		//////////////////////////////////////////////
		////////////////
		//Sap Xep//////
		////////////////
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(207, 195, 237));
		pSapXep.setBounds(337, 195, 685, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		JComboBox<Object> cbbSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cbbSapXep.setBounds(60, 14, 102, 28);
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbSapXep.setBackground(Color.WHITE);
		pSapXep.add(cbbSapXep);
		
		JRadioButton radTheoMaPhong = new JRadioButton("Theo mã phòng");
		radTheoMaPhong.setBounds(188, 16, 133, 27);
		radTheoMaPhong.setSelected(true);
		radTheoMaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoMaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoMaPhong);
		
		JRadioButton radTheoLoaiPhong = new JRadioButton("Theo loại phòng");
		radTheoLoaiPhong.setBounds(342, 16, 139, 27);
		radTheoLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoLoaiPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoLoaiPhong);
		
		JRadioButton radTheoGiaPhong = new JRadioButton("Theo giá phòng");
		radTheoGiaPhong.setBounds(500, 16, 135, 27);
		radTheoGiaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoGiaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoGiaPhong);
		
		ButtonGroup bgRad=new ButtonGroup();
		bgRad.add(radTheoMaPhong);
		bgRad.add(radTheoLoaiPhong);
		bgRad.add(radTheoGiaPhong);
		radTheoMaPhong.setSelected(true);
		//pSX.setBackground(new Color(0,0,0,0));
//		pMain.add(pSX);
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
		spMatHang.setBounds(37, 249, 1194, 346);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		pMain.add(spMatHang);
		//
		//////////////////////////////////////
		
//		demo dữ liệu:
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
//		modelPhong.addRow(new Object[] {"123","123"});
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
