package com.example.richimozes.testvikeaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vike.Vike;
import com.vike.integrations.flurry.FlurryIntegration;
import com.vike.integrations.googleanalytics.GoogleAnalyticsIntegration;
import com.vike.integrations.mixpanel.MixpanelIntegration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTrack, btnIdentify, btnScreen, btnCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vike vike = new Vike.Builder(this, "bcafd7fa43e5bdff1078b")
                .use(MixpanelIntegration.FACTORY)
                .use(GoogleAnalyticsIntegration.FACTORY)
                .use(FlurryIntegration.FACTORY)
                .build();
        Vike.setSingletonInstance(vike);

        findUI();
        initListeners();
    }

    private void findUI() {
        btnTrack    = (Button) findViewById(R.id.btnTestTrack);
        btnIdentify = (Button) findViewById(R.id.btnIdentify);
        btnScreen   = (Button) findViewById(R.id.btnScreen);
        btnCrash    = (Button) findViewById(R.id.btnCrash);
    }

    private void initListeners() {
        btnTrack.setOnClickListener(this);
        btnIdentify.setOnClickListener(this);
        btnScreen.setOnClickListener(this);
        btnCrash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTestTrack:
                Vike.with(this).track("Track event");
                break;
            case R.id.btnIdentify:
                Vike.with(this).identify("Identify event");
                break;
            case R.id.btnScreen:
                Vike.with(this).screen("Screen category", "Main screen");
                break;
            case R.id.btnCrash:
                throw new RuntimeException("Test crash event");
        }
    }
}

