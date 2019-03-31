package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {


        if (searchType.equals("all")) {

            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            int numResult = jobs.size();
            String results = numResult + " Result(s)";
            model.addAttribute("results", results).addAttribute("jobs", jobs);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            int numResult = jobs.size();
            String results = numResult + " Result(s)";
            model.addAttribute("results", results);
            if (!searchTerm.equals("")) {
                model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values containing keyword(s) " + searchTerm);
            } else {
                model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");
            }
            model.addAttribute("jobs", jobs);
            return "search";
        }
    }
}


