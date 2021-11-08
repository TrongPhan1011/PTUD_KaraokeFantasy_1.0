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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import dao.DAOLoaiPhong;
import dao.DAOMatHang;
import dao.DAOPhong;
import entity.KhachHang;
import entity.LoaiKH;
import entity.LoaiMatHang;
import entity.LoaiPhong;
import entity.Phong;


public class FrmPhong extends JPanel implements ActionListener, MouseListener, ItemListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sHeaderMaNV;
	private String sHeaderTenNV;
	private Panel pMain;
	private Date dNgayHienTai;
	private JTextField txtTK;
	private JTextField txtTenP;
	private JTextField txtGiaPhong;
	private JButton btnTim;
	private JButton btnThemP;
	private JButton btnXoaP;
	private JButton btnSuaP;
	private JButton btnReset;
	private JComboBox<String> cboLoaiP;
	private JComboBox<String> cboTinhTrangP;
	private JComboBox<String> cboSapXep;
	private JRadioButton rdoTheoMaP;
	private JRadioButton rdoTheoLoaiP;
	private JRadioButton rdoTheoGiaP;
	private JCheckBox chkAll = new JCheckBox("Tất cả");
	private JTable tblPhong;
	private DefaultTableModel modelPhong;
	private DAOPhong daoPhong;
	private DAOLoaiPhong daoLoaiP;
	private DecimalFormat dfGiaP=new DecimalFormat("###,###");
	
	
	public Panel getFrmPhong() {
		return this.pMain;
	}
	public FrmPhong(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		
		//Khai bao dao
		 daoPhong = new DAOPhong();
		 daoLoaiP = new DAOLoaiPhong();
		
		//giao dien
		setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1281, 984);
		add(pMain);
		pMain.setLayout(null);
		
		JLabel lblQuanLyKH = new JLabel("Quản lý phòng");
		lblQuanLyKH.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblQuanLyKH.setBounds(31, 11, 251, 33);
		pMain.add(lblQuanLyKH);

		// lblTim
		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(310, 13, 90, 35);
		pMain.add(lblTim);

		// txtTK
		txtTK = new JTextField();
		txtTK.setText("Tìm theo mã phòng, loại phòng, tình trạng phòng");
		txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTK.setForeground(Colors.LightGray);
		txtTK.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTK.setBounds(400, 12, 526, 33);
		txtTK.addFocusListener(new FocusAdapter() { // place holder
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTK.getText().equals("Tìm theo mã phòng, loại phòng, tình trạng phòng")) {
					txtTK.setText("");
					txtTK.setFont(new Font("SansSerif", Font.PLAIN, 15));
					txtTK.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTK.getText().equals("")) {
					txtTK.setFont(new Font("SansSerif", Font.ITALIC, 15));
					txtTK.setText("Tìm theo mã phòng, loại phòng, tình trạng phòng");
					txtTK.setForeground(Colors.LightGray);
				}
			}
		});
		pMain.add(txtTK);

		// btnTim
		btnTim = new FixButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(942, 11, 98, 33);
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

				JLabel lblTenPhong = new JLabel("Tên phòng: ");
				lblTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 15));
				lblTenPhong.setBounds(217, 59, 102, 26);
				pMain.add(lblTenPhong);
				
				txtTenP = new JTextField();
				txtTenP.setBackground(new Color(255, 255, 255));
				txtTenP.setFont(new Font("SansSerif", Font.PLAIN, 14));
				txtTenP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
				txtTenP.setBounds(329, 58, 310, 30);
				pMain.add(txtTenP);
				txtTenP.setColumns(30);
				
				//-----
				JLabel lblGiaP = new JLabel("Giá phòng:");
				lblGiaP.setFont(new Font("SansSerif", Font.PLAIN, 15));
				lblGiaP.setBounds(217, 99, 130, 26);
				pMain.add(lblGiaP);
				
				txtGiaPhong = new JTextField();
				txtGiaPhong.setBackground(new Color(255, 255, 255));
				txtGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 14));
				txtGiaPhong.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
				txtGiaPhong.setBounds(329, 98, 310, 30);
				pMain.add(txtGiaPhong);
				txtGiaPhong.setColumns(20);
