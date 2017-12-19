package com.example.thangpham.youtubeplaylistnhap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity {
    private static final String TAG = "MainActivity";
    ListView lvListVideo;
    VideoAdapter adapter;
    ArrayList<VideoInfo> listVideo;
    public static String API_KEY="AIzaSyBdtPb2XWTtcpZo0m6z4PVt4_TL6sQBwjs";
    String ID_PLAYLIST="PLtDPprntafp0AzMXD8sPc3wyW1Me9OiC_";
    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST+"&key="+API_KEY+"&maxResults=50";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listVideo = new ArrayList<>();
        lvListVideo = findViewById(R.id.lv_ListVideo);
         adapter = new VideoAdapter(listVideo,R.layout.item_list_video,this);
        lvListVideo.setAdapter(adapter);
        lvListVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PlayListActivity.class);
                intent.putExtra("videoId",listVideo.get(i).getVideoId());
                startActivity(intent);
            }
        });
        // khởi tạo 1 hàng đợi request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // yêu cầu trả về 1 json object từ URL đó( ban đầu là string thì yêu cầu trả về StringRequest, bđ là JsonObject thì trả vè JsonObjectRequest)
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("items");
                    for(int i=0;i<jsonItems.length();i++)
                    {
                        JSONObject item = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = item.getJSONObject("snippet");
                        String title = jsonSnippet.getString("title");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        String url = jsonMedium.getString("url");
                        JSONObject jsonResoureId = jsonSnippet.getJSONObject("resourceId");
                        String videoId = jsonResoureId.getString("videoId");
                        Log.d(TAG, "onResponse: "+title+" "+url+" "+videoId);
                        listVideo.add(new VideoInfo(title,url,videoId));

                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
            }
        });
        // add request vào hàng đợi để gửi lên server
        requestQueue.add(jsonObjectRequest);



    }
}
