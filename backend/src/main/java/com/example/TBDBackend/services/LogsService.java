package com.example.TBDBackend.services;

import com.example.TBDBackend.entities.LogEntity;
import com.example.TBDBackend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {
    @Autowired
    private LogRepository logRepository;

    public List<LogEntity> getAllLogs() {
        return  logRepository.findAll();
    }
}
