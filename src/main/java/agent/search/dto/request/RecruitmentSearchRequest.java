package agent.search.dto.request;

import java.util.List;

public record RecruitmentSearchRequest(
        String searchTerm,
        List<String> filters,
        Integer page,
        Integer pageSize
) {
}