package com.jovensprofissionais.breakfastitismytreat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jovensprofissionais.breakfastitismytreat.constant.Constant;
import com.jovensprofissionais.breakfastitismytreat.R;
import com.jovensprofissionais.breakfastitismytreat.controller.RankingDBController;

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 02/01/17
 */
public class PersonFragment extends Fragment implements OnClickListener {

    public PersonFragment() {}

    Button voteButton;
    RankingDBController rankingDBController;
    TextView personOfTheWeek;
    RatingBar ratingBar;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_person, container, false);

        rankingDBController = new RankingDBController(getActivity().getBaseContext());
        voteButton = (Button) rootView.findViewById(R.id.voteButton);
        voteButton.setOnClickListener(this);
        personOfTheWeek = (TextView) rootView.findViewById(R.id.personOfTheWeek);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(ratingBar.getProgress() > 0) {
            rankingDBController.insert(personOfTheWeek.getText().toString(),ratingBar.getProgress());

            databaseReference.child(Constant.RANKING_TABLE).setValue(ratingBar.getProgress());

            Toast.makeText(getActivity(), R.string.realized_vote, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.not_realized_vote, Toast.LENGTH_LONG).show();
        }
    }
}