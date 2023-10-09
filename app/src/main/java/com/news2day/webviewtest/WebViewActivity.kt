package com.news2day.webviewtest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news2day.webviewtest.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view )
        val url = "https://forms.gle/fkk1mrQYzfosonMJ7"
        //val url = "https://www.google.co.in/"

        binding.wvWebView.settings.javaScriptEnabled = true
        binding.wvWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        binding.wvWebView.loadUrl( url )
        object : WebViewClient (){

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                var link  = request?.url.toString()
                Log.i("link", link)
                if(link.startsWith("http://") || link.startsWith("https://"))
                    return false;
                else
                {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
            }
        }.also { binding.wvWebView.webViewClient = it }

    }
}