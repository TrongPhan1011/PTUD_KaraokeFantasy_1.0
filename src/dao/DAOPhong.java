package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.Phong;

public class DAOPhong {
	
	public Phong getPhongTheoMa(String ma) {
		
		Phong p = new Phong();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql ="  select * from Phong where maPhong = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				p.setMaPhong(rs.getNString(1));
				p.setLoaiPhong(new LoaiPhong(rs.getNString(2)));
				p.setTinhTrangPhong(rs.getNString(3));
				p.setGiaPhong(rs.getDouble(4));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	public boolean capnhatTrangThaiPhong(String maPhong, String trangThaiPhong) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update [dbo].[Phong] set tinhTrangPhong = ? where maPhong =? ");
			
			stmt.setString(1, trangThaiPhong);
			stmt.setString(2, maPhong);
			
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
