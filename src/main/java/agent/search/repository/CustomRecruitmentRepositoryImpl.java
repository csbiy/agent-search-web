package agent.search.repository;

import agent.search.dto.request.RecruitmentSearchRequest;
import agent.search.dto.response.RecruitmentResponse;
import agent.search.entity.QJobPlanetCompany;
import agent.search.entity.QJobPlanetReview;
import agent.search.entity.QMilitaryCompany;
import agent.search.entity.QRecruitment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.support.ConstantHidingExpression;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.hibernate.query.criteria.JpaExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static agent.search.entity.QJobPlanetCompany.*;
import static agent.search.entity.QJobPlanetReview.*;
import static agent.search.entity.QMilitaryCompany.militaryCompany;
import static agent.search.entity.QRecruitment.*;


public class CustomRecruitmentRepositoryImpl implements CustomRecruitmentRepository {

    private final JPAQueryFactory queryFactory;

    public CustomRecruitmentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<RecruitmentResponse> searchByCondition(List<String> keywords, String searchTerm, PageRequest pageRequest) {
        List<RecruitmentResponse> result = searchQuery(searchTerm, keywords)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        JPAQuery<RecruitmentResponse> countQuery = searchQuery(searchTerm, keywords);

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    private JPAQuery<RecruitmentResponse> searchQuery(String searchTerm, List<String> keywords) {
        return queryFactory.select(
                        Projections.constructor(RecruitmentResponse.class,
                                militaryCompany.name,
                                recruitment.originLink,
                                recruitment.companyLogoPath,
                                recruitment.jobPosition,
                                militaryCompany.year,
                                militaryCompany.location,
                                militaryCompany.activeAssignedNum.subtract(militaryCompany.activeEnlistNum),
                                ExpressionUtils.as(
                                        JPAExpressions.select(jobPlanetReview.reviewScore.avg().stringValue())
                                                .from(jobPlanetReview)
                                                .where(jobPlanetReview.jobPlanetCompany.eq(jobPlanetCompany)), "average"),
                                jobPlanetCompany.originLink
                        )
                ).from(recruitment)
                .innerJoin(militaryCompany).on(militaryCompany.eq(recruitment.company))
                .leftJoin(jobPlanetCompany).on(jobPlanetCompany.company.eq(militaryCompany))
                .where(isSearchTermMatch(searchTerm).and(isFilterMatch(keywords)))
                ;
    }

    private BooleanExpression isSearchTermMatch(String searchTerm) {
        String trimmedInput = searchTerm.trim();
        if (!StringUtils.hasText(trimmedInput)) {
            return null;
        }
        return militaryCompany.name.contains(trimmedInput).or(recruitment.jobPosition.contains(trimmedInput));
    }

    private BooleanBuilder isFilterMatch(List<String> keywords) {
        BooleanBuilder builder = new BooleanBuilder();
        for (String keyword : keywords) {
            builder.or(recruitment.jobPosition.contains(keyword));
        }
        return builder;
    }
}
