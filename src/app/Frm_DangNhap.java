package app;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Frm_DangNhap extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	private JButton btnThoat;
	private JButton btnDangNhap;

	public Frm_DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập Karaoke Fantasy");
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(138, 28 ,186));
		
		
		Image imgHeader = Toolkit.getDefaultToolkit ().getImage ("data\\imgDangNhap\\bgHeader.png");
		JLabel lbHeaderDN = new JLabel("");
		lbHeaderDN.setBounds(0, 0, 488, 87);
		getContentPane().add(lbHeaderDN);
		Image imgresize = imgHeader.getScaledInstance(lbHeaderDN.getWidth(), lbHeaderDN.getHeight(), 0);
		lbHeaderDN.setIcon(new ImageIcon(imgresize));
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setBounds(75, 188, 112, 20);
		getContentPane().add(lblTaiKhoan);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTaiKhoan.setBorder(BorderFactory.createLineBorder(new Color(217,132,219)));

		txtTaiKhoan.setBounds(197, 183, 208, 33);
		getContentPane().add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setForeground(Color.WHITE);
		lblMatKhau.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMatKhau.setBounds(75, 245, 112, 20);
		getContentPane().add(lblMatKhau);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createLineBorder(new Color(217,132,219)));
		txtMatKhau.setBounds(197, 240, 208, 33);
		getContentPane().add(txtMatKhau);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setLabelFor(this);
		lblNewLabel.setBounds(170, 116, 156, 39);
		getContentPane().add(lblNewLabel);
		
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDangNhap.setBackground(new Color(164, 44,167));
		btnDangNhap.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setBounds(170, 323, 156, 33);
		getContentPane().add(btnDangNhap);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnThoat.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnThoat.setBackground(new Color(164, 44, 167));
		btnThoat.setBounds(170, 367, 156, 33);
		getContentPane().add(btnThoat);
		
		JLabel lblNhac1 = new JLabel("");
		
		lblNhac1.setBounds(28, 299, 103, 122);
		
		Image imgNhac1 = Toolkit.getDefaultToolkit ().getImage ("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		getContentPane().add(lblNhac1);
		
		JLabel lblNhac2 = new JLabel("");
		lblNhac2.setBounds(356, 299, 103, 122);
		getContentPane().add(lblNhac2);
		Image imgNhac2 = Toolkit.getDefaultToolkit ().getImage ("data\\img\\IconNhac2.png");
		Image resizeNhac2 = imgNhac2.getScaledInstance(lblNhac2.getWidth(), lblNhac2.getHeight(), 0);
		lblNhac2.setIcon(new ImageIcon(resizeNhac2));
		
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		
	}

	
	//event
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			System.exit(0);
		}
		if(o.equals(btnDangNhap)) {	
			Frm_QuanLy frmNhanVien = new Frm_QuanLy();
			frmNhanVien.setVisible(true);
			this.setVisible(false);
			
			
		}
		
	}
}
