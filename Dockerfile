FROM glassfish:latest
MAINTAINER kpoland kyle.poland@oncorellc.com

RUN apt-get update && \
    apt-get install -y less --no-install-recommends && \
    apt-get install -y vim --no-install-recommends

# copy built EAR into the container
COPY OncoreCHHS/dist/OncoreCHHS.ear /
# one lib and one module into glassfish in the container
COPY libs/mysql-connector-java-5.1.39/mysql-connector-java-5.1.39-bin.jar /usr/local/glassfish4/glassfish/lib/
COPY libs/org.eclipse.persistence.moxy.jar /usr/local/glassfish4/glassfish/modules/
# log4j.properties into the glassfish domain
COPY docker/log4j.properties /usr/local/glassfish4/glassfish/domains/domain1/lib/classes/
# domain.xml for glassfish
COPY docker/domain.xml /usr/local/glassfish4/glassfish/domains/domain1/config/
# entrypoint start script that starts glassfish, installs the EAR, stops glassfish, then starts glassfish
COPY docker/start_glassfish.sh /
# make sure it has unix line endings and is executable
RUN /bin/sed -i -e 's/\r$//' /start_glassfish.sh
RUN /bin/chmod +x /start_glassfish.sh

EXPOSE 8080
EXPOSE 4848

ENTRYPOINT ["/start_glassfish.sh"]
