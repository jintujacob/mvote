-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2013 at 12:33 AM
-- Server version: 5.5.24
-- PHP Version: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mobile_voting`
--

-- --------------------------------------------------------

--
-- Table structure for table `adhaarDB`
--

CREATE TABLE IF NOT EXISTS `adhaarDB` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `adhaar_id` varchar(20) NOT NULL,
  `nameFirst` varchar(30) NOT NULL,
  `nameLast` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adhaar_id` (`adhaar_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `adhaarDB`
--

INSERT INTO `adhaarDB` (`id`, `adhaar_id`, `nameFirst`, `nameLast`, `address`, `phone`) VALUES
(1, '001002003004', 'Jintu', 'Jacob', 'Kalarikkaparambil, Chithrapuzha South, Irimpanam PO, Ernakulam', '9847361387'),
(2, 'UID444', 'AdminFirst', 'AdminLast', 'AdminAddress', '9847361387'),
(3, 'UID888', 'TestFirst', 'TestLast', 'TestAddress', '9847361387'),
(4, 'UID666', 'QATestFirst', 'QATestLast', 'QATestAddress', '9847361387');

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE IF NOT EXISTS `candidates` (
  `cand_id` int(10) NOT NULL AUTO_INCREMENT,
  `cand_name` varchar(40) NOT NULL,
  `cand_logo` varchar(100) NOT NULL,
  `cand_bio` varchar(100) NOT NULL,
  PRIMARY KEY (`cand_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`cand_id`, `cand_name`, `cand_logo`, `cand_bio`) VALUES
(1, 'Candidate1 Piravom', 'logopath', 'candidate1 Piravom bio'),
(2, 'Candidate2 Piravom', 'logopath', 'candidate2 Piravom bio'),
(3, 'Candidate3 Piravom', 'logopath', 'candidate3 Piravom bio'),
(4, 'Candidate4 Aluva', 'logopath', 'candidate4 Aluva bio'),
(5, 'Candidate5 Aluva', 'logopath', 'candidate5 Aluva bio'),
(6, 'Candidate6 Mvtpza', 'logopath', 'candidate6 Mvtpza  bio');

-- --------------------------------------------------------

--
-- Table structure for table `constituencies`
--

