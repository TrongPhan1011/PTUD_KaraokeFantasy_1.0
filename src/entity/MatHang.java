package entity;

import java.io.Serializable;

public class MatHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String maMatHang;
	protected String tenMatHang;
	protected int soLuongMatHang;
	protected double giaMatHang;
	
	private LoaiMatHang loaiMatHang;

	public String getMaMatHang() {
		return maMatHang;
	}

	public void setMaMatHang(String maMatHang) {
		this.maMatHang = maMatHang;
	}

	public String getTenMatHang() {
		return tenMatHang;
	}

	public void setTenMatHang(String tenMatHang) {
		this.tenMatHang = tenMatHang;
	}

	public int getSoLuongMatHang() {
		return soLuongMatHang;
	}

	public void setSoLuongMatHang(int soLuongMatHang) {
		this.soLuongMatHang = soLuongMatHang;
	}

	public double getGiaMatHang() {
		return giaMatHang;
	}

	public void setGiaMatHang(double giaMatHang) {
		this.giaMatHang = giaMatHang;
	}

	public LoaiMatHang getLoaiMatHang() {
		return loaiMatHang;
	}

	public void setLoaiMatHang(LoaiMatHang loaiMatHang) {
		this.loaiMatHang = loaiMatHang;
	}

	public MatHang(String maMatHang, String tenMatHang, int soLuongMatHang, double giaMatHang,
			LoaiMatHang loaiMatHang) {
		super();
		this.maMatHang = maMatHang;
		this.tenMatHang = tenMatHang;
		this.soLuongMatHang = soLuongMatHang;
		this.giaMatHang = giaMatHang;
		this.loaiMatHang = loaiMatHang;
	}
	public MatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatHang(String maMatHang) {
		super();
		this.maMatHang = maMatHang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maMatHang == null) ? 0 : maMatHang.hashCode());
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
		MatHang other = (MatHang) obj;
		if (maMatHang == null) {
			if (other.maMatHang != null)
				return false;
		} else if (!maMatHang.equals(other.maMatHang))
			return false;
		return true;
	}
	

}
