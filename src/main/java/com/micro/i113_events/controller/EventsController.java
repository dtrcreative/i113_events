package com.micro.i113_events.controller;

import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.service.EventService;
import com.micro.i113_events.service.sheduler.TelegramShedule;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events/")
public class EventsController {

    private final EventService unitService;
    private final TelegramShedule shedule;

    public EventsController(EventService unitService, TelegramShedule shedule) {
        this.unitService = unitService;
        this.shedule = shedule;
    }

    @GetMapping("/all")
    public List<EventDto> getAllUnits(@RequestHeader(value = "UserId") String userId) {
        return unitService.getAll(userId);
    }

    @PostMapping("/")
    public EventDto createUnit(@RequestBody EventDto unitDto) {
        return unitService.create(unitDto);
    }

    @PutMapping("/")
    public EventDto updateUnit(@RequestBody EventDto unitDto) {
        return unitService.update(unitDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable("id") int id) {
        unitService.delete(id);
    }

    @PostMapping("/selected")
    public void deleteSelected(@RequestBody List<Integer> values) {
        unitService.deleteSelected(values);
    }

    @DeleteMapping("/")
    public void deleteAllUnit(@RequestHeader(value = "UserId") String userId) {
        unitService.deleteAll(userId);
    }

    @GetMapping("/alltest")
    public void getAllUnits() {
        this.shedule.action();
    }

}
