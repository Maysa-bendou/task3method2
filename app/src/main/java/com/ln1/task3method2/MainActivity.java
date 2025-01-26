package com.ln1.task3method2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Components
        EditText inputA = findViewById(R.id.inputA);
        EditText inputB = findViewById(R.id.inputB);
        EditText inputC = findViewById(R.id.inputC);
        Button btnSolve = findViewById(R.id.btnSolve);
        Button btnReset = findViewById(R.id.btnReset);
        TextView solutions = findViewById(R.id.solutions);

        // Solve Button Logic
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if all input fields are filled
                if (inputA.getText().toString().isEmpty() || inputB.getText().toString().isEmpty() || inputC.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the coefficients.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Parse inputs as doubles
                    double a = Double.parseDouble(inputA.getText().toString());
                    double b = Double.parseDouble(inputB.getText().toString());
                    double c = Double.parseDouble(inputC.getText().toString());

                    // Handle case where 'a' is zero (linear equation scenario)
                    if (a == 0) {
                        if (b != 0) {
                            // Solve linear equation: bx + c = 0
                            double root = -c / b;
                            solutions.setText(String.format("Solution: Root = %.2f", root));
                        } else {
                            solutions.setText("No solution (both 'a' and 'b' cannot be zero).");
                        }
                        return;
                    }

                    // Handle quadratic equation: ax² + bx + c = 0
                    double discriminant = b * b - 4 * a * c;

                    if (discriminant > 0) {
                        // Two real solutions
                        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                        solutions.setText(String.format("Solutions:\nRoot 1 = %.2f\nRoot 2 = %.2f", root1, root2));
                    } else if (discriminant == 0) {
                        // One real solution (double root)
                        double root = -b / (2 * a);
                        solutions.setText(String.format("Solution: Root = %.2f", root));
                    } else {
                        // Complex solutions
                        double realPart = -b / (2 * a);
                        double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                        solutions.setText(String.format("Complex Solutions:\nRoot 1 = %.2f + %.2fi\nRoot 2 = %.2f - %.2fi", realPart, imaginaryPart, realPart, imaginaryPart));
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input (non-numeric values)
                    Toast.makeText(MainActivity.this, "Please enter valid numbers for the coefficients.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Reset Button Logic
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all fields and reset the results
                inputA.setText("");
                inputB.setText("");
                inputC.setText("");
                solutions.setText("Enter coefficients to find solutions.");
            }
        });
    }
}

