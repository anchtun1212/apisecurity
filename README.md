# Create a new Database and a new user

`sudo service postgresql start`

`sudo -u postgres psql`

postgres=# `create database springdb;`

postgres=# `create user springuser with encrypted password 'springpass';`

postgres=# `grant all privileges on database springdb to springuser;`

# Docs API

open: http://localhost:8098/swagger-ui.html

# Stored procedure

Execute the file: `stored_procedure.sql` manually
