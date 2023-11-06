package agent.search.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RecruitmentResponse {

    private String companyName;

    private String wantedOriginLink;

    private String companyLogoPath;

    private String jobPosition;

    private Integer createYear;

    private String companyLocation;

    /**
     * 현역 복무 인원 TO 개수
     */
    private Integer activeRemainNumber;

    private String jobPlanetScore;

    private String jobPlanetOriginLink;

}
