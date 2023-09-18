# Create new Database and new user

`sudo service postgresql start`

`sudo -u postgres psql`

postgres=# `create database springdb;`

postgres=# `create user springuser with encrypted password 'springpass';`

postgres=# `grant all privileges on database springdb to springuser;`
