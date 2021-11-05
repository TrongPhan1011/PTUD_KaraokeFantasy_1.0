package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.ConnectDB;
import dao.DAOLoaiMH;
import dao.DAOMatHang;
import dao.DAOPhatSinhMa;
import entity.LoaiMatHang;
import entity.MatHang;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	private JRadioButton rdoTatCa;
	private JRadioButton rdoTheoGiaMH;
	private JRadioButton rdoTheoLoaiMH;
	private JRadioButton rdoTheoMaMH;
	private DecimalFormat dfK;
	private DecimalFormat dfVND;
	
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
		
		daoMH = new DAOMatHang();
		daoLMH = new DAOLoaiMH();
		daoPhatSinhMa = new DAOPhatSinhMa();
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
//lblQLNV
		JLabel lblQLNV = new JLabel("Quản lý mặt hàng");
		lblQLNV.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQLNV.setBounds(37, 10, 255, 33);
		pMain.add(lblQLNV);
//Tim Kiem
		txtTim = new JTextField();
		txtTim.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTim.setColumns(10);
		txtTim.setBorder(new LineBorder(new Color(114, 23 ,153), 2, true));
		txtTim.setBounds(474, 15, 281, 33);
		pMain.add(txtTim);
		//lblTim
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(374, 15, 90, 35);
		pMain.add(lblTim);
				
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
		btnTim.setBounds(786, 15, 98, 33);
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
		JLabel lblTenMH = new JLabel("Tên mặt hàng: ");
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
		JLabel lblDonGia = new JLabel("Giá bán:");
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
		JLabel lblSoluongMH = new JLabel("Số lượng:");
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
		
		JLabel lblSubLMH = new JLabel("Loại mặt hàng: ");
		lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubLMH.setBounds(654, 99, 102, 26);
		pMain.add(lblSubLMH);
		
		cboLoaiMH = new JComboBox<String>();
		cboLoaiMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboLoaiMH.setBackground(Color.WHITE);
		cboLoaiMH.setBounds(766, 97, 310, 30);
		pMain.add(cboLoaiMH);
/////Buttons
		btnThemKH = new FixButton("Thêm");
		btnThemKH.setForeground(Color.WHITE);
		btnThemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThemKH.setBackground(new Color(114, 43, 153));
		btnThemKH.setBounds(374, 150, 118, 35);
		Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
		Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
		btnThemKH.setIcon(new ImageIcon(resizeImgThemKH));
		pMain.add(btnThemKH);
		
		btnSuaMH = new FixButton("Sửa");
		btnSuaMH.setForeground(Color.WHITE);
		btnSuaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSuaMH.setBackground(new Color(114, 43, 153));
		btnSuaMH.setBounds(537, 150, 118, 35);
		Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
		Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
		btnSuaMH.setIcon(new ImageIcon(resizeImgSuaKH));
		pMain.add(btnSuaMH);
		
		btnXoaMH = new FixButton("Xóa");
		btnXoaMH.setForeground(Color.WHITE);
		btnXoaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoaMH.setBackground(new Color(114, 43, 153));
		btnXoaMH.setBounds(702, 150, 125, 35);
		Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
		Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
		btnXoaMH.setIcon(new ImageIcon(resizeImgXoaKH));
		pMain.add(btnXoaMH);
		
		btnReset = new FixButton("Làm mới");
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
		pSapXep.setBorder(new TitledBorder(new LineBorder(new Color(114, 23 ,153), 1, true), "Sắp xếp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pSapXep.setBackground(new Color(207, 195, 237));
		pSapXep.setBounds(217, 195, 859, 47);
		pMain.add(pSapXep);
		pSapXep.setLayout(null);
		
		cboSapXep = new JComboBox<Object>(new Object[]{"Tăng dần", "Giảm dần"});
		cboSapXep.setBounds(51, 12, 115, 28);
		cboSapXep.setFont(new Font("SansSerif", Font.BOLD, 15));
		cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboSapXep.setBackground(Color.WHITE);
		pSapXep.add(cboSapXep);
		
		rdoTheoMaMH = new JRadioButton("Theo tên mặt hàng");
		rdoTheoMaMH.setBounds(312, 15, 170, 27);
		rdoTheoMaMH.setSelected(true);
		rdoTheoMaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoMaMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoMaMH);
		
		rdoTheoLoaiMH = new JRadioButton("Theo loại mặt hàng");
		rdoTheoLoaiMH.setBounds(518, 15, 170, 27);
		rdoTheoLoaiMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoLoaiMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoLoaiMH);
		
		rdoTheoGiaMH = new JRadioButton("Theo giá ");
		rdoTheoGiaMH.setBounds(718, 15, 135, 27);
		rdoTheoGiaMH.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTheoGiaMH.setBackground(new Color(207, 195, 237));
		pSapXep.add(rdoTheoGiaMH);
		
		rdoTatCa = new JRadioButton("Tất cả ");
		rdoTatCa.setFont(new Font("SansSerif", Font.BOLD, 14));
		rdoTatCa.setBackground(new Color(207, 195, 237));
		rdoTatCa.setBounds(201, 15, 135, 27);
		pSapXep.add(rdoTatCa);
		
		ButtonGroup bgRdo=new ButtonGroup();
		bgRdo.add(rdoTheoMaMH);
		bgRdo.add(rdoTheoLoaiMH);
		bgRdo.add(rdoTheoGiaMH);
		bgRdo.add(rdoTatCa);
		bgRdo.clearSelection();
