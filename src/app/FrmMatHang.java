package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mindfusion.drawing.Colors;

import connection.ConnectDB;
import dao.DAOLoaiMH;
import dao.DAOMatHang;
import dao.DAOPhatSinhMa;
import dao.Regex;
import entity.LoaiMatHang;
import entity.MatHang;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FrmMatHang extends JPanel implements ActionListener, MouseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pMain;
	private JTextField txtSoLuong;
	private JTextField txtTenMH;
	private JTextField txtDonGia;
	private JTable tblMH;
	private DefaultTableModel modelMatHang;
	private FixButton btnTim;
	private JTextField txtTim;
	private FixButton btnThemKH;
	private FixButton btnSuaMH;
	private FixButton btnXoaMH;
	private FixButton btnReset;
	private DAOMatHang daoMH;
	private DAOLoaiMH daoLMH;
	private JComboBox<String> cboLoaiMH;
	private DAOPhatSinhMa daoPhatSinhMa;
	private JComboBox<Object> cboSapXep;
	private ArrayList<LoaiMatHang> loaiMH;
	private JCheckBox chkTatCa;
	private JRadioButton rdoTheoGiaMH;
	private JRadioButton rdoTheoLoaiMH;
	private JRadioButton rdoTheoTenMH;
	private DecimalFormat dfK;
	private DecimalFormat dfVND;
	private MatHang mh;
	private Regex regex;
	
	public Panel getFrmMatHang() {
		return this.pMain;
	}
	public FrmMatHang(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 606);
		add(pMain);
		pMain.setLayout(null);
		
		regex = new Regex();
		daoMH = new DAOMatHang();
		daoLMH = new DAOLoaiMH();
		daoPhatSinhMa = new DAOPhatSinhMa();
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
//lblQLNV
		JLabel lblQLNV = new JLabel("Qu???n l?? m???t h??ng");
		lblQLNV.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLNV.setBounds(37, 10, 255, 33);
		pMain.add(lblQLNV);
