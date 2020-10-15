FROM tomcat
MAINTAINER xyz

ADD foodsearch/target/foodsearch.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]