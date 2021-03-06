package com.maukaim.bulo.trigger.scheduler.app.web;

import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfig;
import com.maukaim.bulo.triggers.io.TriggerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/triggers/schedule")
public class TriggerController {

    private final ScheduleTriggerService triggerService;

    public TriggerController(@Autowired ScheduleTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId) {
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok(isRemoved);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ScheduleTriggerConfig> addSchedule(@RequestBody ScheduleTriggerConfig scheduleTriggerConfig) {
        return ResponseEntity.ok(this.triggerService.setTrigger(scheduleTriggerConfig));
    }
}
