package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.shanshui.smartcommunity.android.util.DrawableHelper;
import com.shanshui.smartcommunity.android.util.WindowHelper;
import com.shanshui.smartcommunity.android.viewcard.CardItem;
import com.shanshui.smartcommunity.android.viewcard.CardPagerAdapter;
import com.shanshui.smartcommunity.android.viewcard.ShadowTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mName;
    private NestedGridView gridView;
    private List<Map<String, Object>> dataList;
    private GridViewColorAdaptor adapter;

    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CollapsingToolbarLayoutState state;

    private HorizontalBarChart propertyIssueChart;
    private PieChart categoryPie;

    private NestedGridView propertyIssueGridView;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CoordinatorLayout view = (CoordinatorLayout) inflater.inflate(R.layout.fragment_home, container, false);
        initFeatureEntries(inflater, view);
        initNotificationZone(view);
        initToolbar(view);
        initPropertyIssueChart(view);
        initPropertyIssueGridChart(inflater, view);
        return view;
    }

    private void initFeatureEntries(LayoutInflater inflater, View view) {
        gridView = (NestedGridView) view.findViewById(R.id.gridview);
        //初始化数据
        initData();

        String[] from = {"img", "text"};

        int[] to = {R.id.item_icon, R.id.item_text};
        int colors[] = {R.color.func_1, R.color.func_2, R.color.func_3, R.color.func_4,
                R.color.func_5,
                R.color.func_6, R.color.func_7, R.color.func_8, R.color.func_9,
                R.color.func_4,
                R.color.func_green, R.color.func_1, R.color.func_3, R.color.func_5,
                R.color.func_2};
        adapter = new GridViewColorAdaptor(inflater, getContext(), dataList, R.layout.grid_view_item, from, to, colors);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });
    }

    private void initData() {
        //图标
        int icno[] = {R.drawable.ic_func_repair, R.drawable.ic_func_suggestion, R.drawable.ic_func_car, R.drawable.ic_func_report,
                R.drawable.ic_func_pay,
                R.drawable.ic_func_notify, R.drawable.ic_func_polling, R.drawable.ic_func_chat, R.drawable.ic_func_sale,
                R.drawable.ic_func_activity,
                R.drawable.ic_func_vegetable, R.drawable.ic_func_housekeeping, R.drawable.ic_func_delivery, R.drawable.ic_func_key,
                R.drawable.ic_func_misc};
        //图标下的文字
        String name[] = {
                "报修", "建议", "车辆", "物业报告", "付费",
                "邻里提醒", "投票", "闲聊", "二手", "社区活动",
                "掌上菜场", "家政", "快递", "电子钥匙", "其他"
        };

        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
    }

    private void initNotificationZone(View parent) {
        mViewPager = (ViewPager) parent.findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_activity_landing, R.string.notify_content));
        mCardAdapter.addCardItem(new CardItem(R.string.title_activity_landing, R.string.notify_content));
        mCardAdapter.addCardItem(new CardItem(R.string.title_activity_landing, R.string.notify_content));
        mCardAdapter.addCardItem(new CardItem(R.string.title_activity_landing, R.string.action_sign_in));
