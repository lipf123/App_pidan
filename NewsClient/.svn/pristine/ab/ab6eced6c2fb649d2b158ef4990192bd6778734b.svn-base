package xinfu.com.newsclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xinfu.com.newsclient.R;
import xinfu.com.newsclient.adapter.MyAdapter;
import xinfu.com.newsclient.data.Data_TV_Final;

/**
 * Created by Administrator on 2015/12/14.
 */
public class TVTime_Final extends Fragment {
    private View view;
    private ListView listView_TV_Final;
    private String id;
    private MyAdapter<Data_TV_Final> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tvtimefinal, null);
        initVIew();
        initData();
        return view;
    }

    private void initData() {
        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

        RequestParams params = new RequestParams();

        params.put("key", "9273a0ef78e31f8428200cca1eb407fd");
        params.put("code", id);

        client.get("http://japi.juhe.cn/tv/getProgram", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                xLog(new String(bytes));
                try {
                    JSONObject jsonObject = new JSONObject(new String(bytes));
                    if (jsonObject.getString("error_code").equals("0")) {
                        JSONArray jsonArray = new JSONArray(jsonObject.get("result").toString());
                        for (int j = 0; j < jsonArray.length(); j++) {
                            xLog(jsonArray.get(j).toString());
                            Data_TV_Final data_tv_final = new Gson().fromJson(jsonArray.get(j).toString(), Data_TV_Final.class);
                            adapter.addItem(data_tv_final);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    private void initVIew() {
        listView_TV_Final = (ListView) view.findViewById(R.id.listView_TV_Final);
        id = getArguments().getString("id");
        adapter = new MyAdapter<Data_TV_Final>(getActivity(), R.layout.item_tv_tv_final) {
            @Override
            public void initGetView(int position, View convertView, ViewGroup parent) {
                TextView tv_cName = (TextView) convertView.findViewById(R.id.tv_cName);
                TextView tv_pName = (TextView) convertView.findViewById(R.id.tv_pName);
                TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                android.webkit.WebView tv_webview = (android.webkit.WebView) convertView.findViewById(R.id.tv_webview);

                tv_cName.setText(getItem(position).getcName());
                tv_pName.setText(getItem(position).getpName());
                tv_time.setText(getItem(position).getTime());

                tv_webview.loadUrl(getItem(position).getpUrl());

            }
        };
        listView_TV_Final.setAdapter(adapter);
    }


    public void xLog(String msg) {

        Log.w("NewsClient------->>>>>>", msg);
    }
}
