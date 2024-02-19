package io.doeasy.springstartmonitoring.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
@Slf4j
public class SpringStartMonitoringTermination2 {
    @PreDestroy
    @Order(1)
    public void onDestory() throws Exception{
        log.info("I am run with order 1 --------------- > Spring Container is destroyed!");
    }
}
