package com.splendo.sample.data;

import android.os.Environment;
import android.widget.Toast;

import com.splendo.sample.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ali on 16/06/2017.
 */

public class IssuesRepository implements IssuesRepositoryContract {

    private List<Issue> issues = null;
    private final String filename = "file.txt";

    public List<Issue> getIssues() {
        return getIssuesFromFile();
    }

    private List<Issue> getIssuesFromFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, filename);
        List<Issue> issues = new ArrayList(0);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {

                    String[] parts = line.split(",");
                    Issue issue = new Issue();
                    issue.firstName = parts[0].split("\"")[1];
                    issue.lastName = parts[1].split("\"")[1];
                    issue.issueCount = Integer.parseInt(parts[2]);
                    issue.dateOfBirth = parseDate(parts[3].split("\"")[1]);
                    issues.add(issue);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(App.getContext(), "This app needs permission to read file from SDCard", Toast.LENGTH_SHORT).show();
            }
        }

        return issues;
    }

    /**
     * Parse the date from a string and return a Date object.
     *
     * @param part
     * @return
     */
    private Date parseDate(String part) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return format.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * a helper method to block the current thread to simulate network delay
     */
    public void blockThread() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read the list of issues from a file as Observables.
     *
     * @return
     */
//    public Observable<List<Issue>> loadIssuesFromFile() {
//        // return the issues as observable
//        return Observable.defer(() -> {
//            if (issues == null) {
//                blockThread();
//                this.issues = getIssuesFromFile();
//            } else {
//                // just return the existing ones
//            }
//
//            return Observable.just(issues);
//        });
//    }


}
