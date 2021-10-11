package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Frm_DonDatPhong extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV, sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JTextField txtTim;
	private JButton btnTim, btnThemDDP, btnSuaDDP, btnXoaDDP, btnLamMoiDDP;
	private JLabel lblTenKH;
	private JTextField txtTenKH;
	private JLabel lblNgaySinh;
	
	public Panel getFrmDDP() {
		return this.pMain;
	}
	public Frm_DonDatPhong(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {

		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblQLDDP = new JLabel("Quản lý đơn đặt phòng");
		lblQLDDP.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLDDP.setBounds(37, 10, 255, 33);
		pMain.add(lblQLDDP);
		
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 13, 90, 35);
		pMain.add(lblTim);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTim.setColumns(10);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(474, 12, 281, 33);
		pMain.add(txtTim);
		
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
		btnTim.setBounds(786, 11, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTenKH.setBounds(47, 65, 118, 19);
		pMain.add(lblTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		txtTenKH.setBounds(175, 60, 189, 28);
		pMain.add(txtTenKH);
		
		JLabel lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(47, 105, 124, 19);
		pMain.add(lblLoaiKH);
		
		JComboBox<Object> cbbLoaiKH = new JComboBox<Object>(new Object[] {"Thường", "Thành viên", "VIP"});
		cbbLoaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbLoaiKH.setBackground(Color.WHITE);
		cbbLoaiKH.setBounds(175, 100, 189, 27);
		pMain.add(cbbLoaiKH);
		
		lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgaySinh.setBounds(442, 69, 102, 19);
		pMain.add(lblNgaySinh);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
