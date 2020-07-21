package tak.saru.botw100guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  private SharedPreferences prefs;
  private WebView web;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    prefs = PreferenceManager.getDefaultSharedPreferences(this);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });

    AdView mAdView = findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
    );

    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);

    web = findViewById(R.id.webView);
    web.setWebViewClient(new WebViewClient(){
      public void onPageFinished(WebView view, String weburl){
        setState(weburl);
      }
    });
    web.getSettings().setJavaScriptEnabled(true);
    web.addJavascriptInterface(new WebAppInterface(this), "Android");
    String page = prefs.getString("lastPage", "Introduction.html");
    page = page.replaceAll("/[ ']|%20", "");
    setSelect(page);
    web.loadUrl("file:///android_asset/" + page);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();

    if(id == R.id.action_reset) {
      web.loadUrl("file:///android_asset/Introduction.html");
      SharedPreferences.Editor edit = prefs.edit();
      edit.putString("G1", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G2", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G3", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G4", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G5", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G6", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G7", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G8", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G9", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G10", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G11", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G12", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G13", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G14", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G15", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G16", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G17", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G18", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G19", "0,0,0,0,0,0,0,0");
      edit.putString("G20", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G21", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G22", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G23", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
      edit.putString("G24", "0,0,0,0,0");
      edit.commit();
      return true;
    }else if(id == R.id.action_save){
      prefs = PreferenceManager.getDefaultSharedPreferences(this);
      web = this.findViewById((R.id.webView));
      web.evaluateJavascript("javascript:getState();", new ValueCallback<String>(){
        @Override
        public void onReceiveValue(String value){
          saveState(value);
        }
      });
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public boolean onNavigationItemSelected(final MenuItem item){
    prefs = PreferenceManager.getDefaultSharedPreferences(this);
    web = (WebView)this.findViewById((R.id.webView));
    web.evaluateJavascript("javascript:getState();", new ValueCallback<String>(){
      @Override
      public void onReceiveValue(String value){
        saveState(value);
        int id = item.getItemId();
        switch(id){
          case R.id.guide0:
            web.loadUrl("file:///android_asset/Introduction.html");
            break;
          case R.id.guide1:
            web.loadUrl("file:///android_asset/01-Plateau1.html");
            break;
          case R.id.guide2:
            web.loadUrl("file:///android_asset/02-DP1.html");
            break;
          case R.id.guide3:
            web.loadUrl("file:///android_asset/03-Hateno1.html");
            break;
          case R.id.guide4:
            web.loadUrl("file:///android_asset/04-DP2.html");
            break;
          case R.id.guide5:
            web.loadUrl("file:///android_asset/05-Hateno2.html");
            break;
          case R.id.guide6:
            web.loadUrl("file:///android_asset/06-Lanaryu1.html");
            break;
          case R.id.guide7:
            web.loadUrl("file:///android_asset/07-Woodland1.html");
            break;
          case R.id.guide8:
            web.loadUrl("file:///android_asset/08-Ridgeland1.html");
            break;
          case R.id.guide9:
            web.loadUrl("file:///android_asset/09-Tabantha1.html");
            break;
          case R.id.guide10:
            web.loadUrl("file:///android_asset/10-Hebra1.html");
            break;
          case R.id.guide11:
            web.loadUrl("file:///android_asset/11-Eldin1.html");
            break;
          case R.id.guide12:
            web.loadUrl("file:///android_asset/12-Akkala1.html");
            break;
          case R.id.guide13:
            web.loadUrl("file:///android_asset/13-Central1.html");
            break;
          case R.id.guide14:
            web.loadUrl("file:///android_asset/14-Wasteland1.html");
            break;
          case R.id.guide15:
            web.loadUrl("file:///android_asset/15-Gerudo1.html");
            break;
          case R.id.guide16:
            web.loadUrl("file:///android_asset/16-Wasteland2.html");
            break;
          case R.id.guide17:
            web.loadUrl("file:///android_asset/17-ChampionsBallad.html");
            break;
          case R.id.guide18:
            web.loadUrl("file:///android_asset/18-Lanaryu2.html");
            break;
          case R.id.guide19:
            web.loadUrl("file:///android_asset/19-Hateno3.html");
            break;
          case R.id.guide20:
            web.loadUrl("file:///android_asset/20-Hylia1.html");
            break;
          case R.id.guide21:
            web.loadUrl("file:///android_asset/21-Faron1.html");
            break;
          case R.id.guide22:
            web.loadUrl("file:///android_asset/22-Central2.html");
            break;
          case R.id.guide23:
            web.loadUrl("file:///android_asset/23-Hyrule Castle.html");
            break;
          case R.id.guide24:
            web.loadUrl("file:///android_asset/24-PostGame.html");
            break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
      }
    });
    return true;
  }

  private void setState(String url){
    url = url.substring(22);
    url = url.replace("--", "-");
    String state;
    switch(url){
      case "01-Plateau1.html":
        setTitle("Great Plateau");
        state = prefs.getString("G1", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "02-DP1.html":
        setTitle("Dueling Peaks 1");
        state = prefs.getString("G2", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "03-Hateno1.html":
        setTitle("Hateno 1");
        state = prefs.getString("G3", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "04-DP2.html":
        setTitle("Dueling Peaks 2");
        state = prefs.getString("G4", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "05-Hateno2.html":
        setTitle("Hateno 2");
        state = prefs.getString("G5", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "06-Lanaryu1.html":
        setTitle("Lanaryu 1");
        state = prefs.getString("G6", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "07-Woodland1.html":
        setTitle("Woodland");
        state = prefs.getString("G7", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "08-Ridgeland1.html":
        setTitle("Ridgeland");
        state = prefs.getString("G8", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "09-Tabantha1.html":
        setTitle("Tabantha 1");
        state = prefs.getString("G9", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "10-Hebra1.html":
        setTitle("Hebra");
        state = prefs.getString("G10", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "11-Eldin1.html":
        setTitle("Eldin");
        state = prefs.getString("G11", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "12-Akkala1.html":
        setTitle("Akkala");
        state = prefs.getString("G12", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "13-Central1.html":
        setTitle("Central Hyrule 1");
        state = prefs.getString("G13", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "14-Wasteland1.html":
        setTitle("Wasteland 1");
        state = prefs.getString("G14", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "15-Gerudo1.html":
        setTitle("Gerudo Highlands");
        state = prefs.getString("G15", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "16-Wasteland2.html":
        setTitle("Wasteland 2");
        state = prefs.getString("G16", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "17-ChampionsBallad.html":
        setTitle("Champion's Ballad");
        state = prefs.getString("G17", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "18-Lanaryu2.html":
        setTitle("Lanaryu 2");
        state = prefs.getString("G18", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "19-Hateno3.html":
        setTitle("Hateno 3");
        state = prefs.getString("G19", "0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "20-Hylia1.html":
        setTitle("Hylia");
        state = prefs.getString("G20", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "21-Faron1.html":
        setTitle("Faron");
        state = prefs.getString("G21", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "22-Central2.html":
        setTitle("Central Hyrule 2");
        state = prefs.getString("G22", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "23-HyruleCastle.html":
        setTitle("Hyrule Castle");
        state = prefs.getString("G23", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
      case "24-PostGame.html":
        setTitle("Post Game");
        state = prefs.getString("G24", "0,0,0,0,0");
        web.loadUrl("javascript:setState([" + state + "]);");
        break;
    }
    prefs.edit().putString("lastPage", url).commit();
    ProgressBar bar = findViewById(R.id.mainProg);
    bar.setProgress(getProgress());
  }

  private int getProgress(){
    // 1508
    int i = oneCount(prefs.getString("G1", "0"));
    i += oneCount(prefs.getString("G2", "0"));
    i += oneCount(prefs.getString("G3", "0"));
    i += oneCount(prefs.getString("G4", "0"));
    i += oneCount(prefs.getString("G5", "0"));
    i += oneCount(prefs.getString("G6", "0"));
    i += oneCount(prefs.getString("G7", "0"));
    i += oneCount(prefs.getString("G8", "0"));
    i += oneCount(prefs.getString("G9", "0"));
    i += oneCount(prefs.getString("G10", "0"));
    i += oneCount(prefs.getString("G11", "0"));
    i += oneCount(prefs.getString("G12", "0"));
    i += oneCount(prefs.getString("G13", "0"));
    i += oneCount(prefs.getString("G14", "0"));
    i += oneCount(prefs.getString("G15", "0"));
    i += oneCount(prefs.getString("G16", "0"));
    i += oneCount(prefs.getString("G17", "0"));
    i += oneCount(prefs.getString("G18", "0"));
    i += oneCount(prefs.getString("G19", "0"));
    i += oneCount(prefs.getString("G20", "0"));
    i += oneCount(prefs.getString("G21", "0"));
    i += oneCount(prefs.getString("G22", "0"));

    return i / 1508;
  }

  private int oneCount(String s){
    return s.length() - (s.replace("1", "")).length();
  }

  private void saveState(String state){
    state = state.substring(1, state.length() - 1);
    switch(prefs.getString("lastPage", "")){
      case "01-Plateau1.html":
        prefs.edit().putString("G1", state).commit();
        break;
      case "02-DP1.html":
        prefs.edit().putString("G2", state).commit();
        break;
      case "03-Hateno1.html":
        prefs.edit().putString("G3", state).commit();
        break;
      case "04-DP2.html":
        prefs.edit().putString("G4", state).commit();
        break;
      case "05-Hateno2.html":
        prefs.edit().putString("G5", state).commit();
        break;
      case "06-Lanaryu1.html":
        prefs.edit().putString("G6", state).commit();
        break;
      case "07-Woodland1.html":
        prefs.edit().putString("G7", state).commit();
        break;
      case "08-Ridgeland1.html":
        prefs.edit().putString("G8", state).commit();
        break;
      case "09-Tabantha1.html":
        prefs.edit().putString("G9", state).commit();
        break;
      case "10-Hebra1.html":
        prefs.edit().putString("G10", state).commit();
        break;
      case "11-Eldin1.html":
        prefs.edit().putString("G11", state).commit();
        break;
      case "12-Akkala1.html":
        prefs.edit().putString("G12", state).commit();
        break;
      case "13-Central1.html":
        prefs.edit().putString("G13", state).commit();
        break;
      case "14-Wasteland1.html":
        prefs.edit().putString("G14", state).commit();
        break;
      case "15-Gerudo1.html":
        prefs.edit().putString("G15", state).commit();
        break;
      case "16-Wasteland1.html":
        prefs.edit().putString("G16", state).commit();
        break;
      case "17-Chamption'sBallad.html":
        prefs.edit().putString("G17", state).commit();
        break;
      case "18-Lanaryu2.html":
        prefs.edit().putString("G18", state).commit();
        break;
      case "19-Hateno3.html":
        prefs.edit().putString("G19", state).commit();
        break;
      case "20-Hylia1.html":
        prefs.edit().putString("G20", state).commit();
        break;
      case "21-Faron1.html":
        prefs.edit().putString("G21", state).commit();
        break;
      case "22-Central2.html":
        prefs.edit().putString("G22", state).commit();
        break;
      case "23-HyruleCastle.html":
        prefs.edit().putString("G23", state).commit();
        break;
      case "24-PostGame.html":
        prefs.edit().putString("G24", state).commit();
        break;
    }
  }

  private void setSelect(String page){
    NavigationView nav = findViewById(R.id.nav_view);

    int pos = 0;

    switch(page){
      case "01-Plateau1.html":
        pos = 1;
        break;
      case "02-DP1.html":
        pos = 2;
        break;
      case "03-Hateno1.html":
        pos = 3;
        break;
      case "04-DP2.html":
        pos = 4;
        break;
      case "05-Hateno2.html":
        pos = 5;
        break;
      case "06-Lanaryu1.html":
        pos = 6;
        break;
      case "07-Woodland1.html":
        pos = 7;
        break;
      case "08-Ridgeland1.html":
        pos = 8;
        break;
      case "09-Tabantha1.html":
        pos = 9;
        break;
      case "10-Hebra1.html":
        pos = 10;
        break;
      case "11-Eldin1.html":
        pos = 11;
        break;
      case "12-Akkala1.html":
        pos = 12;
        break;
      case "13-Central1.html":
        pos = 13;
        break;
      case "14-Wasteland2.html":
        pos = 14;
        break;
      case "15-Gerudo1.html":
        pos = 15;
        break;
      case "16-Wasteland2.html":
        pos = 16;
        break;
      case "17-ChampionsBallad.html":
        pos = 17;
        break;
      case "18-Lanaryu2.html":
        pos = 18;
        break;
      case "19-Hateno3.html":
        pos = 19;
        break;
      case "20-Hylia1.html":
        pos = 20;
        break;
      case "21-Faron1.html":
        pos = 21;
        break;
      case "22-Central2.html":
        pos = 22;
        break;
      case "23-HyruleCastle.html":
        pos = 23;
        break;
      case "24-PostGame.html":
        pos = 24;
        break;
    }
    nav.getMenu().getItem(pos).setChecked(true);
  }

  public class WebAppInterface{
    Context mContext;

    WebAppInterface(Context c){
      mContext = c;
    }

    @JavascriptInterface
    public void showToast(String toast){
      Log.e("TST", "TESTING");
    }
  }
}
