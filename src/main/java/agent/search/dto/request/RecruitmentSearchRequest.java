package agent.search.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecruitmentSearchRequest {

    private String searchTerm;

    private List<String> filters;

    private Integer page;

    private Integer pageSize;
}