package com.example.resumemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skills extends AppCompatActivity {
    EditText skill;
    Button btnAddSkill,btnSaveSkills;
    LinearLayout container;
    Map<Integer,EditText> hm = new HashMap<Integer,EditText>();
    List<String> skillList = new ArrayList<String>();
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        skill = findViewById(R.id.etSkill);
        hm.put(i,skill);
        i++;
        btnAddSkill = findViewById(R.id.btnAddSkill);
        btnSaveSkills = findViewById(R.id.btnSaveSkill);

        container = (LinearLayout)findViewById(R.id.container);

        btnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                EditText skill1 = (EditText) addView.findViewById(R.id.etSkill1);
                skill1.setHint("Skill "+i);
                hm.put(i,skill1);
                i++;
                container.addView(addView);
            }
        });

        btnSaveSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                for(int t=1;t<=hm.size();t++)
                {
                    String s1 = hm.get(t).getText().toString();
                    editor.putString("skill"+t, s1);
                    skillList.add(s1);
                }

                String skillNum = String.valueOf(hm.size());
                editor.putString("skillNum",skillNum);
                editor.apply();

                Intent intent = new Intent(Skills.this, WorkExperience.class);
                startActivity(intent);



            }
        });


    }
}
