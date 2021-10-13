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
	private JButton btnQuayLai;
	private JFrame frm;
	private JTable tableDanhSachKH;
	private JTable tableDanhSachMH;
	private JButton btnTim;
	private JButton btnLamMoi;


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
		setResizable(false);
		
		setTitle("Danh sách hóa đơn");
		setSize(1081, 706);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel pMain = new JPanel();
		pMain.setBounds(0, 0, 1106, 682);
		pMain.setBackground(Color.WHITE);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnQuayLai.setBackground(new Color(114, 23 ,153));
		btnQuayLai.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setBounds(497, 618, 115, 35);
		
		Image imgBack = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconBack.png");
		Image resizeImgback = imgBack.getScaledInstance(25, 25, 0);
		btnQuayLai.setIcon(new ImageIcon(resizeImgback));
		
		pMain.add(btnQuayLai);
		
		JLabel lblDanhSachHD = new JLabel("Danh sách hóa đơn");
		lblDanhSachHD.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblDanhSachHD.setBounds(430, 0, 225, 29);
		pMain.add(lblDanhSachHD);
		btnQuayLai.addActionListener(this);
		
		JLabel lblTimKiem = new JLabel("Tìm Kiếm:");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiem.setBounds(311, 29, 90, 35);
		pMain.add(lblTimKiem);
		
		JTextField textFieldTK = new JTextField();
		textFieldTK.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldTK.setBounds(399, 32, 281, 33);
		textFieldTK.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		pMain.add(textFieldTK);
		textFieldTK.setColumns(10);
		
		btnTim = new FixButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBounds(690, 30, 98, 33);
		btnTim.setBackground(new Color(114, 23 ,153));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setForeground(Color.WHITE);
		Image imgTim = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
		JPanel pSapXep = new JPanel();
		pSapXep.setBackground(new Color(220,210,239));
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23, 153), 1, true), "S\u1EAFp x\u1EBFp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBounds(190, 75, 694, 49);
		pMain.add(pSapXep);
		
		JComboBox<String> cbbSort = new JComboBox<String>();
		cbbSort.setBounds(22, 13, 112, 28);
		cbbSort.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbSort.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSort.setBackground(Color.WHITE);
		String cbSort [] = {"Tăng dần", "Giảm dần"};
		for(int i = 0; i < cbSort.length; i++) {
			cbbSort.addItem(cbSort[i]);
		}
		pSapXep.setLayout(null);
		pSapXep.add(cbbSort);
		
		JRadioButton rdbtnTheoMa = new JRadioButton("Theo mã");
		rdbtnTheoMa.setBounds(160, 14, 85, 27);
		rdbtnTheoMa.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoMa.setBackground(new Color(220,210,239));
		pSapXep.add(rdbtnTheoMa);
		
		JRadioButton rdbtnTheoTenKH = new JRadioButton("Theo tên khách hàng");
		rdbtnTheoTenKH.setBounds(268, 13, 167, 28);
		rdbtnTheoTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoTenKH.setBackground(new Color(220,210,239));
		pSapXep.add(rdbtnTheoTenKH);
		
		JRadioButton rdbtnTheoGia = new JRadioButton("Theo giá");
		rdbtnTheoGia.setBounds(462, 14, 85, 27);
		rdbtnTheoGia.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdbtnTheoGia.setBackground(new Color(220,210,239));
		pSapXep.add(rdbtnTheoGia);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTheoMa);
		bg.add(rdbtnTheoTenKH);
		bg.add(rdbtnTheoGia);
		rdbtnTheoMa.setSelected(true);
		
		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoi.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoi.setBackground(new Color(114, 23, 153));
		btnLamMoi.setBounds(571, 15, 98, 25);
		Image imgLamMoiDS = Toolkit.getDefaultToolkit ().getImage ("data\\img\\iconReset.png");
		Image resizeImgLamMoiDS = imgLamMoiDS.getScaledInstance(25, 25, 0);
		btnLamMoi.setIcon(new ImageIcon(resizeImgLamMoiDS));
		pSapXep.add(btnLamMoi);
		
		JScrollPane scrollPaneListKH = new JScrollPane();
		scrollPaneListKH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneListKH.setBackground(new Color(164, 44, 167));
		scrollPaneListKH.setBounds(34, 136, 1003, 147);
		pMain.add(scrollPaneListKH);
		
		String col []= {"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Mã nhân viên", "Nhân viên lập HĐ", "Ngày lập"};
		DefaultTableModel modelListKH = new DefaultTableModel(col, 0);		
		tableDanhSachKH = new JTable(modelListKH);
		tableDanhSachKH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDanhSachKH.setShowHorizontalLines(true);
		tableDanhSachKH.setRowHeight(30);
		tableDanhSachKH.setSelectionBackground(new Color(164, 44, 167,30));
		//tableDanhSachKH.setOpaque(false);
		tableDanhSachKH.setShowGrid(true);
		
		JTableHeader tbHeader = tableDanhSachKH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		scrollPaneListKH.setViewportView(tableDanhSachKH);
		
		JLabel lblPhong = new JLabel("Phòng :");
		lblPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblPhong.setBounds(129, 294, 56, 25);
		pMain.add(lblPhong);
		
		JLabel lblTenPhong = new JLabel("P001");
		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenPhong.setBounds(190, 298, 57, 21);
		pMain.add(lblTenPhong);
		
		JLabel lblGioVao = new JLabel("Giờ vào :");
		lblGioVao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGioVao.setBounds(257, 298, 66, 21);
		pMain.add(lblGioVao);
		
		JLabel lblGiaTriGioVao = new JLabel("19h : 30");
		lblGiaTriGioVao.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaTriGioVao.setBounds(328, 298, 56, 21);
		pMain.add(lblGiaTriGioVao);
		
		JLabel lblGioRa = new JLabel("Giờ ra :");
		lblGioRa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblGioRa.setBounds(422, 298, 56, 21);
		pMain.add(lblGioRa);
		
		JLabel lblGiaTriGioRa = new JLabel("20h : 30");
		lblGiaTriGioRa.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaTriGioRa.setBounds(476, 298, 56, 21);
		pMain.add(lblGiaTriGioRa);
		
		JLabel lblTrangThaiHoaDon = new JLabel("Trạng thái hóa đơn :");
		lblTrangThaiHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTrangThaiHoaDon.setBounds(712, 297, 146, 22);
		pMain.add(lblTrangThaiHoaDon);
		
		JLabel lblTrangThai = new JLabel("Đã thanh toán");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTrangThai.setBounds(853, 298, 115, 21);
		pMain.add(lblTrangThai);
		
		JScrollPane scrollPaneListMH = new JScrollPane();
		scrollPaneListMH.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneListMH.setBackground(new Color(164, 44, 167));
		scrollPaneListMH.setBounds(34, 330, 1003, 205);
		pMain.add(scrollPaneListMH);
		
		String colMH [] = {"Mã CTHD", "Tên mặt hàng", "Loại mặt hàng", "Số lượng", "Đơn giá", "Tổng tiền"};
		DefaultTableModel modelListMH = new DefaultTableModel(colMH, 0);	
		
		tableDanhSachMH = new JTable(modelListMH);
		tableDanhSachMH.setShowHorizontalLines(true);
		tableDanhSachMH.setShowGrid(true);
		tableDanhSachMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDanhSachMH.setRowHeight(30);
		//tableDanhSachMH.setOpaque(false);
		
		JTableHeader tbHeaderMH = tableDanhSachMH.getTableHeader();
		tbHeaderMH.setBackground(new Color(164, 44, 167));
		tbHeaderMH.setForeground(Color.white);
		tbHeaderMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		scrollPaneListMH.setViewportView(tableDanhSachMH);
		
		//demo
		modelListKH.addRow(new Object[] {"123","123"});
		
		JLabel lblSubGiaPhong = new JLabel("Giá phòng: ");
		lblSubGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubGiaPhong.setBounds(365, 546, 77, 26);
		
		pMain.add(lblSubGiaPhong);
		
		JLabel lblGiaPhong = new JLabel("100 000 vnđ");
		lblGiaPhong.setForeground(Color.RED);
		lblGiaPhong.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblGiaPhong.setBounds(441, 546, 109, 26);
		pMain.add(lblGiaPhong);
		
		JLabel lblThoiGian = new JLabel("2h : 100 000 vnđ");
		lblThoiGian.setForeground(Color.RED);
		lblThoiGian.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblThoiGian.setBounds(636, 546, 152, 26);
		pMain.add(lblThoiGian);
		
		JLabel lblSubThoiGian = new JLabel("Thời gian: ");
		lblSubThoiGian.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubThoiGian.setBounds(560, 546, 77, 26);
		pMain.add(lblSubThoiGian);
		
		JLabel lblpSubPhuThu = new JLabel("Phụ thu: ");
		lblpSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblpSubPhuThu.setBounds(806, 546, 61, 26);
		pMain.add(lblpSubPhuThu);
		
		JLabel lblPhuThu = new JLabel("50 000 vnđ");
		lblPhuThu.setForeground(Color.RED);
		lblPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblPhuThu.setBounds(879, 546, 90, 26);
		pMain.add(lblPhuThu);
		
		JLabel lblSubThanhTien = new JLabel("Thành tiền: ");
		lblSubThanhTien.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblSubThanhTien.setBounds(782, 596, 90, 26);
		pMain.add(lblSubThanhTien);
		
		JLabel lblThanhTien = new JLabel("250 000 vnđ");
		lblThanhTien.setForeground(Color.RED);
		lblThanhTien.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 17));
		lblThanhTien.setBounds(882, 596, 98, 26);
		pMain.add(lblThanhTien);
		
		JLabel lblSubPhuThu = new JLabel("Giờ ra :");
		lblSubPhuThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubPhuThu.setBounds(573, 298, 56, 21);
		pMain.add(lblSubPhuThu);
		
		JLabel lblTenPhuThu = new JLabel("20h : 30");
		lblTenPhuThu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTenPhuThu.setBounds(627, 298, 56, 21);
		pMain.add(lblTenPhuThu);
		
		JPanel pLine = new JPanel();
		pLine.setBackground(Color.BLACK);
		pLine.setBounds(763, 582, 274, 3);
		pMain.add(pLine);
		
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
		if(o.equals(btnQuayLai)) {
			setVisible(false);
			frm.setVisible(true);
			
			
		}
		
	}
}
