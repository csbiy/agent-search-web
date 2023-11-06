package agent.search.service;

import agent.search.dto.request.RecruitmentSearchRequest;
import agent.search.dto.response.RecruitmentResponse;
import org.springframework.data.domain.Page;

public interface RecruitmentService {

    Page<RecruitmentResponse> findByPaging(int page, int pageSize);

    Page<RecruitmentResponse> findBySearch(RecruitmentSearchRequest request);
}
