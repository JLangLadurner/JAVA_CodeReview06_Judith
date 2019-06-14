
/*show students and different classes*/
SELECT student.studentId, student.studentName, classRooms.className
FROM student
INNER JOIN classRooms
ON student.fk_classRoomId = classRooms.classRoomId;


/*show student names / classroom /teacher names*/
SELECT student.studentName, classRooms.className, teacher.teacherName, teacher.teacherSurname 
FROM student
INNER JOIN classRooms
on student.fk_classRoomId = classRooms.classRoomId
INNER JOIN teacher on classRooms.fk_teacherId = teacher.teacherId

/*right join / OUTPUT: shows teachers which have  classes and which have no classes*/
SELECT classRooms.className, teacher.teacherName, teacher.teacherSurname, teacher.teacherEmail 
FROM classRooms 
RIGHT JOIN teacher ON classRooms.fk_teacherId = teacher.teacherId;

/* left join / OUTPUT:  shows all the classes and their teachers*/

SELECT classRooms.className, teacher.teacherName, teacher.teacherSurname, teacher.teacherEmail 
FROM classRooms 
LEFT JOIN teacher ON classRooms.fk_teacherId = teacher.teacherId


/*show students for specific class Id*/
SELECT classRooms.classRoomId, student.studentName 
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.classRoomId = 2;

/*show students for specific class name*/

SELECT classRooms.className, student.studentName 
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.className = "1a";

/*show all classes which have an a in the class name and their students*/
SELECT classRooms.className, student.studentName 
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.className LIKE "%a";


/*show all 1 classes and their students*/
SELECT classRooms.className, student.studentName 
FROM student
INNER JOIN classRooms ON student.fk_classRoomId = classRooms.classRoomId
WHERE classRooms.className LIKE "1%";


