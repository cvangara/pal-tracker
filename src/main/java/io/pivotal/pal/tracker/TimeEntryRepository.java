package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

public interface TimeEntryRepository {

    public abstract TimeEntry create(TimeEntry timeEntry);

    public abstract TimeEntry find(Long timeEntryId);

    public abstract List<TimeEntry> list();

    public abstract TimeEntry update(Long timeEntryId, TimeEntry timeEntryIn);

    public abstract void delete(Long timeEntryId);
}
