package com.splendo.sample.issues;

import com.splendo.sample.data.Issue;
import com.splendo.sample.data.IssuesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ali on 16/06/2017.
 */

public class IssuesPresenter implements IssuesContract.UserActionListener {

    IssuesContract.View view;
    IssuesRepository repository;

    public IssuesPresenter( IssuesContract.View view ){
        repository = new IssuesRepository();
        this.view = view;
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

        // load issues from the model on a separate thread
        repository
                .loadIssuesFromFile()
                .subscribeOn(Schedulers.io())               // worker thread
                .observeOn(AndroidSchedulers.mainThread())  // UI thread
                .subscribe( issues->{
                    // pass it on to the view to display
                    view.setProgressVisibility(false);
                    view.showIssues(issues);
                } );
    }

    @Override
    public void addNewIssue() {

    }

    @Override
    public void openIssueDetail(Issue clickedIssue) {

    }


}
