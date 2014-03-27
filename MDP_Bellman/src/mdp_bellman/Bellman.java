/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Sompa
 */
class Bellman {
    Double discountFactor;
    Vector<State> states;
    Vector<Result> results;


    public Bellman(Double discountFactor)
    {
        this.discountFactor = discountFactor;
    }
    public void processMDP()
    {
        results = new Vector<Result>();
        try {
            Collections.sort(states, new Comparator<State>() {

            public int compare(State a, State b) {
                int x = Integer.parseInt(a.getName().substring(1)),
                        y = Integer.parseInt(b.getName().substring(1));
		return x - y;
            }

        } );
        }
        catch(Exception e)
        {
           Collections.sort(states, new Comparator<State>()
           {
            public int compare(State a, State b)
            {
                return a.getName().compareToIgnoreCase(b.getName());
            }
        });
        }
        for(int i = 0; i<20 ;i++)
        {
            Result result = new Result();
            Result r = results.size()>0 ? results.lastElement() : null;
            results.add(result);
          for(State s : states){
              Map<String, Double> rwd = result.getRewards().get(s.getName());
              {
		rwd = new HashMap<String, Double>();
		result.getRewards().put(s.getName(), rwd);
              }
         String bestAction = "";
	double bestValue = Double.NEGATIVE_INFINITY;
	for (Action a : s.getActions())
        {
            double value = 0;
		if (r!= null)
                {
                    for (Map.Entry<State, Double> sd : a.getTransitions()
                            .entrySet())
                    {
			value += r.getBestValue().get(sd.getKey().getName())* sd.getValue();
                    }
        value *= getDiscountFactor();
       }
          value += s.getReward().doubleValue();
	  if (value > bestValue)
          {
            bestAction = a.getName();
            bestValue = value;
	  }
	rwd.put(a.getName(), new Double(value));
	 }
	result.getBestValue().put(s.getName(), bestValue);
	result.getBestAction().put(s.getName(), bestAction);
        }
    }
    }
    public void printResult()
    {
	int i = 0;
	for (Result r : results)
        {
		i++;
		System.out.println("After Iteration " + i + ": ");
		for (State s : states)
                {
		  System.out.print(String.format(" (%s %s %.4f)", s.getName(), r.getBestAction().get(s.getName()), r.getBestValue()
						.get(s.getName())));
			}
			System.out.println("");
		}
	}

	public Double getDiscountFactor() {
		return discountFactor;
	}

	public void setDiscountFactor(Double discountFactor) {
		this.discountFactor = discountFactor;
	}

	public Vector<State> getStates() {
		return states;
	}

	public void setStates(Vector<State> states) {
		this.states = states;
	}

	public Vector<Result> getResults() {
		return results;
	}

	public void setResults(Vector<Result> results) {
		this.results = results;
	}

}


