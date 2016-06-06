package ua.dp.strahovik.service;

import ua.dp.strahovik.entities.EventState;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="eventStates")
public class EventStatesFactory {

    public EventState[] getEventStates(){
        return EventState.values();
    }
}
