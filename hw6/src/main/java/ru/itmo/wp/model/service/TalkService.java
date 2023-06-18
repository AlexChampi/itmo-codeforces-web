package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImp;

import java.util.List;
import java.util.Objects;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImp();

    public void validateMessage(User targetUser, User user, String text) throws ValidationException {
        if (Objects.isNull(targetUser)) {
            throw new ValidationException("Target user not found");
        }

        if (Objects.isNull(user)) {
            throw new ValidationException("Source user not authorized");
        }

        if (user.equals(targetUser)) {
            throw new ValidationException("You can't send message to yourself");
        }

        if (Strings.isNullOrEmpty(text) && text.trim().isEmpty()) {
            throw new ValidationException("Message is required");
        }
    }

    public void save(Talk talk) {
        talkRepository.save(talk);
    }

    public List<Talk> findAllWithUser(long userId) {
        return talkRepository.findAllWithUser(userId);
    }
}
