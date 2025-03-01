-- MySQL dump 10.13  Distrib 9.2.0, for macos15.2 (arm64)
--
-- Host: localhost    Database: videos
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '&´ªw\ÍI)†^®Åf´\‘','2025-02-25 19:24:31.615000','lokichris@trialrun.com','Loki','Chris','secretpassword','2025-02-25 19:24:31.615000','Lbilly456',''),(_binary '|ó¿wTD]¥n\Ù¢\Î\—','2025-02-27 19:24:22.518269','winnie.chyrstal@trialrun.com','Winnie','Chrystal','secretPassword','2025-02-27 19:24:22.518270','winniec6',''),(_binary 'ç1z¸z}B9ªÅ\—]ãKµ¿','2025-02-27 19:26:16.408659','natalia.jordan@trialrun.com','Natalia','Jordan','secretPassword','2025-02-27 19:26:16.408659','beauty1',''),(_binary '≠9¸\ÈXI\rØr\—$ü≠\ËS','2025-02-27 19:27:27.875628','dawn.inlia@outlook.com','Dawn','Inlia','secretPassword','2025-02-27 19:27:27.875629','fawnly1',''),(_binary 'æ\·∑ôöK5õ\÷\Àa\ÃM','2025-02-25 19:24:31.815000','veronaitaly@trialrun.com','Verona','Made','passwords','2025-02-25 19:24:31.815000','veronaitaly',''),(_binary '\ﬂ\À=h{hLoÜ¶\Ú_\ﬂDæ','2025-02-25 19:24:31.815000','paulpritti@trialrun.com','Paul','Pritti','secretp','2025-02-25 19:24:31.815000','pp95ii','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK75696octon297ywni28sk19ek` (`user_id`),
  CONSTRAINT `FK75696octon297ywni28sk19ek` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (_binary '\n\ƒ++z.Lø≥ßö@l\Z','2025-02-25 19:24:31.615000','video of a dancing cat smiling','smiling cat','cat smiling','2025-02-25 19:24:31.615000','https://stringzone.com',_binary 'ç1z¸z}B9ªÅ\—]ãKµ¿'),(_binary '1¨@uG\0¥ê-7#\Õ\·','2025-02-25 19:24:31.615000','video of a dancing cat smiling','smiling cat','cat smiling','2025-02-25 19:24:31.615000','https://stringzone.com',_binary '|ó¿wTD]¥n\Ù¢\Î\—'),(_binary '^ßç\Ù}Jq∂†ào˛ªt','2025-02-25 19:24:31.615000','Belle\'s first walk in the snow','dog, frenchie, walking','Belle\'s first walk','2025-02-25 19:24:31.615000','https://beautybelle.com',_binary '≠9¸\ÈXI\rØr\—$ü≠\ËS'),(_binary 'z\—Ω\0sK\Îç\Óbt\Í]\œ','2025-02-25 19:24:31.615000','Belle\'s first walk in the snow','dog, frenchie, walking','Belle\'s first walk','2025-02-25 19:24:31.615000','https://beautybelle.com',_binary '≠9¸\ÈXI\rØr\—$ü≠\ËS'),(_binary 'õ;EL\\A˙≤]¿˚r®','2025-02-25 19:24:31.615000','This is a natural make up tutorial with colours for Spring!','makeup, natural','makeup tutorial','2025-02-25 19:24:31.615000','https://naturalbeauty.com',_binary 'ç1z¸z}B9ªÅ\—]ãKµ¿');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-01 12:13:08
