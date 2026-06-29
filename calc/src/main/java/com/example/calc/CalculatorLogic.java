package com.example.calc;
public class CalculatorLogic {

    public String evaluate(String input) {
        try {
            if (input == null || input.isEmpty()) return "";

            input = input.replace("√", "sqrt");

            // Handle scientific functions
            if (input.startsWith("sin(")) return formatResult(Math.sin(Math.toRadians(parseInner(input))));
            if (input.startsWith("cos(")) return formatResult(Math.cos(Math.toRadians(parseInner(input))));
            if (input.startsWith("tan(")) return formatResult(Math.tan(Math.toRadians(parseInner(input))));
            if (input.startsWith("log(")) return parseInner(input) <= 0 ? "Math Error" :
                    formatResult(Math.log10(parseInner(input)));
            if (input.startsWith("sqrt(")) return parseInner(input) < 0 ? "Math Error" :
                    formatResult(Math.sqrt(parseInner(input)));

            // Basic arithmetic parsing (only one operator supported)
            return formatResult(simpleArithmetic(input));

        } catch (NumberFormatException e) {
            return "Invalid Input";
        } catch (ArithmeticException e) {
            return "Math Error";
        } catch (Exception e) {
            return "Error";
        }
    }

    private double parseInner(String func) {
        int start = func.indexOf('(');
        int end = func.indexOf(')');
        if (start == -1 || end == -1 || start >= end) throw new IllegalArgumentException();
        return Double.parseDouble(func.substring(start + 1, end));
    }

    private double simpleArithmetic(String expr) {
        expr = expr.replaceAll(" ", ""); // Remove spaces
        if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expr.contains("-")) {
            String[] parts = expr.split("-", 2); // Important to allow negative numbers
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else if (expr.contains("*")) {
            String[] parts = expr.split("\\*");
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } else if (expr.contains("/")) {
            String[] parts = expr.split("/");
            double denominator = Double.parseDouble(parts[1]);
            if (denominator == 0) throw new ArithmeticException();
            return Double.parseDouble(parts[0]) / denominator;
        } else {
            return Double.parseDouble(expr); // Just a number
        }
    }

    private String formatResult(double result) {
        if (Double.isNaN(result) || Double.isInfinite(result)) return "Math Error";
        if (result == (long) result) return String.format("%d", (long) result); // Remove .0
        return String.format("%.6f", result); // Limit to 6 decimals
    }
}
