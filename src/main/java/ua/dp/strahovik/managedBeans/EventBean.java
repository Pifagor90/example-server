package ua.dp.strahovik.managedBeans;

import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;
import ua.dp.strahovik.entities.EventState;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name="event")
@ViewScoped
public class EventBean implements Serializable{

    private EventState eventState;
    private Date registrationDate;
    private Date deadlineDate;
    private Company responsible;
    private String description;
    private List<PhotoUrlWrapper> photos = new ArrayList<>();

    @PostConstruct
    public void init() {
        responsible = new Company();
        photos = new ArrayList<PhotoUrlWrapper>();
        photos.add(new PhotoUrlWrapper());
    }

    public void extend() {
        photos.add(new PhotoUrlWrapper());
    }

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Company getResponsible() {
        return responsible;
    }

    public void setResponsible(Company responsible) {
        this.responsible = responsible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoUrlWrapper> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoUrlWrapper> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                ", eventState=" + eventState +
                ", registrationDate=" + registrationDate +
                ", deadlineDate=" + deadlineDate +
                ", responsible=" + responsible +
                ", description='" + description + '\'' +
                ", photos=" + photos +
                '}';
    }

    public Event convertEventBeanToEvent() {
        Event event = new Event();
        event.setPhotos(this
                        .getPhotos()
                        .stream()
                        .map(PhotoUrlWrapper::getUrl)
                        .distinct()
                        .collect(Collectors.toList())
        );
        event.setDeadlineDate(this.getDeadlineDate());
        event.setDescription(this.getDescription());
        event.setEventState(this.getEventState());
        event.setRegistrationDate(this.getRegistrationDate());
        event.setResponsible(this.getResponsible());
        return event;
    }
}
