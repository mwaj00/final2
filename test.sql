-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pon 22. led 2018, 16:16
-- Verze serveru: 10.1.28-MariaDB
-- Verze PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `test`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `akce`
--

CREATE TABLE `akce` (
  `id` int(11) NOT NULL,
  `kurz` int(11) NOT NULL,
  `den` varchar(2) COLLATE utf16_czech_ci NOT NULL,
  `cas` varchar(30) COLLATE utf16_czech_ci NOT NULL,
  `mistnost` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `kapacita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `hodnoceni`
--

CREATE TABLE `hodnoceni` (
  `uzivatel` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `hvezdy` double NOT NULL,
  `kurz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `kurz`
--

CREATE TABLE `kurz` (
  `id` int(11) NOT NULL,
  `nazev` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `univerzita` varchar(100) COLLATE utf16_czech_ci NOT NULL,
  `obor` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `prinos` varchar(100) COLLATE utf16_czech_ci NOT NULL,
  `popis` varchar(255) COLLATE utf16_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `ucastnaakci`
--

CREATE TABLE `ucastnaakci` (
  `akce` int(11) NOT NULL,
  `uzivatel` varchar(50) COLLATE utf16_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `uzivatel`
--

CREATE TABLE `uzivatel` (
  `ujmeno` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `heslo` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `jmeno` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `email` varchar(50) COLLATE utf16_czech_ci NOT NULL,
  `admin` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_czech_ci;

--
-- Vypisuji data pro tabulku `uzivatel`
--

INSERT INTO `uzivatel` (`ujmeno`, `heslo`, `jmeno`, `email`, `admin`) VALUES
('admin', 'admin', 'admin', 'admin@admin.cz', 1);

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `akce`
--
ALTER TABLE `akce`
  ADD PRIMARY KEY (`id`),
  ADD KEY `OmezeniAkci` (`kurz`);

--
-- Klíče pro tabulku `hodnoceni`
--
ALTER TABLE `hodnoceni`
  ADD PRIMARY KEY (`uzivatel`,`kurz`),
  ADD KEY `OmezeniHodnoceni` (`kurz`);

--
-- Klíče pro tabulku `kurz`
--
ALTER TABLE `kurz`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `ucastnaakci`
--
ALTER TABLE `ucastnaakci`
  ADD PRIMARY KEY (`akce`,`uzivatel`);

--
-- Klíče pro tabulku `uzivatel`
--
ALTER TABLE `uzivatel`
  ADD PRIMARY KEY (`ujmeno`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `akce`
--
ALTER TABLE `akce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pro tabulku `kurz`
--
ALTER TABLE `kurz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `akce`
--
ALTER TABLE `akce`
  ADD CONSTRAINT `OmezeniAkci` FOREIGN KEY (`kurz`) REFERENCES `kurz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Omezení pro tabulku `hodnoceni`
--
ALTER TABLE `hodnoceni`
  ADD CONSTRAINT `OmezeniHodnoceni` FOREIGN KEY (`kurz`) REFERENCES `kurz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Omezení pro tabulku `ucastnaakci`
--
ALTER TABLE `ucastnaakci`
  ADD CONSTRAINT `Omezeni` FOREIGN KEY (`akce`) REFERENCES `akce` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
