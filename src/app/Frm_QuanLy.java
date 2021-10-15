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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class Frm_QuanLy extends JFrame implements ActionListener,MouseListener{


	private static final long serialVersionUID = 1L;
	private Frm_NhanVien frm_NhanVien;
	private JPanel pContent;
	private JButton btnDangXuat;
	private JLabel lblHeaderTen;
	private JLabel lblSubMa;
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
	private JLabel lblHeaderMaNV;
	private JButton btnHeaderInfo;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					Frm_QuanLy frame = new Frm_QuanLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	@SuppressWarnings("deprecation")
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
		
		int x = 160;    // vi tri chieu ngang cua item
		
		if(btnHeaderInfo.getText().contains("QL")) {
			btnItemNhanVien = new JButton("Quản lý nhân viên");
			btnItemNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemNhanVien.setBackground(new Color(255, 240, 245));
			btnItemNhanVien.setBounds(x, 0, 132, 31);
			btnItemNhanVien.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemNhanVien);
			btnItemNhanVien.addActionListener(this);
			x = x + 142; //  chuyen vi tri sang mot doan 
		}
		
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			if(lblSubMa.toString().contains("TN"))
				x += 255;
			btnItemQLBH = new JButton("Quản lý bán hàng");
			btnItemQLBH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemQLBH.setBackground(new Color(255, 240, 245));
			btnItemQLBH.setBounds(x, 0, 132, 31);
			btnItemQLBH.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemQLBH);
			btnItemQLBH.addActionListener(this);
			
			
			x = x + 142;
			
		
		}
		
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			btnItemDDP = new JButton("Quản lý đơn đặt phòng");
			btnItemDDP.setLayout(null);
			btnItemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemDDP.setBackground(new Color(255, 240, 245));
			btnItemDDP.setBounds(x, 0, 161, 31);
			btnItemDDP.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemDDP);
			btnItemDDP.addActionListener(this);

			
			x = x + 142;
			if(lblSubMa.toString().contains("TN"))
				x += 19;
		}
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("PV")) {
			x = x  + 29;
			if(lblSubMa.toString().contains("PV"))
				x += 275;
			btnItemPhong = new JButton("Quản lý phòng và mặt hàng");
			btnItemPhong.setLayout(null);
			btnItemPhong.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemPhong.setBackground(new Color(255, 240, 245));
			btnItemPhong.setBounds(x, 0, 202, 31);   // 692
			btnItemPhong.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemPhong);
			btnItemPhong.addActionListener(this);
			
			x = x + 142;
		}
		
		if(btnHeaderInfo.getText().contains("")|| btnHeaderInfo.getText().contains("PV")) {
			x += 70;
			
			btnItemKH = new JButton("Quản lý khách Hàng");
			btnItemKH.setLayout(null);
			btnItemKH.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemKH.setBackground(new Color(255, 240, 245));
			btnItemKH.setBounds(x, 0, 142, 31);  // 904
			btnItemKH.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnItemKH);
			btnItemKH.addActionListener(this);
			
			
			x += 142;
			
			
		}
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("TN")) {
			x += 10;
			btnItemTK = new JButton("Quản lý thống kê");
			btnItemTK.setLayout(null);
			btnItemTK.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnItemTK.setBackground(new Color(255, 240, 245));
			btnItemTK.setBounds(x, 0, 132, 31); // 1056
			btnItemTK.setFont(new Font("SansSerif", Font.BOLD, 13));
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
			setTitle("Quản lý nhân viên");
			resetColorMenu();
			pContent.removeAll();
			btnItemNhanVien.setBackground(new Color(192,255,255)); //new Color(233,136,236)
			btnItemNhanVien.setBorder(BorderFactory.createLineBorder(Color.white));
			frm_NhanVien = new Frm_NhanVien("QL",lblHeaderMaNV.getText(), dNow);
			pContent.add(frm_NhanVien.getPanel());

		
	}
	public void loadFrmKhachHang() {
		setTitle("Quản lý khách hàng");
		resetColorMenu();
		pContent.removeAll();
		btnItemKH.setBackground(new Color(192,255,255));
		btnItemKH.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_KhachHang = new Frm_KhachHang("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(Frm_KhachHang.getFrmKH());
	
	}
	public void loadFrmQLBH() {
		setTitle("Quản lý hóa đơn");
		resetColorMenu();
		pContent.removeAll();
		btnItemQLBH.setBackground(new Color(192,255,255));
		btnItemQLBH.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_QLBH = new Frm_QLBH(this,"QL",lblHeaderMaNV.getText(), dNow);
		pContent.add(Frm_QLBH.getFrmQLBH());
	
	}
	public void loadFrmDDP() {
		setTitle("Quản lý đơn đặt phòng");
		resetColorMenu();
		pContent.removeAll();
		btnItemDDP.setBackground(new Color(192,255,255));
		btnItemDDP.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_DDP = new Frm_DonDatPhong("QL",lblHeaderMaNV.getText(), dNow);
		pContent.add(Frm_DDP.getFrmDDP());
	
	}
	
	public void loadFrmPhong() {
		setTitle("Quản lý phòng và mặt hàng");
		resetColorMenu();
		pContent.removeAll();
		btnItemPhong.setBackground(new Color(192,255,255));
		btnItemPhong.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_Phong = new Frm_PhongMatHang("QL",lblHeaderMaNV.getText(),dNow);
		pContent.add(Frm_Phong.getFrmPhong());
	
	}
	
	public void loadFrmThongKe() {
		setTitle("Quản lý thống kê");
		resetColorMenu();
		pContent.removeAll();
		btnItemTK.setBackground(new Color(192,255,255));
		btnItemTK.setBorder(BorderFactory.createLineBorder(Color.white));
		Frm_ThongKe = new Frm_ThongKe("QL",lblHeaderMaNV.getText(),dNow);
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
