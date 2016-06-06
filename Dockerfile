FROM glassfish:latest
MAINTAINER kpoland kyle.poland@oncorellc.com

RUN apt-get update && \
    apt-get install -y less --no-install-recommends && \
    apt-get install -y vim --no-install-recommends

# copy built EARs into the container
COPY OncoreCHHS/dist/OncoreCHHS.ear /
COPY OncoreCHHSServices/dist/OncoreCHHSServices.ear /
# one lib and one module into glassfish in the container
COPY libs/mysql-connector-java-5.1.39/mysql-connector-java-5.1.39-bin.jar /usr/local/glassfish4/glassfish/lib/
COPY libs/org.eclipse.persistence.moxy.jar /usr/local/glassfish4/glassfish/modules/

# log4j.properties into the glassfish domain
COPY docker/log4j.properties /usr/local/glassfish4/glassfish/domains/domain1/lib/classes/
# client layer URL lookup file into the glassfish domain
# This is set with localhost references and will work whenever client and service tiers are deployed in the same VM or same container
#   users.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
#   profile.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
#   messages.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
# TODO it will need to be adjusted if deployed as split tiers in different containers, change localhost to the hostname of the services tier
COPY ArchCommon/src/client_service_urls.properties /usr/local/glassfish4/glassfish/domains/domain1/lib/classes/

# domain.xml for glassfish
# This contains jdbc URLs for the MySQL database in this form
#   <property name="URL" value="jdbc:mysql://overridemysqlserverhostname:3306/chhsdb?zeroDateTimeBehavior=convertToNull"></property>
#   <property name="Url" value="jdbc:mysql://overridemysqlserverhostname:3306/chhsdb?zeroDateTimeBehavior=convertToNull"></property>
# TODO override that hostname at docker deploy time to appropriate TEST (oncorechhsjenkins.westus.cloudapp.azure.com) or PROD (oncorechhsmysql.westus.cloudapp.com) hostname
COPY docker/domain.xml /usr/local/glassfish4/glassfish/domains/domain1/config/

# entrypoint start script that starts glassfish, installs the EAR, stops glassfish, then starts glassfish
COPY docker/start_glassfish.sh /
# make sure it has unix line endings and is executable
RUN /bin/sed -i -e 's/\r$//' /start_glassfish.sh
RUN /bin/chmod +x /start_glassfish.sh

EXPOSE 8080
EXPOSE 4848

ENTRYPOINT ["/start_glassfish.sh"]
