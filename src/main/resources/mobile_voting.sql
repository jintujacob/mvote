-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 19, 2014 at 11:39 PM
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
(1, 'Piravom', '1'),
(2, 'Moovatupuzha', '1'),
(3, 'Aluva', '1'),
(4, 'Kottayam', '1'),
(5, 'Chennai', '2'),
(6, 'Vishakapatanam', '3');

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
-- Table structure for table `elections_candidates`
--

CREATE TABLE IF NOT EXISTS `elections_candidates` (
  `ele_cand_id` int(10) NOT NULL AUTO_INCREMENT,
  `unit_ele_id` int(20) NOT NULL,
  `cand_id` int(20) NOT NULL,
  PRIMARY KEY (`ele_cand_id`),
  KEY `cand_id` (`cand_id`),
  KEY `unit_ele_id` (`unit_ele_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `elections_candidates`
--

INSERT INTO `elections_candidates` (`ele_cand_id`, `unit_ele_id`, `cand_id`) VALUES
(1, 301, 1),
(2, 301, 2),
(3, 301, 3),
(4, 303, 4),
(5, 303, 5),
(6, 302, 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=403 ;

--
-- Dumping data for table `elections_consts`
--

INSERT INTO `elections_consts` (`unit_ele_id`, `ele_id`, `const_id`) VALUES
(301, 1, 1),
(302, 1, 2),
(303, 1, 3),
(304, 1, 4),
(401, 2, 5),
(402, 2, 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `elections_results`
--

INSERT INTO `elections_results` (`ele_result_id`, `unit_ele_id`, `ele_cand_id`, `vote_count`) VALUES
(1, 301, 1, 3004),
(2, 301, 2, 5001),
(3, 301, 3, 7003),
(4, 303, 4, 2500),
(5, 303, 5, 3500),
(6, 302, 6, 1501);

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
-- Table structure for table `elections_states`
--

CREATE TABLE IF NOT EXISTS `elections_states` (
  `st_id` int(2) NOT NULL AUTO_INCREMENT,
  `st_name` varchar(100) NOT NULL,
  `st_desc` varchar(200) NOT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `elections_states`
--

INSERT INTO `elections_states` (`st_id`, `st_name`, `st_desc`) VALUES
(1, 'Kerala', ''),
(2, 'Tamil Nadu', ''),
(3, 'Andhra Pradesh', '');

-- --------------------------------------------------------

--
-- Table structure for table `elections_votingstats`
--

CREATE TABLE IF NOT EXISTS `elections_votingstats` (
  `ev_id` int(20) NOT NULL AUTO_INCREMENT,
  `e_election_id` bigint(25) NOT NULL,
  `ele_id` int(11) NOT NULL,
  `voting_stat` varchar(2) NOT NULL,
  PRIMARY KEY (`ev_id`),
  KEY `ele_id` (`ele_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `elections_votingstats`
--

INSERT INTO `elections_votingstats` (`ev_id`, `e_election_id`, `ele_id`, `voting_stat`) VALUES
(1, 888855556678, 1, 'Y'),
(2, 888855556680, 1, 'Y'),
(3, 888855556679, 1, 'N');

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
  `e_election_id` bigint(25) NOT NULL AUTO_INCREMENT,
  `fk_voters_id` varchar(20) NOT NULL,
  `fk_adhaar_id` varchar(20) NOT NULL,
  `voting_pin` varchar(50) NOT NULL,
  `gen_date` date DEFAULT NULL,
  `lockout_flag` varchar(2) NOT NULL,
  PRIMARY KEY (`e_election_id`),
  UNIQUE KEY `fk_voters_id` (`fk_voters_id`),
  UNIQUE KEY `fk_adhaar_id` (`fk_adhaar_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=888855556681 ;

--
-- Dumping data for table `voters_adhaar`
--

INSERT INTO `voters_adhaar` (`e_election_id`, `fk_voters_id`, `fk_adhaar_id`, `voting_pin`, `gen_date`, `lockout_flag`) VALUES
(888855556678, 'v444', 'uid444', 'AXh+gjCQyzM=', '2014-02-16', 'F'),
(888855556679, 'v666', 'uid666', 'AXh+gjCQyzM=', '2014-02-16', 'F'),
(888855556680, 'v888', 'uid888', 'AXh+gjCQyzM=', '2014-02-16', 'F');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;