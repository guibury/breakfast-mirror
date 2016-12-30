package com.jovensprofissionais.breakfastitismytreat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
//App icon made by Kitchenware from www.flaticon.com

public class MainActivity extends AppCompatActivity {

    Button voteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voteButton = (Button) findViewById(R.id.voteButton);

        voteButton.setOnClickListener(voteHandler);
    }

    View.OnClickListener voteHandler = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, R.string.realized_vote, Toast.LENGTH_SHORT).show();
        }
    };
}
