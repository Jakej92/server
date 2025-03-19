CREATE SEQUENCE SEQ_TAG;
CREATE TABLE TBL_TAG (
                          ID NUMBER CONSTRAINT PK_TAG PRIMARY KEY,
                          TAG_CONTENT VARCHAR2(2000) NOT NULL,
                          FEED_ID NUMBER NOT NULL,
                          CREATED_DATE DATE DEFAULT SYSDATE,
                          UPDATED_DATE DATE DEFAULT SYSDATE,
                          CONSTRAINT FK_TAG_FEED FOREIGN KEY(FEED_ID) REFERENCES TBL_FEED(ID) ON DELETE CASCADE
);

COMMENT ON TABLE TBL_TAG IS '태그 테이블';
COMMENT ON COLUMN TBL_TAG.ID IS '태그의 고유 ID';
COMMENT ON COLUMN TBL_TAG.TAG_CONTENT IS '태그 내용';
COMMENT ON COLUMN TBL_TAG.FEED_ID IS '피드 또는 리얼후기 아이디';
COMMENT ON COLUMN TBL_TAG.CREATED_DATE IS '생성 날짜';
COMMENT ON COLUMN TBL_TAG.UPDATED_DATE IS '수정 날짜';