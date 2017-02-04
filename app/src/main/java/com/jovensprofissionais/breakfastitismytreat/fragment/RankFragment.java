package com.jovensprofissionais.breakfastitismytreat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jovensprofissionais.breakfastitismytreat.R;
import com.jovensprofissionais.breakfastitismytreat.constant.Constant;
import android.widget.TableRow.LayoutParams;

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 02/01/17
 */

public class RankFragment extends Fragment {

    private DatabaseReference databaseReference;
    private TableLayout rankingTable;

    public RankFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'
        View rankFragment =  inflater.inflate(R.layout.fragment_rank, container, false);
        rankingTable = (TableLayout) rankFragment.findViewById(R.id.rankingTableLayout);


        databaseReference.child(Constant.PEOPLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i=0; i<Constant.TOTAL_PEOPLE; i++){
                    createNewTableRow(""+dataSnapshot.child(""+i).child(Constant.NAME).getValue(),
                            ""+dataSnapshot.child(""+i).child(Constant.RATE_AVERAGE).getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rankFragment;
    }

    private void createNewTableRow(String name, String averageRate)
    {
        TableRow tableRow = new TableRow(this.getContext());
        TextView tv = new TextView(this.getContext());

        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv.setPadding(5, 5, 5, 5);
        tv.setText(name);
        tableRow.addView(tv);

        tv = new TextView(this.getContext());
        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv.setPadding(20, 5, 5, 5);
        tv.setText(averageRate);
        tableRow.addView(tv);

        rankingTable.addView(tableRow);
    }

}
