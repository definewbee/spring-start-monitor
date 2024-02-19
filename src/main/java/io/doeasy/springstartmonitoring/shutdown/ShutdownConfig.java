package io.doeasy.springstartmonitoring.shutdown;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
//@Configuration
public class ShutdownConfig {

    @Bean
    public SpringStartMonitoringTermination getSpringStartMonitorinTermination(){
        return new SpringStartMonitoringTermination();
    }

    @Bean
    public SpringStartMonitoringTermination2 springStartMonitoringTermination2(){
        return new SpringStartMonitoringTermination2();
    }
}
