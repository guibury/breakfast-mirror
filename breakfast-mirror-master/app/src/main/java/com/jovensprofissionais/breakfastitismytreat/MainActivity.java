package com.jovensprofissionais.breakfastitismytreat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jovensprofissionais.breakfastitismytreat.fragment.PersonFragment;
import com.jovensprofissionais.breakfastitismytreat.fragment.RankFragment;

import java.util.ArrayList;
import java.util.List;
//App icon made by Kitchenware from www.flaticon.com

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 30/12/16
 */

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseReference mDatabase;
//    Button voteButton;
//    RankingDBController rankingDBController;
//    TextView personOfTheWeek;
//    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDatabase = FirebaseDatabase.getInstance().getReference();


//      rankingDBController = new RankingDBController(getBaseContext());
//      voteButton = (Button) findViewById(R.id.voteButton);
//      personOfTheWeek = (TextView) findViewById(R.id.personOfTheWeek);
//      ratingBar = (RatingBar) findViewById(R.id.ratingBar);

//      voteButton.setOnClickListener(voteHandler);
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PersonFragment(), "PESSOA");
        adapter.addFragment(new RankFragment(), "CLASSIFICAÇÃO");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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