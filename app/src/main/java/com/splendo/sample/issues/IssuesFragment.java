package com.splendo.sample.issues;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.splendo.sample.R;
import com.splendo.sample.data.Issue;
import com.splendo.sample.issuedetail.IssueDetailFragment;
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

    private View progressBar;

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
        // initializations
        this.adapter = new IssuesAdapter(emptyList, clickListener);
        this.presenter = new IssuesPresenter(this, getActivity().getSupportLoaderManager());
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
        progressBar = root.findViewById(R.id.progress);

        // load all issues into the view
        presenter.checkPermissions();

        return root;
    }

    @Override
    public Context getViewContext() {
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
    public void setProgressVisibility(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showIssueDetail(Issue issue) {
        showDetailFragment(issue);
    }

    @Override
    public void onPermissionGranted() {
        //this.presenter.loadIssues();
        this.presenter.startLoading();
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


    private void showDetailFragment(Issue issue) {
        IssueDetailFragment fragment = new IssueDetailFragment();
        Bundle args = new Bundle();
        args.putString("first", issue.firstName);
        args.putString("last", issue.lastName);
        args.putString("count", String.valueOf(issue.issueCount));
        args.putLong("date", issue.dateOfBirth.getTime());
        fragment.setArguments(args);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.add(fragment, "detail")
        transaction.add(R.id.contentFrame, fragment)
                .addToBackStack(null).commit();
    }

}
