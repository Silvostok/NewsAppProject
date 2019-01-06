package com.example.miguelangelrubiocaballero.newsappproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ListNewsPaperViewHolder> {

    private Context mContext;
    private ArrayList<NewsPaperItem> mNewsPaperList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public NewsPaperAdapter(Context context, ArrayList<NewsPaperItem> newsPaperList){
        mContext = context;
        mNewsPaperList = newsPaperList;

        }

    @NonNull
    @Override
    public ListNewsPaperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_paper_item, viewGroup, false);



        return new ListNewsPaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNewsPaperViewHolder listNewsPaperViewHolder, int i) {
        NewsPaperItem currentItem = mNewsPaperList.get(i);
        //Problem??? wit name??
        String creatorName = currentItem.getmName();
        String creatorDescription = currentItem.getmDescription();
        String creatorUrl = currentItem.getmUrl();


        listNewsPaperViewHolder.mTextViewName.setText(creatorName);
        listNewsPaperViewHolder.mTextViewDescription.setText(creatorDescription);
        listNewsPaperViewHolder.mTextViewUrl.setText(creatorUrl);

    }

    @Override
    public int getItemCount() {
        return mNewsPaperList.size();
    }

    public class ListNewsPaperViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewDescription;
        public TextView mTextViewUrl;


        public ListNewsPaperViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewDescription = itemView.findViewById(R.id.text_view_description);
            mTextViewUrl = itemView.findViewById(R.id.text_view_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
