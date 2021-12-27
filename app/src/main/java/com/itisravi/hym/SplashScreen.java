package com.itisravi.hym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private void insertToFoodDB(String values) {
        DBAdapter db = new DBAdapter(SplashScreen.this);
        db.open();
        db.insert("foodDB", "category, foodName, carbs, fats, kcals, protein, size", values);
        db.close();
    }

    private void insertAllFood() {
        DBAdapter db = new DBAdapter(SplashScreen.this);
        db.open();
        if (db.count("foodDB") < 1) {
            insertToFoodDB("0,'whole egg',0.3,4.2,63,5.5,'1 medium'");
            insertToFoodDB("0,'white bread',9,0.8,50,1.5,'1 slice'");
            insertToFoodDB("0,'walnuts',1.4,6.5,65,1.5,'10g'");
            insertToFoodDB("0,'veg sandwich',42,8,282,8,'1 whole'");
            insertToFoodDB("0,'upma',45,3,250,8,'120 grams'");
            insertToFoodDB("0,'tropicana fruit juice',12.4,0,50,0.1,'100 ml'");
            insertToFoodDB("0,'tofu',2.4,9.4,95,16.5,'100g'");
            insertToFoodDB("0,'sunflower seeds (dry)',2,5.1,62.3,2.1,'10g'");
            insertToFoodDB("0,'soybean (dry)',30.1,19.9,446,36.4,'100g'");
            insertToFoodDB("0,'soya chunk',30,1,340,52,'100g'");
            insertToFoodDB("0,'sattu (gram flour)',23.5,2.6,158,9.8,'40g / 2 heaping spoon'");
            insertToFoodDB("0,'roti',22.3,2,112,3.8,'1 roti / 50g'");
            insertToFoodDB("0,'rice',78,0.5,340.5,6,'100g / 1 serving bowl approx'");
            insertToFoodDB("0,'real fruit juice',15,0,60,0.1,'100 ml'");
            insertToFoodDB("0,'raw whey isolate',1.5,1,112.5,27,'30g / 1 level scoop'");
            insertToFoodDB("0,'rajma (kidney bean dry)',60,0.8,333,24,'100g'");
            insertToFoodDB("0,'pumpkin seeds (dry)',5.4,1.9,44.6,1.9,'10g'");
            insertToFoodDB("0,'potato',26,0.2,110,3,'1 medium / 150g approx'");
            insertToFoodDB("0,'plain parantha',29,7,193,3,'50g / 1 parantha'");
            insertToFoodDB("0,'plain dosa',17,2,106,2,'350 grams'");
            insertToFoodDB("0,'pistachios',5.4,9,118.6,4,'20g'");
            insertToFoodDB("0,'peanut butter (unsweetened)',6,16,204,10,'2 tablespoon / 32g approx'");
            insertToFoodDB("0,'peanut butter (high protein)',4,15.3,204,12,'2 tablespoon / 33g approx'");
            insertToFoodDB("0,'peanut butter (flavoured)',10.2,16.4,194,8.3,'2 tablespoon / 32g approx'");
            insertToFoodDB("0,'paneer sandwich',40,6,160,12,'1 whole'");
            insertToFoodDB("0,'paneer (cottage cheese)',2.5,24.2,300,18,'100g'");
            insertToFoodDB("0,'omelette',1,5,183,13.8,'1 large egg'");
            insertToFoodDB("0,'oil',0,10,90,0,'10g'");
            insertToFoodDB("0,'oats',27.1,3.6,152,4.6,'1 small bowl / 40g approx'");
            insertToFoodDB("0,'muscleblaze biozyme performance whey',5.6,1.9,141,25,'36g / 1 scoop'");
            insertToFoodDB("0,'mung beans (dry)',63,1.2,347,24,'100g'");
            insertToFoodDB("0,'masala dosa',88,9,498,16,'350 grams'");
            insertToFoodDB("0,'khichdi',23,4,117,4.1,'100g / 1 small bowl'");
            insertToFoodDB("0,'kala chana (dry)',15,4.2,351.6,18,'100g'");
            insertToFoodDB("0,'impact Whey Protein',1.6,1.8,102,20,'25g / 1 scoop'");
            insertToFoodDB("0,'idli',15,0,69,1,'1 whole'");
            insertToFoodDB("0,'glucose',36.8,0,147,0,'1 serving / 40g'");
            insertToFoodDB("0,'egg white',0.2,0,14,2.8,'1 medium'");
            insertToFoodDB("0,'durum wheat pasta',73.2,1.3,354,11,'1 medium'");
            insertToFoodDB("0,'dal (cooked)',42,4,300,14,'1 cup / 125 ml'");
            insertToFoodDB("0,'curd',4.7,3.3,61,3.5,'100g'");
            insertToFoodDB("0,'cow milk (toned)',5.15,3.4,63.6,3.1,'100 ml / 1 small cup'");
            insertToFoodDB("0,'cow milk',5.3,3.9,70.6,3.5,'100 ml / 1 small cup'");
            insertToFoodDB("0,'choco spread',20,10.2,179.1,1.8,'33g / 1 spoon'");
            insertToFoodDB("0,'chickpeas',61,6,364,19,'100g'");
            insertToFoodDB("0,'chicken sandwich',40,16,360,14,'1 whole'");
            insertToFoodDB("0,'chicken breast',0,3.6,165,31,'100g'");
            insertToFoodDB("0,'chia seeds',4.2,3.1,48.6,1.7,'1 tablespoon / 10g approx'");
            insertToFoodDB("0,'cashews',6,8.8,111,3.6,'20g / 15 pieces'");
            insertToFoodDB("0,'brown rice',76,2.7,362,7.5,'100g / 1 serving bowl approx'");
            insertToFoodDB("0,'brown bread',13,0.3,65,2.4,'1 slice / 28g approx'");
            insertToFoodDB("0,'boiled eggs',1,5,78,6,'1 egg'");
            insertToFoodDB("0,'biozyme whey protein',2,2.5,130.5,25,'1 scoop / 33g'");
            insertToFoodDB("0,'banana',27,0.4,105,1.3,'1 medium'");
            insertToFoodDB("0,'b natural fruit juice',13.8,0,58,0.2,'100 ml'");
            insertToFoodDB("0,'apple',25.1,0.3,95,0.5,'1 medium'");
            insertToFoodDB("0,'aloo parantha',42,7,254,5.5,'1 aloo parantha'");
            insertToFoodDB("0,'almonds',2.2,5,58,2.1,'10g / 10 pieces'");
        }
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences personActivityAndGoal = this.getSharedPreferences("personActivityAndGoal", MODE_PRIVATE);


        boolean containsGoal = personActivityAndGoal.contains("goal");

        if (containsGoal) {
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 400);
        } else {
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashScreen.this, PersonDetails.class);
                            insertAllFood();
                            startActivity(intent);
                            finish();
                        }
                    }, 700);
        }
    }
}