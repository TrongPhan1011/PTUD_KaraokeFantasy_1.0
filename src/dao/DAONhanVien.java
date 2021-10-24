package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import connection.ConnectDB;
import entity.*;

public class DAONhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<NhanVien> dsNV;
	
	public DAONhanVien() {
		dsNV=new ArrayList<NhanVien>();
	}

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
	 
	//Load danh sach NV
	public ArrayList<NhanVien> getDanhSachNV() {
		ArrayList<NhanVien> lstNV=new ArrayList<>();
//		ArrayList<TaiKhoan> lstTK=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select maNhanVien, tenNhanVien, chucVu, gioiTinh, ngaySinh, diaChi, sdt, cccd, luong, caLamViec from [dbo].[NhanVien]\r\n"
														+ "where trangThaiLamViec = N'Đang làm việc'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv=new NhanVien();
				TaiKhoan tk=new TaiKhoan();
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
				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	
	//Tim NV
	public NhanVien timNV(String maNV) {
		NhanVien nv=new NhanVien(maNV);
		if(dsNV.contains(nv))
			return dsNV.get(dsNV.indexOf(nv));
		return null;
	}
	
}
