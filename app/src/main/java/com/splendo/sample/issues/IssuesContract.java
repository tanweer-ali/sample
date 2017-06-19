package com.splendo.sample.issues;

import android.content.Context;

import com.splendo.sample.data.Issue;
import com.splendo.sample.utils.Permissions;

import java.util.List;

/**
 * Created by Ali on 15/06/2017.
 */

public interface IssuesContract {

    interface View extends Permissions.Callback {

        void showIssues(List<Issue> issues);

        void setProgressVisibility(boolean visibility);

        void showIssueDetail(Issue issue);

        void requestUserPermissions();

        Context getViewContext();
    }

    interface UserActionListener {

        void startLoading();

        void checkPermissions();

        void openIssueDetail(Issue clickedIssue);

        void loadIssues();

        void addNewIssue();
    }
}
