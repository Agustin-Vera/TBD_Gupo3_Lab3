package com.example.TBDBackend.services;

import com.example.TBDBackend.entities.Log;
import com.example.TBDBackend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> getAllLogs() {
        return  logRepository.findAll();
    }
}
