package io.pivotal.pal.tracker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

  @RestController
  public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<String> create (@Valid @RequestBody TimeEntry timeEntry){
        System.out.println("*****Time Controller*****"+timeEntry);
        TimeEntry timeEntryRes=timeEntryRepository.create(timeEntry);
        ResponseEntity response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.CREATED);;
        return response;
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity read (@PathVariable Long timeEntryId){
        System.out.println("*****Time Controller***** (timeEntryId)"+timeEntryId);
          TimeEntry timeEntryRes = timeEntryRepository.find(timeEntryId);
          ResponseEntity response;

          if(null !=timeEntryRes)
               response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.OK);
           else
               response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NOT_FOUND);

          return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity list (){
        List<TimeEntry> timeEntryResList = timeEntryRepository.list();
        System.out.println("*****Time Controller*****"+timeEntryResList);
        ResponseEntity response = new ResponseEntity<List<TimeEntry>>(timeEntryResList, HttpStatus.OK);
          return response;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update (@PathVariable("timeEntryId") Long timeEntryId,
                                  @Valid @RequestBody TimeEntry timeEntryIn){
        System.out.println("*****TimeEntryRepository:update(timeEntryId)="+timeEntryId);

        ResponseEntity response;
        TimeEntry timeEntryRes = timeEntryRepository.update(timeEntryId,timeEntryIn);
        System.out.println("*****TimeEntryController:update"+timeEntryRes);
        if(null!=timeEntryRes)
        response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.OK);
        else
            response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NOT_FOUND);
          return response;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete (@PathVariable("timeEntryId") Long timeEntryId){
          TimeEntry timeEntryRes =null;
          timeEntryRepository.delete(timeEntryId);
          ResponseEntity response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NO_CONTENT);
          return response;
    }
}
