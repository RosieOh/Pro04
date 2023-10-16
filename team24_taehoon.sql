-- 자료실 자료 데이터 테이블 생성
CREATE TABLE fileInfo(
    no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    articleno INT,
    saveFolder VARCHAR(300) NOT NULL,
    originFile VARCHAR(300) NOT NULL,
    saveFile VARCHAR(300) NOT NULL);

-- 자료실 테이블 생성
CREATE TABLE fileboard (
    articleno int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id varchar(16) NOT NULL,
    title varchar(100) NOT NULL,
    content varchar(2000) NOT NULL,
    regdate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP);

