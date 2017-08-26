package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value="results")
    public String searchByColumn(Model model,@RequestParam String searchType, @RequestParam String searchTerm){

        ArrayList<HashMap<String,String>> jobs=JobData.findAll();
        ArrayList<HashMap<String,String>> jobs_found= new ArrayList<>();

        searchTerm = searchTerm.toLowerCase();

        if (searchType.equals("all")){
            for (HashMap<String,String> row: jobs){
                for (String column : row.keySet()){
                    String Value=row.get(column);
                    Value = Value.toLowerCase();

                    if (Value.contains(searchTerm) && !jobs_found.contains(row)){
                        jobs_found.add(row);
                    }
                }
            }
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("displayJobs",jobs_found);
            return "search";
        }
        else {
            for (HashMap<String,String> row: jobs){
                String value = row.get(searchType);
                value= value.toLowerCase();
                if (value.contains(searchTerm)) {
                    jobs_found.add(row);
                }
            }


            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("displayJobs", jobs_found);
            return "search";

            }
        }

    }


