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

Execute the stored procedures manually.

To create a limited user privilege: execute `create_limited_user.sql`.

# Create a ReactJS application

1) If you need to uninstall `nodejs` run those commands:

       sudo rm /usr/bin/node
       sudo apt-get remove nodejs
       cd /etc/apt/sources.list.d;ls
       rm -rf nodesource.list nodesource.list.distUpgrade
       sudo apt-get update
       sudo apt-get purge nodejs
       sudo apt-get purge – auto-remove nodejs
       sudo apt-get autoremove

2) Then install `nodejs`, follow the instructions in this link:

   https://github.com/nodesource/distributions

3) Run those commands:
4) 
   cd /home/mohammedayman/git/apisecurity
   npx create-react-app frontend
   npm install react-router-dom --save
   npm install @tinymce/tinymce-react

   
