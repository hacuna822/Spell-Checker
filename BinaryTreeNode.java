package cs106;

import static sbcc.Core.*;
import static org.apache.commons.lang3.StringUtils.*;

// 10/22/2022

public class BinaryTreeNode {

    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public String value;


    BinaryTreeNode(String value) {
        this.value = value;

    }


    BinaryTreeNode(BinaryTreeNode node) {
        left = node.left;
        right = node.right;
        value = node.value;

    }


}
