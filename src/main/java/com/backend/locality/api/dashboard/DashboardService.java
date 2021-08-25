package com.backend.locality.api.dashboard;

import com.backend.locality.api.dashboard.interfaces.IndexDashboardRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public HashMap<String, Long> issuesStatistics(IndexDashboardRequest request) {
        return dashboardRepository.issuesStatistics(request);
    }

    public HashMap<String, Long> announcementsStatistics(IndexDashboardRequest request) {
        return dashboardRepository.announcementsStatistic(request);
    }

    public HashMap<String, Long> usersStatistics(IndexDashboardRequest request) {
        return dashboardRepository.usersStatistic(request);
    }
}
