package agent.search.dto.response;


import agent.search.enumeration.StatisticDateType;
import agent.search.enumeration.StatisticProperty;

public record StatisticResponse(StatisticDateType dateType,
                                StatisticProperty propertyType,
                                Integer value) {

    public static StatisticResponse fromProperty(StatisticDateType dateType,
                                                 StatisticProperty property,
                                                 Integer value) {
        return new StatisticResponse(dateType, property, value);
    }

}