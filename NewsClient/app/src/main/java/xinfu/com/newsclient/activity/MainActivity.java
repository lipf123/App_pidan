package xinfu.com.newsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import xinfu.com.newsclient.R;
import xinfu.com.newsclient.fragment.NewsHome;
import xinfu.com.newsclient.fragment.TVTimeTableHome;
import xinfu.com.newsclient.fragment.TrainsTimeTableHome;
import xinfu.com.newsclient.fragment.WeatherHome;
import xinfu.com.newsclient.fragment.YoudaoHome;
import xinfu.com.pidanview.alerterview.alerterview.AlertView;
import xinfu.com.pidanview.alerterview.alerterview.OnItemClickListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout tv_newsHome, tv_weatherHome, tv_trainsTimeTable, tv_tvTimeTable, tv_youdao,tv_video,wechat;
    private NewsHome newsHome = null;
    private WeatherHome weatherHome = null;
    private TrainsTimeTableHome trainsTimeTableHome = null;
    private TVTimeTableHome tvTimeTableHome = null;
    private YoudaoHome youdaoHome = null;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.cool);
        toolbarLayout.setTitle("贴近生活     _皮蛋");
        toolbarLayout.setTitleEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mainView = R.id.mainView;
//        xLog("开始发送");
//        Intent data=new Intent(Intent.ACTION_SENDTO);
//        data.setData(Uri.parse("mailto:3648415@qq.com"));
//        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
//        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
//        startActivity(data);
//        xLog("发送完成");
        newsHome = new NewsHome();
        weatherHome = new WeatherHome();
        trainsTimeTableHome = new TrainsTimeTableHome();
        tvTimeTableHome = new TVTimeTableHome();
        youdaoHome = new YoudaoHome();

        tv_newsHome = (LinearLayout) findViewById(R.id.tv_newsHome);
        tv_weatherHome = (LinearLayout) findViewById(R.id.tv_weatherHome);
        tv_trainsTimeTable = (LinearLayout) findViewById(R.id.tv_trainsTimeTable);
        tv_tvTimeTable = (LinearLayout) findViewById(R.id.tv_tvTimeTable);
        tv_youdao = (LinearLayout) findViewById(R.id.tv_youdao);
        tv_video= (LinearLayout) findViewById(R.id.tv_video);
        wechat= (LinearLayout) findViewById(R.id.wechat);

        tv_newsHome.setOnClickListener(this);
        tv_weatherHome.setOnClickListener(this);
        tv_trainsTimeTable.setOnClickListener(this);
        tv_tvTimeTable.setOnClickListener(this);
        tv_youdao.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        wechat.setOnClickListener(this);
//        Util.getInfo(this);

//        xLog(Util.getAllApp(this));
//        xLog(Util.getSmsInPhone(this));
//        xLog(Util.getProvidersName(this));
//        repleace(newsHome);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Activity_Parent.class);
        switch (v.getId()) {
            case R.id.tv_newsHome:
                intent.putExtra("id", 1);
                break;
            case R.id.tv_weatherHome:
                intent.putExtra("id", 2);
                break;
            case R.id.tv_trainsTimeTable:
                intent.putExtra("id", 3);
                break;
            case R.id.tv_tvTimeTable:
                intent.putExtra("id", 4);
                break;
            case R.id.tv_youdao:
                intent.putExtra("id", 5);
                break;
            case R.id.tv_video:
                intent.putExtra("id", 6);
                break;
            case R.id.wechat:
                intent.putExtra("id", 7);
                break;

        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            getFragmentManager().popBackStack();
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                new AlertView("提示", "确定退出吗？", "再看看", new String[]{"退出"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        xLog(position + "===========");
                        if (position == 0) {    //
                            finish();
                        }
                    }
                }).show();
            }

        }
        return false;
    }


    public void xLog(String msg) {
        Log.w("NewsClient------->>>>>>", msg);
    }
}
