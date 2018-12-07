ALTER TABLE `impacttracker`.`user` 
ADD COLUMN `u_active` TINYINT NOT NULL AFTER `u_password`;