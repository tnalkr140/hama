--���̺� ����
drop table community;

--����������
drop sequence community_comu_post_id_seq;


create table community (
    comu_post_id        number(10),                         -- �Խñ� ���̵�(pk)
    comu_gubun     	varchar2(20),                       -- '�ñ�'/'��'/'��'
    title               varchar2(1000)not null,              -- �� ����
    content             varchar2(4000)not null,             -- �� ����
    member_id           number(10),                         -- �ø�������̵� /fk
    area                varchar2(40),                       -- ����
    board_pic            blob,                              --�Խ��� ����
    cdate               timestamp default systimestamp,         --�����Ͻ�
    udate               timestamp default systimestamp          --�����Ͻ�
 ); 
 
--�⺻Ű����
alter table community add Constraint community_comu_post_id_pk primary key (comu_post_id);

--��������
alter table community add constraint community_comu_gubun_ck check (comu_gubun in ('�ñ��ؿ�','�󸶿���','�𿩺���'));

--�ܷ�Ű����
alter table community add constraint community_member_id_fk foreign key (member_id) references member(member_id);

--������
create sequence community_comu_post_id_seq;

--����
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '�ñ��ؿ�', '�̰� ��� ���ؿ�?', '�� �������� �԰��ִ� �� ��� ���ؿ�?',1,'����');
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '�󸶿���', '�̰� ���ΰ���?', '�� �������� �԰� �ִ� �� ���ΰ���?',2,'���');
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '�𿩺���', '���� ���͵� �ϽǺ�?','���� ������ ���͵� �Ͻ� ��?',3, '���ֵ�');
    
select * from community;
commit;