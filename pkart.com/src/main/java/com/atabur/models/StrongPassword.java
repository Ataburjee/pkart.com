package com.atabur.models;

import java.util.ArrayList;
import java.util.List;

public class StrongPassword {
	
	public static boolean ifContainSmallLetter(String password) {
		List<Character> charList = new ArrayList<>();
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		
		for(char c : alphabet) charList.add(c);
		
		for(int i=0 ;i<password.length() ;i++) {
			if(charList.contains(password.charAt(i))) return true;
		}
		
		return false;
	}
	
	public static boolean ifContainCapitalLetter(String password) {
		List<Character> charList = new ArrayList<>();
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		
		for(char c : alphabet) charList.add(c);
		
		for(int i=0 ;i<password.length() ;i++) {
			if(charList.contains(password.charAt(i))) return true;
		}
		
		return false;
	}
	
	public static boolean ifContainNum(String password) {
		List<Character> charList = new ArrayList<>();
		
		char[] num = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		
		for(char c : num) charList.add(c);
		
		for(int i=0 ;i<password.length() ;i++) {
			if(charList.contains(password.charAt(i))) return true;
		}
		
		return false;
	}
	
	public static boolean ifContainSpacialChar(String password) {
		List<Character> charList = new ArrayList<>();
		
		char[] num = "<([{\\\\@~#%^-=$!|]})?*+.>".toCharArray();
		
		for(char c : num) charList.add(c);
		
		for(int i=0 ;i<password.length() ;i++) {
			if(charList.contains(password.charAt(i))) return true;
		}
		
		return false;
	}
	
	
}
