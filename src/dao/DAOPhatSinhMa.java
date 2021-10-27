package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;

public class DAOPhatSinhMa {
	//maHD
	public String getMaHD() {
		String maHD="";
		try {
			ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('HD', RIGHT(CONCAT('000',ISNULL(right(max(maHD),3),0) + 1),3)) from [dbo].[HoaDon] where maHD like 'HD%'";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next())
		{
			maHD = rs.getString(1);
		}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return maHD;
	}
<<<<<<< HEAD
	public String getMaKH() {
		String maKH="";
		try {
			ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('KH', RIGHT(CONCAT('000',ISNULL(right(max(maKhachHang),3),0) + 1),3)) from [dbo].[KhachHang] where maKhachHang like 'KH%'";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next())
		{
			maKH = rs.getString(1);
		}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return maKH;
=======
	
	//maNV
	public String getMaNV() {
		String maNV="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NV', RIGHT(CONCAT('000',ISNULL(right(max(maNV),3),0) + 1),3)) from [dbo].[NhanVien] where maNhanVien = 'NV%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
>>>>>>> 49e2fcde783a86612fe3b2f0c95a80c6ee0d83c1
	}
}
