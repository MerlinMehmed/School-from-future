CREATE SCHEMA IF NOT EXISTS `school_from_future` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

USE `school_from_future` ;

CREATE TABLE IF NOT EXISTS `school_from_future`.`Role` (
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `school_from_future`.`User_Right` (
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`role_right` (
  `role_name` VARCHAR(30) NOT NULL,
  `right_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`role_name`, `right_name`),
  CONSTRAINT `role_name`
    FOREIGN KEY (`role_name`)
    REFERENCES `school_from_future`.`Role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `right_name`
    FOREIGN KEY (`right_name`)
    REFERENCES `school_from_future`.`User_Right` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`User` (
  `email` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `pass` VARCHAR(50) NOT NULL,
  `role` VARCHAR(30) NULL,
  PRIMARY KEY (`email`),
  INDEX `role_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `role`
    FOREIGN KEY (`role`)
    REFERENCES `school_from_future`.`Role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `school_from_future`.`Student` (
  `email` VARCHAR(50) NOT NULL,
  `class` INT NOT NULL,
  `class_number` INT NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `student_email`
    FOREIGN KEY (`email`)
    REFERENCES `school_from_future`.`User` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Teacher` (
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `teacher_email`
    FOREIGN KEY (`email`)
    REFERENCES `school_from_future`.`User` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Admin` (
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `admin_email`
    FOREIGN KEY (`email`)
    REFERENCES `school_from_future`.`User` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Subject` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(60) NULL,
  `teacher_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_id`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school_from_future`.`Teacher` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Student_subject` (
  `student_id` VARCHAR(50) NOT NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`student_id`, `subject_id`),
  CONSTRAINT `student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `school_from_future`.`Student` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `school_from_future`.`Subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`student_note` (
  `student_id` VARCHAR(50) NOT NULL,
  `description` VARCHAR(45) NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  CONSTRAINT `note_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `school_from_future`.`Student_subject` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `note_subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `school_from_future`.`Student_subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`student_absence` (
  `student_id` VARCHAR(50) NOT NULL,
  `count` DECIMAL(3,2) NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  CONSTRAINT `absence_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `school_from_future`.`Student_subject` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `absence_subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `school_from_future`.`Student_subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`student_grade` (
  `student_id` VARCHAR(50) NOT NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  `grade` INT UNSIGNED NULL,
  CONSTRAINT `grade_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `school_from_future`.`Student_subject` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `grade_subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `school_from_future`.`Student_subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Event` (
  `event_id` INT AUTO_INCREMENT,
  `subject_id` INT UNSIGNED NOT NULL,
  `date` BIGINT NOT NULL,
  `latitude` DOUBLE(19,15) NOT NULL,
  `longitude` DOUBLE(19,15) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `event_subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `school_from_future`.`Subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `school_from_future`.`Messages` (
  `from_email` VARCHAR(50) NOT NULL,
  `to_email` VARCHAR(50) NOT NULL,
  `content` VARCHAR(150) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  CONSTRAINT `from_email`
    FOREIGN KEY (`from_email`)
    REFERENCES `school_from_future`.`User` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `to_email`
    FOREIGN KEY (`to_email`)
    REFERENCES `school_from_future`.`User` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

