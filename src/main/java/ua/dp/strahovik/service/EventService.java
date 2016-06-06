package ua.dp.strahovik.service;


import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;

import java.util.List;

public interface EventService {

    public void addEvent(Event event);

    public void addEvent(Event event, Company company);

    public Event getEventById (Long id);

    public List<Event> getEventList ();

    public List<Event> getEventListByEventState (EventState eventState);
}
