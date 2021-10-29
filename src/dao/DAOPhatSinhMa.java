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
	}
	//maMH
	public String getMaMH() {
		String maMH="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NV', RIGHT(CONCAT('000',ISNULL(right(max(maMH),3),0) + 1),3)) from [dbo].[MatHang] where maMH = 'MH%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maMH;
	}
}
