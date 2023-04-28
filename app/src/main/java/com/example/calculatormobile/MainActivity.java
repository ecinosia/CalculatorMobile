package com.example.calculatormobile;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTextView, solutionTextView;
    MaterialButton cButton, plusMinusButton, percentageButton;
    MaterialButton divideButton, multiplyButton, plusButton, minusButton, equalsButton;
    MaterialButton zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
    MaterialButton dotButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_textview);
        solutionTextView = findViewById(R.id.solution_textview);

        assignId(cButton,R.id.c_button);
        assignId(plusMinusButton,R.id.plus_minus_button);
        assignId(percentageButton,R.id.percentage_button);
        assignId(divideButton,R.id.divide_button);
        assignId(multiplyButton,R.id.multiply_button);
        assignId(plusButton,R.id.plus_button);
        assignId(minusButton,R.id.minus_button);
        assignId(equalsButton,R.id.equals_button);
        assignId(zeroButton,R.id.zero_button);
        assignId(oneButton,R.id.one_button);
        assignId(twoButton,R.id.two_button);
        assignId(threeButton,R.id.three_button);
        assignId(fourButton,R.id.four_button);
        assignId(fiveButton,R.id.five_button);
        assignId(sixButton,R.id.six_button);
        assignId(sevenButton,R.id.seven_button);
        assignId(eightButton,R.id.eight_button);
        assignId(nineButton,R.id.nine_button);
        assignId(dotButton,R.id.dot_button);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String numberToCalculate = solutionTextView.getText().toString();


        if(buttonText.equals("=")){
            solutionTextView.setText(resultTextView.getText());
            return;
        }

        if(buttonText.equals("+/-")){
            resultTextView.setText("-"+numberToCalculate);
            solutionTextView.setText(resultTextView.getText().toString());

        }


        if(buttonText.equals("C")){
            numberToCalculate = numberToCalculate.replace(numberToCalculate,"0");
        }else{
            numberToCalculate = numberToCalculate+buttonText;
        }
        solutionTextView.setText(numberToCalculate);

        String finalResult = getResult(numberToCalculate);

        if(!finalResult.equals("Error")){
            resultTextView.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}