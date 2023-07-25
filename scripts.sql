select age from student s where age between 10 and 20;
select s.name,f.name from student s join faculty f on f.id = faculty_id;
select name from student s where name like '%t%';
select age from student s where age < 19;
select age from student s order by age;