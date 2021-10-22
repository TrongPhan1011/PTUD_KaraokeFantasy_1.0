package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;


public class FrmThongKe extends JPanel {

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
	public FrmThongKe(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lbbTitle = new JLabel("     Quản lý thống kê");
		lbbTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
		lbbTitle.setBounds(94, 10, 255, 55);
		pMain.add(lbbTitle);
		/////////////////////////////////////------------------------------------------
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		
		dNow = new Date(nam,thang,ngay);
		
		
		JLabel lblHeaderDate = new JLabel("   Thời Gian:");
		lblHeaderDate.setForeground(Color.BLACK);
		lblHeaderDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderDate.setBounds(713, 20, 112, 41);
		pMain.add(lblHeaderDate);
		
		JLabel lblNgayHienTai = new JLabel(ngay+" / "+thang+" / "+nam);
		lblNgayHienTai.setForeground(Color.BLACK);
		lblNgayHienTai.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNgayHienTai.setBounds(835, 27, 151, 21);
		pMain.add(lblNgayHienTai);
		/////////////////////////////////////------------------------------------------
		JPanel pThongKe = new JPanel();
		pThongKe.setBackground(new Color(238,239,243,90));
		pThongKe.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pThongKe.setBounds(37, 79, 388, 470);
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
		
		rdbtnTKNg.setBounds(30, 16, 260, 30);
		rdbtnTKNg.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnTKT.setBounds(30, 97, 260, 29);
		rdbtnTKT.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnTKNam.setBounds(30, 244, 260, 45);
		rdbtnTKNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		JLabel lblChonNgay = new JLabel("Chọn ngày: ");
		lblChonNgay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNgay.setBounds(55, 52, 100, 36);
		pThongKe.add(lblChonNgay);
		
//		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
//		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
//		lblNgaySinh.setBounds(859, 65, 90, 18);
//		pMain.add(lblNgaySinh);
		
		SqlDateModel modelNgaySinh=new SqlDateModel();
		modelNgaySinh.setSelected(true);
		//modelNgaySinh.setDate(2000, 0, 1); //month= 0+1 = 1
		Properties p=new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel=new JDatePanelImpl(modelNgaySinh, p);
		JDatePickerImpl datePicker=new JDatePickerImpl(panel, new AbstractFormatter() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object stringToValue(String text) throws ParseException {
//				text=new String("Chọn ngày");
//				return text;
				return "";
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if(value != null) {
					Calendar cal = (Calendar) value;
					SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
					String strDate = format.format(cal.getTime());
					return strDate;
				}
				return "";
			}
			
		});
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		datePicker.getJFormattedTextField().setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
//		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setFont(new Font("SansSerif", Font.PLAIN, 15));
		datePicker.getJFormattedTextField().setText("dd-mm-yyyy");
		
		datePicker.setBounds(140, 62, 150, 23);
		datePicker.setTextEditable(true);
		
		pThongKe.add(datePicker);
		

//		ftfNgaySinh.setBounds(964, 62, 100, 25);
//		ftfNgaySinh.setEditable(false);
//		pMain.add(ftfNgaySinh);
//		
		JButton btnLich=new JButton();
		btnLich.setBackground(Color.WHITE);
		btnLich.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		btnLich.setIcon(new ImageIcon("data/img/lich1.png"));
		btnLich.setBounds(1072, 62, 26, 25);
		////////////////////////////////////////////////
		////////////////////////////////////////////////
		JLabel lblChonThang = new JLabel("Chọn tháng: ");
		lblChonThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonThang.setBounds(55, 144, 100, 38);
		pThongKe.add(lblChonThang);
		
		JLabel lblChonNamTh = new JLabel("Chọn năm: ");
		lblChonNamTh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNamTh.setBounds(55, 202, 100, 36);
		pThongKe.add(lblChonNamTh);
		
		JComboBox<String> cbbThang = new JComboBox<String>();
		cbbThang.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbThang.setBackground(Color.white);
		cbbThang.setBounds(140, 144, 180, 38);
		for(int i = 1; i <= 12;i++) {
			cbbThang.addItem(""+i);
		}
		pThongKe.add(cbbThang);
		
		JComboBox<String> cbbNamTh = new JComboBox<String>();
		cbbNamTh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNamTh.setBackground(Color.white);
		cbbNamTh.setBounds(140, 202, 180, 36);
		for(int i = 2004; i > 1900; i--) {
			cbbNamTh.addItem(""+i);
		}
		pThongKe.add(cbbNamTh);
		
		JLabel lblChonNam = new JLabel("Chọn năm: ");
		lblChonNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChonNam.setBounds(55, 302, 100, 35);
		pThongKe.add(lblChonNam);
		
		JComboBox<String> cbbNam = new JComboBox<String>();
		cbbNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbNam.setBackground(Color.white);
		cbbNam.setBounds(140, 302, 180, 35);
		for(int i = 2004; i > 1900; i--) {
			cbbNam.addItem(""+i);
		}
		pThongKe.add(cbbNam);
		
		btnTK = new FixButton("Thống kê");
//		btnTK.setFont(new Font("SansSerif", Font.ITALIC, 25));
		btnTK.setForeground(Color.WHITE);
		btnTK.setFont(new Font("SansSerif", Font.BOLD, 25));
//		btnTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTK.setBackground(new Color(114, 23, 153));
		btnTK.setBounds(102, 372, 157, 74);
		pThongKe.add(btnTK);
		
		/////////////////////////////////
		JPanel pTongDoanhThu = new JPanel();
		pTongDoanhThu.setBackground(new Color(238,239,243,90));
		pTongDoanhThu.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pTongDoanhThu.setBounds(461, 79, 180, 100);
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
		pSoKhachHang.setBounds(669, 79, 151, 100);
		pSoKhachHang.setBackground(Color.WHITE);
		pMain.add(pSoKhachHang);
		pSoKhachHang.setLayout(null);
		
		JLabel lblSoKH= new JLabel("   Số khách hàng ");
		lblSoKH.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblSoKH.setForeground(Color.pink);
		lblSoKH.setBounds(20, 62, 140, 17);
		pSoKhachHang.add(lblSoKH);
		
		FixButton btnSoKH = new FixButton("250");
		btnSoKH.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSoKH.setForeground(Color.BLACK);
		btnSoKH.setBackground(Color.WHITE);
		btnSoKH.setBounds(10, 10, 131, 42);
		pSoKhachHang.add(btnSoKH);
		
		JPanel pSoMatHang = new JPanel();
		pSoMatHang.setBackground(new Color(238,239,243,90));
		pSoMatHang.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pSoMatHang.setBounds(848, 79, 169, 100);
		pSoMatHang.setBackground(Color.WHITE);
		pMain.add(pSoMatHang);
		pSoMatHang.setLayout(null);
		
		JLabel lblSoMH= new JLabel("   Số mặt hàng ");
		lblSoMH.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblSoMH.setForeground(Color.pink);
		lblSoMH.setBounds(28, 62, 140, 17);
		pSoMatHang.add(lblSoMH);
		
		FixButton btnSoMH = new FixButton("300");
		btnSoMH.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSoMH.setForeground(Color.BLACK);
		btnSoMH.setBackground(Color.WHITE);
		btnSoMH.setBounds(10, 10, 149, 42);
		pSoMatHang.add(btnSoMH);
		
		JPanel pTgPhongSD = new JPanel();
		pTgPhongSD.setBackground(new Color(238,239,243,90));
		pTgPhongSD.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pTgPhongSD.setBounds(1040, 79, 200, 100);
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
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(461, 189, 793, 359);
		pMain.add(panel1);
	}
}
