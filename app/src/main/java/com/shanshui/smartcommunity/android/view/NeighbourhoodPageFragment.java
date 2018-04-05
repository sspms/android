package com.shanshui.smartcommunity.android.view;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.widget.MsgView;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.Topic;
import com.shanshui.smartcommunity.android.model.Vote;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NeighbourhoodPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NeighbourhoodPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeighbourhoodPageFragment extends PageFragment {
    private static final String ARG_NAME = "ARG_NAME";

    private String name;
    private final String[] subTabs = {
            "社区投票", "社区论坛", "社区提案"
    };

    public NeighbourhoodPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment NeighbourhoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NeighbourhoodPageFragment newInstance(String name) {
        NeighbourhoodPageFragment fragment = new NeighbourhoodPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
        prepareTestData();
    }

    private void prepareTestData() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                List<Topic> mocks = Topic.mock();
                int size = mocks.size();
                Topic[] topics = new Topic[size];
                for (int i = 0; i < size; i++) {
                    topics[i] = mocks.get(i);
                }
                DatabaseRepo.newInstance(getContext()).topicDao().insert(topics);
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseRepo.newInstance(getContext()).voteDao().insert(Vote.mock(25));
                return null;
            }
        }.execute();
    }

    @NonNull
    @Override
    protected List<Fragment> viewPagerFragments() {
        List<Fragment> fragments = new ArrayList();
        fragments.add(VoteFragment.newInstance(this.subTabs[0], R.layout.fragment_my_property_issue));
        fragments.add(TopicFragment.newInstance(this.subTabs[1], R.layout.fragment_my_property_issue));
        fragments.add(VoteFragment.newInstance(this.subTabs[2], R.layout.fragment_my_property_issue));

        return fragments;
    }

    @NonNull
    @Override
    protected String[] tabTitles() {
        return this.subTabs;
    }

    @Override
    protected void customizeTabLayout(SlidingTabLayout tabLayout) {

        tabLayout.showDot(0);

        tabLayout.showMsg(3, 5);
        tabLayout.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = tabLayout.getMsgView(3);
        if (rtv_2_3 != null)

        {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        tabLayout.showMsg(5, 5);
        tabLayout.setMsgMargin(5, 0, 10);
    }
}
