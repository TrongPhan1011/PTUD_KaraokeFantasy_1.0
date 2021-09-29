package entity;

import java.util.Date;

public class CTHD {
	private String maCTHD;
	private Date gioVao;
	private Date gioRa;
	private int soLuong;
	private String trangThaiCTHD;
	private String phuThu;
	private MatHang matHang;
	private Phong phong;
	private HoaDon hoaDon;
	
	private double tongTien = soLuong* matHang.getGiaMatHang();

	public String getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}

	public Date getGioVao() {
		return gioVao;
	}

	public void setGioVao(Date gioVao) {
		this.gioVao = gioVao;
	}

	public Date getGioRa() {
		return gioRa;
	}

	public void setGioRa(Date gioRa) {
		this.gioRa = gioRa;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTrangThaiCTHD() {
		return trangThaiCTHD;
	}

	public void setTrangThaiCTHD(String trangThaiCTHD) {
		this.trangThaiCTHD = trangThaiCTHD;
	}

	public String getPhuThu() {
		return phuThu;
	}

	public void setPhuThu(String phuThu) {
		this.phuThu = phuThu;
	}

	public MatHang getMatHang() {
		return matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public double getTongTien() {
		return tongTien;
	}

	public CTHD(String maCTHD, Date gioVao, Date gioRa, int soLuong, String trangThaiCTHD, String phuThu,
			MatHang matHang, Phong phong, HoaDon hoaDon) {
		super();
		this.maCTHD = maCTHD;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
		this.soLuong = soLuong;
		this.trangThaiCTHD = trangThaiCTHD;
		this.phuThu = phuThu;
		this.matHang = matHang;
		this.phong = phong;
		this.hoaDon = hoaDon;
	}

	public CTHD(String maCTHD) {
		super();
		this.maCTHD = maCTHD;
	}

	public CTHD() {
		super();
	}

	@Override
	public String toString() {
		return "CTHD [maCTHD=" + maCTHD + ", gioVao=" + gioVao + ", gioRa=" + gioRa + ", soLuong=" + soLuong
				+ ", trangThaiCTHD=" + trangThaiCTHD + ", phuThu=" + phuThu + ", matHang=" + matHang + ", phong="
				+ phong + ", hoaDon=" + hoaDon + ", tongTien=" + tongTien + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gioRa == null) ? 0 : gioRa.hashCode());
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
		CTHD other = (CTHD) obj;
		if (gioRa == null) {
			if (other.gioRa != null)
				return false;
		} else if (!gioRa.equals(other.gioRa))
			return false;
		return true;
	}
	
	
}
