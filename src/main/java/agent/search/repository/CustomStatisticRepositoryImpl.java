package agent.search.repository;

import agent.search.entity.Statistic;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static agent.search.entity.QStatistic.*;


public class CustomStatisticRepositoryImpl implements CustomStatisticRepository {

    private final JPAQueryFactory queryFactory;

    public CustomStatisticRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Statistic> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startTime = startDate.atTime(0, 0, 0);
        LocalDateTime endTime = endDate.atTime(23, 59, 59);
        return queryFactory.selectFrom(statistic)
                           .where(
                                   statistic.createdAt.before(startTime).not().and(
                                           statistic.createdAt.after(endTime).not())
                           )
                           .orderBy(statistic.createdAt.desc())
                           .fetch();


    }
}
