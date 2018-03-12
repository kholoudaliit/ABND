package com.example.kholoudali.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final int NEWS_LOADER = 18;
    private static final String NEWS_URL = "https://content.guardianapis.com/search";
    private HttpURLConnection urlConnection;
    private URL url;
    private ProgressBar pb;
    private ListView NewsListView;
    private ArrayList<News> NewsList;

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<News> ParseResponse(String ResonseJSON) throws JSONException, ParseException {
        ArrayList<News> newsList = new ArrayList<News>();

        if (ResonseJSON.isEmpty())
            return null;

        JSONObject mainObj = new JSONObject(ResonseJSON).getJSONObject("response");
        JSONArray results = mainObj.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            News news = new News();
            JSONObject newsObject = results.getJSONObject(i);
            news.setArticle_name(newsObject.getString("webTitle"));
            news.setSection_name(newsObject.getString("sectionName"));
            news.setURL(newsObject.getString("webUrl"));

            if (!newsObject.getString("webPublicationDate").isEmpty()) {
                String stringdate = newsObject.getString("webPublicationDate");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(stringdate);
                news.setDate(date);
            }

            JSONArray tagsArray = newsObject.getJSONArray("tags");

            String Auther = " ";
            for (int j = 0; j < tagsArray.length(); j++) {

                JSONObject tagObj = tagsArray.getJSONObject(j);
                String firstName = tagObj.getString("firstName");
                String lastName = tagObj.getString("lastName");
                if (!firstName.isEmpty() || !lastName.isEmpty()) {
                    Auther = Auther + lastName + " " + firstName + ". ";
                }


            }
            news.setAuther(Auther);
            newsList.add(news);


        }

        return newsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progressBar);
        NewsListView = findViewById(R.id.news_list);

        //Check network connection
        ConnectivityManager ConnectMang = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = ConnectMang.getActiveNetworkInfo();
        if (netInfo == null) {
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
            return;
        }

        //Init the loader
        getSupportLoaderManager().initLoader(NEWS_LOADER, null, this);


        //Handle Click item
        NewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news_item = (News) adapterView.getItemAtPosition(i);
                if (!news_item.getURL().isEmpty()) {
                    Uri uri = Uri.parse(news_item.getURL());
                    Intent web_intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(web_intent);
                }
            }
        });
    }

    //Inflate Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Handle Select Menu Items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            public String loadInBackground() {
                String URL = getURL();
                String response = null;
                try {
                    response = RetriveDataFromURL(URL);
                    NewsList = ParseResponse(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();

                }
                return response;
            }

            @Override
            protected void onStartLoading() {
                //Show Progress bar
                pb.setVisibility(View.VISIBLE);
                forceLoad();
            }

        };
    }

    private String getURL() {
        //get SharedPrefrencess values
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String queryWord = sharedPreferences.getString(getString(R.string.settings_search_Key), getString(R.string.settings_search_default));
        String orderby = sharedPreferences.getString(getString(R.string.setting_order_by_key), getString(R.string.setting_orderby_defValue));

        //Build URI
        Uri mainURI = Uri.parse(NEWS_URL);
        Uri.Builder uriBuilder = mainURI.buildUpon();

        // Append query parameter
        if (!queryWord.isEmpty())
            uriBuilder.appendQueryParameter("q", queryWord);
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("order-by", orderby);
        uriBuilder.appendQueryParameter("api-key", "test");
        uriBuilder.appendQueryParameter("page-size", "30");
        //?q=kids&format=json&show-tags=contributor&api-key=test

        return uriBuilder.toString();
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        //Hide Progress bar
        pb.setVisibility(View.INVISIBLE);
        //set Empty View
        NewsListView.setEmptyView(findViewById(R.id.emptyListText));
        NewsAdapter adapter = new NewsAdapter(this, NewsList);
        NewsListView.setAdapter(adapter);


    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }

    public String RetriveDataFromURL(String Txturl) throws IOException {

        String Response = "";
        url = new URL(Txturl);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 200) {
            //Get the response from HttpURLConnection response
            InputStream input = new BufferedInputStream(urlConnection.getInputStream());
            //Read the stream and convert it to string
            Response = readFromStream(input);
        } else {
            Log.e("NewsApp", "Response Code: " + urlConnection.getResponseCode());

            return "";
        }

        return Response;
    }


}

