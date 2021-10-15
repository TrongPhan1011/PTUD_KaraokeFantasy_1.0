package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;
import entity.KhachHang;
import entity.LoaiKH;
import entity.NhanVien;
import entity.TaiKhoan;

public class DAONhanVien {
	public NhanVien getNVTheoMa(String ma) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "  select * from NhanVien where maNhanVien = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				nv.setMaNhanVien(rs.getString(1));
				nv.setTaiKhoan(new TaiKhoan(rs.getNString(2)));
				nv.setTenNhanVien(rs.getString(3));
				nv.setChucVu(rs.getString(4));
				nv.setGioiTinh(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setDiaChi(rs.getString(7));
				nv.setSdt(rs.getString(8));
				nv.setCccd(rs.getString(9));
				nv.setLuong(rs.getDouble(10));
				nv.setCaLamViec(rs.getInt(11));
				nv.setTrangThaiLamViec(rs.getString(12));
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return nv;
	}
}
