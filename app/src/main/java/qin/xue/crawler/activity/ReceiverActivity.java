package qin.xue.crawler.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import qin.xue.crawler.library.LinkPreviewCallback;
import qin.xue.crawler.library.SourceContent;
import qin.xue.crawler.library.TextCrawler;


/**
 * Created by qinxue on 2018/5/7.
 */

public class ReceiverActivity extends Activity {
    private static final String TAG = "ReceiverActivity";
    private TextCrawler textCrawler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textCrawler = new TextCrawler();
        textCrawler.makePreview(new LinkPreviewCallback() {

            @Override
            public void onPre() {

            }

            @Override
            public void onPos(SourceContent sourceContent, boolean isNull) {
                Log.i(TAG, "onPos() title: " + sourceContent.getTitle());
                List<String> images = sourceContent.getImages();
                Log.i(TAG, "onPos() images.size(): = " + images.size());
                if (images != null && images.size() > 0) {
                    Log.i(TAG, "onPos() image0: = " + images.get(0));
                }
                Log.i(TAG, "onPos() description: = " + sourceContent.getDescription());
            }
        }, getUri());
    }

    public String getUri() {
        Intent intent = getIntent();
        Bundle bundle = null;
        CharSequence result = "";
        if (intent != null) {
            bundle = intent.getExtras();
        }
        if (bundle != null) {
            result = intent.getExtras().getCharSequence(Intent.EXTRA_TEXT);

        }
        Log.i(TAG, "getUri() uri =" + result);
        return result.toString();
    }
}
