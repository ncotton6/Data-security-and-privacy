Install Tomcat 8, or any other recent version of tomcat should work as well.

Install MySql, a recent version is preferable and start the MySQL server (Consult MySQL instructions on how to start MySQL server).

Install Maven

Open command prompt and go to the project directory and run 'mvn clean install'. If Maven ran without errors, Maven will create a .war file in target directory in the project directory. Move the war file in the target directory to the webapps directory in the tomcat directory (Windows: 'c:\{Tomcat_installation_path}/webapps', Linux: '/{Tomcat_installation_path}/webapps').

Then you will need to open the DatabaseModel.mwb with MySql Workbench and put the database model into your MySQL instance using Forward Engineer option in MySQL Workbench. Make sure MySQL is running at its default port, 3306.

Depending on operating system you use, if you are on Windows, go to Tomcat Installation Directory then go to Bin and run 'startup.bat' to start the Tomcat. If you are on Linux and you are running on Systemd, then run 'systemctl start tomcat#'. Replace that # with Tomcat's version like tomcat8 for Apache Tomcat 8. If you don't have Systemd on linux, go to tomcat installation directory then go to bin directory and run './startup.sh'.

Open the web browser on your computer and go to localhost:8080/dataSecurityproj to access the Web Application.
