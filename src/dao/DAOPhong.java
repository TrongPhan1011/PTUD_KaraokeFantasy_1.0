package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class DAOPhong {
	public ArrayList<Phong> getPhongDangHoatDong() {
		
		
		ArrayList< Phong> lsPhong = new ArrayList<Phong>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT Phong.*\r\n"
				+ "FROM  DonDatPhong INNER JOIN\r\n"
				+ "         Phong ON DonDatPhong.maPhong = Phong.maPhong\r\n"
				+ "WHERE tinhTrangPhong = N'Đang hoạt động' and TrangThaiDDP = N'Đã xác nhận'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Phong p = new Phong();
				
				p.setMaPhong(rs.getNString(1));
				p.setLoaiPhong(new LoaiPhong(rs.getNString(2)));
				p.setTinhTrangPhong(rs.getNString(3));
				p.setGiaPhong(rs.getDouble(4));
				
				lsPhong.add(p);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsPhong;
		
		
		
	}
}
