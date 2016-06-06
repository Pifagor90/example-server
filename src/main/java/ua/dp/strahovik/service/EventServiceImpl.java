package ua.dp.strahovik.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import ua.dp.strahovik.dao.EventDAO;
import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Service
@ManagedBean(name="eventService")
@ViewScoped
public class EventServiceImpl implements EventService{
//    TODO: implement normal logging

    private EventDAO eventDAO;

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        logger.error("Event saved successfully, Event Details=" + event);
        this.eventDAO.addEvent(event);
    }

    @Override
    @Transactional
    public void addEvent(Event event, Company company) {
        event.setResponsible(company);
        this.addEvent(event);
    }

    @Override
    @Transactional (readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Event getEventById(Long id) {
        return this.eventDAO.getEventByIdFetchEager(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Event> getEventList() {
        return this.eventDAO.getEventListFetchEager();
    }

    @Override
    @Transactional (readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Event> getEventListByEventState(EventState eventState) {
        return this.eventDAO.getEventListByEventStateFetchEager(eventState);
    }
}
