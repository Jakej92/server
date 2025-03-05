CREATE SEQUENCE SEQ_MEMBER;
CREATE TABLE TBL_MEMBER(
                           ID NUMBER CONSTRAINT PK_USER PRIMARY KEY,
                           MEMBER_EMAIL VARCHAR2(1000) UNIQUE NOT NULL,
                           MEMBER_PASSWORD VARCHAR2(1000) NOT NULL,
                           MEMBER_NICKNAME VARCHAR2(1000) UNIQUE NOT NULL,
                           MEMBER_TELL VARCHAR2(1000) NOT NULL,
                           MEMBER_BIRTH DATE DEFAULT SYSDATE,
                           MEMBER_GENDER VARCHAR2(1000) DEFAULT '선택안함',
                           MEMBER_POINT NUMBER DEFAULT 0,
                           MEMBER_IS_ACT CHAR(1) DEFAULT 'Y' NOT NULL,
                           CREATED_DATE DATE DEFAULT SYSDATE,
                           UPDATED_DATE DATE DEFAULT SYSDATE
);

ALTER TABLE TBL_MEMBER MODIFY MEMBER_PASSWORD VARCHAR2(1000) NULL;

COMMENT ON TABLE  TBL_MEMBER IS '회원 테이블';
COMMENT ON COLUMN TBL_MEMBER.ID IS '고유 식별자';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_EMAIL IS '회원 이메일 (고유 제약 조건)';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_PASSWORD IS '회원 비밀번호';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_NICKNAME IS '회원 닉네임 (고유 제약 조건)';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_TELL IS '회원 전화번호';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_BIRTH IS '회원 생년월일 (기본값: 현재 날짜)';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_GENDER IS '회원 성별 (기본값: 선택안함)';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_POINT IS '회원 포인트 (기본값: 0)';
COMMENT ON COLUMN TBL_MEMBER.MEMBER_IS_ACT IS '활동 여부 (기본값: ''Y'')';
COMMENT ON COLUMN TBL_MEMBER.CREATED_DATE IS '생성 날짜 (기본값: 현재 날짜)';
COMMENT ON COLUMN TBL_MEMBER.UPDATED_DATE IS '수정 날짜 (기본값: 현재 날짜)';

