package agent.search.controller;

import agent.search.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FilterService filterService;

    @GetMapping
    public String homePage(Model model) {
        List<String> filters = filterService.getFilters();
        model.addAttribute("filters",filters);
        return "index";
    }

}
