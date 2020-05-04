package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> timeEntriesMap = new HashMap<>();
    private TimeEntry imeEntryOut;
    long i=1;
    public TimeEntry create(TimeEntry imeEntry){

        //for(int i =1 ;i<0; i++) {
            imeEntryOut = new TimeEntry();
            imeEntryOut.setProjectId(imeEntry.getProjectId());
            imeEntryOut.setUserId(imeEntry.getUserId());
            imeEntryOut.setDate(imeEntry.getDate());
            imeEntryOut.setId(i++);
            imeEntryOut.setHours(imeEntry.getHours());
        //}
            timeEntriesMap.put(imeEntryOut.getId(), imeEntryOut);

        /*imeEntryOut = new TimeEntry();
        imeEntryOut.setProjectId(imeEntry.getProjectId());
        imeEntryOut.setUserId(imeEntry.getUserId());
        imeEntryOut.setDate(imeEntry.getDate());
        imeEntryOut.setId(2L);
        imeEntryOut.setHours(4);
        timeEntriesMap.put(imeEntryOut.getId(),imeEntryOut);*/

        return timeEntriesMap.get(imeEntryOut.getId());

    }


    public TimeEntry find(Long timeEntryId){
        return timeEntriesMap.get(timeEntryId);
    }

    public TimeEntry update(Long timeEntryId,TimeEntry timeEntryRes){
        imeEntryOut = new TimeEntry();
        imeEntryOut=timeEntriesMap.get(timeEntryId);
        System.out.println("*****InMemoryTimeEntryRepository:update(timeEntryId)"+timeEntryId);
        System.out.println("*****InMemoryTimeEntryRepository:update(imeEntryOut)"+imeEntryOut);
        if(null!=imeEntryOut) {
            imeEntryOut.setProjectId(timeEntryRes.getProjectId());
            imeEntryOut.setUserId(timeEntryRes.getUserId());
            imeEntryOut.setDate(timeEntryRes.getDate());
            imeEntryOut.setId(timeEntryId);
            imeEntryOut.setHours(timeEntryRes.getHours());

            timeEntriesMap.put(timeEntryId, imeEntryOut);
        }

        return timeEntriesMap.get(timeEntryId);
    }

    public void delete(Long timeEntryId){
        TimeEntry timeEntryOut = timeEntriesMap.get(timeEntryId);
        System.out.println("InMemoryTimeEntryRepository (DELETE) TimeEntry timeEntryOut : "+ timeEntryOut);
        timeEntriesMap.remove(timeEntryId);
    }

    public List<TimeEntry> list(){
       List<TimeEntry> timeEntryList = new ArrayList<>(timeEntriesMap.values());
       return timeEntryList;
    }
}
