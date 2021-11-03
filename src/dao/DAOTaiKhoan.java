package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.TaiKhoan;

public class DAOTaiKhoan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Load ds TK
	public ArrayList<TaiKhoan> getDanhSachTK(){
		ArrayList<TaiKhoan> lstTK=new ArrayList<TaiKhoan>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from TaiKhoan");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaiKhoan tk=new TaiKhoan();
				tk.setMaTK(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
				lstTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTK;
	}
	
	//Load ds matkhau
	public TaiKhoan getMatKhauTheoMaNV(String ma) {
		TaiKhoan tk=new TaiKhoan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from TaiKhoan where maTK = '"+ma+"'"; 
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tk.setMaTK(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	
	//taoTK khi themNV
	public boolean createTK(TaiKhoan tk) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into TaiKhoan values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tk.getMaTK());
			ps.setString(2, tk.getMatKhau());
			
			return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
}
