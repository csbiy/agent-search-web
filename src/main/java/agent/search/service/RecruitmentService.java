package agent.search.service;

import agent.search.dto.RecruitmentDto;
import org.springframework.data.domain.Page;

public interface RecruitmentService {

    Page<RecruitmentDto> findByPaging(int page, int pageSize);
}
