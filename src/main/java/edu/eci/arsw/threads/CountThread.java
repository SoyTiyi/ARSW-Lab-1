/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread implements Runnable {
	private int firstNumber;
	private int secondNumber;
	

	public CountThread(int firstNumber, int secondNumber) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}
	
	public void run(){
		showNumbersBetween();
	}

	public void showNumbersBetween() {
		for(int i=firstNumber;i <= secondNumber; i++){
			System.out.println(i);
		}	
	}
}
