use bookslibrary;

drop table if exists books;
drop table if exists users;
create table books (
  bookId char(4) not null, 
  isbn varchar(25) ,
  title varchar(50), 
  Author varchar(35),
  checkout boolean,
constraint pkbooks primary key (bookId));

create table users (
  userId char(4) not null, 
  name char(35) not null,
  address varchar(50) not null,
  constraint pkusers primary key (userId));

insert into books values (
  '1', '0133761312', 'Intro to Java Programming (10e)', 'Y. Daniel Liang', '0');
insert into books values (
  '2', '0133761312', 'Intro to Java Programming (10e)', 'Y. Daniel Liang', '0');
insert into books values (
  '3', '0133761312', 'Intro to Java Programming (10e)', 'Y. Daniel Liang', '0');
insert into books values (
  '4', '0062320130', 'The English Spy', 'Daniel Silva', '0');
insert into books values (
  '5', '0062320130', 'The English Spy', 'Daniel Silva', '0');
insert into books values (
  '6', '0062320130', 'The English Spy', 'Daniel Silva', '0');
insert into books values (
  '7', '0345534182', 'Circling the Sun', 'Deckle Edge', '0');
insert into books values (
  '8', '0345534182', 'Circling the Sun', 'Deckle Edge', '0');
insert into books values (
  '9', '1627792120', 'Six of Crows', 'Leigh Bardugo', '0');
insert into books values (
  '10', '1627792120', 'Six of Crows', 'Leigh Bardugo', '0');

insert into users values (
'1', 'John Smith', '3240 Walnut Avenue');
insert into users values (
  '2', 'Mary Taylor', '1782 Main Street West');
insert into users values (
  '3', 'James Johnson', '1384 New Street');
insert into users values (
  '4', 'Patricia Williams', '1662 Monroe Drive');
insert into users values (
  '5', 'Robert Jones', '7792 Railroad Street');
insert into users values (
  '6', 'Michael Davis', '2914 Pennsylvania Avenue');
insert into users values (
  '7', 'Barbara Miller', '5054 12th Street East');
insert into users values (
  '8', 'William Wilson', '3604 Deerfield Drive');
insert into users values (
  '9', 'Linda Brown', '1532 King Street');
insert into users values (
  '10', 'Elizabeth Moore', '9863 College Avenue');
commit;