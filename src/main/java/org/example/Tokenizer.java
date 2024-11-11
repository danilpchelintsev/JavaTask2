package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Tokenizer отвечает за разбиение строки выражения на токены.
 * Поддерживает числа, переменные, операторы и скобки.
 */
public class Tokenizer {
    private final String expression;
    private int position;

    /**
     * Создает экземпляр Tokenizer для разбора заданного выражения.
     *
     * @param expression строка математического выражения
     */
    public Tokenizer(String expression) {
        this.expression = expression;
        this.position = 0;
    }

    /**
     * Разбивает выражение на список токенов.
     *
     * @return список строковых токенов
     */
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        while (position < expression.length()) {
            char current = expression.charAt(position);
            if (Character.isWhitespace(current)) {
                position++;
            } else if (Character.isDigit(current) || current == '.') {
                tokens.add(readNumber());
            } else if (Character.isLetter(current)) {
                tokens.add(readVariableOrFunction());
            } else if ("+-*/()".indexOf(current) != -1) {
                tokens.add(Character.toString(current));
                position++;
            } else {
                throw new IllegalArgumentException("Некорректный символ в выражении: " + current);
            }
        }
        return tokens;
    }

    // Читает число из выражения.
    private String readNumber() {
        int start = position;
        while (position < expression.length() && (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.')) {
            position++;
        }
        return expression.substring(start, position);
    }

    // Читает переменную или имя функции из выражения.
    private String readVariableOrFunction() {
        int start = position;
        while (position < expression.length() && Character.isLetterOrDigit(expression.charAt(position))) {
            position++;
        }
        return expression.substring(start, position);
    }
}
