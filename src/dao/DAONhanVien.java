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

import javax.swing.JLabel;

import connection.ConnectDB;
import entity.*;
import dao.*;

public class DAONhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOPhatSinhMa daoPhatSinhMa;
	private ArrayList<NhanVien> dsNV;
	
	public DAONhanVien() {
		dsNV=new ArrayList<NhanVien>();
	}

	//Load ds NV
	public ArrayList<NhanVien> getDanhSachNV() {
		ArrayList<NhanVien> lstNV=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from [dbo].[NhanVien] where trangThaiLamViec = N'Đang làm việc'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv=new NhanVien();
				nv.setMaNhanVien(rs.getString(1));
				nv.setTaiKhoan(new TaiKhoan(rs.getString(2)));
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
				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	
	//Load 1 NV dang lam viec theo manv
	public NhanVien getNVTheoMa(String ma) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where maNhanVien = '"+ma+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				nv.setMaNhanVien(rs.getString(1));
				nv.setTaiKhoan(new TaiKhoan(rs.getString(2)));
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
	
//	public boolean kiemTraTrangThaiLamViec(JLabel lblNVDaNghiViec) {
//		NhanVien nv=new NhanVien();
//		ConnectDB.getinstance();
//		Connection con = ConnectDB.getConnection();
//		return false;
//	}
	
	//Load 1 NV da nghi viec theo manv
	public NhanVien getNVDaNghiViecTheoMa(String ma) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
//		String sql = "select * from NhanVien where maNhanVien = '"+ma+"'";
		String sql = "select * from [dbo].[NhanVien] where maNhanVien = '"+ma+"' and trangThaiLamViec = N'Đã nghỉ việc'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				nv.setMaNhanVien(rs.getString(1));
				nv.setTaiKhoan(new TaiKhoan(rs.getString(2)));
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
	
//	//Tim NV
//	public NhanVien timNV(String maNV) {
//		NhanVien nv=new NhanVien(maNV);
//		if(dsNV.contains(nv))
//			return dsNV.get(dsNV.indexOf(nv));
//		return null;
//	}
	
	//them NV
	public boolean themNV(NhanVien nv) throws SQLException {
//		String maNV = daoPhatSinhMa.getMaNV();// lỗi
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		int n=0;
		String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getMaNhanVien());
			ps.setString(2, nv.getTaiKhoan().getMaTK());
			ps.setString(3, nv.getTenNhanVien());
			ps.setString(4, nv.getChucVu());
			ps.setString(5, nv.getGioiTinh());
			ps.setDate(6, nv.getNgaySinh());
			ps.setString(7, nv.getDiaChi());
			ps.setString(8, nv.getSdt());
			ps.setString(9, nv.getCccd());
			ps.setDouble(10, nv.getLuong());
			ps.setInt(11, nv.getCaLamViec());
			ps.setString(12, nv.getTrangThaiLamViec());
			
			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
}
