package ua.dp.strahovik.entities;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Companies")
public class Company {

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<Event>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
