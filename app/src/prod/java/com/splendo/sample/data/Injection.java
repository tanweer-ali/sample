package com.splendo.sample.data;

/**
 * Created by Ali on 18/06/2017.
 */

public class Injection {

    public static IssuesRepositoryContract provideIssuesRepository() {
        return new IssuesRepository();
    }

}
