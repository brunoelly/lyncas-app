CREATE DATABASE IF NOT EXISTS `keycloak`;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';

CREATE USER 'keycloak'@'%' IDENTIFIED BY 'keycloak';

GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'%' WITH GRANT OPTION;