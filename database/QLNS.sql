USE [master]
GO
/****** Object:  Database [QuanLyNhaSach]    Script Date: 4/11/2024 7:05:48 PM ******/
CREATE DATABASE [QuanLyNhaSach]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyNhaSach', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyNhaSach.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyNhaSach_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyNhaSach_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyNhaSach] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyNhaSach].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyNhaSach] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyNhaSach] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyNhaSach] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyNhaSach] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyNhaSach] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyNhaSach] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyNhaSach] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyNhaSach] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyNhaSach] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyNhaSach] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyNhaSach] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyNhaSach] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyNhaSach', N'ON'
GO
ALTER DATABASE [QuanLyNhaSach] SET QUERY_STORE = OFF
GO
USE [QuanLyNhaSach]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 4/11/2024 7:05:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[MaHoaDon] [nvarchar](20) NOT NULL,
	[MaSach] [nvarchar](20) NOT NULL,
	[SoLuong] [int] NULL,
	[gia] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC,
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuNhap]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuNhap](
	[MaNhap] [nvarchar](20) NOT NULL,
	[MaSach] [nvarchar](20) NOT NULL,
	[SoLuong] [int] NULL,
	[gia] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhap] ASC,
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DocGia]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DocGia](
	[MaDocGia] [nvarchar](20) NOT NULL,
	[TenDocGia] [nvarchar](100) NULL,
	[mahoadon] [nvarchar](20) NULL,
	[masach] [nvarchar](20) NULL,
	[SDT] [nvarchar](13) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDocGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[giohang]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[giohang](
	[magiohang] [int] IDENTITY(1,1) NOT NULL,
	[masach] [nvarchar](20) NULL,
	[tensach] [nvarchar](200) NULL,
	[gia] [int] NULL,
	[soluong] [int] NULL,
	[mathanhvien] [int] NULL,
	[maNV] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[magiohang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [nvarchar](20) NOT NULL,
	[ngaytao] [datetime] NULL,
	[MaNV] [varchar](20) NULL,
	[mathanhvien] [int] NULL,
	[TongTien] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Luong]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Luong](
	[cap] [varchar](10) NOT NULL,
	[luong] [int] NULL,
	[NgayTraLuong] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[cap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NguoiDung]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NguoiDung](
	[MAND] [varchar](7) NOT NULL,
	[HOTEN] [nvarchar](50) NOT NULL,
	[GIOITINH] [bit] NULL,
	[NGAYSINH] [date] NOT NULL,
	[DIENTHOAI] [varchar](13) NULL,
	[cap] [varchar](10) NULL,
	[MANV] [varchar](20) NOT NULL,
	[NGAYDK] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNhaCC] [nvarchar](20) NOT NULL,
	[TenNhaCC] [nvarchar](100) NULL,
	[Sdt] [nvarchar](20) NULL,
	[DiaChi] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhaCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MANV] [varchar](20) NOT NULL,
	[MATKHAU] [varchar](50) NOT NULL,
	[HOTEN] [nvarchar](50) NOT NULL,
	[EMAIL] [nvarchar](50) NOT NULL,
	[VAITRO] [bit] NULL,
	[IsSuperAdmin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuNhap]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuNhap](
	[MaNhap] [nvarchar](20) NOT NULL,
	[NgayTao] [datetime] NULL,
	[MaNV] [varchar](20) NULL,
	[MaNhaCC] [nvarchar](20) NULL,
	[Tongtien] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[qltacgia]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[qltacgia](
	[matacgia] [int] NOT NULL,
	[tentacgia] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[matacgia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[qlTheLoai]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[qlTheLoai](
	[matheloai] [int] NOT NULL,
	[tentheloai] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[matheloai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sach]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sach](
	[masach] [nvarchar](20) NOT NULL,
	[tensach] [nvarchar](200) NULL,
	[namxb] [int] NULL,
	[nhaXB] [nvarchar](100) NULL,
	[gia] [money] NULL,
	[soluong] [int] NULL,
	[tentacgia] [nvarchar](200) NULL,
	[theloai] [nvarchar](200) NULL,
	[ghichu] [nvarchar](250) NULL,
	[hinh] [varchar](50) NULL,
	[matheloai] [int] NULL,
	[matacgia] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[masach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thanhvien]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thanhvien](
	[mathanhvien] [int] NOT NULL,
	[tenthanhvien] [nvarchar](100) NULL,
	[ngaysinh] [date] NULL,
	[gioitinh] [bit] NULL,
	[diem] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[mathanhvien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHoaDon] ADD  DEFAULT ((0)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[NguoiDung] ADD  DEFAULT ((1)) FOR [GIOITINH]
GO
ALTER TABLE [dbo].[NguoiDung] ADD  DEFAULT (getdate()) FOR [NGAYDK]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [VAITRO]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [IsSuperAdmin]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [DF_Sach_SoLuong]  DEFAULT ((0)) FOR [soluong]
GO
ALTER TABLE [dbo].[thanhvien] ADD  DEFAULT ((0)) FOR [gioitinh]
GO
ALTER TABLE [dbo].[thanhvien] ADD  DEFAULT ((0)) FOR [diem]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDon] FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([masach])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_Sach]
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuNhap_PhieuNhap] FOREIGN KEY([MaNhap])
REFERENCES [dbo].[PhieuNhap] ([MaNhap])
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap] CHECK CONSTRAINT [FK_ChiTietPhieuNhap_PhieuNhap]
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuNhap_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([masach])
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap] CHECK CONSTRAINT [FK_ChiTietPhieuNhap_Sach]
GO
ALTER TABLE [dbo].[DocGia]  WITH CHECK ADD  CONSTRAINT [FK_docgia_sach] FOREIGN KEY([masach])
REFERENCES [dbo].[Sach] ([masach])
GO
ALTER TABLE [dbo].[DocGia] CHECK CONSTRAINT [FK_docgia_sach]
GO
ALTER TABLE [dbo].[giohang]  WITH CHECK ADD  CONSTRAINT [FK_giohang_sach] FOREIGN KEY([masach])
REFERENCES [dbo].[Sach] ([masach])
GO
ALTER TABLE [dbo].[giohang] CHECK CONSTRAINT [FK_giohang_sach]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_hoadon_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MANV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_hoadon_NhanVien]
GO
ALTER TABLE [dbo].[NguoiDung]  WITH CHECK ADD  CONSTRAINT [FK_nguoidung_luong] FOREIGN KEY([cap])
REFERENCES [dbo].[Luong] ([cap])
GO
ALTER TABLE [dbo].[NguoiDung] CHECK CONSTRAINT [FK_nguoidung_luong]
GO
ALTER TABLE [dbo].[NguoiDung]  WITH CHECK ADD  CONSTRAINT [FK_NGUOIDUNG_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
GO
ALTER TABLE [dbo].[NguoiDung] CHECK CONSTRAINT [FK_NGUOIDUNG_NHANVIEN]
GO
ALTER TABLE [dbo].[PhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhap_NhaCungCap] FOREIGN KEY([MaNhaCC])
REFERENCES [dbo].[NhaCungCap] ([MaNhaCC])
GO
ALTER TABLE [dbo].[PhieuNhap] CHECK CONSTRAINT [FK_PhieuNhap_NhaCungCap]
GO
ALTER TABLE [dbo].[PhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhap_NhanNien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MANV])
GO
ALTER TABLE [dbo].[PhieuNhap] CHECK CONSTRAINT [FK_PhieuNhap_NhanNien]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK_Sach_qltacgia] FOREIGN KEY([matacgia])
REFERENCES [dbo].[qltacgia] ([matacgia])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK_Sach_qltacgia]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK_Sach_qltheloai] FOREIGN KEY([matheloai])
REFERENCES [dbo].[qlTheLoai] ([matheloai])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK_Sach_qltheloai]
GO
/****** Object:  StoredProcedure [dbo].[sp_BangLuong]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_BangLuong]
    @thang INT
AS
BEGIN
    SELECT 
        NV.MANV AS 'Mã NV',
        NV.HOTEN AS 'Tên NV',
        CASE 
            WHEN NV.VAITRO = 1 THEN N'Quản lý'
            WHEN NV.VAITRO = 2 THEN N'Nhân viên'
            ELSE 'Khác'
        END AS 'Vai Trò',
        ND.cap AS 'Cấp',
        L.luong AS 'Lương'
    FROM 
        NhanVien NV
    INNER JOIN 
        NguoiDung ND ON NV.MANV = ND.MANV
    INNER JOIN 
        Luong L ON ND.cap = L.cap
    WHERE 
        MONTH(ND.NGAYDK) = @thang
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_getTopDoanhThu]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_getTopDoanhThu]
AS
BEGIN
    -- Tính tổng tiền đã bán
    DECLARE @TongTienBan MONEY;
	DECLARE @TongTienNhap MONEY;
    DECLARE @ThongKe nvarchar(50);
    SELECT @TongTienBan = SUM(TongTien)
    FROM HoaDon;

     -- Tính tổng tiền đã nhập
    SELECT @TongTienNhap = SUM(Tongtien)
    FROM PhieuNhap;

    -- So sánh tổng tiền bán và tổng tiền nhập
    IF @TongTienBan > @TongTienNhap
        SET @ThongKe = N'Đang phát triển';
    ELSE
        SET @ThongKe = N'Có Nguy cơ lỗ vốn';

    SELECT
        @TongTienBan AS TongTienBan,
        @ThongKe AS ThongKe
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_getTopNVBan]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_getTopNVBan]
AS
BEGIN
    -- Lấy mã nhân viên bán hàng nhiều nhất
        DECLARE @TopNV VARCHAR(20);
        DECLARE @TongDon int;
        SELECT TOP 1 @TopNV = MaNV, @TongDon = COUNT(*)
        FROM HoaDon hd
        GROUP BY MaNV
        ORDER BY COUNT(*) DESC;

        -- Lấy tên Nhân viên
        DECLARE @TenNV NVARCHAR(50);
        SELECT @TenNV = HOTEN
        FROM NhanVien
        WHERE MANV = @TopNV;

        -- Tính tổng tiền mà nhân viên này đã bán
        DECLARE @TotalSales MONEY;
        SELECT @TotalSales = SUM(cthd.SoLuong * s.gia)
        FROM ChiTietHoaDon cthd
        INNER JOIN Sach s ON cthd.MaSach = s.masach
        WHERE cthd.MaHoaDon IN (
            SELECT MaHoaDon
            FROM HoaDon
            WHERE MaNV = @TopNV
        );

        -- Trả về kết quả
        SELECT
            @TenNV AS TenNV,
            @TongDon AS SoDon,
            @TotalSales AS TongTien;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_getTopSachBan]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_getTopSachBan]
AS
BEGIN
    -- Lấy sách bán chạy nhất
    DECLARE @TopSach NVARCHAR(20);
    DECLARE @SoLuongBan INT;
    SELECT TOP 1 @TopSach = MaSach, @SoLuongBan = SUM(SoLuong)
    FROM ChiTietHoaDon cthd
    GROUP BY MaSach
    ORDER BY SUM(SoLuong) DESC;

    -- Lấy tên sách
    DECLARE @TenSach NVARCHAR(200);
    SELECT @TenSach = tensach
    FROM Sach
    WHERE masach = @TopSach;

    -- Trả về kết quả
    SELECT
        @TenSach AS TenSach,
        @SoLuongBan AS SoLuongBan;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_SoLuongDonHang]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SoLuongDonHang]
    @MaNV VARCHAR(20)
AS
BEGIN
    SELECT COUNT(*) AS SlgDon
    FROM HoaDon
    WHERE MaNV = @MaNV;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKe]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ThongKe] @thang int
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TongTienDaNhap MONEY;
    DECLARE @TongTienDaBan MONEY;
    DECLARE @TongTienTraLuongNV MONEY;

    -- Tính tổng tiền đã nhập trong tháng
    SELECT @TongTienDaNhap = SUM(TongTien)
    FROM PhieuNhap
    WHERE MONTH(NgayTao) = @thang;

    -- Tính tổng tiền đã bán trong tháng
    SELECT @TongTienDaBan = SUM(TongTien)
    FROM HoaDon
    WHERE MONTH(ngaytao) = @thang;

    -- Tính tổng tiền trả lương NV trong tháng
    SELECT @TongTienTraLuongNV = SUM(Luong)
    FROM NguoiDung nd
    INNER JOIN Luong l ON nd.cap = l.cap
    WHERE MONTH(l.NgayTraLuong) = @thang;

    -- Trả về kết quả
    SELECT
        @TongTienDaNhap AS N'Tổng tiền đã nhập',
        @TongTienDaBan AS N'Tổng tiền đã bán',
        @TongTienTraLuongNV AS N'Tổng tiền trả lương';
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_TongSLNhapBanTonKho]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_TongSLNhapBanTonKho]
    @thang INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TongSoLuongSachNhap INT;
    DECLARE @TongSoLuongSachDaBan INT;
    DECLARE @TongSoLuongSachTrongKho INT;

    -- Tính tổng số lượng sách đã nhập trong tháng
	SELECT @TongSoLuongSachNhap = SUM(C.SoLuong)
	FROM ChiTietPhieuNhap C
	INNER JOIN PhieuNhap P ON C.MaNhap = P.MaNhap
	WHERE MONTH(P.NgayTao) = @thang;


    -- Tính tổng số lượng sách đã bán trong tháng
    SELECT @TongSoLuongSachDaBan = SUM(SoLuong)
    FROM ChiTietHoaDon
    WHERE MONTH((SELECT ngaytao FROM HoaDon WHERE MaHoaDon = ChiTietHoaDon.MaHoaDon)) = @thang;

    -- Tính tổng số lượng sách còn trong kho
    SET @TongSoLuongSachTrongKho = @TongSoLuongSachNhap - @TongSoLuongSachDaBan;

    -- Trả về kết quả
    SELECT 
        @TongSoLuongSachNhap AS N'Tổng số lượng sách đã nhập trong tháng',
        @TongSoLuongSachDaBan AS N'Tổng số lượng sách đã bán trong tháng',
        @TongSoLuongSachTrongKho AS N'Tổng số lượng sách còn trong kho';
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_TongSoLuongSachDaBan_TheoThang]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_TongSoLuongSachDaBan_TheoThang]
    @thang INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TongSoLuongSachDaBan INT;

    -- Tính tổng số lượng sách đã bán theo tháng
    SELECT @TongSoLuongSachDaBan = SUM(ChiTietHoaDon.SoLuong)
    FROM ChiTietHoaDon
    INNER JOIN HoaDon ON ChiTietHoaDon.MaHoaDon = HoaDon.MaHoaDon
    WHERE MONTH(HoaDon.ngaytao) = @thang;

    -- Trả về kết quả
    SELECT @thang as N'Tháng',@TongSoLuongSachDaBan AS N'Tổng số lượng sách đã bán trong tháng' 
END;

/*****************************************************************************************
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_TongTienDaChiTraLuong]
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TongTienChiTraLuong MONEY;

    -- Tính tổng tiền đã chi trả cho lương của nhân viên
    SELECT @TongTienChiTraLuong = SUM(Luong)
    FROM NguoiDung nd
    Inner join Luong l on ND.cap = l.cap;

    -- Trả về kết quả
    SELECT @TongTienChiTraLuong AS N'Tổng tiền đã chi trả cho lương nhân viên';
END;
***/
GO
/****** Object:  StoredProcedure [dbo].[sp_TongTienDaBan_TheoThang]    Script Date: 4/11/2024 7:05:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_TongTienDaBan_TheoThang]
    @thang INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TongTienDaBan MONEY;

    -- Tính tổng tiền đã bán theo tháng
    SELECT @TongTienDaBan = SUM(TongTien)
    FROM HoaDon
    WHERE MONTH(ngaytao) = @thang;

    -- Trả về kết quả
    SELECT @thang as N'Tháng',@TongTienDaBan AS N'Tổng tiền đã bán trong tháng'
END;

/*****************************************************************************************/
INSERT [dbo].[NhanVien] ([MANV], [MATKHAU], [HOTEN], [EMAIL], [CAP], [VAITRO], [IsSuperAdmin]) VALUES ('ADMIN','ADMIN',N'Nguyễn Đình Tuấn','tuanndps36835@fpt.edu.vn',1,1)
INSERT [dbo].[NhanVien] ([MANV], [MATKHAU], [HOTEN], [EMAIL], [CAP], [VAITRO], [IsSuperAdmin]) VALUES ('Khang','khang',N'Đinh Quốc Bảo Khang','khangdqbps36645@fpt.edu.vn',1,1)
INSERT [dbo].[NhanVien] ([MANV], [MATKHAU], [HOTEN], [EMAIL], [CAP], [VAITRO], [IsSuperAdmin]) VALUES ('Khoa','khoa',N'Trần Trọng Đăng Khoa','khoattdps36584@fpt.edu.vn',1,0)
INSERT [dbo].[NhanVien] ([MANV], [MATKHAU], [HOTEN], [EMAIL], [CAP], [VAITRO], [IsSuperAdmin]) VALUES ('Phuc','phuc',N'Nguyễn Hoàng Phúc','phucnhps36595@fpt.edu.vn',1,0)
INSERT [dbo].[NhanVien] ([MANV], [MATKHAU], [HOTEN], [EMAIL], [CAP], [VAITRO], [IsSuperAdmin]) VALUES ('Loi','loi',N'Phạm Hữu Lợi','loiphps36836@fpt.edu.vn',1,0)

INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (1, N'Nguyễn Nhật Ánh')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (2, N'Tô Hoài')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (3, N'Trí Tuệ Việt Nam')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (4, N'Paulo Coelho')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (5, N'Yamada Kanehito')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (6, N'Haruki Murakami')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (7, N'George Orwell')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (8, N'Jane Austen')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (9, N'Agatha Christie')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (10, N'Tranquilizer')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (11, N'J.K. Rowling')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (12, N'Victor Hugo')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (13, N'Mark Twain')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (14, N'Stephen King')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (15, N'Tolstoy')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (16, N'Hồ Chí Minh')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (17, N'Dale Carnegie')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (18, N'Aya kitou')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (19, N'Michelangelo')
INSERT [dbo].[qltacgia] ([matacgia], [tentacgia]) VALUES (20, N'William Shakespear')

INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (1, N'Tiểu Thuyết')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (2, N'Kinh Tế')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (3, N'Kỹ Năng')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (4, N'Văn Học')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (5, N'Khoa Học')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (6, N'Tâm Lý')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (7, N'Trinh Thám')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (8, N'Kỹ Năng Sống')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (9, N'Kỹ Năng Làm Việc')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (10, N'Kỹ Năng Quản Lý')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (11, N'Kỹ Năng Giao Tiếp')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (12, N'Kỹ Năng Tư Duy')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (13, N'Kỹ Năng Phân Tích')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (14, N'Kỹ Năng Lãnh Đạo')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (15, N'Kỹ Năng Tài Chính')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (16, N'Nghệ thuật')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (17, N'Kỹ Năng Marketing')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (18, N'Manga')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (19, N'Light Novel')
INSERT [dbo].[qlTheLoai] ([matheloai], [tentheloai]) VALUES (20, N'Kiến thức tổng hợp')

