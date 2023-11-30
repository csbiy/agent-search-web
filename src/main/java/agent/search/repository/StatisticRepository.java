package agent.search.repository;

import agent.search.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long>, CustomStatisticRepository {

}