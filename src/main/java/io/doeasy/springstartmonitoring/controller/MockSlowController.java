package io.doeasy.springstartmonitoring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */

@RestController
public class MockSlowController {
    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        // 模拟业务耗时处理流程
        Thread.sleep(30 * 1000L);
        return "slow req is success";
    }
}
