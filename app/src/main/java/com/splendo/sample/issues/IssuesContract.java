package com.splendo.sample.issues;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.splendo.sample.data.Issue;
import com.splendo.sample.utils.Permissions;

import java.util.List;

/**
 * Created by Ali on 15/06/2017.
 */

public interface IssuesContract {

    interface View extends Permissions.Callback{

        void showIssues(List<Issue> issues);

        void setProgressVisibility(boolean visibility);

        void showIssueDetail(Issue issue);

        void requestUserPermissions();

        Context getConext();
    }

    interface UserActionListener  {

        void loadIssues();

        void addNewIssue();

        void openIssueDetail(Issue clickedIssue);

        void checkPermissions();

        void startLoading();
    }
}
