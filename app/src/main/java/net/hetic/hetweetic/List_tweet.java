package net.hetic.hetweetic;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import net.hetic.hetweetic.Models.Tweet;
import net.hetic.hetweetic.Utils.Config;
import net.hetic.hetweetic.Webservices.Parser;
import net.hetic.hetweetic.Webservices.Webservices;

import org.apache.http.Header;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link List_tweet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link List_tweet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List_tweet extends Fragment {

    ListView mList;


    public List_tweet() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tweet, container, false);
    }

    @Override
    public void onResume() {
        updateData();

        super.onResume();
    }

    private void updateData(){

        Bundle tweetBundle = getArguments();

        Webservices.getTimeline(tweetBundle.getString("Username"), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = new String(bytes);
                Log.i(Config.LOG_PREFIX, "getTimeline response " + new String(bytes));

                try {
                    ArrayList<Tweet> tweetList = Parser.parseTimelineResponse(response);

                    sendTweetParameters(tweetList);


                } catch (JSONException e) {
                    if (Config.DISPLAY_LOG)
                        Log.e(Config.LOG_PREFIX, "Erreur lors du passage des films");
                }


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                Log.e(Config.LOG_PREFIX, "getTimeline Failed => response " + new String(bytes));

            }
        });
    }


    private void sendTweetParameters(final ArrayList<Tweet> tweetList){
        mList = (ListView) getView().findViewById(R.id.list);

        TweetAdapter tweetAdapter = new TweetAdapter(getActivity(), tweetList);

        mList.setAdapter(tweetAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DescriptionTweetFragment descriptionTweetFragment = new DescriptionTweetFragment();

                Tweet clickedTweet = tweetList.get(position);
                Bundle tweetBundle = new Bundle();

                tweetBundle.putString("user_picture", clickedTweet.getProfileImageUrl());
                tweetBundle.putString("username", clickedTweet.getUserName());
                tweetBundle.putString("tweet", clickedTweet.getText());

                descriptionTweetFragment.setArguments(tweetBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, descriptionTweetFragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
    }

}
