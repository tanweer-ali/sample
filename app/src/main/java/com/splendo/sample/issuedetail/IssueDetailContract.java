package com.splendo.sample.issuedetail;

import android.content.Context;

import com.splendo.sample.data.Issue;
import com.splendo.sample.utils.Permissions;

import java.util.List;

/**
 * Created by Ali on 18/06/2017.
 */

public interface IssueDetailContract {

    interface View {

    }

    interface UserActionListener  {

        void navigateBack();
    }
}
