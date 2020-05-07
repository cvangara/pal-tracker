package io.pivotal.pal.tracker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import javax.validation.Valid;
import java.util.List;

  @RestController
  public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               MeterRegistry meterRegistry) {
        this.timeEntryRepository = timeEntryRepository;
        timeEntrySummary=meterRegistry.summary("timeEntry.summary");
        actionCounter=meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping("/time-entries")
    public ResponseEntity<String> create (@Valid @RequestBody TimeEntry timeEntry){
        TimeEntry timeEntryRes=timeEntryRepository.create(timeEntry);
        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        ResponseEntity response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.CREATED);;
        return response;
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity read (@PathVariable Long timeEntryId){
          TimeEntry timeEntryRes = timeEntryRepository.find(timeEntryId);
          ResponseEntity response;

          if(null !=timeEntryRes) {
              actionCounter.increment();
              response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.OK);
          }else {
              response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NOT_FOUND);
          }
          return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity list (){
        List<TimeEntry> timeEntryResList = timeEntryRepository.list();
        actionCounter.increment();
        ResponseEntity response = new ResponseEntity<List<TimeEntry>>(timeEntryResList, HttpStatus.OK);
          return response;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update (@PathVariable("timeEntryId") Long timeEntryId,
                                  @Valid @RequestBody TimeEntry timeEntryIn){
        ResponseEntity response;
        TimeEntry timeEntryRes = timeEntryRepository.update(timeEntryId,timeEntryIn);
        if(null!=timeEntryRes) {
            actionCounter.increment();
            response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.OK);
        }else
            response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NOT_FOUND);
          return response;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete (@PathVariable("timeEntryId") Long timeEntryId){
          TimeEntry timeEntryRes =null;
          actionCounter.increment();
          timeEntryRepository.delete(timeEntryId);
          ResponseEntity response = new ResponseEntity<TimeEntry>(timeEntryRes, HttpStatus.NO_CONTENT);
          return response;
    }
}
