package ru.job4j.devops.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalcServiceTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine")
            .withReuse(true);

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalcEventRepository calcEventRepository;

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
    }

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();
    }

    @AfterAll
    static void afterAll() {
        POSTGRES.stop();
    }

    @Test
    public void whenAddNumbersThenSaveCalcEvent() {
        var user = new User();
        user.setName("TestUser");
        userRepository.save(user);

        int first = 5;
        int second = 3;
        calcService.add(user, first, second);

        var calcEvents = calcEventRepository.findAll();
        assertThat(calcEvents).hasSize(1);
        calcEvents.forEach(calcEvent -> {
            assertThat(calcEvent.getUserId()).isEqualTo(user.getId());
            assertThat(calcEvent.getFirst()).isEqualTo(first);
            assertThat(calcEvent.getSecond()).isEqualTo(second);
            assertThat(calcEvent.getResult()).isEqualTo(8);
            assertThat(calcEvent.getType()).isEqualTo("ADD");
            assertThat(calcEvent.getCreateDate()).isNotNull();
        });
    }
}

