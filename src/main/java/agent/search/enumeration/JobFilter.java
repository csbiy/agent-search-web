package agent.search.enumeration;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by P-161 at : 2023-11-06
 * <p>
 * 여기에 JobFilter 열거형에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
public enum JobFilter {

    FULL_STACK("웹 풀스택", List.of("웹 풀스택", "Web FullStack")),
    BACKEND("백엔드", List.of("백엔드", "Backend", "Server", "서버","Back-end")),
    DEVOPS("데브옵스", List.of("데브옵스", "Devops", "SRE", "Site Reliable Engineer")),
    SECURITY("보안", List.of("보안", "Security")),
    DATA_ENGINEER("데이터 엔지니어", List.of("데이터 엔지니어", "Data Engineer")),
    PUBLISHING("퍼블리싱", List.of("퍼블리싱", "Publishing", "마크업")),
    DBA("DBA", List.of("DBA")),
    FRONTEND("프론트", List.of("프론트", "Frontend")),
    IOS("IOS", List.of("IOS", "Flutter", "Swift")),
    ANDROID("ANDROID", List.of("ANDROID", "안드로이드")),
    AI("AI", List.of("인공지능", "AI", "ML", "머신러닝"));

    private final String label;

    private final List<String> keywords;

    public static List<String> getFilters() {
        return Arrays.stream(values()).map((value) -> value.label).toList();
    }


    public static List<String> getKeywordsOf(List<String> filters) {
        return Arrays.stream(values()).filter((value) -> filters.contains(value.label))
                .map((value) -> value.keywords)
                .flatMap(Collection::stream)
                .toList();
    }
}