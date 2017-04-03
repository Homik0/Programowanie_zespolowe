/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `warsztat` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `warsztat`;

CREATE TABLE IF NOT EXISTS `listazadan` (
  `id_zadania` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL DEFAULT '0',
  `id_zlecenia` int(11) NOT NULL DEFAULT '0',
  `to_do` varchar(250) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `specjalizacja` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `stan_zadania` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `data_dodawania` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_zadania`),
  KEY `id_user` (`id_user`),
  KEY `id_zlecenia` (`id_zlecenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE IF NOT EXISTS `pracownik` (
  `id_pracownik` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `staz_pracy` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `wynagrodzenie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `specjalizacja` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_pracownik`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_pracownik` int(11) NOT NULL DEFAULT '0',
  `id_zlecenia` int(11) NOT NULL DEFAULT '0',
  `id_zadanie` int(11) NOT NULL DEFAULT '0',
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `login` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `stan_user` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `data_dolaczenia` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_zalogowania` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`),
  KEY `id_pracownik` (`id_pracownik`),
  KEY `id_zlecenia` (`id_zlecenia`),
  KEY `id_zadanie` (`id_zadanie`),
  CONSTRAINT `id_pracownik` FOREIGN KEY (`id_pracownik`) REFERENCES `pracownik` (`id_pracownik`),
  CONSTRAINT `id_zadanie` FOREIGN KEY (`id_zadanie`) REFERENCES `listazadan` (`id_zadania`),
  CONSTRAINT `id_zlecenia` FOREIGN KEY (`id_zlecenia`) REFERENCES `zlecenia` (`id_zlecenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE IF NOT EXISTS `zlecenia` (
  `id_zlecenia` int(11) NOT NULL,
  `id_zadanie` int(11) NOT NULL,
  `name_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `owner` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `to_do` varchar(250) COLLATE utf8_polish_ci NOT NULL,
  `stan_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_zlecenia`),
  KEY `id_zadanie` (`id_zadanie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
