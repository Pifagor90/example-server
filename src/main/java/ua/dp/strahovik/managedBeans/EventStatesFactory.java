package ua.dp.strahovik.managedBeans;

import ua.dp.strahovik.entities.EventState;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="eventStates")
@ApplicationScoped
public class EventStatesFactory {

    public EventState[] getEventStates(){
        return EventState.values();
    }
}
