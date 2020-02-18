package org.alie.alieioc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

@ContentView(layoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @SingleView(viewId = R.id.btn1)
    private Button btn1;

    @SingleView(viewId = R.id.btn2)
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"=========btn1:"+btn1+"=======btn2:"+btn2);
    }
}
