package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CTDDP;
import entity.DonDatPhong;
import entity.MatHang;
import entity.Phong;

public class DAOCTDDP {
	
	public ArrayList<CTDDP> getCTDDPTheoMaDDP(String ma) {
		ArrayList< CTDDP> lsCTDDP = new ArrayList<CTDDP>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT CTDDP.*\r\n"
				+ "FROM  CTDDP INNER JOIN\r\n"
				+ "         Phong ON CTDDP.maPhong = Phong.maPhong INNER JOIN\r\n"
				+ "         DonDatPhong ON CTDDP.maDDP = DonDatPhong.maDDP\r\n"
				+ "WHERE  DonDatPhong.maDDP = '"+ma+"' and phong.tinhTrangPhong = N'Đã đặt' or Phong.tinhTrangPhong = N'Đang hoạt động' \r\n"
				+ "";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				CTDDP ctddp = new CTDDP();
				
				ctddp.setMaCTDDP(rs.getNString(1));
				ctddp.setNgayDen(rs.getDate(2));
				ctddp.setGioDen(rs.getTime(3));
				ctddp.setTrangThaiCTDDP(rs.getNString(4));
				ctddp.setMatHang(new MatHang(rs.getNString(5)));
				ctddp.setPhong(new Phong(rs.getNString(6)));
				ctddp.setDonDatPhong(new DonDatPhong(ma));
				
				lsCTDDP.add(ctddp);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsCTDDP;
		
	}
	
	
}
