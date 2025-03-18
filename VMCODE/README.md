a) Upload the SQL File:
Enter DB using "mysql -h localhost -P 60000 --protocol=TCP -u root -p"
USE Events; ## enter DB
TRUNCATE TABLE Event; ## get rid of old data (clean table)
quit; ## exit into vm
Use "mysql -h localhost -P 60000 --protocol=TCP -u root -p < schema.sql" to upload new schema
USE Events;
SHOW TABLES;
SELECT * FROM Event; ## ensure upload is good

b) Adding into the schema:
Add onto the end of the table, making sure to replace ";" with "," on the existing line
Follow the below style guide
(id, 'Name', 'Displayed text', int moral, int resourses); ## notice that since this is the new one that it needs the ";" terminator

