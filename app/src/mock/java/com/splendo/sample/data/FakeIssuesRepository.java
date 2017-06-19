package com.splendo.sample.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Ali on 18/06/2017.
 */

public class FakeIssuesRepository implements IssuesRepositoryContract {

    @Override
    public List<Issue> getIssues() {
        return getMockedIssues();
    }

    /**
     * Get a list of in-memory Mocked Issues.
     *
     * @return
     */
    private  List<Issue> getMockedIssues() {

        List<Issue> issues = new ArrayList();

        Issue issue = new Issue();
        issue.lastName = "Frederik";
        issue.firstName = "Andersen";
        issue.dateOfBirth = new GregorianCalendar(2001, 1, 1).getTime();
        issue.issueCount = 0;
        issues.add(issue);

        issue = new Issue();
        issue.lastName = "John";
        issue.firstName = "van Neuman";
        issue.dateOfBirth = new GregorianCalendar(1998, 2, 10).getTime();
        issue.issueCount = 1;
        issues.add(issue);

        issue = new Issue();
        issue.lastName = "Nikola";
        issue.firstName = "Tesla";
        issue.dateOfBirth = new GregorianCalendar(1976, 3, 21).getTime();
        issue.issueCount = 1;
        issues.add(issue);

        return issues;
    }
}
