a) Upload the SQL File:
Use docker cp to copy the .sql file into the container and then execute the SQL file in MySQL:

docker cp event#.sql events-db1-1:/event#.sql
docker exec -i events-db1-1 mysql -u root -p1 Events < /event#.sql

Upload the JSON File:
To upload the event2.json file into the container, use docker cp again. You can then either load the JSON data directly into the database using a MySQL function or process it through your application. Here's how you can copy the file:


docker cp event2.json events-db1-1:/event2.json

