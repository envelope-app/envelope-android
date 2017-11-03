package com.mrinalraj.envelope.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mrinalraj.envelope.R;

import java.util.List;

/**
 * Created by mrinal on 11/2/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    List<PostStructure> postStructureList;
    PostAdapterListener listener;

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        public TextView time,date,postText,likes,dislikes,comments,postID;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            date = (TextView) itemView.findViewById(R.id.date);
            postText = (TextView) itemView.findViewById(R.id.textView4);
            likes = (TextView) itemView.findViewById(R.id.likecount);
            dislikes = (TextView) itemView.findViewById(R.id.dislikescount);
            comments = (TextView) itemView.findViewById(R.id.commentscount);
            postID = (TextView) itemView.findViewById(R.id.postid);

            itemView.findViewById(R.id.likebtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLikeClicked(v,getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.dislikebtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDislikeClicked(v,getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.commentbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCommentClicked(v,getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.extendedMenu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onExtendedMenuClicked(v,getAdapterPosition());
                }
            });

        }
    }

    public PostAdapter(List<PostStructure> postStructuresList,PostAdapterListener listener){
        this.postStructureList = postStructuresList;
        this.listener= listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostStructure post= postStructureList.get(position);
        holder.time.setText(post.getPost()[0]);
        holder.date.setText(post.getPost()[1]);
        holder.postText.setText(post.getPost()[2]);
        holder.likes.setText(post.getPost()[3]+ " Likes");
        holder.dislikes.setText(post.getPost()[4]+ " disliked");
        holder.comments.setText(post.getPost()[5]+" comments");
        holder.postID.setText(post.getPost()[6]);
    }

    @Override
    public int getItemCount() {
        return postStructureList.size();
    }

    public interface PostAdapterListener{
        void onLikeClicked(View v,int position);
        void onDislikeClicked(View v,int position);
        void onCommentClicked(View v,int position);
        void onExtendedMenuClicked(View v,int position);
    }

}
