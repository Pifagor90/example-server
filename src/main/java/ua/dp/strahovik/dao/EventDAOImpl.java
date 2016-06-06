package ua.dp.strahovik.dao;


import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class EventDAOImpl implements EventDAO {
//    TODO: implement normal logging
    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    private CompanyDAO companyDAO;

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addEvent(Event event) {
        if (event.getResponsible() != null){
            if (companyDAO.companyExistsByName(event.getResponsible().getName())){
                event.setResponsible(companyDAO.getCompanyByName(event.getResponsible().getName()));
            } else {
                if (event.getResponsible().getName().equals("")){
                    event.setResponsible(null);
                } else {
                    companyDAO.addCompany(event.getResponsible());
                }
            }
        }
        URL templateUrl = null;
        try {
            templateUrl = new URL("http://");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        event.getPhotos().removeIf(Predicate.isEqual(templateUrl));
//        for (URL url:event.getPhotos()){
//            if (url.equals(templateUrl)){
//                url
//            }
//        }

        entityManager.persist(event);

        logger.info("Event saved successfully, Event Details=" + event);
    }

    public Event getEventByIdFetchEager(Long id) {
        TypedQuery<Event> query = entityManager.createNamedQuery("Events.getEventByIdFetchEager", Event.class);
        query.setParameter("id", id);
        Event event = query.getSingleResult();
        logger.info("Event retrieved successfully, Event Details=" + event);
        return event;
    }

    public List<Event> getEventListFetchEager() {
        TypedQuery<Event> query = entityManager.createNamedQuery("Events.getEventListFetchEager", Event.class);
        List<Event> events = query.getResultList();
//        Distinct
        Collection<Event> tmpEventList = new LinkedHashSet<>(events.size());
        tmpEventList.addAll(events);
        events.clear();
        events.addAll(tmpEventList);
//        Distinct end
        logger.info("Event list retrieved successfully, Details=" + events);
        return events;
    }

    @Override
    public List<Event> getEventListByEventStateFetchEager(EventState eventState) {
        TypedQuery<Event> query = entityManager.createNamedQuery(
                "Events.getEventListByEventStateFetchEager", Event.class);
        query.setParameter("eventState", eventState);
//        Distinct
        List<Event> events = query.getResultList();
        Collection<Event> tmpEventList = new LinkedHashSet<>(events.size());
        tmpEventList.addAll(events);
        events.clear();
        events.addAll(tmpEventList);
//        Distinct end
        logger.info("Event list by EventState retrieved successfully, EventState " + eventState + " Event list Details=" + events);
        return events;
    }
}
