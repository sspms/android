//package com.shanshui.smartcommunity.android.view;
//
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.ViewTreeObserver;
//
//import com.ramotion.circlemenu.CircleMenuView;
//import com.shanshui.smartcommunity.android.R;
//
//public class MenuActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        final CircleMenuView menu = findViewById(R.id.circle_menu);
//        menu.setEventListener(new CircleMenuView.EventListener() {
//            @Override
//            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
//                Log.d("D", "onMenuOpenAnimationStart");
//            }
//
//            @Override
//            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
//                Log.d("D", "onMenuOpenAnimationEnd");
//            }
//
//            @Override
//            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
//                Log.d("D", "onMenuCloseAnimationStart");
//            }
//
//            @Override
//            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
//                MenuActivity.this.finish();
//                Log.d("D", "onMenuCloseAnimationEnd");
//            }
//
//            @Override
//            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
//                Log.d("D", "onButtonClickAnimationStart| index: " + index);
//            }
//
//            @Override
//            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
//                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
//            }
//
//            @Override
//            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
//                Log.d("D", "onButtonLongClick| index: " + index);
//                return true;
//            }
//
//            @Override
//            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
//                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
//            }
//
//            @Override
//            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
//                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
//            }
//        });
//        menu.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                menu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                menu.open(true);
//            }
//        });
//    }
//}
