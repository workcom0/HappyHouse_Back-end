load data infile 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\user.txt' 
into table user character set utf8mb4 
fields terminated by '\t' 
lines terminated by '\r\n' 
ignore 1 rows;

load data infile 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\userinfo.txt' 
into table userinfo character set utf8mb4 
fields terminated by '\t' 
lines terminated by '\r\n' 
ignore 1 rows;

load data infile 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\interestregiondata.txt' 
into table interestregion character set utf8mb4 
fields terminated by '\t' 
lines terminated by '\r\n' 
ignore 1 rows;

select * from user;
select * from userinfo;
select * from interestregion;
select * from userinfo join user u using(userid) join interestregion i using(userno) where userid='abc';
