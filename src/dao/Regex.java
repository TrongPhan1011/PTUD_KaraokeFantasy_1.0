package dao;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Regex {
	public boolean regexDiaChi(JTextField txtDiaChi) {
		String input = txtDiaChi.getText();
		String regex = "^([ A-Za-z0-9,.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ!\nMẫu địa chỉ:56a Cầu Xéo, Tân quí, Tân Phú", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!\nMẫu họ tên: Nguyễn Văn A", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
//			JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!\nSĐT gồm 10 chữ số và bắt đầu bằng số 0", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
//			JOptionPane.showMessageDialog(null, "CCCD không hợp lệ!\nCCD gồm 12 chữ số", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
		{	JOptionPane.showMessageDialog(null, "Số lượng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 10", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtSoluong.requestFocus();
			txtSoluong.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexGiaP(JTextField txtGiaP) {
		String input = txtGiaP.getText();
		String regex = "^[1-9]+[0-9]*$";
		if(!input.matches(regex))
		{	JOptionPane.showMessageDialog(null, "Giá phòng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 10", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtGiaP.requestFocus();
			txtGiaP.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemMaPhong(JTextField txtTim) {
		String input = txtTim.getText();
		String regex = "^(P[0-9]{3})$";
		if(!input.matches(regex))
		{	JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã Phòng. Ví dụ: P003\n", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemMaNV(JTextField txtTim) {
		String input = txtTim.getText();
		String regexMaNV = "^(NV[0-9]{2}[1-9]{1})$";
		Pattern pattern = Pattern.compile(regexMaNV);
		Matcher matcher = pattern.matcher(input);
		if(!input.matches(regexMaNV)) {
//			JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không hợp lệ\nThông tin có thể tìm kiếm:\n - Mã nhân viên. Ví dụ: NV001\n", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	public boolean regexTimKiemMaKH(JTextField txtTK) {
		String input = txtTK.getText();
		String regexMaKH = "^(KH[0-9]{3})$";
		if(!input.matches(regexMaKH)) {
			txtTK.requestFocus();
			txtTK.selectAll();
			return false;
		}
		return true;
	}
	public boolean regexTenNV(JTextField txtTen2) {
		String input = txtTen2.getText();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
//			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!\nMẫu họ tên: Nguyễn Văn A", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTen2.requestFocus();
			txtTen2.selectAll();
			return false;
		} else
			return true;

	}

	
	public boolean regexNVTren18(JDateChooser dateChooser) {
		LocalDate dNow = LocalDate.now();
		int nam = dNow.getYear();
		int day = dateChooser.getDate().getDay();
		int monnth = dateChooser.getDate().getMonth();
		int year = dateChooser.getDate().getYear();
		if(day<=0 || day>31 || monnth<=0 || monnth>12 || year<=0 || year>(nam - 18)) {
			JOptionPane.showMessageDialog(null, "Nhân viên phải trên 18 tuổi!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean regexTimKiemChucVu(JTextField txtTim) {
		String input = txtTim.getText().toLowerCase().trim();
//		String regex = "^[(quản lý)|(thu ngân)|(phục vụ)]$";
//		String regexQL = "^[ A-Za-zA-ZQqẢảNnLlÝý]*+$";	//chạy đc
		String regexQL = "^[quản lý]*+$";   			//chạy đc
		Pattern patternQL = Pattern.compile(regexQL);
		Matcher matcherQL = patternQL.matcher(input);
		
//		String regexTN = "^[ A-Za-zA-ZÂâGgHhNnTtUu]*+$";
		String regexTN = "^[thu ngân]*+$";
//		String regexTN = "aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz0123456789_]+$";
		Pattern patternTN = Pattern.compile(regexTN);
		Matcher matcherTN = patternTN.matcher(input);
		
//		String regexPV = "^[ A-Za-zA-ZPpHhỤụCcVv]*+$";
		String regexPV = "^[phục vụ]*+$";
//		String regexPV = "aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz0123456789_]+$";
		Pattern patternPV = Pattern.compile(regexPV);
		Matcher matcherPV = patternPV.matcher(input);
		if (!matcherQL.find() && !matcherTN.find() && !matcherPV.find()) {
//		if (!matcherQL.find()) {
//			JOptionPane.showMessageDialog(null, "Chức vụ không hợp lệ!\nTìm theo chức vụ: phục vụ, thu ngân, quản lý", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexTimKiemCa(JTextField txtTim) {
		String input = txtTim.getText();
		String regex = "^[1-3]{1}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
//			JOptionPane.showMessageDialog(null, "Ca làm việc không hợp lệ!\nTìm theo ca: 1, 2, 3", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexTimKiemGioiTinh(JTextField txtTim) {
		String input = txtTim.getText();
		String regexNam = "^[nam]$";
		String regexNu = "^[nữ]$";
		Pattern patternNam = Pattern.compile(regexNam);
		Matcher matcherNam = patternNam.matcher(input);
		if (!matcherNam.find()) {
			JOptionPane.showMessageDialog(null, "Giới tính không hợp lệ!\nTìm giới tính: nam hoặc nữ", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		
		Pattern patternNu = Pattern.compile(regexNu);
		Matcher matcherNu = patternNu.matcher(input);
		if (!matcherNu.find()) {
			JOptionPane.showMessageDialog(null, "Giới tính không hợp lệ!\nTìm giới tính: nam hoặc nữ", "Thông báo", JOptionPane.ERROR_MESSAGE);
			txtTim.requestFocus();
			txtTim.selectAll();
			return false;
		}
		return true;
	}
	

}
