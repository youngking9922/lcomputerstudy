-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- yyyy 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `yyyy` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yyyy`;

-- 테이블 yyyy.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` varchar(255) NOT NULL,
  `b_writer` varchar(255) NOT NULL,
  `u_idx` int(10) NOT NULL,
  `view_count` int(11) NOT NULL DEFAULT 0,
  `group` int(11) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yyyy.board:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`, `u_idx`, `view_count`, `group`, `order`, `depth`) VALUES
	(41, 'test', 'testtesttesttesttesttesttesttesttesttest', '2022-03-22 09:18:11', 'dd', 17, 89, 41, 1, 0),
	(93, 't-1', 'safd', '12', 'dd', 17, 7, 41, 11, 1),
	(94, 't-2', 'asdfsadf', '12', 'dd', 17, 1, 41, 9, 1),
	(95, 't-3', 'sadfsafd', '12', 'dd', 17, 0, 41, 8, 1),
	(96, 't-4', 'sdafsaf', '12', 'dd', 17, 2, 41, 4, 1),
	(99, 't1-1', 'dsfafsd', '12', 'ff', 21, 0, 41, 12, 2),
	(100, 't4-1', 'sdfasdf', '12', 'dd', 17, 1, 41, 5, 2),
	(101, 't4-1-1', 'asdfasd', '12', 'dd', 17, 2, 41, 6, 3),
	(102, '4-1-1-1', 'sdgsdg', '2022-03-22 11:15:18', 'dd', 17, 0, 41, 7, 4),
	(103, 't2-1', 'sdaf', '2022-03-22 11:15:34', 'dd', 17, 0, 41, 10, 2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 yyyy.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` int(10) NOT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yyyy.user:~18 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(1, 'abc', '1234', 'NAME1', '010-0000-0000', 0),
	(3, 'a', '111', '김땡땡', '010-1111-1111', 20),
	(4, 'b', '222', '이모씨', '010-2222-2222', 37),
	(5, 'ccc', '34343', '강하다', '010-2121-3232', 58),
	(6, 'dfdf', '545', '홍길동', '010-2898-4767', 44),
	(7, 'zyzy', '252d', '대한민국', '010-4747-3634', 88),
	(8, 'a1234', 'bbbb', '만세', '010-8487-7978', 69),
	(9, 'baba', 'cfdfd', '바비', '010-8787-1111', 56),
	(10, 'wew', 'qqqq', '박찬호', '010-7777-3232', 23),
	(11, 'a1', '5555', '강호동', '090-4343-4444', 32),
	(12, 'b1', '1111', '박길동', '090-3333-7777', 49),
	(13, 'bb1', '2222', '고길동', '090-4444-6666', 61),
	(14, 'cc3', 'c1c1', '최길동', '090-1111-3333', 74),
	(17, 'dd', 'dd', 'dd', '010-1344-1234', 12),
	(18, '테스트2', '테스트2', '테스트2', '010-1234-1234', 20),
	(19, '테스트3', '테스트3', '테스트3', '010-1234-1234', 20),
	(20, '테스트4', '1234', '테스트4', '010-2222-2222', 10),
	(21, 'ff', 'ff', 'ff', '010-1234-1234', 12);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
