--테이블 삭제

drop table uploadfile;
drop table coment;
drop table estimate;
drop table requestBoard;
drop table member;

drop sequence uploadfile_uploadfile_id_seq;
drop sequence coment_coment_id_seq;
drop sequence estimate_estimate_id_seq;
drop sequence requestB_requestB_id_seq;
drop sequence member_member_id_seq;

-------
--회원
-------
create table member (
    member_id   number(10),         --내부 관리 아이디
    work_member_id   number(10),         --내부 관리 아이디
    email       varchar2(50),   --로긴 아이디
    passwd      varchar2(12),   --로긴 비밀번호
    tel         varchar2(20),   --연락처 ex)010-1234-5678
    nickname    varchar2(30),   --별칭
    hobby       varchar2(300),  --취미
    gubun       varchar2(11)   default '주마', --회원구분 (일반,우수,관리자..)
    pic         number(10),            --사진
    cdate       timestamp default systimestamp,         --생성일시
    udate       timestamp default systimestamp          --수정일시
);
--기본키생성
alter table member add Constraint member_member_id_pk primary key (member_id);

--제약조건
alter table member modify email constraint member_email_uk unique;
alter table member modify tel constraint member_tel_uk unique;
alter table member modify email constraint member_email_nn not null;
alter table member add constraint member_gubun_ck check (gubun in ('주마','하마'));

--시퀀스
create sequence member_member_id_seq;

--샘플데이터 of member
insert into member (member_id,work_member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval,1, 'test1@kh.com', '1234', '010-1111-1111','테스터1','안녕하세요', '주마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'test2@kh.com', '1234', '010-1111-1112','테스터2','안녕하세요', '주마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'admin1@kh.com', '1234','010-1111-1113','관리자1', '안녕하세요','하마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'admin2@kh.com', '1234','010-1111-1114','관리자2', '안녕하세요','하마');
insert into member (member_id,work_member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval,1, 'test6@kh.com', '1234', '010-1111-1115','테스터3','안녕하세요', '주마');
select * from member;
commit;




-------
--회원
-------
create table requestBoard (
    board_id        number(10),         --내부 관리 아이디
    member_id     number(10),       -- 올린맴버아이디
    category        varchar2(60) not null,   --카테고리
    area              varchar2(40) not null,   --지역
    hope_date     varchar2(40) not null,   --희망날짜
    hope_text       varchar2(300),  --희망문구
    cdate            timestamp default systimestamp,         --생성일시
    udate            timestamp default systimestamp          --수정일시
);
--기본키생성
alter table requestBoard add Constraint requestBoard_requestBoard_id_pk primary key (board_id);

--외래키생성
alter table requestBoard add constraint request_member_id_fk foreign key(member_id) references member(member_id);

--시퀀스
create sequence requestB_requestB_id_seq;

--샘플데이터 of member
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 1, '디자인/개발  웹 디자인', '울산/신정동','2023-11-20','안녕하세요 빠르게해주실분구합니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 2,'청소/이사 가정집 청소', '부산/영도구','협의 가능해요.','안녕하세요 테스트입니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 3,'레슨/수학 과외', '경상/통영시','가능한 빨리 하고싶어요.','안녕하세요 이것은테스트입니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 4,'도우미/배달 도우미', '경상/창원시','2024-01-20','안녕하세요 테스트트');

select board_id,member_id,category,area,hope_date,hope_text from requestBoard;
select board_id,member_id,category,area,hope_date,hope_text,to_char(cdate,'YY/MM/DD')"cdate"
  from requestboard
 where member_id = 1;
commit;




create table estimate (
    estimate_id      number(10), -- pk,
    board_id         number(10), -- requestBoard 테이블의 외래키,
    member_id        number(10), -- member 테이블의 외래키,       -- 올린맴버아이디
    work_member_id   number(10),     -- 하마의 아이디
    esti_gubun       varchar2(10) not null, --견적서 작성의 총 비용과 시간 당 구분
    esti_price       varchar2(20) not null,   --견적서 작성의 가격
    esti_text        varchar2(300),
    esti_file        number(10),         --견적서 작성의 파일
    cdate               timestamp default systimestamp,         --생성일시
    udate               timestamp default systimestamp          --수정일시
 );

--기본키생성
alter table estimate add Constraint estimate_estimate_id_pk primary key (estimate_id);

--외래키생성
alter table estimate add constraint estimate_board_id_fk foreign key(board_id) references requestBoard(board_id);

--시퀀스
create sequence estimate_estimate_id_seq;

--샘플데이터 of estimate
insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text)
    values(estimate_estimate_id_seq.nextval, 1, 2, 1,'총 비용', '3,000', '잘해드리겠습니다!');
insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text)
    values(estimate_estimate_id_seq.nextval, 2, 1, 2,'시간 당', '9,000', '걱정마세요!');

select * from estimate;
commit;




create table coment (
    coment_id      number(10), -- pk,
    estimate_id           number(10), -- requestBoard 테이블의 외래키,
    member_id        number(10), -- member 테이블의 외래키,       -- 올린맴버아이디
    coment_text     varchar2(200),
    cdate               timestamp default systimestamp         --생성일시
 );

--기본키생성
alter table coment add Constraint coment_coment_id_pk primary key (coment_id);

--외래키생성
alter table coment add constraint coment_estimate_id_fk foreign key(estimate_id)
references estimate(estimate_id);
alter table coment add constraint coment_member_id_fk foreign key(member_id)
references member(member_id);

--시퀀스
create sequence coment_coment_id_seq;

--샘플데이터 of estimate
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 1, 1, '안녕하세요!');
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 1, 2, '안녕하세요 누구누구입니다');
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 2, 2,'반갑습니다!');

select * from coment;
commit;



---------
--첨부파일
---------
create table uploadfile(
    uploadfile_id   number(10),     --파일아이디
    table_info      varchar2(20),     --참조번호(게시글번호등) ,member의 이미지, 견적서작성의 첨부파일 member냐 estimate냐 구분 하마찾기냐
    table_id        number(10),
    store_filename  varchar2(100),   --서버보관파일명
    upload_filename varchar2(100),   --업로드파일명(유저가 업로드한파일명)
    fsize           varchar2(100),   --업로드파일크기(단위byte)
    ftype           varchar2(100),   --파일유형(mimetype)
    cdate           timestamp default systimestamp -- 등록일시
);
--기본키
alter table uploadfile add constraint uploadfile_uploadfile_id_pk primary key(uploadfile_id);

--외래키


--제약조건
alter table uploadfile modify table_id constraint uploadfile_table_id_nn not null;
alter table uploadfile modify table_info constraint uploadfile_table_info_nn not null;
alter table uploadfile modify store_filename constraint uploadfile_store_filename_nn not null;
alter table uploadfile modify upload_filename constraint uploadfile_upload_filename_nn not null;
alter table uploadfile modify fsize constraint uploadfile_fsize_nn not null;
alter table uploadfile modify ftype constraint uploadfile_ftype_nn not null;

--시퀀스
create sequence uploadfile_uploadfile_id_seq;

commit;