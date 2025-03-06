-- SELECT SEQ_MESSAGE.CURRVAL FROM DUAL; -- 인설트에 들어가는 VO

-- 메세지 슈퍼키
CREATE TABLE TBL_MESSAGE(
	ID NUMBER CONSTRAINT PK_MESSAGE_ALL PRIMARY KEY,
	MESSAGE_ALL_CONTENT VARCHAR2(1000) NOT NULL,
	MESSAGE_ALL_DATE DATE DEFAULT SYSDATE
);

-- 받은 쪽지
CREATE TABLE TBL_RECEIVE_MESSAGE(
	ID NUMBER CONSTRAINT PK_RECEIVE_MESSAGE PRIMARY KEY,
	RECEIVE_MESSAGE_RECEIVER NUMBER NOT NULL,
	RECEIVE_MESSAGE_SENDER NUMBER NOT NULL,
	RECEIVE_MESSAGE_CHECK VARCHAR2(1000) DEFAULT 'FALSE',
	CONSTRAINT FK_RECEIVE_MESSAGE_MESSAGE FOREIGN KEY(ID)
	REFERENCES TBL_MESSAGE(ID),
	CONSTRAINT FK_RECEIVE_MESSAGE_RECEIVER FOREIGN KEY(RECEIVE_MESSAGE_RECEIVER)
	REFERENCES TBL_MEMBER(ID),
	CONSTRAINT FK_RECEIVE_MESSAGE_SENDER FOREIGN KEY(RECEIVE_MESSAGE_SENDER)
	REFERENCES TBL_MEMBER(ID)
);

-- 보낸 쪽지
CREATE TABLE TBL_SEND_MESSAGE(
	ID NUMBER CONSTRAINT PK_SEND_MESSAGE PRIMARY KEY,
	SEND_MESSAGE_RECEIVER NUMBER NOT NULL,
	SEND_MESSAGE_SENDER NUMBER NOT NULL,
	CONSTRAINT FK_SEND_MESSAGE_MESSAGE FOREIGN KEY(ID)
	REFERENCES TBL_MESSAGE(ID),
	CONSTRAINT FK_SEND_MESSAGE_RECEIVER FOREIGN KEY(SEND_MESSAGE_RECEIVER)
	REFERENCES TBL_MEMBER(ID),
	CONSTRAINT FK_SEND_MESSAGE_SENDER FOREIGN KEY(SEND_MESSAGE_SENDER)
	REFERENCES TBL_MEMBER(ID)
);

CREATE TABLE TBL_RECEIVE_MESSAGE_FILE(
    ID NUMBER CONSTRAINT PK_RECEIVE_MESSAGE_FILE PRIMARY KEY,
    RECEIVE_MESSAGE_ID NUMBER NOT NULL,
    CONSTRAINT FK_RECEIVE_MESSAGE_FILE FOREIGN KEY(ID)
    REFERENCES TBL_FILE(ID),
    CONSTRAINT FK_RECEIVE_MESSAGE FOREIGN KEY(RECEIVE_MESSAGE_ID)
    REFERENCES TBL_RECEIVE_MESSAGE(ID)
);
CREATE TABLE TBL_SEND_MESSAGE_FILE(
    ID NUMBER CONSTRAINT PK_SEND_MESSAGE_FILE PRIMARY KEY,
    SEND_MESSAGE_ID NUMBER NOT NULL,
    CONSTRAINT FK_SEND_MESSAGE_FILE FOREIGN KEY(ID)
    REFERENCES TBL_FILE(ID),
    CONSTRAINT FK_SEND_MESSAGE FOREIGN KEY(SEND_MESSAGE_ID)
    REFERENCES TBL_SEND_MESSAGE(ID)
);