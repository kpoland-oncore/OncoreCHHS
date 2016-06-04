FROM glassfish:latest
MAINTAINER kpoland kyle.poland@oncorellc.com

# copy built EAR into the container
COPY OncoreCHHS/dist/OncoreCHHS.ear /
# one lib and one module into glassfish in the container
COPY libs/mysql-connector-java-5.1.39/mysql-connector-java-5.1.39-bin.jar /usr/local/glassfish4/glassfish/lib/
COPY libs/org.eclipse.persistence.moxy.jar /usr/local/glassfish4/glassfish/modules/
# domain.xml for glassfish
COPY docker/domain.xml /usr/local/glassfish4/glassfish/domains/domain1/config/domain.xml
COPY docker/start_glassfish.sh /

EXPOSE 8080
EXPOSE 4848

ENTRYPOINT ["/start_glassfish.sh"]
