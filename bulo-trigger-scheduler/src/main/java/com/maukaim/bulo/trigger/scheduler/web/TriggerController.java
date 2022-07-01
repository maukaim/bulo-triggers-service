package com.maukaim.bulo.trigger.scheduler.web;

import com.maukaim.bulo.trigger.scheduler.ScheduleTriggerConfig;
import com.maukaim.bulo.trigger.scheduler.ScheduleTriggerService;
import com.maukaim.bulo.triggers.api.TriggerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/triggers/schedule")
public class TriggerController {

    private final ScheduleTriggerService triggerService;

    public TriggerController(@Autowired ScheduleTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Boolean> removeSchedule(@RequestParam String flowId,
                                                  @RequestParam Set<String> stageIds) {
        TriggerId triggerId = TriggerId.of(flowId, stageIds);
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getStageIds());
        return ResponseEntity.ok(isRemoved);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ScheduleTriggerConfig> addSchedule(@RequestBody ScheduleTriggerConfig scheduleTriggerConfig) {
        return ResponseEntity.ok(this.triggerService.setTrigger(scheduleTriggerConfig));
    }
}