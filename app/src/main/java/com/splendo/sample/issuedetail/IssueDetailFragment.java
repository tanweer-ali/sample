package com.splendo.sample.issuedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.splendo.sample.R;
import com.splendo.sample.data.Issue;
import com.splendo.sample.utils.DateParser;

import java.util.Date;

/**
 * Created by Ali on 18/06/2017.
 */

public class IssueDetailFragment extends Fragment implements IssueDetailContract.View {

    // Attributes :
    private IssueDetailContract.UserActionListener presenter;

    private Issue issue;

    private TextView name;
    private TextView count;
    private CalendarView calendarView;

    public IssueDetailFragment(){
    }

    @Override
    public void showDetail(Issue issue) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new IssueDetailPresenter(this);

        Bundle args = getArguments();
        issue = new Issue();
        issue.firstName = (String) args.get("first");
        issue.lastName = (String) args.get("last");
        issue.issueCount = Integer.parseInt((String) args.get("count"));
        Long time = args.getLong("date");
        issue.dateOfBirth = new Date();
        issue.dateOfBirth.setTime(time);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        name = (TextView) root.findViewById(R.id.name);
        count = (TextView) root.findViewById(R.id.count);
        calendarView = (CalendarView) root.findViewById(R.id.calendar);

        name.setText(issue.firstName + " " + issue.lastName);
        count.setText(""+issue.issueCount);
        calendarView.setDate(issue.dateOfBirth.getTime());

        return root;
    }
}