//				//------
				JLabel lblLoaiP = new JLabel("Loại phòng:");
				lblLoaiP.setFont(new Font("SansSerif", Font.PLAIN, 15));
				lblLoaiP.setBounds(654, 59, 84, 26);
				pMain.add(lblLoaiP);
				
				cboLoaiP = new JComboBox<String>();
				cboLoaiP.setBackground(new Color(255, 255, 255));
				cboLoaiP.setFont(new Font("SansSerif", Font.PLAIN, 14));
				cboLoaiP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
				cboLoaiP.setBounds(766, 58, 310, 30);
				pMain.add(cboLoaiP);
				//txtSoLuong.setColumns(20);
				
				JLabel lblSubLMH = new JLabel("Tình trạng phòng:");
				lblSubLMH.setFont(new Font("SansSerif", Font.PLAIN, 15));
				lblSubLMH.setBounds(654, 99, 102, 26);
				pMain.add(lblSubLMH);
				
				cboTinhTrangP = new JComboBox<String>();
				cboTinhTrangP.setFont(new Font("SansSerif", Font.PLAIN, 15));
				cboTinhTrangP.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
				cboTinhTrangP.setBackground(Color.WHITE);
				cboTinhTrangP.setBounds(766, 97, 310, 30);
				pMain.add(cboTinhTrangP);
		/////Buttons
				btnThemP = new FixButton("Thêm");
				btnThemP.setForeground(Color.WHITE);
				btnThemP.setFont(new Font("SansSerif", Font.BOLD, 14));
				btnThemP.setBackground(new Color(114, 43, 153));
				btnThemP.setBounds(374, 150, 118, 35);
				Image imgThemKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconGrayThem.png");
				Image resizeImgThemKH = imgThemKH.getScaledInstance(25, 25, 0);
				btnThemP.setIcon(new ImageIcon(resizeImgThemKH));
				pMain.add(btnThemP);
				
				btnSuaP = new FixButton("Sửa");
				btnSuaP.setForeground(Color.WHITE);
				btnSuaP.setFont(new Font("SansSerif", Font.BOLD, 14));
				btnSuaP.setBackground(new Color(114, 43, 153));
				btnSuaP.setBounds(537, 150, 118, 35);
				Image imgSuaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconTool.png");
				Image resizeImgSuaKH = imgSuaKH.getScaledInstance(25, 25, 0);
				btnSuaP.setIcon(new ImageIcon(resizeImgSuaKH));
				pMain.add(btnSuaP);
				
				btnXoaP = new FixButton("Xóa");
				btnXoaP.setForeground(Color.WHITE);
				btnXoaP.setFont(new Font("SansSerif", Font.BOLD, 14));
				btnXoaP.setBackground(new Color(114, 43, 153));
				btnXoaP.setBounds(702, 150, 125, 35);
				Image imgXoaKH = Toolkit.getDefaultToolkit().getImage("data\\img\\iconRemove.png");
				Image resizeImgXoaKH = imgXoaKH.getScaledInstance(25, 25, 0);
				btnXoaP.setIcon(new ImageIcon(resizeImgXoaKH));
				pMain.add(btnXoaP);
				
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
				
				cboSapXep = new JComboBox<String>();
				cboSapXep.setBounds(51, 12, 115, 28);
				cboSapXep.setFont(new Font("SansSerif", Font.BOLD, 15));
				cboSapXep.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
				cboSapXep.setBackground(Color.WHITE);
				String cbSort[] = { "Tăng dần", "Giảm dần" };
				for (int i = 0; i < cbSort.length; i++) {
					cboSapXep.addItem(cbSort[i]);
				}
				pSapXep.add(cboSapXep);
				
				
				
				rdoTheoMaP = new JRadioButton("Theo mã phòng");
				rdoTheoMaP.setBounds(312, 15, 170, 27);
				rdoTheoMaP.setSelected(true);
				rdoTheoMaP.setFont(new Font("SansSerif", Font.BOLD, 14));
				rdoTheoMaP.setBackground(new Color(207, 195, 237));
				pSapXep.add(rdoTheoMaP);
				
				rdoTheoLoaiP = new JRadioButton("Theo loại phòng");
				rdoTheoLoaiP.setBounds(518, 15, 170, 27);
				rdoTheoLoaiP.setFont(new Font("SansSerif", Font.BOLD, 14));
				rdoTheoLoaiP.setBackground(new Color(207, 195, 237));
				pSapXep.add(rdoTheoLoaiP);
				
				rdoTheoGiaP = new JRadioButton("Theo giá phòng ");
				rdoTheoGiaP.setBounds(718, 15, 135, 27);
				rdoTheoGiaP.setFont(new Font("SansSerif", Font.BOLD, 14));
				rdoTheoGiaP.setBackground(new Color(207, 195, 237));
				pSapXep.add(rdoTheoGiaP);
				

				chkAll.setFont(new Font("SansSerif", Font.BOLD, 14));
				chkAll.setBackground(new Color(207, 195, 237));
				chkAll.setBounds(201, 15, 135, 27);
				pSapXep.add(chkAll);
				chkAll.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if(e.getStateChange()==1) {
							loadDanhSachPhong();
						}
						else
							clearTable();
					}
				});
				
				
				
				ButtonGroup bgRdo=new ButtonGroup();
				bgRdo.add(rdoTheoMaP);
				bgRdo.add(rdoTheoLoaiP);
				bgRdo.add(rdoTheoGiaP);
				bgRdo.clearSelection();
				
				String phong [] = {"Mã phòng","Tên phòng", "Giá phòng", "Tình trạng phòng"};
				modelPhong = new DefaultTableModel(phong,0);
				
				tblPhong = new JTable(modelPhong);
				tblPhong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tblPhong.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				tblPhong.setShowHorizontalLines(true);
				tblPhong.setShowGrid(true);
				tblPhong.setBackground(Color.WHITE);
				tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 14));
				tblPhong.setSelectionBackground(new Color(164, 44, 167, 30));
				tblPhong.setSelectionForeground(new Color(114, 23, 153));
				tblPhong.setRowHeight(30);
				
				JTableHeader tbHeader = tblPhong.getTableHeader();
				tbHeader.setBackground(new Color(164, 44, 167));
				tbHeader.setForeground(Color.white);
				tbHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
				
				JScrollPane spPhong = new JScrollPane(tblPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				spPhong.setBounds(37, 249, 1194, 346);
				spPhong.setBorder(new LineBorder(new Color(164, 44, 167), 1, true));
				spPhong.setBackground(new Color(164, 44, 167));
				spPhong.getHorizontalScrollBar();
				pMain.add(spPhong);
				
				tblPhong.getColumnModel().getColumn(0).setPreferredWidth(240);
				tblPhong.getColumnModel().getColumn(1).setPreferredWidth(240);
				tblPhong.getColumnModel().getColumn(2).setPreferredWidth(240);
				tblPhong.getColumnModel().getColumn(3).setPreferredWidth(240);
				
				DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
				leftRenderer.setHorizontalAlignment(JLabel.LEFT);
				tblPhong.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
				tblPhong.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
				tblPhong.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				tblPhong.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
		///Background
				JLabel lblBackGround=new JLabel("");
				lblBackGround.setIcon(new ImageIcon("data\\img\\background.png"));
				lblBackGround.setBounds(0, 0, 1281, 606);
				Image imgBackGround = Toolkit.getDefaultToolkit().getImage("data\\img\\background.png");
				Image resizeBG = imgBackGround.getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), 0);
				lblBackGround.setIcon(new ImageIcon(resizeBG));
				pMain.add(lblBackGround);
				
	}
	
	//Lam moi danh sach
	public void clearTable() {
		while (tblPhong.getRowCount() > 0) {
			modelPhong.removeRow(0);
		}
	}
	
	//Load danh sach cac phong
	public void loadDanhSachPhong() {
		clearTable();
		ArrayList<Phong> lsP = daoPhong.getPhongDangHoatDong();
		for (Phong p : lsP) {
			LoaiPhong loaiP = daoLoaiP.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLoaiPhong());
			modelPhong.addRow(new Object[] {p.getMaPhong(), loaiP.getTenLoaiPhong(), dfGiaP.format(p.getGiaPhong()), p.getTinhTrangPhong() });
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
