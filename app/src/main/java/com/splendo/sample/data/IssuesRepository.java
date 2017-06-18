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
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ali on 16/06/2017.
 */

public class IssuesRepository {

    private static List<Issue> issues = null;

    /**
     * Get a list of in-memory Mocked Issues.
     *
     * @return
     */
    public Observable<List<Issue>> getMockedIssues() {

        issues = new ArrayList();

        Issue issue = new Issue();
        issue.lastName = "Ali";
        issue.firstName = "Tanweer";
        issue.dateOfBirth = new GregorianCalendar(2017, 1, 1).getTime();
        issue.issueCount = 0;
        issues.add(issue);

        issue = new Issue();
        issue.lastName = "Johnny";
        issue.firstName = "Bravo";
        issue.dateOfBirth = new GregorianCalendar(2017, 2, 1).getTime();
        issue.issueCount = 1;
        issues.add(issue);

        issue = new Issue();
        issue.lastName = "Bugs";
        issue.firstName = "Bunny";
        issue.dateOfBirth = new GregorianCalendar(2017, 3, 1).getTime();
        issue.issueCount = 1;
        issues.add(issue);

        // return the issues as observable
        return Observable.defer(() -> {
            blockThread();
            return Observable.just(issues);
        });
    }

    /**
     * Read the list of issues from a file.
     *
     * @return
     */
    public Observable<List<Issue>> loadIssuesFromFile() {
        // return the issues as observable
        return Observable.defer(() -> {
            if(issues == null){
                 blockThread();
                readFromFile();
            } else{
                // just return the existing ones
            }

            return Observable.just(issues);
        });
    }

    private void readFromFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "file.txt");
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

        this.issues = issues;
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
    private void blockThread() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
