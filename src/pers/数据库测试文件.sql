---运行完毕一定要commit；

alter table t_memberfee rename constraint fk_mf_id  to fk_mf_m_id;
 MERGE INTO t_infor_parking A USING (select to_date(sysdate) as today from dual) B ON(A.today=B.today)  
                 WHEN NOT MATCHED THEN  
                     INSERT(A.today,A.m_id) values(B.today,'001')
        
  WHEN MATCHED THEN
                     update set ex_num=1 where A.today=B.today
--alter table t_manager rename column id to d_id;
--alter table t_manager add id varchar2(10);
--update t_manager set id=trim(d_id);
--alter table t_manager drop column d_id;
--alter table t_manager drop constraint  pk_m_id;
--alter table t_manager rename constraint pk_id to pk_m_id;
insert into t_manager values('admin','123456','马化腾',0);
insert into t_manager values('001','123456','李子明',1);
--alter table t_MemberFee rename constraint fk_m_id to fk_mf_id;
alter table t_parking
 add   constraint c_p check((state='空'or state='占')and (type='临时'or type ='会员'));
alter table t_parking drop constraint c_p;
alter table t_MemberFee drop column (name,tel);
set linesize 300
col s_num format a16
col id format a10
col m_id format a10
col p_num format a10
insert into t_parking values('A1','空','临时',5,500);
insert into t_parking values('A2','空','临时',5,500);
insert into t_parking values('A3','空','临时',5,500);
insert into t_parking values('B1','空','会员',5,500);
insert into t_parking values('B2','空','会员',5,500);
insert into t_parking values('B3','空','会员',5,500);
select count(*)count from t_parking where type='临时';
update t_parking set state='空' where id='A1';

select * from t_infor_parking;
select * from t_MemberFee;
select * from t_TempFee;
select * from t_manager;
select * from t_parking;	
2018-03-19 00:02:21
insert into t_TempFee values(?,?,?,?,?,?,?);
insert into t_MemberFee values(?,?,?,?,?,?,?,?,?);
update t_TempFee set ex_time=? where p_num = ? and ex_time is null;
insert into t_TempFee values('20180324A10001','A1','贵C-U8888',to_date(sysdate),null,0,'001');	
insert into t_TempFee values('20180324A10001','A1','贵C-U8888',current_timestamp(5),null,0,'001');	
insert from t_TempFee where id='A1';
insert into t_TempFee values('20180324A10002','A1','贵C-U8888',to_date('2018-03-24 23:15:40','yyyy-mm-dd hh24:mi:ss'),null,0,'001');  
update t_TempFee set ex_time=to_date('2018-03-24 23:15:50','yyyy-mm-dd hh24:mi:ss') where p_num='贵C-U8888' and ex_time is null;
alter session set nls_date_format = 'yyyy-mm-dd hh24:mi:ss';
select round(to_number(ex_time-en_time)*24*60*60) from t_tempfee where p_num='贵C-U8888';
select to_number(ex_time-en_time)*24*60*60 from t_tempfee where p_num='贵C-U8888';
insert into t_TempFee values('20180324A10003','A1','贵C-U8888',to_date('2018-03-24 23:15:40','yyyy-mm-dd hh24:mi:ss'),to_date('2018-03-24 23:20:50','yyyy-mm-dd hh24:mi:ss'),0,'001'); 
select round(to_number(ex_time-en_time)) from t_tempfee where p_num='贵C-U8888'and s_num ='20180324A10003';
insert into t_TempFee values('20180409A10001','A1','贵C-U8888',to_date('2018-04-24 03:15:40','yyyy-mm-dd hh24:mi:ss'),to_date('2018-04-24 23:20:50','yyyy-mm-dd hh24:mi:ss'),0,'001'); 
select * from t_infor_parking where today=to_date(sysdate);
insert into t_infor_parking(today) values(to_date('2018-03-24','yyyy-mm-dd'));
select en_num from t_infor_parking where today=to_date(sysdate);
select count(*) from t_tempfee where  to_date(to_char(en_time,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('2018-04-24','yyyy/mm/dd');
select * from (select id from t_parking where state='空' order by id) where rownum<2;
select count(rowid)count from t_parking where type='临时';
delete t_parking;
select state from t_parking where id='A1';
select isnull((select top(1) from t_parking where id='B1'), 0);
select en_num from t_infor_parking where today= to_date(sysdate);
select * from(select a.*,ROWNUM rn from(select * from t_TempFee order by s_num asc) a where ROWNUM<=1) where rn>1