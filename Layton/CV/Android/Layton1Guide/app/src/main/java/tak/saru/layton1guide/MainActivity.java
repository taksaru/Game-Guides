package tak.saru.layton1guide;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  private SharedPreferences prefs;
  private WebView web;
  private boolean yank;
  private boolean mobile;
  private String state;
  private NavigationView nav;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    prefs = PreferenceManager.getDefaultSharedPreferences(this);

    yank = prefs.getBoolean("yank", false);

    mobile = prefs.getBoolean("mobile", false);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MobileAds.initialize(this, new OnInitializationCompleteListener(){
      @Override
      public  void onInitializationComplete(InitializationStatus initializationStatus){
      }
    });

    AdView mAdView = findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    nav = findViewById(R.id.nav_view);

    ActionBarDrawerToggle  toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
    );

    drawer.addDrawerListener(toggle);
    toggle.syncState();
    nav.setNavigationItemSelectedListener(this);

    web = findViewById(R.id.webView);
    web.setWebViewClient(new WebViewClient(){
      public void onPageFinished(WebView view, String weburl){
        view.loadUrl("javascript:setState(" + yank + "," + mobile + ");");
        yankify(yank);
      }

      public void onPageStarted(WebView view, String url){
        view.stopLoading();
      }
    });


    WebSettings settings = web.getSettings();

    settings.setJavaScriptEnabled(true);
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    settings.setDomStorageEnabled(true);
    settings.setLoadWithOverviewMode(true);

    Log.e("c", "startup");

    web.addJavascriptInterface(new WebAppInterface(this), "Android");
    state = prefs.getString("state", "001");
    Log.e("STATE", state);
    setState(state);
  }

  @Override
  protected void onStart(){
    yankify(yank);

    super.onStart();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    menu.getItem(0).setChecked(yank);
    menu.getItem(1).setChecked(mobile);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();
    item.setChecked(!item.isChecked());
    Log.e("item", item.getTitle().toString());
    if(id == R.id.action_yank){
      yankify(item.isChecked());
    }else if(id == R.id.action_mobile){
      mobile = item.isChecked();
      prefs.edit().putBoolean("mobile", mobile).commit();
    }
    web.loadUrl("javascript:setState(" + yank + "," + mobile + ");");
    return super.onOptionsItemSelected(item);
  }

  public boolean onNavigationItemSelected(final MenuItem item){
    String num = item.getTitle().toString().substring(0, 3);
    setState(num);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void doThing(String str){
    setState(str);
  }

  private void setState(String num){
    state = num;
    prefs.edit().putString("state", state).commit();
    int i = Integer.parseInt(state) - 1;
    Log.e("T3", num);
    if(yank){
      switch(state){
        case "005":
        case "010":
        case "045":
        case "046":
        case "050":
        case "062":
        case "066":
        case "067":
        case "072":
        case "076":
        case "085":
        case "088":
        case "096":
        case "111":
        case "113":
        case "124":
          num = num + "A";
      }
    }

    String s = "file:///android_asset/www/" + num + ".html";

    Log.e("T4", s);


    web.loadUrl(s);


    Log.e("T5", "DONE");
    nav.getMenu().getItem(i).setChecked(true);
  }

  private void yankify(boolean check){
    yank = check;
    MenuItem item;

    if(yank){
      // 001
      item = nav.getMenu().getItem(0);
      item.setTitle(R.string.puzzle001a);
      //005
      item = nav.getMenu().getItem(4);
      item.setTitle(R.string.puzzle005a);
      //010
      item = nav.getMenu().getItem(9);
      item.setTitle(R.string.puzzle010a);
      //019
      item =  nav.getMenu().getItem(19);
      item.setTitle(R.string.puzzle019a);
      //032
      item =  nav.getMenu().getItem(031);
      item.setTitle(R.string.puzzle032a);
      //040
      item = nav.getMenu().getItem(39);
      item.setTitle(R.string.puzzle040a);
      //045
      item = nav.getMenu().getItem(45);
      item.setTitle(R.string.puzzle045a);
      //046
      item = nav.getMenu().getItem(46);
      item.setTitle(R.string.puzzle046a);
      //050
      item = nav.getMenu().getItem(49);
      item.setTitle(R.string.puzzle050a);
      //062
      item = nav.getMenu().getItem(61);
      item.setTitle(R.string.puzzle062a);
      //063
      item = nav.getMenu().getItem(62);
      item.setTitle(R.string.puzzle063a);
      //065
      item = nav.getMenu().getItem(64);
      item.setTitle(R.string.puzzle065a);
      //066
      item = nav.getMenu().getItem(65);
      item.setTitle(R.string.puzzle066a);
      //067
      item = nav.getMenu().getItem(66);
      item.setTitle(R.string.puzzle067a);
      //072
      item = nav.getMenu().getItem(71);
      item.setTitle(R.string.puzzle072a);
      //076
      item = nav.getMenu().getItem(75);
      item.setTitle(R.string.puzzle076a);
      //085
      item = nav.getMenu().getItem(84);
      item.setTitle(R.string.puzzle085a);
      //088
      item = nav.getMenu().getItem(87);
      item.setTitle(R.string.puzzle088a);
      //096
      item = nav.getMenu().getItem(95);
      item.setTitle(R.string.puzzle096a);
      //107
      item = nav.getMenu().getItem(106);
      item.setTitle(R.string.puzzle107a);
      //111
      item = nav.getMenu().getItem(110);
      item.setTitle(R.string.puzzle111a);
      //113
      item = nav.getMenu().getItem(112);
      item.setTitle(R.string.puzzle113a);
      //124
      item = nav.getMenu().getItem(123);
      item.setTitle(R.string.puzzle124a);
      //132
      item = nav.getMenu().getItem(131);
      item.setTitle(R.string.puzzle132a);
      //135
      item = nav.getMenu().getItem(134);
      item.setTitle(R.string.puzzle135a);
    }else{
      // 001
      item = nav.getMenu().getItem(0);
      item.setTitle(R.string.puzzle001);
      //005
      item = nav.getMenu().getItem(4);
      item.setTitle(R.string.puzzle005);
      //010
      item = nav.getMenu().getItem(9);
      item.setTitle(R.string.puzzle010);
      //019
      item = nav.getMenu().getItem(18);
      item.setTitle(R.string.puzzle019);
      //032
      item = nav.getMenu().getItem(31);
      item.setTitle(R.string.puzzle032);
      //040
      item = nav.getMenu().getItem(39);
      item.setTitle(R.string.puzzle040);
      //045
      item = nav.getMenu().getItem(44);
      item.setTitle(R.string.puzzle045);
      //046
      item = nav.getMenu().getItem(45);
      item.setTitle(R.string.puzzle046);
      //050
      item = nav.getMenu().getItem(49);
      item.setTitle(R.string.puzzle050);
      //062
      item = nav.getMenu().getItem(61);
      item.setTitle(R.string.puzzle062);
      //063
      item = nav.getMenu().getItem(62);
      item.setTitle(R.string.puzzle063);
      //065
      item = nav.getMenu().getItem(64);
      item.setTitle(R.string.puzzle065);
      //066
      item = nav.getMenu().getItem(65);
      item.setTitle(R.string.puzzle066);
      //067
      item = nav.getMenu().getItem(66);
      item.setTitle(R.string.puzzle067);
      //072
      item = nav.getMenu().getItem(71);
      item.setTitle(R.string.puzzle072);
      //076
      item = nav.getMenu().getItem(75);
      item.setTitle(R.string.puzzle076);
      //085
      item = nav.getMenu().getItem(84);
      item.setTitle(R.string.puzzle085);
      //088
      item = nav.getMenu().getItem(87);
      item.setTitle(R.string.puzzle088);
      //096
      item = nav.getMenu().getItem(95);
      item.setTitle(R.string.puzzle096);
      //107
      item = nav.getMenu().getItem(106);
      item.setTitle(R.string.puzzle107);
      //111
      item = nav.getMenu().getItem(110);
      item.setTitle(R.string.puzzle111);
      //113
      item = nav.getMenu().getItem(112);
      item.setTitle(R.string.puzzle113);
      //124
      item = nav.getMenu().getItem(123);
      item.setTitle(R.string.puzzle124);
      //132
      item = nav.getMenu().getItem(131);
      item.setTitle(R.string.puzzle132);
      //135
      item = nav.getMenu().getItem(134);
      item.setTitle(R.string.puzzle135);
    }
    prefs.edit().putBoolean("yank", yank).commit();
  }

  public class WebAppInterface{
    private Activity activity;

    WebAppInterface(Activity act){
      this.activity = act;
    }

    @JavascriptInterface
    public void changePage(final String state){
      Log.e("T2", state);
      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          MainActivity.this.setState(state);
        }
      });
    }
  }
}
