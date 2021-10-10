package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class DAOPhong {
	public ArrayList<Phong> getPhongTheoTrangThaiCTDDP() {
		
		
		ArrayList< Phong> lsPhong = new ArrayList<Phong>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT Phong.*\r\n"
				+ "FROM  CTDDP INNER JOIN\r\n"
				+ "         Phong ON CTDDP.maPhong = Phong.maPhong INNER JOIN\r\n"
				+ "         DonDatPhong ON CTDDP.maDDP = DonDatPhong.maDDP\r\n"
				+ "WHERE phong.tinhTrangPhong = N'Đã đặt' or Phong.tinhTrangPhong = N'Đang hoạt động'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Phong p = new Phong();
				
				p.setMaPhong(rs.getNString(1));
				p.setTinhTrangPhong(rs.getNString(2));
				p.setGiaPhong(rs.getDouble(3));
				p.setLoaiPhong(new LoaiPhong(rs.getNString(4)));
				
				lsPhong.add(p);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsPhong;
		
		
		
	}
}
