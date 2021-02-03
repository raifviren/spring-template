package com.example.demo.commons.utils;

import java.util.function.Supplier;

public class CustomPreconditions{

	 public static void checkArgument(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
		    if (!expression) {
		    	throw exceptionSupplier.get();
		    }
		  }
}
