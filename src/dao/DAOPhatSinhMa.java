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
	}
	//maNV
	public String getMaNV() {
		String maNV="";
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NV', RIGHT(CONCAT('000',ISNULL(right(max(maNhanVien),3),0) + 1),3)) from [dbo].[NhanVien] where maNhanVien like 'NV%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
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
		String sql = "select CONCAT('MH', RIGHT(CONCAT('000',ISNULL(right(max(maMH),3),0) + 1),3)) from [dbo].[MatHang] where maMH like  'MH%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maMH = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maMH;
	}
	
<<<<<<< HEAD

	//matkhau
//	public String getMatKhau() {
//		String mk="";
//		ConnectDB.getinstance()
//	}

=======
>>>>>>> e8ebb7a20daf49c6a3e45c3caee6cd4c0c048825
}
