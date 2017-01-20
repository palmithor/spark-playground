-- drop database spark_playground;
-- drop role spark_playground;

create role spark_playground;
alter user spark_playground with password 'spark_playground';
alter role spark_playground LOGIN;
create database spark_playground owner spark_playground;

-- drop database spark_playground_test;
-- drop role spark_playground_test;

create role spark_playground_test;
alter user spark_playground_test with password 'spark_playground_test';
alter role spark_playground_test LOGIN;
create database spark_playground_test owner spark_playground_test;

-- drop database spark_playground_dev;
-- drop role spark_playground_dev;

create role spark_playground_dev;
alter user spark_playground_dev with password 'spark_playground_dev';
alter role spark_playground_dev LOGIN;
create database spark_playground_dev owner spark_playground_dev;



