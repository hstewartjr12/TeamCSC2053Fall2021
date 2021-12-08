package com.example.project3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetNums extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mFact;

    GetNums(TextView Fact) {
        this.mFact = new WeakReference<>(Fact);
    }

    protected String getNumFact(String query) throws IOException {
        //Google Books API URL
        String apiURL = "http://numbersapi.com/";
        //Append query
        // String apiURL = "https://quotable.io/quotes?page=1";
        apiURL = apiURL + query;

        //Make connection to API
        URL requestURL = new URL(apiURL);
        HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        //Receive the response
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        //Create a String with the reponse
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        String jsonString = builder.toString();
        Log.d("GetNumsTagJsonString", jsonString);
        return jsonString;
    }


    @Override
    protected String doInBackground(String... strings) {
        String jsonString= null;
        try {
            jsonString = getNumFact(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String fact;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
                fact = jsonObject.getString("text");
                if (fact != null) {
                    mFact.get().setText(fact);
                } else {
                    mFact.get().setText("No Results");
                }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}
