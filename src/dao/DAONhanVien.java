package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.*;

public class DAONhanVien {
	public NhanVien getNVTheoMa(String ma) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhanVien where maNhanVien = '"+ma+"'";
		
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
	 
	//danhsachNV
	public ArrayList<NhanVien> getDanhSachNV() {
		ArrayList<NhanVien> lstNV=new ArrayList<NhanVien>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
//		String sql = "select * from NhanVien where trangThaiLamViec = N'Đang làm việc'";
		try {
			PreparedStatement ps = con.prepareStatement("select maNhanVien, tenNhanVien, chucVu, gioiTinh, ngaySinh, diaChi, sdt, cccd, luong, caLamViec, trangThaiLamViec from [dbo].[NhanVien] where trangThaiLamViec = N'Đang làm việc'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv=new NhanVien();
				nv.setMaNhanVien(rs.getString(1));
				nv.setTenNhanVien(rs.getString(2));
				nv.setChucVu(rs.getString(3));
				nv.setGioiTinh(rs.getString(4));
				nv.setNgaySinh(rs.getDate(5));
				nv.setDiaChi(rs.getString(6));
				nv.setSdt(rs.getString(7));
				nv.setCccd(rs.getString(8));
				nv.setLuong(rs.getDouble(9));
				nv.setCaLamViec(rs.getInt(10));
				nv.setTrangThaiLamViec(rs.getString(11));
				//nv.setTaiKhoan((TaiKhoan) rs.getObject(12));
			
				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	
	//themNV
	
	
}
