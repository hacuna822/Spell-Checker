package cs106;

import static sbcc.Core.*;
import static org.apache.commons.lang3.StringUtils.*;
import static java.lang.System.*;
import static java.util.Arrays.*;
import static java.lang.Math.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class BasicDictionary implements Dictionary {
    private BinaryTreeNode root = null;
    int count = 0;

    @Override
    public void importFile(String filename) throws Exception {
        var words = readFileAsLines(filename);
        Collections.shuffle(words);
        for (var word : words) add(word);
    }


    @Override
    public void load(String filename) throws Exception {
        for (var word : readFileAsLines(filename)) add(word);
    }

    @Override
    // The `save` and `saveTreePreOrder` functions came from
    // Stephen Strenn's CS 106 Course Guidebook, Fall 2023 (Page 114)
    public void save(String filename) throws Exception {
        var sb = new StringBuilder();
        saveTreePreOrder(root, sb);
        writeFile(filename, sb.toString());
    }

    void saveTreePreOrder(BinaryTreeNode cursor, StringBuilder sb) {
        if (cursor == null) return; // base condition
        sb.append(cursor.value).append(lineSeparator()); // visit
        saveTreePreOrder(cursor.left, sb); // Traverse left subtree
        saveTreePreOrder(cursor.right, sb); // Traverse right subtree
    }

    @Override
    public String[] find(String word) {
        word = word.trim();
        var preceeding = "";
        var succeeding = "";
        var current = root;
        while (current != null) {
            var difference = word.compareToIgnoreCase(current.value);
            if (difference < 0) {
                succeeding = current.value;
                current = current.left;
            } else if (difference > 0) {
                preceeding = current.value;
                current = current.right;
            } else return null;
        }
        return new String[]{preceeding, succeeding};
    }

    @Override
    public void add(String word) {
        word = word.trim();
        if (root == null) {    //If the tree empty, insert this at root//
            root = new BinaryTreeNode(word);
        } else {
            var current = root;  //if not the root is the current//
            while (true) {
                var difference = word.compareToIgnoreCase(current.value);
                if (difference < 0) {
                    if (current.left == null) {
                        current.left = new BinaryTreeNode(word);
                        break;
                    }
                    current = current.left;
                } else if (difference > 0) {
                    if (current.right == null) {
                        current.right = new BinaryTreeNode(word);
                        break;
                    }
                    current = current.right;
                } else return;
            }
        }
        count++;
    }

    @Override
    public BinaryTreeNode getRoot() {
        return root;
    }

    @Override
    public int getCount() {
        return count;
    }
}
