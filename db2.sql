-- ---------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.5-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 데이터 yyyy.board:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
REPLACE INTO `board` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`, `u_idx`, `view_count`, `group`, `order`, `depth`) VALUES
	(41, 'test', 'testtesttesttesttesttesttesttesttesttest', '2022-03-22 09:18:11', 'dd', 17, 233, 41, 1, 0),
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

-- 테이블 데이터 yyyy.comment:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
REPLACE INTO `comment` (`c_idx`, `c_board_idx`, `c_content`) VALUES
	(1, 41, 'dgdgas');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 데이터 yyyy.user:~18 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
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
