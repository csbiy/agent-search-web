package agent.search.controller;

import agent.search.dto.RecruitmentDto;
import agent.search.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/recruitments")
@RequiredArgsConstructor
public class RecruitmentAPIController {

    private final RecruitmentService service;

    @GetMapping
    public Page<RecruitmentDto> getRecruitments(@RequestParam(value = "page") Integer pageNumber,
                                                @RequestParam(value = "pageSize") Integer pageSize
    ) {
        return service.findByPaging(pageNumber, pageSize);
    }


}
