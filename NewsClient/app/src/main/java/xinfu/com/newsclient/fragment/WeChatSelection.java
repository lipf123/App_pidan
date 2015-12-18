package xinfu.com.newsclient.fragment;
/**
 * .   如果你认为你败了，那你就一败涂地；
 * .   如果你认为你不敢，那你就会退缩；
 * .   如果你想赢但是认为你不能；
 * .   那么毫无疑问你就会失利；
 * .   如果你认为你输了，你就输了；
 * .   我们发现成功是从一个人的意志开始的；
 * .   成功是一种心态。
 * .   生活之战中，
 * .   胜利并不属于那些更强和更快的人，
 * .   胜利者终究是认为自己能行的人。
 * .
 * .   If you think you are beaten,you are;
 * .   If you think you dare not,you don't;
 * .   If you can to win but think you can't;
 * .   It's almost a cinch you won't.
 * .   If you think you'll lose,you're lost;
 * .   For out of the world we find Success begins with a fellow's will;
 * .   It's all in a state of mind.
 * .   Life's battles don't always go to the stronger and faster man,But sooner or later the man who wins Is the man who thinks he can.
 * .
 * .   You can you do.  No can no bb.
 * .
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import xinfu.com.newsclient.R;
import xinfu.com.newsclient.adapter.MyAdapter;
import xinfu.com.newsclient.data.Data_WeChatSelection;
import xinfu.com.newsclient.listener.GetDataListener;
import xinfu.com.newsclient.utils.GetDataFromService;
import xinfu.com.newsclient.utils.Util;
import xinfu.com.pidanview.alerterview.progress.SVProgressHUD;


/**
 * 项目名称： NewsClient
 * 创建日期： 2015/12/18  9:26
 * 项目作者： 赵文贇
 * 项目包名： xinfu.com.newsclient.fragment
 */
public class WeChatSelection extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private final String AppKey = "ba6a565b20139239ec4dd6f46f5540f8";
    private final String url = "http://v.juhe.cn/weixin/query";
    private ListView listView_WeChat_Selection;
    private MyAdapter<Data_WeChatSelection> adapter;
    private SVProgressHUD svProgressHUD = null;
    private int position_images = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wechat, null);
        initView();
        initData();
        return view;
    }

    private void initData() {

        GetDataFromService client = new GetDataFromService(url);
        client.put("key", AppKey);
        client.put("ps", 20);
//        if (svProgressHUD != null) {
//            svProgressHUD.dismiss(getActivity());
//        }
        client.doGet(new GetDataListener() {
            @Override
            public void onSucc(byte[] data) {
                xLog("Succ:" + new String(data));
                if (svProgressHUD != null) {
                    //
                    svProgressHUD.dismiss(getActivity());
                }
                try {
                    JSONObject all = new JSONObject(new String(data));
                    if (all.getInt("error_code") == 0) {
                        spiltsData(all.get("result").toString());
                    } else {

                        Toast.makeText(getActivity(), all.getString("reason"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "数据解析异常", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (svProgressHUD != null) {
                    svProgressHUD.dismiss(getActivity());
                }
                xLog("Error," + errorMsg);
            }
        });
    }

    private void spiltsData(String re) {
        try {
            JSONObject result = new JSONObject(re);
            int ps = result.getInt("ps");
            JSONArray list = new JSONArray(result.get("list").toString());
            for (int i = 0; i < list.length(); i++) {
                xLog(list.get(i).toString());
                Data_WeChatSelection data_WeChat = new Gson().fromJson(list.get(i).toString(), Data_WeChatSelection.class);
                adapter.addItem(data_WeChat);
                adapter.notifyDataSetChanged();
                getImageFromInternet(position_images, data_WeChat.getFirstImg());
                position_images++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getImageFromInternet(final int position_images, final String firstImg) {
        GetDataFromService client = new GetDataFromService(firstImg);
        client.doGet(new GetDataListener() {
            @Override
            public void onSucc(byte[] data) {
                xLog("get Bitmap Succ");
                adapter.getItem(position_images).setBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg) {
                xLog("Error,get bitmap time out," + errorMsg);
            }
        });
    }

    private void initView() {
        svProgressHUD = new SVProgressHUD();
        svProgressHUD.showWithStatus(getActivity(), "正在加载最新数据……");
        listView_WeChat_Selection = (ListView) view.findViewById(R.id.listView_WeChat_Selection);
        adapter = new MyAdapter<Data_WeChatSelection>(getActivity(), R.layout.item_wechatselection) {
            @Override
            public void initGetView(int position, View convertView, ViewGroup parent) {
//                if (svProgressHUD != null) {
//                    svProgressHUD.dismiss(getActivity());
//                }
                ImageView imageView = (ImageView) convertView.findViewById(R.id.im_wechat_images);

                TextView tv_wechat_title = (TextView) convertView.findViewById(R.id.tv_wechat_title);
                TextView tv_wechat_source = (TextView) convertView.findViewById(R.id.tv_wechat_source);

                tv_wechat_title.setText(getItem(position).getTitle());
                tv_wechat_source.setText(getItem(position).getSource());

                imageView.setImageBitmap(getItem(position).getBitmap());

            }
        };
        listView_WeChat_Selection.setAdapter(adapter);
        listView_WeChat_Selection.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WeChat_WebView weChat_webView = new
                WeChat_WebView();
        Bundle bundle = new Bundle();

        bundle.putString("url", adapter.getItem(position).getUrl());
        weChat_webView.setArguments(bundle);
        FragmentTransaction y = getFragmentManager().beginTransaction();
        y.addToBackStack("WebView");
        y.replace(R.id.mainView, weChat_webView).commit();
        xLog(adapter.getItem(position).getUrl());
    }

    @Override
    public void onResume() {
        position_images = 0;
        super.onResume();
    }

    public void xLog(String msg) {
        Log.w("NewsClient------->>>>>>", msg);
    }
}
