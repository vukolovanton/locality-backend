package com.backend.locality.utils;

import com.backend.locality.api.AbstractPatchRequest;
import com.backend.locality.api.issues.IssueStatuses;

public class CheckIsEnum {
    public static boolean checkIsIssueStatusesEnum(AbstractPatchRequest request)  {
        for (IssueStatuses statuses : IssueStatuses.values()) {
            if (statuses.name().equals(request.getValue())) {
                return true;
            }
        }
        return false;
    }
}
