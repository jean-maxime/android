package net.hetic.hetweetic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import net.hetic.hetweetic.Models.Tweet;
import java.util.ArrayList;


public class TweetAdapter extends BaseAdapter {


    private ArrayList<Tweet> mListTweet;
    private LayoutInflater mLayoutinflater;
    private ImageLoader mImageLoader;


    public TweetAdapter(Context context, ArrayList<Tweet> listTweet){
        mListTweet = listTweet;
        mLayoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return mListTweet.size();
    }

    @Override
    public Object getItem(int position) {
        return mListTweet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){

            convertView = (LinearLayout) mLayoutinflater.inflate(R.layout.item_list, null);

            holder = new ViewHolder();
            holder.user_pictureIV = (ImageView) convertView.findViewById(R.id.user_picture);
            holder.usernameTV = (TextView) convertView.findViewById(R.id.username);
            holder.tweetTV = (TextView) convertView.findViewById(R.id.tweet);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Tweet currentTweet = mListTweet.get(position);

        mImageLoader.displayImage(currentTweet.getProfileImageUrl(), holder.user_pictureIV);
        holder.usernameTV.setText(currentTweet.getUserName());
        holder.tweetTV.setText(currentTweet.getText());

        return convertView;

    }

    public class ViewHolder{
        ImageView user_pictureIV;
        TextView usernameTV;
        TextView tweetTV;
    }
}
