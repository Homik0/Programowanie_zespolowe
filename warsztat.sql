/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `warsztat` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `listazadan` DISABLE KEYS */;
INSERT INTO `listazadan` (`id_zadania`, `id_pracownik`, `id_zlecenia`, `to_do`, `specjalizacja`, `stan_zadania`, `data_dodawania`) VALUES
	(1, 1, 1, 'Wymieniac opony', 'Technik sam.', 'Gotowy', '2017-04-02 23:36:15');
/*!40000 ALTER TABLE `listazadan` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `pracownik` (
  `id_pracownik` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `staz_pracy` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `wynagrodzenie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `specjalizacja` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_pracownik`),
  KEY `FKUser` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `pracownik` DISABLE KEYS */;
INSERT INTO `pracownik` (`id_pracownik`, `id_user`, `staz_pracy`, `nr_tel`, `wynagrodzenie`, `specjalizacja`, `status`) VALUES
	(1, 5, '5 years', '12312321', '2300zl', 'technik komp.', 'wolny'),
	(2, 3, '4 years', '12312321', '3300zl', 'technik komp.', 'wolny');
/*!40000 ALTER TABLE `pracownik` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `login` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `stan_user` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `data_dolaczenia` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_zalogowania` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id_user`, `imie`, `nazwisko`, `login`, `password`, `stan_user`, `data_dolaczenia`, `data_zalogowania`) VALUES
	(3, 'Jan', 'Kowalski', 'jkowal', 'kowal44', 'Kierownik', '2017-04-02 22:18:09', '2017-04-02 22:18:09'),
	(5, 'Tomek', 'Kowalski', 'tomek1', 't9esd', 'Pracownik', '2017-04-02 23:31:34', '2017-04-02 23:31:34');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `zlecenia` (
  `id_zlecenia` int(11) NOT NULL AUTO_INCREMENT,
  `name_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `owner` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_tel` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `to_do` varchar(250) COLLATE utf8_polish_ci NOT NULL,
  `stan_car` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_zlecenia`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

/*!40000 ALTER TABLE `zlecenia` DISABLE KEYS */;
INSERT INTO `zlecenia` (`id_zlecenia`, `name_car`, `owner`, `nr_tel`, `to_do`, `stan_car`) VALUES
	(1, 'BMW', 'Jan Kowalski', '123123', 'Wymieniać opony', 'Oczekuje');
/*!40000 ALTER TABLE `zlecenia` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
