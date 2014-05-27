
Add a datasource to the standlone.xml which points to the database you’re going to use 

Download the quartz distributions and add the required tables to the database, the database specific DDL for this is embedded in the download http://quartz-scheduler.org/downloads/destination?name=quartz-1.8.6.tar.gz&bucket=tcdistributions&file=quartz-1.8.6.tar.gz under docs/dbtables

Edit the quartz.properties under src/main/resources/com/redhat to point to the correct jndi name for datasource

Build and deploy the application



Example datasource
------------------


'''XML
<subsystem xmlns="urn:jboss:domain:datasources:1.1">
            <datasources>
                <datasource jta="false" jndi-name="java:jboss/datasources/QuartzDS" pool-name="QuartzDS" enabled="true">
                    <connection-url>jdbc:postgresql://192.168.2.10:5432/fsw_db</connection-url>
                    <driver>postgresql</driver>
                    <security>
                        <user-name>jboss_admin</user-name>
                        <password>${VAULT::ds_BpelDS::password::1}</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                    </validation>
                </datasource>
               <datasources>
               </subsystem>
'''
