package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ContainerListener extends JFrame {

    private static final long serialVersionUID = 1L;

    public ContainerListener() {
        super("Test");
        setContentPane(new TestPanel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] parameters) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ContainerListener containerListener = new ContainerListener();
            }
        });
    }

    private class TestPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        TestPanel() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            add(new JButton(new AbstractAction("Add label") {

                private static final long serialVersionUID = 1L;
             
				private Frm_NhanVien frm_NhanVien;

                @Override
                public void actionPerformed(ActionEvent event) {
                	
        			frm_NhanVien = new Frm_NhanVien();
        			
                    TestPanel.this.add(frm_NhanVien.getFrm_NhanVien());

                }
            }));
            
           

        }

//        @Override
//        public Dimension getPreferredSize() {
//            return new Dimension(400, 400);
//        }
    }
}