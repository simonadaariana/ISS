package com.example.proiect.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BugReportRequest {
    private String testerName;
    private String bugName;
    private String description;
    private Boolean solved;
}
