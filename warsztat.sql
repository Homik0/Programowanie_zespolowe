/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `warsztat` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `warsztat`;

CREATE TABLE IF NOT EXISTS `listazadan` (
  `id_zadania` int(11) NOT NULL AUTO_INCREMENT,
  `id_pracownik` int(11) NOT NULL DEFAULT '0',
  `id_zlecenia` int(11) NOT NULL DEFAULT '0',
  `to_do` varchar(250) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `specjalizacja` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `stan_zadania` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `data_dodawania` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_zadania`),
  KEY `id_user` (`id_pracownik`),
  KEY `id_zlecenia` (`id_zlecenia`),
  CONSTRAINT `FKPracownik` FOREIGN KEY (`id_pracownik`) REFERENCES `pracownik` (`id_pracownik`),
  CONSTRAINT `FKZlecenia` FOREIGN KEY (`id_zlecenia`) REFERENCES `zlecenia` (`id_zlecenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `listazadan` DISABLE KEYS */;
/*!40000 ALTER TABLE `listazadan` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `pracownik` (
  `id_pracownik` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `staz_pracy` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `wynagrodzenie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `specjalizacja` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT 'jeszcze nie przydzielony',
  PRIMARY KEY (`id_pracownik`),
  KEY `FK_pracownik_users` (`id_user`),
  CONSTRAINT `FK_pracownik_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `pracownik` DISABLE KEYS */;
INSERT INTO `pracownik` (`id_pracownik`, `id_user`, `staz_pracy`, `nr_tel`, `wynagrodzenie`, `specjalizacja`, `status`) VALUES
	(6, 17, '4 years', '3334445555', '2000 zł', 'Dyrektor', 'jeszcze nie przydzielony'),
	(11, 22, '4 years', '555444333', '3000 zł', 'Kierownik', 'jeszcze nie przydzielony');
/*!40000 ALTER TABLE `pracownik` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `login` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `stan_user` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT 'wolny',
  `data_dolaczenia` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_zalogowania` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id_user`, `imie`, `nazwisko`, `login`, `password`, `stan_user`, `data_dolaczenia`, `data_zalogowania`) VALUES
	(17, 'Andrzej', 'Duda', 'ADdyr', '123asd', 'wolny', '2017-04-23 13:08:45', '2017-04-23 13:08:45'),
	(22, 'Damian2', 'Kawka', 'DKkier', '123asd', 'wolny', '2017-04-23 13:24:33', '2017-04-23 13:24:33');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `zlecenia` (
  `id_zlecenia` int(11) NOT NULL AUTO_INCREMENT,
  `name_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `owner` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `to_do` varchar(250) COLLATE utf8_polish_ci NOT NULL,
  `stan_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_zlecenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `zlecenia` DISABLE KEYS */;
/*!40000 ALTER TABLE `zlecenia` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
