package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class managerActivity extends AppCompatActivity {

    Button viewUsers;
    Button editUsers;
    Button viewSurveys;
    Button splitTips;
    Button viewComps;
    Button editMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);


        viewUsers = findViewById(R.id.bViewUsers);
        editUsers = findViewById(R.id.bEditUsers);
        viewSurveys = findViewById(R.id.bViewSurveys);
        splitTips = findViewById(R.id.bSplitTips);
        viewComps = findViewById(R.id.bViewComps);
        editMenu = findViewById(R.id.bEditMenu);


        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewUserIntent = new Intent(managerActivity.this, DisplayUsers.class);
                startActivity(viewUserIntent);
            }
        });


        editUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editUserIntent = new Intent(managerActivity.this, EditUser.class);
                startActivity(editUserIntent);

            }
        });

        viewSurveys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSurveysIntent = new Intent(managerActivity.this, DisplaySurveys.class);
                startActivity(viewSurveysIntent);
            }
        });

        splitTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splitTipsIntent = new Intent(managerActivity.this, SplitTipsActivity.class);
                startActivity(splitTipsIntent);
            }
        });

        viewComps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCompsIntent = new Intent(managerActivity.this, ViewCompsActivity.class);
                startActivity(viewCompsIntent);
            }
        });

        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editmenuIntent = new Intent(managerActivity.this, UpdateMenu.class);
                startActivity(editmenuIntent);

            }
        });

    }
}
