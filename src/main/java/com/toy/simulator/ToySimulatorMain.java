package com.toy.simulator;
import com.toy.simulator.exception.ToyRobotSimulatorException;
import com.toy.simulator.utility.SquareBoard;
import com.toy.simulator.utility.ToyRobot;

import java.io.Console;
public class ToySimulatorMain {

	public static void main(String[] args) {
		 Console console = System.console();

	       if (console == null) {
	            System.err.println("No console.");
	            System.exit(1);
	        }

	        SquareBoard squareBoard = new SquareBoard(4, 4);
	        ToyRobot robot = new ToyRobot();
	        Game game = new Game(squareBoard, robot);

	        System.out.println("Toy Robot Simulator");
	        System.out.println("Enter a command, Valid commands are:");
	        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

	        boolean keepRunning = true;
	        while (keepRunning) {
	            String inputString = console.readLine(": ");
	           
	            if ("EXIT".equals(inputString)) {
	                keepRunning = false;
	            } else {
	                try {
	                    String outputVal = game.eval(inputString);
	                  
	                } catch (ToyRobotSimulatorException e) {
	                	//We can use log4j for loggin it is not implemented
	                    System.out.println(e.getMessage());
	                }
	            }
	        }
	}

}
