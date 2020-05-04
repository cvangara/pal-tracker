package io.pivotal.pal.trackerapi;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

public class TimeEntryApi {

    //privete RestTemplate restTemplate;
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryApi() {

    }

    @RequestMapping(value = "/time-entries",
                     method = RequestMethod.GET)
    public ResponseEntity<String> getTineEntries(@RequestBody TimeEntry timeEntry){
        TimeEntry timeEntryRes=timeEntryRepository.create(timeEntry);
        ResponseEntity <String> response = new ResponseEntity<>("",HttpStatus.CREATED);
        return response;
    }
}
