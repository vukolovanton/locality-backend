package com.backend.locality.api.dashboard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public HashMap<String, Long> issuesStatistics() {
        return dashboardRepository.issuesStatistics();
    }

    public HashMap<String, Long> announcementsStatistics() {
        return dashboardRepository.announcementsStatistic();
    }

    public HashMap<String, Long> usersStatistics() {
        return dashboardRepository.usersStatistic();
    }
}
