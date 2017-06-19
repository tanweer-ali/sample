package com.splendo.sample.issues;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.splendo.sample.R;
import com.splendo.sample.data.Issue;
import com.splendo.sample.data.IssueLoader;

import java.util.List;

/**
 * Created by Ali on 16/06/2017.
 */

public class IssuesPresenter implements IssuesContract.UserActionListener,
        LoaderManager.LoaderCallbacks<List<Issue>> {

    IssuesContract.View view;
    LoaderManager loaderMgr;

    public IssuesPresenter(IssuesContract.View view, LoaderManager loaderMgr) {
        this.view = view;
        this.loaderMgr = loaderMgr;
    }

    @Override
    public void startLoading() {
        // show the progress bar from the view
        view.setProgressVisibility(true);
        loaderMgr.initLoader(R.id.loader_id, null, this);
    }

    @Override
    public void checkPermissions() {
        // check permissions
        view.requestUserPermissions();
    }

    @Override
    public void loadIssues() {
        // show the progress bar from the view
        view.setProgressVisibility(true);
    }

    @Override
    public void addNewIssue() {

    }

    @Override
    public void openIssueDetail(Issue clickedIssue) {
        this.view.showIssueDetail(clickedIssue);
    }


    @Override
    public Loader<List<Issue>> onCreateLoader(int id, Bundle args) {
        return new IssueLoader(view.getViewContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
        // pass it on to the view to display
        view.setProgressVisibility(false);
        view.showIssues(issues);
    }

    @Override
    public void onLoaderReset(Loader<List<Issue>> loader) {

    }

}
