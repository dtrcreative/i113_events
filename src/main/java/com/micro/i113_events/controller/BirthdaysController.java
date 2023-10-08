package com.micro.i113_events.controller;

import com.micro.i113_events.model.dto.BirthdayDto;
import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.service.BirthdayService;
import com.micro.i113_events.service.converter.BirthdayConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i113/api/events/")
public class BirthdaysController {

    private BirthdayService unitService;

    private BirthdaysController(BirthdayService unitService){
        this.unitService = unitService;
    }

    @GetMapping("/all")
    public List<BirthdayDto> getAllUnits(@RequestHeader(value = "user") String authorization) {
        return unitService.getAll(authorization);
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
    public void deleteAllUnit(@RequestHeader(value = "user") String userName) {
        unitService.deleteAll(userName);
    }

}
