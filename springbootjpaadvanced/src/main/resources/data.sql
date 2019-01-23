/*
insert into FAB_COURSE (CO_ID, CO_NAME) values (10001, 'JPA');
insert into FAB_COURSE (CO_ID, CO_NAME) values (10002, 'MVC');
insert into FAB_COURSE (CO_ID, CO_NAME) values (10003, 'Spring Basics');
insert into FAB_COURSE (CO_ID, CO_NAME) values (10004, 'Spring BooT');
insert into FAB_COURSE (CO_ID, CO_NAME) values (10005, 'Node Js');
*/
/* soft delete */
insert into FAB_COURSE (CO_ID, CO_NAME, CO_IS_DELETE) values (10001, 'JPA', true);
insert into FAB_COURSE (CO_ID, CO_NAME, CO_IS_DELETE) values (10002, 'MVC', false);
insert into FAB_COURSE (CO_ID, CO_NAME, CO_IS_DELETE) values (10003, 'Spring Basics', false);
insert into FAB_COURSE (CO_ID, CO_NAME, CO_IS_DELETE) values (10004, 'Spring BooT', false);
insert into FAB_COURSE (CO_ID, CO_NAME, CO_IS_DELETE) values (10005, 'Node Js', false);

insert into FAB_PASSPORT (PA_ID, PA_NUMBER) values (30001, 'EM47859');
insert into FAB_PASSPORT (PA_ID, PA_NUMBER) values (30002, 'YK45659');
insert into FAB_PASSPORT (PA_ID, PA_NUMBER) values (30003, 'KI47836');
insert into FAB_PASSPORT (PA_ID, PA_NUMBER) values (30004, 'WQ49659');

insert into FAB_STUDENT (ST_ID, ST_NAME, ST_PASSPORT_ID) values (20001, 'James',30001);
insert into FAB_STUDENT (ST_ID, ST_NAME, ST_PASSPORT_ID) values (20002, 'Erika',30002);
insert into FAB_STUDENT (ST_ID, ST_NAME, ST_PASSPORT_ID) values (20003, 'Joe',30003);
insert into FAB_STUDENT (ST_ID, ST_NAME, ST_PASSPORT_ID) values (20004, 'Jessie',30004);

insert into FAB_REVIEW (RE_ID, RE_RATING, RE_DESCRIPTION, RE_COURSE_ID) values (40001, '4', 'Good lesson',10001 );
insert into FAB_REVIEW (RE_ID, RE_RATING, RE_DESCRIPTION, RE_COURSE_ID) values (40002, '4','Awesomme Good lesson',10001);
insert into FAB_REVIEW (RE_ID, RE_RATING, RE_DESCRIPTION, RE_COURSE_ID) values (40003, '4','very Good lesson',10003);
insert into FAB_REVIEW (RE_ID, RE_RATING, RE_DESCRIPTION, RE_COURSE_ID) values (40004, '4','super Good lesson',10004);

insert into FAB_COURSE_STUDENT(CS_COURSE_ID, CS_STUDENT_ID) values (10001, 20001);
insert into FAB_COURSE_STUDENT(CS_COURSE_ID, CS_STUDENT_ID) values (10002, 20001);
insert into FAB_COURSE_STUDENT(CS_COURSE_ID, CS_STUDENT_ID) values (10002, 20002);
insert into FAB_COURSE_STUDENT(CS_COURSE_ID, CS_STUDENT_ID) values (10003, 20003);
insert into FAB_COURSE_STUDENT(CS_COURSE_ID, CS_STUDENT_ID) values (10004, 20004);