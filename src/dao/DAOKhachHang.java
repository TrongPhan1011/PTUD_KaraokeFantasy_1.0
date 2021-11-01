package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;

import connection.ConnectDB;
import entity.KhachHang;
import entity.LoaiKH;
import entity.LoaiMatHang;
import entity.LoaiPhong;
import entity.MatHang;
import entity.Phong;

public class DAOKhachHang {
	public KhachHang getKHTheoMa(String ma) { 
		KhachHang kh = new KhachHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * from KhachHang where maKhachHang = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				kh.setMaKhangHang(rs.getString(1));
				kh.setLoaiKH(new LoaiKH(rs.getString(2)));
				kh.setTenKH(rs.getString(3));
				kh.setSdt(rs.getString(4));
				kh.setCccd(rs.getString(5));
				kh.setDiaChi(rs.getString(6));
				kh.setNgaySinh(rs.getDate(7));
				kh.setGioiTinh(rs.getString(8));
				kh.setDiemTichLuy(rs.getInt(9));
				kh.setNgayDangKy(rs.getDate(10));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return kh;
	}
	
	public KhachHang getKHTheoMa(JTextField tam) { 
		KhachHang kh = new KhachHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * from KhachHang where maKhachHang = '"+tam+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				kh.setMaKhangHang(rs.getString(1));
				kh.setLoaiKH(new LoaiKH(rs.getString(2)));
				kh.setTenKH(rs.getString(3));
				kh.setSdt(rs.getString(4));
				kh.setCccd(rs.getString(5));
				kh.setDiaChi(rs.getString(6));
				kh.setNgaySinh(rs.getDate(7));
				kh.setGioiTinh(rs.getString(8));
				kh.setDiemTichLuy(rs.getInt(9));
				kh.setNgayDangKy(rs.getDate(10));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return kh;
	}
public ArrayList<KhachHang> getDanhSachKH() {
		
		
		ArrayList<KhachHang> lsKH = new ArrayList<KhachHang>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select *from KhachHang";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				KhachHang kh = new KhachHang();
				
				kh.setMaKhangHang(rs.getString(1));
				kh.setLoaiKH(new LoaiKH(rs.getString(2)));
				kh.setTenKH(rs.getString(3));
				kh.setSdt(rs.getString(4));
				kh.setCccd(rs.getString(5));
				kh.setDiaChi(rs.getString(6));
				kh.setNgaySinh(rs.getDate(7));
				kh.setGioiTinh(rs.getString(8));
				kh.setDiemTichLuy(rs.getInt(9));
				kh.setNgayDangKy(rs.getDate(10));
				
				lsKH.add(kh);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsKH;
	}
public boolean themDanhSachKH(KhachHang kh) {
	
	ConnectDB.getinstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement stmt = null;
	int n=0;
	try { 
		stmt = con.prepareStatement("insert into KhachHang values (?,?,?,?,?,?,?,?,?,?)");
		stmt.setString(1, kh.getMaKhangHang());
		stmt.setString(2, kh.getLoaiKH().getMaLoaiKH());
		stmt.setString(3, kh.getTenKH());
		stmt.setString(4, kh.getSdt());
		stmt.setString(5, kh.getCccd());
		stmt.setString(6, kh.getDiaChi());
		stmt.setDate(7, kh.getNgaySinh());
		stmt.setString(8, kh.getGioiTinh());
		stmt.setInt(9, kh.getDiemTichLuy());
		stmt.setDate(10, kh.getNgayDangKy());
		
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

public boolean suaThongTinKhachHang(KhachHang kh) {
	
	ConnectDB.getinstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement stmt = null;
	int n=0;
	try { 
		stmt = con.prepareStatement("update KhachHang set maLoaiKH=?, tenKH=?, sdt=?, cccd=?, diaChi=?, ngaySinh=?, gioiTinh=?, diemTichLuy=?, ngayDangKy=? where maKhachHang=? ");
		stmt.setString(1, kh.getLoaiKH().getMaLoaiKH());
		stmt.setString(2, kh.getTenKH());
		stmt.setString(3, kh.getSdt());
		stmt.setString(4, kh.getCccd());
		stmt.setString(5, kh.getDiaChi());
		stmt.setDate(6, kh.getNgaySinh());
		stmt.setString(7, kh.getGioiTinh());
		stmt.setInt(8, kh.getDiemTichLuy());
		stmt.setDate(9, kh.getNgayDangKy());
		stmt.setString(10, kh.getMaKhangHang());
		
		n = stmt.executeUpdate();
		} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	return n>0;
}


}
