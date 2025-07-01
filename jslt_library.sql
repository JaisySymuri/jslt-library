-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2025 at 07:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jslt_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookcopies`
--

CREATE TABLE `bookcopies` (
  `BookID` int(11) NOT NULL,
  `ISBN` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookcopies`
--

INSERT INTO `bookcopies` (`BookID`, `ISBN`) VALUES
(38, '9780078022159'),
(39, '9780078022159'),
(40, '9780078022159'),
(41, '9780078022159'),
(51, '9780078023163'),
(52, '9780078023163'),
(53, '9780078023163'),
(29, '9780078025099'),
(30, '9780078025099'),
(31, '9780078025099'),
(32, '9780078025099'),
(21, '9780133591620'),
(22, '9780133591620'),
(23, '9780133591620'),
(24, '9780133591620'),
(25, '9780133591620'),
(75, '9780134602823'),
(76, '9780134602823'),
(86, '9780136042597'),
(87, '9780136042597'),
(88, '9780136042597'),
(89, '9780136042597'),
(13, '9780195015409'),
(14, '9780195015409'),
(80, '9780199340132'),
(81, '9780199340132'),
(1, '9780262033848'),
(2, '9780262033848'),
(3, '9780262033848'),
(4, '9780262033848'),
(5, '9780262033848'),
(83, '9780375423727'),
(84, '9780375423727'),
(85, '9780375423727'),
(54, '9780393532964'),
(55, '9780393532964'),
(56, '9780393532964'),
(63, '9780393603121'),
(64, '9780393603121'),
(65, '9780393603121'),
(66, '9780393603121'),
(67, '9780393603121'),
(93, '9780415278444'),
(94, '9780415278444'),
(42, '9780415300671'),
(43, '9780415300671'),
(77, '9780470007233'),
(78, '9780470007233'),
(79, '9780470007233'),
(15, '9780538453059'),
(16, '9780538453059'),
(17, '9780538453059'),
(18, '9780538453059'),
(19, '9780538453059'),
(20, '9780538453059'),
(6, '9780553380163'),
(7, '9780553380163'),
(8, '9780553380163'),
(57, '9780745621462'),
(58, '9780745621462'),
(71, '9780803658677'),
(72, '9780803658677'),
(73, '9780803658677'),
(74, '9780803658677'),
(47, '9781118793145'),
(48, '9781118793145'),
(49, '9781118793145'),
(50, '9781118793145'),
(68, '9781259616020'),
(69, '9781259616020'),
(70, '9781259616020'),
(26, '9781285061917'),
(27, '9781285061917'),
(28, '9781285061917'),
(90, '9781305251809'),
(91, '9781305251809'),
(92, '9781305251809'),
(59, '9781305577800'),
(60, '9781305577800'),
(61, '9781305577800'),
(62, '9781305577800'),
(98, '9781305644656'),
(99, '9781305644656'),
(100, '9781305644656'),
(101, '9781305644656'),
(95, '9781305958678'),
(96, '9781305958678'),
(97, '9781305958678'),
(9, '9781337098192'),
(10, '9781337098192'),
(11, '9781337098192'),
(12, '9781337098192'),
(44, '9781337401046'),
(45, '9781337401046'),
(46, '9781337401046'),
(82, '9781442269921'),
(33, '9781464158933'),
(34, '9781464158933'),
(35, '9781464158933'),
(36, '9781464158933'),
(37, '9781464158933');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `ISBN` varchar(20) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Author` varchar(100) DEFAULT NULL,
  `Publisher` varchar(100) DEFAULT NULL,
  `YearPublished` year(4) DEFAULT NULL,
  `CallNumber` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`ISBN`, `Title`, `Author`, `Publisher`, `YearPublished`, `CallNumber`) VALUES
('9780078022159', 'Database System Concepts', 'Abraham Silberschatz', 'McGraw-Hill', '2011', 'QA76.9.D3'),
('9780078023163', 'Understanding Business', 'William Nickels', 'McGraw-Hill', '2016', 'HF1008.N53'),
('9780078025099', 'Music: An Appreciation', 'Roger Kamien', 'McGraw-Hill', '2014', 'MT6.K26'),
('9780133591620', 'Modern Operating Systems', 'Andrew S. Tanenbaum', 'Pearson', '2014', 'QA76.76.O63'),
('9780134602823', 'Introduction to Agricultural Economics', 'John B. Penson Jr.', 'Pearson', '2017', 'S561.P46'),
('9780136042597', 'Artificial Intelligence: A Modern Approach', 'Stuart Russell', 'Pearson', '2010', 'Q335.R86'),
('9780195015409', 'The Art of War', 'Sun Tzu', 'Oxford University Press', '1990', 'U101.S85'),
('9780199340132', 'Military Strategy', 'Antulio J. Echevarria', 'Oxford University Press', '2017', 'U162.E24'),
('9780262033848', 'Introduction to Algorithms', 'Thomas H. Cormen', 'MIT Press', '2009', 'QA76.6.C66'),
('9780375423727', 'The Information: A History, A Theory, A Flood', 'James Gleick', 'Pantheon', '2011', 'Z665.G54'),
('9780393532964', 'Comparative Politics', 'Patrick H. O’Neil', 'W. W. Norton', '2020', 'JF51.O54'),
('9780393603121', 'The Norton Anthology of English Literature', 'Stephen Greenblatt', 'W. W. Norton', '2018', 'PR1109.N67'),
('9780415278444', 'The Logic of Scientific Discovery', 'Karl Popper', 'Routledge', '2002', 'Q175.P6'),
('9780415300671', 'Philosophy: The Basics', 'Nigel Warburton', 'Routledge', '2004', 'B72.W37'),
('9780470007233', 'Introduction to Engineering', 'Paul H. Wright', 'Wiley', '2002', 'T15.W74'),
('9780538453059', 'Principles of Economics', 'N. Gregory Mankiw', 'Cengage Learning', '2011', 'HB171.5.M26'),
('9780553380163', 'The Theory of Everything', 'Stephen Hawking', 'Bantam Books', '2002', 'QB981.H377'),
('9780745621462', 'Education and Society', 'Rob Moore', 'Polity Press', '2004', 'LA131.M66'),
('9780803658677', 'Medical Terminology', 'Barbara Janson Cohen', 'F.A. Davis', '2017', 'R123.C63'),
('9781118793145', 'Human Geography', 'Erin H. Fouberg', 'Wiley', '2015', 'G128.F68'),
('9781259616020', 'Genetics: Analysis and Principles', 'Robert J. Brooker', 'McGraw-Hill', '2017', 'QH430.B76'),
('9781285061917', 'Criminal Law', 'Joel Samaha', 'Cengage Learning', '2013', 'KF9219.S25'),
('9781305251809', 'Probability and Statistics for Engineering and the Sciences', 'Jay L. Devore', 'Cengage', '2015', 'QA273.D48'),
('9781305577800', 'Understanding Art', 'Lois Fichner-Rathus', 'Cengage', '2016', 'N7425.F53'),
('9781305644656', 'Cognitive Psychology', 'Robert Sternberg', 'Cengage', '2017', 'BF201.S74'),
('9781305958678', 'Ethics: Theory and Contemporary Issues', 'Barbara MacKinnon', 'Cengage', '2016', 'BJ1012.M33'),
('9781337098192', 'Psychology: Themes and Variations', 'Wayne Weiten', 'Cengage Learning', '2017', 'BF121.W45'),
('9781337401046', 'World History', 'William J. Duiker', 'Cengage', '2018', 'D21.D85'),
('9781442269921', 'Naval Warfare', 'Jeremy Black', 'Rowman & Littlefield', '2017', 'V25.B53'),
('9781464158933', 'Introduction to the Practice of Statistics', 'David S. Moore', 'W. H. Freeman', '2015', 'QA276.M66');

-- --------------------------------------------------------

--
-- Table structure for table `borrowing`
--

CREATE TABLE `borrowing` (
  `BorrowID` int(11) NOT NULL,
  `StudentID` varchar(13) NOT NULL,
  `BookID` int(11) NOT NULL,
  `BorrowDate` date NOT NULL,
  `DueDate` date NOT NULL,
  `ReturnDate` date DEFAULT NULL,
  `Status` enum('BORROWED','RETURNED','OVERDUE') NOT NULL DEFAULT 'BORROWED'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrowing`
