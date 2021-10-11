package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;
import entity.KhachHang;
import entity.LoaiKH;
import entity.LoaiMatHang;
import entity.MatHang;

public class DAOKhachHang {
	public KhachHang getKHTheoMa(String ma) { 
		KhachHang kh = new KhachHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * from KhachHang where maKhachHang = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				kh.setMaKhangHang(rs.getString(1));
				kh.setLoaiKH(new LoaiKH(rs.getString(2)));
				kh.setTenKH(rs.getString(3));
				kh.setSdt(rs.getString(4));
				kh.setCccd(rs.getString(5));
				kh.setDiaChi(rs.getString(6));
				kh.setNgaySinh(rs.getDate(7));
				kh.setGioiTinh(rs.getString(8));
				kh.setDiemTichLuy(rs.getInt(9));
				kh.setNgayDangKy(rs.getDate(10));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return kh;
	}
}
