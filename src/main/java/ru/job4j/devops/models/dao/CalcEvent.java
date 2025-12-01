package ru.job4j.devops.models.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calc_events")
public class CalcEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first")
    private Integer first;

    @Column(name = "second")
    private Integer second;

    @Column(name = "result")
    private Integer result;

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @Column(name = "type")
    private String type;
}

