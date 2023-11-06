package agent.search.controller;

import agent.search.dto.response.RecruitmentResponse;
import agent.search.dto.request.RecruitmentSearchRequest;
import agent.search.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/recruitments")
@RequiredArgsConstructor
public class RecruitmentAPIController {

    private final RecruitmentService service;

    @GetMapping
    public Page<RecruitmentResponse> getRecruitments(@RequestParam(value = "page") Integer pageNumber,
                                                     @RequestParam(value = "pageSize") Integer pageSize
    ) {
        return service.findByPaging(pageNumber, pageSize);
    }

    @PostMapping
    public Page<RecruitmentResponse> searchRecruitments(@RequestBody RecruitmentSearchRequest request
    ) {
        return service.findBySearch(request);
    }

}
