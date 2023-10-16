CREATE DATABASE haebeop;

USE haebeop;

CREATE TABLE test(num INT AUTO_INCREMENT PRIMARY KEY,
                  title VARCHAR(100) NOT NULL);

INSERT INTO test VALUES (DEFAULT, '테스트제목1');
INSERT INTO test VALUES (DEFAULT, '테스트제목2');
INSERT INTO test VALUES (DEFAULT, '테스트제목3');
INSERT INTO test VALUES (DEFAULT, '테스트제목4');
INSERT INTO test VALUES (DEFAULT, '테스트제목5');

SELECT * from test;

COMMIT;

SELECT * FROM emp;
haebeop
DESC test;

DESC emp;

CREATE TABLE member(
                       id VARCHAR(20) PRIMARY KEY, pw VARCHAR(350) NOT NULL,
                       name VARCHAR(50) NOT NULL, email VARCHAR(150),
                       tel VARCHAR(20), addr1 VARCHAR(200),
                       addr2 VARCHAR(200), postcode VARCHAR(20),
                       regdate DATETIME DEFAULT CURRENT_TIME,
                       birth DATE DEFAULT CURRENT_DATE,
                       pt INT DEFAULT 0,
                       visited INT DEFAULT 0
);

DESC MEMBER;

INSERT INTO member VALUES ('admin', '1234', '관리자',
                           'admin@teaspoon.co.kr', '02-1234-1234',
                           '서울특별시 금천구 디지털로9길 23 (마리오2 패션타워)',
                           '11층 1108호', '08511', DEFAULT, '2023-12-25',
                           DEFAULT, DEFAULT);


INSERT INTO member VALUES ('sirious920', '1234', '오태훈',
                           'sirious920@teaspoon.co.kr', '010-7329-7484',
                           '서울특별시 금천구 가산로9길 54',
                           '천재교과서', '08513', DEFAULT, '1998-09-20',
                           DEFAULT, DEFAULT);

COMMIT;

UPDATE member SET pw='$2a$10$TMTp1e7vW8nx7l7B49a2d.0robqb2qUFTuBPntKHRvVILg0CmBkqW' WHERE id='admin';

UPDATE member SET pw='$2a$10$mpr36CRQpp903gPf.f76auK1CYzFkq.LD65JsGzdrBCiOluBotWYW' WHERE id='sirious920';

SELECT * FROM member;

-- 자유게시판
CREATE TABLE free(
                     fno INT AUTO_INCREMENT PRIMARY KEY,  -- 글번호
                     title VARCHAR(100) NOT null,   -- 글제목
                     content VARCHAR(1500) not null,    -- 글내용
                     regdate DATETIME DEFAULT CURRENT_TIMESTAMP(),   -- 작성일
                     visited INT,    -- 조회수
                     id VARCHAR(20),    -- 작성자
                     rec INT -- 추천수
);

CREATE TABLE free_comment(
                             cno INT PRIMARY KEY AUTO_INCREMENT,
                             fno INT,
                             author VARCHAR(16) NOT NULL,
                             resdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             content VARCHAR(200),
                             FOREIGN KEY(fno) REFERENCES free(fno) ON DELETE CASCADE
);

CREATE VIEW freecommentlist AS (SELECT a.cno AS cno, a.fno as fno, a.content AS content, a.author AS author, a.resdate AS resdate,
                                       b.name AS name FROM free_comment a, member b WHERE a.author=b.id ORDER BY a.cno ASC);

CREATE TABLE record (
                        rno INT AUTO_INCREMENT PRIMARY KEY,
                        fno INT REFERENCES free(fno) ON DELETE CASCADE,
                        id VARCHAR(20),
                        flag INT DEFAULT 0
);

CREATE TABLE fileinfo(
                         NO INT AUTO_INCREMENT PRIMARY KEY,  -- 번호
                         articleno varchar(45) DEFAULT NULL,	-- 글번호
                         saveFolder varchar(45) DEFAULT NULL,	-- 저장 디렉토리
                         originFile varchar(45) DEFAULT NULL,	--
                         saveFile varchar(45) DEFAULT NULL
);

CREATE TABLE fileobj (
                         no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         postno INT NOT NULL,
                         savefolder VARCHAR(400),
                         originfile VARCHAR(400),
                         savefile VARCHAR(800),
                         filesize LONG,
                         uploaddate VARCHAR(100)
);
CREATE TABLE fileboard (
                           postno int NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- 글 번호
                           title VARCHAR(100) not null,   -- 글제목
                           content VARCHAR(1500) not null,    -- 글내용
                           regdate DATETIME DEFAULT CURRENT_TIMESTAMP(),   -- 작성일
                           visited INT DEFAULT 0   -- 조회수
);
DESC fileobj;
DESC fileboard;
SELECT * FROM fileobj;
SELECT * FROM fileboard;

CREATE TABLE board (
                       bno INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       content VARCHAR(1000) NOT NULL,
                       nickname VARCHAR(20),
                       regdate DATETIME DEFAULT CURRENT_TIMESTAMP(),
                       visited INT DEFAULT 0
);






