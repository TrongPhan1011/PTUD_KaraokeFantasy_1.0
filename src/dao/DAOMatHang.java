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
	public ArrayList<MatHang> getMatHangTheoMaLoai(String Maloai) {
		ArrayList< MatHang> lsMatHang = new ArrayList<MatHang>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from MatHang where MaLoaiMatHang = '"+Maloai +"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				MatHang mh = new MatHang();
				
				mh.setMaMatHang(rs.getString(1));
				mh.setTenMatHang(rs.getString(2));
				mh.setSoLuongMatHang(rs.getInt(3));
				mh.setGiaMatHang(rs.getDouble(4));
				mh.setLoaiMatHang(new LoaiMatHang(Maloai));
				
				lsMatHang.add(mh);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsMatHang;
		
	}
}
