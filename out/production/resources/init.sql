CREATE DATABASE IF NOT EXISTS medical_insurance;
CREATE USER IF NOT EXISTS 'myuser'@'%' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON medical_insurance.* TO 'myuser'@'%';
FLUSH PRIVILEGES;