INSERT [dbo].[Sach] ([masach], [tensach], [namxb], [nhaXB], [gia], [soluong], [tentacgia], [theloai], [ghichu], [hinh], [matheloai], [matacgia]) VALUES (N'S1', N'Frieren - Pháp sư tiễn táng', 2020, N'Kim Đồng', 55000, 200, N'Yamada Kanehito', N'Manga', N'', N'Frieren.jpg', 18, 5)
INSERT [dbo].[Sach] ([masach], [tensach], [namxb], [nhaXB], [gia], [soluong], [tentacgia], [theloai], [ghichu], [hinh], [matheloai], [matacgia]) VALUES (N'S2', N'Tô Bình Yên Vẽ Hạnh Phúc', 2022, N'Phụ nữ Việt Nam', 88000,0200, N'Agatha Christie', N'Tiểu thuyết', N'', N'tbyvhp.jpg', 1, 9)
INSERT [dbo].[Sach] ([masach], [tensach], [namxb], [nhaXB], [gia], [soluong], [tentacgia], [theloai], [ghichu], [hinh], [matheloai], [matacgia]) VALUES (N'S3', N'Đắc Nhân Tâm', 2022, N'Trẻ', 100000, 200, N'Dale Carnegie', N'Kỹ năng sống', N'', N'dnt.jpg', 8, 17)
INSERT [dbo].[Sach] ([masach], [tensach], [namxb], [nhaXB], [gia], [soluong], [tentacgia], [theloai], [ghichu], [hinh], [matheloai], [matacgia]) VALUES (N'S4', N'Nhà Giả Kim', 2022, N'Trẻ', 90000, 200, N'Paulo Coelho', N'Tiểu thuyết', N'', N'ngk.jpg', 1, 4)
INSERT [dbo].[Sach] ([masach], [tensach], [namxb], [nhaXB], [gia], [soluong], [tentacgia], [theloai], [ghichu], [hinh], [matheloai], [matacgia]) VALUES (N'S5', N'Cuốn Theo Chiều Gió', 1936, N'Minh Thắng', 300000, 200, N'Dale Carnegie', N'Kỹ năng sống', N'', N'ctcg.jpg', 8, 17)

