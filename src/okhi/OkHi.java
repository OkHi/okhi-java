package okhi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class OkHi {
  private OkHiAuth auth;
  private OkHiUser user;
  private OkHiLocationHandler okHiLocationHandler;
  private OkHiDevMode devMode;
  private OkHiStyle style;
  private OkHiLaunchMode launchMode;
  private String SANDBOX_URL = "https://manager-v4.okhi.dev";
  private String PRODUCTION_URL = "https://manager-v4.okhi.co";
  private String SANDBOX_NAMESPACE = "com.develop.okhiJavaLib.okhi";
  private String PRODUCTION_NAMESPACE = "com.production.okhiJavaLib.okhi";
  private String NAME = "okhiJavaLib";
  private String VERSION = "1.0.0";
  private String BUILD = "1";
  private String NAMESPACE;
  private String URL;
  private boolean debug = false;

  public OkHi(OkHiAuth auth, OkHiUser user, OkHiStyle style, OkHiLaunchMode launchMode, OkHiDevMode devMode, OkHiLocationHandler okHiLocationHandler) {
    this.auth = auth;
    this.user = user;
    this.okHiLocationHandler = okHiLocationHandler;
    this.devMode = devMode;
    this.style = style;
    this.launchMode = launchMode;
    if(devMode == OkHiDevMode.PRODUCTION) {
      this.URL = PRODUCTION_URL;
      this.NAMESPACE = PRODUCTION_NAMESPACE;
    } else {
      this.URL = SANDBOX_URL;
      this.NAMESPACE = SANDBOX_NAMESPACE;
    }
  }

  public Parent locationManager() {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    webEngine.setJavaScriptEnabled(true);
    webView.setContextMenuEnabled(true);
    webEngine.load(URL);
    OkHiLocationManagerApp okhiLocationManagerApp = new OkHiLocationManagerApp(okHiLocationHandler);

    webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
      @Override
      public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.FAILED) {
          OkHiError error = new OkHiError("fatal_exit", "Unable to launch Webview. Check network connection");
          okHiLocationHandler.onError(error);
          return;
        }

        if (newValue == Worker.State.SUCCEEDED) {
          JSObject window = (JSObject) webEngine.executeScript("window");
          window.setMember(okhiLocationManagerApp.name, okhiLocationManagerApp);
          webEngine.executeScript("startOkHiLocationManager("+okhiLocationManagerApp.name+", "+generateStartPayload()+")");

          if(debug) {
            webEngine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");
          }
        }
      }
    });

    return webView;
  }

  private String generateStartPayload() {
    String message = launchMode.name().toLowerCase();
    JSONObject output = new JSONObject();
    JSONObject payload = new JSONObject();
    JSONObject parent = new JSONObject();

    parent.put("name", NAME);
    parent.put("version", VERSION);
    parent.put("build", BUILD);
    parent.put("namespace", NAMESPACE);

    payload.put("user", user.toJSONObject());
    payload.put("auth", auth.toJSONObject());
    payload.put("style", style.toJSONObject());
    payload.put("parent", parent);

    output.put("message", message);
    output.put("payload", payload);
    return output.toString();
  }


}
