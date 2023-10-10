package com.micro.i113_events.controller;

import com.micro.i113_events.model.dto.BirthdayDto;
import com.micro.i113_events.service.BirthdayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events/birthdays")
public class BirthdaysController {

    private final BirthdayService unitService;

    private BirthdaysController(BirthdayService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/all")
    public List<BirthdayDto> getAllUnits(@RequestHeader(value = "UserId") String userId) {
        return unitService.getAll(userId);
    }
    @PostMapping("/")
    public BirthdayDto createUnit(@RequestBody BirthdayDto unitDto) {
        return unitService.create(unitDto);
    }

    @PutMapping("/")
    public BirthdayDto updateUnit(@RequestBody BirthdayDto unitDto) {
        return unitService.update(unitDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable("id") int id) {
        unitService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAllUnit(@RequestHeader(value = "UserId") String userId) {
        unitService.deleteAll(userId);
    }

}
