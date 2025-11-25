package ru.job4j.devops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

class CalcServiceTest extends ContainersConfig {

    @Autowired
    private CalcService calcService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CalcEventRepository calcEventRepository;

    @Test
    public void whenAddNumbersThenSaveCalcEvent() {
        var user = new User();
        user.setName("TestUser");
        userRepository.save(user);

        int first = 5;
        int second = 3;
        calcService.add(user, first, second);

        var calcEvent = calcEventRepository.findByUserId(user.getId()).orElse(null);
        assertThat(calcEvent).isNotNull();
        assertThat(calcEvent.getUserId()).isEqualTo(user.getId());
        assertThat(calcEvent.getFirst()).isEqualTo(first);
        assertThat(calcEvent.getSecond()).isEqualTo(second);
        assertThat(calcEvent.getResult()).isEqualTo(8);
        assertThat(calcEvent.getType()).isEqualTo("ADD");
        assertThat(calcEvent.getCreateDate()).isNotNull();
    }
}

