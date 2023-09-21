# Create a new Database and a new user

`sudo service postgresql start`

`sudo -u postgres psql`

postgres=# `create database springdb;`

postgres=# `create user springuser with encrypted password 'springpass';`

postgres=# `grant all privileges on database springdb to springuser;`

# Docs API

open: http://localhost:8098/swagger-ui.html

# Stored procedure

This file `fix_stored_procedure.sql` contains the correct syntax to avoid SQL Injection.
Execute the stored procedures manually
