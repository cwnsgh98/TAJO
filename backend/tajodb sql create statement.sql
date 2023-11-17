CREATE TABLE `course` (
  `courseid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `viewCnt` int default 0,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `coursereview` (
  `content` varchar(45) DEFAULT NULL,
  `writer` varchar(45) DEFAULT NULL,
  `star` int DEFAULT NULL,
  `courseid` int NOT NULL,
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `writeDate` datetime DEFAULT NULL,
  `courseReviewcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `fk_courseReview_course1_idx` (`courseid`),
  CONSTRAINT `fk_courseReview_course1` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `favoritecourse` (
  `userid` varchar(20) NOT NULL,
  `courseid` int NOT NULL,
  KEY `fk_favoriteCourse_user1_idx` (`userid`),
  KEY `fk_favoriteCourse_course1_idx` (`courseid`),
  CONSTRAINT `fk_favoriteCourse_course1` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `fk_favoriteCourse_user1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `groupboard` (
  `groupid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `membercnt` int DEFAULT NULL,
  PRIMARY KEY (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `record` (
  `distance` int NOT NULL DEFAULT '0',
  `cal` int NOT NULL DEFAULT '0',
  `time` int NOT NULL DEFAULT '0',
  `userid` varchar(20) NOT NULL,
  `lastride` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`),
  CONSTRAINT `fk_record_user1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `userid` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
