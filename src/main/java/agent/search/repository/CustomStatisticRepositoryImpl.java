package agent.search.repository;

import agent.search.dto.response.RecruitmentResponse;
import agent.search.entity.QStatistic;
import agent.search.entity.Statistic;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static agent.search.entity.QJobPlanetCompany.jobPlanetCompany;
import static agent.search.entity.QJobPlanetReview.jobPlanetReview;
import static agent.search.entity.QMilitaryCompany.militaryCompany;
import static agent.search.entity.QRecruitment.recruitment;
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
