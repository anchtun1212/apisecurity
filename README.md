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
   
       cd /home/mohammedayman/git/apisecurity
       npx create-react-app frontend
       cd frontend
       npm install react-router-dom@5.3.0 --save
       npm install @tinymce/tinymce-react

4) Then run the application: `npm start`

    The application will open on this link: http://localhost:3000/

# Run Jmeter

1) Download the `.tgz` file from: https://jmeter.apache.org/download_jmeter.cgi
2) Extract the file and go to `bin` directory and run: `sh jmeter.sh`
3) You can see the image `jmeter-DoS.png` that illustrate DoS attack (CPU: 100%)

# Create self-signed certificate with 10 years validity period (not trusted by Browsers: Browsers only trust Certificates from CA)

`keytool -genkeypair -alias apisecurity -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore apisecurity.p12 -validity 3650`

# Important Links
- Content Security Policy - https://content-security-policy.com/
- OWASP Cheat Sheet Series - https://cheatsheetseries.owasp.org/index.html
- REST Security Cheat Sheet - https://cheatsheetseries.owasp.org/cheatsheets/REST_Security_Cheat_Sheet.html
- Apache JMeter - https://jmeter.apache.org/
- Loggly - https://www.loggly.com/
- Loggly (Documentation for Loggly) - https://documentation.solarwinds.com/en/success_center/loggly/content/admin/logging-setup.htm
- Loggly (Java Logback Setup) - https://documentation.solarwinds.com/en/success_center/loggly/content/admin/java-logback.htm

# Tips
- Domain needs to be treated as https only - chrome://net-internals/#htst
