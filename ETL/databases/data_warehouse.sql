-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.42 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for data_warehouse
CREATE DATABASE IF NOT EXISTS `data_warehouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `data_warehouse`;

-- Dumping structure for table data_warehouse.student_summary
CREATE TABLE IF NOT EXISTS `student_summary` (
  `student_id` bigint DEFAULT NULL,
  `log_id` bigint DEFAULT NULL,
  `name` text,
  `course` text,
  `enrollment_date` date DEFAULT NULL,
  `username` text,
  `role` text,
  `last_login` datetime DEFAULT NULL,
  `total_fees` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `last_payment_amount` double DEFAULT NULL,
  `last_payment_date` date DEFAULT NULL,
  `total_pages_printed` double DEFAULT NULL,
  `last_print_date` date DEFAULT NULL,
  `last_action` text,
  `last_action_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table data_warehouse.student_summary: ~6 rows (approximately)
INSERT INTO `student_summary` (`student_id`, `log_id`, `name`, `course`, `enrollment_date`, `username`, `role`, `last_login`, `total_fees`, `balance`, `last_payment_amount`, `last_payment_date`, `total_pages_printed`, `last_print_date`, `last_action`, `last_action_date`) VALUES
	(1, 221198632, 'Alice', 'Nursing', '2021-02-01', 'alice2021', 'Student', '2025-06-25 08:30:00', 10000, 5000, 5000, '2023-01-10', 20, '2025-06-20', 'Login', '2023-05-01'),
	(2, 231285365, 'Bob', 'Business', '2022-03-15', 'bob2022', 'Student', '2025-06-28 14:45:00', 10000, 5500, 4500, '2023-02-12', 15, '2025-06-21', 'Submit Form', '2023-05-03'),
	(3, 242526985, 'Vasco', 'Engineering', '2023-02-01', 'Vasco2020', 'Student', '2025-06-25 08:30:00', 20000, 5000, 5500, '2023-01-10', 10, '2025-06-30', 'Login', '2023-05-02'),
	(4, 235263985, 'Vinny', 'IT', '2024-02-01', 'Vinny1010', 'Student', '2025-06-28 14:45:00', 30000, 5500, 6500, '2023-02-12', 35, '2025-06-21', 'Submit Form', '2023-05-04'),
	(5, 221177441, 'Gugu', 'BSc', '2025-07-29', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(6, 221188932, 'Vee', 'Technical', '2025-07-29', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
