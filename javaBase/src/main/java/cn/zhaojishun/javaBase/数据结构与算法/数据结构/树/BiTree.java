package cn.zhaojishun.javaBase.数据结构与算法.数据结构.树;


import java.util.*;

/**
 * @program: javaBaseLearn
 * @description: 二叉树的链式存储
 * @author: Jishun Zhao
 * @create: 2020-07-11 17:24
 */
public class BiTree {

    class BiNode{
        char data;
        BiNode leftChild;
        BiNode rigthChild;
    }

    class BiThrNode{
        char data;
        int ltag;
        int rtag;
        BiNode leftChild;   //左孩子
        BiNode rigthChild;  //右孩子
    }

    BiNode root;


    //先序遍历
    public static void preOrderTraverse (BiNode node){
        if (node == null) return;

        System.out.println(node.data);          //输出根节点数据

        preOrderTraverse(node.leftChild);       //遍历左子树
        preOrderTraverse(node.rigthChild);      //遍历右子树
    }

    //中序遍历遍历
    public static void inOrderTraverse (BiNode node){

        if (node == null) return;

        inOrderTraverse(node.leftChild);        //遍历左子树

        System.out.println(node.data);          //输出根节点数据

        inOrderTraverse(node.rigthChild);       //遍历右子树
    }

    //中序遍历非递归
    public static void inOrderTraverseStack(BiNode node){

        BiNode tempNode = node;
        Stack<BiNode> stack = new Stack<>();

        //如果node不为空或者栈不为空
        while (tempNode != null || !stack.empty()){
            if (tempNode != null){
                stack.push(tempNode);
                tempNode = tempNode.leftChild;
            }else {
                BiNode pop = stack.pop();
                System.out.println(pop.data);
                tempNode = tempNode.rigthChild;
            }
        }
    }

    //后序遍历
    public static void postOrderTraverse (BiNode node){

        if (node == null) return;

        inOrderTraverse(node.leftChild);        //遍历左子树
        inOrderTraverse(node.rigthChild);       //遍历右子树

        System.out.println(node.data);          //输出根节点数据
    }

    //层次遍历算法
    public static void levelOrder(BiNode node){

        if (node == null) return;                   //如果为空直接退出

        BiNode currentNode;
        Queue<BiNode> queue = new ArrayDeque<>();   //初始化队列
        queue.add(node);                            //将根节点入队

        while (!queue.isEmpty()){                   //如果当前队列不为空

            currentNode = queue.peek();             //出队
            System.out.println(currentNode.data);   //输出出对元素
            if (currentNode.leftChild != null) queue.add(currentNode.leftChild);    //有左孩子入队
            if (currentNode.rigthChild != null) queue.add(currentNode.rigthChild);  //有有孩子入队

            queue.poll();
        }
    }

    //复制树
    public BiNode copy(BiNode node){

        if (node == null) return null;

        BiNode biNode = new BiNode();
        biNode.data = node.data;
        biNode.leftChild = copy(node.leftChild);
        biNode.rigthChild = copy(node.rigthChild);
        return biNode;
    }

    //树的深度
    int depth(BiNode node){
        if (node == null) return 0;
        int m = depth(node.leftChild);
        int n = depth(node.rigthChild);
        if (m > n) {
            return m+1;
        }else {
            return n + 1;
        }
    }

    //计算树的结点数
    int nodeCount(BiNode node){
        if (node == null) return 0;
        return nodeCount(node.leftChild) + nodeCount(node.rigthChild) + 1;
    }

    //计算叶子结点数
    int leafCount(BiNode node){
        if (node == null) return 0;
        if (node.leftChild == null && node.rigthChild == null) {
            return 1;
        }else {
            return leafCount(node.leftChild) + leafCount(node.rigthChild);
        }
    }
}