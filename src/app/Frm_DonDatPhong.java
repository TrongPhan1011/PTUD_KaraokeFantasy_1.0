package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.border.TitledBorder;

public class Frm_DonDatPhong extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV, sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JLabel lblQLDDP, lblTim, lblTenKH, lblLoaiKH, lblNgayDen, lblSDT, lblGioDen, lblTinhTrangDDP, lblDiaChi, lblChonPhong, lblBackGround;
	private JTextField txtTim, txtTenKH, txtSDT, txtGioDen;
	private JTextArea txtDiaChi;
	private JButton btnTim, btnThemDDP, btnSuaDDP, btnXoaDDP, btnLamMoiDDP;
	private Date dNow;
	private LocalDate now;
	private int ngay, thang, nam;
	
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
		
		//lblDDP
		lblQLDDP = new JLabel("Quản lý đơn đặt phòng");
		lblQLDDP.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLDDP.setBounds(37, 10, 255, 33);
		pMain.add(lblQLDDP);
		
		//lblTim
		lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 13, 90, 35);
		pMain.add(lblTim);
		
		//txtTim
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTim.setColumns(10);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(474, 12, 281, 33);
		pMain.add(txtTim);
		
		//btnTim
		btnTim = new FixButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		
		//lblTenKH
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTenKH.setBounds(47, 65, 138, 19);
		pMain.add(lblTenKH);
		
		//txtTenKH
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		txtTenKH.setBounds(190, 58, 175, 28);
		pMain.add(txtTenKH);
		
		//lblLoaiKH
		lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLoaiKH.setBounds(47, 105, 138, 19);
		pMain.add(lblLoaiKH);
		
		//cbbLoaiKH
		JComboBox<Object> cbbLoaiKH = new JComboBox<Object>(new Object[] {"Thường", "Thành viên", "VIP"});
		cbbLoaiKH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbLoaiKH.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbLoaiKH.setBackground(Color.WHITE);
		cbbLoaiKH.setBounds(190, 98, 175, 27);
		pMain.add(cbbLoaiKH);
		
		//lblNgayDen
		lblNgayDen = new JLabel("Ngày đến:");
		lblNgayDen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgayDen.setBounds(413, 65, 74, 19);
		pMain.add(lblNgayDen);
		
		//modelNgayDen
		SqlDateModel modelNgayDen=new SqlDateModel();
		modelNgayDen.setSelected(true);
		Properties p=new Properties();
		p.put("text.day", p);
		p.put("text.month", p);
		p.put("text.year", p);
		JDatePanelImpl panel=new JDatePanelImpl(modelNgayDen, p);
		JDatePickerImpl datePicker=new JDatePickerImpl(panel, new AbstractFormatter() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				return "";
			}
		});
		datePicker.getJFormattedTextField().setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setFont(new Font("SansSerif", Font.PLAIN, 15));
		datePicker.getJFormattedTextField().setText(" ... - ... - .....");
		
		datePicker.setBounds(496, 62, 120, 22);
		datePicker.setTextEditable(true);
		
		pMain.add(datePicker);
		
		//lblSDT
		lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(413, 105, 46, 19);
		pMain.add(lblSDT);
		
		//txtSDT
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSDT.setBounds(496, 96, 120, 28);
		pMain.add(txtSDT);
		
		//lblGioDen
		lblGioDen = new JLabel("Giờ đến:");
		lblGioDen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioDen.setBounds(677, 65, 68, 19);
		pMain.add(lblGioDen);
		
		//txtGioDen
		txtGioDen = new JTextField();
		txtGioDen.setColumns(10);
		txtGioDen.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtGioDen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtGioDen.setBounds(755, 58, 74, 28);
		pMain.add(txtGioDen);
		
		//lblDiaChi
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiaChi.setBounds(677, 105, 61, 20);
		pMain.add(lblDiaChi);
		
		//txtDiaChi
		txtDiaChi = new JTextArea();
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDiaChi.setBounds(755, 96, 452, 28);
		pMain.add(txtDiaChi);
		
		//lblTinhTrangDDP
		lblTinhTrangDDP = new JLabel("Tình trạng đơn đặt phòng:");
		lblTinhTrangDDP.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTinhTrangDDP.setBounds(860, 65, 189, 19);
		pMain.add(lblTinhTrangDDP);
		
		//cbbTinhTrangDDP
		JComboBox<Object> cbbTinhTrangDDP = new JComboBox<Object>(new Object[]{"Đã nhận phòng", "Chờ nhận phòng", "Hủy"});
		cbbTinhTrangDDP.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbTinhTrangDDP.setBorder(new LineBorder(new Color(114, 23 ,153), 1, true));
		cbbTinhTrangDDP.setBackground(Color.WHITE);
		cbbTinhTrangDDP.setBounds(1059, 60, 148, 27);
		pMain.add(cbbTinhTrangDDP);
		
		//lblChonPhong
		lblChonPhong = new JLabel("Chọn phòng:");
		lblChonPhong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChonPhong.setBounds(47, 145, 98, 19);
		pMain.add(lblChonPhong);
		
		//bangthongtinPhong
		JScrollPane scrollPaneChonPhong = new JScrollPane();
		scrollPaneChonPhong.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneChonPhong.setBackground(new Color(164, 44, 167));
		scrollPaneChonPhong.setBounds(190, 142, 709, 131);
		pMain.add(scrollPaneChonPhong);
		
		String colPhong[] = {"Mã phòng", "Mã loại phòng", "Loại phòng", "Giá phòng", "Tình trạng phòng"};
		DefaultTableModel modelPhong=new DefaultTableModel(colPhong, 0);
		
		JTable tablePhong=new JTable(modelPhong);
		JTableHeader tbHeaderPhong = tablePhong.getTableHeader();
		tbHeaderPhong.setBackground(new Color(164, 44, 167));
		tbHeaderPhong.setForeground(Color.white);
		tbHeaderPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tablePhong.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablePhong.getColumnModel().getColumn(1).setPreferredWidth(30);
		tablePhong.getColumnModel().getColumn(2).setPreferredWidth(35);
		tablePhong.getColumnModel().getColumn(3).setPreferredWidth(40);
		tablePhong.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		tablePhong.setBackground(Color.white);
		tablePhong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tablePhong.setRowHeight(30);
		tablePhong.setShowHorizontalLines(true);
		tablePhong.setShowGrid(true);
