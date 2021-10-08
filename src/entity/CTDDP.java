package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class CTDDP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String maCTDDP;
	private Date ngayDen;
	private Time gioDen;
	private String trangThaiCTDDP;
	private DonDatPhong donDatPhong;
	private Phong phong;
	private MatHang matHang;
	
	public Time getGioDen() {
		return gioDen;
	}
	public void setGioDen(Time gioDen) {
		this.gioDen = gioDen;
	}
	public String getMaCTDDP() {
		return maCTDDP;
	}
	public void setMaCTDDP(String maCTDDP) {
		this.maCTDDP = maCTDDP;
	}
	public Date getNgayDen() {
		return ngayDen;
	}
	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}
	public String getTrangThaiCTDDP() {
		return trangThaiCTDDP;
	}
	public void setTrangThaiCTDDP(String trangThaiCTDDP) {
		this.trangThaiCTDDP = trangThaiCTDDP;
	}
	public DonDatPhong getDonDatPhong() {
		return donDatPhong;
	}
	public void setDonDatPhong(DonDatPhong donDatPhong) {
		this.donDatPhong = donDatPhong;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public MatHang getMatHang() {
		return matHang;
	}
	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public CTDDP() {
		super();
	}
	public CTDDP(String maCTDDP) {
		super();
		this.maCTDDP = maCTDDP;
	}
	
	public CTDDP(String maCTDDP, Date ngayDen, Time gioDen, String trangThaiCTDDP, DonDatPhong donDatPhong, Phong phong,
			MatHang matHang) {
		super();
		this.maCTDDP = maCTDDP;
		this.ngayDen = ngayDen;
		this.gioDen = gioDen;
		this.trangThaiCTDDP = trangThaiCTDDP;
		this.donDatPhong = donDatPhong;
		this.phong = phong;
		this.matHang = matHang;
	}
	
	
	public CTDDP(String maCTDDP, Date ngayDen, Time gioDen, String trangThaiCTDDP, DonDatPhong donDatPhong,
			Phong phong) {
		super();
		this.maCTDDP = maCTDDP;
		this.ngayDen = ngayDen;
		this.gioDen = gioDen;
		this.trangThaiCTDDP = trangThaiCTDDP;
		this.donDatPhong = donDatPhong;
		this.phong = phong;
	}
	@Override
	public String toString() {
		return "CTDDP [maCTDDP=" + maCTDDP + ", ngayDen=" + ngayDen + ", gioDen=" + gioDen + ", trangThaiCTDDP="
				+ trangThaiCTDDP + ", donDatPhong=" + donDatPhong + ", phong=" + phong + ", matHang=" + matHang + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((donDatPhong == null) ? 0 : donDatPhong.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTDDP other = (CTDDP) obj;
		if (donDatPhong == null) {
			if (other.donDatPhong != null)
				return false;
		} else if (!donDatPhong.equals(other.donDatPhong))
			return false;
		return true;
	}
	
	
	

}
