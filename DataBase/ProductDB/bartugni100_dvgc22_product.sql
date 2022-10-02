-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2022 at 12:06 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bartugni100_dvgc22_product`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ADMIN_NAME` varchar(255) NOT NULL,
  `ADMIN_PASSWORD` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ADMIN_NAME`, `ADMIN_PASSWORD`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `STORE` varchar(255) NOT NULL,
  `CATEGORY` varchar(255) NOT NULL,
  `IMAGE` varchar(255) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  `CAMPAIGN` varchar(255) NOT NULL,
  `PRICE` decimal(10,0) NOT NULL,
  `PROD_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`STORE`, `CATEGORY`, `IMAGE`, `TITLE`, `CAMPAIGN`, `PRICE`, `PROD_ID`) VALUES
('NULL', 'NULL', 'https://se.cat-ret.assets.lidl/catalog5media/se/article/7003453/xs/7003453.jpg', 'Kelda soppor', '2 FÖR:', '35', 1),
('NULL', 'NULL', 'https://se.cat-ret.assets.lidl/catalog5media/se/article/7005155/xs/7005155.jpg', 'Nybergs deli fläskytterfilé', 'SUPERPRIS!', '50', 2),
('NULL', 'NULL', 'https://se.cat-ret.assets.lidl/catalog5media/se/article/82031/xs/82031.jpg', 'Isbergssallad', 'SUPERPRIS!', '10', 3),
('NULL', 'NULL', 'https://se.cat-ret.assets.lidl/catalog5media/se/article/80755/xs/80755.jpg', 'Sötpotatis', 'SUPERPRIS!', '20', 4),
('NULL', 'NULL', 'NULL', 'Fazer Fruktkusar/ Frökusar/ Havrefralla', 'UPP TILL -21%', '999', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`PROD_ID`),
  ADD UNIQUE KEY `PROD_ID` (`PROD_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `PROD_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
