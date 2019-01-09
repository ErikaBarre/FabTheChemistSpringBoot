/*create table fab_person
(
PE_ID                    integer not null,
PE_NAME                           varchar(255) not null,
PE_LOCATION   varchar(255),
PE_BIRTH_DATE               timestamp,
primary key(PE_ID)
);
*/
insert into fab_person (PE_ID  ,PE_NAME,PE_LOCATION,PE_BIRTH_DATE) values (10001, 'Muriel', 'Namur', sysdate());
insert into fab_person (PE_ID  ,PE_NAME,PE_LOCATION,PE_BIRTH_DATE) values (10002, 'James', 'Compos', sysdate());
insert into fab_person (PE_ID  ,PE_NAME,PE_LOCATION,PE_BIRTH_DATE) values (10003, 'Erika', 'Barre', sysdate());
insert into fab_person (PE_ID  ,PE_NAME,PE_LOCATION,PE_BIRTH_DATE) values (10004, 'Natalia', 'Gronounou', sysdate());