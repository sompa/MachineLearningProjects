/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Sompa
 */
public class Main {

    ExtractingDataFromInputFile fetch;
	Bellman bellman;

	public void checkArguments(String[] args) {
		if (args.length != 4) {
			Runtime.getRuntime().exit(1);
		}
	}

	public void process(String[] args) {
		this.checkArguments(args);
		try {
			Integer noOfStates = Integer.valueOf(args[0]);
			Integer noOfActions = Integer.valueOf(args[1]);
			Double discountFactor = Double.valueOf(args[3]);
			bellman = new Bellman(discountFactor) ;
			fetch = new ExtractingDataFromInputFile(noOfActions, noOfStates, args[2]);
			fetch.extractData(bellman);
		} catch (NumberFormatException e) {
			System.out.println("n & m should be natural numbers");
			Runtime.getRuntime().exit(1);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate file " + e.getMessage());
			Runtime.getRuntime().exit(1);
		} catch (IOException e) {
			System.out.println("Unable to access file " + e.getMessage());
			Runtime.getRuntime().exit(1);
		} catch (Exception e) {
			System.out.println(" unknown error occured ");
			e.printStackTrace();
			Runtime.getRuntime().exit(1);
		}
		bellman.processMDP();
		bellman.printResult();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.process(args);
	}
}
