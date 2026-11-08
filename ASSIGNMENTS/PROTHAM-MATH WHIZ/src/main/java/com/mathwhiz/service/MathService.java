package com.mathwhiz.service;

/**
 * Service class containing reusable mathematical functions for PROTHAM - MATH WHIZ.
 * Covers Prime Number check, Modulus operation, and Trigonometric calculations.
 */
public class MathService {

    /**
     * Tolerance used for comparing floating-point trigonometric answers.
     */
    public static final double TOLERANCE = 0.05;

    /**
     * TASK 03: Prime Number Function
     * Determines whether a given integer is a prime number.
     *
     * @param number the integer to check
     * @return 1 if prime, 0 otherwise. Numbers less than 2 are not prime.
     */
    public static int isPrime(int number) {
        if (number < 2) {
            return 0;
        }
        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * TASK 04: Modulus Function
     * Calculates the remainder of integer division (a % b).
     *
     * @param a the dividend integer
     * @param b the divisor integer (must not be zero)
     * @return the calculated remainder
     * @throws IllegalArgumentException if divisor is zero
     */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor must not be zero");
        }
        return a % b;
    }

    /**
     * TASK 05: Trigonometric Function - Sine
     * Calculates the sine of an angle given in degrees.
     *
     * @param degree angle in degrees
     * @return sine value rounded to 4 decimal places
     */
    public static double calculateSin(double degree) {
        double radians = Math.toRadians(degree);
        double result = Math.sin(radians);
        return roundToFourDecimals(result);
    }

    /**
     * TASK 05: Trigonometric Function - Cosine
     * Calculates the cosine of an angle given in degrees.
     *
     * @param degree angle in degrees
     * @return cosine value rounded to 4 decimal places
     */
    public static double calculateCos(double degree) {
        double radians = Math.toRadians(degree);
        double result = Math.cos(radians);
        return roundToFourDecimals(result);
    }

    /**
     * TASK 05: Trigonometric Function - Tangent
     * Calculates the tangent of an angle given in degrees.
     * Handles undefined angles (e.g., 90, 270, -90, -270 degrees).
     *
     * @param degree angle in degrees
     * @return tangent value rounded to 4 decimal places
     * @throws ArithmeticException if tangent is undefined at the given angle
     */
    public static double calculateTan(double degree) {
        // Tangent is undefined at odd multiples of 90 degrees: 90 + k*180
        double normalized = Math.abs(degree) % 180.0;
        if (Math.abs(normalized - 90.0) < 1e-6) {
            throw new ArithmeticException("Tangent is undefined for angle " + degree + "°");
        }
        double radians = Math.toRadians(degree);
        double result = Math.tan(radians);
        return roundToFourDecimals(result);
    }

    /**
     * Compares user answer with expected trigonometric value within specified tolerance.
     *
     * @param expected the dynamically calculated expected answer
     * @param actual the player's submitted answer
     * @param tolerance acceptable margin of error (e.g. 0.05)
     * @return true if difference is within tolerance, false otherwise
     */
    public static boolean isTrigEqual(double expected, double actual, double tolerance) {
        return Math.abs(expected - actual) <= tolerance;
    }

    /**
     * Utility method to round floating point numbers to 4 decimal places
     * to eliminate standard IEEE-754 precision artifacts (e.g., cos(90) -> 0.0).
     */
    public static double roundToFourDecimals(double value) {
        if (Math.abs(value) < 1e-9) {
            return 0.0;
        }
        return Math.round(value * 10000.0) / 10000.0;
    }
}
