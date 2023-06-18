package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public interface DomainInstance extends Serializable {
    long getId();

    void setId(long id);

    Date getCreationTime();

    void setCreationTime(Date creationTime);

}
