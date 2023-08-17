SELECT s.name, s.age,f.name
FROM student
INNER JOIN faculty ON s.faculty_id = f.name

SELECT a.id, a.filePath, a.mediaType, a.fileSize, s.name
FROM avatar
INNER JOIN student ON a.student_id = s.name