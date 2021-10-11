package app;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import com.mindfusion.drawing.Colors;

import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;

public class Frm_NhanVien extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThemNV, btnSuaNV, btnXoaNV, btnLamMoiNV;
	private Panel pMain;
	private String sHeaderTenNV, sHeaderMaNV;
	private Date dNgayHienTai;
	private JTextField txtTim, txtHoTen, txtSDT, txtCccd;
	
	public Panel getPanel() {
		return pMain;
	}
	
	public  Frm_NhanVien(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
	//lblQLNV
		JLabel lblQLNV = new JLabel("Quản lý nhân viên");
		lblQLNV.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLNV.setBounds(37, 10, 255, 33);
		pMain.add(lblQLNV);
		
	//lblTim
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 13, 90, 35);
		pMain.add(lblTim);
		
	//txtTim
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTim.setColumns(10);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(474, 12, 281, 33);
		pMain.add(txtTim);
		
	//btnTim
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
		
	//imgNhac1
		JLabel lblNhac1=new JLabel("");
		lblNhac1.setBounds(25, 120, 120, 135);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
	//imgNhac2
		JLabel lblNhac2=new JLabel("");
		lblNhac2.setBounds(1100, 120, 105, 159);
		Image imgNhac2 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac2.png");
		Image resizeNhac2 = imgNhac2.getScaledInstance(lblNhac2.getWidth(), lblNhac2.getHeight(), 0);
		lblNhac2.setIcon(new ImageIcon(resizeNhac2));
		pMain.add(lblNhac2);
		
	//thongtinNV
		//hoten
		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHoTen.setBounds(165, 65, 72, 19);
		pMain.add(lblHoTen);
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtHoTen.setBounds(258, 64, 189, 25);
		pMain.add(txtHoTen);
		
		//sdt
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSDT.setBounds(165, 107, 46, 19);
		pMain.add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSDT.setBounds(258, 103, 189, 25);
		pMain.add(txtSDT);
		
		//diachi
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiaChi.setBounds(165, 142, 72, 20);
		pMain.add(lblDiaChi);
		JTextArea txtDiaChi = new JTextArea();
		txtDiaChi.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDiaChi.setBounds(258, 142, 189, 37);
		pMain.add(txtDiaChi);
		
		//chucvu
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblChucVu.setBounds(512, 65, 124, 19);
		pMain.add(lblChucVu);
		JComboBox<Object> cbbChucVu = new JComboBox<Object>(new Object[] {"Quản lý", "Phục vụ", "Thu ngân"});
		cbbChucVu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbChucVu.setBackground(Color.WHITE);
		cbbChucVu.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbChucVu.setBounds(657, 61, 124, 25);
		pMain.add(cbbChucVu);
		
		//cccc
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCccd.setBounds(512, 103, 65, 19);
		pMain.add(lblCccd);
		txtCccd = new JTextField();
		txtCccd.setColumns(10);
		txtCccd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtCccd.setBounds(657, 98, 124, 25);
		pMain.add(txtCccd);
		
		//gioitinh
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGioiTinh.setBounds(512, 143, 72, 14);
		pMain.add(lblGioiTinh);
		JComboBox<Object> cbbGioiTinh = new JComboBox<Object>(new Object[] {"Nam", "Nữ"});
		cbbGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbGioiTinh.setBackground(Color.WHITE);
		cbbGioiTinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbGioiTinh.setBounds(657, 137, 65, 25);
		pMain.add(cbbGioiTinh);
		
		//ngaysinh
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNgaySinh.setBounds(859, 65, 75, 18);
		pMain.add(lblNgaySinh);
		
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
		datePicker.getJFormattedTextField().setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		datePicker.setBounds(964, 62, 120, 26);
		datePicker.setTextEditable(true);
		
		pMain.add(datePicker);
		

//		ftfNgaySinh.setBounds(964, 62, 100, 25);
//		ftfNgaySinh.setEditable(false);
//		pMain.add(ftfNgaySinh);
//		
		JButton btnLich=new JButton();
		btnLich.setBackground(Color.WHITE);
		btnLich.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		btnLich.setIcon(new ImageIcon("data/img/lich1.png"));
		btnLich.setBounds(1072, 62, 26, 25);
//		btnLich.setPreferredSize(new Dimension(26, 25));
//		pMain.add(btnLich);
		datePicker.add(btnLich);
		
