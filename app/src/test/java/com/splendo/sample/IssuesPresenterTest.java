package com.splendo.sample;

import android.support.v4.app.LoaderManager;

import com.splendo.sample.data.Issue;
import com.splendo.sample.issues.IssuesContract;
import com.splendo.sample.issues.IssuesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Ali on 18/06/2017.
 */

public class IssuesPresenterTest {

    @Mock
    private LoaderManager loader;

    @Mock
    private IssuesContract.View view;

    private IssuesPresenter presenter;

    @Before
    public void setupIssuesPresenter() {
        MockitoAnnotations.initMocks(this);
        this.presenter = new IssuesPresenter(view, loader);
    }


    @Test
    public void testPermissionsRequests() {
        this.presenter.checkPermissions();
        verify(view).requestUserPermissions();
    }

    @Test
    public void testOpenIssueDetail() {
        Issue issue = mock(Issue.class);
        this.presenter.openIssueDetail(issue);
        verify(view).showIssueDetail(issue);
    }
}