CREATE TABLE IF NOT EXISTS `constituencies` (
  `const_id` int(10) NOT NULL AUTO_INCREMENT,
  `const_name` varchar(20) NOT NULL,
  `const_state` varchar(20) NOT NULL,
  PRIMARY KEY (`const_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `constituencies`
--

INSERT INTO `constituencies` (`const_id`, `const_name`, `const_state`) VALUES
(1, 'Piravom', 'Kerala'),
(2, 'Moovatupuzha', 'Kerala'),
(3, 'Aluva', 'Kerala'),
(4, 'Kottayam', 'Kerala'),
(5, 'Chennai', 'Tamil Nadu'),
(6, 'Vishakapatanam', 'Andhra Pradesh');

-- --------------------------------------------------------

--
-- Table structure for table `elections`
--

CREATE TABLE IF NOT EXISTS `elections` (
  `ele_id` int(11) NOT NULL AUTO_INCREMENT,
  `ele_title` varchar(100) NOT NULL,
  `ele_start_dt` date NOT NULL,
  `ele_end_dt` date NOT NULL,
  `ele_desc` text NOT NULL,
  PRIMARY KEY (`ele_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `elections`
--

INSERT INTO `elections` (`ele_id`, `ele_title`, `ele_start_dt`, `ele_end_dt`, `ele_desc`) VALUES
(1, 'LokSabha Elections', '2013-10-10', '2013-11-10', 'Lok Sabha Elections / south zone'),
(2, 'Rajya Sabha Elections', '2013-11-10', '2013-12-10', 'Rajya Sabha Elections / north zone');

-- --------------------------------------------------------

--
-- Table structure for table `elections_consts`
--

CREATE TABLE IF NOT EXISTS `elections_consts` (
  `unit_ele_id` int(20) NOT NULL AUTO_INCREMENT,
  `ele_id` int(10) NOT NULL,
  `const_id` int(10) NOT NULL,
  `cand_id` int(10) NOT NULL,
  PRIMARY KEY (`unit_ele_id`),
  KEY `ele_id` (`ele_id`),
  KEY `const_id` (`const_id`),
  KEY `cand_id` (`cand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `elections_results`
--

CREATE TABLE IF NOT EXISTS `elections_results` (
  `ele_result_id` int(10) NOT NULL AUTO_INCREMENT,
  `unit_ele_id` int(20) NOT NULL,
  `cand_id` int(10) NOT NULL,
  `vote_count` int(20) NOT NULL,
  PRIMARY KEY (`ele_result_id`),
  KEY `unit_ele_id` (`unit_ele_id`),
  KEY `cand_id` (`cand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `elections_schedules`
--

CREATE TABLE IF NOT EXISTS `elections_schedules` (
  `sch_id` int(10) NOT NULL AUTO_INCREMENT,
  `unit_ele_id` int(10) NOT NULL,
  `unit_ele_dt` date NOT NULL,
  PRIMARY KEY (`sch_id`),
  KEY `unit_ele_id` (`unit_ele_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `elections_votingstats`
--

CREATE TABLE IF NOT EXISTS `elections_votingstats` (
  `ev_id` int(20) NOT NULL AUTO_INCREMENT,
  `voting_pin` varchar(50) NOT NULL,
  `ele_id` int(11) NOT NULL,
  `voting_stat` varchar(2) NOT NULL,
  PRIMARY KEY (`ev_id`),
  UNIQUE KEY `voting_pin` (`voting_pin`),
  KEY `ele_id` (`ele_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `elections_votingstats`
--

INSERT INTO `elections_votingstats` (`ev_id`, `voting_pin`, `ele_id`, `voting_stat`) VALUES
(1, 'VPIN444', 1, 'Y'),
(2, 'VPIN888', 1, 'N'),
(3, 'VPIN666', 1, 'N');

-- --------------------------------------------------------

--
-- Table structure for table `voters`
--

CREATE TABLE IF NOT EXISTS `voters` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `voters_id` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `const` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `voters_id` (`voters_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `voters`
--

INSERT INTO `voters` (`id`, `voters_id`, `name`, `const`, `place`) VALUES
(1, 'v444', 'admin', '1', 'Ernakulam'),
(2, 'v888', 'test', '1', 'Thripunithura'),
(3, 'v666', 'qatest', '2', 'Muvattupuzha');

-- --------------------------------------------------------

--
-- Table structure for table `voters_adhaar`
--

CREATE TABLE IF NOT EXISTS `voters_adhaar` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fk_voters_id` varchar(20) NOT NULL,
  `fk_adhaar_id` varchar(20) NOT NULL,
  `voting_pin` varchar(50) NOT NULL,
  `gen_date` date DEFAULT NULL,
  `lockout_flag` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_voters_id` (`fk_voters_id`),
  UNIQUE KEY `fk_adhaar_id` (`fk_adhaar_id`),
  UNIQUE KEY `voting_pin` (`voting_pin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `voters_adhaar`
--

INSERT INTO `voters_adhaar` (`id`, `fk_voters_id`, `fk_adhaar_id`, `voting_pin`, `gen_date`, `lockout_flag`) VALUES
(1, 'v444', 'UID444', 'VPIN444', '2013-12-12', 'F'),
(2, 'v888', 'UID888', 'VPIN888', '2013-12-12', 'F'),
(3, 'v666', 'UID666', 'VPIN666', '2013-12-12', 'F');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `elections_consts`
--
ALTER TABLE `elections_consts`
  ADD CONSTRAINT `elections_consts_ibfk_1` FOREIGN KEY (`ele_id`) REFERENCES `elections` (`ele_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_consts_ibfk_2` FOREIGN KEY (`const_id`) REFERENCES `constituencies` (`const_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_consts_ibfk_3` FOREIGN KEY (`cand_id`) REFERENCES `candidates` (`cand_id`) ON DELETE CASCADE;

--
-- Constraints for table `elections_results`
--
ALTER TABLE `elections_results`
  ADD CONSTRAINT `elections_results_ibfk_2` FOREIGN KEY (`unit_ele_id`) REFERENCES `elections_consts` (`unit_ele_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_results_ibfk_3` FOREIGN KEY (`cand_id`) REFERENCES `candidates` (`cand_id`) ON DELETE CASCADE;

--
-- Constraints for table `elections_schedules`
--
ALTER TABLE `elections_schedules`
  ADD CONSTRAINT `elections_schedules_ibfk_1` FOREIGN KEY (`unit_ele_id`) REFERENCES `elections_consts` (`unit_ele_id`) ON DELETE CASCADE;

--
-- Constraints for table `elections_votingstats`
--
ALTER TABLE `elections_votingstats`
  ADD CONSTRAINT `elections_votingstats_ibfk_1` FOREIGN KEY (`ele_id`) REFERENCES `elections` (`ele_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_votingstats_ibfk_2` FOREIGN KEY (`voting_pin`) REFERENCES `voters_adhaar` (`voting_pin`) ON DELETE CASCADE;

--
-- Constraints for table `voters_adhaar`
--
ALTER TABLE `voters_adhaar`
  ADD CONSTRAINT `voters_adhaar_ibfk_1` FOREIGN KEY (`fk_voters_id`) REFERENCES `voters` (`voters_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `voters_adhaar_ibfk_2` FOREIGN KEY (`fk_adhaar_id`) REFERENCES `adhaarDB` (`adhaar_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
