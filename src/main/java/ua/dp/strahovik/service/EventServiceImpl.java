package ua.dp.strahovik.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import ua.dp.strahovik.dao.EventDAO;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dp.strahovik.managedBeans.EventBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Service
@ManagedBean(name="eventService")
@ViewScoped
public class EventServiceImpl implements EventService{

    private EventDAO eventDAO;

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        logger.debug("invoked addEvent(Event event), Event details=" + event.toString());
        this.eventDAO.addEvent(event);
    }

    @Override
    @Transactional
    public String addEvent(EventBean eventBean) {
        logger.debug("invoked addEvent(EventBean eventBean) EventBean details=" + eventBean.toString());
        Event event = eventBean.convertEventBeanToEvent();
        this.addEvent(event);
        return "event.xhtml";
    }

    @Override
    @Transactional (readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Event getEventById(Long id) {
        logger.debug("invoked getEventById(Long id), id=" + id);
        return this.eventDAO.getEventByIdFetchEager(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Event> getEventList() {
        logger.debug("invoked List<Event> getEventList");
        return this.eventDAO.getEventListFetchEager();
    }

    @Override
    @Transactional (readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Event> getEventListByEventState(EventState eventState) {
        logger.debug("invoked List<Event> getEventListByEventState(EventState eventState, eventState=" + eventState);
        return this.eventDAO.getEventListByEventStateFetchEager(eventState);
    }

}
