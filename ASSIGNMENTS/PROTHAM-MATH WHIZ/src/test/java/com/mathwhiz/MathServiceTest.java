package com.mathwhiz;

import com.mathwhiz.service.MathService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests verifying TASK 03, TASK 04, TASK 05, and TASK 09 requirements.
 */
public class MathServiceTest {

    @Test
    public void testPrimeFunction() {
        // Test case: 7 is prime -> 1
        assertEquals(1, MathService.isPrime(7));

        // Test case: 8 is not prime -> 0
        assertEquals(0, MathService.isPrime(8));

        // Test case: 1 is not prime -> 0
        assertEquals(0, MathService.isPrime(1));

        // Edge cases
        assertEquals(0, MathService.isPrime(0));
        assertEquals(0, MathService.isPrime(-5));
        assertEquals(1, MathService.isPrime(2));
        assertEquals(1, MathService.isPrime(13));
        assertEquals(0, MathService.isPrime(15));
    }

    @Test
    public void testModulusFunction() {
        // Test case: 17 % 5 -> 2
        assertEquals(2, MathService.calculateMod(17, 5));

        // Test other modulus values
        assertEquals(0, MathService.calculateMod(20, 5));
        assertEquals(3, MathService.calculateMod(23, 4));
        assertEquals(-2, MathService.calculateMod(-17, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testModulusZeroDivisor() {
        // Divisor: 0 -> should throw IllegalArgumentException
        MathService.calculateMod(10, 0);
    }

    @Test
    public void testTrigonometricSine() {
        // Test case: Sine 30° -> 0.5
        double sin30 = MathService.calculateSin(30.0);
        assertEquals(0.5, sin30, 0.001);

        // Sine 0° -> 0.0
        assertEquals(0.0, MathService.calculateSin(0.0), 0.001);

        // Sine 90° -> 1.0
        assertEquals(1.0, MathService.calculateSin(90.0), 0.001);
    }

    @Test
    public void testTrigonometricCosine() {
        // Test case: Cosine 60° -> 0.5
        double cos60 = MathService.calculateCos(60.0);
        assertEquals(0.5, cos60, 0.001);

        // Cosine 0° -> 1.0
        assertEquals(1.0, MathService.calculateCos(0.0), 0.001);

        // Cosine 90° -> 0.0
        assertEquals(0.0, MathService.calculateCos(90.0), 0.001);
    }

    @Test
    public void testTrigonometricTangent() {
        // Test case: Tangent 45° -> 1.0
        double tan45 = MathService.calculateTan(45.0);
        assertEquals(1.0, tan45, 0.001);

        // Tangent 0° -> 0.0
        assertEquals(0.0, MathService.calculateTan(0.0), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testTangentUndefinedAngle() {
        // Tangent 90° is undefined
        MathService.calculateTan(90.0);
    }

    @Test
    public void testTrigToleranceComparison() {
        // User inputs 0.5 for sin(30°)
        assertTrue(MathService.isTrigEqual(0.5, 0.5, MathService.TOLERANCE));
        // User inputs 0.501
        assertTrue(MathService.isTrigEqual(0.5, 0.501, MathService.TOLERANCE));
        // User inputs 0.8
        assertFalse(MathService.isTrigEqual(0.5, 0.8, MathService.TOLERANCE));
    }
}
