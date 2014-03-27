/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;

/**
 *
 * @author Sompa
 */
import java.util.HashMap;
import java.util.Map;

public class Result {
	Map<String, Map<String, Double>> rewards; // State name ->
	// Action name -> result
	Map<String, String> bestAction; // State Name -> Best Action
	Map<String, Double> bestValue; // State Name -> Best Value

	Result() {
		rewards = new HashMap<String, Map<String, Double>>();
		bestAction = new HashMap<String, String>();
		bestValue = new HashMap<String, Double>();
	}

	public Map<String, Map<String, Double>> getRewards() {
		return rewards;
	}

	public void setRewards(Map<String, Map<String, Double>> rewards) {
		this.rewards = rewards;
	}

	public Map<String, String> getBestAction() {
		return bestAction;
	}

	public void setBestAction(Map<String, String> bestAction) {
		this.bestAction = bestAction;
	}

	public Map<String, Double> getBestValue() {
		return bestValue;
	}

	public void setBestValue(Map<String, Double> bestValue) {
		this.bestValue = bestValue;
	}

}
