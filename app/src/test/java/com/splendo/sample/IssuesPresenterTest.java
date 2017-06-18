package com.splendo.sample;

import android.support.v4.app.LoaderManager;

import com.splendo.sample.issues.IssuesContract;
import com.splendo.sample.issues.IssuesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

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
    void setupIssuesPresenter(){
        // this needs to be called for @Mock annotations to work
        MockitoAnnotations.initMocks(this);

        this.presenter = new IssuesPresenter(view, loader);
    }


    @Test
    public void requestPermissions() {0
        assertEquals(4, 2 + 2);
    }
}
