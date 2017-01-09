package com.jovensprofissionais.breakfastitismytreat.fragment;

import android.media.MediaPlayer;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jovensprofissionais.breakfastitismytreat.R;
import com.jovensprofissionais.breakfastitismytreat.constant.Constant;
import com.jovensprofissionais.breakfastitismytreat.controller.RankingDBController;

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 02/01/17
 */
public class PersonFragment extends Fragment implements OnClickListener {

    private Button voteButton;
    private RankingDBController rankingDBController;
    private TextView personOfTheWeek;
    private RatingBar ratingBar;
    private DatabaseReference databasePeople;
    private DatabaseReference databasePersonOfTheWeek;
    private DatabaseReference databaseWeekOfYear;

    MediaPlayer ratingBarOneStarSound;
    MediaPlayer ratingBarTwoStarSound;
    MediaPlayer ratingBarThreeStarSound;
    MediaPlayer ratingBarFourStarSound;
    MediaPlayer ratingBarFiveStarSound;

    int p = -1;

    public PersonFragment() {

    }

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
        ratingBarOneStarSound = MediaPlayer.create(getActivity(), R.raw.nosedive_1_star);
        ratingBarTwoStarSound = MediaPlayer.create(getActivity(), R.raw.nosedive_2_stars);
        ratingBarThreeStarSound = MediaPlayer.create(getActivity(), R.raw.nosedive_3_stars);
        ratingBarFourStarSound = MediaPlayer.create(getActivity(), R.raw.nosedive_4_stars);
        ratingBarFiveStarSound = MediaPlayer.create(getActivity(), R.raw.nosedive_5_stars);

        databasePeople = FirebaseDatabase.getInstance().getReference();
        databasePersonOfTheWeek = FirebaseDatabase.getInstance().getReference();
        databaseWeekOfYear = FirebaseDatabase.getInstance().getReference();

        databasePeople.child(Constant.PEOPLE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String i = dataSnapshot.getRef().child(Constant.PERSON_OF_THE_WEEK).getKey();
                Toast.makeText(getActivity(), "Updated "+i + dataSnapshot.child(Constant.NAME).getValue()
                        + " " + dataSnapshot.child(Constant.RATE).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.toException().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        databasePersonOfTheWeek.child(Constant.PERSON_OF_THE_WEEK).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getActivity(), "Person of the week:" + dataSnapshot.child(
                        Constant.PERSON_OF_THE_WEEK).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getActivity(), "Person of the week: " + dataSnapshot.child(
                        Constant.PERSON_OF_THE_WEEK).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // With week of the year is set the person of the week  (person_week)
        databaseWeekOfYear.child(Constant.WEEK_OF_THE_YEAR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child(Constant.PERSON_OF_THE_WEEK).setValue(dataSnapshot.getValue());
                Toast.makeText(getActivity(), "->"+ dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databasePersonOfTheWeek.child(Constant.PERSON_OF_THE_WEEK).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personOfTheWeek.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }


    @Override
    public void onClick(View v) {
        if (ratingBar.getProgress() > 0) {
            p = ((p+1) %12);
            databasePeople.child(Constant.PEOPLE).child(Integer.toString(p)).child(Constant.RATE).setValue(ratingBar.getProgress());
            databasePersonOfTheWeek.child(Constant.PERSON_OF_THE_WEEK).setValue(p);

            if(ratingBar.getProgress() == 1) {
                ratingBarOneStarSound.start();
            } else if (ratingBar.getProgress() == 2) {
                ratingBarTwoStarSound.start();
            } else if (ratingBar.getProgress() == 3) {
                ratingBarThreeStarSound.start();
            } else if (ratingBar.getProgress() == 4) {
                ratingBarFourStarSound.start();
            } else if (ratingBar.getProgress() == 5) {
                ratingBarFiveStarSound.start();
            }

            Toast.makeText(getActivity(), R.string.realized_vote, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.not_realized_vote, Toast.LENGTH_LONG).show();
        }
    }
}