--

INSERT INTO `borrowing` (`BorrowID`, `StudentID`, `BookID`, `BorrowDate`, `DueDate`, `ReturnDate`, `Status`) VALUES
(1, '231011400253', 51, '2025-06-24', '2025-07-01', NULL, 'RETURNED'),
(2, '231011400253', 38, '2025-06-24', '2025-07-01', '2025-06-24', 'RETURNED'),
(3, '231011400253', 29, '2025-06-24', '2025-07-01', '2025-06-24', 'RETURNED'),
(4, '231011400253', 75, '2025-06-24', '2025-07-01', '2025-06-24', 'RETURNED'),
(5, '231011400253', 90, '2025-06-24', '2025-07-01', '2025-06-24', 'RETURNED'),
(6, '231011400253', 83, '2025-06-24', '2025-07-01', '2025-06-24', 'RETURNED'),
(7, '231011400253', 90, '2025-06-30', '2025-07-07', NULL, 'BORROWED'),
(8, '231011400253', 57, '2025-06-30', '2025-07-07', '2025-06-30', 'RETURNED'),
(9, '231011400253', 82, '2025-06-30', '2025-07-07', NULL, 'BORROWED');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StudentID` varchar(13) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Major` varchar(100) DEFAULT NULL,
  `EnrollmentYear` year(4) DEFAULT NULL,
  `PasswordHash` char(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StudentID`, `Name`, `Email`, `Major`, `EnrollmentYear`, `PasswordHash`) VALUES
('231011400216', 'Liza Cahyati Rami ', 'liza@gmail.com', 'Information Engineering', '2023', 'c81836771a9de76836aa55379caefd21c0009b0765d4d02ef29d4f670befc728'),
('231011400253', 'Jaisy Muhammad Algifari', 'bestsymuri@mail.com', 'Information Engineering', '2023', '72ab994fa2eb426c051ef59cad617750bfe06d7cf6311285ff79c19c32afd236'),
('231011400261', 'Salsabila ', 'salsabila@gmail.com ', 'Information Engineering', '2023', '36be74c249a6a3343127722af049628ee719113f3637415e6ec8b5d3af48d214'),
('231011401099', 'Tri Wulan Setiyowati  ', 'wulan@gmail.com', 'Information Engineering', '2023', '0cbb37b9f5a0aae9f6934fae2c846f252d4c2ec2cb63378b8162a7d3cab181e2');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_available_copies`
-- (See below for the actual view)
--
CREATE TABLE `view_available_copies` (
`ISBN` varchar(20)
,`Title` varchar(255)
,`TotalCopies` bigint(21)
,`AvailableCopies` bigint(22)
);

-- --------------------------------------------------------

--
-- Structure for view `view_available_copies`
--
DROP TABLE IF EXISTS `view_available_copies`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_available_copies`  AS SELECT `b`.`ISBN` AS `ISBN`, `b`.`Title` AS `Title`, count(`c`.`BookID`) AS `TotalCopies`, count(`c`.`BookID`) - count(`br`.`BorrowID`) AS `AvailableCopies` FROM ((`books` `b` join `bookcopies` `c` on(`b`.`ISBN` = `c`.`ISBN`)) left join `borrowing` `br` on(`c`.`BookID` = `br`.`BookID` and `br`.`Status` = 'BORROWED')) GROUP BY `b`.`ISBN` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookcopies`
--
ALTER TABLE `bookcopies`
  ADD PRIMARY KEY (`BookID`),
  ADD KEY `ISBN` (`ISBN`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`ISBN`);

--
-- Indexes for table `borrowing`
--
ALTER TABLE `borrowing`
  ADD PRIMARY KEY (`BorrowID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`StudentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookcopies`
--
ALTER TABLE `bookcopies`
  MODIFY `BookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `borrowing`
--
ALTER TABLE `borrowing`
  MODIFY `BorrowID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookcopies`
--
ALTER TABLE `bookcopies`
  ADD CONSTRAINT `bookcopies_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `books` (`ISBN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
