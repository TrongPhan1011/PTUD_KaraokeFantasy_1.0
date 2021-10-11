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
				+ "FROM  CTDDP where maDDP = '"+ma+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				CTDDP ctddp = new CTDDP();

				ctddp.setDonDatPhong(new DonDatPhong(ma));
				ctddp.setMatHang(new MatHang(rs.getNString(2)));
				ctddp.setSoLuongMH(rs.getInt(3));
				
				lsCTDDP.add(ctddp);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsCTDDP;
		
	}
	
	
}
