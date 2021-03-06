package grup.developer.agung.webview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener  {
    private AdvancedWebView mWebView;
    private ProgressDialog progressDialog;
    private TextView errorMessage;
    private Button reload;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        view = (View)findViewById(R.id.background);
        errorMessage = (TextView)findViewById(R.id.error_message);
        errorMessage.setVisibility(View.GONE);
        reload = (Button)findViewById(R.id.reload);
        reload.setVisibility(View.GONE);
        //load website
        loadWeb();
    }

    private void loadWeb(){
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("http://dewiariyanti105.000webhostapp.com/");

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressDialog.setMessage("Loading.. " + progress + "%" );
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);

                if (progress == 100) {
                    progressDialog.dismiss();
                    mWebView.setVisibility(View.VISIBLE);
                } else {
                    progressDialog.show();
                    mWebView.setVisibility(View.GONE);
                }
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        view.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
        reload.setVisibility(View.VISIBLE);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.GONE);
                errorMessage.setVisibility(View.GONE);
                reload.setVisibility(View.GONE);
                loadWeb();
            }
        });
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
