package agent.search.service;

import java.util.List;

public interface FilterService {

    List<String> getFilters();

    List<String> getKeywords(List<String> filters);
}
