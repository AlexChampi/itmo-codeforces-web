package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.EventRepository;

public class EventRepositoryImpl extends AbstractBasicRepository<Event> implements EventRepository {
    public EventRepositoryImpl() {
        super("Event", Event::new);
    }


    @Override
    public void save(Event event) {
        save(event, "INSERT INTO `Event` (`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())",
                event.getUserId(), event.getType().name());
    }

    public Event find(long id) {
        return findByParams("SELECT * FROM Event WHERE id=?", id);
    }
}
