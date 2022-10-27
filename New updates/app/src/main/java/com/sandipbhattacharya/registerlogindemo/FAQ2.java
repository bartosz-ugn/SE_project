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

public class FAQ2 extends AppCompatActivity {

    String  question1 = "1. How to log in?",
            question2 = "2. How to find a store?",
            question3 = "3. Are there alternatives for Swedish and English?",
            question4 = "4. Which stores do you compare offers at";


    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.elvMobiles);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, mobileCollection);
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
        String[] answer1 = {"You press sign in and add your details. With the username and password you enter the app."};
        String[] answer2 = {"When you have logged into the app, you go to \\\"find stores\\\" and then you get two options. Antigen find stores without GPS or with GPS. If you choose with GPS, you must then approve that your device location on the phone is activated and then you can see all stores with their distance to them from you Or you choose without GPS and you can see which stores are available."};
        String[] answer3 = {"The app temporarily only supports Swedish and English"};
        String[] answer4 = {"We compare prices at Coop, Willys, Lidl and ica"};
        String[] answer5 = {"svar5"};
        String[] answer6 = { "svar6"};
        mobileCollection = new HashMap<String, List<String>>();
        for(String group : groupList){
            if (group.equals(question1)){
                loadChild(answer1);
            } else if (group.equals(question2))
                loadChild(answer2);
            else if (group.equals(question3))
                loadChild(answer3);
            else if (group.equals(question4))
                loadChild(answer4);


            mobileCollection.put(group, childList);
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

    }
}