//Table
		String mh [] = {"Mã MH","Tên mặt hàng", "Loại MH", "Số lượng", "Giá bán"};
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
		rdoTheoMaMH.addActionListener(this);
		rdoTatCa.addActionListener(this);
//Dinh dang thap phan, VND
		dfK = new DecimalFormat("###,###");
		dfVND = new DecimalFormat("###,### VND");
	}
	/**
	 * Lấy dữ liệu từ SQL nạp vào bảng
	 */
	public void loadTableMH() {
		ArrayList<MatHang> lsMH = daoMH.getDSMatHang();
		for(MatHang mh : lsMH) {
			LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
			modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), dfK.format(Math.round(mh.getSoLuongMatHang())),dfVND.format(Math.round(mh.getGiaMatHang())) } );
		}
	}
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
		}else if(o.equals(rdoTatCa)) {
			loadTableMH();
		}	
	}
	/**
	 * Xóa mặt hàng khỏi table và SQL
	 */
	public void XoaMH() {
		if (tblMH.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn mặt hàng cần xóa");
		}else {
			int r = tblMH.getSelectedRow();
			if(r > 0) {
			int del = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa? ", "Thông báo", JOptionPane.YES_NO_OPTION);
			String maMH = modelMatHang.getValueAt(r, 0).toString();
			if(del == JOptionPane.YES_OPTION) {
				if(daoMH.XoaMH(maMH))
				modelMatHang.removeRow(r);
				}
			}
		}
	}
	/**
	 * Thêm mặt hàng vào table và SQL
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
			JOptionPane.showMessageDialog(this, "Thêm mặt hàng thất bại!");
		}
		LoaiMatHang lMH = daoLMH.getLoaiMHTheoMaLoai(mh.getLoaiMatHang().getMaLoaiMatHang());
		modelMatHang.addRow(new Object[] {mh.getMaMatHang(), mh.getTenMatHang(), lMH.getTenLoaiMatHang(), dfK.format(Math.round(mh.getSoLuongMatHang())),dfVND.format(Math.round(mh.getGiaMatHang())) } );
		JOptionPane.showMessageDialog(this, "Thêm mặt hàng thành công!");
	}
	/**
	 * Sửa thông tin mặt hàng
	 */
	public void SuaMH() {
		String maMH = daoPhatSinhMa.getMaMH();
		String tenMH = txtTenMH.getText();
		String loaiMH = cboLoaiMH.getSelectedItem().toString();
		String maLMH = daoLMH.getMaLoaiMHTheoTen(loaiMH);
		int soluong = Integer.parseInt(txtSoLuong.getText());
		double dongia = Double.parseDouble(txtDonGia.getText());
		MatHang mh = new MatHang(maMH, tenMH, soluong, dongia, new LoaiMatHang(maLMH));  
		try {
			daoMH.updateMH(mh);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Mã mặt hàng không tồn tại.");
		}	
		clearTable();
		loadTableMH();
		JOptionPane.showMessageDialog(this, "Cập nhật mặt hàng thành công! ");
	}
	/**
	 * Xóa bảng
	 */
	private void clearTable() {
		while(tblMH.getRowCount() > 0) {
			modelMatHang.removeRow(0);
		}
	}
	/**
	 * Làm mới dữ liệu nhập
	 */
	private void LamMoi() {
		txtTenMH.setText("");
			txtDonGia.setText("");
			txtSoLuong.setText("");
			txtTenMH.requestFocus();
	}
	/**
	 * Sự kiện click chuột
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
