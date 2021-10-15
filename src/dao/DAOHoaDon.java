package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectDB;
import entity.HoaDon;

public class DAOHoaDon {
public boolean themHoaDon(HoaDon hd) {
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into HoaDon values (?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setString(2, hd.getPhong().getMaPhong());
			stmt.setString(3, hd.getKhachHang().getMaKhangHang());
			stmt.setString(4, hd.getNhanVien().getMaNhanVien());
			stmt.setDate(5, hd.getNgayLap());
			stmt.setTime(6, hd.getGioVao());
			stmt.setTime(7, hd.getGioRa());
			stmt.setString(8, hd.getPhuThu());
			stmt.setString(9, hd.getTrangThaiHD());
			
			
			
			
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}
}
