package com.example.android.seinfeldtrivia;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Declaring variables - Question 1
    RadioButton Q1A1, Q1A2, Q1A3;
    RadioGroup Q1RadioGroup;

    //Declaring variables - Question 2
    RadioButton Q2A1, Q2A2, Q2A3;
    RadioGroup Q2RadioGroup;

    //Declaring variables - Question 3
    EditText Q3AnswerEditText;

    //Declaring variable for extracting the string from the EditText on Q3
    String question3Answer;

    //Declaring variables - Question 4
    CheckBox Q4A1, Q4A2, Q4A3;

    //Declaring variables to check the state of the checkboxes on Q4
    boolean checkedQ4A1, checkedQ4A2, checkedQ4A3;

    //Declaring variables - Question 5
    RadioButton Q5A1, Q5A2, Q5A3;
    RadioGroup Q5RadioGroup;

    //Declaring variable - Score
    int score;

    //Declaring variable for the score and reset button
    Button scoreMe, resetQuiz;

    //Declaring variables to whether the user's answer was correct or incorrect
    RadioButton userAnswerQ1, userAnswerQ2, userAnswerQ5;
    boolean userAnswerQ3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views - Question 1
        Q1RadioGroup = (RadioGroup) findViewById(R.id.question_1);
        Q1A1 = (RadioButton) findViewById(R.id.question_one_answer_one);
        Q1A2 = (RadioButton) findViewById(R.id.question_one_answer_two);
        Q1A3 = (RadioButton) findViewById(R.id.question_one_answer_three);

        //Initializing views - Question 2
        Q2RadioGroup = (RadioGroup) findViewById(R.id.question_2);
        Q2A1 = (RadioButton) findViewById(R.id.question_two_answer_one);
        Q2A2 = (RadioButton) findViewById(R.id.question_two_answer_two);
        Q2A3 = (RadioButton) findViewById(R.id.question_two_answer_three);

        //Initializing views - Question 3
        Q3AnswerEditText = (EditText) findViewById(R.id.question_three_answer);

        //Initializing views - Question 4
        Q4A1 = (CheckBox) findViewById(R.id.question_four_answer_one);
        Q4A2 = (CheckBox) findViewById(R.id.question_four_answer_two);
        Q4A3 = (CheckBox) findViewById(R.id.question_four_answer_three);

        //Initializing views - Question 5
        Q5RadioGroup = (RadioGroup) findViewById(R.id.question_5);
        Q5A1 = (RadioButton) findViewById(R.id.question_five_answer_one);
        Q5A2 = (RadioButton) findViewById(R.id.question_five_answer_two);
        Q5A3 = (RadioButton) findViewById(R.id.question_five_answer_three);

        //Initializing views - Score & Reset buttons
        scoreMe = (Button) findViewById(R.id.score_me);
        resetQuiz = (Button) findViewById(R.id.reset_me);
    }

    //To disable the radio groups once the user has chosen an answer
    //https://stackoverflow.com/questions/19224560/view-setenabledfalse-does-not-work-quite-right-in-android/19235037#19235037
    public static void enableQuestion(View view, boolean enabled) {
        view.setEnabled(enabled);
        view.setFocusable(enabled);

        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++)
                enableQuestion(vg.getChildAt(i), enabled);
        }
    }

    //Getting the values of the answers entered
    /*
     * Checking which answer was selected - Question 1
     */
    public void onRadioButtonQ1Clicked(View view) {
        // Check that the user chose an answer
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.question_one_answer_one:
                if (checked) {
                    userAnswerQ1 = Q1A1;
                    enableQuestion(Q1RadioGroup, false);
                    break;
                }
                //Correct answer
            case R.id.question_one_answer_two:
                if (checked) {
                    userAnswerQ1 = Q1A2;
                    score++;
                    enableQuestion(Q1RadioGroup, false);
                    break;
                }
            case R.id.question_one_answer_three:
                if (checked) {
                    userAnswerQ1 = Q1A3;
                    enableQuestion(Q1RadioGroup, false);
                    break;
                }
        }
    }

    /*
     * Checking which answer was selected - Question 2
     */
    public void onRadioButtonQ2Clicked(View view) {
        // Check that the user chose an answer
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.question_two_answer_one:
                if (checked) {
                    userAnswerQ2 = Q2A1;
                    enableQuestion(Q2RadioGroup, false);
                    break;
                }
            case R.id.question_two_answer_two:
                if (checked) {
                    userAnswerQ2 = Q2A2;
                    enableQuestion(Q2RadioGroup, false);
                    break;
                }
                //Correct answer
            case R.id.question_two_answer_three:
                if (checked) {
                    userAnswerQ2 = Q2A3;
                    score++;
                    enableQuestion(Q2RadioGroup, false);
                    break;
                }
        }
    }

    /*
     * Checking which answers were selected - Question 4
     */
    public void onCheckBoxesQ4Clicked(View view) {
        checkedQ4A1 = Q4A1.isChecked();
        checkedQ4A2 = Q4A2.isChecked();
        checkedQ4A3 = Q4A3.isChecked();

        if (checkedQ4A1 && checkedQ4A3 && !checkedQ4A2) {
            score++;
        }

        // Check which checkbox button was checked
        switch(view.getId()) {
            case R.id.question_four_answer_one:
                if (checkedQ4A1) {
                    enableQuestion(Q4A1, false);
                    break;
                }
            case R.id.question_four_answer_two:
                if (checkedQ4A2) {
                    enableQuestion(Q4A2, false);
                    break;
                }
            case R.id.question_four_answer_three:
                if (checkedQ4A3) {
                    enableQuestion(Q4A3, false);
                    break;
                }
        }
    }

    /*
     * Checking which answer was selected - Question 5
     */
    public void onRadioButtonQ5Clicked(View view) {
        // Check that the user chose an answer
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.question_five_answer_one:
                if (checked) {
                    userAnswerQ5 = Q5A1;
                    enableQuestion(Q5RadioGroup, false);
                    break;
                }
            case R.id.question_five_answer_two:
                if (checked) {
                    userAnswerQ5 = Q5A2;
                    enableQuestion(Q5RadioGroup, false);
                    break;
                }
                //Correct answer
            case R.id.question_five_answer_three:
                if (checked) {
                    userAnswerQ5 = Q5A3;
                    score++;
                    enableQuestion(Q5RadioGroup, false);
                    break;
                }
        }
    }


    /*
     * The following 5 methods are used to get the user's answer and to display said answers in red
     * if it's incorrect and in green if it's correct.
    */
    public void usersAnswerToQ1(RadioButton yourAnswerQ1) {
        if (yourAnswerQ1 == Q1A1) {
            Q1A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (yourAnswerQ1 == Q1A2) {
            Q1A2.setTextColor(getResources().getColor(R.color.color_correct_answer));
        } else {
            Q1A3.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        }
    }

    public void usersAnswerToQ2(RadioButton yourAnswerQ2) {
        if (yourAnswerQ2 == Q2A1) {
            Q2A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (yourAnswerQ2 == Q2A2) {
            Q2A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else {
            Q2A3.setTextColor(getResources().getColor(R.color.color_correct_answer));
        }
    }

    public void usersAnswerToQ3(boolean userAnswerQ3) {
        if (userAnswerQ3) {
            Q3AnswerEditText.setTextColor(getResources().getColor(R.color.color_correct_answer));
        } else {
            Q3AnswerEditText.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        }
    }

    public void usersAnswerToQ4(boolean checkedQ4A1, boolean checkedQ4A2, boolean checkedQ4A3) {
        if (checkedQ4A1 && checkedQ4A3 && !checkedQ4A2) {
            Q4A1.setTextColor(getResources().getColor(R.color.color_correct_answer));
            Q4A3.setTextColor(getResources().getColor(R.color.color_correct_answer));
        } else if (checkedQ4A1 && checkedQ4A2 && checkedQ4A3) {
            Q4A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
            Q4A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
            Q4A3.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (checkedQ4A1 && checkedQ4A2) {
            Q4A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
            Q4A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (checkedQ4A2 && checkedQ4A3) {
            Q4A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
            Q4A3.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (checkedQ4A1) {
            Q4A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (checkedQ4A2) {
            Q4A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (checkedQ4A3) {
            Q4A3.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        }
    }

    public void usersAnswerToQ5(RadioButton userAnswerQ5) {
        if (userAnswerQ5 == Q5A1) {
            Q5A1.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else if (userAnswerQ5 == Q5A2) {
            Q5A2.setTextColor(getResources().getColor(R.color.color_incorrect_answer));
        } else {
            Q5A3.setTextColor(getResources().getColor(R.color.color_correct_answer));
        }
    }

    public void showScoreMessage(View view) {
        //Checking the answer on Question 3
        //Answer inspired from an answer to my question on the Udacity ABND forum
        question3Answer = Q3AnswerEditText.getText().toString();
        if (!question3Answer.isEmpty()){
            Q3AnswerEditText.setEnabled(false);
            if (question3Answer.equalsIgnoreCase("cosmo")) {
                score++;
                userAnswerQ3 = true;
            }
        }

        //Building the score message and a custom message according to what score the user got
        String scoreMessage = String.format(getResources().getString(R.string.your_score_is), score);
        String customMessage = "";

        if (score == 0 || score == 1) {
            customMessage = getResources().getString(R.string.message_scores_0_1);
        } else if (score == 2 || score == 3) {
            customMessage = getResources().getString(R.string.message_scores_2_3);
        } else {
            customMessage = getResources().getString(R.string.message_scores_4_5);
        }

        //Displaying the score message and the score
        Toast.makeText(this, scoreMessage + customMessage, Toast.LENGTH_SHORT).show();

        //Changing the answer's colors according to correct and incorrect
        usersAnswerToQ1(userAnswerQ1);
        usersAnswerToQ2(userAnswerQ2);
        usersAnswerToQ3(userAnswerQ3);
        usersAnswerToQ4(checkedQ4A1, checkedQ4A2, checkedQ4A3);
        usersAnswerToQ5(userAnswerQ5);
    }

    public void enableAllQuestions() {
        //Enabling the questions - radio buttons
        enableQuestion(Q1RadioGroup, true);
        enableQuestion(Q2RadioGroup, true);
        enableQuestion(Q5RadioGroup, true);

        //Resetting questions: clearing the checked radio buttons and checkboxes
        Q1RadioGroup.clearCheck();
        Q2RadioGroup.clearCheck();
        Q5RadioGroup.clearCheck();

        //Resetting questions: Q3 - deleting the text in the EditText
        Q3AnswerEditText.setText("");
        Q3AnswerEditText.setEnabled(true);

        //if the checkboxes are checked, then uncheck and enable them
        if (checkedQ4A1) {
            Q4A1.setChecked(false);
            enableQuestion(Q4A1, true);
        }
        if (checkedQ4A2) {
            Q4A2.setChecked(false);
            enableQuestion(Q4A2, true);
        }
        if (checkedQ4A3) {
            Q4A3.setChecked(false);
            enableQuestion(Q4A3, true);
        }
    }

    public void answersInOriginalColors() {
        //Question 1
        Q1A1.setTextColor(getResources().getColor(R.color.color_text));
        Q1A2.setTextColor(getResources().getColor(R.color.color_text));
        Q1A3.setTextColor(getResources().getColor(R.color.color_text));

        //Question 2
        Q2A1.setTextColor(getResources().getColor(R.color.color_text));
        Q2A2.setTextColor(getResources().getColor(R.color.color_text));
        Q2A3.setTextColor(getResources().getColor(R.color.color_text));

        //Question 3
        Q3AnswerEditText.setTextColor(getResources().getColor(R.color.color_text));

        //Question 4
        Q4A1.setTextColor(getResources().getColor(R.color.color_text));
        Q4A2.setTextColor(getResources().getColor(R.color.color_text));
        Q4A3.setTextColor(getResources().getColor(R.color.color_text));

        //Question 5
        Q5A1.setTextColor(getResources().getColor(R.color.color_text));
        Q5A2.setTextColor(getResources().getColor(R.color.color_text));
        Q5A3.setTextColor(getResources().getColor(R.color.color_text));
    }

    public void resetQuiz(View view) {
        //Enabling radio buttons and checkboxes
        enableAllQuestions();

        //Resetting the score variable
        score = 0;

        //Resetting Q3 string variable
        question3Answer = "";

        //Resetting the answers to their original colors
        answersInOriginalColors();
    }

    //To get rid of the focus on the EditText and to hide the keyboard
    //https://github.com/amaliaman/android-tips/blob/master/tips/disable-edittext-focus.md
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
