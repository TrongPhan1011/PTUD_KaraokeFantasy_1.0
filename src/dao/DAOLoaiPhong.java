package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;
import entity.LoaiPhong;

public class DAOLoaiPhong {
	public LoaiPhong getLoaiPhongTheoMa(String ma) {
		LoaiPhong lp = new LoaiPhong();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from LoaiPhong where maLoaiPhong = N'"+ma +"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				 lp.setMaLoaiPhong(rs.getNString(1));
				 lp.setTenLoaiPhong(rs.getNString(2));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lp;
		
	}
	
	public LoaiPhong getLoaiPhongTheoTenLoai(String ten) {
		LoaiPhong lp = new LoaiPhong();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from LoaiPhong where tenLoaiPhong = N'"+ten +"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				 lp.setMaLoaiPhong(rs.getNString(1));
				 lp.setTenLoaiPhong(rs.getNString(2));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lp;
		
	}
}
