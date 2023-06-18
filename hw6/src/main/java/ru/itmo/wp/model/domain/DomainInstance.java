package ru.itmo.wp.model.domain;

import java.util.Date;

public interface DomainInstance {
    long getId();

    void setId(long id);

    Date getCreationTime();

    void setCreationTime(Date creationTime);

}
