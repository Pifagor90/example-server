package ua.dp.strahovik.dao;


import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;

import java.util.List;

public interface EventDAO {

    public void addEvent(Event event);

    public Event getEventByIdFetchEager(Long id);

    public List<Event> getEventListFetchEager();

    public List<Event> getEventListByEventStateFetchEager(EventState eventState);


}
