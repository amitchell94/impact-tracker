CREATE TABLE `impacttracker`.`token` (
  `id` INT NOT NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `impacttracker`.`hibernate_sequence` (
  `next_val` BIGINT(20) NOT NULL,
  PRIMARY KEY (`next_val`));

  ALTER TABLE `impacttracker`.`hibernate_sequence`
CHANGE COLUMN `next_val` `next_val` BIGINT(20) NULL ,
DROP PRIMARY KEY;
;

INSERT INTO impacttracker.hibernate_sequence VALUES (1);