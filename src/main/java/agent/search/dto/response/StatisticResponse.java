package agent.search.dto.response;


import agent.search.enumeration.StatisticTarget;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatisticResponse {

    private final StatisticTarget target;

    private final Integer value;
}