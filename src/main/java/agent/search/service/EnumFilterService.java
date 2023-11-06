package agent.search.service;

import agent.search.enumeration.JobFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnumFilterService implements FilterService {
    @Override
    public List<String> getFilters() {
        return JobFilter.getFilters();
    }

    @Override
    public List<String> getKeywords(List<String> filters) {
        return JobFilter.getKeywordsOf(filters);
    }
}
