-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 10, 2017 at 10:26 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dev7_assistance`
--

-- --------------------------------------------------------

--
-- Table structure for table `assists`
--

CREATE TABLE `assists` (
  `ass_id` int(11) NOT NULL,
  `sta_id` int(11) NOT NULL,
  `ass_date` date NOT NULL,
  `ass_time_input` time DEFAULT NULL,
  `ass_time_output` time DEFAULT NULL,
  `ass_state` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assists`
--

INSERT INTO `assists` (`ass_id`, `sta_id`, `ass_date`, `ass_time_input`, `ass_time_output`, `ass_state`, `created_at`, `updated_at`) VALUES
(1, 1, '2017-08-08', NULL, NULL, 1, '2017-08-08 18:19:24', '2017-08-08 18:19:24'),
(2, 2, '2017-08-08', NULL, NULL, 2, '2017-08-08 18:19:24', '2017-08-08 18:19:24'),
(3, 3, '2017-08-08', NULL, NULL, 3, '2017-08-08 18:19:24', '2017-08-08 18:19:24'),
(4, 4, '2017-08-08', NULL, NULL, 1, '2017-08-08 18:19:24', '2017-08-08 18:19:24'),
(5, 5, '2017-08-08', NULL, NULL, 2, '2017-08-08 18:19:24', '2017-08-08 18:19:24'),
(6, 1, '2017-08-09', NULL, NULL, 1, '2017-08-09 13:08:28', '2017-08-09 13:08:28'),
(7, 2, '2017-08-09', NULL, NULL, 1, '2017-08-09 13:08:28', '2017-08-09 13:08:28'),
(8, 3, '2017-08-09', NULL, NULL, 1, '2017-08-09 13:08:28', '2017-08-09 13:08:28'),
(9, 4, '2017-08-09', NULL, NULL, 1, '2017-08-09 13:08:28', '2017-08-09 13:08:28'),
(10, 5, '2017-08-09', '13:08:47', NULL, 2, '2017-08-09 13:08:28', '2017-08-09 13:08:28');

-- --------------------------------------------------------

--
-- Table structure for table `staffs`
--

CREATE TABLE `staffs` (
  `sta_id` int(11) NOT NULL,
  `sta_code` char(8) NOT NULL,
  `sta_surname` varchar(150) NOT NULL,
  `sta_name` varchar(150) NOT NULL,
  `sta_email` varchar(150) NOT NULL,
  `sta_phone_number` varchar(20) NOT NULL,
  `sta_state` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staffs`
--

INSERT INTO `staffs` (`sta_id`, `sta_code`, `sta_surname`, `sta_name`, `sta_email`, `sta_phone_number`, `sta_state`, `created_at`, `updated_at`) VALUES
(1, 'S0001', 'Rueda Diaz', 'Carlos', 'carl.rdj@gmail.com', '987 456 321', 1, '2017-08-07 01:24:55', '2017-08-01 00:00:00'),
(2, 'S0002', 'Sotelo', 'Lady', 'lady@gmail.com', '987 452 655', 1, '2017-08-07 19:39:35', '2017-08-07 19:39:35'),
(3, 'S0003', 'Vergara', 'Keyla', 'keyla@gmail.com', '345 5645', 1, '2017-08-07 19:45:27', '2017-08-07 19:45:27'),
(4, 'S0004', 'Kakaroto', 'Cesar', 'cesar@dios.com', '984 566 121', 1, '2017-08-07 19:45:53', '2017-08-08 21:37:30'),
(5, 'S0005', 'Bernedo', 'Julia', 'julia@gmail.com', '455 4899', 1, '2017-08-07 19:46:00', '2017-08-08 21:36:25'),
(6, 'S0006', 'Hola', 'Mundo', 'hola@mundo.com', '987 566 646', 2, '2017-08-07 19:46:11', '2017-08-07 23:40:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assists`
--
ALTER TABLE `assists`
  ADD PRIMARY KEY (`ass_id`),
  ADD KEY `sta_id` (`sta_id`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`sta_id`),
  ADD UNIQUE KEY `sta_code` (`sta_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assists`
--
ALTER TABLE `assists`
  MODIFY `ass_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `sta_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `assists`
--
ALTER TABLE `assists`
  ADD CONSTRAINT `fk_sta_id` FOREIGN KEY (`sta_id`) REFERENCES `staffs` (`sta_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
