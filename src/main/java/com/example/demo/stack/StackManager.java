package com.example.demo.stack;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Stack;

public class StackManager {

    private static Stack<Integer> argumentsStack = new Stack<>();

    public static int getStackSize() {
        return argumentsStack.size();
    }

    public static void addArguments(List<Integer> i_arguments) {
    }
}

