USE [master]
GO
/****** Object:  Database [KaraokeFantasy]    Script Date: 10/7/2021 11:12:56 AM ******/
CREATE DATABASE [KaraokeFantasy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'KaraokeFantasy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\KaraokeFantasy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'KaraokeFantasy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\KaraokeFantasy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
/****** Object:  Table [dbo].[CTDDP]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDDP](
	[maCTDDP] [nvarchar](255) NOT NULL,
	[ngayDen] [date] NOT NULL,
	[gioDen] [time](7) NOT NULL,
	[trangThaiCTDDP] [nvarchar](255) NOT NULL,
	[maMatHang] [nvarchar](255) NULL,
	[maPhong] [nvarchar](255) NOT NULL,
	[maDDP] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[maCTDDP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHD]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHD](
	[maCTHD] [nvarchar](255) NOT NULL,
	[gioVao] [time](7) NOT NULL,
	[gioRa] [time](7) NOT NULL,
	[soLuong] [int] NOT NULL,
	[trangThaiCTHD] [nvarchar](255) NOT NULL,
	[phuThu] [nvarchar](255) NOT NULL,
	[maHoaDon] [nvarchar](255) NOT NULL,
	[maMatHang] [nvarchar](255) NULL,
	[maPhong] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_CTHD] PRIMARY KEY CLUSTERED 
(
	[maCTHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonDatPhong]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonDatPhong](
	[maDDP] [nvarchar](255) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[maKhachHang] [nvarchar](255) NOT NULL,
	[maNhanVien] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_DonDatPhong] PRIMARY KEY CLUSTERED 
(
	[maDDP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nvarchar](255) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[maKhachHang] [nvarchar](255) NOT NULL,
	[maNhanVien] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](255) NOT NULL,
	[tenKH] [nvarchar](255) NOT NULL,
	[sdt] [nvarchar](255) NOT NULL,
	[cccd] [varchar](12) NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](255) NULL,
	[diemTichLuy] [int] NULL,
	[ngayDangKy] [date] NOT NULL,
	[maLoaiKH] [nvarchar](255) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiKH]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiKH](
	[maLoaiKH] [nvarchar](255) NOT NULL,
	[tenLoaiKH] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_LoaiKH] PRIMARY KEY CLUSTERED 
(
	[maLoaiKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiMatHang]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiMatHang](
	[maLoaiMatHang] [nvarchar](255) NOT NULL,
	[tenLoaiMatHang] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_LoaiMatHang] PRIMARY KEY CLUSTERED 
(
	[maLoaiMatHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](255) NOT NULL,
	[tenLoaiPhong] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_LoaiPhong] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MatHang]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MatHang](
	[maMatHang] [nvarchar](255) NOT NULL,
	[tenMatHang] [nvarchar](255) NOT NULL,
	[soLuongMH] [int] NOT NULL,
	[giaMatHang] [float] NOT NULL,
	[maLoaiMatHang] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_MatHang] PRIMARY KEY CLUSTERED 
(
	[maMatHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](255) NOT NULL,
	[tenNhanVien] [nvarchar](255) NOT NULL,
	[chucVu] [nvarchar](255) NOT NULL,
	[gioiTinh] [nvarchar](255) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[sdt] [nvarchar](255) NOT NULL,
	[cccd] [varchar](12) NOT NULL,
	[luong] [float] NOT NULL,
	[caLamViec] [int] NOT NULL,
	[maTaiKhoan] [nvarchar](255) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](255) NOT NULL,
	[tinhTrangPhong] [nvarchar](255) NOT NULL,
	[giaPhong] [float] NOT NULL,
	[maLoaiPhong] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Phong] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 10/7/2021 11:12:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTK] [nvarchar](255) NOT NULL,
	[matKhau] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTDDP] ([maCTDDP], [ngayDen], [gioDen], [trangThaiCTDDP], [maMatHang], [maPhong], [maDDP]) VALUES (N'CTDDP001', CAST(N'2021-01-10' AS Date), CAST(N'20:15:00' AS Time), N'Đã nhận phòng', N'MH001
', N'P001', N'DDP001')
INSERT [dbo].[CTDDP] ([maCTDDP], [ngayDen], [gioDen], [trangThaiCTDDP], [maMatHang], [maPhong], [maDDP]) VALUES (N'CTDDP002', CAST(N'2021-03-10' AS Date), CAST(N'15:35:00' AS Time), N'Chờ nhận phòng', N'MH002
', N'P002', N'DDP002')
INSERT [dbo].[CTDDP] ([maCTDDP], [ngayDen], [gioDen], [trangThaiCTDDP], [maMatHang], [maPhong], [maDDP]) VALUES (N'CTDDP003', CAST(N'2021-04-12' AS Date), CAST(N'10:30:00' AS Time), N'Đã nhận phòng', N'MH003
', N'P003', N'DDP002')
INSERT [dbo].[CTDDP] ([maCTDDP], [ngayDen], [gioDen], [trangThaiCTDDP], [maMatHang], [maPhong], [maDDP]) VALUES (N'CTDDP004', CAST(N'2021-04-12' AS Date), CAST(N'16:00:00' AS Time), N'Hủy', N'MH003
', N'P005', N'DDP002')
INSERT [dbo].[CTDDP] ([maCTDDP], [ngayDen], [gioDen], [trangThaiCTDDP], [maMatHang], [maPhong], [maDDP]) VALUES (N'CTDDP005', CAST(N'2020-12-25' AS Date), CAST(N'17:00:00' AS Time), N'Đã nhận phòng', NULL, N'P006', N'DDP005')
GO
INSERT [dbo].[CTHD] ([maCTHD], [gioVao], [gioRa], [soLuong], [trangThaiCTHD], [phuThu], [maHoaDon], [maMatHang], [maPhong]) VALUES (N'CTHD001', CAST(N'15:20:00' AS Time), CAST(N'17:30:00' AS Time), 2, N'Đã thanh toán', N'Không', N'HD001', N'MH005
', N'P001')
GO
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP001', CAST(N'2021-01-10' AS Date), N'KH001', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP002', CAST(N'2021-03-10' AS Date), N'KH002', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP003', CAST(N'2021-04-12' AS Date), N'KH001', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP004', CAST(N'2020-12-20' AS Date), N'KH003', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP005', CAST(N'2020-12-25' AS Date), N'KH002', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP006', CAST(N'2021-10-02' AS Date), N'KH004', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP007', CAST(N'2021-09-20' AS Date), N'KH001', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP008', CAST(N'2021-09-05' AS Date), N'KH003', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP009', CAST(N'2021-08-24' AS Date), N'KH005', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP010', CAST(N'2021-07-15' AS Date), N'KH006', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP011', CAST(N'2021-05-01' AS Date), N'KH007', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP012', CAST(N'2021-03-29' AS Date), N'KH008', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP013', CAST(N'2020-11-28' AS Date), N'KH010', N'QL002')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP014', CAST(N'2020-11-01' AS Date), N'KH011', N'TN003')
INSERT [dbo].[DonDatPhong] ([maDDP], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'DDP015', CAST(N'2020-10-10' AS Date), N'KH012', N'TN003')
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'HD001', CAST(N'2021-04-01' AS Date), N'KH001', N'TN003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKhachHang], [maNhanVien]) VALUES (N'HD002', CAST(N'2021-10-02' AS Date), N'KH002', N'QL002')
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH001', N'Đoàn Phạm Bích Hợp', N'0708985897', N'056546236412', N'1358 Quang Trung, P.14, Q.Gò Vấp', CAST(N'2001-03-01' AS Date), N'Nữ', 23, CAST(N'2021-08-03' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH002', N'Nguyễn Thị Tường Vy', N'0383068801', N'056523326647', N'1374/1c tổ 11 khu phố 4, P.An Phú Đông, Q.12', CAST(N'2003-10-27' AS Date), N'Nữ', 12, CAST(N'2021-09-21' AS Date), N'LKH002')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH003', N'Lê Hữu Nam Kha', N'0936903351', N'079201011268', N'597/29/6 Quang Trung, P.11', CAST(N'2001-11-02' AS Date), N'Nam', 41, CAST(N'2021-06-10' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH004', N'Bùi Thị Thanh Tuyền', N'0968105419', N'079301015409', N'11/1B ấp Nam Lân Bà Điểm, Hóc Môn', CAST(N'2001-01-25' AS Date), N'Nữ', 51, CAST(N'2021-02-21' AS Date), N'LKH002')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH005', N'Phạm Hà Minh Hương', N'0916836933
', N'079301231302', N'16C5 đường DN3, P.Tân Hưng Thuận, Q.12', CAST(N'2001-10-10' AS Date), N'Nữ', 7, CAST(N'2021-05-19' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH006', N'Hồ Tuấn Ngọc', N'0777959011', N'079201031493', N'66 Thống Nhất, P.10, Q.Gò Vấp', CAST(N'2001-08-27' AS Date), N'Nam', 12, CAST(N'2021-03-02' AS Date), N'LKH002')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH007', N'Thái Doãn Hoàng An', N'0901762781', N'079203004825', N'220/120 đường số 10, P.9, Q.Gò Vấp', CAST(N'2003-01-25' AS Date), N'Nam', 87, CAST(N'2021-01-30' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH008', N'Đặng Phước Tuyền', N'0932012306', N'079201000674', N'157/20 Phạm Văn Chiêu, P.14, Q.Gò Vấp ', CAST(N'2001-01-01' AS Date), N'Nam', 12, CAST(N'2021-01-23' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH009', N'Trần Phương Uyên', N'0797935560', N'079301013322', N'47/3A Trung Lân, Bà Điểm, Hóc Môn', CAST(N'2001-11-06' AS Date), N'Nữ', 32, CAST(N'2021-04-12' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH010', N'Phan Thị Hồng Nhung', N'0357986976', N'079301013322', N'72/3 Văn Chung, P.13, Q.Tân Bình', CAST(N'1992-04-05' AS Date), N'Nữ', 23, CAST(N'2021-03-02' AS Date), N'LKH002')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH011', N'Trương Gia Lâm', N'0964734410', N'072010061791', N'88/964 Lê Đức Thọ, Q.Gò Vấp', CAST(N'2000-04-20' AS Date), N'Nam', 4, CAST(N'2020-12-09' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH012', N'Nguyễn Phạm Phương Uyên', N'0932543143', N'079201609129', N'15 Nguyễn Kiệm, Q.Gò Vấp', CAST(N'1999-09-11' AS Date), N'Nữ', 5, CAST(N'2021-01-01' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH013', N'Vũ Hoang Lan Anh', N'0777618759', N'079201031607', N'21A Nguyễn Công Trứ, Q.5', CAST(N'1986-11-24' AS Date), N'Nữ', 12, CAST(N'2021-04-21' AS Date), N'LKH002')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH014', N'Nguyễn Trần Trường Thắng', N'0123022771', N'072010567122', N'42/10/2 Đoàn Thị Điểm, Q.Phú Nhuận', CAST(N'1991-01-02' AS Date), N'Nam', 45, CAST(N'2021-03-21' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH015', N'Đinh Nhất Thắng', N'0320613210', N'079121032845', N'69 Điện Biên Phủ, Q.3', CAST(N'1995-04-20' AS Date), N'Nam', 34, CAST(N'2021-01-24' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH016', N'Trần Tiến Đạt', N'0342010311', N'079152528456', N'42 Hòa Hưng, Q.3', CAST(N'1997-03-19' AS Date), N'Nam', 12, CAST(N'2021-05-21' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH017', N'Nguyễn Thị Tuyết Nhi', N'0352215311', N'072340127147', N'48 Phố Mỹ Hưng, Q.7', CAST(N'2000-02-20' AS Date), N'Nữ', 42, CAST(N'2020-11-20' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH018', N'Đoàn Nguyễn Anh Tuấn', N'0777527724', N'07952652845', N'7 Nguyễn Văn Linh, Q.7', CAST(N'2003-01-20' AS Date), N'Nam', 53, CAST(N'2021-03-14' AS Date), N'LKH001')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH019', N'Trần Đức Bo', N'0779769831', N'079201013276', N'8/16/1 TX40 tổ 38 khu phố 3, P.Thạnh Xuân, Q.12', CAST(N'2001-01-21' AS Date), N'Nam', 12, CAST(N'2021-06-21' AS Date), N'LKH003')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKH], [sdt], [cccd], [diaChi], [ngaySinh], [gioiTinh], [diemTichLuy], [ngayDangKy], [maLoaiKH]) VALUES (N'KH020', N'Tăng Thị Lan Anh', N'0779324831', N'072340122361', N'53 Thống Nhất, Q.Gò Vấp', CAST(N'2001-05-19' AS Date), N'Nữ', 42, CAST(N'2021-02-20' AS Date), N'LKH002')
GO
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH001', N'Khách hàng thường')
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH002', N'Thành viên thường')
INSERT [dbo].[LoaiKH] ([maLoaiKH], [tenLoaiKH]) VALUES (N'LKH003', N'Thành viên VIP')
GO
INSERT [dbo].[LoaiMatHang] ([maLoaiMatHang], [tenLoaiMatHang]) VALUES (N'LMH001
', N'Đồ uống')
INSERT [dbo].[LoaiMatHang] ([maLoaiMatHang], [tenLoaiMatHang]) VALUES (N'LMH002', N'Đồ ăn')
INSERT [dbo].[LoaiMatHang] ([maLoaiMatHang], [tenLoaiMatHang]) VALUES (N'LMH003', N'Khác')
GO
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP001', N'Phòng thường')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP002', N'Phòng trung')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'LP003', N'Phòng VIP')
GO
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH001
', N'Khăn giấy
', 5000, 5000, N'LMH003')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH002
', N'Bia Heniken Silver
', 640, 45000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH003
', N'Bia Heniken
', 1200, 40000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH004
', N'Bia Tiger Silver
', 640, 35000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH005
', N'Bia Tiger
', 2400, 30000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH006
', N'Bia Sài Gòn
', 2400, 28000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH007
', N'Bia Ruby
', 960, 25000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH008
', N'Coca Cola
', 1200, 20000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH009
', N'Pepsi
', 1200, 20000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH010
', N'StrongBow táo
', 480, 35000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH011
', N'StrongBow mật ong
', 480, 35000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH012
', N'StrongBow dâu
', 480, 35000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH013
', N'StrongBow dâu đen
', 480, 35000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH014
', N'Trái cây tổng hợp
', 50, 80000, N'LMH002')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH015
', N'Bia Corona
', 640, 45000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH016
', N'Bánh Snack
', 200, 20000, N'LMH002')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH017
', N'Không độ
', 480, 20000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH018
', N'Trà ô lông
', 1200, 25000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH019
', N'Wake Up 247
', 1200, 25000, N'LMH001
')
INSERT [dbo].[MatHang] ([maMatHang], [tenMatHang], [soLuongMH], [giaMatHang], [maLoaiMatHang]) VALUES (N'MH020
', N'Bò húc 
', 1200, 30000, N'LMH001
')
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [maTaiKhoan]) VALUES (N'PV001', N'Phạm Vũ Hoài An', N'Nhân viên phục vụ', N'Nữ', CAST(N'2000-04-20' AS Date), N'60 Thống Nhất, p10, Q.Gò Vấp', N'0966105479', N'079001013302', 200000, 1, N'PV001')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [maTaiKhoan]) VALUES (N'QL002', N'Trần Thanh Thiện', N'Nhân viên quản lý', N'Nam', CAST(N'1990-11-09' AS Date), N'34 Nguyễn Văn Linh, Q.7', N'0977531720', N'079010814123', 200000, 3, N'QL002')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [chucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [luong], [caLamViec], [maTaiKhoan]) VALUES (N'TN003', N'Nguyễn Thu Phương', N'Nhân viên thu ngân', N'Nữ', CAST(N'1995-03-03' AS Date), N'15 Nguyễn Kiệm, Q.Gò Vấp', N'0938135702', N'079201553276', 200000, 2, N'TN003')
GO
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P001', N'Đã đặt', 120000, N'LP001')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P002', N'Trống', 250000, N'LP001')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P003', N'Đã đặt', 250000, N'LP002')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P004', N'Trống', 150000, N'LP003')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P005', N'Trống', 120000, N'LP002')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P006', N'Trống', 250000, N'LP002')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P007', N'Đã đặt', 300000, N'LP003')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P008', N'Trống', 120000, N'LP001')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P009', N'Trống', 250000, N'LP002')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P010', N'Đã đặt', 300000, N'LP003')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P011', N'Đã đặt', 150000, N'LP003')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P012', N'Trống', 250000, N'LP002')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P013', N'Đã đặt', 250000, N'LP001')
INSERT [dbo].[Phong] ([maPhong], [tinhTrangPhong], [giaPhong], [maLoaiPhong]) VALUES (N'P014', N'Đã đặt', 120000, N'LP002')
GO
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'PV001', N'PV001hoaian')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'QL002', N'QL003thanhthien')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau]) VALUES (N'TN003', N'TN002thuphuong')
GO
ALTER TABLE [dbo].[CTDDP]  WITH CHECK ADD  CONSTRAINT [FK_CTDDP_DonDatPhong] FOREIGN KEY([maDDP])
REFERENCES [dbo].[DonDatPhong] ([maDDP])
GO
ALTER TABLE [dbo].[CTDDP] CHECK CONSTRAINT [FK_CTDDP_DonDatPhong]
GO
ALTER TABLE [dbo].[CTDDP]  WITH CHECK ADD  CONSTRAINT [FK_CTDDP_MatHang] FOREIGN KEY([maMatHang])
REFERENCES [dbo].[MatHang] ([maMatHang])
GO
ALTER TABLE [dbo].[CTDDP] CHECK CONSTRAINT [FK_CTDDP_MatHang]
GO
ALTER TABLE [dbo].[CTDDP]  WITH CHECK ADD  CONSTRAINT [FK_CTDDP_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[CTDDP] CHECK CONSTRAINT [FK_CTDDP_Phong]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_maHoaDon] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_maHoaDon]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_maPhong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_maPhong]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_MatHang] FOREIGN KEY([maMatHang])
REFERENCES [dbo].[MatHang] ([maMatHang])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_MatHang]
GO
ALTER TABLE [dbo].[DonDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_maKhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[DonDatPhong] CHECK CONSTRAINT [FK_DonDatPhong_maKhachHang]
GO
ALTER TABLE [dbo].[DonDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DonDatPhong_maNhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[DonDatPhong] CHECK CONSTRAINT [FK_DonDatPhong_maNhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_maKhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_maKhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_maHoaDon_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_maHoaDon_NhanVien]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_maLoaiKH] FOREIGN KEY([maLoaiKH])
REFERENCES [dbo].[LoaiKH] ([maLoaiKH])
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_maLoaiKH]
GO
ALTER TABLE [dbo].[MatHang]  WITH CHECK ADD  CONSTRAINT [FK_MatHang_maLoaiMatHang] FOREIGN KEY([maLoaiMatHang])
REFERENCES [dbo].[LoaiMatHang] ([maLoaiMatHang])
GO
ALTER TABLE [dbo].[MatHang] CHECK CONSTRAINT [FK_MatHang_maLoaiMatHang]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TaiKhoan] FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTK])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_LoaiPhong1] FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_LoaiPhong1]
GO
USE [master]
GO
ALTER DATABASE [KaraokeFantasy] SET  READ_WRITE 
GO
