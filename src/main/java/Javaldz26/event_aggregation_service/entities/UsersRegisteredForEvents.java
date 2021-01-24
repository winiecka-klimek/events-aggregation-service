package Javaldz26.event_aggregation_service.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users_events")
public class UsersRegisteredForEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registeredUserEmail;

    private String registeredUserNickname;

//    private LocalDateTime added =  LocalDateTime.now();
//
//    private LocalDateTime deleted;

    @ManyToMany
    @JoinTable(name = "registered_event" )
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "registered_user")
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getRegisteredUserEmail() {
        return registeredUserEmail;
    }

    public void setRegisteredUserEmail(String registeredUserEmail) {
        this.registeredUserEmail = registeredUserEmail;
    }

    public String getRegisteredUserNickname() {
        return registeredUserNickname;
    }

    public void setRegisteredUserNickname(String registeredUserNickname) {
        this.registeredUserNickname = registeredUserNickname;
    }

//    public LocalDateTime getAdded() {
//        return added;
//    }
//
//    public void setAdded(LocalDateTime added) {
//        this.added = added;
//    }
//
//    public LocalDateTime getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(LocalDateTime deleted) {
//        this.deleted = deleted;
//    }

    public Set<Event> getEvents() {
        return events;
    }
    public void addEvent(Event event) {
        events.add(event);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

}
