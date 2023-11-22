package agent.search.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statisticKey;

    private String property;

    private String value;

    private LocalDateTime createdAt;

}