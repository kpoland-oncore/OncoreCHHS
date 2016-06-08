#!/bin/sh

/usr/local/glassfish4/bin/asadmin start-domain --verbose
/usr/local/glassfish4/bin/asadmin -u admin deploy --force=true /OncoreCHHSServices.ear
/usr/local/glassfish4/bin/asadmin -u admin deploy --force=true /OncoreCHHS.ear
#/usr/local/glassfish4/bin/asadmin stop-domain
#/usr/local/glassfish4/bin/asadmin start-domain --verbose