package com.shanshui.smartcommunity.android;

import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I336253 on 2/24/2018.
 */

public class StepPathBuilder {
    static final int FINISHED = 1;
    static final int ONGONING = 0;
    static final int TODO = -1;
    List<StepBean> steps = new ArrayList();
    String[] names;
    int[] indicators;

    private StepPathBuilder nextStep(String step, int indicator) {
        steps.add(new StepBean(step, indicator));
        return this;
    }

    public StepPathBuilder steps(String[] name) {
        this.names = name;
        return this;
    }

    public StepPathBuilder status(int[] indicator) {
        this.indicators = indicator;
        return this;
    }

    public List<StepBean> build() {
        if (this.names != null) {
            for (int i = 0; i < this.names.length; i++) {
                nextStep(this.names[i], indicator(i));
            }
        }
        return this.steps;
    }

    private int indicator(int position) {
        return position + 1 > this.indicators.length ? TODO : this.indicators[position];
    }
}
