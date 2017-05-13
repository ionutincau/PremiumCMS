-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2017 at 06:55 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `evenimente`
--

CREATE TABLE `evenimente` (
  `id_eveniment` int(11) NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `web-page` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `locatie` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `descriere` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `d_abstract` date NOT NULL,
  `d_propunere` date NOT NULL,
  `d_evaluare` date NOT NULL,
  `d_taxa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event-sesiune`
--

CREATE TABLE `event-sesiune` (
  `id_eveniment` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pc-event`
--

CREATE TABLE `pc-event` (
  `id_utilizator` int(11) NOT NULL,
  `id_eveniment` int(11) NOT NULL,
  `functie` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pc-prop`
--

CREATE TABLE `pc-prop` (
  `id_utilizator` int(11) NOT NULL,
  `id_propunere` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `review` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prezentari`
--

CREATE TABLE `prezentari` (
  `id_prezentare` int(11) NOT NULL,
  `speaker` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL,
  `rezumat` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `propuneri`
--

CREATE TABLE `propuneri` (
  `id_propunere` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `alti_autori` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `keywords` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `topics` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `tip` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `data_trimitere` date NOT NULL,
  `data_acceptare` date DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `abstract` longblob NOT NULL,
  `document` longblob NOT NULL,
  `id_sesiune` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sali`
--

CREATE TABLE `sali` (
  `id_sala` int(11) NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `locatie` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `nr_locuri` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sesiuni`
--

CREATE TABLE `sesiuni` (
  `id_sesiune` int(11) NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `id_sala` int(11) NOT NULL,
  `data_in` date NOT NULL,
  `data_out` date NOT NULL,
  `presedinte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tranzactii`
--

CREATE TABLE `tranzactii` (
  `id_tranzactie` int(11) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user-sesiune`
--

CREATE TABLE `user-sesiune` (
  `id_utilizator` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL,
  `functie` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `utilizatori`
--

CREATE TABLE `utilizatori` (
  `id_utilizator` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `parola` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `prenume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `statut` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `tara` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `email` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `afiliere` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `telefon` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `web-page` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `id_tranzactie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evenimente`
--
ALTER TABLE `evenimente`
  ADD PRIMARY KEY (`id_eveniment`);

--
-- Indexes for table `event-sesiune`
--
ALTER TABLE `event-sesiune`
  ADD KEY `id_eveniment` (`id_eveniment`),
  ADD KEY `id_sectiune` (`id_sesiune`);

--
-- Indexes for table `pc-event`
--
ALTER TABLE `pc-event`
  ADD KEY `id_utilizator` (`id_utilizator`),
  ADD KEY `id_eveniment` (`id_eveniment`);

--
-- Indexes for table `pc-prop`
--
ALTER TABLE `pc-prop`
  ADD KEY `id_utilizator` (`id_utilizator`),
  ADD KEY `id_propunere` (`id_propunere`);

--
-- Indexes for table `prezentari`
--
ALTER TABLE `prezentari`
  ADD PRIMARY KEY (`id_prezentare`),
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `speaker` (`speaker`);

--
-- Indexes for table `propuneri`
--
ALTER TABLE `propuneri`
  ADD PRIMARY KEY (`id_propunere`),
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `id_autor` (`id_autor`);

--
-- Indexes for table `sali`
--
ALTER TABLE `sali`
  ADD PRIMARY KEY (`id_sala`);

--
-- Indexes for table `sesiuni`
--
ALTER TABLE `sesiuni`
  ADD PRIMARY KEY (`id_sesiune`),
  ADD KEY `presedinte` (`presedinte`),
  ADD KEY `sala` (`id_sala`);

--
-- Indexes for table `tranzactii`
--
ALTER TABLE `tranzactii`
  ADD PRIMARY KEY (`id_tranzactie`);

--
-- Indexes for table `user-sesiune`
--
ALTER TABLE `user-sesiune`
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `id_utilizator` (`id_utilizator`);

--
-- Indexes for table `utilizatori`
--
ALTER TABLE `utilizatori`
  ADD PRIMARY KEY (`id_utilizator`),
  ADD KEY `id_tranzactie` (`id_tranzactie`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event-sesiune`
--
ALTER TABLE `event-sesiune`
  ADD CONSTRAINT `event-sesiune_ibfk_1` FOREIGN KEY (`id_eveniment`) REFERENCES `evenimente` (`id_eveniment`),
  ADD CONSTRAINT `event-sesiune_ibfk_2` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`);

--
-- Constraints for table `pc-event`
--
ALTER TABLE `pc-event`
  ADD CONSTRAINT `pc-event_ibfk_1` FOREIGN KEY (`id_eveniment`) REFERENCES `evenimente` (`id_eveniment`),
  ADD CONSTRAINT `pc-event_ibfk_2` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `pc-prop`
--
ALTER TABLE `pc-prop`
  ADD CONSTRAINT `pc-prop_ibfk_1` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `pc-prop_ibfk_2` FOREIGN KEY (`id_propunere`) REFERENCES `propuneri` (`id_propunere`);

--
-- Constraints for table `prezentari`
--
ALTER TABLE `prezentari`
  ADD CONSTRAINT `prezentari_ibfk_1` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`),
  ADD CONSTRAINT `prezentari_ibfk_2` FOREIGN KEY (`speaker`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `propuneri`
--
ALTER TABLE `propuneri`
  ADD CONSTRAINT `propuneri_ibfk_1` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`),
  ADD CONSTRAINT `propuneri_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `sesiuni`
--
ALTER TABLE `sesiuni`
  ADD CONSTRAINT `sesiuni_ibfk_1` FOREIGN KEY (`presedinte`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `sesiuni_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sali` (`id_sala`);

--
-- Constraints for table `user-sesiune`
--
ALTER TABLE `user-sesiune`
  ADD CONSTRAINT `user-sesiune_ibfk_1` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `user-sesiune_ibfk_2` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`);

--
-- Constraints for table `utilizatori`
--
ALTER TABLE `utilizatori`
  ADD CONSTRAINT `utilizatori_ibfk_1` FOREIGN KEY (`id_tranzactie`) REFERENCES `tranzactii` (`id_tranzactie`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
