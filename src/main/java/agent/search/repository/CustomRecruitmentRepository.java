package agent.search.repository;

import agent.search.dto.response.RecruitmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomRecruitmentRepository {

    Page<RecruitmentResponse> searchByCondition(List<String> keywords, String searchTerm, PageRequest request);
}
