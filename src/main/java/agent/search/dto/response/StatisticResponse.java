package agent.search.dto.response;


import agent.search.enumeration.StatisticTarget;

public record StatisticResponse(StatisticTarget target,
                                Integer value) {

}