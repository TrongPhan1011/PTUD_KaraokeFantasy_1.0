package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.LoaiMatHang;
import entity.MatHang;

public class DAOMatHang {
//	public ArrayList<MatHang> getAllTableMatHang() {
//		ArrayList<MatHang> dsMH = new ArrayList<MatHang>();
//		try {
//			ConnectDB.getinstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "select * from MatHang";
//			java.sql.Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				String maMH = rs.getString(1);
//				String LoaiMH = rs.getString(2);
//				String tenMH = rs.getString(3);
//				int soluong = rs.getInt(4);
//				double giaMH = rs.getDouble(5);
//				MatHang mh = new MatHang(maMH, tenMH, soluong, giaMH, null);
//				dsMH.add(mh);
//			}
//		} catch (Exception e) {
//			e.getStackTrace();
//		}
//		return dsMH;
//	}
	
	public MatHang getMHTheoMaMH(String ma) {
		
		MatHang mh = new MatHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from MatHang where maMH = '"+ ma +"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				mh.setMaMatHang(rs.getNString(1));
				mh.setLoaiMatHang(new LoaiMatHang(rs.getNString(2)));
				mh.setTenMatHang(rs.getNString(3));
				mh.setSoLuongMatHang(rs.getInt(4));
				mh.setGiaMatHang(rs.getDouble(5));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mh;
	}
	
	public ArrayList<MatHang> getMatHangTheoMaLoai(String Maloai) {
		ArrayList< MatHang> lsMatHang = new ArrayList<MatHang>();
		
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from MatHang where MaLoaiMH = '"+Maloai +"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				MatHang mh = new MatHang();
				
				mh.setMaMatHang(rs.getNString(1));
				mh.setLoaiMatHang(new LoaiMatHang(rs.getNString(2)));
				mh.setTenMatHang(rs.getNString(3));
				mh.setSoLuongMatHang(rs.getInt(4));
				mh.setGiaMatHang(rs.getDouble(5));
				
				lsMatHang.add(mh);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsMatHang;
		
	}
	
	public MatHang getMHTheoTenMHVaLoaiMH(String tenMH, String tenLoai) {
		MatHang mh = new MatHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT MatHang.*\r\n"
				+ "FROM  LoaiMatHang INNER JOIN\r\n"
				+ "         MatHang ON LoaiMatHang.maLoaiMH = MatHang.maLoaiMH\r\n"
				+ "Where tenMH = N'"+tenMH+"' and tenLoaiMH = N'"+tenLoai+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				mh.setMaMatHang(rs.getNString(1));
				mh.setLoaiMatHang(new LoaiMatHang(rs.getNString(2)));
				mh.setTenMatHang(rs.getNString(3));
				mh.setSoLuongMatHang(rs.getInt(4));
				mh.setGiaMatHang(rs.getDouble(5));
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mh;
		
	}
	
	
	
	
}
