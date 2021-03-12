package com.chris.conferenceapp.controllers;

   import com.chris.conferenceapp.models.Speaker;
import com.chris.conferenceapp.repositories.SpeakerRepository;
   import org.springframework.beans.BeanUtils;
   import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.findById(id).get();
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //deal with cascades deletes
        //check for children records before deleting
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker currentSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, currentSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(currentSpeaker);
    }

}
