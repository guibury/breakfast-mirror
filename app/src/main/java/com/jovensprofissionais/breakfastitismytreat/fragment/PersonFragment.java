package com.jovensprofissionais.breakfastitismytreat.fragment;

import android.media.MediaPlayer;

import android.os.Bundle;
import java.util.Calendar;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    /* Variables related to Json files on server */
    private int personWeekId;
    private int weekOfYear;
    private int currTotal5StarVotes;
    private int currTotal4StarVotes;
    private int currTotal3StarVotes;
    private int currTotal2StarVotes;
    private int currTotal1StarVotes;

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

        // With week of the year is set the person of the week  (person_week)
        /*databaseWeekOfYear.child(Constant.WEEK_OF_THE_YEAR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                weekOfYear = Integer.parseInt(dataSnapshot.getValue().toString());
                int updatedWeekOfYear = WeekOfYear();

                Log.i("PERSONFRAG","weekOfYear on server " + weekOfYear);
                Log.i("PERSONFRAG","weekOfYear on calendar " + updatedWeekOfYear);
                // Verify if JSon is update with the latest week of the year, if not then update server
                if (weekOfYear < updatedWeekOfYear){
                    databaseWeekOfYear.child(Constant.WEEK_OF_THE_YEAR).setValue(updatedWeekOfYear);
                }

                // Calculate the current person of the week using a circular buffer
                personWeekId = (updatedWeekOfYear % Constant.TOTAL_PEOPLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        Log.i("PERSONFRAG","weekOfYear on calendar " + getWeekOfYear());
        personWeekId = getWeekOfYear();

        // Getting all information of the person of the week(such as total times voted, rate not needed because its calculate each time it occurs a vote) for later calculations
        databasePeople.child(Constant.PEOPLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personOfTheWeek.setText(""+dataSnapshot.child(""+ personWeekId).child(Constant.NAME).getValue());
                currTotal5StarVotes = Integer.parseInt(dataSnapshot.child(""+ personWeekId).child(Constant.TOTAL_5_STARS_VOTE).getValue().toString());
                currTotal4StarVotes = Integer.parseInt(dataSnapshot.child(""+ personWeekId).child(Constant.TOTAL_4_STARS_VOTE).getValue().toString());
                currTotal3StarVotes = Integer.parseInt(dataSnapshot.child(""+ personWeekId).child(Constant.TOTAL_3_STARS_VOTE).getValue().toString());
                currTotal2StarVotes = Integer.parseInt(dataSnapshot.child(""+ personWeekId).child(Constant.TOTAL_2_STARS_VOTE).getValue().toString());
                currTotal1StarVotes = Integer.parseInt(dataSnapshot.child(""+ personWeekId).child(Constant.TOTAL_1_STARS_VOTE).getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Listener for the People JSON
        databasePeople.child(Constant.PEOPLE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String i = dataSnapshot.getRef().child(Constant.PERSON_OF_THE_WEEK).getKey();
                Toast.makeText(getActivity(), "Updated "+i + dataSnapshot.child(Constant.NAME).getValue()
                        + " " + dataSnapshot.child(Constant.RATE_AVERAGE).getValue(), Toast.LENGTH_SHORT).show();
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

    private int getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    private int calculateAverageRating(){

        int averageRate;

        averageRate = (Constant.FIVE_STARS * currTotal5StarVotes + Constant.FOUR_STARS * currTotal4StarVotes
         + Constant.THREE_STARS * currTotal3StarVotes + Constant.TWO_STARS * currTotal2StarVotes
         + Constant.ONE_STAR * currTotal1StarVotes) / (currTotal5StarVotes + currTotal4StarVotes
         + currTotal3StarVotes + currTotal2StarVotes + currTotal1StarVotes);

        return averageRate;
    }

    private void countVote(int vote){
        switch(vote){
            case 5:{
                currTotal5StarVotes ++;
                databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.TOTAL_5_STARS_VOTE).setValue(currTotal5StarVotes);
                break;
            }
            case 4:{
                currTotal4StarVotes ++;
                databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.TOTAL_4_STARS_VOTE).setValue(currTotal4StarVotes);
                break;
            }
            case 3:{
                currTotal3StarVotes ++;
                databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.TOTAL_3_STARS_VOTE).setValue(currTotal3StarVotes);
                break;
            }
            case 2:{
                currTotal2StarVotes ++;
                databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.TOTAL_2_STARS_VOTE).setValue(currTotal2StarVotes);
                break;
            }
            case 1:{
                currTotal1StarVotes ++;
                databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.TOTAL_1_STARS_VOTE).setValue(currTotal1StarVotes);
                break;
            }
        }
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

            countVote(ratingBar.getProgress());
            databasePeople.child(Constant.PEOPLE).child(""+ personWeekId).child(Constant.RATE_AVERAGE).setValue(calculateAverageRating());
            Toast.makeText(getActivity(), R.string.realized_vote, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.not_realized_vote, Toast.LENGTH_LONG).show();
        }
    }
}