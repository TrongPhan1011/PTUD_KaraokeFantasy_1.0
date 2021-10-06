package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Frm_QuanLy extends JFrame implements ActionListener,MouseListener{


	private static final long serialVersionUID = 1L;
	private Frm_NhanVien frm_NhanVien;
	private JPanel pContent;
	private JButton btnDangXuat;
	private JLabel lblHeaderTen;
	private JLabel lblHeaderMa;
	private Frm_KhachHang Frm_KhachHang;
	private JButton btnItemNhanVien;
	private JButton btnItemQLBH;
	private JButton btnItemDDP;
	private JButton btnItemPhong;
	private JButton btnItemKH;
	private JButton btnItemTK;
	private Frm_QLBH Frm_QLBH;
	private Frm_DonDatPhong Frm_DDP;
	private Frm_PhongMatHang Frm_Phong;
	private Frm_ThongKe Frm_ThongKe;
	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_QuanLy frame = new Frm_QuanLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Frm_QuanLy() {
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
		lblLogo.setBounds(23, 0, 256, 78);
		Image imglogo = Toolkit.getDefaultToolkit ().getImage ("data\\img\\logo.png");
		Image resizelogo = imglogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 0);
		lblLogo.setIcon(new ImageIcon(resizelogo));
		panel.add(lblLogo);
		
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue() -1;
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
		lblHeaderTen.setBounds(908, 11, 135, 20);
		panel.add(lblHeaderTen);
		
		lblHeaderMa = new JLabel("Mã: QL1234 ");
		lblHeaderMa.setForeground(Color.WHITE);
		lblHeaderMa.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHeaderMa.setBounds(908, 42, 135, 20);
		panel.add(lblHeaderMa);
		
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDangXuat.setBounds(1134, 20, 110, 30);
		btnDangXuat.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDangXuat.setBackground(new Color(164, 44, 167));
		panel.add(btnDangXuat);
		
		JPanel pQL = new JPanel();
		pQL.setBackground(Color.LIGHT_GRAY);
		pQL.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		pQL.setBounds(1053, 11, 55, 51);
		panel.add(pQL);
		pQL.setLayout(null);
		
		JLabel lblQL = new JLabel("QL");
		lblQL.setBounds(14, 9, 37, 35);
		lblQL.setFont(new Font("SansSerif", Font.BOLD, 20));
		pQL.add(lblQL);
		
		
		
		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(221, 160, 221));
		pMenu.setBounds(0, 77, 1281, 31);
		getContentPane().add(pMenu);
		pMenu.setLayout(null);
		
		int x = 160;    // vi tri chieu ngang cua item
		
		if(lblHeaderMa.toString().contains("QL")) {
			btnItemNhanVien = new JButton("Quản lý nhân viên");
			btnItemNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanVien.setBackground(new Color(255, 240, 245));
			btnItemNhanVien.setBounds(x, 0, 132, 31);
			pMenu.add(btnItemNhanVien);
			btnItemNhanVien.addActionListener(this);
			x = x + 142; //  chuyen vi tri sang mot doan 
		}
		
		if(lblHeaderMa.toString().contains("QL")|| lblHeaderMa.toString().contains("TN")) {
			if(lblHeaderMa.toString().contains("TN"))
				x += 255;
			btnItemQLBH = new JButton("Quản lý bán hàng");
			btnItemQLBH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLBH.setBackground(new Color(255, 240, 245));
			btnItemQLBH.setBounds(x, 0, 132, 31);
			pMenu.add(btnItemQLBH);
			btnItemQLBH.addActionListener(this);
			
			
			x = x + 142;
			
		
		}
		
		if(lblHeaderMa.toString().contains("QL")|| lblHeaderMa.toString().contains("TN")) {
			btnItemDDP = new JButton("Quản lý đơn đặt phòng");
			btnItemDDP.setLayout(null);
			btnItemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemDDP.setBackground(new Color(255, 240, 245));
			btnItemDDP.setBounds(x, 0, 161, 31);
			pMenu.add(btnItemDDP);
			btnItemDDP.addActionListener(this);

			
			x = x + 142;
			if(lblHeaderMa.toString().contains("TN"))
				x += 19;
		}
		if(lblHeaderMa.toString().contains("QL")|| lblHeaderMa.toString().contains("PV")) {
			x = x  + 29;
			if(lblHeaderMa.toString().contains("PV"))
				x += 275;
			btnItemPhong = new JButton("Quản lý phòng và mặt hàng");
			btnItemPhong.setLayout(null);
			btnItemPhong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemPhong.setBackground(new Color(255, 240, 245));
			btnItemPhong.setBounds(x, 0, 202, 31);   // 692
			pMenu.add(btnItemPhong);
			btnItemPhong.addActionListener(this);
			
			x = x + 142;
		}
		
		if(lblHeaderMa.toString().contains("QL")|| lblHeaderMa.toString().contains("PV")) {
			x += 70;
			
			btnItemKH = new JButton("Quản lý khách Hàng");
			btnItemKH.setLayout(null);
			btnItemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemKH.setBackground(new Color(255, 240, 245));
			btnItemKH.setBounds(x, 0, 142, 31);  // 904
			pMenu.add(btnItemKH);
			btnItemKH.addActionListener(this);
			
			
			x += 142;
			
			
		}
		if(lblHeaderMa.toString().contains("QL")|| lblHeaderMa.toString().contains("TN")) {
			x += 10;
			btnItemTK = new JButton("Quản lý thống kê");
			btnItemTK.setLayout(null);
			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemTK.setBackground(new Color(255, 240, 245));
			btnItemTK.setBounds(x, 0, 132, 31); // 1056
			pMenu.add(btnItemTK);
			btnItemTK.addActionListener(this);
			
			
		
		}
		
		pContent = new JPanel();
		pContent.setBounds(0, 109, 1281, 606);
		getContentPane().add(pContent);
		pContent.setLayout(null);
		
		btnDangXuat.addActionListener(this);
	}
	
	// reset màu menu
	public void resetColorMenu() {
		btnItemNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemNhanVien.setBackground(new Color(255, 240, 245));
		
		btnItemQLBH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemQLBH.setBackground(new Color(255, 240, 245));
		
		btnItemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemDDP.setBackground(new Color(255, 240, 245));
		
		btnItemPhong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemPhong.setBackground(new Color(255, 240, 245));
		
		btnItemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemKH.setBackground(new Color(255, 240, 245));
		
		btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnItemTK.setBackground(new Color(255, 240, 245));
		
	}
	
	public void loadFrmNhanVien() {
			resetColorMenu();
			pContent.removeAll();
			btnItemNhanVien.setBackground(new Color(192,255,255)); //new Color(233,136,236)
			btnItemNhanVien.setBorder(BorderFactory.createLineBorder(Color.white));
			frm_NhanVien = new Frm_NhanVien("QL","Phan Huu Trong", dNow);
			pContent.add(frm_NhanVien.getPanel());

		
	}
	public void loadFrmKhachHang() {
		resetColorMenu();
		pContent.removeAll();
		btnItemKH.setBackground(new Color(192,255,255));
		btnItemKH.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_KhachHang = new Frm_KhachHang("QL","Phan Huu Trong",dNow);
		pContent.add(Frm_KhachHang.getFrmKH());
	
	}
	public void loadFrmQLBH() {
		resetColorMenu();
		pContent.removeAll();
		btnItemQLBH.setBackground(new Color(192,255,255));
		btnItemQLBH.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_QLBH = new Frm_QLBH("QL","Phan Huu Trong", dNow);
		pContent.add(Frm_QLBH.getFrmQLBH());
	
	}
	public void loadFrmDDP() {
		resetColorMenu();
		pContent.removeAll();
		btnItemDDP.setBackground(new Color(192,255,255));
		btnItemDDP.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_DDP = new Frm_DonDatPhong("QL","Phan Huu Trong", dNow);
		pContent.add(Frm_DDP.getFrmDDP());
	
	}
	
	public void loadFrmPhong() {
		resetColorMenu();
		pContent.removeAll();
		btnItemPhong.setBackground(new Color(192,255,255));
		btnItemPhong.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_Phong = new Frm_PhongMatHang("QL","Phan Huu Trong",dNow);
		pContent.add(Frm_Phong.getFrmPhong());
	
	}
	
	public void loadFrmThongKe() {
		resetColorMenu();
		pContent.removeAll();
		btnItemTK.setBackground(new Color(192,255,255));
		btnItemTK.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_ThongKe = new Frm_ThongKe("QL","Phan Huu Trong",dNow);
		pContent.add(Frm_ThongKe.getFrmThongKe());
	
	}
	
	public void dangXuat() {
		int optDangXuat = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION );
		if(optDangXuat == JOptionPane.YES_OPTION) {
			Frm_DangNhap frame = new Frm_DangNhap();
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
