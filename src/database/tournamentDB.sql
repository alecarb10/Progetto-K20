CREATE DATABASE  IF NOT EXISTS `tournament`;
USE `tournament`;

DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `day`;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS `manager`;
DROP TABLE IF EXISTS `match`;
DROP TABLE IF EXISTS `player`;
DROP TABLE IF EXISTS `player_position_type`;
DROP TABLE IF EXISTS `stadium`;
DROP TABLE IF EXISTS `team`;
DROP TABLE IF EXISTS `tournament`;
DROP TABLE IF EXISTS `tournament_type`;

CREATE TABLE `manager` (
  `Username` varchar(20) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Surname` varchar(20) DEFAULT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Username`)
);

CREATE TABLE `tournament_type` (
  `IDTournamentType` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  PRIMARY KEY (`IDTournamentType`)
);

CREATE TABLE `player_position_type` (
  `IDPlayerPositionType` int(11) NOT NULL,
  `Position` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`IDPlayerPositionType`)
);

CREATE TABLE `stadium` (
  `Name` varchar(20) NOT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Capacity` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Name`)
);

CREATE TABLE `tournament` (
  `IDTournament` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Manager` varchar(20) DEFAULT NULL,
  `TournamentType` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDTournament`),
  KEY `Manager_idx` (`Manager`),
  KEY `TournamentType_idx` (`TournamentType`),
  CONSTRAINT `manager` FOREIGN KEY (`Manager`) REFERENCES `manager` (`Username`) ON UPDATE CASCADE,
  CONSTRAINT `tournament_type` FOREIGN KEY (`TournamentType`) REFERENCES `tournament_type` (`IDTournamentType`) ON UPDATE CASCADE
);

CREATE TABLE `board` (
  `IDBoard` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `IDTournament` int(11) DEFAULT NULL,
  `Completed` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`IDBoard`),
  KEY `tournament_idx` (`IDTournament`),
  CONSTRAINT `tournament_board` FOREIGN KEY (`IDTournament`) REFERENCES `tournament` (`IDTournament`)
);

CREATE TABLE `group` (
  `IDGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `IDTournament` int(11) DEFAULT NULL,
  `Completed` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`IDGroup`),
  KEY `tournament.idx` (`IDTournament`),
  CONSTRAINT `tournament_group` FOREIGN KEY (`IDTournament`) REFERENCES `tournament` (`IDTournament`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `day` (
  `Number` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Group` int(11) DEFAULT NULL,
  `Board` int(11) DEFAULT NULL,
  PRIMARY KEY (`Number`,`Date`),
  KEY `group_idx` (`Group`),
  KEY `board_idx` (`Board`),
  CONSTRAINT `board` FOREIGN KEY (`Board`) REFERENCES `board` (`IDBoard`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group` FOREIGN KEY (`Group`) REFERENCES `group` (`IDGroup`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `team` (
  `IDTeam` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `IDTournament` int(11) DEFAULT NULL,
  `Stadium` varchar(20) DEFAULT NULL,
  `Points` int(11) DEFAULT NULL,
  `GoalsScored` int(11) DEFAULT NULL,
  `GoalsConceded` int(11) DEFAULT NULL,
  `Group` int(11) DEFAULT NULL,
  `Board` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDTeam`),
  KEY `id_tournament_idx` (`IDTournament`),
  KEY `stadium_idx` (`Stadium`),
  KEY `group_idx` (`Group`),
  KEY `board_idx` (`Board`),
  CONSTRAINT `stadium` FOREIGN KEY (`Stadium`) REFERENCES `stadium` (`Name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `team_board` FOREIGN KEY (`Board`) REFERENCES `board` (`IDBoard`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `team_group` FOREIGN KEY (`Group`) REFERENCES `group` (`IDGroup`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `tournament_team` FOREIGN KEY (`IDTournament`) REFERENCES `tournament` (`IDTournament`) ON UPDATE CASCADE
);

CREATE TABLE `match` (
  `IDMatch` int(11) NOT NULL AUTO_INCREMENT,
  `Day` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Stadium` varchar(20) DEFAULT NULL,
  `HomeTeam` int(11) DEFAULT NULL,
  `AwayTeam` int(11) DEFAULT NULL,
  `HomeScore` int(11) DEFAULT NULL,
  `AwayScore` int(11) DEFAULT NULL,
  `Played` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`IDMatch`),
  KEY `match_team1_idx` (`HomeTeam`),
  KEY `match_team2_idx` (`AwayTeam`),
  KEY `match_day_idx` (`Day`,`Date`),
  KEY `match_stadium_idx` (`Stadium`),
  CONSTRAINT `match_day` FOREIGN KEY (`Day`, `Date`) REFERENCES `day` (`Number`, `Date`) ON UPDATE CASCADE,
  CONSTRAINT `match_stadium` FOREIGN KEY (`Stadium`) REFERENCES `stadium` (`Name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `match_team1` FOREIGN KEY (`HomeTeam`) REFERENCES `team` (`IDTeam`) ON UPDATE CASCADE,
  CONSTRAINT `match_team2` FOREIGN KEY (`AwayTeam`) REFERENCES `team` (`IDTeam`) ON UPDATE CASCADE
);

CREATE TABLE `player` (
  `IDPlayer` int(11) NOT NULL AUTO_INCREMENT,
  `IDTeam` int(11) DEFAULT NULL,
  `Number` int(11) DEFAULT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Surname` varchar(20) DEFAULT NULL,
  `PlayerPositionType` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDPlayer`),
  KEY `team_player_idx` (`IDTeam`),
  KEY `position_type_idx` (`PlayerPositionType`),
  CONSTRAINT `position_type` FOREIGN KEY (`PlayerPositionType`) REFERENCES `player_position_type` (`IDPlayerPositionType`) ON UPDATE CASCADE,
  CONSTRAINT `team_player` FOREIGN KEY (`IDTeam`) REFERENCES `team` (`IDTeam`) ON DELETE SET NULL ON UPDATE CASCADE
);

LOCK TABLES `player_position_type` WRITE;
INSERT INTO `player_position_type` VALUES (1,'GK'),(2,'CB'),(3,'MF'),(4,'CF');
UNLOCK TABLES;

LOCK TABLES `tournament_type` WRITE;
INSERT INTO `tournament_type` VALUES (1,'MIXED'),(2,'LEAGUE'),(3,'KNOCKOUT_PHASE');
UNLOCK TABLES;