-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 08, 2020 lúc 10:00 PM
-- Phiên bản máy phục vụ: 10.4.13-MariaDB
-- Phiên bản PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `id14296461_db_nhanvien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ghidanh`
--

CREATE TABLE `ghidanh` (
  `stt` int(11) NOT NULL,
  `msnv` varchar(255) NOT NULL,
  `thang` varchar(5) NOT NULL,
  `ngaytao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ghidanh`
--

INSERT INTO `ghidanh` (`stt`, `msnv`, `thang`, `ngaytao`) VALUES
(2, '17503131', '5', '2020-05-18 00:00:00'),
(3, '17503131', '5', '2020-05-28 00:00:00'),
(4, '17503131', '7', 'Jul 8, 2020'),
(5, '17503131', '5', '2020-05-18 00:00:00'),
(6, '17503131', '5', '2020-05-18 00:00:00'),
(7, '17520101', '7', 'Jul 8, 2020'),
(8, '17520101', '7', 'Jul 8, 2020'),
(9, '17520101', '6', '2020-06-18 00:00:00'),
(12, '17520201', '7', '2020-07-08 00:00:00'),
(13, '17520201', '7', '2020-07-08 00:00:00'),
(17, '17503131', '6', '2020-06-28 00:00:00'),
(18, '17503131', '6', '2020-06-08 04:42:40'),
(20, '17503131', '07', '2020-07-08 05:48:32'),
(21, '17503131', '07', '2020-07-08 13:37:35'),
(22, '17503131', '07', '2020-07-08 14:14:05'),
(23, '17503131', '07', '2020-07-08 14:16:03'),
(28, '17503131', '07', '2020-07-09 00:45:33'),
(29, '17520201', '07', '2020-07-09 01:54:03');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `ma` varchar(20) NOT NULL,
  `matkhau` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ten` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`ma`, `matkhau`, `status`, `ten`, `email`) VALUES
('10052121', '$2y$10$rF9HEgH3iEkZZw43nn4CvOLt9jrrRVbA38Sk3adCFfRUdE6TlqYfa', 1, 'Lê Quốc Hoàng', '10052121@gmail.com'),
('1009900', '$2y$10$2SerVJec3tamCrbL/1B6sOBhaUOQ7Rb/GePkag4cJ6KWVcxSlMK56', 1, 'Nguyễn Đình Thi', 'dinhthi1965@gmail.com'),
('15019651', '12', 0, 'Nguyễn Thị Thanh Thảo', 'thanhthao@gmail.com'),
('15022571', '1', 0, 'Phạm Nguyễn Minh Trí', 'minhtri@gmail.com'),
('15029061', '1', 0, 'Bùi Nguyễn Minh Trung', '15029061@gmail.com'),
('15094631', '1', 0, 'Nguyễn Minh Thiên', '15094631@gmail.com'),
('17501010', '1', 0, 'Hoang Van Thu', 'vanthu@gmail.com'),
('17503131', '$2y$10$Lu9wXJgUjKLdZEQym9WQIePOKmH31oiJnbRunnJ/25UPTML/5tQKG', 0, 'Ngo Thuy Trang', 'thuytrang@gmail.com'),
('17520101', '$2y$10$jm8IwILow1lcjkN7hlgCUuDo1Yq7RqUpucmihtOt1Jjv1l2YxbyXu', 0, 'Le Van Quyet', 'vanquyet12@gmail.com'),
('17520201', '$2y$10$v7OOYp/w3hq3/dQBSq/kiuzvLM1moiXFz66m6ubf/wsX0drQsh2YW', 0, 'Cao Ba Hung', 'bahung@gmail.com'),
('17550101', '12', 0, 'Hoang Van VIet', 'vanviet@gmail.com'),
('72562', '12', 0, 'Le Hoa Hau', 'hoahau@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongbao`
--

CREATE TABLE `thongbao` (
  `mathongbao` varchar(20) NOT NULL,
  `ma` varchar(20) NOT NULL,
  `tenthongbao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `noidung` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `ngaytao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `thongbao`
--

INSERT INTO `thongbao` (`mathongbao`, `ma`, `tenthongbao`, `noidung`, `ngaytao`) VALUES
('2', '10052121', 'Nghỉ lễ', 'Nghỉ lễ quốc tế thiếu nhi', '2019-03-22 00:00:00'),
('3', '10052121', 'Tăng ca', 'Tăng ca lễ thiếu nhi', '2019-03-22 00:00:00'),
('4', '1009900', 'Nghỉ lễ', 'Nghỉ lễ quốc khánh', '2019-03-24 00:00:00'),
('5', '1009900', 'Tăng ca', 'Tăng ca lễ quốc khánh', '2019-03-24 00:00:00'),
('6', '10052121', 'Du dai hoi', '7h sáng mai tai cong ty', '2020-07-07 11:25:05');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ghidanh`
--
ALTER TABLE `ghidanh`
  ADD PRIMARY KEY (`stt`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`ma`);

--
-- Chỉ mục cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`mathongbao`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `ghidanh`
--
ALTER TABLE `ghidanh`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