//Tim Kiem
		//txtTim 
		txtTim = new JTextField();
		txtTim.setText("T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(452, 15, 396, 33);
		txtTim.addFocusListener(new FocusAdapter() {	
			//place holder
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTim.getText().equals("T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng")) {
					txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTim.setForeground(Color.BLACK);
					txtTim.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTim.getText().equals("")) {
					txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTim.setForeground(Colors.LightGray);
					txtTim.setText("T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng");
				}
			}
		});
		pMain.add(txtTim);
		//lblTim
		JLabel lblTim = new JLabel("T??m ki???m:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 15, 90, 35);
		pMain.add(lblTim);
				
		btnTim = new FixButton("T??m");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(870, 15, 98, 33);
		Image imgTim = Toolkit.getDefaultToolkit().getImage("data\\img\\iconKinhLup.png");
		Image resizeImgTim = imgTim.getScaledInstance(20, 20, 0);
		btnTim.setIcon(new ImageIcon(resizeImgTim));
		pMain.add(btnTim);
		
//imgNhac1
		JLabel lblNhac1=new JLabel("");
		lblNhac1.setBounds(25, 105, 120, 135);
		Image imgNhac1 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac1.png");
		Image resizeNhac1 = imgNhac1.getScaledInstance(lblNhac1.getWidth(), lblNhac1.getHeight(), 0);
		lblNhac1.setIcon(new ImageIcon(resizeNhac1));
		pMain.add(lblNhac1);
		
//imgNhac2
		JLabel lblNhac2=new JLabel("");
		lblNhac2.setBounds(1100, 105, 105, 159);
		Image imgNhac2 = Toolkit.getDefaultToolkit().getImage("data\\img\\IconNhac2.png");
		Image resizeNhac2 = imgNhac2.getScaledInstance(lblNhac2.getWidth(), lblNhac2.getHeight(), 0);
		lblNhac2.setIcon(new ImageIcon(resizeNhac2));
		pMain.add(lblNhac2);
//////////		
		JLabel lblTenMH = new JLabel("T??n m???t h??ng: ");
		lblTenMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTenMH.setBounds(217, 59, 102, 26);
		pMain.add(lblTenMH);
		
		txtTenMH = new JTextField();
		txtTenMH.setBackground(new Color(255, 255, 255));
		txtTenMH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenMH.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtTenMH.setBounds(329, 58, 310, 30);
		pMain.add(txtTenMH);
		txtTenMH.setColumns(30);
		
		//-----
		JLabel lblDonGia = new JLabel("Gi?? b??n:");
		lblDonGia.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDonGia.setBounds(217, 99, 130, 26);
		pMain.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setBackground(new Color(255, 255, 255));
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtDonGia.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtDonGia.setBounds(329, 98, 310, 30);
		pMain.add(txtDonGia);
		txtDonGia.setColumns(20);
//		//------
		JLabel lblSoluongMH = new JLabel("S??? l?????ng:");
		lblSoluongMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSoluongMH.setBounds(654, 59, 84, 26);
		pMain.add(lblSoluongMH);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(new Color(255, 255, 255));
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSoLuong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		txtSoLuong.setBounds(766, 58, 310, 30);
		pMain.add(txtSoLuong);
		txtSoLuong.setColumns(20);
		
		JLabel lblSubLMH = new JLabel("Lo???i m???t h??ng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(654, 99, 102, 26);
		pMain.add(lblSubLMH);
		
		cboLoaiMH = new JComboBox<String>();
		cboLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboLoaiMH.setBackground(Color.WHITE);
		cboLoaiMH.setBounds(766, 97, 310, 30);
		pMain.add(cboLoaiMH);
/////Buttons
		btnThemKH = new FixButton("Th??m");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemKH.setBackground(new Color(114, 43, 153));
		btnThemKH.setBounds(374, 150, 118, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		btnSuaMH = new FixButton("S???a");
		btnSuaMH.setForeground(Color.WHITE);
		btnSuaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaMH.setBackground(new Color(114, 43, 153));
		btnSuaMH.setBounds(537, 150, 118, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaMH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaMH);
		
		btnXoaMH = new FixButton("X??a");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaMH.setBackground(new Color(114, 43, 153));
		btnXoaMH.setBounds(702, 150, 125, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaMH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaMH);
		
		btnReset = new FixButton("L??m m???i");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnReset.setBackground(new Color(114, 43, 153));
		btnReset.setBounds(868, 150, 144, 35);
		Image imgLamMoiKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconReset.png");
		Image resizeImgLamMoiKH = imgLamMoiKH.getScaledInstance(25, 25, 0);
		btnReset.setIcon(new ImageIcon(resizeImgLamMoiKH));
		pMain.add(btnReset);
//SapXep
		JPanel pSapXep = new JPanel();
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "S???p x???p", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(207, 195, 237));
		pSapXep.setBounds(217, 195, 859, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		cboSapXep = new JComboBox<Object>(new Object[]{"T??ng d???n", "Gi???m d???n"});
		cboSapXep.setBounds(51, 12, 115, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.BOLD, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		pSapXep.add(cboSapXep);
		
		rdoTheoTenMH = new JRadioButton("Theo t??n m???t h??ng");
		rdoTheoTenMH.setBounds(312, 15, 170, 27);
		rdoTheoTenMH.setSelected(true);
		rdoTheoTenMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoTenMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoTenMH);
		
		rdoTheoLoaiMH = new JRadioButton("Theo lo???i m???t h??ng");
		rdoTheoLoaiMH.setBounds(518, 15, 170, 27);
		rdoTheoLoaiMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLoaiMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoLoaiMH);
		
		rdoTheoGiaMH = new JRadioButton("Theo gi?? ");
		rdoTheoGiaMH.setBounds(718, 15, 135, 27);
		rdoTheoGiaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoGiaMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoGiaMH);
		
		chkTatCa = new JCheckBox("T???t c??? ");
		chkTatCa.setFont(new Font("SansSerif", Font.BOLD, 14));
		chkTatCa.setBackground(new Color(207, 195, 237));
		chkTatCa.setBounds(201, 15, 135, 27);
		pSapXep.add(chkTatCa);
		
		ButtonGroup bgRdo=new ButtonGroup();
		bgRdo.add(rdoTheoTenMH);
		bgRdo.add(rdoTheoLoaiMH);
		bgRdo.add(rdoTheoGiaMH);
		bgRdo.add(chkTatCa);
		bgRdo.clearSelection();
//Table
		String mh [] = {"M?? MH","T??n m???t h??ng", "Lo???i MH", "S??? l?????ng", "Gi?? b??n"};
		modelMatHang = new DefaultTableModel(mh,0);
		
		tblMH = new JTable(modelMatHang);
		tblMH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblMH.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblMH.setShowHorizontalLines(true);
		tblMH.setShowGrid(true);
		tblMH.setBackground(Color.WHITE);
		tblMH.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tblMH.setSelectionBackground(new Color(164, 44, 167, 30));
		tblMH.setSelectionForeground(new Color(114, 23, 153));
		tblMH.setRowHeight(30);
		tblMH.setSelectionBackground(new Color(164, 44, 167,30));
		
		JTableHeader tbHeader = tblMH.getTableHeader();
		tbHeader.setBackground(new Color(164, 44, 167));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JScrollPane spMatHang = new JScrollPane(tblMH, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMatHang.setBounds(37, 249, 1194, 346);
		spMatHang.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
		spMatHang.setBackground(new Color(164, 44, 167));
		spMatHang.getHorizontalScrollBar();
		pMain.add(spMatHang);
		
		tblMH.getColumnModel().getColumn(0).setPreferredWidth(240);
		tblMH.getColumnModel().getColumn(1).setPreferredWidth(240);
		tblMH.getColumnModel().getColumn(2).setPreferredWidth(240);
		tblMH.getColumnModel().getColumn(3).setPreferredWidth(240);
		tblMH.getColumnModel().getColumn(4).setPreferredWidth(230);
		
		DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		tblMH.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
		tblMH.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblMH.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		spMatHang.setViewportView(tblMH);
///Background
		JLabel lblBackGround=new JLabel("");
		lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
		lblBackGround.setBounds(0, 0, 1281, 606);
		Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
		Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
		lblBackGround.setIcon(new ImageIcon(resizeBG));
		pMain.add(lblBackGround);
/// Load loai MH
		loaiMH = daoLMH.getAllLoaiMatHang();
		for(LoaiMatHang lmh : loaiMH) {
			cboLoaiMH.addItem(lmh.getTenLoaiMatHang());
		}
////Action, Mouse
		btnThemKH.addActionListener(this);
		btnXoaMH.addActionListener(this);
		btnTim.addActionListener(this);
		btnSuaMH.addActionListener(this);
		btnReset.addActionListener(this);
		
		tblMH.addMouseListener(this);
		
		rdoTheoGiaMH.addActionListener(this);
		rdoTheoLoaiMH.addActionListener(this);
		rdoTheoTenMH.addActionListener(this);
		
		chkTatCa.addActionListener(this);
		
		rdoTheoGiaMH.addActionListener(this);
		rdoTheoLoaiMH.addActionListener(this);
		rdoTheoTenMH.addActionListener(this);
		
		cboSapXep.addActionListener(this);
//Dinh dang thap phan, VND
		dfK = new DecimalFormat("###,###");
		dfVND = new DecimalFormat("###,### VND");
	}
	/**
	 * L???y d??? li???u t??? SQL n???p v??o b???ng
	 */
	public void loadTableMH() {
		ArrayList<MatHang> lsMH = daoMH.getDSMatHang();
		for(MatHang mh : lsMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), mh.getGiaMatHang() } );
		}
	}
	////dfK.format(Math.round(mh.getSoLuongMatHang())),dfVND.format(Math.round(mh.getGiaMatHang()))
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnReset)) {
			LamMoi();
		}else if(o.equals(btnThemKH)) {
			ThemMH();
		}else if(o.equals(btnXoaMH)) {
			XoaMH();
		}else if (o.equals(btnSuaMH)) {
			SuaMH();
		}else if(o.equals(chkTatCa)) {
			loadTableMH();
		}else if(o.equals(btnTim)) {
			timMH();
		}else if (cboSapXep.getSelectedItem() == "T??ng d???n") {
			if(o.equals(rdoTheoGiaMH)) {
				sortGiaTangDan(mh);
			}else if (o.equals(rdoTheoLoaiMH)) {
				clearTable();
				sortLMHTangDan(mh);
			}else if (o.equals(rdoTheoTenMH)) {
				sortTenMHTangDan(mh); 
			} 
		}else if (cboSapXep.getSelectedItem() == "Gi???m d???n") {
			if(o.equals(rdoTheoGiaMH)) {
				sortGiaGiamDan(mh);
			}else if (o.equals(rdoTheoLoaiMH)) {
				sortLMHGiamDan(mh);
			}else if (o.equals(rdoTheoTenMH)) {
				sortTenMHGiamDan(mh);
			} 
		}
	}
	/**
	 * X??a m???t h??ng kh???i table v?? SQL
	 */
	public void XoaMH() {
		if (tblMH.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Vui l??ng ch???n m???t h??ng c???n x??a");
		}else {
			int r = tblMH.getSelectedRow();
			if(r > 0) {
			int del = JOptionPane.showConfirmDialog(null, "B???n ch???c ch???n mu???n x??a? ", "Th??ng b??o", JOptionPane.YES_NO_OPTION);
			String maMH = modelMatHang.getValueAt(r, 0).toString();
			if(del == JOptionPane.YES_OPTION) {
				if(daoMH.XoaMH(maMH))
				modelMatHang.removeRow(r);
				}
			}
		}
	}
	/**
	 * Th??m m???t h??ng v??o table v?? SQL
	 */
	public void ThemMH() {
		String maMH = daoPhatSinhMa.getMaMH();
		String tenMH = txtTenMH.getText();
		String loaiMH = cboLoaiMH.getSelectedItem().toString();
		String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
		int soluong = Integer.parseInt(txtSoLuong.getText());
		double dongia = Double.parseDouble(txtDonGia.getText());
		MatHang mh = new MatHang(maMH, tenMH, soluong, dongia, new LoaiMatHang(maLMH));
		try {
			daoMH.ThemMH(mh);
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Th??m m???t h??ng th???t b???i!");
		}
		LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
		modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), mh.getGiaMatHang() } );
		JOptionPane.showMessageDialog(this, "Th??m m???t h??ng th??nh c??ng!");
	}
	/**
	 * S???a th??ng tin m???t h??ng
	 */
	public void SuaMH() {
		int row = tblMH.getSelectedRow();
		try {
			String maMH = (String) tblMH.getValueAt(row, 0);
			String tenMH = txtTenMH.getText();
			String loaiMH = cboLoaiMH.getSelectedItem().toString();
			String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
			int soluong = Integer.parseInt(txtSoLuong.getText());
			double dongia = Double.parseDouble(txtDonGia.getText());
			MatHang mh = new MatHang(maMH, tenMH, soluong, dongia, new LoaiMatHang(maLMH));  
			daoMH.updateMH(mh);
			clearTable();
			loadTableMH();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "M?? m???t h??ng kh??ng t???n t???i.");
		}	
		
		JOptionPane.showMessageDialog(this, "C???p  nh???t m???t h??ng th??nh c??ng! ");
	}
	/**
	 * X??a b???ng
	 */
	private void clearTable() {
		while(tblMH.getRowCount() > 0) {
			modelMatHang.removeRow(0);
		}
	}
	/**
	 * L??m m???i d??? li???u nh???p
	 */
	private void LamMoi() {
		txtTenMH.setText("");
			txtDonGia.setText("");
			txtSoLuong.setText("");
			txtTenMH.requestFocus();
	}
	private void sortTenMHTangDan(MatHang mh) {
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.getDSMatHang();
		Collections.sort(lstMH, new Comparator<MatHang>() {

			@Override
			public int compare(MatHang o1, MatHang o2) {
				return o1.getTenMatHang().compareTo(o2.getTenMatHang());
			}
		});
		for (MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	private void sortTenMHGiamDan(MatHang mh) {
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.getDSMatHang();
		Collections.sort(lstMH, new Comparator<MatHang>() {

			@Override
			public int compare(MatHang o1, MatHang o2) {
				return o2.getTenMatHang().compareTo(o1.getTenMatHang());
			}
		});
		for (MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	private void sortLMHGiamDan(MatHang mh){
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.sortLMH("DESC");
		for(MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	
	private void sortLMHTangDan(MatHang mh){
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.sortLMH("ASC");
		for(MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	/**
	 *	
	 * @param mh
	 */
	private void sortGiaTangDan(MatHang mh){
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.sortGia("ASC");
		for(MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	private void sortGiaGiamDan(MatHang mh){
		clearTable();
		ArrayList<MatHang> lstMH = daoMH.sortGia("DESC");
		for(MatHang infoMH : lstMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(infoMH.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {infoMH.getMaMatHang(), infoMH.getTenMatHang(), lMH.getTenLoaiMatHang(), infoMH.getSoLuongMatHang(), infoMH.getGiaMatHang() } );
		}
	}
	private void timMH() {
		String info = txtTim.getText().toLowerCase().trim();
		ArrayList<MatHang> mh1 = daoMH.getTenMH(info);
		ArrayList<MatHang> mh2 = daoMH.getLMH(info);
		if(!info.equals("") && !info.equals("T??m m???t h??ng theo t??n m???t h??ng, lo???i m???t h??ng")) {
			if(regex.regexTenMH(txtTim)) {
				try {
					loadTenMH(mh1);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y t??n m???t h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
			}if(regex.regexTimKiemLoaiMatHang(txtTim)) {
				try {
					loadLoaiMH(mh2);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y lo???i m???t h??ng!", "Th??ng b??o", JOptionPane.OK_OPTION);
				}
			}
		}else
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p th??ng tin t??m ki???m!", "Th??ng b??o", JOptionPane.WARNING_MESSAGE);
	}
	
	public void loadTenMH(ArrayList<MatHang> mh1) {
		clearTable();
		ArrayList<MatHang> lsMH = daoMH.getTenMH(txtTim.getText());
		for(MatHang mh : lsMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), mh.getGiaMatHang() } );
		}
	}
	
	public void loadLoaiMH(ArrayList<MatHang> mh1) {
		clearTable();
		String maLoai = daoLMH.getMaLoaiMHTheoTen(txtTim.getText());
		ArrayList<MatHang> lsMH = daoMH.getLMH(maLoai);
		for(MatHang mh : lsMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), mh.getSoLuongMatHang(), mh.getGiaMatHang() } );
		}
	}
	
	/**
	 * S??? ki???n click chu???t
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(tblMH)) {
			int row = tblMH.getSelectedRow();	
			txtTenMH.setText(modelMatHang.getValueAt(row, 1).toString());
			txtSoLuong.setText(modelMatHang.getValueAt(row, 3).toString());
			txtDonGia.setText(modelMatHang.getValueAt(row, 4).toString());
			cboLoaiMH.setSelectedItem(modelMatHang.getValueAt(row, 2).toString());
		
		}	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
