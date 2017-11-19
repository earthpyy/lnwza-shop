package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author SE-lnwza
 */
public class WebViewController {
    
    @FXML
    private WebView webView;
    
    public WebView getWebView() {
        return webView;
    }
    
    public WebEngine getWebEngine() {
        return webView.getEngine();
    }
    
    public void setURL(String url) {
        webView.getEngine().load(url);
    }
    
}
