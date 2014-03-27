/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;


import java.util.Map;


/**
 *
 * @author Sompa
 */
class Action {
    String name;
    Map<State, Double> transitions;

    public Action(String name, Map<State, Double> transitions)
    {
        super();
        this.name = name;
        this.transitions = transitions;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Map<State, Double> getTransitions()
    {
        return transitions;
    }
    public void setTransitions(Map<State, Double> transitions)
    {
        this.transitions = transitions;
    }
}
