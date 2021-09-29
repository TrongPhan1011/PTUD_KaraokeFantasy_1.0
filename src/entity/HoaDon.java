package entity;

import java.io.Serializable;
import java.sql.Date;

public class HoaDon implements Serializable {
	private String maHoaDon;
	private Date ngayLap;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public HoaDon(String maHoaDon, Date ngayLap, NhanVien nhanVien, KhachHang khachHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}
	public HoaDon() {
		super();
	}
	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", nhanVien=" + nhanVien + ", khachHang="
				+ khachHang + "]";
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
		HoaDon other = (HoaDon) obj;
		if (khachHang == null) {
			if (other.khachHang != null)
				return false;
		} else if (!khachHang.equals(other.khachHang))
			return false;
		return true;
	}
	
	
}
