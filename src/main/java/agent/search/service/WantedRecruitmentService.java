package agent.search.service;

import agent.search.dto.RecruitmentDto;
import agent.search.entity.MilitaryCompany;
import agent.search.entity.Recruitment;
import agent.search.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WantedRecruitmentService implements RecruitmentService {

    private final RecruitmentRepository repository;

    public Page<RecruitmentDto> findByPaging(int page, int pageSize) {
        PageRequest pagingRequest = PageRequest.of(page, pageSize);
        Page<Recruitment> foundRecruitments = repository.findAll(pagingRequest);

        return foundRecruitments.map((recruitment -> {
            MilitaryCompany company = recruitment.getCompany();
            Integer remainNumber = company.getRemainNumber();

            return RecruitmentDto.builder()
                    .companyName(company.getName())
                    .createYear(company.getYear())
                    .companyLocation(company.getLocation())
                    .companyLogoPath(recruitment.getCompanyLogoPath())
                    .jobPosition(recruitment.getJobPosition())
                    .originLink(recruitment.getOriginLink())
                    .activeRemainNumber(remainNumber)
                    .build();
        }));
    }
}
