-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 12, 2020 alle 18:41
-- Versione del server: 10.1.38-MariaDB
-- Versione PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `5ci_cinema`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `attori`
--

CREATE TABLE `attori` (
  `CodAttore` int(11) NOT NULL,
  `Nome` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AnnoNascita` int(4) NOT NULL,
  `Nazionalita` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `film`
--

CREATE TABLE `film` (
  `CodFilm` int(11) NOT NULL,
  `Titolo` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AnnoProduzione` int(11) DEFAULT NULL,
  `Nazionalita` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Regista` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Genere` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Durata` int(11) DEFAULT NULL,
  `Immagine` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'images/default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dump dei dati per la tabella `film`
--

INSERT INTO `film` (`CodFilm`, `Titolo`, `AnnoProduzione`, `Nazionalita`, `Regista`, `Genere`, `Durata`, `Immagine`) VALUES
(1, 'bohhh', 2050, 'italia', 'io', 'no', 150, 'images/default.jpg'),
(3, 'fgvrsd', 2, 'vzdf', 'vzdf', 'vzdf', 4, 'images/default.jpg'),
(7, 'fgvrsd', 2020, 'vzdf', 'vzdf', 'vzdf', 12, 'images/fgvrsd_1582196040.png'),
(8, 'Pippo', 1980, 'Italiano', 'Me stesso', 'Animazione', 50, 'images/Pippo_1584033874.png');

-- --------------------------------------------------------

--
-- Struttura della tabella `proiezioni`
--

CREATE TABLE `proiezioni` (
  `CodProiezione` int(11) NOT NULL,
  `CodFilm` int(11) DEFAULT NULL,
  `CodSala` int(11) DEFAULT NULL,
  `Incasso` decimal(10,0) DEFAULT NULL,
  `DataProiezione` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `recita`
--

CREATE TABLE `recita` (
  `CodAttore` int(11) NOT NULL,
  `CodFilm` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `sale`
--

CREATE TABLE `sale` (
  `CodSala` int(11) NOT NULL,
  `Posti` int(11) DEFAULT NULL,
  `Nome` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Citta` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `ID` int(10) UNSIGNED NOT NULL,
  `email` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` char(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`ID`, `email`, `password`, `Admin`) VALUES
(1, 'bork@gmail.com', '8f5dabb83317aec2dd5291447c94e2cc', 1),
(2, 'verifica@ismonnet.it', '237d120d53e29c728d06e35d6e3dca7e', 0),
(3, 'abc@aaa.it', '900150983cd24fb0d6963f7d28e17f72', 0),
(4, 'xyz@zzz.eu', 'd16fb36f0911f878998c136191af705e', 0),
(5, 'boooooork@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', 0),
(7, 'boh@fff.com', '6e6bc4e49dd477ebc98ef4046c067b5f', 0),
(9, 'booooooooooork@gmail.com', '2f3e9eccc22ee583cf7bad86c751d865', 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `attori`
--
ALTER TABLE `attori`
  ADD PRIMARY KEY (`CodAttore`);

--
-- Indici per le tabelle `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`CodFilm`);

--
-- Indici per le tabelle `proiezioni`
--
ALTER TABLE `proiezioni`
  ADD PRIMARY KEY (`CodProiezione`),
  ADD KEY `filmProiezione` (`CodFilm`),
  ADD KEY `proiezioneSala` (`CodSala`);

--
-- Indici per le tabelle `recita`
--
ALTER TABLE `recita`
  ADD PRIMARY KEY (`CodAttore`,`CodFilm`),
  ADD KEY `recitaFilm` (`CodFilm`);

--
-- Indici per le tabelle `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`CodSala`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `attori`
--
ALTER TABLE `attori`
  MODIFY `CodAttore` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `film`
--
ALTER TABLE `film`
  MODIFY `CodFilm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `proiezioni`
--
ALTER TABLE `proiezioni`
  MODIFY `CodProiezione` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `sale`
--
ALTER TABLE `sale`
  MODIFY `CodSala` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `proiezioni`
--
ALTER TABLE `proiezioni`
  ADD CONSTRAINT `filmProiezione` FOREIGN KEY (`CodFilm`) REFERENCES `film` (`CodFilm`),
  ADD CONSTRAINT `proiezioneSala` FOREIGN KEY (`CodSala`) REFERENCES `sale` (`CodSala`);

--
-- Limiti per la tabella `recita`
--
ALTER TABLE `recita`
  ADD CONSTRAINT `recitaAttore` FOREIGN KEY (`CodAttore`) REFERENCES `attori` (`CodAttore`),
  ADD CONSTRAINT `recitaFilm` FOREIGN KEY (`CodFilm`) REFERENCES `film` (`CodFilm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
