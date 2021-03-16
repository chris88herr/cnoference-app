package com.chris.conferenceapp.controllers;

import com.chris.conferenceapp.models.Attendee;
import com.chris.conferenceapp.repositories.AttendeeRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")

public class AttendeeController {
    @Autowired
    private AttendeeRepository attendeesRepository;

    @GetMapping
    public List<Attendee> list(){
        return attendeesRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Attendee get(@PathVariable Long id){
        return attendeesRepository.getOne(id);
    }

    @PostMapping
    public Attendee create(@RequestBody final Attendee attendee){
        return attendeesRepository.saveAndFlush(attendee);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        attendeesRepository.deleteById(id);
    }

    @RequestMapping (value = "{id}" , method = RequestMethod.PUT)
    public Attendee update(@PathVariable Long id, @RequestBody Attendee attendee){
        Attendee currentAttendee = attendeesRepository.getOne(id);
        BeanUtils.copyProperties(attendee, currentAttendee, "attendee_id");
        return attendeesRepository.saveAndFlush(currentAttendee);
    }
}
