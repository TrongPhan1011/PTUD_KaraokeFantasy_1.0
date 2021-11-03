package dao;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Regex {
	public boolean regexDiaChi(JTextArea txtDiaChi) {
		String input = txtDiaChi.getText();
		String regex = "^([ A-Za-z0-9,.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ!\nMẫu địa chỉ:56a Cầu Xéo, Tân quí, Tân Phú");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexTen(JTextField txtTen2) {
		String input = txtTen2.getText();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!\nMẫu họ tên: Nguyễn Văn A");
			txtTen2.requestFocus();
			txtTen2.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexSDT(JTextField txtSDT) {
		String input = txtSDT.getText();
		String regex = "^(0[0-9]{9}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(!matcher.find()) {
			JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!\nSĐT gồm 10 chữ số và bắt đầu bằng số 0");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}else
			return true;
	}
	
	public boolean regexCCCD(JTextField txtCCCD) {
		String input = txtCCCD.getText();
		String regex = "^([0-9]{12}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(!matcher.find()) {
			JOptionPane.showMessageDialog(null, "CCCD không hợp lệ!\nCCD gồm 12 chữ số");
			txtCCCD.requestFocus();
			txtCCCD.selectAll();
			return false;
		}else
			return true;
	}
	
	public boolean regexSoLuong(JTextField txtSoluong) {
		String input = txtSoluong.getText();
		String regex = "^[1-9]+[0-9]*$";
		if(!input.matches(regex))
		{	JOptionPane.showMessageDialog(null, "Số lượng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 10");
			txtSoluong.requestFocus();
			txtSoluong.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemMaPhong(JTextField txtTim) {
		String input = txtTim.getText();
		String regex = "^(P[0-9]{3})$";
		if(!input.matches(regex))
		{	JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã Phòng. Ví dụ: P003\n");
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemMaNV(JTextField txtTim) {
		String input = txtTim.getText();
		String regexMaNV = "^(NV[0-9]{3})$";
		if(!input.matches(regexMaNV)) {
			JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã nhân viên. Ví dụ: NV001\n");
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemMaKH(JTextField txtTim) {
		String input = txtTim.getText();
		String regexMaKH = "^(KH[0-9]{3})$";
		if(!input.matches(regexMaKH)) {
			JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã Khách hàng. Ví dụ: KH001\n");
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexNVTren18(JDateChooser dateChooser) {
		LocalDate dNow = LocalDate.now();
		int nam = dNow.getYear();
		int day = dateChooser.getDate().getDay();
		int monnth = dateChooser.getDate().getMonth();
		int year = dateChooser.getDate().getYear();
		if(day<=0 || day>31 || monnth<=0 || monnth>12 || year<=0 || year>(nam - 18)) {
			JOptionPane.showMessageDialog(null, "Nhân viên phải trên 18 tuổi!");
			return false;
		}
		return true;
	}
}
