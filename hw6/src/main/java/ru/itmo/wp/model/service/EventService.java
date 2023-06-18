package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private final ru.itmo.wp.model.repository.EventRepository eventRepository = new EventRepositoryImpl();

    public void save(Event event) {
        eventRepository.save(event);
    }
}