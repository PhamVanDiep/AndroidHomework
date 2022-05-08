package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final long MAX = (long)Math.pow(10,5)*Integer.MAX_VALUE; // set max value is accepted when input
    private final long MIN = -MAX; // set min value is accepted when input
    private final int SMALL_TEXT_VIEW_MAX_LENGTH = 20; // set length to change text size of small text view
    private final int LARGE_TEXT_VIEW_MAX_LENGTH = 10; // set length to change text size of large text view
    private boolean isSigned; // check the number is negative or positive
    private boolean isCalculated; // check the calculation is calculated
    private long result;
    private TextView smallTextView;
    private TextView largeTextView;
    private long firstElement;
    private long secondElement;
    private char operator;
    private boolean preButtonIsOperator; // check the button that previous clicked is an operator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallTextView = findViewById(R.id.smallTextView);
        largeTextView = findViewById(R.id.largeTextView);

        // init for logic variables
        initProperties();
        // Change after clicked on CE button
        clearLargeEditText();
        // Change after clicked on C button
        clearAll();
        // Change after clicked on Backspace button
        backspaceClicked();
        // Change after click on operator buttons such: +; -; x; /; +/-
        operatorClicked();
        // Change after click on number buttons
        numberClicked();
    }

    private void numberClicked() {
        findViewById(R.id._0Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('0');
            }
        });

        findViewById(R.id._1Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('1');
            }
        });

        findViewById(R.id._2Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('2');
            }
        });

        findViewById(R.id._3Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('3');
            }
        });

        findViewById(R.id._4Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('4');
            }
        });

        findViewById(R.id._5Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('5');
            }
        });

        findViewById(R.id._6Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('6');
            }
        });

        findViewById(R.id._7Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('7');
            }
        });

        findViewById(R.id._8Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('8');
            }
        });

        findViewById(R.id._9Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('9');
            }
        });
    }
    // handle after clicked on a number button
    private void appendNumber(char c) {
        preButtonIsOperator = false;
        // Check if calculated and have no clicked on a number before
        if (isCalculated){
            isCalculated = false;
            largeTextView.setText("");
        }
        String largeEditTextString = largeTextView.getText().toString();
        largeEditTextString += c;
        if((Long.parseLong(largeEditTextString) <= MAX && largeEditTextString.charAt(0) != '-')
            || (Long.parseLong(largeEditTextString) >= MIN && largeEditTextString.charAt(0) == '-')){
            setText(largeTextView, largeEditTextString);
        } else if(largeEditTextString.charAt(0) != '-') {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setMessage("Cannot calculate with too large number!");
            alertDialogBuilder.show();
        }else {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setMessage("Cannot calculate with too small number!");
            alertDialogBuilder.show();
        }
    }

    private void operatorClicked() {
        findViewById(R.id.divButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('/');
            }
        });

        findViewById(R.id.multiButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('x');
            }
        });

        findViewById(R.id.subButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('-');
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('+');
            }
        });

        findViewById(R.id.calcButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('=');
            }
        });

        findViewById(R.id.signButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAfterClickOnAnOperator('s');
            }
        });
    }

    // handle after clicked on an operator button
    private void calculateAfterClickOnAnOperator(char operator){
        String smallTextViewString = smallTextView.getText().toString();
        String largeTextViewString = largeTextView.getText().toString();

        if(largeTextViewString.length() != 0){
            // if click on operator buttons such: +; -; x; /;
            if (operator == '/' || operator == '+' || operator == '-' || operator == 'x') {
                // check if operator number had clicked before
                if (smallTextViewString.length() != 0) {
                    if (!preButtonIsOperator){ // if not clicked on an operator before
                        preButtonIsOperator = true;
                        // set value for second parameter
                        secondElement = Long.parseLong(largeTextViewString);
                        // check if last operator is not '='
                        if (smallTextViewString.charAt(smallTextViewString.length() - 2) != '='){
                            calculate(firstElement, secondElement, this.operator);
                            if (isCalculated){ // if calculate successful
                                firstElement = result;
                                this.operator = operator;
                                String newSmallTextViewString = "" + result + " " + operator + " ";
                                setText(smallTextView, newSmallTextViewString);
                                setText(largeTextView, Long.toString(result));
                            }
                        }else{ // if last operator is '='
                            firstElement = result;
                            this.operator = operator;
                            String newSmallTextViewString = largeTextViewString + " " + operator + " ";
                            setText(smallTextView, newSmallTextViewString);
                            setText(largeTextView, "");
                        }
                    }else{ // if clicked on an operator before -> change operator
                        this.operator = operator;
                        String newSmallTextViewString = smallTextViewString.substring(0, smallTextViewString.length() - 2) + operator + " ";
                        setText(smallTextView, newSmallTextViewString);
                    }
                } else { // if has no operator clicked before
                    preButtonIsOperator = true;
                    firstElement = Long.parseLong(largeTextViewString);
                    this.operator = operator;
                    String newSmallEditText = largeTextViewString + " " + operator + " ";
                    setText(smallTextView, newSmallEditText);
                    setText(largeTextView, "");
                }
            }else if(operator == '=' ){ // if clicked on '=' button
                if (smallTextViewString.length() != 0){ // if had first parameter and operator
                    secondElement = Long.parseLong(largeTextViewString);
                    calculate(firstElement, secondElement, this.operator);
                    if (isCalculated){ // if calculated successful
                        preButtonIsOperator = false;
                        String newSmallEditText = "" + firstElement + " " + this.operator + " " + secondElement + " = ";
                        setText(smallTextView, newSmallEditText);
                        setText(largeTextView, Long.toString(result));
                    }
                }
            }else if(operator == 's' && !isCalculated){ // if clicked on +/- button
                String newLargeEditTextString = "";
                if(!isSigned){ // if had - sign
                    isSigned = true;
                    newLargeEditTextString = "-" + largeTextViewString;
                }else{
                    isSigned = false;
                    newLargeEditTextString = largeTextViewString.substring(1, largeTextViewString.length());
                }
                setText(largeTextView, newLargeEditTextString);
            }
        }
    }

    // calculate the calculation after clicked on '=' button or an operator button which had both first element and second element
    private void calculate(long firstElement, long secondElement, char operator){
        isCalculated = true;
        isSigned = false;
        if (operator == '/'){
            if (secondElement == 0){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Cannot divide by 0!");
                alertDialogBuilder.show();
            } else result = firstElement/secondElement;
        }else if(operator == '+'){
            if (MAX - secondElement < firstElement){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Result is too large");
                alertDialogBuilder.show();
            } else if (MIN - secondElement > firstElement){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Result is too small");
                alertDialogBuilder.show();
            } else result = firstElement + secondElement;
        }else if (operator == '-'){
            if (MAX + secondElement < firstElement){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Result is too large");
                alertDialogBuilder.show();
            } else if (MIN + secondElement > firstElement){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Result is too small");
                alertDialogBuilder.show();
            } else result = firstElement - secondElement;
        }else if(operator == 'x'){
            if (MAX / Math.abs(secondElement) < Math.abs(firstElement)){ // check if the result is overflow
                isCalculated = false;
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setMessage("Result is overflow");
                alertDialogBuilder.show();
            } else result = firstElement * secondElement;
        }
    }

    // handle when clicked on backspace button
    private void backspaceClicked() {
        ((ImageButton)findViewById(R.id.backspaceButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String largeEditTextString = largeTextView.getText().toString();
                if (!isCalculated && largeEditTextString.length() != 0){ // check if not calculated and largeEditText not null
                    if(largeEditTextString.charAt(largeEditTextString.length() - 1) == '-'){
                        isSigned = false;
                    }
                    String res = largeEditTextString.substring(0, largeEditTextString.length() - 1);
                    setText(largeTextView, res);
                }
            }
        });
    }

    // handle when click on 'C' button
    private void clearAll() {
        ((Button)findViewById(R.id.cButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText(smallTextView, "");
                setText(largeTextView, "");
                initProperties();
            }
        });
    }

    // handle when click on 'CE' button
    private void clearLargeEditText() {
        ((Button)findViewById(R.id.ceButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCalculated)
                setText(largeTextView, "");
            }
        });
    }

    // export result on small textview or large textview
    private void setText(TextView textView, String text){
        if (textView.getId() == smallTextView.getId()){
            checkSmallTextViewLength(text);
        }else{
            checkLargeTextViewLength(text);
        }
        textView.setText(text);
    }

    private void checkSmallTextViewLength(String string){
        if(string.length() >= SMALL_TEXT_VIEW_MAX_LENGTH){
            smallTextView.setTextSize(24);
        }else{
            smallTextView.setTextSize(32);
        }
    }

    private void checkLargeTextViewLength(String string){
        if(string.length() >= LARGE_TEXT_VIEW_MAX_LENGTH){
            largeTextView.setTextSize(56);
        }else{
            largeTextView.setTextSize(64);
        }
    }

    private void initProperties() {
        isSigned = false;
        result = 0;
        isCalculated = false;
        firstElement = 0;
        secondElement = 0;
        operator = ' ';
        preButtonIsOperator = false;
    }
}