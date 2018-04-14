package com.shanshui.smartcommunity.android.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.widget.MsgView;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.Community;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropertyPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropertyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyPageFragment extends PageFragment {
    private static final String ARG_NAME = "ARG_NAME";
    private static final String TAG = "PropertyFragment";

    private String name;
    private final String[] subTabs = {
            "我的报修", "公共报修", "物业话题", "物业报告"
    };

    public PropertyPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment PropertyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertyPageFragment newInstance(String name) {
        PropertyPageFragment fragment = new PropertyPageFragment();
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        long id = 1L;//preferences.getLong("id", 0L);
        prepareTestData();
    }

    private void prepareTestData() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Community community = new Community(1L, "Community A");
                community.setCity("Shanghai");
                community.setLocation("Zhangjiang Rd. 404 No.");
                community.setDistrict("Pudong District");
                community.setCapacyRate(1.8);
                community.setGreenRate(3.3);
                community.setBuildingTotal(32);
                community.setComleteDate(new Date());
                community.setHouseholdTotal(1600);
                community.setParkingLotTotal(2000);
                community.setPropertyCompany("Shanshui Property Service Company, Ltd.");
                DatabaseRepo.newInstance(getContext()).communityDao().insert(community);

                PropertyIssue[] issues = new PropertyIssue[32];
                for (int i = 0; i < 8; i++) {
                    PropertyIssue pi = new PropertyIssue(1L * (i + 1), 1L, 1L);
                    pi.setCategory("墙");
                    pi.setDescription("公司来电问，怎么回事？都三天了还不去弄（我这不是身体不舒服嘛，弄什么弄？你要快，你叫别人去）家里的龙眼熟了，回去摘些吃吃，宽带的事，明天再去……");
                    pi.setLocation("25栋201室");
                    pi.setStatus("验收");
                    pi.setType("PUBLIC");
                    pi.setOpenDate(new Date());
                    PropertyIssue pii = new PropertyIssue(2L * (i + 1), 1L, 1L);
                    pii.setCategory("门");
                    pii.setDescription("昨夜睡的晚了，起床已经9点了，起来抽烟，喝茶，吃早餐，又中午了。看看外面的太阳……唉！！宽带的事，还是明天再去吧！");
                    pii.setLocation("西大门");
                    pii.setStatus("维修");
                    pii.setOpenDate(new Date());
                    PropertyIssue piii = new PropertyIssue(3L * (i + 1), 1L, 1L);
                    piii.setCategory("游乐设施");
                    piii.setDescription("今天起来觉得身体不太对劲，有点累。有人拿两个主机来叫修修，唉……精神不好，你先放到墙角那里吧！宽带用户来电问，怎么还不来检查的？唉……身体不舒服，明天吧！");
                    piii.setLocation("地库");
                    piii.setStatus("确认");
                    piii.setOpenDate(new Date());
                    PropertyIssue piiii = new PropertyIssue(4L * (i + 1), 1L, 2L);
                    piiii.setCategory("车位");
                    piiii.setDescription("昨夜喝了点酒，和一帮帅哥美女疯到半夜，今天起床晚了，头有点痛，休息一下，下午再去检查宽带，刚刚想去的，天又下雨了，唉……还是明天再去吧！");
                    piiii.setLocation("地库");
                    piiii.setStatus("报修");
                    piiii.setType("PUBLIC");
                    piiii.setOpenDate(new Date());
                    issues[4 * i] = pi;
                    issues[4 * i + 1] = pii;
                    issues[4 * i + 2] = piii;
                    issues[4 * i + 3] = piiii;
                }
                DatabaseRepo.newInstance(getContext()).propertyIssueDao().insert(issues);
                PropertyIssue[] pis = DatabaseRepo.newInstance(getContext()).propertyIssueDao().findAll();


                Log.i(TAG, "prepare data done, verifying...");
                Log.i(TAG, String.valueOf(pis.length));
                return null;
            }
        }.execute();

    }

    @NonNull
    @Override
    protected List<Fragment> viewPagerFragments() {
        List<Fragment> fragments = new ArrayList();
        fragments.add(MyPropertyIssueFragment.newInstance("我的报修", R.layout.fragment_my_property_issue));
        fragments.add(PublicPropertyIssueFragment.newInstance("公共报修", R.layout.fragment_my_property_issue));
        fragments.add(MyPropertyIssueFragment.newInstance("物业话题", R.layout.fragment_my_property_issue));
        fragments.add(PublicPropertyIssueFragment.newInstance("物业报告", R.layout.fragment_my_property_issue));

        return fragments;
    }

    protected void setToolbarImage(ImageView view) {
        view.setImageResource(R.drawable.property_header);
    }

    @Override
    @NonNull
    protected String[] tabTitles() {
        return this.subTabs;
    }

    @Override
    protected void customizeTabLayout(SlidingTabLayout tabLayout) {
        tabLayout.showDot(0);

        tabLayout.showMsg(3, 5);
        tabLayout.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = tabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        tabLayout.showMsg(5, 5);
        tabLayout.setMsgMargin(5, 0, 10);
    }
}
