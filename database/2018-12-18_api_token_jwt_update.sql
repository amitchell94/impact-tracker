ALTER TABLE `impacttracker`.`token`
CHANGE COLUMN `value` `token` VARCHAR(45) NULL DEFAULT NULL ;


ALTER TABLE `impacttracker`.`token`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `impacttracker`.`token`
CHANGE COLUMN `token` `token` VARCHAR(150) NULL DEFAULT NULL ;