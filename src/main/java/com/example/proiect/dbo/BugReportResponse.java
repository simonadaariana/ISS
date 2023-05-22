package com.example.proiect.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BugReportResponse {
    private int id;
    private String bugName;
    private String msg;
    private Boolean err;
}
