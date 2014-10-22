package com.dong.password.hiden.test;

/**
 * @author Wang Donghua Oct 22, 2014
 *
 */
public class IntegerTest {

	public static void main(String[] args){
		//max vlaue of integer
		String strMaxValue = "2147483647";
		char[] charArrays = strMaxValue.toCharArray();
		System.out.println("" + Integer.valueOf(strMaxValue));
		int m = '0';
		System.out.println("parse int: " + Integer.parseInt("0"));
		System.out.println("value of: " + Integer.valueOf("0"));
		System.out.println("int: " + m);
		
		int i = 2773;

		Integer value = new Integer(i);
		System.out.println("" + Byte.toString(value.byteValue()));
		System.out.println("" + Integer.toBinaryString(i));
		
		int max = Integer.MAX_VALUE;
		Integer maxInteger = new Integer(max);
		System.out.println("max byte: " + Byte.toString(maxInteger.byteValue()));
		System.out.println("max: " + Integer.toBinaryString(max));
		
	}
}
