package com.example.physicsmate.activities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.solveEquationNumeric;
import static com.example.physicsmate.Custom_methods.solveEquationSymbolic;

public class CollisionTest {

    @Test
    public void test() {

        //initialize the variables
        double M1 = Double.NaN, M2 = Double.NaN, V1 = Double.NaN, V2 = Double.NaN, A1 = Double.NaN, A2 = Double.NaN, B1 = Double.NaN, B2 = Double.NaN, U1 = Double.NaN, U2 = Double.NaN;

        String eqn1 = "v1x = (u1 * cos(A1 - Abs(A1-A2)) * (m1 - m2) + 2 * m2 * u2 * cos(A2 - Abs(A1-A2))) / (m1 + m2) * cos(Abs(A1-A2)) + u2 * sin(A1 - Abs(A1-A2)) * sin(Abs(A1-A2) + pi/2)";
        String eqn2 = "v1y = (u1 * cos(A1 - Abs(A1-A2)) * (m1 - m2) + 2 * m2 * u2 * cos(A2 - Abs(A1-A2))) / (m1 + m2) * sin(Abs(A1-A2)) + u1 * sin(A1 - Abs(A1-A2)) * sin(Abs(A1-A2) + pi/2)";
        String eqn3 = "v2x = (u2 * cos(A2 - Abs(A1-A2)) * (m2 - m1) + 2 * m1 * u1 * cos(A1 - Abs(A1-A2))) / (m1 + m2) * cos(Abs(A1-A2)) + u2 * sin(A2 - Abs(A1-A2)) * sin(Abs(A1-A2) + pi/2)";
        String eqn4 = "v2y = (u2 * cos(A2 - Abs(A1-A2)) * (m2 - m1) + 2 * m1 * u1 * cos(A1 - Abs(A1-A2))) / (m1 + m2) * sin(Abs(A1-A2)) + u2 * sin(A2 - Abs(A1-A2)) * sin(Abs(A1-A2) + pi/2)";
        String eqn5 = "tan(B1) = v1y/v1x";
        String eqn6 = "tan(B2) = v2y/v2x";
        String eqn7 = "v1 = sqrt(v1x^2 + v1y^2)";
        String eqn8 = "v2 = sqrt(v2x^2 + v2y^2)";
        String eqn15 = "m1*u1*cos(A1) + m2*u2*cos(A2) = m1*v1x + m2*v2x";


        String eqn9="M1=2";
        String eqn10="M2=2";
        String eqn11="A1=0";
        String eqn12="A2=pi";
        String eqn13="U1=3";
        String eqn14="U2=4";

        //replace the variables in the equations with the values entered by the user
        /*if (!Double.isNaN(M1)) {
            eqn1 = eqn1.replace("m1", String.valueOf(M1));
            eqn2 = eqn2.replace("m1", String.valueOf(M1));
            eqn3 = eqn3.replace("m1", String.valueOf(M1));
            eqn4 = eqn4.replace("m1", String.valueOf(M1));
            eqn5 = eqn5.replace("m1", String.valueOf(M1));
            eqn6 = eqn6.replace("m1", String.valueOf(M1));
        }

        if (!Double.isNaN(M2)) {
            eqn1 = eqn1.replace("m2", String.valueOf(M2));
            eqn2 = eqn2.replace("m2", String.valueOf(M2));
            eqn3 = eqn3.replace("m2", String.valueOf(M2));
            eqn4 = eqn4.replace("m2", String.valueOf(M2));
            eqn5 = eqn5.replace("m2", String.valueOf(M2));
            eqn6 = eqn6.replace("m2", String.valueOf(M2));
        }

        if (!Double.isNaN(V1)) {
            eqn1 = eqn1.replace("v1", String.valueOf(V1));
            eqn2 = eqn2.replace("v1", String.valueOf(V1));
            eqn3 = eqn3.replace("v1", String.valueOf(V1));
            eqn4 = eqn4.replace("v1", String.valueOf(V1));
            eqn5 = eqn5.replace("v1", String.valueOf(V1));
            eqn6 = eqn6.replace("v1", String.valueOf(V1));
        }

        if (!Double.isNaN(V2)) {
            eqn1 = eqn1.replace("v2", String.valueOf(V2));
            eqn2 = eqn2.replace("v2", String.valueOf(V2));
            eqn3 = eqn3.replace("v2", String.valueOf(V2));
            eqn4 = eqn4.replace("v2", String.valueOf(V2));
            eqn5 = eqn5.replace("v2", String.valueOf(V2));
            eqn6 = eqn6.replace("v2", String.valueOf(V2));
        }

        if (!Double.isNaN(A1)) {
            eqn1 = eqn1.replace("A1", String.valueOf(A1));
            eqn2 = eqn2.replace("A1", String.valueOf(A1));
            eqn3 = eqn3.replace("A1", String.valueOf(A1));
            eqn4 = eqn4.replace("A1", String.valueOf(A1));
            eqn5 = eqn5.replace("A1", String.valueOf(A1));
            eqn6 = eqn6.replace("A1", String.valueOf(A1));
        }

        if (!Double.isNaN(A2)) {
            eqn1 = eqn1.replace("A2", String.valueOf(A2));
            eqn2 = eqn2.replace("A2", String.valueOf(A2));
            eqn3 = eqn3.replace("A2", String.valueOf(A2));
            eqn4 = eqn4.replace("A2", String.valueOf(A2));
            eqn5 = eqn5.replace("A2", String.valueOf(A2));
            eqn6 = eqn6.replace("A2", String.valueOf(A2));
        }

        if (!Double.isNaN(B1)) {
            eqn1 = eqn1.replace("B1", String.valueOf(B1));
            eqn2 = eqn2.replace("B1", String.valueOf(B1));
            eqn3 = eqn3.replace("B1", String.valueOf(B1));
            eqn4 = eqn4.replace("B1", String.valueOf(B1));
            eqn5 = eqn5.replace("B1", String.valueOf(B1));
            eqn6 = eqn6.replace("B1", String.valueOf(B1));
        }

        if (!Double.isNaN(B2)) {
            eqn1 = eqn1.replace("B2", String.valueOf(B2));
            eqn2 = eqn2.replace("B2", String.valueOf(B2));
            eqn3 = eqn3.replace("B2", String.valueOf(B2));
            eqn4 = eqn4.replace("B2", String.valueOf(B2));
            eqn5 = eqn5.replace("B2", String.valueOf(B2));
            eqn6 = eqn6.replace("B2", String.valueOf(B2));
        }

        if (!Double.isNaN(U1)) {
            eqn1 = eqn1.replace("u1", String.valueOf(U1));
            eqn2 = eqn2.replace("u1", String.valueOf(U1));
            eqn3 = eqn3.replace("u1", String.valueOf(U1));
            eqn4 = eqn4.replace("u1", String.valueOf(U1));
            eqn5 = eqn5.replace("u1", String.valueOf(U1));
            eqn6 = eqn6.replace("u1", String.valueOf(U1));
        }

        if (!Double.isNaN(U2)) {
            eqn1 = eqn1.replace("u2", String.valueOf(U2));
            eqn2 = eqn2.replace("u2", String.valueOf(U2));
            eqn3 = eqn3.replace("u2", String.valueOf(U2));
            eqn4 = eqn4.replace("u2", String.valueOf(U2));
            eqn5 = eqn5.replace("u2", String.valueOf(U2));
            eqn6 = eqn6.replace("u2", String.valueOf(U2));
        }*/

        //use symja to solve the equations for 4 unknowns when the user enters 6 values
        Collection<String> equations = new ArrayList<>();
        equations.add(eqn1);
        equations.add(eqn2);
        equations.add(eqn3);
        equations.add(eqn4);
        equations.add(eqn5);
        equations.add(eqn6);
        equations.add(eqn7);
        equations.add(eqn8);
        //equations.add(eqn9);
        //equations.add(eqn10);
        //equations.add(eqn11);
        //equations.add(eqn12);
        //equations.add(eqn13);
        //equations.add(eqn14);
        equations.add(eqn15);

        //obtain the list of unknown variables which the user has not entered
        Collection<String> unknowns = new ArrayList<>();
        if (Double.isNaN(M1)) {
            unknowns.add("m1");
        }
        if (Double.isNaN(M2)) {
            unknowns.add("m2");
        }
        if (Double.isNaN(V1)) {
            unknowns.add("v1");
        }
        if (Double.isNaN(V2)) {
            unknowns.add("v2");
        }
        if (Double.isNaN(A1)) {
            unknowns.add("A1");
        }
        if (Double.isNaN(A2)) {
            unknowns.add("A2");
        }
        if (Double.isNaN(B1)) {
            unknowns.add("B1");
        }
        if (Double.isNaN(B2)) {
            unknowns.add("B2");
        }
        if (Double.isNaN(U1)) {
            unknowns.add("u1");
        }
        if (Double.isNaN(U2)) {
            unknowns.add("u2");
        }

        //solve the equations
        String solution = solveEquationNumeric(equations, "m1, m2, v1, v2, A1, A2, B1, B2, u1, u2, v1x, v1y, v2x, v2y");
        System.out.println(solution);

    }
}
