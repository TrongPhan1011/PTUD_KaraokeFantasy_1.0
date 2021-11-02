package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

import com.formdev.flatlaf.FlatLightLaf;


import connection.ConnectDB;

public class FrmQuanLy extends JFrame implements ActionListener,MouseListener{


	private static final long serialVersionUID = 1L;
	private FrmNhanVien frmNhanVien;
	private JPanel pContent;
	private JButton btnDangXuat;
	private JLabel lblHeaderTen;
	private JLabel lblSubMa;
	private FrmKhachHang frmKhachHang;
	private JButton btnItemNhanVien;
	private JButton btnItemQLBH;
	private JButton btnItemDDP;
	private JButton btnItemMatHang;
	private JButton btnItemKH;
	private JButton btnItemTK;
	private FrmThanhToan frmQLBH;
	private FrmDonDatPhong frmDDP;
	private FrmThongKe frmThongKe;

	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JLabel lblHeaderMaNV;
	private JButton btnHeaderInfo;
	private JButton btnItemPhong;
	private FrmMatHang frmMatHang;
	private FrmPhong frmPhong;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					FrmQuanLy frame = new FrmQuanLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	@SuppressWarnings("deprecation")

	public FrmQuanLy() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý nhân viên");
//		setExtendedState(MAXIMIZED_BOTH);
		setSize(1281,750);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1281, 78);
		panel.setBackground(new Color(114, 23 ,153));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(23, 11, 217, 56);
		Image imglogo = Toolkit.getDefaultToolkit ().getImage ("data\\img\\logo.png");
		Image resizelogo = imglogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 0);
		lblLogo.setIcon(new ImageIcon(resizelogo));
		panel.add(lblLogo);
		
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		
		dNow = new Date(nam,thang,ngay);
		
		
		JLabel lblHeaderDate = new JLabel("Hiện tại:");
		lblHeaderDate.setForeground(Color.WHITE);
		lblHeaderDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderDate.setBounds(489, 41, 66, 21);
		panel.add(lblHeaderDate);
		
		JLabel lblNgayHienTai = new JLabel(ngay+" / "+thang+" / "+nam);
		lblNgayHienTai.setForeground(Color.WHITE);
		lblNgayHienTai.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNgayHienTai.setBounds(565, 38, 151, 21);
		panel.add(lblNgayHienTai);
		
		
		lblHeaderTen = new JLabel("Tên Nhân Viên");
		lblHeaderTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderTen.setForeground(Color.WHITE);
		lblHeaderTen.setBounds(873, 11, 170, 20);
		panel.add(lblHeaderTen);
		
		lblSubMa = new JLabel("Mã nhân viên:");
		lblSubMa.setForeground(Color.WHITE);
		lblSubMa.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSubMa.setBounds(873, 41, 110, 20);
		panel.add(lblSubMa);
		
		btnDangXuat = new FixButton("Đăng Xuất");
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDangXuat.setBounds(1134, 20, 110, 35);
		btnDangXuat.setBackground(new Color(0xE91940));
		panel.add(btnDangXuat);
		
		lblHeaderMaNV = new JLabel("NV002");
		lblHeaderMaNV.setForeground(Color.WHITE);
		lblHeaderMaNV.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblHeaderMaNV.setBounds(983, 41, 60, 20);
		panel.add(lblHeaderMaNV);
		
		btnHeaderInfo = new JButton("QL");
		btnHeaderInfo.setForeground(Color.WHITE);
		btnHeaderInfo.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnHeaderInfo.setBounds(1053, 11, 60, 56);
		btnHeaderInfo.setBackground(new Color(57, 210, 247));
