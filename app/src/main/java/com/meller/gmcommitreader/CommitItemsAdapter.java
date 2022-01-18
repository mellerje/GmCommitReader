package com.meller.gmcommitreader;

import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommitItemsAdapter extends ArrayAdapter<CommitItem> {

    public CommitItemsAdapter(CommitListActivity context, int activity_listview, List<CommitItem> commitItems) {
        super(context, activity_listview, commitItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommitItem commitItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.commit_item, parent, false);
        }

        if (commitItem.commitNumber % 2 == 1) {
            convertView.setBackgroundColor(checkForNightMode() ? Color.DKGRAY : Color.LTGRAY);
        }
        else {
            convertView.setBackgroundColor(checkForNightMode() ? Color.BLACK : Color.WHITE);
        }

        // Lookup view for data population
        TextView tvHash = convertView.findViewById(R.id.tvHash);
        TextView tvAuthor = convertView.findViewById(R.id.tvAuthor);
        TextView tvMessage = convertView.findViewById(R.id.tvMessage);

        // Populate the data into the template view using the data object
        tvHash.setText(commitItem.commitHash);
        tvAuthor.setText(commitItem.author);
        tvMessage.setText(commitItem.commitMessage);

        // Return the completed view to render on screen
        return convertView;
    }

    private boolean checkForNightMode() {
        int nightModeFlags = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                return true;

            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return false;
        }

        return false;
    }


}