//		tablePhong.setOpaque(false);
		tablePhong.setSelectionBackground(new Color(164, 44, 167, 30));
		tablePhong.setSelectionForeground(new Color(114, 23, 153));
		scrollPaneChonPhong.setViewportView(tablePhong);
		
		//demo data phong
		modelPhong.addRow(new Object[] {"123", "123", "1"});
		modelPhong.addRow(new Object[] {"123", "123"});
		modelPhong.addRow(new Object[] {"123", "123"});
		modelPhong.addRow(new Object[] {"123", "123"});
		modelPhong.addRow(new Object[] {"123", "123"});
		modelPhong.addRow(new Object[] {"123", "123", "6"});
		
		//ngay thang nam lap DDP
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		dNow = new Date(nam, thang, ngay);
		
		//btnthem,sua,xoa,lammoiDDP
		btnThemDDP = new FixButton("Thêm");
		btnThemDDP.setForeground(Color.white);
		btnThemDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemDDP.setBackground(new Color(114, 23, 153));
		btnThemDDP.setBounds(956, 160, 110, 35);
		Image imgThemDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemDDP = imgThemDDP.getScaledInstance(25, 25, 0);
		btnThemDDP.setIcon(new ImageIcon(resizeImgThemDDP));
		pMain.add(btnThemDDP);
		
		btnSuaDDP = new FixButton("Sửa");
		btnSuaDDP.setForeground(Color.white);
		btnSuaDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaDDP.setBackground(new Color(114, 23, 153));
		btnSuaDDP.setBounds(1097, 160, 110, 35);
		Image imgSuaDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaDDP = imgSuaDDP.getScaledInstance(25, 25, 0);
		btnSuaDDP.setIcon(new ImageIcon(resizeImgSuaDDP));
		pMain.add(btnSuaDDP);
		
		btnXoaDDP = new FixButton("Hủy");
		btnXoaDDP.setForeground(Color.white);
		btnXoaDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaDDP.setBackground(new Color(114, 23, 153));
		btnXoaDDP.setBounds(956, 218, 110, 35);
		Image imgXoaDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaDDP = imgXoaDDP.getScaledInstance(25, 25, 0);
		btnXoaDDP.setIcon(new ImageIcon(resizeImgXoaDDP));
		pMain.add(btnXoaDDP);
		
		btnLamMoiDDP = new FixButton("Làm mới");
		btnLamMoiDDP.setForeground(Color.white);
		btnLamMoiDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiDDP.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiDDP.setBackground(new Color(114, 23, 153));
		btnLamMoiDDP.setBounds(1097, 218, 110, 35);
		Image imgLamMoiDDP = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiDDP = imgLamMoiDDP.getScaledInstance(25, 25, 0);
		btnLamMoiDDP.setIcon(new ImageIcon(resizeImgLamMoiDDP));
		pMain.add(btnLamMoiDDP);
		
		//sapxep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(207, 195, 237));
		pSapXep.setBounds(302, 280, 685, 50);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		JComboBox<Object> cbbSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cbbSapXep.setBounds(60, 14, 102, 28);
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbSapXep.setBackground(Color.WHITE);
		pSapXep.add(cbbSapXep);
		
		JRadioButton radTheoMaPhong = new JRadioButton("Theo mã phòng");
		radTheoMaPhong.setBounds(188, 16, 133, 27);
		radTheoMaPhong.setSelected(true);
		radTheoMaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoMaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoMaPhong);
		
		JRadioButton radTheoLoaiPhong = new JRadioButton("Theo loại phòng");
		radTheoLoaiPhong.setBounds(342, 16, 139, 27);
		radTheoLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoLoaiPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoLoaiPhong);
		
		JRadioButton radTheoGiaPhong = new JRadioButton("Theo giá phòng");
		radTheoGiaPhong.setBounds(500, 16, 135, 27);
		radTheoGiaPhong.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoGiaPhong.setBackground(new Color(207, 195, 237));
		pSapXep.add(radTheoGiaPhong);
		
		ButtonGroup bgRad=new ButtonGroup();
		bgRad.add(radTheoMaPhong);
		bgRad.add(radTheoLoaiPhong);
		bgRad.add(radTheoGiaPhong);
		radTheoMaPhong.setSelected(true);
		
		//bangthongtinDDP
		JScrollPane scrollPaneDDP = new JScrollPane();
		scrollPaneDDP.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneDDP.setBackground(new Color(164, 44, 167));
		scrollPaneDDP.setBounds(26, 336, 1212, 259);
		pMain.add(scrollPaneDDP);
		
		String colDDP[] = {"Mã DDP", "Mã KH", "Tên KH", "Ngày đến", "Giờ đến", "Mã phòng", "Mã NV lập", "Số lượng", "Ngày lập", "Tình trạng phòng"};
		DefaultTableModel modelDDP=new DefaultTableModel(colDDP, 0);
		
		JTable tableDDP=new JTable(modelDDP);
		JTableHeader tbHeaderDDP = tableDDP.getTableHeader();
		tbHeaderDDP.setBackground(new Color(164, 44, 167));
		tbHeaderDDP.setForeground(Color.white);
		tbHeaderDDP.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableDDP.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableDDP.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableDDP.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableDDP.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableDDP.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableDDP.getColumnModel().getColumn(5).setPreferredWidth(30);
		tableDDP.getColumnModel().getColumn(6).setPreferredWidth(30);
		tableDDP.getColumnModel().getColumn(7).setPreferredWidth(20);
		tableDDP.getColumnModel().getColumn(8).setPreferredWidth(40);
		tableDDP.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		tableDDP.setBackground(Color.white);
		tableDDP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableDDP.setRowHeight(30);
		tableDDP.setShowHorizontalLines(true);
		tableDDP.setShowGrid(true);
//		tableDDP.setOpaque(false);
		tableDDP.setSelectionBackground(new Color(164, 44, 167, 30));
		tableDDP.setSelectionForeground(new Color(114, 23, 153));
		scrollPaneDDP.setViewportView(tableDDP);
		
		//demo data DDP
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		modelDDP.addRow(new Object[] {"123","123"});
		
		//background
		lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
