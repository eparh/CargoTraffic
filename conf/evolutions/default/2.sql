# --- Adding address table

# --- !Ups

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`address` (
  `id`      INTEGER(11)  UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `country` VARCHAR(250) NOT NULL,
  `city`    VARCHAR(250) NOT NULL,
  `street`  VARCHAR(250) NOT NULL,
  `house`   VARCHAR(250) NOT NULL,
  `deleted` BIT(1) DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `address` (country, city, street, house) VALUES
  ("Belarus", "Minsk", "blabla", "12");

# --- !Downs

DROP TABLE IF EXISTS `cargo_traffic`.`address`;