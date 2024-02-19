package io.doeasy.springstartmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringStartMonitoringApplication {

	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		 ctx = SpringApplication.run(SpringStartMonitoringApplication.class, args);
	}

	@Bean
	public void mockDelay() throws Exception {
		Object mockDelay = System.getenv().get("MOCK_DELAY");
		System.out.println("======> " + mockDelay.toString());
		Thread.sleep(mockDelay == null ? 10 : Long.parseLong(mockDelay.toString()));
	}

}
