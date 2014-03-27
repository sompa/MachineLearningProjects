/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

/**
 *
 * @author Sompa
 */
public class Node {

    private String attribute;
    private Node leftChild;
    private Node rightChild;
    private int value;

    public Node(String attribute, Node leftChild, Node rightChild) {
        this.attribute = attribute;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(int value) {
        this.attribute = null;
        this.leftChild = null;
        this.rightChild = null;
        this.value = value;
    }

    public Node() {
        this.attribute = null;
        this.leftChild = null;
        this.rightChild = null;
        this.value = -1;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return the leftChild
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return the rightChild
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void print(String formatter) {
        if (isLeaf()) {
            System.out.println(getValue());
            return;
        }
        System.out.println();
        System.out.print(formatter + attribute + " = 0 : ");
        leftChild.print(formatter + "| ");
        System.out.print(formatter + attribute + " = 1 : ");
        rightChild.print(formatter + "| ");
    }

    boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
