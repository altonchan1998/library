## Log Aggregation
* Run Zipkin 
  * docker pull openzipkin/zipkin
  * docker run -d -p 9411:9411 openzipkin/zipkin
## Metric Monitoring
* Run Prometheus
  * docker pull prom/prometheus
  * docker run -p 9090:9090 -v /absolute/path/to/config:/etc/prometheus prom/prometheus
* Run Grafana
  * docker pull grafana/grafana
  * docker run -d --name=grafana -p 3000:3000 grafana/grafana
  * username/password: admin/admin
  * data source: host.docker.internal:9090
  * dashboard: 
    * https://grafana.com/grafana/dashboards/17271-spring-boot-2-1-system-monitor/
    * https://grafana.com/grafana/dashboards/9889-jvm-micrometer/
## Authentication Server
* Run KeyCloak
  * docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.1 start-dev
* Create Client for client credentials grant type (for application)
  * library-gateway
    * Client authentication (O)
    * Direct access grants (O)
    * Service accounts roles (O)
* Create Realm Role
  * bbs
  * bms
* Assign the roles to the client
* Create Client for authorization code grant type
  * library-gateway-ui
    * Client authentication (O)
    * Standard flow (O)
    * Service accounts roles (O)
    * Valid redirect URIs: * (dev only)
