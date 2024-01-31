package com.example.guru2_github

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        // 좌우 스크롤을 가능하게 함
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL

        // JavaScript에서 viewport 설정 변경
        webView.loadUrl("javascript:document.querySelector('meta[name=viewport]').setAttribute('content', 'width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes');")

        val url = intent.getStringExtra("url") ?: ""
        webView.loadUrl(url)
    }
}