package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Color;
import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.itmo.web.hw4.model.Color.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", BLUE),
            new User(6, "pashka", "Pavel Mavrin", RED),
            new User(9, "geranazavr555", "Georgiy Nazarov", GREEN),
            new User(11, "tourist", "Gennady Korotkevich", RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "45th ICPC World Finals Challenge powered by Huawei",
                    "Hello, Codeforces!\n" +
                            "\n" +
                            "We are happy to invite you to the great event the 45th ICPC World Finals Challenge powered by Huawei，which will start on November 17, 2022, 00:00 UTC (UTC+0).\n" +
                            "\n" +
                            "In this Challenge, You will have a unique chance:\n" +
                            "\n" +
                            "to compete during 14 days online challenge;\n" +
                            "to solve 1 or 2 problems prepared by different business domains of HUAWEI;\n" +
                            "to win amazing prizes from HUAWEI!\n" +
                            "As a special prize, HUAWEI together with ICPC Foundation will provide the travel trip to the 46th Annual ICPC World Finals in a guest role to the 2 winners (1 winner for each problem)!\n" +
                            "\n" +
                            "Everybody is welcome to participate. It is an individual competition.\n" +
                            "\n" +
                            "45th ICPC World Finals Challenge powered by Huawei (open to public):\n" +
                            "\n" +
                            "Start: November 17, 2022 00:00 UTC (UTC+0)\n" +
                            "Finish: November 30, 2022 23:59 UTC (UTC+0)",
                    1),
            new Post(9, "The problem about hacking and cheating",
                    "There is a problem I notice recently When hacking, for example, some people will hack others to see others' code after they have done such as D question. At this time, some of his friends may come to him to ask for the solution of D question, but many people do not want to take the risk of their own solution being judged cheating. They might send someone else's code which copied from hacking other people to their friends, and if their friend are judged cheating by system, the one who be hacked is really hard to prove he is innocent. So I think this problem is really serious",
                    6),
            new Post(4, "Вторая командная интернет-олимпиада, Сезон 2022-23",
                    "Всем привет!\n" +
                            "\n" +
                            "Уже завтра, 9 октября 2022 года в 13:00 (по Московскому времени) состоится вторая командная интернет-олимпиада сезона 2022-23. Приглашаем вас принять в ней участие! Условия в этот раз будут по «Рику» и Морти – кажется, сейчас как раз уже некоторое время выходит шестой сезон.\n" +
                            "\n" +
                            "Начиная с этого раза командные интернет-олимпиады будут проходить в двух номинациях: в базовой и усложненной. Регистрация для двух номинаций общая, не забудьте зарегистрироваться на цикл командных интернет-олимпиад в этом сезоне перед началом олимпиады. Обратите внимание, что для участия в командных олимпиадах нужно зарегистрировать именно команду. Команда может содержать от 1 до 3 человек.\n" +
                            "\n" +
                            "Ссылка на регистрацию команд доступна из данного анонса, а также на основном сайте интернет-олимпиад. Всю дополнительную информацию, а также расписание всех предстоящих командных интернет-олимпиад можно посмотреть на той же страничке.\n" +
                            "\n",
                    9),
            new Post(5, "Codeforces Beta Round #1",
                    "В этом топике я бы хотел поднять вопросы вокруг Codeforces Beta Round #1. Что вам понравилось? Что не понравилось? Что показалось неудобным? Что вы видите можно изменить, чтобы сделать участие более комфортным? Какие у вас были проблемы во время участия? Интересно ваше мнение по поводу интерфейса.\n" +
                            "\n" +
                            "Просьба не отписываться ярко по поводу (не)доступности сайта с адреса http://codeforces.ru/ (я рекомендовал использовать http://codeforces.ru:8081/). Я догадываюсь в чем проблема. Связка Apache Virtual Hosts + AJP Connector то ли настроена кривовато, то ли работает плоховато. Короче, это я исправлю.\n" +
                            "\n" +
                            " Жду комментариев. И, конечно, приглашаю на Codeforces Beta Round #2.\n" +
                            "\n" +
                            "Еще момент. Мне бы хотелось, чтобы кто-то взял на себя разбор задач прошедшего раунда. Это надо сделать на русском и английском языках. Разумеется вы должны сдать задачи либо на контесте, либо в дорешивании. Если у вас есть желание это сделать - пишите в комментариях. Ваш пост будет опубликован на главной и позже доступен по спец. ссылке из контеста.",
                    1)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
