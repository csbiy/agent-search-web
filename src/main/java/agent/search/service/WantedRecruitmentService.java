package agent.search.service;

import agent.search.dto.request.RecruitmentSearchRequest;
import agent.search.dto.response.RecruitmentResponse;
import agent.search.entity.JobPlanetCompany;
import agent.search.entity.MilitaryCompany;
import agent.search.entity.Recruitment;
import agent.search.repository.JobPlanetCompanyRepository;
import agent.search.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WantedRecruitmentService implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final JobPlanetCompanyRepository jobPlanetRepository;

    public Page<RecruitmentResponse> findByPaging(int page, int pageSize) {
        PageRequest pagingRequest = PageRequest.of(page, pageSize);
        Page<Recruitment> foundRecruitments = recruitmentRepository.findAll(pagingRequest);

        return foundRecruitments.map((recruitment -> {
            MilitaryCompany company = recruitment.getCompany();
            Integer remainNumber = company.getRemainNumber();

            JobPlanetCompany jobPlanetCompany = jobPlanetRepository.findByCompany(company);
            String originLink = jobPlanetCompany != null ? jobPlanetCompany.getOriginLink() : null;
            String averageReview = jobPlanetCompany != null ? jobPlanetCompany.getAverageReviews() : null;

            return RecruitmentResponse.builder()
                                      .companyName(company.getName())
                                      .createYear(company.getYear())
                                      .companyLocation(company.getLocation())
                                      .companyLogoPath(recruitment.getCompanyLogoPath())
                                      .jobPosition(recruitment.getJobPosition())
                                      .wantedOriginLink(recruitment.getOriginLink())
                                      .activeRemainNumber(remainNumber)
                                      .jobPlanetOriginLink(originLink)
                                      .jobPlanetScore(averageReview)
                                      .build();
        }));
    }

    @Override
    public Page<RecruitmentResponse> findBySearch(RecruitmentSearchRequest request) {


        return null;
    }
}
