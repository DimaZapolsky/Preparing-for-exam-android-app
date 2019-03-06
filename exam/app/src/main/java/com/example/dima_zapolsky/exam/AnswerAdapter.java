package com.example.dima_zapolsky.exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static com.example.dima_zapolsky.exam.R.color.white;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder> {
    ArrayList<String> answers;
    ArrayList<Integer> rightAnswers;
    Set<Integer> nowPushed;
    boolean checked;
    ArrayList<Integer> index;

    class AnswerHolder extends RecyclerView.ViewHolder {
        private Button ansButton;

        public AnswerHolder(View itemView) {
            super(itemView);
            ansButton = itemView.findViewById(R.id.answerViewkek);
        }
    }

    @Override
    public AnswerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnswerAdapter.AnswerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_view, parent, false));
    }

    public AnswerAdapter(ArrayList<String> answers, ArrayList<Integer> rightAnswers) {
        this.answers = answers;
        this.rightAnswers = rightAnswers;
        nowPushed = new TreeSet<Integer>();
        checked = false;
        index = new ArrayList<Integer>();
        for (int i = 0; i < answers.size(); i++) {
            index.add(i);
        }
        Collections.shuffle(index);
    }

    @Override
    public void onBindViewHolder(final AnswerAdapter.AnswerHolder holder, final int position) {
        int pos = index.get(position);
        holder.ansButton.setText(String.valueOf(position + 1).concat(") ").concat(answers.get(pos)));
        if (!checked) {
            if (nowPushed.contains(pos))
                holder.ansButton.setBackgroundColor(Color.YELLOW);
            else
                holder.ansButton.setBackgroundColor(Color.WHITE);
            holder.ansButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = index.get(position);
                    Log.e("push", String.valueOf(pos));
                    if (!nowPushed.contains(pos)) {
                        nowPushed.add(pos);
                        holder.ansButton.setBackgroundColor(Color.YELLOW);
                    } else {
                        nowPushed.remove(pos);
                        holder.ansButton.setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }
        else {
            if (rightAnswers.contains(pos) && !nowPushed.contains(pos))
                holder.ansButton.setBackgroundColor(Color.GREEN);
            else if (rightAnswers.contains(pos) && nowPushed.contains(pos))
                holder.ansButton.setBackgroundColor(Color.YELLOW);
            else if (!rightAnswers.contains(pos) && nowPushed.contains(pos))
                holder.ansButton.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public static TreeSet getSort(ArrayList<Integer> list){
        TreeSet<Integer> set = new TreeSet<Integer>(list);
        return set;
    }

    public boolean check() {
        checked = true;
        boolean flag = nowPushed.equals(getSort(rightAnswers));
        if (!flag) {
            notifyDataSetChanged();
        }
        return flag;
    }
}