//		btnLich.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});	
		
		//calamviec
		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCaLamViec.setBounds(859, 105, 90, 20);
		pMain.add(lblCaLamViec);
		JComboBox<Object> cbbCaLamViec = new JComboBox<Object>(new Object[] {"Ca 1", "Ca 2", "Ca 3"});
		cbbCaLamViec.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbCaLamViec.setBackground(Color.WHITE);
		cbbCaLamViec.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cbbCaLamViec.setBounds(964, 102, 65, 25);
		pMain.add(cbbCaLamViec);
		
		//btnthem,sua,xoa,resetNV
		btnThemNV = new FixButton("Thêm");
		btnThemNV.setForeground(Color.WHITE);
		btnThemNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThemNV.setBackground(new Color(114, 23, 153));
		btnThemNV.setBounds(374, 190, 110, 35);
		Image imgThemNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemNV = imgThemNV.getScaledInstance(25, 25, 0);
		btnThemNV.setIcon(new ImageIcon(resizeImgThemNV));
		pMain.add(btnThemNV);
		
		btnSuaNV = new FixButton("Sửa");
		btnSuaNV.setForeground(Color.WHITE);
		btnSuaNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSuaNV.setBackground(new Color(114, 23, 153));
		btnSuaNV.setBounds(515, 190, 110, 35);
		Image imgSuaNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaNV = imgSuaNV.getScaledInstance(25, 25, 0);
		btnSuaNV.setIcon(new ImageIcon(resizeImgSuaNV));
		pMain.add(btnSuaNV);
		
		btnXoaNV = new FixButton("Xóa");
		btnXoaNV.setForeground(Color.WHITE);
		btnXoaNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnXoaNV.setBackground(new Color(114, 23, 153));
		btnXoaNV.setBounds(653, 190, 110, 35);
		Image imgXoaNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaNV = imgXoaNV.getScaledInstance(25, 25, 0);
		btnXoaNV.setIcon(new ImageIcon(resizeImgXoaNV));
		pMain.add(btnXoaNV);
		
		btnLamMoiNV = new FixButton("Làm mới");
		btnLamMoiNV.setForeground(Color.WHITE);
		btnLamMoiNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoiNV.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoiNV.setBackground(new Color(114, 23, 153));
		btnLamMoiNV.setBounds(794, 190, 110, 35);
		Image imgLamMoiNV = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiNV = imgLamMoiNV.getScaledInstance(25, 25, 0);
		btnLamMoiNV.setIcon(new ImageIcon(resizeImgLamMoiNV));
		pMain.add(btnLamMoiNV);
		
		//sapxep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(220,210,239));
		pSapXep.setBounds(270, 236, 733, 69);
		pMain.add(pSapXep);
		
		JComboBox<Object> cbbSapXep = new JComboBox<Object>(new Object[] {"Tăng dần", "Giảm dần"});
		cbbSapXep.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbbSapXep.setBackground(Color.WHITE);
		cbbSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pSapXep.add(cbbSapXep);
		
		JRadioButton radTheoMaNV = new JRadioButton("Theo mã nhân viên");
		radTheoMaNV.setSelected(true);
		radTheoMaNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoMaNV.setBackground(new Color(220,210,239));
		pSapXep.add(radTheoMaNV);
		
		JRadioButton radTheoTenNV = new JRadioButton("Theo tên nhân viên");
		radTheoTenNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoTenNV.setBackground(new Color(220,210,239));
		pSapXep.add(radTheoTenNV);
		
		JRadioButton radTheoChucVuNV = new JRadioButton("Theo chức vụ nhân viên");
		radTheoChucVuNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		radTheoChucVuNV.setBackground(new Color(220,210,239));
		pSapXep.add(radTheoChucVuNV);
		
		ButtonGroup bgRad=new ButtonGroup();
		bgRad.add(radTheoMaNV);
		bgRad.add(radTheoTenNV);
		bgRad.add(radTheoChucVuNV);
		radTheoMaNV.setSelected(true);
		
		//bangthongtinNV
		JScrollPane scrollPaneNV = new JScrollPane();
		scrollPaneNV.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		scrollPaneNV.setBackground(new Color(164, 44, 167));
		scrollPaneNV.setBounds(22, 320, 1220, 266);
		pMain.add(scrollPaneNV);
		
		String col[] = {"Mã NV", "Họ và tên nhân viên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "CCCD", "Ca làm việc", "Lương"};
		DefaultTableModel modelNV = new DefaultTableModel(col, 0);
		
		JTable tableNV = new JTable(modelNV);
		JTableHeader tbHeader = tableNV.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tableNV.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableNV.getColumnModel().getColumn(1).setPreferredWidth(110);
		tableNV.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableNV.getColumnModel().getColumn(3).setPreferredWidth(20);
		tableNV.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableNV.getColumnModel().getColumn(8).setPreferredWidth(30);
		tableNV.getColumnModel().getColumn(9).setPreferredWidth(50);
//		tableNV.getColumnModel().getColumn(0).setPreferredWidth(55);
//		tableNV.getColumnModel().getColumn(0).setPreferredWidth(55);
//		tableNV.getColumnModel().getColumn(0).setPreferredWidth(55);
		
//		tableNV.setShowGrid(false);
//		tableNV.setShowHorizontalLines(false);
		tableNV.setBackground(Color.WHITE);
		tableNV.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableNV.setRowHeight(30);
		tableNV.setOpaque(false);
		tableNV.setSelectionBackground(new Color(164, 44, 167, 30));
		scrollPaneNV.setViewportView(tableNV);
		
		//demo data
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		modelNV.addRow(new Object[] {"123","123"});
		
		
		//background
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		Object  object = e.getSource();
//		if(object.equals(btnNewButton_1)) {
//			System.exit(0);
//		}
		
	}

}
