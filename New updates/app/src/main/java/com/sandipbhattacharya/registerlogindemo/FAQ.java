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

    String  question1 = "1. Hur loggar man in i appen?",
            question2 = "2. Hur gör man för att hitta en butik?",
            question3 = "3. Finns det alternativ för svenska och engelska?",
            question4 = "4. Vilka butiker jämför ni erbjudande på";


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
        String[] answer1 = {"Man trycker på sign up och lägger till sina uppgifter. Med användarnamnet och lösenordet kommer du in på appen."};
        String[] answer2 = {" När man loggat in i appen så går man till \"hitta butiker\" och då får man upp två alternativ. Antigen hitta butiker utan GPS eller med GPS. Väljer man med gps så måste man därefter godkänna att ens enhetsplats på telefonen aktiveras och så kan man se alls butiker med dess avstånd till dem från dig. \n" +
                "Eller så väljer man utan gps så kan du se vilka butiker som finns."};
        String[] answer3 = {"Appen supportar för tillfälligt enbart svenska och engelska"};
        String[] answer4 = {"Vi jämför priser på Coop, willys, lidl och ica"};
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
