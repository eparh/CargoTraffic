# --- Adding company table

# --- !Ups

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`company` (
  `id`      INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `name`    VARCHAR(250)         NOT NULL,
  `deleted` BIT(1)                        DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `cargo_traffic`.`company` (name) VALUES
  ("TradeCorp"),
  ("TravelInc");

# --- !Downs

DROP TABLE IF EXISTS `cargo_traffic`.`company`;

