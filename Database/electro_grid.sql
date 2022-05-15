-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 07:39 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_grid`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billID` varchar(20) NOT NULL,
  `electricityAccountNo` int(11) NOT NULL,
  `meterCode` varchar(11) NOT NULL,
  `month` varchar(20) NOT NULL,
  `issuedDate` varchar(40) NOT NULL,
  `unitsConsumed` int(11) NOT NULL,
  `chargeForUnitsconsumed` double NOT NULL,
  `fixedCharge` double NOT NULL,
  `totalCostOfSupply` decimal(10,0) NOT NULL,
  `startedUnits` int(11) NOT NULL,
  `endedUnits` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billID`, `electricityAccountNo`, `meterCode`, `month`, `issuedDate`, `unitsConsumed`, `chargeForUnitsconsumed`, `fixedCharge`, `totalCostOfSupply`, `startedUnits`, `endedUnits`, `status`) VALUES
('220506184527', 685948930, '76348', 'May-2022', '06-May-2022 18:45:27', 600, 21539, 480, '22019', 100, 700, 'Not Paid');

-- --------------------------------------------------------

--
-- Table structure for table `consumer`
--

CREATE TABLE `consumer` (
  `consumerNo` int(11) NOT NULL,
  `accountNo` int(11) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `nic` varchar(15) NOT NULL,
  `number` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(150) NOT NULL,
  `city` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumer`
--

INSERT INTO `consumer` (`consumerNo`, `accountNo`, `fullName`, `gender`, `nic`, `number`, `email`, `password`, `address`, `city`) VALUES
(29, 456777659, 'Ravi Jayasinghe', 'Male', '199087655678', '7655678899', 'ravii@gmail.com', 'acc123qq', 'Kandy, Peradeniya', 'Kandy'),
(31, 685948930, 'Ravi Amarasinghe', 'Male', '199988766666', '7688666667', 'Ravi123@gmail.com', 'acc984993', 'Trinco, malpara', 'Trinco'),
(32, 646675837, 'Palak Ranaweera', 'Female', '19987488933', '768493749', 'Palakk@gmail.com', 'acc132553', 'Kurunegala, jayamawatha', 'Kurunegala');

-- --------------------------------------------------------

--
-- Table structure for table `meter`
--

CREATE TABLE `meter` (
  `meterID` int(11) NOT NULL,
  `meterCode` varchar(10) NOT NULL,
  `premisesID` varchar(7) NOT NULL,
  `electricityAccountNo` int(10) NOT NULL,
  `manufactureDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `meter`
--

INSERT INTO `meter` (`meterID`, `meterCode`, `premisesID`, `electricityAccountNo`, `manufactureDate`) VALUES
(45635, 'AK76348', 'PGH4326', 456777659, '2008-04-04'),
(45651, 'DR54286', 'HSK5428', 685948930, '2012-02-14'),
(45652, 'LA54823', 'KUR6358', 646675837, '2012-07-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `consumer`
--
ALTER TABLE `consumer`
  ADD PRIMARY KEY (`consumerNo`);

--
-- Indexes for table `meter`
--
ALTER TABLE `meter`
  ADD PRIMARY KEY (`meterID`),
  ADD UNIQUE KEY `meterCode` (`meterCode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consumer`
--
ALTER TABLE `consumer`
  MODIFY `consumerNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `meter`
--
ALTER TABLE `meter`
  MODIFY `meterID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45654;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
