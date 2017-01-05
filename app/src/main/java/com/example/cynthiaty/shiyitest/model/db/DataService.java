package com.example.cynthiaty.shiyitest.model.db;

import com.example.cynthiaty.shiyitest.model.entity.Magazine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：尚萍萍
 * 功能：获取源数据
 * 时间：2017-1-5
 */
public class DataService {
    private final String serverUrl = "http://thoughtworks-ios.herokuapp.com/facts.json";
    public static List<Magazine> magazines = new ArrayList<>();

    /**
     * 调用GET方式发送请求
     */
    public void run() throws Exception {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(serverUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                try {
                    JSONObject jo = new JSONObject(response.body().string());
                    JSONArray ja = jo.getJSONArray("rows");
                    for (int i = 0; i < ja.length(); i++) {
                        if (!ja.getJSONObject(i).getString("title").equals("null")) {
                            magazines.add(new Magazine(ja.getJSONObject(i).getString("title"),
                                    ja.getJSONObject(i).getString("description"),
                                    ja.getJSONObject(i).getString("imageHref")));
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
