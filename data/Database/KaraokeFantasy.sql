USE [master]
GO
/****** Object:  Database [KaraokeFantasy]    Script Date: 10/21/2021 12:15:25 PM ******/
CREATE DATABASE [KaraokeFantasy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'KaraokeFantasy', FILENAME = N'D:\Quang Tuan\database_KaraokeFantasy\KaraokeFantasy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'KaraokeFantasy_log', FILENAME = N'D:\Quang Tuan\database_KaraokeFantasy\KaraokeFantasy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [KaraokeFantasy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [KaraokeFantasy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [KaraokeFantasy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET ARITHABORT OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [KaraokeFantasy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [KaraokeFantasy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET  DISABLE_BROKER 
GO
ALTER DATABASE [KaraokeFantasy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [KaraokeFantasy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET RECOVERY FULL 
GO
ALTER DATABASE [KaraokeFantasy] SET  MULTI_USER 
GO
ALTER DATABASE [KaraokeFantasy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [KaraokeFantasy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [KaraokeFantasy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [KaraokeFantasy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [KaraokeFantasy] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [KaraokeFantasy] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'KaraokeFantasy', N'ON'
GO
ALTER DATABASE [KaraokeFantasy] SET QUERY_STORE = OFF
GO
USE [KaraokeFantasy]
GO
/****** Object:  Table [dbo].[CTDDP]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDDP](
	[maDDP] [nvarchar](50) NOT NULL,
	[maMH] [nvarchar](50) NOT NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_CTDDP] PRIMARY KEY CLUSTERED 
(
	[maDDP] ASC,
	[maMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHD]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHD](
	[maHD] [nvarchar](50) NOT NULL,
	[maMH] [nvarchar](50) NOT NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_CTHD] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonDatPhong]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonDatPhong](
	[maDDP] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[maKH] [nvarchar](50) NOT NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[ngayLap] [date] NULL,
	[gioDen] [time](7) NULL,
	[ngayDen] [date] NULL,
	[TrangThaiDDP] [nvarchar](255) NULL,
 CONSTRAINT [PK_DonDatPhong] PRIMARY KEY CLUSTERED 
(
	[maDDP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [nvarchar](50) NOT NULL,
	[maPhong] [nvarchar](50) NOT NULL,
	[maKH] [nvarchar](50) NOT NULL,
	[maNhanVien] [nvarchar](50) NOT NULL,
	[ngayLap] [date] NULL,
	[gioVao] [time](7) NULL,
	[gioRa] [time](7) NULL,
	[phuThu] [nvarchar](255) NULL,
	[trangThai] [nvarchar](255) NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](50) NOT NULL,
	[maLoaiKH] [nvarchar](50) NOT NULL,
	[tenKH] [nvarchar](255) NULL,
	[sdt] [nvarchar](255) NULL,
	[cccd] [nvarchar](255) NULL,
	[diaChi] [nvarchar](255) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](255) NULL,
	[diemTichLuy] [int] NULL,
	[ngayDangKy] [date] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiKH]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiKH](
	[maLoaiKH] [nvarchar](50) NOT NULL,
	[tenLoaiKH] [nvarchar](255) NULL,
 CONSTRAINT [PK_LoaiKH] PRIMARY KEY CLUSTERED 
(
	[maLoaiKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiMatHang]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiMatHang](
	[maLoaiMH] [nvarchar](50) NOT NULL,
	[tenLoaiMH] [nvarchar](255) NULL,
 CONSTRAINT [PK_LoaiMatHang] PRIMARY KEY CLUSTERED 
(
	[maLoaiMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tenLoaiPhong] [nvarchar](255) NULL,
 CONSTRAINT [PK_LoaiPhong] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MatHang]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MatHang](
	[maMH] [nvarchar](50) NOT NULL,
	[maLoaiMH] [nvarchar](50) NOT NULL,
	[tenMH] [nvarchar](255) NULL,
	[soLuongMH] [int] NULL,
	[giaMH] [float] NULL,
 CONSTRAINT [PK_MatHang] PRIMARY KEY CLUSTERED 
(
	[maMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](50) NOT NULL,
	[maTK] [nvarchar](50) NOT NULL,
	[tenNhanVien] [nvarchar](255) NULL,
	[chucVu] [nvarchar](255) NULL,
	[gioiTinh] [nvarchar](255) NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](255) NULL,
	[sdt] [nvarchar](255) NULL,
	[cccd] [nvarchar](255) NULL,
	[luong] [float] NULL,
	[caLamViec] [nvarchar](255) NULL,
	[trangThaiLamViec] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](50) NOT NULL,
	[maLoaiPhong] [nvarchar](50) NOT NULL,
	[tinhTrangPhong] [nvarchar](255) NULL,
	[giaPhong] [float] NULL,
 CONSTRAINT [PK_Phong] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 10/21/2021 12:15:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTK] [nvarchar](50) NOT NULL,
	[matKhau] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP001', N'MH001', 10)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP001', N'MH002', 2)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP001', N'MH003', 4)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP001', N'MH004', 1)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP002', N'MH001', 20)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP002', N'MH002', 1)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP002', N'MH010', 5)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP004', N'MH016', 4)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP004', N'MH018', 1)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP005', N'MH001', 10)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP005', N'MH004', 1)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP005', N'MH007', 2)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP005', N'MH009', 5)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP005', N'MH014', 6)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP007', N'MH001', 3)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP007', N'MH014', 3)
INSERT [dbo].[CTDDP] ([maDDP], [maMH], [soLuong]) VALUES (N'DDP007', N'MH017', 2)
GO
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD001', N'MH001', 10)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD001', N'MH002', 2)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD001', N'MH003', 4)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD001', N'MH004', 1)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD002', N'MH001', 20)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD002', N'MH002', 1)
INSERT [dbo].[CTHD] ([maHD], [maMH], [soLuong]) VALUES (N'HD002', N'MH010', 5)
GO
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP001', N'P001', N'KH001', N'NV002', CAST(N'2020-10-11' AS Date), CAST(N'10:30:00' AS Time), CAST(N'2020-10-11' AS Date), N'Đã trả phòng')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP002', N'P001', N'KH002', N'NV002', CAST(N'2020-12-10' AS Date), CAST(N'17:00:00' AS Time), CAST(N'2020-12-10' AS Date), N'Đã trả phòng')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP003', N'P002', N'KH003', N'NV003', CAST(N'2020-12-11' AS Date), CAST(N'15:00:00' AS Time), CAST(N'2020-12-11' AS Date), N'Hủy')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP004', N'P002', N'KH001', N'NV003', CAST(N'2020-11-12' AS Date), CAST(N'18:30:00' AS Time), CAST(N'2020-11-12' AS Date), N'Đã xác nhận')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP005', N'P003', N'KH004', N'NV002', CAST(N'2021-01-01' AS Date), CAST(N'18:00:00' AS Time), CAST(N'2021-01-01' AS Date), N'Đã xác nhận')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP006', N'P003', N'KH003', N'NV002', CAST(N'2021-11-10' AS Date), CAST(N'20:15:00' AS Time), CAST(N'2021-11-10' AS Date), N'Chờ xác nhận')
INSERT [dbo].[DonDatPhong] ([maDDP], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioDen], [ngayDen], [TrangThaiDDP]) VALUES (N'DDP007', N'P004', N'KH005', N'NV003', CAST(N'2021-10-10' AS Date), CAST(N'19:00:00' AS Time), CAST(N'2021-10-10' AS Date), N'Đã xác nhận')
GO
INSERT [dbo].[HoaDon] ([maHD], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioVao], [gioRa], [phuThu], [trangThai]) VALUES (N'HD001', N'P001', N'KH001', N'NV002', CAST(N'2020-10-11' AS Date), CAST(N'10:30:00' AS Time), CAST(N'13:30:00' AS Time), N'Không', N'Đã thanh toán')
INSERT [dbo].[HoaDon] ([maHD], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioVao], [gioRa], [phuThu], [trangThai]) VALUES (N'HD002', N'P001', N'KH002', N'NV002', CAST(N'2020-12-10' AS Date), CAST(N'17:00:00' AS Time), CAST(N'20:00:00' AS Time), N'Không', N'Đã thanh toán')
INSERT [dbo].[HoaDon] ([maHD], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioVao], [gioRa], [phuThu], [trangThai]) VALUES (N'HD003', N'P004', N'KH005', N'NV002', CAST(N'3921-11-15' AS Date), CAST(N'19:00:00' AS Time), CAST(N'00:00:00' AS Time), N'Không', N'Đã thanh toán')
INSERT [dbo].[HoaDon] ([maHD], [maPhong], [maKH], [maNhanVien], [ngayLap], [gioVao], [gioRa], [phuThu], [trangThai]) VALUES (N'HD004', N'P003', N'KH004', N'NV002', CAST(N'3921-11-16' AS Date), CAST(N'18:00:00' AS Time), CAST(N'00:00:00' AS Time), N'Không', N'Đã thanh toán')
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH001', N'LKH001', N'Đoàn Phạm Bích Hợp', N'0708985897', N'056546236412', N'1358 Quang Trung, P.14, Q.Gò Vấp', CAST(N'2001-03-01' AS Date), N'Nữ', 23, CAST(N'2021-08-03' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH002', N'LKH002', N'Nguyễn Thị Tường Vy', N'0383068801', N'056523326647', N'1374/1c tổ 11 khu phố 4, P.An Phú Đông, Q.12', CAST(N'2003-10-27' AS Date), N'Nữ', 12, CAST(N'2021-09-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH003', N'LKH003', N'Lê Hữu Nam Kha', N'0936903351', N'079201011268', N'597/29/6 Quang Trung, P.11', CAST(N'2001-11-02' AS Date), N'Nam', 41, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH004', N'LKH002', N'Bùi Thị Thanh Tuyền', N'0968105419', N'079301015409', N'11/1B ấp Nam Lân Bà Điểm, Hóc Môn', CAST(N'2001-01-25' AS Date), N'Nữ', 51, CAST(N'2021-02-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH005', N'LKH003', N'Phạm Hà Minh Hương', N'0916836933
', N'079301231302', N'16C5 đường DN3, P.Tân Hưng Thuận, Q.12', CAST(N'2001-10-10' AS Date), N'Nữ', 7, CAST(N'2021-05-19' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH006', N'LKH002', N'Hồ Tuấn Ngọc', N'0777959011', N'079201031493', N'66 Thống Nhất, P.10, Q.Gò Vấp', CAST(N'2001-08-27' AS Date), N'Nam', 12, CAST(N'2021-03-02' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH007', N'LKH001', N'Thái Doãn Hoàng An', N'0901762781', N'079203004825', N'220/120 đường số 10, P.9, Q.Gò Vấp', CAST(N'2003-01-25' AS Date), N'Nam', 87, CAST(N'2021-01-30' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH008', N'LKH003', N'Đặng Phước Tuyền', N'0932012306', N'079201000674', N'157/20 Phạm Văn Chiêu, P.14, Q.Gò Vấp ', CAST(N'2001-01-01' AS Date), N'Nam', 12, CAST(N'2021-01-23' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH009', N'LKH001', N'Trần Phương Uyên', N'0797935560', N'079301013322', N'47/3A Trung Lân, Bà Điểm, Hóc Môn', CAST(N'2001-11-06' AS Date), N'Nữ', 32, CAST(N'2021-04-12' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH010', N'LKH002', N'Phan Thị Hồng Nhung', N'0357986976', N'079301013322', N'72/3 Văn Chung, P.13, Q.Tân Bình', CAST(N'1992-04-05' AS Date), N'Nữ', 23, CAST(N'2021-03-02' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH011', N'LKH003', N'Trương Gia Lâm', N'0964734410', N'072010061791', N'88/964 Lê Đức Thọ, Q.Gò Vấp', CAST(N'2000-04-20' AS Date), N'Nam', 4, CAST(N'2020-12-09' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH012', N'LKH001', N'Nguyễn Phạm Phương Uyên', N'0932543143', N'079201609129', N'15 Nguyễn Kiệm, Q.Gò Vấp', CAST(N'1999-09-11' AS Date), N'Nữ', 5, CAST(N'2021-01-01' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH013', N'LKH002', N'Vũ Hoang Lan Anh', N'0777618759', N'079201031607', N'21A Nguyễn Công Trứ, Q.5', CAST(N'1986-11-24' AS Date), N'Nữ', 12, CAST(N'2021-04-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH014', N'LKH003', N'Nguyễn Trần Trường Thắng', N'0123022771', N'072010567122', N'42/10/2 Đoàn Thị Điểm, Q.Phú Nhuận', CAST(N'1991-01-02' AS Date), N'Nam', 45, CAST(N'2021-03-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH015', N'LKH001', N'Đinh Nhất Thắng', N'0320613210', N'079121032845', N'69 Điện Biên Phủ, Q.3', CAST(N'1995-04-20' AS Date), N'Nam', 34, CAST(N'2021-01-24' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH016', N'LKH003', N'Trần Tiến Đạt', N'0342010311', N'079152528456', N'42 Hòa Hưng, Q.3', CAST(N'1997-03-19' AS Date), N'Nam', 12, CAST(N'2021-05-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH017', N'LKH001', N'Nguyễn Thị Tuyết Nhi', N'0352215311', N'072340127147', N'48 Phố Mỹ Hưng, Q.7', CAST(N'2000-02-20' AS Date), N'Nữ', 42, CAST(N'2020-11-20' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH018', N'LKH001', N'Đoàn Nguyễn Anh Tuấn', N'0777527724', N'07952652845', N'7 Nguyễn Văn Linh, Q.7', CAST(N'2003-01-20' AS Date), N'Nam', 53, CAST(N'2021-03-14' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH019', N'LKH003', N'Trần Đức Bo', N'0779769831', N'079201013276', N'8/16/1 TX40 tổ 38 khu phố 3, P.Thạnh Xuân, Q.12', CAST(N'2001-01-21' AS Date), N'Nam', 12, CAST(N'2021-06-21' AS Date))
INSERT [dbo].[KhachHang] ([maKhachHang], [maLoaiKH], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy]) VALUES (N'KH020', N'LKH002', N'Tăng Thị Lan Anh', N'0779324831', N'072340122361', N'53 Thống Nhất, Q.Gò Vấp', CAST(N'2001-05-19' AS Date), N'Nữ', 42, CAST(N'2021-02-20' AS Date))
GO
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH001', N'Khách hàng thường')
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH002', N'Thành viên thường')
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH003', N'Thành viên VIP')
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH004', N'Không còn là khách hàng')
GO
INSERT [dbo].[LoaiMatHang] ([maLoaiMH], [tenLoaiMH]) VALUES (N'LMH001', N'Đồ uống')
INSERT [dbo].[LoaiMatHang] ([maLoaiMH], [tenLoaiMH]) VALUES (N'LMH002', N'Đồ ăn')
INSERT [dbo].[LoaiMatHang] ([maLoaiMH], [tenLoaiMH]) VALUES (N'LMH003', N'Khác')
INSERT [dbo].[LoaiMatHang] ([maLoaiMH], [tenLoaiMH]) VALUES (N'LMH004', N'Ngừng kinh doanh')
GO
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP001', N'Phòng thường')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP002', N'Phòng trung')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP003', N'Phòng VIP')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP004', N'Ngừng hoạt động')
GO
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH001', N'LMH003', N'Khăn giấy', 5000, 5000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH002', N'LMH001', N'Bia Heniken Silver', 640, 45000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH003', N'LMH001', N'Bia Heniken', 1200, 40000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH004', N'LMH001', N'Bia Tiger Silver', 640, 35000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH005', N'LMH001', N'Bia Tiger', 2400, 30000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH006', N'LMH001', N'Bia Sài Gòn', 2400, 28000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH007', N'LMH001', N'Bia Ruby', 960, 25000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH008', N'LMH001', N'Coca Cola', 1200, 20000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH009', N'LMH001', N'Pepsi', 1200, 20000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH010', N'LMH001', N'StrongBow táo', 480, 35000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH011', N'LMH001', N'StrongBow mật ong', 480, 35000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH012', N'LMH001', N'StrongBow dâu', 480, 35000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH013', N'LMH001', N'StrongBow dâu đen', 480, 35000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH014', N'LMH002', N'Trái cây tổng hợp', 50, 80000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH016', N'LMH002', N'Bánh Snack', 200, 20000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH017', N'LMH001', N'Không độ', 480, 20000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH018', N'LMH001', N'Trà ô long', 1200, 25000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH019', N'LMH001', N'Wake Up 247', 1200, 25000)
INSERT [dbo].[MatHang] ([maMH], [maLoaiMH], [tenMH], [soLuongMH], [giaMH]) VALUES (N'MH020', N'LMH001', N'Bò húc ', 1200, 30000)
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [maTK], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [trangThaiLamViec]) VALUES (N'NV001', N'NV001', N'Phạm Vũ Hoài An', N'Phục vụ', N'Nữ', CAST(N'2000-04-20' AS Date), N'60 Thống Nhất, p10, Q.Gò Vấp', N'0966105479', N'079001013302', 200000, N'1', N'Đang làm việc')
INSERT [dbo].[NhanVien] ([maNhanVien], [maTK], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [trangThaiLamViec]) VALUES (N'NV002', N'NV002', N'Trần Thanh Thiện', N'Quản lý', N'Nam', CAST(N'1990-11-09' AS Date), N'34 Nguyễn Văn Linh, Q.7', N'0977531720', N'079010814123', 200000, N'3', N'Đang làm việc')
INSERT [dbo].[NhanVien] ([maNhanVien], [maTK], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [trangThaiLamViec]) VALUES (N'NV003', N'NV003', N'Nguyễn Thu Phương', N'Thu ngân', N'Nữ', CAST(N'1995-03-03' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'0938135702', N'079201553276', 200000, N'2', N'Đang làm việc')
INSERT [dbo].[NhanVien] ([maNhanVien], [maTK], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [trangThaiLamViec]) VALUES (N'NV004', N'NV004', N'Phan Hữu Trọng', N'Quản lý', N'Nam', CAST(N'2001-10-11' AS Date), N'12 Nguyễn Văn Bảo, Gò Vấp, HCM', N'0363435019', N'0791234567', 5000000, N'1', N'Đã nghỉ việc')
GO
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P001', N'LP001', N'Đã đặt', 120000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P002', N'LP001', N'Đang hoạt động', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P003', N'LP002', N'Trống', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P004', N'LP003', N'Trống', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P005', N'LP002', N'Trống', 120000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P006', N'LP002', N'Trống', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P007', N'LP003', N'Đang hoạt động', 300000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P008', N'LP001', N'Trống', 120000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P009', N'LP002', N'Đang hoạt động', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P010', N'LP003', N'Đã đặt', 300000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P011', N'LP003', N'Đã đặt', 150000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P012', N'LP002', N'Trống', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P013', N'LP001', N'Đã đặt', 250000)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [tinhTrangPhong], [giaPhong]) VALUES (N'P014', N'LP002', N'Đã đặt', 120000)
GO
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV001', N'PV001')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV002', N'QL003')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV003', N'TN002')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'NV004', N'TN003')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_NhanVien]    Script Date: 10/21/2021 12:15:26 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [IX_NhanVien] UNIQUE NONCLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CTDDP]  WITH CHECK ADD  CONSTRAINT [FK_CTDDP_DonDatPhong] FOREIGN KEY([maDDP])
REFERENCES [dbo].[DonDatPhong] ([maDDP])
GO
ALTER TABLE [dbo].[CTDDP] CHECK CONSTRAINT [FK_CTDDP_DonDatPhong]
GO
ALTER TABLE [dbo].[CTDDP]  WITH CHECK ADD  CONSTRAINT [FK_CTDDP_MatHang] FOREIGN KEY([maMH])
REFERENCES [dbo].[MatHang] ([maMH])
GO
ALTER TABLE [dbo].[CTDDP] CHECK CONSTRAINT [FK_CTDDP_MatHang]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_HoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_HoaDon]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_MatHang] FOREIGN KEY([maMH])
REFERENCES [dbo].[MatHang] ([maMH])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_MatHang]
GO
ALTER TABLE [dbo].[DonDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DonDatPhong] CHECK CONSTRAINT [FK_DonDatPhong_KhachHang]
GO
ALTER TABLE [dbo].[DonDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DonDatPhong] CHECK CONSTRAINT [FK_DonDatPhong_NhanVien]
GO
ALTER TABLE [dbo].[DonDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DonDatPhong] CHECK CONSTRAINT [FK_DonDatPhong_Phong]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Phong]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_LoaiKH] FOREIGN KEY([maLoaiKH])
REFERENCES [dbo].[LoaiKH] ([maLoaiKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_LoaiKH]
GO
ALTER TABLE [dbo].[MatHang]  WITH CHECK ADD  CONSTRAINT [FK_MatHang_LoaiMatHang] FOREIGN KEY([maLoaiMH])
REFERENCES [dbo].[LoaiMatHang] ([maLoaiMH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MatHang] CHECK CONSTRAINT [FK_MatHang_LoaiMatHang]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TaiKhoan1] FOREIGN KEY([maTK])
REFERENCES [dbo].[TaiKhoan] ([maTK])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan1]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_LoaiPhong] FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_LoaiPhong]
GO
USE [master]
GO
ALTER DATABASE [KaraokeFantasy] SET  READ_WRITE 
GO
