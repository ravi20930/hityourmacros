package com.itisravi.hym;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FoodList extends AppCompatActivity {

    private int mealId;
    private String table;
    private FloatingActionButton addYourOwnFood;
    private MaterialCardView addFoodCardView;
    private MaterialButton addBtnFinal;
    private EditText ETaddFoodFoodName, ETaddFoodCarbs, ETaddFoodProtein, ETaddFoodFats, ETaddFoodSize;
    private Animation fromBottom, toBottom, rotateClose, rotateOpen;
    private boolean fabIsOpen = false;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private void initViews() {

        addFoodCardView = findViewById(R.id.addFoodCardView);

        addYourOwnFood = findViewById(R.id.addYourOwnFood);
        addBtnFinal = findViewById(R.id.addBtnFinal);
        ETaddFoodSize = findViewById(R.id.ETaddFoodSize);
        ETaddFoodFats = findViewById(R.id.ETaddFoodFats);
        ETaddFoodProtein = findViewById(R.id.ETaddFoodProtein);
        ETaddFoodCarbs = findViewById(R.id.ETaddFoodCarbs);
        ETaddFoodFoodName = findViewById(R.id.ETaddFoodFoodName);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setAddYourOwnFood() {
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);

        if (fabIsOpen) {
            addYourOwnFood.startAnimation(rotateClose);
            addFoodCardView.startAnimation(toBottom);

            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            addFoodCardView.setVisibility(View.GONE);
            ETaddFoodFoodName.setText("");
            ETaddFoodCarbs.setText("");
            ETaddFoodProtein.setText("");
            ETaddFoodFats.setText("");
            ETaddFoodSize.setText("");

            fabIsOpen = false;
        } else {
            addYourOwnFood.startAnimation(rotateOpen);
            addFoodCardView.startAnimation(fromBottom);

            addFoodCardView.setVisibility(View.VISIBLE);

            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);

            fabIsOpen = true;
        }
    }

    private void insertToFoodDB(String values) {
        DBAdapter db = new DBAdapter(this);
        db.open();
        db.insert("foodDB", "category, foodName, carbs, fats, kcals, protein, size", values);
        db.close();
    }

    private void addFoodToFoodDB() {
        String foodName = ETaddFoodFoodName.getText().toString().trim();
        double carbs = Double.parseDouble(ETaddFoodCarbs.getText().toString().trim());
        double fats = Double.parseDouble(ETaddFoodFats.getText().toString().trim());
        double protein = Double.parseDouble(ETaddFoodProtein.getText().toString().trim());
        String size = ETaddFoodSize.getText().toString().trim();
        double kcals = 4 * protein + 4 * carbs + 9 * fats;

        insertToFoodDB("" + 1 + ",'" + foodName + "'," + carbs + "," + fats + "," + kcals + "," + protein + "," + "'" + size + "'");
    }

    private void setupTabView() {

        FoodListFragmentAdapter adapter = new FoodListFragmentAdapter(this);
        viewPager.setAdapter(adapter);

        String[] foodListTabs = {"All Foods", "Your Foods"};

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(foodListTabs[position]);
            }
        }).attach();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        initViews();

        Intent intent = getIntent();
        mealId = intent.getIntExtra("mealId", 1);
        table = intent.getStringExtra("tableName");

        setupTabView();

        addYourOwnFood.setOnClickListener(v -> {
            setAddYourOwnFood();
        });

        addBtnFinal.setOnClickListener(v -> {

            Boolean notOkay = ETaddFoodFoodName.getText().toString().trim().equals("")
                    || ETaddFoodCarbs.getText().toString().trim().equals("")
                    || ETaddFoodProtein.getText().toString().trim().equals("")
                    || ETaddFoodFats.getText().toString().trim().equals("")
                    || ETaddFoodSize.getText().toString().trim().equals("");

            if (notOkay) {
                Toast.makeText(FoodList.this, "ERROR! empty field", Toast.LENGTH_LONG).show();
            } else {
                addFoodToFoodDB();
                Toast.makeText(FoodList.this, "" + ETaddFoodFoodName.getText().toString() + " added to the list", Toast.LENGTH_SHORT).show();

                addYourOwnFood.startAnimation(rotateClose);
                addFoodCardView.startAnimation(toBottom);

                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);

                addFoodCardView.setVisibility(View.GONE);
                ETaddFoodFoodName.setText("");
                ETaddFoodCarbs.setText("");
                ETaddFoodProtein.setText("");
                ETaddFoodFats.setText("");
                ETaddFoodSize.setText("");

                fabIsOpen = false;

                setupTabView();
            }
        });
    }
}