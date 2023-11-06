package agent.search.enumeration;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by P-161 at : 2023-11-06
 *
 * 여기에 JobFilter 열거형에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
public enum JobFilter {

    FULL_STACK("웹 풀스택", List.of(
            "웹 풀스택",
            "Web FullStack"
    ));


    private final String label;

    private final List<String> keywords;


}