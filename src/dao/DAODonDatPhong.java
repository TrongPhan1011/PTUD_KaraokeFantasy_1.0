package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.LoaiMatHang;
import entity.LoaiPhong;
import entity.MatHang;
import entity.NhanVien;
import entity.Phong;

public class DAODonDatPhong {
	
	public ArrayList<DonDatPhong> getAllDonDatPhong(){
		ArrayList<DonDatPhong> lsDDP = new ArrayList<DonDatPhong>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from DonDatPhong";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				DonDatPhong ddp = new DonDatPhong();
				ddp.setMaDDP(rs.getNString(1));
				ddp.setNgayLap(rs.getDate(2));
				ddp.setKhachHang(new KhachHang(rs.getNString(3)));
				ddp.setNhanVien(new NhanVien(rs.getNString(4)));
					lsDDP.add(ddp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsDDP;
	}
	
	
	public DonDatPhong getDDPTheoMaPhongTrangThaiPhong(String ma){
		DonDatPhong ddp = new DonDatPhong();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT DonDatPhong.*\r\n"
				+ "FROM  CTDDP INNER JOIN\r\n"
				+ "         Phong ON CTDDP.maPhong = Phong.maPhong INNER JOIN\r\n"
				+ "         DonDatPhong ON CTDDP.maDDP = DonDatPhong.maDDP\r\n"
				+ "WHERE Phong.maPhong = '"+ma+"' and  phong.tinhTrangPhong = N'Đã đặt' or Phong.tinhTrangPhong = N'Đang hoạt động' \r\n"
				+ "";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				ddp.setMaDDP(rs.getNString(1));
				ddp.setNgayLap(rs.getDate(2));
				ddp.setKhachHang(new KhachHang(rs.getNString(3)));
				ddp.setNhanVien(new NhanVien(rs.getNString(4)));

				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ddp;
	}
	
}
