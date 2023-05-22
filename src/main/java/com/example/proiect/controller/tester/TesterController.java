package com.example.proiect.controller.tester;

import com.example.proiect.dbo.BugReportRequest;
import com.example.proiect.dbo.BugReportResponse;
import com.example.proiect.model.Bug;
import com.example.proiect.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/homepage/tester")
public class TesterController {
    @Autowired
    private TesterService testerService;

    @GetMapping("")
    public List<Bug> index() {
        return testerService.getBugs();
    }

    @PostMapping("/post")
    public BugReportResponse addBug(@RequestBody BugReportRequest bugReportRequest) {
        Bug bug = new Bug(bugReportRequest.getTesterName(),
                bugReportRequest.getBugName(),
                bugReportRequest.getDescription(),
                bugReportRequest.getSolved());
        Bug bug1 = testerService.addBug(bug);

        if (bug1 != null) {
            return new BugReportResponse(bug1.getId(), bug1.getBugName(), "Bug added successfully", true);
        } else
            return new BugReportResponse(0, bug.getBugName(), "Couldn't add", false);
    }
}
