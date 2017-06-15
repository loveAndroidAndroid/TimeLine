package com.rmyh.timelinedemo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wen on 2017/6/14.
 */

public class TimeAdapter extends RecyclerView.Adapter{

    private MainActivity activity;
    private List<TimeData> data;

    public TimeAdapter(MainActivity mainActivity, List<TimeData> list) {
        this.activity = mainActivity;
        this.data = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tiemview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).setPosition(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDateTime;
        private TextView txt_date_title;
        private RelativeLayout rlTitle;
        private View vLine;
        private int position;
        private TimeData timeData;

        public ViewHolder(View itemView) {
            super(itemView);
            rlTitle = (RelativeLayout) itemView.findViewById(R.id.rl_title);
            vLine = itemView.findViewById(R.id.v_line);
            txtDateTime = (TextView) itemView.findViewById(R.id.txt_date_time);
            txt_date_title = (TextView) itemView.findViewById(R.id.txt_date_title);
        }

        public void setPosition(int position) {
            this.position = position;
            timeData = data.get(position);
            //时间轴竖线的layoutParams,用来动态的添加竖线
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vLine.getLayoutParams();
            if (position == 0) {
                if (data.size() >= 2) {
                    if (!timeData.getPosttime().equals(data.get(position + 1).getPosttime())) {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                    } else {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgtop);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                    }
                } else {
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                }
                rlTitle.setVisibility(View.VISIBLE);
                txtDateTime.setText(TimeFormat.format("yyyy.MM.dd", timeData.getPosttime()));
                //代码设置是px
                layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
            } else if (position < data.size() - 1) {
                if (timeData.getPosttime().equals(data.get(position - 1).getPosttime())) {
                    if (timeData.getPosttime().equals(data.get(position + 1).getPosttime())) {
                        rlTitle.setVisibility(View.GONE);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgcenter);
                        layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                    } else {
                        rlTitle.setVisibility(View.GONE);
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgbot);
                        layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                    }

                } else {
                    if (!timeData.getPosttime().equals(data.get(position + 1).getPosttime())) {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    } else {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgtop);
                    }
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    rlTitle.setVisibility(View.VISIBLE);
                    txtDateTime.setText(TimeFormat.format("yyyy.MM.dd", timeData.getPosttime()));
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                }

            } else {
                if (!timeData.getPosttime().equals(data.get(position - 1).getPosttime())) {
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    rlTitle.setVisibility(View.VISIBLE);
                    txtDateTime.setText(TimeFormat.format("yyyy.MM.dd", timeData.getPosttime()));
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                } else {
                    rlTitle.setVisibility(View.GONE);
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgbot);
                    txtDateTime.setText(TimeFormat.format("yyyy.MM.dd", timeData.getPosttime()));
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                }

            }
            vLine.setLayoutParams(layoutParams);
            txt_date_title.setText(timeData.getTitle());
        }
    }
}