INSERT [dbo].[NguoiDung] ([MAND], [HOTEN], [GIOITINH], [NGAYSINH], [DIENTHOAI], [cap], [MANV], [NGAYDK]) VALUES (N'ADMIN', N'Nguyễn Đình Tuấn', 1, CAST(N'2004-08-16' AS Date), N'0783955138', N'Q1', N'ADMIN', CAST(N'2024-03-11' AS Date))
INSERT [dbo].[NguoiDung] ([MAND], [HOTEN], [GIOITINH], [NGAYSINH], [DIENTHOAI], [cap], [MANV], [NGAYDK]) VALUES (N'Khang', N'Đinh Quốc Bảo Khang', 1, CAST(N'2004-08-30' AS Date), N'0865399254', N'Q4', N'Khang', CAST(N'2024-03-11' AS Date))

INSERT [dbo].[NhaCungCap] ([MaNhaCC], [TenNhaCC], [DiaChi], [SDT]) VALUES (N'FHS', N'Công ty cổ phần Phát hành sách TP Hồ Chí Minh - FAHASA', N'60-62 Lê Lợi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', N'02838225798')
INSERT [dbo].[NhaCungCap] ([MaNhaCC], [TenNhaCC], [DiaChi], [SDT]) VALUES (N'NSTT', N'Công Ty Cổ Phần Sách & Thiết Bị Giáo Dục Trí Tuệ', N'187 Giảng Võ, Q. Đống Đa, Hà Nội', N'02438515567')
INSERT [dbo].[NhaCungCap] ([MaNhaCC], [TenNhaCC], [DiaChi], [SDT]) VALUES (N'VLB', N'Công Ty TNHH Văn Hóa Việt Long', N'14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh (TPHCM)', N'02838452708')
INSERT [dbo].[NhaCungCap] ([MaNhaCC], [TenNhaCC], [DiaChi], [SDT]) VALUES (N'VNS', N'Công Ty Cổ Phần Văn Hóa - Nghệ Thuật - Sách', N'36 Lê Văn Hưu, P. Nguyễn Du, Q. Hai Bà Trưng, Hà Nội', N'02439434567')
INSERT [dbo].[NhaCungCap] ([MaNhaCC], [TenNhaCC], [DiaChi], [SDT]) VALUES (N'SGDHN', N'Công Ty Cổ Phần Sách Giáo Dục Tại Thành Phố Hà Nội', N'289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội', N'02462534308')

