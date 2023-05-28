CREATE DATABASE IF NOT EXISTS api_gadgets;
USE api_gadgets;
CREATE USER 'admin_gadgets'@'localhost' IDENTIFIED BY 'admin_gadgets';
CREATE USER 'admin_gadgets'@'%' IDENTIFIED BY 'admin_gadgets';
GRANT ALL PRIVILEGES ON api_gadgets.* TO 'admin_gadgets'@'localhost';
GRANT ALL PRIVILEGES ON api_gadgets.* TO 'admin_gadgets'@'%';
FLUSH PRIVILEGES;