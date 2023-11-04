package agent.search.controller;

import agent.search.dto.RecruitmentDto;
import agent.search.service.RecruitmentService;
import agent.search.service.WantedRecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {


    @GetMapping
    public String homePage(){
        return "index";
    }

}
