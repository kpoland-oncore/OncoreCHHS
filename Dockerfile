FROM kpoland/glassfish:4.1.1
MAINTAINER kpoland kyle.poland@oncorellc.com

RUN apt-get -qq update && \
    apt-get install -qqy less --no-install-recommends && \
    apt-get install -qqy vim --no-install-recommends && \
    apt-get install -qqy mysql-client --no-install-recommends
RUN echo "set -o vi" >> /root/.bashrc
RUN echo "US/Pacific-New" > /etc/timezone && dpkg-reconfigure --frontend noninteractive tzdata

# one lib and one module into glassfish in the container
COPY libs/mysql-connector-java-5.1.39/mysql-connector-java-5.1.39-bin.jar /usr/local/glassfish-4.1.1/glassfish/lib/
COPY libs/org.eclipse.persistence.moxy.jar /usr/local/glassfish-4.1.1/glassfish/modules/
RUN /bin/rm /usr/local/glassfish-4.1.1/glassfish/modules/jackson*.jar
COPY libs/jackson-2.7.4/ /usr/local/glassfish-4.1.1/glassfish/modules/

# log4j.properties into the glassfish domain
COPY docker/log4j.properties /usr/local/glassfish-4.1.1/glassfish/domains/domain1/lib/classes/
# client layer URL lookup file into the glassfish domain
# This is set with localhost references and will work whenever client and service tiers are deployed in the same VM or same container
#   users.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
#   profile.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
#   messages.rest.url.json=http://localhost:8080/OncoreCHHSServices-war/rest
# It will need to be overridden if deployed as split tiers in different containers, change/override localhost with the hostname of the services tier
COPY ArchCommon/src/client_service_urls.properties /usr/local/glassfish-4.1.1/glassfish/domains/domain1/lib/classes/

# domain.xml for glassfish
# This contains jdbc URLs for the MySQL database in this form
#   <property name="URL" value="jdbc:mysql://127.0.0.1:3306/chhsdb?zeroDateTimeBehavior=convertToNull"></property>
#   <property name="Url" value="jdbc:mysql://127.0.0.1:3306/chhsdb?zeroDateTimeBehavior=convertToNull"></property>
# Hardcoded to a locally running database, will need to adjust/override when database is separate from docker container
COPY docker/domain.xml /usr/local/glassfish-4.1.1/glassfish/domains/domain1/config/

# entrypoint start script that starts glassfish, installs the EAR, stops glassfish, then starts glassfish
COPY docker/start_glassfish.sh /
# make sure it has unix line endings and is executable
RUN /bin/sed -i -e 's/\r$//' /start_glassfish.sh && /bin/chmod +x /start_glassfish.sh

# copy built EARs into the container
COPY OncoreCHHS/dist/OncoreCHHS.ear /
COPY OncoreCHHSServices/dist/OncoreCHHSServices.ear /

RUN /bin/mkdir /home/oncore && /bin/ln -s /usr/local/glassfish-4.1.1/ /home/oncore/glassfish-4.1.1

EXPOSE 4848
EXPOSE 8080

CMD ["/start_glassfish.sh"]