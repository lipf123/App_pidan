package xinfu.com.newsclient.activity;
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

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import xinfu.com.newsclient.R;
import xinfu.com.newsclient.fragment.NewsHome;
import xinfu.com.newsclient.fragment.TVTimeTableHome;
import xinfu.com.newsclient.fragment.TrainsTimeTableHome;
import xinfu.com.newsclient.fragment.Video;
import xinfu.com.newsclient.fragment.WeChatSelection;
import xinfu.com.newsclient.fragment.WeatherHome;
import xinfu.com.newsclient.fragment.YoudaoHome;

/**
 * 项目名称： NewsClient
 * 创建日期： 2015/12/15  14:44
 * 项目作者： 赵文贇
 * 项目包名： xinfu.com.newsclient.activity
 */
public class Activity_Parent extends Activity {
    private int mainView = 0;

    private List<Fragment> fragmentList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent);
        initData();
//        switch ()
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsHome());
        fragmentList.add(new WeatherHome());
        fragmentList.add(new TrainsTimeTableHome());
        fragmentList.add(new TVTimeTableHome());
        fragmentList.add(new YoudaoHome());
        fragmentList.add(new Video());
        fragmentList.add(new WeChatSelection());
        mainView = R.id.mainView;
//        newsHome = ;
//        weatherHome = ;
//        trainsTimeTableHome = ;
//        tvTimeTableHome =;
//        youdaoHome =;
        switch (getIntent().getIntExtra("id", 0)) {
            case 1:
                if (!fragmentList.get(0).isVisible()) {
                    repleace(fragmentList.get(0));
                }
                break;
            case 2:
                if (!fragmentList.get(1).isVisible()) {
                    repleace(fragmentList.get(1));
                }
                break;
            case 3:
                if (!fragmentList.get(2).isVisible()) {
                    repleace(fragmentList.get(2));
                }
                break;
            case 4:
                if (!fragmentList.get(3).isVisible()) {
                    repleace(fragmentList.get(3));
                }
                break;
            case 5:
                if (!fragmentList.get(4).isVisible()) {
                    repleace(fragmentList.get(4));
                }
                break;
            case 6:
                if (!fragmentList.get(5).isVisible()) {
                    repleace(fragmentList.get(5));
                }
                break;
            case 7:
                if (!fragmentList.get(6).isVisible()) {
                    repleace(fragmentList.get(6));
                }
                break;
        }
    }

    private void repleace(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(mainView, fragment).commit();
    }

}
