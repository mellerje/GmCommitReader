package com.meller.gmcommitreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CommitItemsAdapter extends ArrayAdapter<CommitItem> {

    public CommitItemsAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull CommitItem[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CommitItemsAdapter(MainActivity context, int activity_listview, List<CommitItem> commitItems) {
        super(context, activity_listview, commitItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CommitItem commitItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.commit_item, parent, false);
        }
        // Lookup view for data population
        TextView tvHash = (TextView) convertView.findViewById(R.id.tvHash);
        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);

        // Populate the data into the template view using the data object
        tvHash.setText(commitItem.commitHash);
        tvAuthor.setText(commitItem.author);
        tvMessage.setText(commitItem.commitMessage);

        // Return the completed view to render on screen
        return convertView;
    }
}