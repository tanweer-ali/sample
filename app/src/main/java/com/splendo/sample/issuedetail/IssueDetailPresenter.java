package com.splendo.sample.issuedetail;

/**
 * Created by Ali on 18/06/2017.
 */

public class IssueDetailPresenter implements IssueDetailContract.UserActionListener {

    IssueDetailContract.View view;

    public IssueDetailPresenter(IssueDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void navigateBack() {
        // change the fragment backstack or something
    }


}
