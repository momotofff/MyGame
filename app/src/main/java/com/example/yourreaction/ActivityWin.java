package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;

import java.util.Locale;

public class ActivityWin extends AppCompatActivity
{
    private BannerAdView mBannerAdView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        mBannerAdView = (BannerAdView) findViewById(R.id.banner2);
        mBannerAdView.setAdUnitId("R-M-7893449-1");
        mBannerAdView.setAdSize(BannerAdSize.stickySize(this, 1000));

        final AdRequest adRequest = new AdRequest.Builder().build();


        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {}

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {}

            @Override
            public void onAdClicked() {}

            @Override
            public void onAdLoaded() {}
            @Override
            public void onLeftApplication() {}

            @Override
            public void onReturnedToApplication() {}
        });

        mBannerAdView.loadAd(adRequest);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        GameResult gameResult = (GameResult) bundle.getSerializable(GameResult.class.getName());

        setText(R.id.buttonMin, gameResult.min());
        setText(R.id.buttonMax, gameResult.max());
        setText(R.id.buttonAvg, gameResult.avg());
        setText(R.id.countFalseClick, gameResult.falseStarts);

        findViewById(R.id.buttonRepeat).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, gameResult.caller).putExtras(bundle))
        );

        findViewById(R.id.buttonBack).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, RoundsActivity.class).putExtras(bundle))
        );


        OnBackPressedCallback onBackPressed = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(ActivityWin.this, RoundsActivity.class));
                finish();
            }
        };

        this.getOnBackPressedDispatcher().addCallback(this, onBackPressed);
    }

    void setText(int id, long value)
    {
        TextView view = findViewById(id);
        view.setText(String.format(Locale.getDefault(), "%s %d", view.getText(), value));
    }
}
