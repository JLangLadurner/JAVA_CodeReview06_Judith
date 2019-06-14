-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 14, 2019 at 12:45 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CR6_Judith`
--

-- --------------------------------------------------------

--
-- Table structure for table `classRooms`
--

CREATE TABLE `classRooms` (
  `classRoomId` int(11) NOT NULL,
  `className` varchar(20) DEFAULT NULL,
  `fk_teacherId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classRooms`
--

INSERT INTO `classRooms` (`classRoomId`, `className`, `fk_teacherId`) VALUES
(1, '1a', 1),
(2, '1b', 4),
(3, '2a', 4),
(4, '2b', 4),
(5, '3a', 2),
(6, '3b', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(55) DEFAULT NULL,
  `studentSurname` varchar(55) DEFAULT NULL,
  `studentEmail` varchar(55) DEFAULT NULL,
  `fk_classRoomId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentId`, `studentName`, `studentSurname`, `studentEmail`, `fk_classRoomId`) VALUES
(1, 'Jakob', 'Little', 'little@email.com', 1),
(2, 'Tina', 'Kurz', 'kurz@email.com', 1),
(3, 'Simone', 'Meyer', 'meyer@email.com', 2),
(4, 'Konrad', 'Lang', 'lang@email.com', 2),
(5, 'Toni', 'Juma', 'juma@email.com', 3),
(6, 'Lisa', 'Walcher', 'walcher@email.com', 4),
(7, 'Martha', 'Sweet', 'sweet@email.com', 4),
(8, 'David', 'Stone', 'stone@email.com', 5),
(9, 'Memeth', 'Ali', 'ali@email.com', 5),
(10, 'Neha', 'Sethi', 'sethi@email.com', 6),
(11, 'Rajesh', 'Amid', 'amid@email.com', 6);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(55) DEFAULT NULL,
  `teacherSurname` varchar(55) DEFAULT NULL,
  `teacherEmail` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherId`, `teacherName`, `teacherSurname`, `teacherEmail`) VALUES
(1, 'Otto', 'Thinker', 'thinker@email.com'),
(2, 'Conrad', 'Knowsall', 'knowsall@email.com'),
(3, 'Rosetta', 'Stone', 'stone@email.com'),
(4, 'Klara', 'Golightly', 'golightly@email.com'),
(5, 'Christina', 'Goodone', 'goodone@email.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classRooms`
--
ALTER TABLE `classRooms`
  ADD PRIMARY KEY (`classRoomId`),
  ADD KEY `fk_teacherId` (`fk_teacherId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `fk_classRoomId` (`fk_classRoomId`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classRooms`
--
ALTER TABLE `classRooms`
  MODIFY `classRoomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classRooms`
--
ALTER TABLE `classRooms`
  ADD CONSTRAINT `fk_teacherId` FOREIGN KEY (`fk_teacherId`) REFERENCES `teacher` (`teacherId`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_classRoomId` FOREIGN KEY (`fk_classRoomId`) REFERENCES `classRooms` (`classRoomId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
