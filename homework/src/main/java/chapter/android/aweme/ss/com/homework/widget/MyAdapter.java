package chapter.android.aweme.ss.com.homework.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;

import static android.support.v4.graphics.ColorUtils.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NumberViewHolder> {

    private static final String TAG = "GreenAdapter";

    private int mNumberItems;

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    private List<Message> MessageList;


    public MyAdapter(int numListItems, ListItemClickListener listener, List<Message> MessageList) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        this.MessageList=MessageList;
    }


    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

       /*int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);*/

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);

        String name = MessageList.get(position).getTitle();

        numberViewHolder.TimeView.setText(MessageList.get(position).getTime());
        numberViewHolder.viewHolderIndex.setText(MessageList.get(position).getDescription());
        numberViewHolder.listItemNumberView.setText(MessageList.get(position).getTitle());

        switch (name){
            case "游戏小助手":
                numberViewHolder.CircleView.setImageResource(R.drawable.icon_micro_game_comment);
                numberViewHolder.imageView.setImageResource(R.drawable.im_icon_notice_official);
                break;
            case"抖音小助手":
                numberViewHolder.CircleView.setImageResource(R.drawable.session_robot);
                numberViewHolder.imageView.setImageResource(R.drawable.im_icon_notice_official);
                break;
            case "陌生人消息":
                numberViewHolder.CircleView.setImageResource(R.drawable.session_stranger);
                break;
            case "系统消息":
                numberViewHolder.CircleView.setImageResource(R.drawable.session_system_notice);
                numberViewHolder.imageView.setImageResource(R.drawable.im_icon_notice_official);
                break;
            default:
                numberViewHolder.CircleView.setImageResource(R.drawable.icon_girl);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView viewHolderIndex;
        private final TextView listItemNumberView;
        private final TextView TimeView;
        private final CircleImageView CircleView;
        private final ImageView imageView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_title);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_description);
            TimeView = (TextView) itemView.findViewById(R.id.tv_time);
            CircleView = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            imageView = (ImageView)itemView.findViewById(R.id.robot_notice);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            listItemNumberView.setText(String.valueOf(position));


            viewHolderIndex.setText(String.format("ViewHolder index: %s", getAdapterPosition()));
           //int backgroundColorForViewHolder = getViewHolderBackgroundColorFromInstance(itemView.getContext(), getAdapterPosition() % 10);
            itemView.setBackgroundColor(Color.BLACK);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
