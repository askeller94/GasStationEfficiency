package com.askapps.gasstationefficiency;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText distanceGas1, distanceGas2, costGas1, costGas2, mpg, gallonsToFill;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distanceGas1 = findViewById(R.id.distance_gas1);
        distanceGas2 = findViewById(R.id.distance_gas2);
        costGas1 = findViewById(R.id.cost_gas1);
        costGas2 = findViewById(R.id.cost_gas2);
        mpg = findViewById(R.id.mpg);
        gallonsToFill = findViewById(R.id.gallons_to_fill);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(distanceGas1.getText().toString().trim().length() == 0 || distanceGas2.getText().toString().trim().length() == 0 || costGas1.getText().toString().trim().length() == 0 || costGas2.getText().toString().trim().length() == 0 || mpg.getText().toString().trim().length() == 0 || gallonsToFill.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                double distanceToGasStation1 = Double.parseDouble(distanceGas1.getText().toString());
                double distanceToGasStation2 = Double.parseDouble(distanceGas2.getText().toString());
                double costOfGas1 = Double.parseDouble(costGas1.getText().toString());
                double costOfGas2 = Double.parseDouble(costGas2.getText().toString());
                double vehicleMpg = Double.parseDouble(mpg.getText().toString());
                double gallons = Double.parseDouble(gallonsToFill.getText().toString());

                double costToFillTank1 = gallons * costOfGas1;
                double costToFillTank2 = gallons * costOfGas2;

                double costToDriveToGasStation1 = (distanceToGasStation1 / vehicleMpg) * costOfGas1;
                double costToDriveToGasStation2 = (distanceToGasStation2 / vehicleMpg) * costOfGas2;

                double totalCostGasStation1 = costToFillTank1 + costToDriveToGasStation1;
                double totalCostGasStation2 = costToFillTank2 + costToDriveToGasStation2;

                String mostEfficientGasStation;
                double lowestCost;

                if(totalCostGasStation1 < totalCostGasStation2) {
                    mostEfficientGasStation = "Gas Station 1";
                    lowestCost = totalCostGasStation1;
                } else {
                    mostEfficientGasStation = "Gas Station 2";
                    lowestCost = totalCostGasStation2;
                }
                DecimalFormat df = new DecimalFormat("#.00");
                result.setText("The most efficient gas station is " + mostEfficientGasStation + " with a total cost of $" + df.format(lowestCost));


            }
        });
    }
}