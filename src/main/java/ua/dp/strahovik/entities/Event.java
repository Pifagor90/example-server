package ua.dp.strahovik.entities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Events")
@NamedQueries({
        @NamedQuery(name="Events.getEventByIdFetchEager",
                query="SELECT event FROM Event event LEFT JOIN FETCH event.photos WHERE event.id = :id"),
        @NamedQuery(name="Events.getEventListFetchEager",
                query="SELECT event FROM Event event LEFT JOIN FETCH event.photos"),
        @NamedQuery(name="Events.getEventListByEventStateFetchEager",
                query="SELECT event FROM Event event LEFT JOIN FETCH event.photos WHERE" +
                        " event.eventState = :eventState"),
})
public class Event implements Cloneable{

    @Id
    @Column(name="ID", unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name="STATE")
    private EventState eventState;

    @Temporal(TemporalType.DATE)
    @Column(name="CREATION_DATE", updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name="REGISTRATION_DATE")
    private Date registrationDate;

    @Temporal(TemporalType.DATE)
    @Column(name="DEADLINE_DATE")
    private Date deadlineDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Company responsible;

    @Column(name="DESCRIPTION")
    private String description;

    @ElementCollection
    @CollectionTable(name = "Event_photos_url", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "URL")
    private List<URL> photos = new ArrayList<URL>();

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
        if (eventState == null){
            eventState = EventState.WAITING;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public List<URL> getPhotos() {
        return photos;
    }

    public void setPhotos(List<URL> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventState=" + eventState +
                ", creationDate=" + creationDate +
                ", registrationDate=" + registrationDate +
                ", deadlineDate=" + deadlineDate +
                ", responsible=" + responsible +
                ", description='" + description + '\'' +
                ", photos=" + photos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (getId() != event.getId()) return false;
        if (getEventState() != event.getEventState()) return false;
        if (!getCreationDate().equals(event.getCreationDate())) return false;
        if (getRegistrationDate() != null ? !getRegistrationDate().equals(event.getRegistrationDate()) : event.getRegistrationDate() != null)
            return false;
        if (getDeadlineDate() != null ? !getDeadlineDate().equals(event.getDeadlineDate()) : event.getDeadlineDate() != null)
            return false;
        if (getResponsible() != null ? !getResponsible().equals(event.getResponsible()) : event.getResponsible() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(event.getDescription()) : event.getDescription() != null)
            return false;
        return !(getPhotos() != null ? !getPhotos().equals(event.getPhotos()) : event.getPhotos() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getEventState().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        result = 31 * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
        result = 31 * result + (getDeadlineDate() != null ? getDeadlineDate().hashCode() : 0);
        result = 31 * result + (getResponsible() != null ? getResponsible().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPhotos() != null ? getPhotos().hashCode() : 0);
        return result;
    }
}
