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

    Button voteButton;
    RankingDBController rankingDBController;
    TextView personOfTheWeek;
    RatingBar ratingBar;
    private DatabaseReference databaseReference;
    int weekOfTheYear;

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
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(Constant.RANKING).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child(Constant.RANKING).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getActivity(), "New: " + dataSnapshot.child(Constant.NAME).getValue()
                        + dataSnapshot.child(Constant.RATE).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    Toast.makeText(getActivity(), "Updated" + dataSnapshot.child(Constant.NAME).getValue()
                            + dataSnapshot.child(Constant.RATE).getValue(), Toast.LENGTH_SHORT).show();
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

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (ratingBar.getProgress() > 0) {

//           UserRating userRating = new UserRating(databaseReference.child(Constant.RANKING).push().getKey()
//                    ,personOfTheWeek.getText().toString(), ratingBar.getProgress());
//            userRating.incTotalTimesVoted();
//            databaseReference.child(Constant.RANKING).child(userRating.getId()).setValue(userRating);

            Toast.makeText(getActivity(), R.string.realized_vote, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.not_realized_vote, Toast.LENGTH_LONG).show();
        }
    }

    public void changePersonText() {
        switch (Integer.parseInt(personOfTheWeek.getText().toString())) {
            case 0:
                personOfTheWeek.setText(R.string.ernesto);
                break;
            case 1:
                personOfTheWeek.setText(R.string.gabriel);
                break;
            case 2:
                personOfTheWeek.setText(R.string.guilherme);
                break;
            case 3:
                personOfTheWeek.setText(R.string.gustavo);
                break;
            case 4:
                personOfTheWeek.setText(R.string.hivison);
                break;
            case 5:
                personOfTheWeek.setText(R.string.leonardo);
                break;
            case 6:
                personOfTheWeek.setText(R.string.mateus);
                break;
            case 7:
                personOfTheWeek.setText(R.string.matheus);
                break;
            case 8:
                personOfTheWeek.setText(R.string.tassia);
                break;
            case 9:
                personOfTheWeek.setText(R.string.thales);
                break;
            case 10:
                personOfTheWeek.setText(R.string.thiago);
                break;
            case 11:
                personOfTheWeek.setText(R.string.wendler);
                break;
            default:
                personOfTheWeek.setText(R.string.person_of_the_week);
                break;
        }
    }

}