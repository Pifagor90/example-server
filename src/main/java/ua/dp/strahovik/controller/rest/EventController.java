package ua.dp.strahovik.controller.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;
import ua.dp.strahovik.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/rest/event")
public class EventController {

    private EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addEvent(@RequestParam(value = "event", required = true) Event event) {
        eventService.addEvent(event);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable(value = "eventId") Long eventId) {
        return eventService.getEventById(eventId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Event> getEventList() {
        List<Event> eventList = eventService.getEventList();
        logger.debug("Event list retrieved successfully, Event list Details=" + eventList);
        return eventList;
    }

    @RequestMapping(value = "/eventStateList/{eventState}", method = RequestMethod.GET)
    public List<Event> getEventListByEventState(@RequestParam(value = "eventState", required = true) EventState eventState) {
        return eventService.getEventListByEventState(eventState);
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public EventService getEventService() {
        return eventService;
    }
}
