drop table uploadfile;
drop sequence uploadfile_uploadfile_id_seq;
---------
--÷������
---------
create table uploadfile(
    uploadfile_id   number(10),     --���Ͼ��̵� 
    table_info      varchar2(20),     --������ȣ(�Խñ۹�ȣ��) ,member�� �̹���, �������ۼ��� ÷������ member�� estimate�� ���� �ϸ�ã���
    table_id        number(10),
    store_filename  varchar2(100),   --�����������ϸ�
    upload_filename varchar2(100),   --���ε����ϸ�(������ ���ε������ϸ�)
    fsize           varchar2(45),   --���ε�����ũ��(����byte)
    ftype           varchar2(100),   --��������(mimetype)
    cdate           timestamp default systimestamp -- ����Ͻ�
);
--�⺻Ű
alter table uploadfile add constraint uploadfile_uploadfile_id_pk primary key(uploadfile_id);

--�ܷ�Ű


--��������
alter table uploadfile modify table_id constraint uploadfile_table_id_nn not null;
alter table uploadfile modify table_info constraint uploadfile_table_info_nn not null;
alter table uploadfile modify store_filename constraint uploadfile_store_filename_nn not null;
alter table uploadfile modify upload_filename constraint uploadfile_upload_filename_nn not null;
alter table uploadfile modify fsize constraint uploadfile_fsize_nn not null;
alter table uploadfile modify ftype constraint uploadfile_ftype_nn not null;

--������
create sequence uploadfile_uploadfile_id_seq;

commit;