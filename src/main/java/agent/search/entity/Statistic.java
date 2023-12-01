package agent.search.entity;

import agent.search.enumeration.StatisticProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private StatisticProperty property;

    private String value;

    private LocalDateTime createdAt;

    @Builder
    private Statistic(StatisticProperty property, String value, LocalDateTime createdAt) {
        this.property = property;
        this.value = value;
        this.createdAt = createdAt;
    }

    public boolean hasProperty(StatisticProperty property) {
        return this.property.equals(property);
    }

    public boolean isToday() {
        return this.createdAt.toLocalDate().isEqual(LocalDate.now());
    }
}