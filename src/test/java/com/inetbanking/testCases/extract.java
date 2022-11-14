package com.inetbanking.testCases;

import java.io.IOException;
import java.util.ArrayList;

public class extract {

	public static void main(String[] args) throws IOException {
		dataDriven da = new dataDriven();
		ArrayList<String> arr = da.getData("test");
		for (String options : arr) {
			System.out.println(options);
		}
	}
}