//        mFragmentCardAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(),
//                PixelHelper.dpToPixels(2, getContext()));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        //mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initToolbar(View view) {
        final AppBarLayout barLayout = view.findViewById(R.id.appbar);
        final CollapsingToolbarLayout toolbarLayout = view.findViewById(R.id.collapsing_layout);
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        final LinearLayout linear = view.findViewById(R.id.ll_home_header);
        final TextView location = view.findViewById(R.id.btn_auto_loc);
        final TextView message = view.findViewById(R.id.btn_msg);
        final TextView city = view.findViewById(R.id.btn_city_select);
        final TextView community = view.findViewById(R.id.btn_community_search);
        initImageSwitcher(view);
        final int primary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        final int black = ContextCompat.getColor(getContext(), R.color.black_overlay);
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        WindowHelper.setStatusBarLightForCollapsingToolbar(getActivity(), barLayout, toolbarLayout, toolbar, R.color.colorPrimary);
                        DrawableHelper.setTextViewColor(location, primary);
                        DrawableHelper.setTextViewColor(message, primary);
                        DrawableHelper.setBackgroundColor(linear, primary);
                        DrawableHelper.setTextViewColor(city, black);
                        DrawableHelper.setTextViewColor(community, black);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        WindowHelper.translucentStatusBar(getActivity());

                        DrawableHelper.setTextViewColor(location, black);
                        DrawableHelper.setTextViewColor(message, black);
                        DrawableHelper.setTextViewColor(city, primary);
                        DrawableHelper.setTextViewColor(community, primary);
                        DrawableHelper.setBackgroundColor(linear, ContextCompat.getColor(getContext(), R.color.colorfab));
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
        WindowHelper.setStatusBarColorForCollapsingToolbar(getActivity(), barLayout, toolbarLayout, toolbar, getResources().getColor(R.color.colorPrimary));
    }

    private void initImageSwitcher(View view) {
        ConstraintLayout constraintLayout = view.findViewById(R.id.switcher_container);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (getChildFragmentManager().findFragmentByTag(ImageSwitcherFragment.TAG) == null) {
            transaction.add(R.id.switcher_container,
                    ImageSwitcherFragment.newInstance(new int[]{R.drawable.ic_cover_1, R.drawable.ic_cover_2,
                            R.drawable.ic_cover_3, R.drawable.ic_cover_4}),
                    ImageSwitcherFragment.TAG).commit();
        }
    }

    private void initPropertyIssueGridChart(LayoutInflater inflater, View view) {
        propertyIssueGridView = view.findViewById(R.id.gridview_issue);
//        propertyIssueGridView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT,));
        Chart charts[] = {this.propertyIssueChart, this.categoryPie};
        propertyIssueGridView.setAdapter(new GridViewChartAdaptor(inflater, getContext(), charts));
    }

    private void initPropertyIssueChart(View view) {
        LinearLayout chartLayout = view.findViewById(R.id.property_issue_chart);
        LinearLayout pieLayout = view.findViewById(R.id.property_issue_pie);
        if (propertyIssueChart == null) {
            propertyIssueChart = new HorizontalBarChart(getContext());

            propertyIssueChart.setData(initPropertyIssueData());
            propertyIssueChart.setFitBars(true); // make the x-axis fit exactly all bars
            propertyIssueChart.setMinimumHeight(520);
            propertyIssueChart.setMinimumWidth(520);
            Description description = new Description();
            description.setText("报修概况");
            description.setTextSize(10f);
            propertyIssueChart.setDescription(description);
            propertyIssueChart.disableScroll();
            propertyIssueChart.setDrawBorders(false);
            propertyIssueChart.setDrawGridBackground(false);
            propertyIssueChart.setDrawMarkers(false);
            propertyIssueChart.getXAxis().setDrawGridLines(false);
            propertyIssueChart.getXAxis().setDrawAxisLine(false);
            propertyIssueChart.getXAxis().disableGridDashedLine();
            propertyIssueChart.getAxisRight().setEnabled(false);
            propertyIssueChart.setFitBars(true);
            propertyIssueChart.getAxisLeft().setSpaceMax(5f);
            propertyIssueChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return "";
                }
            });
        }

        if (categoryPie == null) {
            categoryPie = new PieChart(getContext());
            categoryPie.setData(initPieChartData());
            Description description = new Description();
            description.setText("报修分布");
            description.setTextSize(10f);
            categoryPie.setDescription(description);
            categoryPie.setMinimumHeight(520);
            categoryPie.setMinimumWidth(520);
        }
        //chartLayout.addView(propertyIssueChart, 400, 600);
        //pieLayout.addView(categoryPie, 400, 600);
        //propertyIssueChart.invalidate();
        //categoryPie.invalidate();
    }

    private PieData initPieChartData() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "门"));
        entries.add(new PieEntry(26.7f, "窗"));
        entries.add(new PieEntry(24.0f, "电梯"));
        entries.add(new PieEntry(30.8f, "信箱"));
        entries.add(new PieEntry(4f, "其他"));
        PieDataSet set = new PieDataSet(entries, "");
        set.addColor(ContextCompat.getColor(getContext(), R.color.func_2));
        set.addColor(ContextCompat.getColor(getContext(), R.color.func_7));
        set.addColor(ContextCompat.getColor(getContext(), R.color.func_4));
        set.addColor(ContextCompat.getColor(getContext(), R.color.func_3));
        PieData data = new PieData(set);
        return data;
    }

    private BarData initPropertyIssueData() {
        List<BarEntry> personal = new ArrayList<>();
        personal.add(new BarEntry(0f, 2f));
        List<BarEntry> pub = new ArrayList<>();
        pub.add(new BarEntry(1f, 10f));
        List<BarEntry> total = new ArrayList<>();
        total.add(new BarEntry(2f, 30f));
        List<BarEntry> resolved = new ArrayList<>();
        resolved.add(new BarEntry(3f, 15f));

        BarDataSet setPersonal = new BarDataSet(personal, "我的");
        setPersonal.setColor(ContextCompat.getColor(getContext(), R.color.func_2));
        BarDataSet setPublic = new BarDataSet(pub, "公共");
        setPublic.setColor(ContextCompat.getColor(getContext(), R.color.func_7));
        BarDataSet setTotal = new BarDataSet(total, "总计");
        setTotal.setColor(ContextCompat.getColor(getContext(), R.color.func_4));
        BarDataSet setResolved = new BarDataSet(resolved, "已修");
        setResolved.setColor(ContextCompat.getColor(getContext(), R.color.func_3));
        BarData data = new BarData(setPersonal, setPublic, setTotal, setResolved);
        data.setBarWidth(0.5f); // set custom bar width
        return data;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
}
