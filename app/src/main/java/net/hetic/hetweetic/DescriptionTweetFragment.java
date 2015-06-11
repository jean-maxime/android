package net.hetic.hetweetic;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.hetic.hetweetic.Models.Tweet;


public class DescriptionTweetFragment extends Fragment {

    private ImageLoader mImageLoader;

    public DescriptionTweetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On lui donne la vue associé
        return inflater.inflate(R.layout.fragment_description_tweet, container, false);
    }

    @Override
    public void onResume() {

        Bundle movieBundle = getArguments();

        Tweet tweet = new Tweet();
        tweet.setProfileImageUrl(movieBundle.getString("user_picture"));
        tweet.setUserName(movieBundle.getString("username"));
        tweet.setText(movieBundle.getString("tweet"));

        ImageView userPicture = (ImageView) getView().findViewById(R.id.user_picture);
        TextView userName = (TextView) getView().findViewById(R.id.username);
        TextView tweetDisplay = (TextView) getView().findViewById(R.id.tweet);

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.displayImage(tweet.getProfileImageUrl(), userPicture);
        userName.setText(tweet.getUserName());
        tweetDisplay.setText(tweet.getText());

        super.onResume();
    }

}
