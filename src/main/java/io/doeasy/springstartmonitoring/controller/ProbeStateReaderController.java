package io.doeasy.springstartmonitoring.controller;

import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
@RestController
@RequestMapping("/statereader")
public class ProbeStateReaderController {

    @Resource
    ApplicationAvailability applicationAvailability;

    @RequestMapping(value = "/get")
    public String getState() {
        return "livenessState: " + applicationAvailability.getLivenessState() +
                "<br/>readinessState: " + applicationAvailability.getReadinessState() +
                "<br/>" + new Date();
    }
}
