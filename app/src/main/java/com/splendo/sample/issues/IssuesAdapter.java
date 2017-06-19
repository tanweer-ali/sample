package com.splendo.sample.issues;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.splendo.sample.R;
import com.splendo.sample.data.Issue;

import java.util.List;


/**
 * Created by Ali on 15/06/2017.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private List<Issue> issues;
    private IssueItemListener itemListener;

    IssuesAdapter(List<Issue> issues, IssueItemListener itemListener){
        this.issues = issues;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_issue, parent, false);
        return new ViewHolder(view, itemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Issue issue = this.issues.get(position);

        holder.name.setText(issue.firstName + " "+ issue.lastName);
     }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public Issue getItem(int pos){
        return issues.get(pos);
    }

    public void setList( List<Issue> issues){
        this.issues = issues;
        notifyDataSetChanged();
    }
    /**
     *  View Holder for this adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        public IssueItemListener listener;

        public ViewHolder(View itemView, IssueItemListener listener){
            super(itemView);
            this.listener = listener;
            this.name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Issue issue = getItem(pos);
            this.listener.onIssueClick(issue);
        }
    }

    /**
     *  Interface to handle item click event.
     */
    public interface IssueItemListener{
        void onIssueClick(Issue clickedIssue);
    }

}
