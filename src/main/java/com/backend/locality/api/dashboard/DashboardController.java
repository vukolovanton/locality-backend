package com.backend.locality.api.dashboard;

import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsRequest;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse;
import com.backend.locality.api.dashboard.interfaces.IndexDashboardRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET, value = "/issues")
    public HashMap<String, Long> getIssuesStats(@ModelAttribute IndexDashboardRequest request) {
        return dashboardService.issuesStatistics();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/announcements")
    public HashMap<String, Long> getAnnouncementsStats(@ModelAttribute IndexDashboardRequest request) {
        return dashboardService.announcementsStatistics();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public HashMap<String, Long> getUsersStats(@ModelAttribute IndexDashboardRequest request) {
        return dashboardService.usersStatistics();
    }

}
