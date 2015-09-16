"""
Connect nodes at same level of a binary tree recursively using O(1) space (we can ignore stack space used for recursion)
Tree node is like following:

struct node {
  int data;
  struct node* left;
  struct node* right;
  struct node* nextRight;
}

Initially, all the nextRight pointers point to garbage values. Your function should set these pointers to point next right for each node. You can use only constant extra space.
"""

from chapter4.datastructures import *

class SkeweredTreeNode(TreeNode):
    def __init__(self, value, left=None, right=None, nextRight=None):
        super().__init__(value, left, right)
        self.nextRight = nextRight

    def __str__(self):
        return 'SkeweredTreeNode({})'.format(self.value)


def buildTreeFromList(l: list):
    def buildMinTree(l: list, parent, start, end):
        if l is None or len(l) == 0:
            return None
        if start >= end:
            return None
        middle = int((end + start) / 2)
        if middle < end:
            node = SkeweredTreeNode(l[middle])
            node.left = buildMinTree(l, node, start, middle)
            node.right = buildMinTree(l, node, middle + 1, end)
            return node

    if l is None or len(l) == 0:
        return None
    return buildMinTree(l, None, 0, len(l))

def skewerTree(node: SkeweredTreeNode):
    def skewerWithLevels(node: SkeweredTreeNode, level, prevNodes):

    return skewerWithLevels(node, 0, {})


"""
0
of 0 vote
I assume you want to traverse the node level order, and store the same in new singly linked list.

We can achieve it using simple queue,

- Maddy 3 days ago | Flag Reply
0
of 0 votes
Not O(1) space as required

- zortlord a day ago | Flag
0
of 0 vote

void connect(Node root) {
	if(root != null)
		connect(root, 0, new Vector<Node>());

}

void connect(Node root, int level, Vector<Node> links) {

	Node prev = null;
	prev = links.get(level);
	if(prev != null) {
		prev.next = root;
	}
	links.replace(level, root);
	if(!isLeaf(root)) {
		if(root.left != null)
			connect(root.left, level + 1, links);
		if(root.right != null)
			connect(root.right, level + 1, links);
	}

}
- Noobie 2 days ago | Flag Reply
0
of 0 votes
Not O(1) memory. Recursion uses memory too.

- zortlord about 21 hours ago | Flag
0
of 0 vote
Just do a pre-order or in-order traversal and keep a vector. Time O(n). Space O(log n)

- Obama 2 days ago | Flag Reply
0
of 0 votes
not O(1) space as required.

- zortlord a day ago | Flag
0
of 0 vote
Hi.. I have a doubt on this question.
By 'connect' do you mean that each node must store a reference to all nodes at the same level?

If yes, then this reference storage itself cannot be achieved in O(1) space because as the height of the tree increases, every node will have more number of peers.

It would be helpful to think about this problem if you can explain.

Thanks,
Pravin

- june.pravin 2 days ago | Flag Reply
0
of 0 vote
This can be done in O(n) with O(1) space by doing the following (in java)


class Node{
    int data;
    Node left, right, nextRight;
}

public static void setupLevels(Node leftMost){
    Node lastOnLevel, nextOnLevel, firstOnLevel;
    //while still computing levels
    while(leftMost != null){
        //while there are still Nodes in the parent level
        while(leftMost != null){
            //if there is a new left child to consider
            nextOnLevel = leftMost.left;
            if(nextOnLevel != null){
                //if there is a previous child, attach the last child to the new one
                if(lastOnLevel != null){
                    lastOnLevel.nextRight = nextOnLevel;
                }
                lastOnLevel = nextOnLevel;
            }
            //capture the first non-null child
            if(lastOnLevel != null && firstOnLevel == null){
                firstOnLevel = lastOnLevel;
            }

            //if there is a new right child
            nextOnLevel = leftMost.right;
            if(nextOnLevel != null){
                //if there is a previous child, attach the last child to the new one
                if(lastOnLevel != null){
                   lastOnLevel.nextRight = nextOnLevel;
                }
                lastOnLevel = nextOnLevel;
            }
            //capture the first non-null child
            if(lastOnLevel != null && firstOnLevel == null){
               firstOnLevel = lastOnLevel;
            }

            //move to the next parent
            leftMost = leftMost.nextRight;
        }
        //prep for the next level
        leftMost = firstOnLevel;
        firstOnLevel = null;
        lastOnLevel = null;
    }
}
- zortlord a day ago | Flag Reply
0
of 0 vote

public class Node
{
	int value;
	Node left;
	Node right;
	Node next;
}
public void connectNodes(Node n)
{
	if(n==null)
	{
		return;
	}
	Node rightSibling=getNextSiblingChild(n.next);//Iteratively visit the right sibling of n until we find a node that has at least one child.
	if(n.left!=null && n.left!=null)
	{
		n.left.next=n.right;
		n.right.next=rightSibling;
	}else if (n.left!=null && n.right==null)
	{
		n.left.next=rightSibling;
	}else if (n.right!=null && n.left==null)
	{
		n.right.next=rightSibling;
	}
	connectNodes(n.left);
	connectNodes(n.right);

}

private Node getNextSiblingChild(Node n)
{
	while(n!=null)
	{
		if(n.left!=null)
		{
			return n.left;
		}else if(n.right!=null)
		{
			return n.right;
		}
		n=n.next;
	}
}
//O(n) time. O(1) space.

- divm01986 about 23 hours ago | Flag Reply
0
of 0 votes
This is not O(1) memory. You are using recursion and that requires memory too.


"""