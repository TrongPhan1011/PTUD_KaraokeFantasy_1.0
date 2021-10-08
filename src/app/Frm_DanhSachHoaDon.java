package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Frm_DanhSachHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JFrame frm;
	private JTable tableDanhSachKH;
	private JTable tableDanhSachMH;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frm_DanhSachHoaDon frame = new Frm_DanhSachHoaDon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Frm_DanhSachHoaDon(JFrame frm) {
		this.frm = frm;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Danh sách hóa đơn");
		setSize(1081, 706);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel pMain = new JPanel();
		pMain.setBounds(0, 0, 1106, 682);
		pMain.setBackground(Color.WHITE);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
		btnNewButton = new JButton("Quay lại");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(114, 23 ,153));
		btnNewButton.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(497, 624, 89, 23);
		pMain.add(btnNewButton);
		
		JLabel lblDanhSachHD = new JLabel("Danh sách hóa đơn");
		lblDanhSachHD.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblDanhSachHD.setBounds(30, 0, 225, 29);
		pMain.add(lblDanhSachHD);
		btnNewButton.addActionListener(this);
		
		JLabel lblTimKiem = new JLabel("Tìm Kiếm:");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiem.setBounds(314, 13, 90, 35);
		pMain.add(lblTimKiem);
		
		JTextField textFieldTK = new JTextField();
		textFieldTK.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldTK.setBounds(402, 16, 281, 33);
		textFieldTK.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldTK);
		textFieldTK.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(693, 14, 98, 33);
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setForeground(Color.WHITE);
		Image imgTim = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
		JPanel pSapXep = new JPanel();
		pSapXep.setBackground(new Color(255,255,255));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(309, 59, 497, 68);
		pMain.add(pSapXep);
		
		JComboBox<String> cbbSort = new JComboBox<String>();
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(Color.WHITE);
		String cbSort [] = {"Tăng dần", "Giảm dần"};
		for(int i = 0; i < cbSort.length; i++) {
			cbbSort.addItem(cbSort[i]);
		}
		pSapXep.add(cbbSort);
		
		JRadioButton rdbtnTheoMa = new JRadioButton("Theo mã");
		rdbtnTheoMa.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoMa.setBackground(Color.WHITE);
		pSapXep.add(rdbtnTheoMa);
		
		JRadioButton rdbtnTheoTenKH = new JRadioButton("Theo tên khách hàng");
		rdbtnTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoTenKH.setBackground(Color.WHITE);
		pSapXep.add(rdbtnTheoTenKH);
		
		JRadioButton rdbtnTheoGia = new JRadioButton("Theo giá");
		rdbtnTheoGia.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoGia.setBackground(Color.white);
		pSapXep.add(rdbtnTheoGia);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTheoMa);
		bg.add(rdbtnTheoTenKH);
		bg.add(rdbtnTheoGia);
		rdbtnTheoMa.setSelected(true);
		
		JScrollPane scrollPaneListKH = new JScrollPane();
		scrollPaneListKH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneListKH.setBackground(new Color(164, 44, 167));
		scrollPaneListKH.setBounds(30, 145, 1003, 170);
		pMain.add(scrollPaneListKH);
		
		String col []= {"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Mã nhân viên", "Nhân viên lập HĐ", "Ngày lập"};
		DefaultTableModel modelListKH = new DefaultTableModel(col, 0);		
		tableDanhSachKH = new JTable(modelListKH);
		tableDanhSachKH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDanhSachKH.setShowHorizontalLines(false);
		tableDanhSachKH.setRowHeight(30);
		tableDanhSachKH.setOpaque(false);
		tableDanhSachKH.setShowGrid(false);
		
		JTableHeader tbHeader = tableDanhSachKH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		scrollPaneListKH.setViewportView(tableDanhSachKH);
		
		JLabel lblPhong = new JLabel("Phòng :");
		lblPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblPhong.setBounds(203, 335, 56, 23);
		pMain.add(lblPhong);
		
		JLabel lblTenPhong = new JLabel("P001");
		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenPhong.setBounds(264, 339, 57, 14);
		pMain.add(lblTenPhong);
		
		JLabel lblGioVao = new JLabel("Giờ vào :");
		lblGioVao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGioVao.setBounds(331, 339, 66, 14);
		pMain.add(lblGioVao);
		
		JLabel lblGiaTriGioVao = new JLabel("19h : 30");
		lblGiaTriGioVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaTriGioVao.setBounds(402, 339, 56, 14);
		pMain.add(lblGiaTriGioVao);
		
		JLabel lblGioRa = new JLabel("Giờ ra :");
		lblGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGioRa.setBounds(496, 339, 56, 14);
		pMain.add(lblGioRa);
		
		JLabel lblGiaTriGioRa = new JLabel("20h : 30");
		lblGiaTriGioRa.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaTriGioRa.setBounds(550, 339, 56, 14);
		pMain.add(lblGiaTriGioRa);
		
		JLabel lblTrangThaiHoaDon = new JLabel("Trạng thái hóa đơn :");
		lblTrangThaiHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTrangThaiHoaDon.setBounds(637, 338, 131, 17);
		pMain.add(lblTrangThaiHoaDon);
		
		JLabel lblTrangThai = new JLabel("đã thanh toán");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTrangThai.setBounds(778, 339, 98, 14);
		pMain.add(lblTrangThai);
		
		JScrollPane scrollPaneListMH = new JScrollPane();
		scrollPaneListMH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneListMH.setBackground(new Color(164, 44, 167));
		scrollPaneListMH.setBounds(30, 379, 1003, 190);
		pMain.add(scrollPaneListMH);
		
		String colMH [] = {"Mã CTHD", "Mã mặt hàng", "Tên mặt hàng", "Loại mặt hàng", "Số lượng", "Đơn giá", "Tổng tiền"};
		DefaultTableModel modelListMH = new DefaultTableModel(col, 0);	
		
		tableDanhSachMH = new JTable(modelListMH);
		tableDanhSachMH.setShowHorizontalLines(false);
		tableDanhSachMH.setShowGrid(false);
		tableDanhSachMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDanhSachMH.setRowHeight(30);
		tableDanhSachMH.setOpaque(false);
		
		JTableHeader tbHeaderMH = tableDanhSachMH.getTableHeader();
		tbHeaderMH.setBackground(new Color(164, 44, 167));
		tbHeaderMH.setForeground(Color.white);
		tbHeaderMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		scrollPaneListMH.setViewportView(tableDanhSachMH);
		
		//demo
		modelListKH.addRow(new Object[] {"123","123"});
		
		JLabel lblSubGiaPhong = new JLabel("Giá phòng: ");
		lblSubGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGiaPhong.setBounds(390, 580, 77, 26);
		
		pMain.add(lblSubGiaPhong);
		
		JLabel lblGiaPhong = new JLabel("100 000 vnđ");
		lblGiaPhong.setForeground(Color.RED);
		lblGiaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaPhong.setBounds(466, 580, 109, 26);
		pMain.add(lblGiaPhong);
		
		JLabel lblThoiGian = new JLabel("2h : 100 000 vnđ");
		lblThoiGian.setForeground(Color.RED);
		lblThoiGian.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblThoiGian.setBounds(661, 580, 152, 26);
		pMain.add(lblThoiGian);
		
		JLabel lblSubThoiGian = new JLabel("Thời gian: ");
		lblSubThoiGian.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubThoiGian.setBounds(585, 580, 77, 26);
		pMain.add(lblSubThoiGian);
		
		JLabel lblpSubPhuThu = new JLabel("Phụ thu: ");
		lblpSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblpSubPhuThu.setBounds(831, 580, 61, 26);
		pMain.add(lblpSubPhuThu);
		
		JLabel lblPhuThu = new JLabel("50 000 vnđ");
		lblPhuThu.setForeground(Color.RED);
		lblPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhuThu.setBounds(904, 580, 90, 26);
		pMain.add(lblPhuThu);
		
		JLabel lblSubThanhTien = new JLabel("Thành tiền: ");
		lblSubThanhTien.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblSubThanhTien.setBounds(804, 621, 90, 26);
		pMain.add(lblSubThanhTien);
		
		JLabel lblThanhTien = new JLabel("250 000 vnđ");
		lblThanhTien.setForeground(Color.RED);
		lblThanhTien.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 17));
		lblThanhTien.setBounds(904, 621, 98, 26);
		pMain.add(lblThanhTien);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(800, 609, 200, 1);
		pMain.add(panel);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackground.setBounds(0, 0, 1292, 670);
		Image imgBackground = Toolkit.getDefaultToolkit ().getImage ("data\\img\\background.png");
		Image resizeBG = imgBackground.getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), 0);
		lblBackground.setIcon(new ImageIcon(resizeBG));
		
		pMain.add(lblBackground);
		// Important
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				frm.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnNewButton)) {
			setVisible(false);
			frm.setVisible(true);
			
			
		}
		
	}
}
