package com.play4noobwin.p4browser;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.ClipboardManager;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;



public class MainActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear2;
	private WebView webview1;
	private EditText edittext1;
	private Button button1;
	private Spinner spinner1;
	private LinearLayout linearstg1;
	private TextView generaltext;
	private LinearLayout linearstg2;
	private LinearLayout linearstg3;
	private TextView betatext;
	private LinearLayout linearstg4;
	private TextView anothertext;
	private ImageView imageview1;
	private TextView infotext1;
	private TextView infotext2;
	private TextView infotext3;
	private TextView infotext4;
	private Button githubsource;
	private Button backwv;
	private TextView settingstext;
	private TextView cachetext;
	private Button cleancachebutton;
	private TextView desktoptext;
	private Button desktoponoff;
	private TextView themetext;
	private Spinner theme;

	private double stop = 0;
	private double desktop = 0;
	private boolean Stg;

	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> themelist = new ArrayList<String>();

	private Timer _timer = new Timer();
	private TimerTask timer;
	private SharedPreferences themed;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		webview1.getSettings().setBuiltInZoomControls(true);
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _view,final String _url, Bitmap _favicon) {
				edittext1.setText(_url);
				super.onPageStarted(_view, _url, _favicon);
			}
			@Override
			public void onPageFinished(WebView _view,final String _url) {

				super.onPageFinished(_view, _url);
			}
		});
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		linearstg1 = (LinearLayout) findViewById(R.id.linearstg1);
		generaltext = (TextView) findViewById(R.id.generaltext);
		linearstg2 = (LinearLayout) findViewById(R.id.linearstg2);
		linearstg3 = (LinearLayout) findViewById(R.id.linearstg3);
		betatext = (TextView) findViewById(R.id.betatext);
		linearstg4 = (LinearLayout) findViewById(R.id.linearstg4);
		anothertext = (TextView) findViewById(R.id.anothertext);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		infotext1 = (TextView) findViewById(R.id.infotext1);
		infotext2 = (TextView) findViewById(R.id.infotext2);
		infotext3 = (TextView) findViewById(R.id.infotext3);
		infotext4 = (TextView) findViewById(R.id.infotext4);
		githubsource = (Button) findViewById(R.id.githubsource);
		backwv = (Button) findViewById(R.id.backwv);
		settingstext = (TextView) findViewById(R.id.settingstext);
		cachetext = (TextView) findViewById(R.id.cachetext);
		cleancachebutton = (Button) findViewById(R.id.cleancachebutton);
		desktoptext = (TextView) findViewById(R.id.desktoptext);
		desktoponoff = (Button) findViewById(R.id.desktoponoff);
		themetext = (TextView) findViewById(R.id.themetext);
		theme = (Spinner) findViewById(R.id.theme);


		themed = getSharedPreferences("themedata", Activity.MODE_PRIVATE);

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (edittext1.getText().toString().contains("http://") || (edittext1.getText().toString().contains("https://") || (edittext1.getText().toString().contains("www.") || (edittext1.getText().toString().contains(".com") || (edittext1.getText().toString().contains(".net") || edittext1.getText().toString().contains(".org")))))) {
					if (edittext1.getText().toString().contains("http://") || edittext1.getText().toString().contains("https://")) {
						webview1.loadUrl(edittext1.getText().toString());
					}
					else {
						webview1.loadUrl("https://".concat(edittext1.getText().toString()));
					}
				}
				else {
					webview1.loadUrl("https://www.google.com.br/search?q=".concat(edittext1.getText().toString().replace(" ", "+")));
				}
			}
		});

		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView _parent, View _view, final int _position, long _id) { 
				if (_position == 1) {
					webview1.goForward();
				}
				else {
					if (_position == 2) {
						webview1.setVisibility(View.GONE);
						linear1.setVisibility(View.GONE);
						linear2.setVisibility(View.VISIBLE);
						spinner1.setSelection((int)(0));
					}
					else {

					}
				}
			}
			@Override
			public void onNothingSelected(AdapterView _parent) { 
			}
		});
		desktoponoff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (desktop == 0) {
					desktop++;
					webview1.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
					webview1.loadUrl(edittext1.getText().toString());
					desktoponoff.setText("(ON)");
				}
				else {
					desktop--;
					webview1.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 7.1.2; GalaxyNexus Build/IMM76B) AppleWebKit/535.19(KHTML, like Gecko) Chrome/18.0.1025.133Mobile Safari/535.19");
					webview1.loadUrl(edittext1.getText().toString());
					desktoponoff.setText("(OFF)");
				}
			}
		});
		backwv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				webview1.setVisibility(View.VISIBLE);
				linear1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.GONE);
			}
		});
		cleancachebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				webview1.clearCache(true);
				showMessage("Cleaned!");
			}
		});
		theme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView _parent, View _view, final int _position, long _id) { 
				if (_position < 1) {
					linear1.setBackgroundColor(0xFFFFFFFF);
					linear2.setBackgroundColor(0xFFFFFFFF);
					webview1.setBackgroundColor(0xFFFFFFFF);
					edittext1.setBackgroundColor(0xFFFFFFFF);
					generaltext.setBackgroundColor(0xFFFFFFFF);
					betatext.setBackgroundColor(0xFFFFFFFF);
					anothertext.setBackgroundColor(0xFFFFFFFF);
					themed.edit().putString("theme", String.valueOf((long)(_position))).commit();
				}
				else {
					if (_position < 2) {
						linear1.setBackgroundColor(0xFF757575);
						linear2.setBackgroundColor(0xFF757575);
						webview1.setBackgroundColor(0xFF757575);
						edittext1.setBackgroundColor(0xFF757575);
						generaltext.setBackgroundColor(0xFF9E9E9E);
						betatext.setBackgroundColor(0xFF9E9E9E);
						anothertext.setBackgroundColor(0xFF9E9E9E);
						themed.edit().putString("theme", String.valueOf((long)(_position))).commit();
					}
					else {
						if (_position < 3) {
							linear1.setBackgroundColor(0xFF80DEEA);
							linear2.setBackgroundColor(0xFF80DEEA);
							webview1.setBackgroundColor(0xFF80DEEA);
							edittext1.setBackgroundColor(0xFF80DEEA);
							generaltext.setBackgroundColor(0xFFB2EBF2);
							betatext.setBackgroundColor(0xFFB2EBF2);
							anothertext.setBackgroundColor(0xFFB2EBF2);
							themed.edit().putString("theme", String.valueOf((long)(_position))).commit();
						}
						else {

						}
					}
				}
			}
			@Override
			public void onNothingSelected(AdapterView _parent) { 
			}
		});
		githubsource.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				webview1.loadUrl("https://www.github.com/player4noobwinner/p4browser");
				webview1.setVisibility(View.VISIBLE);
				linear1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.GONE);
			}
		});

	}

	private void  initializeLogic() {
		webview1.loadUrl("https://www.google.com");
		webview1.setDownloadListener(new DownloadListener() {

public void onDownloadStart(String str, String str2, String str3, String str4, long j) {

Intent intent = new Intent("android.intent.action.VIEW");

intent.setData(Uri.parse(str));

MainActivity.this.startActivity(intent);

}

});
		linear2.setVisibility(View.GONE);
		list.add("...");
		list.add("Next");
		list.add("Settings");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, list));
		themelist.add("White");
		themelist.add("Grey");
		themelist.add("Cyan");
		theme.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, themelist));
		if (themed.getString("theme", "").contains("0") || (themed.getString("theme", "").contains("1") || themed.getString("theme", "").contains("2"))) {
			theme.setSelection((int)(Double.parseDouble(themed.getString("theme", ""))));
		}
	}

	@Override
	public void onBackPressed() {
				webview1.setVisibility(View.VISIBLE);
				linear1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.GONE);
				if (webview1.canGoBack()) {
					webview1.goBack();
				}
				else {
					if (stop == 0) {
						showMessage("Click again to exit");
						stop++;
						timer = new TimerTask() {
									@Override
										public void run() {
											runOnUiThread(new Runnable() {
											@Override
												public void run() {
																		stop--;
												}
											});
										}
									};
									_timer.schedule(timer, (int)(1500));
					}
					else {
						if (stop == 1) {
							finish();
						}
					}
				}
	}





	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

	private float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}

	private int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}

	private int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}


}
