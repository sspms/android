package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shanshui.smartcommunity.android.adaptor.VegetableItemAdaptor;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingFragment extends Fragment implements OnTabSelectListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mName;
    private final String[] subTabs = {
            "掌上团菜", "社区商业", "家政服务", "二手市场"
    };
    private SlidingTabLayout tabLayout_2;
    private OnFragmentInteractionListener mListener;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ShoppingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingFragment newInstance(String param1) {
        ShoppingFragment fragment = new ShoppingFragment();
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
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_shopping, container, false);
        final NestedScrollView nsv = frameLayout.findViewById(R.id.shopping_scroll_view);
        NestedViewPager vp = frameLayout.findViewById(R.id.vp);

        List<LinearLayout> listViews = new ArrayList();
        for (int i = 0; i < this.subTabs.length; i++) {
            LinearLayout scrollView = (LinearLayout) inflater.inflate(R.layout.shopping_veg, frameLayout, false);
            //NestedGridView ngv = scrollView.findViewById(R.id.gridview_veg);
            //ngv.setAdapter(new GridViewBaseAdaptor(inflater, getContext(), SellingItem.mock()));
            RecyclerView cell = scrollView.findViewById(R.id.veg_recycler_view);

            // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
            final VegetableItemAdaptor adapter = new VegetableItemAdaptor(getContext(), R.layout.cell_veg, SellingItem.mock());
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            // set elements to adapter
            cell.setAdapter(adapter);
            cell.setLayoutManager(llm);
//            cell.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_UP) {
//                        nsv.requestDisallowInterceptTouchEvent(false);
//                    } else {
//                        nsv.requestDisallowInterceptTouchEvent(true);
//                    }
//                    return false;
//                }
//            });
            // set on click event listener to list view
//            cell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                    // toggle clicked cell state
//
//                    ((FoldingCell) view).toggle(false);
//                    // register in adapter that state for selected cell is toggled
//                    adapter.registerToggle(pos);
//                }
//            });

            listViews.add(scrollView);
        }

        vp.setAdapter(new ViewPagerAdapter(listViews));
        vp.setCurrentItem(0);
        tabLayout_2 = frameLayout.findViewById(R.id.tl_2);

        tabLayout_2.setViewPager(vp, this.subTabs);
        tabLayout_2.setOnTabSelectListener(this);

        //int currentPos = 0;
        //vp.setCurrentItem(currentPos);

        tabLayout_2.showDot(0);

        tabLayout_2.showMsg(3, 5);
        tabLayout_2.setMsgMargin(3, 0, 10);
        return frameLayout;
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

    @Override
    public void onTabSelect(int position) {

    }

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
