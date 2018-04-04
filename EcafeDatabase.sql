-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 04, 2018 at 01:08 AM
-- Server version: 5.1.53
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ecafedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `ItemID` int(11) NOT NULL,
  `ItemName` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`ItemID`, `ItemName`, `Price`, `Quantity`) VALUES
(1, 'Pizza', 10, 10),
(2, 'Pasta', 20, 20),
(3, 'Fries', 10, 10),
(4, 'Burger', 20, 20),
(5, 'Pepsi', 5, 30),
(6, 'Coffee', 15, 20),
(7, 'Chicken', 30, 40),
(8, 'Alfredo', 30, 20),
(9, 'Macroni', 20, 20),
(10, 'Chocolate', 20, 25);

-- --------------------------------------------------------

--
-- Table structure for table `ordereditems`
--

CREATE TABLE IF NOT EXISTS `ordereditems` (
  `ItemID` int(11) NOT NULL,
  `OrderNo` int(11) NOT NULL,
  `OrderStatus` text NOT NULL,
  KEY `ItemID` (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordereditems`
--

INSERT INTO `ordereditems` (`ItemID`, `OrderNo`, `OrderStatus`) VALUES
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(2, 2, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress'),
(1, 1, 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `UserID` int(20) NOT NULL,
  `StaffID` int(20) NOT NULL,
  `OrderNo` int(20) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `Status` text NOT NULL,
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`UserID`, `StaffID`, `OrderNo`, `OrderDate`, `Status`) VALUES
(1, 1, 0, '0000-00-00 00:00:00', 'In Progress'),
(1, 1, 1, '2018-03-31 10:31:21', 'In Progress'),
(1, 2, 2, '2018-03-31 10:31:21', 'In Progress'),
(1, 1, 1, '2018-03-31 10:37:57', 'In Progress'),
(1, 1, 1, '2018-03-31 10:37:57', 'In Progress'),
(1, 1, 1, '2018-03-31 10:38:19', 'In Progress'),
(1, 1, 1, '2018-03-31 10:38:19', 'In Progress'),
(1, 1, 1, '2018-03-31 10:41:58', 'In Progress'),
(1, 1, 1, '2018-03-31 10:41:58', 'In Progress'),
(1, 1, 1, '2018-03-31 10:41:58', 'In Progress'),
(1, 1, 1, '2018-03-31 10:47:44', 'In Progress'),
(1, 1, 1, '2018-03-31 11:54:06', 'In Progress'),
(1, 1, 1, '2018-04-03 17:46:59', 'In Progress'),
(1, 1, 1, '2018-04-03 17:46:59', 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL,
  `FirstName` text NOT NULL,
  `LastName` text NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Pasword` varchar(30) NOT NULL,
  `Rank` varchar(30) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `FirstName`, `LastName`, `Username`, `Pasword`, `Rank`) VALUES
(1, 'Amal', 'Naeem', 'Amal', '123', ''),
(3, 'Maryam', 'Adnan', 'Maryam', '123', '');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ordereditems`
--
ALTER TABLE `ordereditems`
  ADD CONSTRAINT `ordereditems_ibfk_1` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);