//		btnHeaderInfo.setBorder(new LineBorder(Color.white,10));
		
		panel.add(btnHeaderInfo);
		
		
		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(221, 160, 221));
		pMenu.setBounds(0, 77, 1281, 31);
		getContentPane().add(pMenu);
		pMenu.setLayout(null);
		
		int x = 119;    // vi tri chieu ngang cua item
		
		btnItemNhanVien = new JButton("Quản lý nhân viên");
		if(btnHeaderInfo.getText().contains("QL")) {
			
			btnItemNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanVien.setBackground(new Color(255, 240, 245));
			btnItemNhanVien.setBounds(x, 0, 132, 31);
			btnItemNhanVien.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemNhanVien);
		
			
			x = x + 142; //  chuyen vi tri sang mot doan 
		}
		
		btnItemDDP = new JButton("Quản lý đơn đặt phòng");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			
			if(btnHeaderInfo.getText().contains("TN")){
				x+= 225;
			}
			
			btnItemDDP.setLayout(null);
			btnItemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemDDP.setBackground(new Color(255, 240, 245));
			btnItemDDP.setBounds(x, 0, 161, 31);   //261
			btnItemDDP.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemDDP);
			

			
			x = x + 142;
			if( btnHeaderInfo.getText().contains("TN"))
				x -= 255;
		}
		
		btnItemQLBH = new JButton("Quản lý thanh toán");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			x+= 29;
			if(btnHeaderInfo.getText().contains("TN"))
				x += 255;
			
			btnItemQLBH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLBH.setBackground(new Color(255, 240, 245));
			btnItemQLBH.setBounds(x, 0, 132, 31);   //432
			btnItemQLBH.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemQLBH);
		
			
			x = x + 142;
			
		
		}
		

		btnItemMatHang = new JButton("Quản lý mặt hàng");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("PV")) {
			
			if(btnHeaderInfo.getText().contains("PV"))
				x += 305;
			
			btnItemMatHang.setLayout(null);
			btnItemMatHang.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemMatHang.setBackground(new Color(255, 240, 245));
			btnItemMatHang.setBounds(x, 0, 149, 31);   // 574
			btnItemMatHang.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemMatHang);
			
			
			x = x + 142;
		}
		
		btnItemPhong = new JButton("Quản lý phòng");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("PV")) {
			x+= 17;
			
			btnItemPhong.setFont(new Font("SansSerif", Font.BOLD, 13));
			btnItemPhong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemPhong.setBackground(new Color(255, 240, 245));
			btnItemPhong.setBounds(x, 0, 132, 31); // 733
			pMenu.add(btnItemPhong);
			x = x + 142;
		
		}
		
		btnItemKH = new JButton("Quản lý khách Hàng");
		if(btnHeaderInfo.getText().contains("")|| btnHeaderInfo.getText().contains("PV")) {
			
			
			btnItemKH.setLayout(null);
			btnItemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemKH.setBackground(new Color(255, 240, 245));
			btnItemKH.setBounds(x, 0, 142, 31);  // 875
			btnItemKH.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemKH);
			
			
			
			x += 142;
			
			
		}
		
		btnItemTK = new JButton("Quản lý thống kê");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			x += 10;
			
			btnItemTK.setLayout(null);
			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemTK.setBackground(new Color(255, 240, 245));
			btnItemTK.setBounds(x, 0, 132, 31); // 1056
			btnItemTK.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemTK);
			
		
			
			
		
		}
		
		pContent = new JPanel();
		pContent.setBounds(0, 109, 1281, 606);
		getContentPane().add(pContent);
		pContent.setLayout(null);
		
		// Load frm mac dinh 
		if(btnHeaderInfo.getText().contains("QL")){
			loadFrmNhanVien();
		}
		
		btnDangXuat.addActionListener(this);
		btnItemNhanVien.addActionListener(this);
		btnItemDDP.addActionListener(this);
		btnItemQLBH.addActionListener(this);
		btnItemMatHang.addActionListener(this);
		btnItemPhong.addActionListener(this);
		btnItemKH.addActionListener(this);
		btnItemTK.addActionListener(this);
	}
	
	// reset màu menu
	public void resetColorMenu() {
		btnItemNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemNhanVien.setBackground(new Color(255, 240, 245));
		
		btnItemQLBH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemQLBH.setBackground(new Color(255, 240, 245));
		
		btnItemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemDDP.setBackground(new Color(255, 240, 245));
		
		btnItemMatHang.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemMatHang.setBackground(new Color(255, 240, 245));
		
		btnItemPhong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemPhong.setBackground(new Color(255, 240, 245));
		
		btnItemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemKH.setBackground(new Color(255, 240, 245));
		
		btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemTK.setBackground(new Color(255, 240, 245));
		
	}
	
	public void loadFrmNhanVien() {
			setTitle("Quản lý nhân viên");
			resetColorMenu();
			pContent.removeAll();
			btnItemNhanVien.setBackground(new Color(192,255,255)); //new Color(233,136,236)
			btnItemNhanVien.setBorder(BorderFactory.createLineBorder(Color.white));
			frmNhanVien = new FrmNhanVien("QL",lblHeaderMaNV.getText(), dNow);
			pContent.add(frmNhanVien.getPanel());
		

		
	}
	public void loadFrmKhachHang() {
		setTitle("Quản lý khách hàng");
		resetColorMenu();
		pContent.removeAll();
		btnItemKH.setBackground(new Color(192,255,255));
		btnItemKH.setBorder(BorderFactory.createLineBorder(Color.white));
		frmKhachHang = new FrmKhachHang("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(frmKhachHang.getFrmKH());
	
	
	}
	public void loadFrmQLBH() {
		setTitle("Quản lý thanh toán");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLBH.setBackground(new Color(192,255,255));
		btnItemQLBH.setBorder(BorderFactory.createLineBorder(Color.white));
		frmQLBH = new FrmThanhToan(this,"QL",lblHeaderMaNV.getText(), dNow);
		pContent.add(frmQLBH.getFrmQLBH());
	
	}
	public void loadFrmDDP() {
		setTitle("Quản lý đơn đặt phòng");
		resetColorMenu();
		pContent.removeAll();
		btnItemDDP.setBackground(new Color(192,255,255));
		btnItemDDP.setBorder(BorderFactory.createLineBorder(Color.white));

		frmDDP = new FrmDonDatPhong("QL",lblHeaderMaNV.getText(), dNow);
		pContent.add(frmDDP.getFrmDDP());

		

	
	}
	
	public void loadFrmMatHang() {
		setTitle("Quản lý mặt hàng");
		resetColorMenu();
		pContent.removeAll();
		btnItemMatHang.setBackground(new Color(192,255,255));
		btnItemMatHang.setBorder(BorderFactory.createLineBorder(Color.white));
		frmMatHang = new FrmMatHang("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(frmMatHang.getFrmMatHang());
	
	}
	public void loadFrmPhong() {
		setTitle("Quản lý phòng");
		resetColorMenu();
		pContent.removeAll();
		btnItemPhong.setBackground(new Color(192,255,255));
		btnItemPhong.setBorder(BorderFactory.createLineBorder(Color.white));
		frmPhong = new FrmPhong("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(frmPhong.getFrmPhong());
		
	
	}
	
	public void loadFrmThongKe() {
		setTitle("Quản lý thống kê");
		resetColorMenu();
		pContent.removeAll();
		btnItemTK.setBackground(new Color(192,255,255));
		btnItemTK.setBorder(BorderFactory.createLineBorder(Color.white));
		frmThongKe = new FrmThongKe("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(frmThongKe.getFrmThongKe());
		
	
	}
	
	public void dangXuat() {
		int optDangXuat = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION );
		if(optDangXuat == JOptionPane.YES_OPTION) {
			FrmDangNhap frame = new FrmDangNhap();
			frame.setVisible(true);
			this.setVisible(false);
			
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDangXuat)){
			dangXuat();
		}
		
		if(o.equals(btnItemNhanVien)) {
			loadFrmNhanVien();
		}
		if(o.equals(btnItemQLBH)) {
			loadFrmQLBH();
		}
		if(o.equals(btnItemKH)) {
			loadFrmKhachHang();
		}
		if(o.equals(btnItemDDP)) {
			loadFrmDDP();
		}
		if(o.equals(btnItemMatHang)) {
			loadFrmMatHang();
		}
		if(o.equals(btnItemPhong)) {
			loadFrmPhong();
		}
		
		if(o.equals(btnItemTK)) {
			loadFrmThongKe();
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
		
	

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
		

		
		
		
		
		
		
		
	}
}