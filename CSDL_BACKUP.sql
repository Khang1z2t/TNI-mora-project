-- Tạo CSDL quản lý nhà sách
USE master
CREATE DATABASE QuanLyNhaSach;
USE QuanLyNhaSach;
--Tạo bảng userLogin
create table NhanVien(
	MANV VARCHAR(20) PRIMARY KEY,
	MATKHAU VARCHAR(50) NOT NULL,
	HOTEN NVARCHAR(50) NOT NULL,
	EMAIL nvarchar(50) not null,
	VAITRO BIT DEFAULT 0
);
-- Tạo bảng nhân viên
CREATE TABLE NguoiDung(
	MAND VARCHAR(7) PRIMARY KEY,
	HOTEN NVARCHAR(50) NOT NULL,
	GIOITINH BIT DEFAULT 1,
	NGAYSINH DATE NOT NULL,
	DIENTHOAI VARCHAR(13) null,
	cap varchar(10) null,
	MANV VARCHAR(20) NOT NULL,
	NGAYDK DATE DEFAULT GETDATE()
);	

create table Luong(
	cap varchar(10) primary key,
	luong int null,
);
ALTER TABLE Luong
ADD NgayTraLuong DATE;

-- Cập nhật dữ liệu để đặt ngày trả lương mặc định là ngày cuối cùng của tháng
UPDATE Luong
SET NgayTraLuong = DATEADD(DAY, -1, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0));
-- Default là 0 -> Thủ thư
-- 1 là Quản lý

-- Tạo bảng sách
CREATE TABLE Sach (
	masach nvarchar(20) primary key,
	tensach nvarchar(200) null,
	namxb int null,
	nhaXB nvarchar(100) null,
	gia money,
	soluong int null,
	tentacgia nvarchar(200) null,
	theloai nvarchar(200) null,
	ghichu nvarchar(250) null,
	hinh varchar(50) null,
	matheloai int null,
	matacgia int null,
);

create table thanhvien(
	mathanhvien int primary key,
	tenthanhvien nvarchar(100) null,
	ngaysinh date null,
	gioitinh bit default 0,
	diem int default 0
);

-- Tạo bảng đọc giả
CREATE TABLE DocGia (
    MaDocGia nvarchar(20) PRIMARY KEY,
    TenDocGia nvarchar(100) NULL,
	mahoadon nvarchar(20) null,
	masach nvarchar(20) null,
    SDT nvarchar(13) NULL
);

-- Tạo bảng hóa đơn bán sách
CREATE TABLE HoaDon (
    MaHoaDon nvarchar(20) PRIMARY KEY,
    ngaytao datetime NULL,
    MaNV varchar(20) NULL,
	mathanhvien int null,
	TongTien money null
);
-- Tạo bảng chi tiết hóa đơn bán sách
CREATE TABLE ChiTietHoaDon (
    MaHoaDon nvarchar(20),
	MaSach nvarchar(20),
	SoLuong int default 0,
	gia money,
	primary key(MaHoaDon,MaSach)
);

CREATE TABLE NhaCungCap(
	MaNhaCC nvarchar(20) primary key,
	TenNhaCC nvarchar(100) null,
	Sdt nvarchar(20) null,
	DiaChi nvarchar(200) null
);

CREATE TABLE PhieuNhap(
	MaNhap nvarchar(20) primary key,
	NgayTao datetime null,
	MaNV varchar(20) null,
	MaNhaCC nvarchar(20) null,
	Tongtien money
);


CREATE TABLE ChiTietPhieuNhap(
	MaNhap nvarchar(20),
	MaSach nvarchar(20) ,
	SoLuong int null,
	gia money,
	primary key(MaNhap,MaSach)
);


create table qltacgia(
	matacgia int primary key,
	tentacgia nvarchar(100) null
);

create table qlTheLoai(
	matheloai int primary key,
	tentheloai nvarchar(100) null
);

create table giohang(
    magiohang INT PRIMARY KEY IDENTITY(1,1),
	masach nvarchar(20) null,
	tensach nvarchar(200) null,
	gia int null,
	soluong int null,
	mathanhvien int null,
	maNV varchar(20) null
);
delete from giohang
select * from giohang
update giohang set soluong = 10 where masach = 'S01'
/*
drop table khosach
drop table ChiTietHoaDon
drop table HoaDon
drop table DocGia
drop table sach*/
ALTER TABLE NGUOIDUNG
ADD CONSTRAINT FK_NGUOIDUNG_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV)
-- Phai co Tac gia va the loai truoc moi co thong tin du lieu Tac gia va the loai

alter table nguoidung
add constraint FK_nguoidung_luong foreign key (cap) references luong(cap)

alter table sach
add constraint FK_Sach_qltacgia foreign key (matacgia) references qltacgia(matacgia)

alter table sach
add constraint FK_Sach_qltheloai foreign key (matheloai) references qltheloai(matheloai)

alter table sach
add constraint [DF_Sach_SoLuong] default ((0)) for [SoLuong]

alter table ChiTietHoaDon
add constraint FK_chitiethoadon_hoadon foreign key (mahoadon) references hoadon(mahoadon)

alter table giohang
add constraint FK_giohang_sach foreign key (masach) references sach(masach)

/*
alter table giohang
add constraint FK_giohang_tichdiem foreign key (mathanhvien) references thanhvien(mathanhvien)
*/

alter table docgia
add constraint FK_docgia_sach foreign key (masach) references sach(masach)

alter table hoadon
add constraint FK_hoadon_NhanVien foreign key (MaNV) references NhanVien(MaNV)

