package com.askapps.gasstationefficiency;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

/*MainActivity class in the gas station efficiency app.
Contains the logic to calculate the most efficient gas station.*/
public class MainActivity extends AppCompatActivity {

    /*Declaring Variables to be called/sent to user application form*/
    private EditText distanceGas1, distanceGas2, costGas1, costGas2, mpg, gallonsToFill;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*call inputs from user and assign to matching variable declared above*/
        Button calculate;
        distanceGas1 = findViewById(R.id.distance_gas1);
        distanceGas2 = findViewById(R.id.distance_gas2);
        costGas1 = findViewById(R.id.cost_gas1);
        costGas2 = findViewById(R.id.cost_gas2);
        mpg = findViewById(R.id.mpg);
        gallonsToFill = findViewById(R.id.gallons_to_fill);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);


        calculate.setOnClickListener((v) -> {
            /*Validate user inputs via Validation class and declare values to an array*/
            EditText[] editTexts = new EditText[]{distanceGas1, distanceGas2, costGas1, costGas2, mpg, gallonsToFill};
            if (Validation.isAnyEditTextEmpty(editTexts) || !Validation.isValueGreaterThanZero(editTexts)) {
                Toast toast = Toast.makeText(MainActivity.this, "Fill all values, higher than zero.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show(); /*Send user popup error message detailing all inputs need a value and it must be greater than zero*/
                return;
            }
            /*Reuse above array to create a new array that converts text inputs to double values*/
            double[] values = new double[editTexts.length];
            for (int i = 0; i < editTexts.length; i++) {
                values[i] = Double.parseDouble(editTexts[i].getText().toString());
            }
            /*Assign values to variables to used in calculation*/
            double distanceToGasStation1 = values[0],
                    distanceToGasStation2 = values[1],
                    costOfGas1 = values[2],
                    costOfGas2 = values[3],
                    vehicleMpg = values[4],
                    gallons = values[5];
            /*Calculate the cost to fill the tank at each gas station*/
            double costToFillTank1 = gallons * costOfGas1;
            double costToFillTank2 = gallons * costOfGas2;
            /*Calculate the cost to drive to each gas station*/
            double costToDriveToGasStation1 = (distanceToGasStation1 / vehicleMpg) * costOfGas1;
            double costToDriveToGasStation2 = (distanceToGasStation2 / vehicleMpg) * costOfGas2;
            /*Calculate the total cost of each gas station*/
            double totalCostGasStation1 = costToFillTank1 + costToDriveToGasStation1;
            double totalCostGasStation2 = costToFillTank2 + costToDriveToGasStation2;
            /*Declare variables used in output*/
            String mostEfficientGasStation;
            double lowestCost;
                /*Use if else logic to determine cheapest station from output
                of the calculation above*/
            if (totalCostGasStation1 < totalCostGasStation2) {
                mostEfficientGasStation = "Gas Station 1";
                lowestCost = totalCostGasStation1;
            } else {
                mostEfficientGasStation = "Gas Station 2";
                lowestCost = totalCostGasStation2;
            } /*Format into 2 decimal places as is currency format and return result to user*/
            DecimalFormat df = new DecimalFormat("#.00");
            String cost = df.format(lowestCost);
            result.setText(getString(R.string.result_text, mostEfficientGasStation, cost));
        });
    }
}