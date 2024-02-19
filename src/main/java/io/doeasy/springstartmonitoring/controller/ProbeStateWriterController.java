package io.doeasy.springstartmonitoring.controller;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
@RestController
@RequestMapping("/statewriter")
public class ProbeStateWriterController {

    @Resource
    private ApplicationEventPublisher ApplicationEventPublisher;

    @RequestMapping(value = "/broken")
    public String broken() {
        AvailabilityChangeEvent.publish(ApplicationEventPublisher, ProbeStateWriterController.this, LivenessState.BROKEN);
        return "Broken successfully, " + new Date();
    }

    @RequestMapping(value = "/correct")
    public String correct() {
        AvailabilityChangeEvent.publish(ApplicationEventPublisher, ProbeStateWriterController.this, LivenessState.CORRECT);
        return "Correct successfully, " + new Date();
    }

    @RequestMapping(value = "/refuse")
    public String refuse() {
        AvailabilityChangeEvent.publish(ApplicationEventPublisher, ProbeStateWriterController.this, ReadinessState.REFUSING_TRAFFIC);
        return "Refuse traffic successfully, " + new Date();
    }

    @RequestMapping(value = "/accept")
    public String accept() {
        AvailabilityChangeEvent.publish(ApplicationEventPublisher, ProbeStateWriterController.this, ReadinessState.ACCEPTING_TRAFFIC);
        return "Accept traffic successfully, " + new Date();
    }
}
