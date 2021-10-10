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
		String sql = "SELECT KhachHang.*\r\n"
				+ "FROM  KhachHang INNER JOIN\r\n"
				+ "         DonDatPhong ON KhachHang.maKhachHang = DonDatPhong.maKhachHang\r\n"
				+ "where maDDP = '"+ma+"'\r\n"
				+ "";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				kh.setMaKhangHang(rs.getString(1));
				kh.setTenKH(rs.getString(2));
				kh.setSdt(rs.getString(3));
				kh.setCccd(rs.getString(4));
				kh.setDiaChi(rs.getString(5));
				kh.setNgaySinh(rs.getDate(6));
				kh.setGioiTinh(rs.getString(7));
				kh.setDiemTichLuy(rs.getInt(8));
				kh.setNgayDangKy(rs.getDate(9));
				kh.setLoaiKH(new LoaiKH(rs.getString(10)));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return kh;
	}
}
