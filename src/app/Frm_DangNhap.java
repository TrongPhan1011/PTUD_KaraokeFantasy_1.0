package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class Frm_DangNhap extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_DangNhap frame = new Frm_DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Frm_DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(92, 84, 61, 16);
		contentPane.add(lblNewLabel);
		setExtendedState(MAXIMIZED_BOTH);
		
//		Image img = Toolkit.getDefaultToolkit().createImage("data/img/background.png");
//		this.setContentPane(new JPanel() {
//	         /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//	         public void paintComponent(Graphics g) {
//	            super.paintComponent(g);
//	            g.drawImage(img, 0, 0, null);
//	         }
//	      });
//		pack();
		
		
		
	}
}
