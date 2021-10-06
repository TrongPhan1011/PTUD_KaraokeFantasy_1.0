package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



import entity.LoaiPhong;
import entity.Phong;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

public class Frm_QLBH extends JPanel implements ActionListener {

	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Date dNgayHienTai;
	private Panel pMain;
	private JTextField textField;
	private JTextField txtTim;
	private JTextField textField_1;
	
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
		
		JButton btnDSHD = new JButton("Xem toàn bộ hóa đơn");
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
		lblSubTenKH.setBounds(158, 201, 295, 26);
		pMain.add(lblSubTenKH);
		
		JLabel lblTenKH = new JLabel("Phan Hữu Trọng");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenKH.setBounds(276, 201, 177, 26);
		pMain.add(lblTenKH);
		
		JLabel lblSubGioVao = new JLabel("Giờ vào: ");
		lblSubGioVao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioVao.setBounds(474, 201, 61, 26);
		pMain.add(lblSubGioVao);
		
		JLabel lblGioVao = new JLabel("15h : 30");
		lblGioVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGioVao.setBounds(535, 201, 83, 26);
		pMain.add(lblGioVao);
		
		JLabel lblSubGioRa = new JLabel("Giờ ra: ");
		lblSubGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGioRa.setBounds(628, 201, 61, 26);
		pMain.add(lblSubGioRa);
		
		JComboBox<String> cbbGioRa = new JComboBox<String>();
		cbbGioRa.setBackground(Color.WHITE);
		cbbGioRa.setBounds(684, 205, 47, 22);
		cbbGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		for(int i=0 ; i <24;i++ ) {
			cbbGioRa.addItem(""+i);
		}
		pMain.add(cbbGioRa);
		
		JLabel blbSubAfterGioRa = new JLabel(":");
		blbSubAfterGioRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blbSubAfterGioRa.setBounds(733, 209, 6, 14);
		pMain.add(blbSubAfterGioRa);
		
		JComboBox<String> cbbPhutRa = new JComboBox<String>();
		cbbPhutRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhutRa.setBackground(Color.WHITE);
		cbbPhutRa.setBounds(740, 205, 47, 22);
		for(int i =0; i<60; i++) {
			cbbPhutRa.addItem(""+i);
		}
		pMain.add(cbbPhutRa);
		
		JLabel lblSubPhuThu = new JLabel("Phụ thu: ");
		lblSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhuThu.setBounds(870, 201, 61, 26);
		pMain.add(lblSubPhuThu);
		
		JComboBox<String> cbbPhuThu = new JComboBox<String>();
		cbbPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbPhuThu.setBackground(Color.WHITE);
		cbbPhuThu.setBounds(934, 205, 90, 22);
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
		lblNgayLap.setBounds(1154, 201, 83, 26);
		pMain.add(lblNgayLap);
		
		JLabel lblSubNgayLap = new JLabel("Ngày lập:");
		lblSubNgayLap.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubNgayLap.setBounds(1084, 201, 71, 26);
		pMain.add(lblSubNgayLap);
		
		JPanel pDichVu = new JPanel();
		pDichVu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Dịch vụ ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pDichVu.setBackground(Color.WHITE);
		pDichVu.setBounds(37, 235, 255, 335);
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
		rdbtnGiamSL.setBackground(Color.WHITE);
		rdbtnGiamSL.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnGiamSL.setBounds(62, 185, 147, 35);
		pDichVu.add(rdbtnGiamSL);
		
		

		
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
