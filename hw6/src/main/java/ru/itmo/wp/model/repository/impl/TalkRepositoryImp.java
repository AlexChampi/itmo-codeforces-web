package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;

import java.util.List;

public class TalkRepositoryImp extends AbstractBasicRepository<Talk> implements TalkRepository {

    public TalkRepositoryImp() {
        super("Talk", Talk::new);
    }

    @Override
    public List<Talk> findAllWithUser(long userId) {
        return findAllWithParams("SELECT * FROM Talk WHERE sourceUserId LIKE ? OR targetUserId LIKE ? ORDER BY creationTime",
                userId, userId);
    }

    @Override
    public void save(Talk talk) {
        save(talk,
                "INSERT INTO `Talk` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())",
                talk.getSourceUserId(), talk.getTargetUserId(), talk.getText());
    }

    private Talk find(long id) {
        return findByParams("SELECT * FROM Talk WHERE id=?", id);
    }
}
