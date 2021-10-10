package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Frm_ThongKe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JRadioButton rdbtnTKNg;
	private JRadioButton rdbtnTKT;
	private JRadioButton rdbtnTKNam;
	private FixButton btnTK;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private Date dNow;
	
	
	public Panel getFrmThongKe() {
		return this.pMain;
	}
	public Frm_ThongKe(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lbbTitle = new JLabel("Quản lý thống kê");
		lbbTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
		lbbTitle.setBounds(78, 26, 255, 55);
		pMain.add(lbbTitle);
		/////////////////////////////////////------------------------------------------
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		
		dNow = new Date(nam,thang,ngay);
		
		
		JLabel lblHeaderDate = new JLabel("Thời Gian:");
		lblHeaderDate.setForeground(Color.BLACK);
		lblHeaderDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderDate.setBounds(735, 41, 96, 21);
		pMain.add(lblHeaderDate);
		
		JLabel lblNgayHienTai = new JLabel(ngay+" / "+thang+" / "+nam);
		lblNgayHienTai.setForeground(Color.BLACK);
		lblNgayHienTai.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNgayHienTai.setBounds(835, 38, 151, 21);
		pMain.add(lblNgayHienTai);
		/////////////////////////////////////------------------------------------------
		JPanel pThongKe = new JPanel();
		pThongKe.setBackground(new Color(238,239,243,90));
		pThongKe.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pThongKe.setBounds(37, 79, 370, 470);
		pThongKe.setBackground(Color.WHITE);
		pMain.add(pThongKe);
		pThongKe.setLayout(null);
		
		//
		JPanel pBieuDo = new JPanel();
		pBieuDo.setBackground(new Color(238,239,243,90));
		pBieuDo.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pBieuDo.setBounds(461, 189, 780, 358);
		pBieuDo.setBackground(Color.WHITE);
		pMain.add(pBieuDo);
		pBieuDo.setLayout(null);
		//
		
		rdbtnTKNg = new JRadioButton("Thống kê theo ngày");
		rdbtnTKT = new JRadioButton("Thống kê theo tháng");
		rdbtnTKNam = new JRadioButton("Thống kê theo năm");
		rdbtnTKNam.setBackground(Color.white);
		rdbtnTKNg.setBackground(Color.white);
		rdbtnTKT.setBackground(Color.white);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTKNam);
		bg.add(rdbtnTKNg);
		bg.add(rdbtnTKT);
		rdbtnTKNg.setSelected(true);
		
		pThongKe.add(rdbtnTKNg);
		pThongKe.add(rdbtnTKNam);
		pThongKe.add(rdbtnTKT);
		
		rdbtnTKNg.setBounds(30, 45, 250, 17);
		rdbtnTKNg.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnTKT.setBounds(30, 137, 250, 17);
		rdbtnTKT.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnTKNam.setBounds(30, 275, 250, 17);
		rdbtnTKNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		JLabel lblChonNgay = new JLabel("Chọn ngày: ");
		lblChonNgay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNgay.setBounds(55, 81, 100, 36);
		pThongKe.add(lblChonNgay);
		
		JComboBox<String> cbbNgaySinh = new JComboBox<String>();
		cbbNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNgaySinh.setBackground(Color.white);
		cbbNgaySinh.setBounds(140, 81, 150, 36);
		for(int i = 1;i <=31; i++) {
			cbbNgaySinh.addItem(""+i);
		}
		pThongKe.add(cbbNgaySinh);
		
		JLabel lblChonThang = new JLabel("Chọn tháng: ");
		lblChonThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonThang.setBounds(55, 168, 100, 38);
		pThongKe.add(lblChonThang);
		
		JLabel lblChonNamTh = new JLabel("Chọn năm: ");
		lblChonNamTh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNamTh.setBounds(55, 216, 100, 36);
		pThongKe.add(lblChonNamTh);
		
		JComboBox<String> cbbThang = new JComboBox<String>();
		cbbThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThang.setBackground(Color.white);
		cbbThang.setBounds(140, 172, 150, 38);
		for(int i = 1; i <= 12;i++) {
			cbbThang.addItem(""+i);
		}
		pThongKe.add(cbbThang);
		
		JComboBox<String> cbbNamTh = new JComboBox<String>();
		cbbNamTh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamTh.setBackground(Color.white);
		cbbNamTh.setBounds(140, 218, 150, 36);
		for(int i = 2004; i > 1900; i--) {
			cbbNamTh.addItem(""+i);
		}
		pThongKe.add(cbbNamTh);
		
		JLabel lblChonNam = new JLabel("Chọn năm: ");
		lblChonNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNam.setBounds(55, 301, 100, 35);
		pThongKe.add(lblChonNam);
		
		JComboBox<String> cbbNam = new JComboBox<String>();
		cbbNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNam.setBackground(Color.white);
		cbbNam.setBounds(140, 301, 150, 35);
		for(int i = 2004; i > 1900; i--) {
			cbbNam.addItem(""+i);
		}
		pThongKe.add(cbbNam);
		
		btnTK = new FixButton("Thống kê");
		btnTK.setFont(new Font("SansSerif", Font.ITALIC, 25));
		btnTK.setForeground(Color.WHITE);
		btnTK.setFont(new Font("SansSerif", Font.BOLD, 14));
