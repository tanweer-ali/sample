package com.splendo.sample.issues;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.splendo.sample.R;
import com.splendo.sample.data.Issue;
import com.splendo.sample.utils.Permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali on 15/06/2017.
 */

public class IssuesFragment extends Fragment implements IssuesContract.View {

    // Attributes :
    private IssuesContract.UserActionListener presenter;
    private IssuesAdapter adapter;
    private Permissions permissions;

    public static IssuesFragment newInstance() {
        return new IssuesFragment();
    }

    public IssuesFragment() {
        // empty public constructor
    }


    // clickListener for list-item clicks
    IssuesAdapter.IssueItemListener clickListener = new IssuesAdapter.IssueItemListener() {
        @Override
        public void onIssueClick(Issue clickedIssue) {
            presenter.openIssueDetail(clickedIssue);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Issue> emptyList = new ArrayList<Issue>(0);
        List<Issue> issues = new ArrayList<Issue>(0);

        // initializations
        this.adapter = new IssuesAdapter(emptyList, clickListener);
        this.presenter = new IssuesPresenter(this); // pass 'this' as reference to the view
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_issues, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.issues_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // load all issues into the view
        presenter.checkPermissions();

        return root;
    }

    @Override
    public Context getConext() {
        return getContext();
    }

    @Override
    public void requestUserPermissions() {
        permissions = new Permissions(this, this);
        permissions.checkPermissions();
    }

    @Override
    public void showIssues(List<Issue> issues) {

        adapter.setList(issues);
    }

    @Override
    public void setProgressVisibility(boolean visibility) {

    }

    @Override
    public void showIssueDetail(Issue issue) {

    }

    @Override
    public void onPermissionGranted() {
        this.presenter.loadIssues();

        //getActivity().getSupportLoaderManager().initLoader(R.id.loader_id, null, presenter);
    }

    @Override
    public void showPermissionRationale() {
        Toast.makeText(getContext(), "This app needs permission to read file from SDCard", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionDenied() {
        Toast.makeText(getContext(), "This app needs permission to read file from SDCard", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        this.permissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
