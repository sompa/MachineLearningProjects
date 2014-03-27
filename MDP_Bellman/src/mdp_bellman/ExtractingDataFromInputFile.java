/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Sompa
 */
public class ExtractingDataFromInputFile {
    Integer noOfStates;
    Integer noOfActions;
    String inputFileName;

    public ExtractingDataFromInputFile(Integer noOfActions, Integer noOfStates,
			String inputFileName) {
		super();
		this.inputFileName = inputFileName;
		this.noOfActions = noOfActions;
		this.noOfStates = noOfStates;
	}

    void extractData(Bellman mdp) throws IOException,FileNotFoundException,NumberFormatException
    {
       Scanner scanner = new Scanner(new File(inputFileName));
       Vector<State> states = new Vector<State>();
       Map<String, Integer> stateMap = new HashMap<String, Integer>();
       while(scanner.hasNext()){
       String eachLine = scanner.nextLine();
       Scanner scr = new Scanner(eachLine).useDelimiter("[()\\p{Blank}]+");
       String state = scr.next();
       Double reward = scr.nextDouble();
       State current = null;
       if(stateMap.get(state)==null)
       {
        states.add(new State(state, reward, new Vector<Action>()));
        stateMap.put(state, new Integer(states.size()-1));
        current = states.lastElement();
       }
       else
            {
                current = states.get(stateMap.get(state).intValue());
                current.setReward(reward);
            }
       while(scr.hasNext()){
        Action curr = null;
        String action = scr.next();
        for(Action a : current.getActions())
        {
            if(a.getName().equals(action))
            {
                curr = a;
                break;
            }
         }
         if(curr == null)
         {
            curr = new Action(action, new HashMap<State, Double>());
            current.getActions().add(curr);
         }
            State next = null;
            String stateName = scr.next();
            for(State s: states)
            {
                if(s.getName().equals(stateName))
                {
                    next = s;
                    break;
                }
            }
            if(next == null)
            {
                next = new State(stateName, new Double(0.0), new Vector<Action>());
                states.add(next);
                stateMap.put(next.getName(), new Integer(states.size()-1));
            }
            Double transitionProbability = scr.nextDouble();
            curr.getTransitions().put(next, transitionProbability);
       }
       scr.close();
       }
    scanner.close();
    mdp.setStates(states);
    }
  public Integer getNoOfStates() {
		return noOfStates;
	}

	public void setNoOfStates(Integer noOfStates) {
		this.noOfStates = noOfStates;
	}

	public Integer getNoOfActions() {
		return noOfActions;
	}

	public void setNoOfActions(Integer noOfActions) {
		this.noOfActions = noOfActions;
	}

	public String getInputFilename() {
		return inputFileName;
	}

	public void setInputFilename(String inputFileName) {
		this.inputFileName = inputFileName;
	}

}
