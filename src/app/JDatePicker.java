package app;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class JDatePicker extends JFrame {
	JDatePickerImpl datePicker;
	public JDatePicker() {
		// TODO Auto-generated constructor stub
		SqlDateModel model=new SqlDateModel();
		Properties p=new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl panel=new JDatePanelImpl(model, p);
		datePicker=new JDatePickerImpl(panel, null);
		this.add(datePicker);
		this.pack();
		this.setVisible(true);
	}
	
	

//	private void pack() {
//		// TODO Auto-generated method stub
//		
//	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JDatePicker();
	}

}
