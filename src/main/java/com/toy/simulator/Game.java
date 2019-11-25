package com.toy.simulator;
import com.toy.simulator.exception.ToyRobotSimulatorException;
import com.toy.simulator.utility.Board;
import com.toy.simulator.utility.Command;
import com.toy.simulator.utility.Direction;
import com.toy.simulator.utility.Position;
import com.toy.simulator.utility.ToyRobot;
public class Game {
	  Board squareBoard;
	    ToyRobot robot;

	    public Game(Board squareBoard, ToyRobot robot) {
	        this.squareBoard = squareBoard;
	        this.robot = robot;
	    }

	    /**
	     * Places the robot on the squareBoard  in position X,Y and facing NORTH, SOUTH, EAST or WEST
	     *
	     * @param position Robot position
	     * @return true if placed successfully
	     * @throws ToyRobotSimulatorException
	     */
	    public boolean placeToyRobot(Position position) throws ToyRobotSimulatorException {

	        if (squareBoard == null)
	            throw new ToyRobotSimulatorException("Invalid squareBoard object");

	        if (position == null)
	            throw new ToyRobotSimulatorException("Invalid position object");

	        if (position.getDirection() == null)
	            throw new ToyRobotSimulatorException("Invalid direction value");

	        // validate the position
	        if (!squareBoard.isValidPosition(position))
	            return false;

	        // if position is valid then assign values to fields
	        robot.setPosition(position);
	        return true;
	    }

	    /**
	     * Evals and executes a string command.
	     *
	     * @param inputString command string
	     * @return string value of the executed command
	     * @throws com.gvnn.trb.exception.ToyRobotSimulatorException
	     *
	     */
	    public String eval(String inputString) throws ToyRobotSimulatorException {
	        String[] args = inputString.split(" ");

	        // validate command
	        Command command;
	        try {
	            command = Command.valueOf(args[0]);
	        } catch (IllegalArgumentException e) {
	            throw new ToyRobotSimulatorException("Invalid command");
	        }
	        if (command == Command.PLACE && args.length < 2) {
	            throw new ToyRobotSimulatorException("Invalid command");
	        }

	        // validate PLACE params
	        String[] params;
	        int x = 0;
	        int y = 0;
	        Direction commandDirection = null;
	        if (command == Command.PLACE) {
	            params = args[1].split(",");
	            try {
	                x = Integer.parseInt(params[0]);
	                y = Integer.parseInt(params[1]);
	                commandDirection = Direction.valueOf(params[2]);
	            } catch (Exception e) {
	                throw new ToyRobotSimulatorException("Invalid command");
	            }
	        }

	        String output;

	        switch (command) {
	            case PLACE:
	                output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
	                break;
	            case MOVE:
	                Position newPosition = robot.getPosition().getNextPosition();
	                if (!squareBoard.isValidPosition(newPosition))
	                    output = String.valueOf(false);
	                else
	                    output = String.valueOf(robot.move(newPosition));
	                break;
	            case LEFT:
	                output = String.valueOf(robot.rotateLeft());
	                break;
	            case RIGHT:
	                output = String.valueOf(robot.rotateRight());
	                break;
	            case REPORT:
	                output = report();
	                break;
	            default:
	                throw new ToyRobotSimulatorException("Invalid command");
	        }

	        return output;
	    }

	    /**
	     * Returns the X,Y and Direction of the robot
	     */
	    public String report() {
	        if (robot.getPosition() == null)
	            return null;

	        return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
	    }
}
