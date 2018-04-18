package com.abn.controllers;

public class LambdaExp {
	LambdaExp le = new LambdaExp();
	MathOperation addition = (a,b) -> a+b; 

	public interface MathOperation{
		int operation(int a, int b);
	}
	
	//le.operation(5,3, addition)
	
	
	

}
