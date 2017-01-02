package com.jovensprofissionais.breakfastitismytreat;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.jovensprofissionais.breakfastitismytreat.controller.RankingDBController;
import com.jovensprofissionais.breakfastitismytreat.fragments.PersonFragment;
//App icon made by Kitchenware from www.flaticon.com

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 30/12/16
 */

public class MainActivity extends AppCompatActivity {

    Button voteButton;
    RankingDBController rankingDBController;
    TextView personOfTheWeek;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new PersonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_person, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

     //   rankingDBController = new RankingDBController(getBaseContext());
     //   voteButton = (Button) findViewById(R.id.voteButton);
     //   personOfTheWeek = (TextView) findViewById(R.id.personOfTheWeek);
     //   ratingBar = (RatingBar) findViewById(R.id.ratingBar);

     //   voteButton.setOnClickListener(voteHandler);
    }

//    View.OnClickListener voteHandler = new OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if(ratingBar.getProgress() > 0) {
//                rankingDBController.insert(personOfTheWeek.getText().toString(),ratingBar.getProgress());
//                Toast.makeText(MainActivity.this, R.string.realized_vote, Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this, R.string.not_realized_vote, Toast.LENGTH_LONG).show();
//            }
//
//        }
//    };
}