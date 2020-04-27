package com.andry.rover;

import com.andry.rover.invoker.IInvoker;
import com.andry.rover.invoker.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		String filename = getInputFileName(args);
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			IInvoker invoker = new Invoker(reader);
			invoker.initProcess();
			List<String> newPositions = invoker.process();

			newPositions.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getInputFileName(String[] args) {
		final boolean thereisNoFile = args.length == 0 || (args[0] == null || args[0].isBlank());
		if (thereisNoFile) {
			throw new InvalidParameterException("You must provide filename as argument");
		}
		return args[0];
	}
}
