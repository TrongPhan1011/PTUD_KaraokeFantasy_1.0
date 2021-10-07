package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Frm_DanhSachHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JFrame frm;


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
		pMain.setBounds(0, 0, 1069, 671);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(830, 0, 89, 23);
		pMain.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(980, 370, 89, 23);
		pMain.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(803, 648, 89, 23);
		pMain.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(0, 385, 89, 23);
		pMain.add(btnNewButton_3);
		btnNewButton.addActionListener(this);
		
		
		
		
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
