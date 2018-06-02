package scripts

/*docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql*/

docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 -d mysql:5.7.22

CREATE DATABASE dbinkus;

CREATE USER 'dbinkus'@'localhost' IDENTIFIED BY 'dbinkus';

GRANT SELECT ON dbinkus.* TO 'dbinkus'@'localhost';
GRANT INSERT ON dbinkus.* TO 'dbinkus'@'localhost';
GRANT DELETE ON dbinkus.* TO 'dbinkus'@'localhost';
GRANT UPDATE ON dbinkus.* TO 'dbinkus'@'localhost';

USE dbinkus;

CREATE TABLE `rola` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nazwa` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `rola`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `rola`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `uzytkownik` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `login` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `rola_id` bigint(20) UNSIGNED NOT NULL DEFAULT '2',
  `adres_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `adres_id` (`adres_id`);

ALTER TABLE `uzytkownik`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `adres` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `ulica` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nr_mieszkania` varchar(20) COLLATE utf8_polish_ci NOT NULL,
  `nr_domu` varchar(20) COLLATE utf8_polish_ci,
  `kod_pocztowy` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `miasto` varchar(50) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `adres`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `adres`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `kategoria` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nazwa` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `kategoria`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `kategoria`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `podkategoria` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `kategoria_id` bigint(20) UNSIGNED NOT NULL,
  `nazwa` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `obrazek` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `podkategoria`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `podkategoria`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `produkt` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nazwa` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `podkategoria_id` bigint(20) UNSIGNED NOT NULL,
  `cena` DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;


ALTER TABLE `produkt`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `produkt`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `zamowienie` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uzytkownik_id` bigint(20) UNSIGNED NOT NULL,
  `data_zlozenia` date NOT NULL,
  `data_wysylki` date DEFAULT NULL,
  `czy_oplacone` BIT(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `zamowienie`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

CREATE TABLE `koszyk` (
  `zamowienie_id` bigint(20) UNSIGNED NOT NULL,
  `produkt_id` bigint(20) UNSIGNED NOT NULL,
  `ilosc` int(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `koszyk`
  ADD PRIMARY KEY (`zamowienie_id`,`produkt_id`);

ALTER TABLE `uzytkownik`
  ADD CONSTRAINT `uzytkownik_rola_fk` FOREIGN KEY (`rola_id`) REFERENCES `rola` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `uzytkownik_adres_fk` FOREIGN KEY (`adres_id`) REFERENCES `adres` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `podkategoria`
  ADD CONSTRAINT `podkategoria_kategoria_fk` FOREIGN KEY (`kategoria_id`) REFERENCES `kategoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `produkt`
  ADD CONSTRAINT `produkt_podkategoria_fk` FOREIGN KEY (`podkategoria_id`) REFERENCES `podkategoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamowienie_uzytkownik_fk` FOREIGN KEY (`uzytkownik_id`) REFERENCES `uzytkownik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `koszyk`
  ADD CONSTRAINT `koszyk_zamowienie_fk` FOREIGN KEY (`zamowienie_id`) REFERENCES `zamowienie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `koszyk_produkt_fk` FOREIGN KEY (`produkt_id`) REFERENCES `produkt` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `rola` (nazwa,opis) VALUES
  ('Administrator', 'Zarządza sklepem'),
  ('Użytkownik', 'Zwykły użytkownik');

INSERT INTO `adres` (ulica, nr_mieszkania, nr_domu, kod_pocztowy, miasto) VALUES
  ('Kolorowa', '12a', '1', '11-222', 'Gdańsk'),
  ('Rumiankowa', '11', '2a', '11-111', 'Gdynia');

INSERT INTO `uzytkownik` (login, haslo, imie, nazwisko, email, rola_id, adres_id) VALUES
  ('admin','admin','Admin','Abacki','admin@stronka.pl',1,1),
  ('user','user','Użytkownik','Babacki','user@stronka.pl',2,2);

INSERT INTO `kategoria` (nazwa, opis) VALUES
  ('Prezenty','Prezenty na każdą okazję!'),
  ('Gadżety', 'Super gadżety!');

INSERT INTO `podkategoria` (kategoria_id,nazwa,opis) VALUES
  (1,'Na dzień dziecka','Prezenty na dzień dziecka'),
  (1,'Na dzień matki','Prezenty na dzień matki'),
  (2,'Dla niej', 'Gadżety dla niej!'),
  (2,'Dla niego', 'Gadżety dla niego!');

INSERT INTO `produkt` (nazwa, opis, podkategoria_id, cena) VALUES
  ('Fidget spinner', 'Super zabawka dla dziecka',1,20.99),
  ('Figurka','Własnoręcznie robiona figurka dla twojej mamy',2,142.23),
  ('Poradnik dobrej żony','Książka napisana przez autora Łukasza Stanisławowskiego, autora takich książek jak "Pomarańcza"!',3,21.37),
  ('Pilot do znajdowania kluczy','Dzięki temu gadżetowi już nigdy nie zgubisz kluczy!',4,11.11);

INSERT INTO `zamowienie` (uzytkownik_id, `data_zlozenia`,`data_wysylki`,`czy_oplacone`) VALUES
  (1, '2018-3-02',NULL,0);

INSERT INTO `koszyk` (zamowienie_id, produkt_id, ilosc) VALUES
  (1,1,1),
  (1,2,2),
  (1,3,1),
  (1,4,2);
