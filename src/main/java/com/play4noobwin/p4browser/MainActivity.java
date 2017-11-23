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
	private WebView webview1;
	private EditText edittext1;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;

	private double stop = 0;


	private Timer _timer = new Timer();
	private TimerTask timer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
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
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);



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
		button2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					webview1.loadUrl("https://www.google.com");
				}
			});
		button3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					webview1.goBack();
				}
			});
		button4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					webview1.goForward();
				}
			});


	}

	private void  initializeLogic() {
		webview1.loadUrl("https://www.google.com");
	}

	@Override
	public void onBackPressed() {
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

