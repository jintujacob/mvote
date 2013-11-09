-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 09, 2013 at 08:52 PM
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

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `hello`(s CHAR(20)) RETURNS char(50) CHARSET latin1
RETURN CONCAT('Hello, ',s,'!')$$

DELIMITER ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `adhaarDB`
--

INSERT INTO `adhaarDB` (`id`, `adhaar_id`, `nameFirst`, `nameLast`, `address`, `phone`) VALUES
(1, '001002003004', 'Jintu', 'Jacob', 'Kalarikkaparambil, Chithrapuzha South, Irimpanam PO, Ernakulam', '9847361387');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `constituencies`
--

CREATE TABLE IF NOT EXISTS `constituencies` (
  `const_id` int(10) NOT NULL AUTO_INCREMENT,
  `const_name` varchar(20) NOT NULL,
  `const_state` varchar(20) NOT NULL,
  PRIMARY KEY (`const_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `elections`
--

INSERT INTO `elections` (`ele_id`, `ele_title`, `ele_start_dt`, `ele_end_dt`, `ele_desc`) VALUES
(1, 'elections title 1', '2013-10-07', '2013-10-31', 'Description for election title 1'),
(2, 'election title 2', '2013-11-11', '2013-12-12', 'Description for election title 2'),
(3, 'election title 3', '2013-02-12', '2012-10-10', 'Description for election title 3'),
(4, '123', '2013-10-12', '2013-12-12', 'alsdjflajsdf'),
(5, 'test', '2013-10-12', '2013-02-08', 'test descriptions'),
(6, 'sdjg', '2013-10-11', '2013-02-08', 'eqjwerljqw'),
(7, 'finaltest', '2013-10-11', '2013-12-12', 'final test description'),
(8, 'test', '2013-03-03', '2013-04-04', 'teting again'),
(9, 'test', '2013-03-03', '2013-04-04', 'teting again'),
(10, 'uiuiu', '2000-01-01', '2000-02-02', 'uiuiui'),
(11, 'testform', '2000-01-01', '2000-02-02', 'final test description 2'),
(12, 'xzzzzz', '2013-10-12', '2013-04-04', 'zxzzzxzzz');

-- --------------------------------------------------------

--
-- Table structure for table `elections_candidates`
--

CREATE TABLE IF NOT EXISTS `elections_candidates` (
  `ele_cand_id` int(10) NOT NULL AUTO_INCREMENT,
  `unit_ele_id` int(20) NOT NULL,
  `cand_id` int(20) NOT NULL,
  PRIMARY KEY (`ele_cand_id`),
  KEY `cand_id` (`cand_id`),
  KEY `unit_ele_id` (`unit_ele_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `elections_consts`
--

CREATE TABLE IF NOT EXISTS `elections_consts` (
  `unit_ele_id` int(20) NOT NULL AUTO_INCREMENT,
  `ele_id` int(10) NOT NULL,
  `const_id` int(10) NOT NULL,
  PRIMARY KEY (`unit_ele_id`),
  KEY `ele_id` (`ele_id`),
  KEY `const_id` (`const_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `elections_results`
--

CREATE TABLE IF NOT EXISTS `elections_results` (
  `ele_result_id` int(10) NOT NULL AUTO_INCREMENT,
  `unit_ele_id` int(20) NOT NULL,
  `ele_cand_id` int(10) NOT NULL,
  `vote_count` int(20) NOT NULL,
  PRIMARY KEY (`ele_result_id`),
  KEY `ele_cand_id` (`ele_cand_id`),
  KEY `unit_ele_id` (`unit_ele_id`)
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `age` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `name`, `age`) VALUES
(1, 'abc', '45'),
(2, 'alsjdf', '2');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `elections_candidates`
--
ALTER TABLE `elections_candidates`
  ADD CONSTRAINT `elections_candidates_ibfk_1` FOREIGN KEY (`cand_id`) REFERENCES `candidates` (`cand_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_candidates_ibfk_2` FOREIGN KEY (`unit_ele_id`) REFERENCES `elections_consts` (`unit_ele_id`) ON DELETE CASCADE;

--
-- Constraints for table `elections_consts`
--
ALTER TABLE `elections_consts`
  ADD CONSTRAINT `elections_consts_ibfk_1` FOREIGN KEY (`ele_id`) REFERENCES `elections` (`ele_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_consts_ibfk_2` FOREIGN KEY (`const_id`) REFERENCES `constituencies` (`const_id`) ON DELETE CASCADE;

--
-- Constraints for table `elections_results`
--
ALTER TABLE `elections_results`
  ADD CONSTRAINT `elections_results_ibfk_1` FOREIGN KEY (`ele_cand_id`) REFERENCES `elections_candidates` (`ele_cand_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `elections_results_ibfk_2` FOREIGN KEY (`unit_ele_id`) REFERENCES `elections_consts` (`unit_ele_id`) ON DELETE CASCADE;

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
