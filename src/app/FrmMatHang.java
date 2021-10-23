package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JPanel;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FrmMatHang extends JPanel implements ActionListener, MouseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTextField txtSoLuong;
	private JTextField txtTenMH;
	private JTextField txtDonGia;
	private JTable tableMH;
	private DefaultTableModel modelMatHang;
	private FixButton btnTim;
	private JTextField txtTim;
	private FixButton btnThemKH;
	private FixButton btnSuaKH;
	private FixButton btnXoaKH;
	private FixButton btnReset;
	private DAOMatHang daoMH;
	private DAOLoaiMH daoLMH;
	
	
	public Panel getFrmMatHang() {
		return this.pMain;
	}
	public FrmMatHang(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		daoMH = new DAOMatHang();
		daoLMH = new DAOLoaiMH();
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
//		JPanel pDichVu = new JPanel();
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
//		String cbbLoaiMH1 [] = {"Đồ ăn", "Đồ uống", "Khác"};
		ArrayList<LoaiMatHang> loaiMH = daoLMH.getAllLoaiMatHang();
		for(LoaiMatHang lmh : loaiMH) {
			cbbLoaiMH.addItem(lmh.getTenLoaiMatHang());
		}
		/////
		////////////////
		//Buttons//////
		////////////////
		/////
		btnThemKH = new FixButton("Thêm");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnThemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemKH.setBackground(new Color(114, 43, 153));
		btnThemKH.setBounds(374, 150, 118, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		btnSuaKH = new FixButton("Sửa");
		btnSuaKH.setForeground(Color.WHITE);
		btnSuaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnSuaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaKH.setBackground(new Color(114, 43, 153));
		btnSuaKH.setBounds(537, 150, 118, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaKH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaKH);
		
		btnXoaKH = new FixButton("Xóa");
		btnXoaKH.setForeground(Color.WHITE);
		btnXoaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnXoaKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaKH.setBackground(new Color(114, 43, 153));
		btnXoaKH.setBounds(702, 150, 125, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaKH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaKH);
		
		btnReset = new FixButton("Làm mới");
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
		cbbSapXep.setBounds(23, 14, 115, 28);
		cbbSapXep.setFont(new Font("SansSerif", Font.BOLD, 15));
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbSapXep.setBackground(Color.WHITE);
		pSapXep.add(cbbSapXep);
		
		JRadioButton radTheoMaPhong = new JRadioButton("Theo tên mặt hàng");
		radTheoMaPhong.setBounds(150, 16, 170, 27);
		radTheoMaPhong.setSelected(true);
		radTheoMaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoMaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoMaPhong);
		
		JRadioButton radTheoLoaiPhong = new JRadioButton("Theo loại mặt hàng");
		radTheoLoaiPhong.setBounds(342, 16, 170, 27);
		radTheoLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoLoaiPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoLoaiPhong);
		
		JRadioButton radTheoGiaPhong = new JRadioButton("Theo giá ");
		radTheoGiaPhong.setBounds(520, 15, 135, 27);
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
		String mh [] = {"Mã MH","Tên mặt hàng", "Loại MH", "Số lượng", "Giá bán"};
		modelMatHang = new DefaultTableModel(mh,0);
		
		tableMH = new JTable(modelMatHang);
		tableMH.setShowHorizontalLines(false);
		tableMH.setShowGrid(false);
		tableMH.setBackground(Color.WHITE);
		tableMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableMH.setSelectionBackground(new Color(164, 44, 167, 30));
		tableMH.setSelectionForeground(new Color(114, 23, 153));
		tableMH.setRowHeight(30);
		
		JTableHeader tbHeader = tableMH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableMH.setSelectionBackground(new Color(164, 44, 167,30));
		tableMH.setRowHeight(30);
		
		tableMH.setOpaque(false);
	
		JScrollPane spMatHang = new JScrollPane(tableMH);
		spMatHang.setBounds(37, 249, 1194, 346);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		pMain.add(spMatHang);
		//
		//////////////////////////////////////
		btnThemKH.addActionListener(null);
		btnXoaKH.addActionListener(null);
		btnTim.addActionListener(null);
		btnSuaKH.addActionListener(null);
		btnReset.addActionListener(null);
		////
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		//////////////////////////////////////////////////////////////////////////
		loadTableMH();
	}
	public void loadTableMH() {
		ArrayList<MatHang> lsMH = daoMH.getDSMatHang();
		for(MatHang mh : lsMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(),mh.getGiaMatHang() } );
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
