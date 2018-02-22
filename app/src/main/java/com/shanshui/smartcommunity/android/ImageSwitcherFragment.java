package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.shanshui.smartcommunity.android.util.ResourceHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageSwitcherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageSwitcherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSwitcherFragment extends Fragment implements ViewSwitcher.ViewFactory {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "ImageSwitcherFragment";
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private ImageSwitcher mImageSwitcher;
    float downX;
    private int[] mImageIds;
    private int mCurrentPos = 0;
    private int mIteratorDuration = 5000;
    private OnFragmentInteractionListener mListener;
    private ImageView[] mThumbViews;

    public ImageSwitcherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageIds Parameter 1.
     * @return A new instance of fragment ImageSwitcherFragment.
     */
    public static ImageSwitcherFragment newInstance(int[] imageIds) {
        ImageSwitcherFragment fragment = new ImageSwitcherFragment();
        fragment.mImageIds = imageIds;
        Bundle args = new Bundle();
        args.putIntArray(ARG_PARAM1, imageIds);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageIds = getArguments().getIntArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_image_switcher, container, false);

        mImageSwitcher = frameLayout.findViewById(R.id.imageSwitcher);

        LinearLayout linearLayout = frameLayout.findViewById(R.id.thumbs);
        mThumbViews = new ImageView[this.mImageIds.length];
        for (int i = 0; i < mThumbViews.length; i++) {
            ImageView imageView = new ImageView(getContext());
            mThumbViews[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 3;
            layoutParams.leftMargin = 3;
//            mThumbViews[i].setBackgroundResource(R.drawable.ic_blackdot);
//            if (0 == i) {
//                ResourceHelper.setImageViewTint(getContext(), mThumbViews[i], R.color.colorPrimary);
//            } else {
//                ResourceHelper.setImageViewTint(getContext(), mThumbViews[i], R.color.itemNotSelected);
//            }
            //imageView.setBackgroundResource(R.drawable.ic_blackdot);
            linearLayout.addView(imageView, layoutParams);
        }

        //上一个界面传过来的位置
        mCurrentPos = getActivity().getIntent().getIntExtra("position", 0);
        mImageSwitcher.setFactory(this);
        mImageSwitcher.setImageResource(mImageIds[mCurrentPos]);

        // setFactory will invoke makeView in its stack, which needs mThumbViews and other parameters to be ready first.
        mImageSwitcher.postDelayed(new Runnable() {
            public void run() {
                if (mCurrentPos == mImageIds.length - 1) {
                    mCurrentPos = 0;
                } else {
                    mCurrentPos++;
                }
                onIndexChange();
                mImageSwitcher.postDelayed(this, mIteratorDuration);
            }
        }, mIteratorDuration);

        setImageBackground(mCurrentPos);
        return frameLayout;
    }

    private void onIndexChange() {
        mImageSwitcher.setImageResource(mImageIds[this.mCurrentPos]);
        setImageBackground(this.mCurrentPos);
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < mThumbViews.length; i++) {

            if (i == selectItems) {// in fact, we could use one vector image and set the image tint for different color
                //ResourceHelper.setImageViewTint(getContext(), mThumbViews[i], R.color.colorPrimary);
                mThumbViews[i].setBackgroundResource(R.drawable.ic_whitedot);
            } else {
                //ResourceHelper.setImageViewTint(getContext(), mThumbViews[i], R.color.itemNotSelected);
                mThumbViews[i].setBackgroundResource(R.drawable.ic_blackdot);
            }
        }
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
    public View makeView() {
        ImageView i = new ImageView(getContext());
        //i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.MATCH_PARENT));
        i.setImageResource(mImageIds[mCurrentPos]);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        });
        setImageBackground(mCurrentPos);
        i.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        //手指按下的X坐标
                        downX = event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        float lastX = event.getX();
                        //抬起的时候的X坐标大于按下的时候就显示上一张图片
                        if (lastX > downX) {
                            if (mCurrentPos == 0) {
                                mCurrentPos = mImageIds.length - 1;
                            } else {
                                mCurrentPos--;
                            }
                            onIndexChange();
                        } else if (lastX < downX) {
                            if (mCurrentPos == mImageIds.length - 1) {
                                mCurrentPos = 0;
                            } else {
                                mCurrentPos++;
                            }
                            onIndexChange();
                        }
                    }
                    break;
                }
                return true;
            }

        });
        //点击事件换图
                /* i.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(Iindex==mImageIds.length-1){
                    Iindex=0;
                 }else{
                     Iindex++;
                 }
                 is.setImageResource(mImageIds[Iindex]);
                 //sm("you click this picture");
             }
        });*/
        return i;
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
