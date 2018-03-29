package com.zhangyan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyan.bean.Girl;
import com.zhangyan.rxjava.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder> {
    private List<Girl> mlist;

    public RecyclerViewAdapter(List<Girl> mlist) {
        this.mlist = mlist;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MainViewHolder holder = new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.mName.setText(mlist.get(position).getName());
        holder.mStart.setText(mlist.get(position).getStart());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        private TextView mName;
        private TextView mStart;


    public MainViewHolder(View itemView) {
        super(itemView);
        if (itemView!=null){
            mName = itemView.findViewById(R.id.item_image);
            mStart = itemView.findViewById(R.id.item_title);
        }
    }
}

}
