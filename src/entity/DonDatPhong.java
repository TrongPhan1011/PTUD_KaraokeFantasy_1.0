package entity;

import java.io.Serializable;
import java.sql.Date;

public class DonDatPhong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String maDDP;
	private Date ngayLap;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	
	public String getMaDDP() {
		return maDDP;
	}
	public void setMaDDP(String maDDP) {
		this.maDDP = maDDP;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DonDatPhong(String maDDP, Date ngayLap, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maDDP = maDDP;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public DonDatPhong() {
		super();
	}
	public DonDatPhong(String maDDP) {
		super();
		this.maDDP = maDDP;
	}
	@Override
	public String toString() {
		return "DonDatPhong [maDDP=" + maDDP + ", ngayLap=" + ngayLap + ", khachHang=" + khachHang + ", nhanVien="
				+ nhanVien + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((khachHang == null) ? 0 : khachHang.hashCode());
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
		DonDatPhong other = (DonDatPhong) obj;
		if (khachHang == null) {
			if (other.khachHang != null)
				return false;
		} else if (!khachHang.equals(other.khachHang))
			return false;
		return true;
	}
	

}
