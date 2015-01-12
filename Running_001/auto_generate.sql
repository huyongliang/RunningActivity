-----Memberinfo和Graderecord的关系
--     1       双向关联        n
--Graderecord-------------->Memberinfo
--这个关系的外键在memberinfo表中
--memberinfo表中有一个叫做gradeid的外键列
create sequence SEQ_COMMON;
create table GRADERECORD (
    id number(19,0) not null,
    gradename varchar2(20 char) not null,
    iconpath varchar2(50 char) not null,
    maxpoint number(19,0) not null,
    minpoint number(19,0) not null,
    primary key (id)
);

create table MEMBERINFO (
    id number(19,0) not null,
    address varchar2(200 char),
    age number(19,0) not null,
    email varchar2(100 char) not null,
    gender varchar2(1 char) not null,
    isonline INT default 0,
    latestdate date,
    nickName varchar2(20 char) not null,
    passwd varchar2(50 char) not null,
    passwdAnswer varchar2(200 char),
    passwdQuestion varchar2(200 char),
    phone varchar2(50 char),
    point INT default 0,
    provinceCity varchar2(10 char),
    recommender varchar2(20 char),
    registerdate date,
    status INT default 0,
    gradeid number(19,0),
    primary key (id)
);
alter table MEMBERINFO 
    add constraint FK_8uxiub7ealf6udsv1cvv1se6a 
    foreign key (gradeid) 
    references GRADERECORD;
    
    
create table MEMBERSPACE (
    id number(19,0) not null,
    cellphone varchar2(50 char),
    icon varchar2(200 char),
    opinion varchar2(200 char),
    runhabit varchar2(50 char),
    runplace varchar2(20 char),
    runstar varchar2(50 char),
    runtime varchar2(20 char),
    memberid number(19,0),
    primary key (id)
);
alter table MEMBERSPACE 
    add constraint FK_8ddupda59ahahsi27c4nc5g0q 
    foreign key (memberid) 
    references MEMBERINFO;
create table POINTRECORD (
    id number(19,0) not null,
    nickname varchar2(20 char) not null,
    receivedate date not null,
    pointactionid number(19,0),
    primary key (id)
);

create table pointaction (
    id number(19,0) not null,
    actionname varchar2(20 char),
    description varchar2(200 char),
    point number(19,0) not null,
    primary key (id);
)

alter table POINTRECORD 
    add constraint FK_5csyfxekf1l7cl9f2biy4mc13 
    foreign key (pointactionid) 
    references pointaction;
    
create table friendrecord (
    id number(19,0) not null,
    friendname varchar2(20 char) not null,
    selfname varchar2(20 char) not null,
    primary key (id)
);
create table blackrecord (
    id number(19,0) not null,
    blackname varchar2(20 char) not null,
    selfname varchar2(20 char) not null,
    primary key (id)
);

create table messagerecord (
    id number(19,0) not null,
    content varchar2(300 char) not null,
    receiver varchar2(20 char) not null,
    receiverstatus number default 0,
    senddate date not null,
    sender varchar2(20 char) not null,
    senderstatus number default 0,
    status number default 0,
    title varchar2(100 char) not null,
    primary key (id)
);




--插入积分动作数据
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(1,'REGISTER',50,N'注册会员');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(2,'RECOMMEND',20,N'推荐会员');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(3,'LOGIN',3,N'登录');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(4,'LOGINDESKHELPER',3,N'登录桌面助手');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(5,'CREATEPERSONALSPACE',25,N'创建个人空间');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(6,'SENDSTICK',25,N'发帖');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(7,'REPLYSTICK',25,N'回帖');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(8,'GOODSTICK',30,N'精华贴');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(9,'SUPERGOODSTICK',60,N'超级精华贴');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(10,'BBSMANAGER',200,N'成为版主');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(11,'REPLYSTICK',5,N'每10个回复帖子楼主+5');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(12,'EDM',10,N'成为版主');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(13,'JOINRUNNING',500,N'参加都市跑活动');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(14,'WINRUNNING1',5000,N'都市跑活动得名次');
INSERT INTO PointAction
(id,ActionName,Point,Description) Values(15,'WINRUNNING2',4000,N'都市跑活动得名次');
commit;

--插入等级记录数据
INSERT INTO GradeRecord (id, minpoint, maxpoint, gradename, iconpath) VALUES(1,0,300,'业余爱好者','/images/face1.gif');
INSERT INTO GradeRecord (id, minpoint, maxpoint, gradename, iconpath) VALUES(2,300,700,'跑步小将','/images/face2.gif');
INSERT INTO GradeRecord (id, minpoint, maxpoint, gradename, iconpath) VALUES(3,700,1200,'跑步健将','/images/face3.gif');
INSERT INTO GradeRecord (id, minpoint, maxpoint, gradename, iconpath) VALUES(4,1200,1800,'专业运动员','/images/face4.gif');
INSERT INTO GradeRecord (id, minpoint, maxpoint, gradename, iconpath) VALUES(5,1800,2500,'顶级运动员','/images/face5.gif');
commit;


