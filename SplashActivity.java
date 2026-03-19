package com.huahui.company;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.huahui.company.activity.login.LoginActivity;
import com.huahui.company.activity.multiplex.HtmlDeailActivity;
import com.huahui.company.common.PushMessageReceiver;
import com.huahui.company.common.PushMessageService;
import com.huahui.company.model.HttpServer;
import com.huahui.company.util.BaseUtil;
import com.huahui.company.util.BaseUtils;
import com.huahui.company.util.SerializableMap;
import com.huahui.company.util.SharedPClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initMainView() {
        if (BaseUtils.isEmpty(SharedPClass.getParam(SharedPClass.FIRSTTIME,this))){
            showAgreementWindow();
        }else {
            goNextActivity();
        }
    }

    //协议弹框提示
    public void showAgreementWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        View contentView = LayoutInflater.from(SplashActivity.this).inflate(R.layout.popuplayout_agreement, null);
        builder.setView(contentView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
        TextView tv_agree = contentView.findViewById(R.id.tv_agree);

        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableStringBuilder spannable = new SpannableStringBuilder(tv_content.getText());

        int startIndex = 0;
        int endIndex = 0;
        int startIndex2 = 0;
        int endIndex2 = 0;
        startIndex = 88;
        endIndex = startIndex + 6;
        startIndex2 = 95;
        endIndex2 = startIndex2 + 6;
        spannable.setSpan(new AgreementTextClick(), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new PolicyTextClick(), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_content.setText(spannable);
        tv_content.setHighlightColor(Color.parseColor("#00ffffff"));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        tv_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.getInstance().initAllSdkApp();
                new SharedPClass(SharedPClass.FIRSTTIME,"1",SplashActivity.this);
                goNextActivity();
            }
        });
        dialog.show();
        Button btnPos = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button btnNeg = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        btnPos.setTextColor(getResources().getColor(R.color.orange0));
        btnNeg.setTextColor(getResources().getColor(R.color.orange0));
    }

    private class AgreementTextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) { //在此处理点击事件
            if (BaseUtil.isFastDoubleClick()) {
                return;
            }
            toAgreementType(HttpServer.agreeMnetUrl+14);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(Color.parseColor("#F86B24"));
        }
    }

    private class PolicyTextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) { //在此处理点击事件
            if (BaseUtil.isFastDoubleClick()) {
                return;
            }
            toAgreementType(HttpServer.agreeMnetUrl+15);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(Color.parseColor("#F86B24"));
        }
    }

    private void toAgreementType(String webUrl)
    {
        NetWorkCallbackInterface netWorkCallbackInter = result -> {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Intent intent = new Intent(baseContext, HtmlDeailActivity.class);
                intent.putExtra("title",jsonObject.getString("name"));
                intent.putExtra("webUrl",jsonObject.getString("url"));
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        sendDataGetServier(webUrl,true,netWorkCallbackInter);
    }

    private void goNextActivity() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            //判断登录等状态的
            String login = SharedPClass.getParam(SharedPClass.HUA_HUI_LOGIN,baseContext);
            if (BaseUtil.isNullString(login))
            {
                newTaskActivity(LoginActivity.class);
                return;
            }

            Intent it = new Intent(baseContext, MainActivity.class);
            if (getIntent().hasExtra(PushMessageService.jpushNotice))
            {
                SerializableMap tempMap = (SerializableMap) getIntent().getExtras().get(PushMessageService.jpushNotice);
                HashMap hashMap = tempMap.getHashMap();
                it.putExtras(baseContext.creaMapBundle(PushMessageService.jpushNotice, hashMap));
            }
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }, 1000);
    }
}
