package com.example.proiect.service;

import com.example.proiect.model.Bug;
import com.example.proiect.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TesterService {
    @Autowired
    private BugRepository bugRepository;

    public List<Bug> getBugs() {
        return bugRepository.findAll();
    }

    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }
}

