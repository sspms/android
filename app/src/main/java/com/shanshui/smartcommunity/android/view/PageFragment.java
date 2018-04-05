package com.shanshui.smartcommunity.android.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.FragmentListAdaptor;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class PageFragment extends Fragment implements OnTabSelectListener {

    private OnFragmentInteractionListener mListener;
    protected SlidingTabLayout tabLayout;

    protected ViewPager initViewPager(View parent) {
        ViewPager vp = parent.findViewById(R.id.vp);
        List<Fragment> recyclerViewList = new ArrayList();
        recyclerViewList.addAll(viewPagerFragments());
//        for (int i = 0; i < this.subTabs.length; i++) {
//            MyPropertyIssueFragment fragment = MyPropertyIssueFragment.newInstance(this.subTabs[i]);
//            recyclerViewList.add(fragment);
//        }

        vp.setAdapter(new FragmentListAdaptor(getChildFragmentManager(), recyclerViewList, tabTitles()));
        vp.setOffscreenPageLimit(4);
        vp.setCurrentItem(0);
        return vp;
    }

    protected SlidingTabLayout initTab(View parent, ViewPager pager, String[] tabs) {
        tabLayout = parent.findViewById(R.id.tl_2);
        tabLayout.setViewPager(pager, tabs);
        tabLayout.setOnTabSelectListener(this);

        customizeTabLayout(tabLayout);
        return tabLayout;
    }

    @NonNull
    protected abstract List<Fragment> viewPagerFragments();

    @NonNull
    protected abstract String[] tabTitles();

    protected abstract void customizeTabLayout(SlidingTabLayout tabLayout);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_property, container, false);

        this.tabLayout = initTab(frameLayout, initViewPager(frameLayout), tabTitles());
        return frameLayout;
    }

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
     * from {@link SlidingTabLayout}
     *
     * @param position
     */
    @Override
    public void onTabSelect(int position) {
        Log.d(getTag(), "selected position: " + position);
    }

    /**
     * from {@link SlidingTabLayout}
     *
     * @param position
     */
    @Override
    public void onTabReselect(int position) {

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
}