INSERT [dbo].[PhieuNhap] ([MaNhap], [NgayTao], [MaNV], [MaNhaCC], [Tongtien]) VALUES (N'PN1', CAST(N'2024-01-10 12:25:33.000' AS DateTime), N'Khang', N'FHS', 59400000.00)
INSERT [dbo].[PhieuNhap] ([MaNhap], [NgayTao], [MaNV], [MaNhaCC], [Tongtien]) VALUES (N'PN2', CAST(N'2024-02-12 15:22:33.000' AS DateTime), N'ADMIN', N'NSTT', 57000000.00)
INSERT [dbo].[PhieuNhap] ([MaNhap], [NgayTao], [MaNV], [MaNhaCC], [Tongtien]) VALUES (N'PN3', CAST(N'2024-03-09 11:10:35.000' AS DateTime), N'Khoa', N'VLB', 106500000.00)
INSERT [dbo].[PhieuNhap] ([MaNhap], [NgayTao], [MaNV], [MaNhaCC], [Tongtien]) VALUES (N'PN4', CAST(N'2024-04-08 10:26:13.000' AS DateTime), N'Phuc', N'VNS', 56400000.00)
INSERT [dbo].[PhieuNhap] ([MaNhap], [NgayTao], [MaNV], [MaNhaCC], [Tongtien]) VALUES (N'PN5', CAST(N'2024-04-11 19:55:25.000' AS DateTime), N'Loi', N'SGDHN', 117000000.00)

INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN1', N'S1', 600, 55000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN1', N'S2', 300, 88000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN2', N'S3', 300, 100000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN2', N'S4', 300, 90000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN3', N'S5', 300, 300000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN3', N'S1', 300, 55000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN4', N'S2', 300, 88000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN4', N'S3', 300, 100000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN5', N'S4', 300, 90000.00)
INSERT [dbo].[ChiTietPhieuNhap] ([MaNhap], [MaSach], [SoLuong], [DonGia]) VALUES (N'PN5', N'S5', 300, 300000.00)

INSERT [dbo].[HoaDon] ([MaHoaDon], [NgayTao], [MaNV], [TongTien]) VALUES (N'HD1', CAST(N'2024-02-11 12:25:33.000' AS DateTime), N'Khang', 45100000.00)
INSERT [dbo].[HoaDon] ([MaHoaDon], [NgayTao], [MaNV], [TongTien]) VALUES (N'HD2', CAST(N'2024-03-12 15:22:33.000' AS DateTime), N'Khang', 38000000.00)
INSERT [dbo].[HoaDon] ([MaHoaDon], [NgayTao], [MaNV], [TongTien]) VALUES (N'HD3', CAST(N'2024-03-15 11:10:35.000' AS DateTime), N'Khang', 71000000.00)
INSERT [dbo].[HoaDon] ([MaHoaDon], [NgayTao], [MaNV], [TongTien]) VALUES (N'HD4', CAST(N'2024-04-08 10:26:13.000' AS DateTime), N'Khang', 37600000.00)
INSERT [dbo].[HoaDon] ([MaHoaDon], [NgayTao], [MaNV], [TongTien]) VALUES (N'HD5', CAST(N'2024-04-11 19:55:25.000' AS DateTime), N'Khang', 78000000.00)

INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD1', N'S1', 500, 55000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD1', N'S2', 200, 88000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD2', N'S3', 200, 100000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD2', N'S4', 200, 90000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD3', N'S5', 200, 300000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD3', N'S1', 200, 55000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD4', N'S2', 200, 88000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD4', N'S3', 200, 100000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD5', N'S4', 200, 90000.00)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaSach], [SoLuong], [DonGia]) VALUES (N'HD5', N'S5', 200, 300000.00)


GO
USE [master]
GO
ALTER DATABASE [QuanLyNhaSach] SET  READ_WRITE 
GO