//		btnTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTK.setBackground(new Color(114, 23, 153));
		btnTK.setBounds(103, 358, 157, 74);
		pThongKe.add(btnTK);
		
		/////////////////////////////////
		JPanel pTongDoanhThu = new JPanel();
		pTongDoanhThu.setBackground(new Color(238,239,243,90));
		pTongDoanhThu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pTongDoanhThu.setBounds(461, 79, 180, 80);
		pTongDoanhThu.setBackground(Color.WHITE);
		pMain.add(pTongDoanhThu);
		pTongDoanhThu.setLayout(null);
		
		JLabel lblTDT= new JLabel("  Tổng doanh thu ");
		lblTDT.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblTDT.setForeground(Color.pink);
		lblTDT.setBounds(28, 62, 160, 17);
		pTongDoanhThu.add(lblTDT);
		
		FixButton btnTongDoanhThu = new FixButton("10,000,000đ");
		btnTongDoanhThu.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnTongDoanhThu.setForeground(Color.BLACK);
		btnTongDoanhThu.setBackground(Color.WHITE);
		btnTongDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTongDoanhThu.setBounds(10, 10, 160, 42);
		pTongDoanhThu.add(btnTongDoanhThu);
		
		JPanel pSoKhachHang = new JPanel();
		pSoKhachHang.setBackground(new Color(238,239,243,90));
		pSoKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pSoKhachHang.setBounds(680, 79, 140, 80);
		pSoKhachHang.setBackground(Color.WHITE);
		pMain.add(pSoKhachHang);
		pSoKhachHang.setLayout(null);
		
		JLabel lblSoKH= new JLabel("Số khách hàng ");
		lblSoKH.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblSoKH.setForeground(Color.pink);
		lblSoKH.setBounds(20, 62, 140, 17);
		pSoKhachHang.add(lblSoKH);
		
		FixButton btnSoKH = new FixButton("250");
		btnSoKH.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSoKH.setForeground(Color.BLACK);
		btnSoKH.setBackground(Color.WHITE);
		btnSoKH.setBounds(10, 10, 120, 42);
		pSoKhachHang.add(btnSoKH);
		
		JPanel pSoMatHang = new JPanel();
		pSoMatHang.setBackground(new Color(238,239,243,90));
		pSoMatHang.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pSoMatHang.setBounds(859, 79, 140, 80);
		pSoMatHang.setBackground(Color.WHITE);
		pMain.add(pSoMatHang);
		pSoMatHang.setLayout(null);
		
		JLabel lblSoMH= new JLabel("Số mặt hàng ");
		lblSoMH.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblSoMH.setForeground(Color.pink);
		lblSoMH.setBounds(28, 62, 140, 17);
		pSoMatHang.add(lblSoMH);
		
		FixButton btnSoMH = new FixButton("300");
		btnSoMH.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSoMH.setForeground(Color.BLACK);
		btnSoMH.setBackground(Color.WHITE);
		btnSoMH.setBounds(10, 10, 120, 42);
		pSoMatHang.add(btnSoMH);
		
		JPanel pTgPhongSD = new JPanel();
		pTgPhongSD.setBackground(new Color(238,239,243,90));
		pTgPhongSD.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pTgPhongSD.setBounds(1040, 79, 200, 80);
		pTgPhongSD.setBackground(Color.WHITE);
		pMain.add(pTgPhongSD);
		pTgPhongSD.setLayout(null);
		
		JLabel lblTGSD= new JLabel("Thời gian hoạt động phòng ");
		lblTGSD.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblTGSD.setForeground(Color.pink);
		lblTGSD.setBounds(8, 62, 200, 17);
		pTgPhongSD.add(lblTGSD);
		
		FixButton btnTGHD = new FixButton("500 Giờ");
		btnTGHD.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnTGHD.setForeground(Color.BLACK);
		btnTGHD.setBackground(Color.WHITE);
		btnTGHD.setBounds(10, 10, 180, 42);
		pTgPhongSD.add(btnTGHD);
		
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, -23, 1281, 629);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		
		JPanel panel = new JPanel();
		panel.setBounds(461, 189, 793, 359);
		pMain.add(panel);
	}
}
