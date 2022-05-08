package com.example.formapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText fullNameEditText;
    private EditText mssvEditText;
    private EditText cccdEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private ArrayList<EditText> requires;

    private ImageButton chooseDateOfBirthButton;
    private EditText dateOfBirthEditText;
    private CalendarView calendarView;
    private boolean hidden;

    private EditText homeTownEditText;

    private EditText addressEditText;

    private RadioGroup majorRadioGroup;

    private ArrayList<CheckBox> programmingLanguageCheckBoxes;

    private CheckBox agreeCheckBox;
    private Button submitButton;

    private Drawable originalDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        chooseDateOfBirth();
        submit();
    }

    private void catchTypingOnRequireEditText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) editText.setBackground(originalDrawable);
                else editText.setBackgroundResource(R.drawable.missing_textview_layout);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) editText.setBackground(originalDrawable);
                else editText.setBackgroundResource(R.drawable.missing_textview_layout);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) editText.setBackground(originalDrawable);
                else editText.setBackgroundResource(R.drawable.missing_textview_layout);
            }
        });
    }

    private void submit() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!agreeCheckBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Bạn chưa chấp nhận các điều khoản!", Toast.LENGTH_LONG).show();
                }else{
                    if (filledAll())
                        Toast.makeText(getApplicationContext(), "Nhập dữ liệu thành công!", Toast.LENGTH_LONG).show();
                    else{
                        Toast.makeText(getApplicationContext(), "Bạn chưa điền đầy đủ thông tin bắt buộc!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean filledAll(){
        boolean filledAll = true;
        for (int i = 0; i < requires.size(); i++){
            if (requires.get(i).getText().toString().length() == 0) {
                filledAll = false;
                requires.get(i).setBackgroundResource(R.drawable.missing_textview_layout);
                catchTypingOnRequireEditText(requires.get(i));
            }
        }
        return filledAll;
    }

    private void chooseDateOfBirth() {
        chooseDateOfBirthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hidden){
                    hidden = false;
                    calendarView.setVisibility(View.VISIBLE);
                }else {
                    hidden = true;
                    calendarView.setVisibility(View.GONE);
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                calendarView.setVisibility(View.GONE);
                hidden = true;
                String newDate = "" + i2 + "/" + (i1 + 1) + "/" + i;
                dateOfBirthEditText.setText(newDate);
            }
        });
    }

    private void init(){
        fullNameEditText = findViewById(R.id.fullName);
        mssvEditText = findViewById(R.id.studentID);
        cccdEditText = findViewById(R.id.cccd);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        requires = new ArrayList<>();
        requires.add(fullNameEditText);
        requires.add(mssvEditText);
        requires.add(cccdEditText);
        requires.add(phoneNumberEditText);
        requires.add(emailEditText);

        originalDrawable = fullNameEditText.getBackground();

        chooseDateOfBirthButton = findViewById(R.id.datePickerButton);
        dateOfBirthEditText = findViewById(R.id.dateOfBirth);
        calendarView = findViewById(R.id.getDateOfBirth);
        calendarView.setVisibility(View.GONE);
        hidden = true;

        homeTownEditText = findViewById(R.id.hometown);
        addressEditText = findViewById(R.id.address);

        majorRadioGroup = findViewById(R.id.majorGroup);

        programmingLanguageCheckBoxes = new ArrayList<>();
        programmingLanguageCheckBoxes.add(findViewById(R.id.checkbox1));
        programmingLanguageCheckBoxes.add(findViewById(R.id.checkbox2));
        programmingLanguageCheckBoxes.add(findViewById(R.id.checkbox3));
        programmingLanguageCheckBoxes.add(findViewById(R.id.checkbox4));
        programmingLanguageCheckBoxes.add(findViewById(R.id.checkbox5));

        agreeCheckBox = findViewById(R.id.agree);
        submitButton = findViewById(R.id.submit);
    }
}