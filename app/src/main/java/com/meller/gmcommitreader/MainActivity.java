package com.meller.gmcommitreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button refreshButton;
    ListView commitListView;
    String [] commitItemsArray = { "Test1", "Test2" };
    String [] commitSubItemsArray = { "SubTest1", "SubTest2" };
    List<CommitItem> commitItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);

        commitItems = new ArrayList<CommitItem>();

        commitItems.add(new CommitItem("Hash", "Author", "Message"));
        commitItems.add(new CommitItem("Hash2", "Author2", "Message2"));

        CommitItemsAdapter adapter = new CommitItemsAdapter(this,
                R.layout.activity_listview, commitItems);

        commitListView = (ListView) findViewById(R.id.commitListView);

        commitListView.setAdapter(adapter);
        refreshRepository();
    }

    private boolean connectToRepository()
    {

        return true;
    }


    @Override
    public void onClick(View view) {
        refreshRepository();
    }

    private void refreshRepository() {

        showToast("Repository Refreshed");
    }

    private void showToast(String toastMessage) {
        Context context = getApplicationContext();
        CharSequence text = toastMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}