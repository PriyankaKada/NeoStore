package com.example.webwerks.neostore.Rating;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by webwerks on 4/4/18.
 */

public class RatingBarActivity extends Activity implements RatingView{
    @BindView(R.id.title)
    TextView Title;
    @BindView(R.id.ratingBar)
    RatingBar Ratingbar;
    @BindView(R.id.img_product)
    ImageView imageView;
    RatingPresenter ratingPresenter;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    @BindView(R.id.btn_rate)
    Button btn_rate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_popup);
        ButterKnife.bind(this);
        setGetData();
        setUpDisplayMetrics();
        ratingPresenter=new RatingPresenterImplmentation(RatingBarActivity.this);

    }
    @OnClick(R.id.btn_rate)
    public void rateClick() {

        ratingPresenter.submitRatings(Integer.toString(SPManager.getInstance(getApplicationContext()).retriveInt("product_id", 1)), (int) Ratingbar.getRating());
    }

    private void setUpDisplayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
    }

    private void setGetData() {
        String image_url = getIntent().getStringExtra("main_image_url");
        String title = getIntent().getStringExtra("Title");

        Title.setText(title);
        Ratingbar.setRating(Float.parseFloat("3.0"));

        Glide.with(this)
                .load(image_url)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
