## Log Aggregation
* Run Zipkin 
  * docker pull openzipkin/zipkin
  * docker run -d -p 9411:9411 openzipkin/zipkin
## Metric Monitoring
* Run Prometheus
  * docker pull prom/prometheus
  * docker run -p 9090:9090 -v /absolute_path/to/config:/etc/prometheus prom/prometheus
* Run Grafana
  * docker pull grafana/grafana
  * docker run -d --name=grafana -p 3000:3000 grafana/grafana
  * data source: host.docker.internal:9090
  * dashboard: https://grafana.com/grafana/dashboards/17271-spring-boot-2-1-system-monitor/
