package com.splendo.sample.data;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ali on 18/06/2017.
 */

public class IssueLoader extends AsyncTaskLoader<List<Issue>> {

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
        return loadIssuesFromFile();
    }

    @Override
    public void deliverResult(List<Issue> data) {
        cache = data;
        super.deliverResult(data);
    }

    public List<Issue> loadIssuesFromFile() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Issue> issues = new ArrayList(0);
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "file.txt");

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
            }
        }

        return issues;
    }

    private Date parseDate(String part) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return format.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
