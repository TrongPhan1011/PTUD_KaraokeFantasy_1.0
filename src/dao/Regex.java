package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Regex {
	public boolean regexDiaChi(JTextField txtDiaChi) {
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
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!\nMẫu họ tên: Nguyễn Văn A)");
			txtTen2.requestFocus();
			txtTen2.selectAll();
			return false;
		} else
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
}
