/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalestructuras;

/**
 *
 * @author samaniw
 */
public class BinaryNode {

    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    private int data;
    private BinaryNode left;
    private BinaryNode right;
    private boolean ChildPosition;
    private int X;
    private int Y;
    

    public BinaryNode(int data) {
        setData(data);
        setLeft(null);
        setRight(null);
    }
    
    public BinaryNode(int data, int X, int Y) {
        setData(data);
        setLeft(null);
        setRight(null);
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null)) ? true : false;
    }

    public boolean hasOneChild() {
        if (left == null && right != null) {
            ChildPosition = true;
            return true;
        } else if (left != null && right == null) {
            ChildPosition = false;
            return true;
        }else{
            return false;
        }

    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @return the left
     */
    public BinaryNode getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public BinaryNode getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinaryNode right) {
        this.right = right;
    }

    /**
     * @return the ChildPosition
     */
    public boolean isChildPosition() {
        return ChildPosition;
    }
}
