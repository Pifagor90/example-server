package ua.dp.strahovik.service;


import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;
import ua.dp.strahovik.managedBeans.EventBean;

import java.util.List;

public interface EventService {

    public void addEvent(Event event);

    public String addEvent(EventBean eventBean);

    public Event getEventById (Long id);

    public List<Event> getEventList ();

    public List<Event> getEventListByEventState (EventState eventState);
}
