package com.splendo.sample.data;

import com.splendo.sample.data.IssuesRepository;
import com.splendo.sample.data.IssuesRepositoryContract;

/**
 * Created by Ali on 18/06/2017.
 */

public class Injection {

    IssuesRepositoryContract repository;

    public static IssuesRepositoryContract provideIssuesRepository(){
        return new IssuesRepository();
    }

}
