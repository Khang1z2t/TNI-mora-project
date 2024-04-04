-- Tạo CSDL quản lý nhà sách
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
	DIENTHOAI VARCHAR(13),
	EMAIL VARCHAR(50),
	MANV VARCHAR(20) NOT NULL,
	NGAYDK DATE DEFAULT GETDATE()
);

-- Default là 0 -> Thủ thư
-- 1 là Quản lý

-- Tạo bảng sách
CREATE TABLE Sach (
	masach nvarchar(20) primary key,
	tensach nvarchar(200) null,
	namxb int null,
	nhaXB nvarchar(100) null,
	gia money,
	tentacgia nvarchar(200) null,
	theloai nvarchar(200) null,
	ghichu nvarchar(250) null,
	hinh varchar(50) null,
	matheloai nvarchar(20) null,
	matacgia nvarchar(20) null,
	soluong int null,
	ngayton date default getdate() not null
);
-- Tạo bảng đọc giả
CREATE TABLE DocGia (
    MaDocGia int PRIMARY KEY,
    TenDocGia nvarchar(100) NULL,
	mahoadon int null,
	masach nvarchar(20) null,
    SDT nvarchar(13) NULL
);

-- Tạo bảng hóa đơn bán sách
CREATE TABLE HoaDon (
    MaHoaDon int identity(1,1) PRIMARY KEY,
	masach nvarchar(20) null,
    ngaytao date default getdate(),
	madocgia int null,	
    MaNV varchar(20) NULL
);
-- Tạo bảng chi tiết hóa đơn bán sách
CREATE TABLE ChiTietHoaDon (
    MaHoaDon int primary key,
    SoLuong int,
    gia money
);
-- Tạo bảng kho sách
CREATE TABLE KhoSach (
	makho nvarchar(100) primary key,
	maSach nvarchar(20) null,
	tensach nvarchar(100) null,
	soluong int null,
	ghichu nvarchar(100),
	maNV varchar(20) null,
	ngayton date null
);

create table qltacgia(
	matacgia nvarchar(20) primary key,
	tentacgia nvarchar(100) null
);
create table qlTheLoai(
	matheloai nvarchar(20) primary key,
	tentheloai nvarchar(100) null
);
/*
drop table khosach
drop table ChiTietHoaDon
drop table HoaDon
drop table DocGia
drop table sach*/

ALTER TABLE NGUOIDUNG
ADD CONSTRAINT FK_NGUOIDUNG_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV)
-- Phai co Tac gia va the loai truoc moi co thong tin du lieu Tac gia va the loai
alter table sach
add constraint FK_Sach_qltacgia foreign key (matacgia) references qltacgia(matacgia)

alter table sach
add constraint FK_Sach_qltheloai foreign key (matheloai) references qltheloai(matheloai)
-- 
alter table khosach
add constraint FK_khosach_Sach foreign key (masach) references sach(masach)

alter table khosach
add constraint FK_khosach_NhanVien foreign key (maNV) references nhanvien(maNV)

alter table ChiTietHoaDon
add constraint FK_chitiethoadon_hoadon foreign key (mahoadon) references hoadon(mahoadon)

alter table docgia
add constraint FK_docgia_sach foreign key (masach) references sach(masach)

alter table hoadon
add constraint FK_hoadon_sach foreign key (masach) references sach(masach)

alter table hoadon
add constraint FK_hoadon_NhanVien foreign key (manv) references NhanVien(maNV)
-- Thêm dữ liệu
insert into NHANVIEN(MANV,MATKHAU,HOTEN,EMAIL,VAITRO) values 
('ADMIN','ADMIN',N'Nguyen Dinh Tuan','tuanndps36835@fpt.edu.vn',2)
INSERT INTO NGUOIDUNG VALUES 
('ADMIN', N'Nguyen Dinh Tuan', 0, '08-16-2004', '0783955138', 'tuanndps36835@fpt.edu.vn', 'ADMIN', GETDATE())
INSERT INTO NGUOIDUNG VALUES 
('NV1', N'Trần Đăng Khoa', 0, '08-20-2004', '079654321', 'tyngominer@gmail.com', 'NV2', GETDATE())
select * from sach

-- Thêm dữ liệu vào bảng qlTheLoai
INSERT INTO qlTheLoai (matheloai, tentheloai) VALUES
('TL1', N'Trinh thám'),
('TL2', N'Khoa học'),
('TL3', N'Tâm lý học'),
('TL4', N'Kỹ năng sống'),
('TL5', N'Tiểu thuyết'),
('TL6', N'Sử thi'),
('TL7', N'Ngôn tình'),
('TL8', N'Tiểu sử'),
('TL9', N'Văn hóa'),
('TL10', N'Kinh tế'),
('TL11', N'Chính trị'),
('TL12', N'Tôn giáo'),
('TL13', N'Thể thao'),
('TL14', N'Âm nhạc'),
('TL15', N'Manga'),
('TL16', N'Kiến thức tổng hợp'),
('TL17', N'Du lịch'),
('TL18', N'Tự nhiên'),
('TL19', N'Tình cảm'),
('TL20', N'Khoa học viễn tưởng');
-- Thêm dữ liệu vào bảng qltacgia
INSERT INTO qltacgia (matacgia, tentacgia) VALUES
('TG1', N'Nguyễn Nhật Ánh'),
('TG2', N'Tô Hoài'),
('TG3', N'Trí Tuệ Việt Nam'),
('TG4', N'Paulo Coelho'),
('TG5', N'Haruki Murakami'),
('TG6', N'George Orwell'),
('TG7', N'Jane Austen'),
('TG8', N'Dan Brown'),
('TG9', N'Agatha Christie'),
('TG10', N'Tranquilize'),
('TG11', N'J.K. Rowling'),
('TG12', N'Victor Hugo'),
('TG13', N'Mark Twain'),
('TG14', N'Stephen King'),
('TG15', N'Tolstoy'),
('TG16', N'Hạnh Phúc'),
('TG17', N'Dale Carnegie'),
('TG18', N'Maya Angelou'),
('TG19', N'Hồ Chí Minh'),
('TG20', N'Michelangelo');

ALTER TABLE NhanVien
ADD IsSuperAdmin BIT DEFAULT 0 null;
UPDATE NhanVien
SET IsSuperAdmin = 1

WHERE VAITRO = 2;

/****CREATE PROCEDURE sp_HangTon
    @SoLuong INT,
    @NgayTon DATE
AS
BEGIN
    -- Your logic to save the inventory goes here
    -- For example, you might want to insert the data into a table named HangTon
    INSERT INTO HangTon (SoLuong, NgayTon)
    VALUES (@SoLuong, @NgayTon)
END****/

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_HangTon](@ngayton date)
AS BEGIN
	SELECT
		sach.masach AS MaSach,
		sach.tensach AS TenSach,
		sach.soluong AS SoLuong
	FROM sach
	where sach.ngayton = @ngayton
END
GO
SELECT masach,tensach,soluong,ngayton from sach;


update sach
set soluong = null ,ngayton = getdate()

DECLARE @NgayTon DATE = '2024-02-24';
EXEC sp_HangTon @ngayton = @NgayTon;

