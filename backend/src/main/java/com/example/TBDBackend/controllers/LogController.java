package com.example.TBDBackend.controllers;

import com.example.TBDBackend.models.Log;
import com.example.TBDBackend.services.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
public class LogController {
    @Autowired
    private LogsService logsService;

    @GetMapping
    public List<Log> getAllLogs() {
        return logsService.getAllLogs();
    }
}
