-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2022 at 10:50 PM
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
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `date_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `discount` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `discount`, `image`, `name`, `price`, `quantity`) VALUES
(1, 40, '/images/alice-in-wonderland.jpg', 'alice in wonderland', 70, 5),
(2, 20, '/images/the-little-prince.jpg', 'the little prince', 100, 30),
(3, 15, '/images/kidsbook.png', 'counting numbers', 120, 20),
(4, 0, '/images/hound-of-baskerville.jpg', 'hound of the Baskerville\'s', 80, 3),
(5, 0, '/images/LOR.jpg', 'lord of the rings', 99, 4),
(6, 0, '/images/randombook.png', 'sefer', 30, 9),
(7, 30, 'https://covers.openlibrary.org/b/id/8595092-L.jpg', 'hitchhiker\'s guide to the galaxy', 100, 9),
(8, 5, 'https://images-na.ssl-images-amazon.com/images/I/81DBK93b6jL.jpg', 'a brief history of time', 88, 10),
(9, 15, 'https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781510769175/winnie-the-pooh-9781510769175_hr.jpg', 'winnie the pooh', 68, 7),
(10, 40, 'https://www.judaica-il.com/content/images/thumbs/0003748_-_600.jpeg', 'הידד לגיבור', 70, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
