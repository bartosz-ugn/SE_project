package com.sandipbhattacharya.registerlogindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FAQ extends AppCompatActivity {

    String  question1 = "fråga1",
            question2 = "fråga2",
            question3 = "fråga3",
            question4 = "fråga4",
            question5 = "fråga5",
            question6 = "fråga6";


    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> questionCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.elvMobiles);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, questionCollection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createCollection() {
        String[] answer1 = {"svar1"};
        String[] answer2 = {"svar2"};
        String[] answer3 = {"svar3"};
        String[] answer4 = {"svar4"};
        String[] answer5 = {"svar5"};
        String[] answer6 = { "svar6"};
        questionCollection = new HashMap<String, List<String>>();
        for(String group : groupList){
            if (group.equals(question1)){
                loadChild(answer1);
            } else if (group.equals(question2))
                loadChild(answer2);
            else if (group.equals(question3))
                loadChild(answer3);
            else if (group.equals(question4))
                loadChild(answer4);
            else if (group.equals(question5))
                loadChild(answer5);
            else if (group.equals(question6))
                loadChild(answer6);

            questionCollection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for(String model : mobileModels){
            childList.add(model);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add(question1);
        groupList.add(question2);
        groupList.add(question3);
        groupList.add(question4);
        groupList.add(question5);
        groupList.add(question6);
    }
}
