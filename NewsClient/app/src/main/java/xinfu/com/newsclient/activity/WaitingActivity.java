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
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

import xinfu.com.newsclient.R;
import xinfu.com.newsclient.fragment.Video;

/**
 * 项目名称： NewsClient
 * 创建日期： 2015/12/17  9:19
 * 项目作者： 赵文贇
 * 项目包名： xinfu.com.newsclient.activity
 */

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class WaitingActivity extends Activity {
    Tencent mTencent;
    IUiListener iUiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);
        StatConfig.setDebugEnable(true);
        StatService.trackCustomEvent(this, "onCreate", "");
        String open_appid = "1104950333";
        // androidManifest.xml指定本activity最先启动
        // 因此，MTA的初始化工作需要在本onCreate中进行
        // 在startStatService之前调用StatConfig配置类接口，使得MTA配置及时生效
//        StatConfig.setAppKey(open_appid);
        // 初始化并启动MTA
        // 第三方SDK必须按以下代码初始化MTA，其中appkey为规定的格式或MTA分配的代码。
        // 其它普通的app可自行选择是否调用
        try {
            // 第三个参数必须为：com.tencent.stat.common.StatConstants.VERSION
            StatService.startStatService(this, open_appid,
                    com.tencent.stat.common.StatConstants.VERSION);

            mTencent = Tencent.createInstance(open_appid, this.getApplicationContext());
            iUiListener = new BaseUiListener();

        } catch (Throwable e) {
            // MTA初始化失败
            xLog("Error");
        }

    }

    public void onClick(View view) {
        String SCOPE = "get_user_info,add_t";
        mTencent.login(this, SCOPE, iUiListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    public void xLog(String msg) {
        Log.w("NewsClient------->>>>>>", msg);
    }
//    private void doLogin() {
//        IUiListener listener = new BaseUiListener() {
//            @Override
//            protected void doComplete(JSONObject values) {
//                updateLoginButton();
//            }
//        };
//        mTencent.login(this, SCOPE, listener);
//    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档
//            doComplete(response);

            xLog(response.toString());
        }

        protected void doComplete(JSONObject values) {
            xLog(values.toString());
        }

        @Override
        public void onError(UiError e) {
//            showResult("onError:", "code:" + e.errorCode + ", msg:"
//                    + e.errorMessage + ", detail:" + e.errorDetail);
            xLog(e.errorMessage);
        }

        @Override
        public void onCancel() {
//            showResult("onCancel", "");
            xLog("cancle");
        }
    }
}
