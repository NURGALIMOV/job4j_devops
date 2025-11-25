package ru.job4j.devops.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class CalcEventListenerTest extends ContainersConfig {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private CalcEventRepository calcEventRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void whenSendCalcEvent() {
        var user = new User();
        user.setName("Job4j new member : " + System.nanoTime());
        User savedUser = userRepository.save(user);

        CalcEvent calcEvent = new CalcEvent();
        calcEvent.setUserId(savedUser.getId());
        calcEvent.setFirst(1);
        calcEvent.setSecond(2);
        calcEvent.setResult(3);
        calcEvent.setCreateDate(LocalDateTime.now());
        calcEvent.setType("ADD");

        kafkaTemplate.send("calc", calcEvent);
        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    var optionalUser = calcEventRepository.findByUserId(savedUser.getId());
                    assertThat(optionalUser).isPresent();
                });
    }
}
