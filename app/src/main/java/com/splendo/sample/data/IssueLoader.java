package com.splendo.sample.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Ali on 18/06/2017.
 */

public class IssueLoader extends AsyncTaskLoader<List<Issue>> {

    IssuesRepositoryContract repository = Injection.provideIssuesRepository();
    List<Issue> cache = null;

    public IssueLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if(null == cache){
            forceLoad();
        } else{
            deliverResult(cache);
        }
    }

    @Override
    public List<Issue> loadInBackground() {
        return repository.getIssues();
    }

    @Override
    public void deliverResult(List<Issue> data) {
        cache = data;
        super.deliverResult(data);
    }
}
