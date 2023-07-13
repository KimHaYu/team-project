CREATE DATABASE baseball_db;
-- stadium 테이블 만들기
CREATE TABLE stadium (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
created_at TIMESTAMP
);
-- team 테이블 만들기
CREATE TABLE team (
id INT PRIMARY KEY AUTO_INCREMENT,
stadium_id INT,
name VARCHAR(255),
created_at TIMESTAMP,
FOREIGN KEY (stadium_id) REFERENCES stadium(id)
);
-- player 테이블 만들기
CREATE TABLE player (
id INT PRIMARY KEY AUTO_INCREMENT,
team_id INT,
name VARCHAR(255),
position VARCHAR(255),
created_at TIMESTAMP,
CONSTRAINT unique_team_position UNIQUE (team_id, position),
FOREIGN KEY (team_id) REFERENCES team(id)
);
drop table out_player;
-- out_player 테이블 만들기
CREATE TABLE out_player (
id INT PRIMARY KEY AUTO_INCREMENT,
player_id INT,
reason VARCHAR(255),
created_at TIMESTAMP,
FOREIGN KEY (player_id) REFERENCES player(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into out_player(player_id, reason, created_at) values(15, '도박', now());
insert into out_player(player_id, reason, created_at) values(19, '은퇴', now());
insert into out_player(player_id, reason, created_at) values(22, '군입대', now());
insert into out_player(player_id, reason, created_at) values(23, '약물', now());
insert into out_player(player_id, reason, created_at) values(24, '도박', now());
select * from out_table;
-- 날짜 지정값으로 바꾸기
UPDATE out_player SET created_at = '2020-09-09' WHERE player_id = 15;
UPDATE out_player SET created_at = '2020-02-01' WHERE player_id = 19;
UPDATE out_player SET created_at = '2020-09-17' WHERE player_id = 22;
UPDATE out_player SET created_at = '2020-01-09' WHERE player_id = 23;
UPDATE out_player SET created_at = '2020-09-27' WHERE player_id = 24;
--  야구장 인서트
INSERT INTO stadium (name, created_at)
VALUES ('잠실야구장', CURRENT_TIMESTAMP);
INSERT INTO stadium (name, created_at)
VALUES ('사직야구장', CURRENT_TIMESTAMP);
INSERT INTO stadium (name, created_at)
VALUES ('목동야구장', CURRENT_TIMESTAMP);
-- 팀테이블 인서트
INSERT INTO team (stadium_id, name, created_at)
VALUES (1, 'KIA', CURRENT_TIMESTAMP);
INSERT INTO team (stadium_id, name, created_at)
VALUES (1, 'Hanwha', CURRENT_TIMESTAMP);
INSERT INTO team (stadium_id, name, created_at)
VALUES (1, 'NC', CURRENT_TIMESTAMP);
-- 기아 팀 만들기
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '김선우', '포수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '강병우', '투수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '김규성', '유격수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '고중욱', '1루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '김도영', '3루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '김선빈', '2루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '이우성', '우익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '고종욱', '좌익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (1, '이창진', '중견수', CURRENT_TIMESTAMP);
-- 한화 팀 만들기
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '최재훈', '포수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '문동주', '투수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '김건', '유격수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '원혁재', '중견수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '노시환', '3루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '정은원', '2루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '이진영', '우익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '이원석', '좌익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (2, '채은성', '1루수', CURRENT_TIMESTAMP);
-- nc팀 만들기
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '박세혁', '포수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '송명기', '투수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '오태양', '내야수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '한석현', '외야수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '박석민', '3루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '서호철', '2루수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '손아섭', '우익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '권희동', '좌익수', CURRENT_TIMESTAMP);
INSERT INTO player (team_id, name, position, created_at)
VALUES (3, '오영수', '1루수', CURRENT_TIMESTAMP);
-- 조인하기
SELECT p.id, p.team_id, p.name, p.position, op.created_at, op.reason
FROM player p
RIGHT JOIN out_player op ON p.id = op.player_id;
select * from stadium;
select * from team;
select * from player;
select * from out_player;