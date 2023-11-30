package agent.search.dto.request;

import lombok.Builder;

import java.util.List;

public record RecruitmentSearchRequest(
        String searchTerm,
        List<String> filters,
        Integer page,
        Integer pageSize
) {
}