package in.jgrservices.jgrrepairingandservices;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    private WebView mywebView;


RelativeLayout relativeLayout;
Button NointernetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mywebView.getSettings();
        mywebView.loadUrl("https://jgrservices.in/");
        webSettings.setJavaScriptEnabled(true);
        mywebView.setWebViewClient(new WebViewClient());

    NointernetBtn= (Button) findViewById(R.id.btnRetry);
    relativeLayout= (RelativeLayout) findViewById(R.id.nointernet);
    internetcheck();

        NointernetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internetcheck();
            }
        });



    }

    public void callNow(View view) {
        String uri= "tel: "+" 7999930643";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }


    @Override
    public void onBackPressed() {
        if (mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void internetcheck(){

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobiledata = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);



        if(mobiledata.isConnected()){
            mywebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mywebView.reload();


        }

        else if(wifi.isConnected()){

            mywebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mywebView.reload();
        }

        else{

            mywebView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);

        }
    }


}
