/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdp_bellman;

import java.util.Vector;

/**
 *
 * @author Sompa
 */
public class State {
    Vector<Action> actions;
    String name; // name of the state
    Double reward; // reward associated with that state

    public State(String name, Double reward, Vector<Action> actions)
    {
        super();
        this.name = name;
        this.reward = reward;
        this.actions = actions;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Double getReward()
    {
        return reward;
    }
    public void setReward(Double reward)
    {
        this.reward = reward;
    }
    public Vector<Action> getActions()
    {
        return actions;
    }
    public void setActions(Vector<Action> actions)
    {
        this.actions = actions;
    }
}
