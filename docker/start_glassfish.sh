#!/bin/sh

/usr/local/glassfish-4.1.1/bin/asadmin start-domain
/usr/local/glassfish-4.1.1/bin/asadmin -u admin deploy --force=true /OncoreCHHSServices.ear
/usr/local/glassfish-4.1.1/bin/asadmin -u admin deploy --force=true /OncoreCHHS.ear
/bin/bash -c "while true; do sleep 60; done"