/* chay cái này là ăn cứt trình 
alter table hoadon
add constraint FK_hoadon_ThanhVien foreign key (mathanhvien) references thanhvien(mathanhvien)

ALTER TABLE hoadon
DROP CONSTRAINT FK_hoadon_ThanhVien
*/

alter table ChiTietHoaDon
add constraint FK_ChiTietHoaDon_HoaDon foreign key (MaHoaDon) references HoaDon(MaHoaDon)

alter table ChiTietHoaDon
add constraint FK_ChiTietHoaDon_Sach foreign key (MaSach) references Sach(MaSach)

alter table PhieuNhap
add constraint FK_PhieuNhap_Nhanvien foreign key (MaNV) references NhanVien(MaNV)

alter table PhieuNhap
add constraint FK_PhieuNhap_NhaCungCap foreign key (MaNhaCC) references NhaCungCap(MaNhaCC)

alter table ChiTietPhieuNhap
add constraint FK_ChiTietPhieuNhap_PhieuNhap foreign key (MaNhap) references PhieuNhap(MaNhap)

alter table ChiTietPhieuNhap
add constraint FK_ChiTietPhieuNhap_Sach foreign key (MaSach) references Sach(MaSach)

-- Thêm dữ liệu
insert into NHANVIEN(MANV,MATKHAU,HOTEN,EMAIL,VAITRO) values 
('ADMIN','ADMIN',N'Nguyen Dinh Tuan','tuanndps36835@fpt.edu.vn',2)
INSERT INTO NGUOIDUNG VALUES 
('ADMIN', N'Nguyen Dinh Tuan', 0, '08-16-2004', '0783955138','O1', 'ADMIN', GETDATE())
insert into luong values
('O1','5000000'),
('O2','10000000')
-- Thêm dữ liệu vào bảng qlTheLoai
INSERT INTO qlTheLoai (matheloai,tentheloai) VALUES
('1',N'Trinh thám'),
('2',N'Khoa học'),
('3',N'Tâm lý học'),
('4',N'Kỹ năng sống'),
('5',N'Tiểu thuyết'),
('6',N'Sử thi'),
('7',N'Ngôn tình'),
('8',N'Tiểu sử'),
('9',N'Văn hóa'),
('10',N'Kinh tế'),
('11',N'Chính trị'),
('12',N'Tôn giáo'),
('13',N'Thể thao'),
('14',N'Âm nhạc'),
('15',N'Manga'),
('16',N'Kiến thức tổng hợp'),
('17',N'Du lịch'),
('18',N'Tự nhiên'),
('19',N'Tình cảm'),
('20',N'Khoa học viễn tưởng');

-- Thêm dữ liệu vào bảng qltacgia
INSERT INTO qltacgia (matacgia,tentacgia) VALUES
('1',N'Nguyễn Nhật Ánh'),
('2',N'Tô Hoài'),
('3',N'Trí Tuệ Việt Nam'),
('4',N'Paulo Coelho'),
('5',N'Haruki Murakami'),
('6',N'George Orwell'),
('7',N'Jane Austen'),
('8',N'Dan Brown'),
('9',N'Agatha Christie'),
('10',N'Tranquilize'),
('11',N'J.K. Rowling'),
('12',N'Victor Hugo'),
('13',N'Mark Twain'),
('14',N'Stephen King'),
('15',N'Tolstoy'),
('16',N'Kanehito Yamada'),
('17',N'Dale Carnegie'),
('18',N'Aya kitou'),
('19',N'Hồ Chí Minh'),
('20',N'Michelangelo');

insert into NhaCungCap(MaNhaCC, TenNhaCC, SDT, DiaChi) values
('FHS',N'Công ty cổ phần Phát hành sách TP Hồ Chí Minh - FAHASA','02838225798',N'60-62 Lê Lợi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)'),
('NSTT',N'Công Ty Cổ Phần Sách & Thiết Bị Giáo Dục Trí Tuệ','02438515567',N'187 Giảng Võ, Q. Đống Đa, Hà Nội'),
('VLB',N'Công Ty TNHH Văn Hóa Việt Long','02838452708',N'14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh (TPHCM)'),
('SGDHN',N'Công Ty Cổ Phần Sách Giáo Dục Tại Thành Phố Hà Nội','02462534308',N'289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội')
go

ALTER TABLE NhanVien
ADD IsSuperAdmin BIT DEFAULT 0 null;

UPDATE NhanVien
SET IsSuperAdmin = 1

WHERE VAITRO = 2;

/**
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_TongTienGioHang] 
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @TongTien money;

    SELECT @TongTien = SUM(TongTienMoiMon)
    FROM (
        SELECT (gia * soluong) AS TongTienMoiMon
        FROM giohang
    ) AS TongTienMoiMonChoMoiMon;

    SELECT @TongTien AS TongTienGioHang;
END
GO
**/
/*****************************************************************************************/

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
        NguoiDung ND
    INNER JOIN 
        Luong L ON ND.cap = L.cap
    INNER JOIN 
        NhanVien NV ON ND.MANV = NV.MANV
    WHERE 
        MONTH(ND.NGAYDK) = @thang
END;


/*****************************************************************************************/
SELECT DISTINCT MONTH(NGAYDK) MONTH FROM NguoiDung ORDER BY MONTH DESC

/*THONG KE*/
/****************************************************************************************/
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


EXECUTE sp_ThongKe 4
/****************************************************************************************/
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
exec sp_TongSLNhapBanTonKho 4