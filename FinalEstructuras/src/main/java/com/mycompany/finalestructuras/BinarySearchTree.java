/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalestructuras;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author samaniw
 */
public class BinarySearchTree extends frmGrafo {

    ArrayList<BinaryNode> list = new ArrayList<>();
    private BinaryNode root;
    private BinaryNode father;
    private boolean position;
    private int nodes;
    private int leafs;

    public BinarySearchTree() {
        root = null;
        nodes = 0;
    }

    public BinarySearchTree(int data) {
        root = new BinaryNode(data);
        nodes = 1;
    }

    //Punto 1
    public ArrayList<BinaryNode> InOrden() {
        leafs = 0;
        //identificar desde qué método se ha llamado a InOrden
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        InOrden(root, method);
        return list;
    }

    private void InOrden(BinaryNode currentRoot, String m) {
        if (currentRoot != null) {

            if (m == "CountLeafs" && currentRoot.isLeaf()) {
                leafs++;
            } else {
                InOrden(currentRoot.getLeft(), m);
                list.add(currentRoot);
                System.out.print(currentRoot.getData() + " ");
                System.out.print(currentRoot.getX() + " ");
                System.out.println(currentRoot.getY());
                InOrden(currentRoot.getRight(), m);
            }

        }
    }

    //Punto 2
    public void Postorden() {
        Postorden(root);
    }

    private void Postorden(BinaryNode currentRoot) {
        if (currentRoot != null) {
            Postorden(currentRoot.getLeft());
            Postorden(currentRoot.getRight());
            System.out.print(currentRoot.getData() + " ");
        }
    }

    //Punto 3
    public void PreOrden() {
        PreOrden(root);
    }

    private void PreOrden(BinaryNode currentRoot) {
        if (currentRoot != null) {
            System.out.print(currentRoot.getData() + " ");
            PreOrden(currentRoot.getLeft());
            PreOrden(currentRoot.getRight());
        }
    }

    //Punto 4
    public int CountNodes() {
        return nodes;
    }

    //Punto 5
    public int CountLeafs() {
        InOrden();
        return leafs;
    }

    public void Add(int data) {
        if (root == null) {
            root = new BinaryNode(data, 485, 60);
        } else //validar si el dato ya existe
        if (Search(data) != null) {
            System.out.println("Dato repetido, no se puede insertar");
            JOptionPane.showMessageDialog(super.getComponent(0), "No se pudo agregar dato repetido");
        } else {
            Add(data, root, 105);
        }
        nodes++;
    }

    private void Add(int data, BinaryNode currentRoot, int x) {
        if (data < currentRoot.getData()) {
            if (currentRoot.getLeft() == null) {
                currentRoot.setLeft(new BinaryNode(data, currentRoot.getX() - x, currentRoot.getY() + 45));
            } else {
                if (x < 45) {
                    Add(data, currentRoot.getLeft(), x);
                } else {
                    Add(data, currentRoot.getLeft(), x - 15);
                } 
            }

        } else if (currentRoot.getRight() == null) {
            currentRoot.setRight(new BinaryNode(data, currentRoot.getX() + x, currentRoot.getY() + 45));
        } else {
            if (x < 45) {
                Add(data, currentRoot.getRight(), x);
            } else {
                Add(data, currentRoot.getRight(), x - 15);
            }

        }
    }

    public BinaryNode Search(int data) {
        return Search(data, root);
    }

    private BinaryNode Search(int data, BinaryNode currentRoot) {
        if (currentRoot == null) {
            return null;
        }
        if (data == currentRoot.getData()) {
            return currentRoot;
        }

        father = currentRoot;

        if (data < currentRoot.getData()) {
            position = false;
            return Search(data, currentRoot.getLeft());
        } else {
            position = true;
            return Search(data, currentRoot.getRight());
        }
    }

    //Punto 6
    public void Delete(int data) {
        if (root == null) {
            System.out.print("Árbol vacío");
        } else {
            Delete(data, root);
        }
        nodes--;
    }

    private void Delete(int data, BinaryNode currentRoot) {

        BinaryNode v = Search(data);
        if (v.isLeaf()) {
            if (position) {
                father.setRight(null);
            } else {
                father.setLeft(null);
            }
        } else if (v.hasOneChild()) {
            if (v.isChildPosition()) {

                if (v == root) {
                    root = root.getRight();
                    return;
                }

                if (position) {
                    father.setRight(v.getRight());
                } else {
                    father.setLeft(v.getRight());
                }
            } else if (position) {
                father.setRight(v.getLeft());
            } else {
                father.setLeft(v.getLeft());
            }
        } else {
            BinaryNode minimum = getMinor(v.getRight());
            Delete(minimum.getData());
            v.setData(minimum.getData());
        }
    }

    //Punto 7
    public int LastLevel() {
        return LastLevel(root);
    }

    private int LastLevel(BinaryNode currentRoot) {
        if (currentRoot == null) {
            return 0;
        }
        int rightLevels = LastLevel(currentRoot.getRight());
        int leftLevels = LastLevel(currentRoot.getLeft());

        int max = (rightLevels > leftLevels) ? rightLevels : leftLevels;
        return (max + 1);
    }

    private void showLevel(BinaryNode currentRoot, int level) {
        if (currentRoot == null) {
            return;
        }
        if (level == 1) {
            System.out.print(currentRoot.getData() + " ");
        } else if (level > 1) {
            showLevel(currentRoot.getLeft(), level - 1);
            showLevel(currentRoot.getRight(), level - 1);
        }

    }

    //Punto 8
    public void LevelOrder() {
        //...
        /* 
        Para mostrar los datos se recomienda usar:
            System.out.print(x.getData()+" ");
        donde x representa un nodo del árbol

        Para generar un salto de linea se recomienda usar:
            System.out.print("\n");
        
         */

        int totalLevels = LastLevel(root);
        for (int i = 1; i <= totalLevels; i++) {
            showLevel(root, i);
            System.out.print("\n");

        }
    }

    public BinaryNode getMinor(BinaryNode currentRoot) {
        if (currentRoot.getLeft() == null) {
            return currentRoot;
        } else {
            return getMinor(currentRoot.getLeft());
        }
    }
}
