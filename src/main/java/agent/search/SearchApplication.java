package agent.search;

import agent.search.entity.Statistic;
import agent.search.enumeration.StatisticProperty;
import agent.search.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SearchApplication {

    private final StatisticRepository statisticRepository;

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            statisticRepository.saveAll(
                    List.of(
                            new Statistic(StatisticProperty.ACTIVE_ASSIGN_NUM, "1"),
                            new Statistic(StatisticProperty.SUPP_ASSIGN_NUM, "2"),
                            new Statistic(StatisticProperty.ACTIVE_ENLIST_NUM, "3"),
                            new Statistic(StatisticProperty.SUPP_ENLIST_NUM, "4")
                    )
            );
        };
    }
}
