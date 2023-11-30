package agent.search.dto.response;

import lombok.Builder;

public record RecruitmentResponse(
        String companyName,
        String wantedOriginLink,
        String companyLogoPath,
        String jobPosition,
        Integer createYear,
        String companyLocation,
        Integer activeRemainNumber,
        String jobPlanetScore,
        String jobPlanetOriginLink
) {
    @Builder
    public RecruitmentResponse {
    }

}
