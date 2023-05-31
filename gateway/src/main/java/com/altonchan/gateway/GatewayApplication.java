package com.altonchan.gateway;

import io.micrometer.context.ContextSnapshot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/library/bbs/**") // For path with this pattern
						.filters(f -> f.rewritePath("/library/bbs/(?<segment>.*)","/${segment}") // rewrite to ...
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://BBS")) // Define load balancer
				.route(p -> p
						.path("/library/bms/**")
						.filters(f -> f.rewritePath("/library/bms/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://BMS")).build();
	}

	@ConditionalOnClass({ContextSnapshot.class, Hooks.class})
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
	@Bean
	public ApplicationListener<ContextRefreshedEvent> reactiveObservableHook() {
		return event -> Hooks.onEachOperator(
				ObservationContextSnapshotLifter.class.getSimpleName(),
				Operators.lift(ObservationContextSnapshotLifter.lifter()));
	}
}
