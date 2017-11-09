-- MySQL dump 10.13  Distrib 5.7.14, for osx10.11 (x86_64)
--
-- Host: localhost    Database: StihoPlet
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `PERSISTENT_LOGINS`
--

DROP TABLE IF EXISTS `PERSISTENT_LOGINS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSISTENT_LOGINS` (
  `USERNAME` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `TOKEN` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSISTENT_LOGINS`
--

LOCK TABLES `PERSISTENT_LOGINS` WRITE;
/*!40000 ALTER TABLE `PERSISTENT_LOGINS` DISABLE KEYS */;
INSERT INTO `PERSISTENT_LOGINS` VALUES ('testfinal','sj3b/0I0dJ1hXRHhV2BLsg==','4PoabeSO6ch6+GMZMFwW6Q==','2017-11-09 08:06:51');
/*!40000 ALTER TABLE `PERSISTENT_LOGINS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RIFMO_PLET_FOLLOWING`
--

DROP TABLE IF EXISTS `RIFMO_PLET_FOLLOWING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RIFMO_PLET_FOLLOWING` (
  `login_follower` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `login_following` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  KEY `fk_login_follower_idx` (`login_follower`),
  KEY `fk_login_following_idx` (`login_following`),
  CONSTRAINT `fk_login_follower` FOREIGN KEY (`login_follower`) REFERENCES `USERS` (`login`),
  CONSTRAINT `fk_login_following` FOREIGN KEY (`login_following`) REFERENCES `USERS` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RIFMO_PLET_FOLLOWING`
--

LOCK TABLES `RIFMO_PLET_FOLLOWING` WRITE;
/*!40000 ALTER TABLE `RIFMO_PLET_FOLLOWING` DISABLE KEYS */;
INSERT INTO `RIFMO_PLET_FOLLOWING` VALUES ('Gena','d3m0n1c'),('test123','d3m0n1c'),('d3m0n1c','Gena');
/*!40000 ALTER TABLE `RIFMO_PLET_FOLLOWING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RIFMS`
--

DROP TABLE IF EXISTS `RIFMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RIFMS` (
  `id_rifma` int(11) NOT NULL AUTO_INCREMENT,
  `id_parent_rifma` int(11) DEFAULT NULL,
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `text_rifm` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_rifma`),
  KEY `fk_parent_rifma_idx` (`id_parent_rifma`),
  KEY `fk_login_idx` (`login`),
  CONSTRAINT `fk_login_rifma` FOREIGN KEY (`login`) REFERENCES `USERS` (`login`),
  CONSTRAINT `fk_parent_rifma` FOREIGN KEY (`id_parent_rifma`) REFERENCES `RIFMS` (`id_rifma`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RIFMS`
--

LOCK TABLES `RIFMS` WRITE;
/*!40000 ALTER TABLE `RIFMS` DISABLE KEYS */;
INSERT INTO `RIFMS` VALUES (1,NULL,'d3m0n1c','test','2017-11-02 11:52:25'),(2,NULL,'d3m0n1c','еще один тест на русском','2017-11-02 11:53:34'),(3,2,'d3m0n1c','попробуем ответить на рифму','2017-11-02 11:54:09'),(4,NULL,'Gena','попробуем написать одну рифму от лица отца','2017-11-02 11:56:48'),(5,4,'d3m0n1c','попробуем ответить на рифму отца','2017-11-02 11:57:19'),(6,NULL,'d3m0n1c','test','2017-11-02 15:13:43'),(7,6,'d3m0n1c','answer to test','2017-11-02 15:13:50'),(8,6,'d3m0n1c','another answer to test','2017-11-02 15:14:07'),(9,NULL,'d3m0n1c','sdfsfd','2017-11-02 15:17:38'),(10,NULL,'d3m0n1c','sdfsdfdsf','2017-11-02 15:20:32'),(11,NULL,'d3m0n1c','sdfsdfsdf','2017-11-02 15:24:39'),(12,11,'d3m0n1c','sdfsdf','2017-11-02 15:24:47'),(13,12,'d3m0n1c','sdfssf','2017-11-02 15:24:54'),(14,NULL,'d3m0n1c','тест шмест','2017-11-02 15:31:26'),(15,14,'d3m0n1c','тест шмест хуест','2017-11-02 15:31:36'),(16,13,'d3m0n1c','другая попытка','2017-11-02 15:31:48'),(17,4,'d3m0n1c','sdfsdfdsf','2017-11-02 15:32:12'),(18,NULL,'newuser','sdfdsdf','2017-11-02 15:46:43'),(19,18,'newuser','давай попробуем еще раз написать рифму','2017-11-02 15:47:57'),(20,NULL,'d3m0n1c','нормальная рифма','2017-11-02 16:18:21'),(21,NULL,'d3m0n1c','давай попробуем тест','2017-11-02 18:00:30'),(22,4,'d3m0n1c','давай попробуем ответить','2017-11-02 18:13:47'),(23,NULL,'d3m0n1c','пробуем после того как перенесли скрипт','2017-11-02 18:20:21'),(24,23,'d3m0n1c','отвечаем на рифму','2017-11-02 18:20:28'),(25,NULL,'d3m0n1c','dsfasdfasfsflamskflasmpsamfpoafpoamspvomaspvomsaomopsamfpsam','2017-11-02 18:37:43'),(26,NULL,'d3m0n1c','new rifma','2017-11-02 19:26:32'),(27,25,'d3m0n1c','test','2017-11-02 19:26:55'),(28,26,'d3m0n1c','еще одна рифма','2017-11-02 19:37:13'),(29,28,'d3m0n1c','тест рифмы','2017-11-02 20:11:26'),(30,NULL,'d3m0n1c','Три девицы под окном пряли чего то там ','2017-11-02 20:17:11'),(31,30,'d3m0n1c','Одна девица сказала что хочет делать минет Сереже','2017-11-02 20:17:46'),(32,4,'d3m0n1c','ответ к рифму','2017-11-02 20:20:26'),(33,5,'d3m0n1c','ответить на рифу','2017-11-02 20:21:00'),(34,32,'d3m0n1c','ываывадьфв','2017-11-02 20:21:42'),(35,32,'d3m0n1c','ываыаыва','2017-11-02 20:21:45'),(36,32,'d3m0n1c','ываждьыаы','2017-11-02 20:21:49'),(37,35,'d3m0n1c','фывдаьывдальыв','2017-11-02 20:21:52'),(38,37,'d3m0n1c','ыдваждыьаыа','2017-11-02 20:21:54'),(39,38,'d3m0n1c','ntsdfsdf','2017-11-04 08:39:45'),(40,39,'d3m0n1c','sdfsdfd','2017-11-04 08:44:07'),(41,NULL,'d3m0n1c','test new rifm','2017-11-04 09:04:17'),(42,NULL,'test','новая рифма для теста','2017-11-04 14:49:22'),(43,4,'d3m0n1c','на рифму мы ответим дружно','2017-11-04 17:45:05'),(44,NULL,'d3m0n1c','и напишем новую рифм','2017-11-04 17:45:25'),(45,NULL,'Gena','давай напишем новую рифму для теста','2017-11-04 18:16:44'),(46,45,'d3m0n1c','и демоник на нее ответит','2017-11-04 18:17:18'),(47,46,'d3m0n1c','сейчас попробуем ответить','2017-11-06 09:48:48'),(48,NULL,'d3m0n1c','новая рифма тест','2017-11-06 10:57:05'),(49,NULL,'d3m0n1c','давай попробуем еще одни тест','2017-11-06 11:30:21'),(50,49,'d3m0n1c','ответим на тест','2017-11-06 11:30:27'),(51,49,'d3m0n1c','ответим еще раз на тест','2017-11-06 11:30:35'),(52,50,'d3m0n1c','и тут ответим на тест','2017-11-06 11:30:44'),(53,NULL,'d3m0n1c','давай добавим новую рифму','2017-11-06 16:17:46'),(54,53,'d3m0n1c','ответим на рифму','2017-11-06 16:17:54'),(55,NULL,'d3m0n1c','тест написания рифмы','2017-11-07 08:27:00'),(56,55,'d3m0n1c','тест ответа на рифму','2017-11-07 08:27:10'),(57,NULL,'testfinal','My new rime','2017-11-09 07:53:08'),(58,57,'testfinal','I want to answer to my new rime','2017-11-09 07:53:22'),(59,58,'testfinal','one more time','2017-11-09 07:54:01'),(60,59,'testfinal','and one more time','2017-11-09 07:54:47'),(61,60,'testfinal','хорошо, давай еще раз попробуем','2017-11-09 08:01:47');
/*!40000 ALTER TABLE `RIFMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`login`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('alex','Alex','Artemenko','alex@gmail.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1),('d3m0n1c','Sergii','Galkin','d3m0n1c.ua@gmail.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1),('Gena','Gena','Galkin','gena@gmail.com','$2a$10$lpfTGJx21PTycAWfGr6Hxue.oNvNV65aS7v/2rd0E3KZGvfU6QrbS',1),('newuser','newuser','userovich','user@gmail.com','$2a$10$j0lV/pHlAu8ixKT9ivi3ZO7ch.i3t0ezjOjFk8tqxprTnL5FE7XyC',1),('test','test','test','test@gmail.com','$2a$10$GpMsk2lipkDVLf0b.wRene5ArwCBqCvfFFIWZ7oISbcQJtLFZMc0u',1),('test123','test123','test123','test123@gmail.com','$2a$10$ZvHfqMQ8PMQzvkdt46xsV.GivZNrJIWqPXTafEEZ.c45OC9VQ/ns6',1),('test4','test4','test4','test4@gmail.com','$2a$10$QejmrWPT5jMw4CYaHgdSL.ZoLWAhk873awpTEA86zGDXBIRJtg.zS',1),('test5','test5','test5','test5@gmail.com','$2a$10$/S.f85Ia8y3TzsNiWaC4keQmHAY9CE.HYsWPmXvLP1HB2V/jMxJ.q',1),('testfinal','testfinal','testfinal','testfinal@gmail.com','$2a$10$zGMjl83PURrDkiJfI7TDvO3E18Uijx1ZpvhDJpW1CyFQWdtWbwf.G',1);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ROLES`
--

DROP TABLE IF EXISTS `USER_ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ROLES` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_login_role` (`role`,`login`),
  KEY `fk_login_idx` (`login`),
  CONSTRAINT `fk_login` FOREIGN KEY (`login`) REFERENCES `USERS` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ROLES`
--

LOCK TABLES `USER_ROLES` WRITE;
/*!40000 ALTER TABLE `USER_ROLES` DISABLE KEYS */;
INSERT INTO `USER_ROLES` VALUES (2,'d3m0n1c','ROLE_ADMIN'),(3,'alex','ROLE_USER'),(1,'d3m0n1c','ROLE_USER'),(6,'Gena','ROLE_USER'),(9,'newuser','ROLE_USER'),(5,'test','ROLE_USER'),(10,'test123','ROLE_USER'),(7,'test4','ROLE_USER'),(8,'test5','ROLE_USER'),(11,'testfinal','ROLE_USER');
/*!40000 ALTER TABLE `USER_ROLES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09  9:58:28
