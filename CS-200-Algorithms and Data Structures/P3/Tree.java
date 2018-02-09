public class Tree {
    private TreeNode root;
    
    
    //empty tree
    public Tree(){
    	this.root = null;
    }
    
    // rootItem, empty children
    public Tree(TreeNode root){
    	this.root = root;
    }
    
    public boolean isEmpty(){
    	return root==null;
    }
    
    public void preorderTraverse(){
    	if (!isEmpty())
    		preorderTraverse(root,"");
    	else
    		System.out.println("root is null");
    }
    
	public void preorderTraverse(TreeNode node, String indent){
		System.out.println(indent+node.getItem());
		if(node.getLeft()!=null) preorderTraverse(node.getLeft(),indent+" ");
		if(node.getRight()!=null) preorderTraverse(node.getRight(),indent+" ");	
	}
	
    // if tree empty return null
    // else evaluate the tree by postorder traversal 
    // and return its value
    
    public Integer postorderEval(){
        Integer res = null;
        if(!isEmpty()){
            res = postorderEval(root);
        }
        return res;
    }
    
    public Integer postorderEval(TreeNode node){
    	Integer x = null, y = null;
        if(node.getLeft()!=null) x = Integer.valueOf(postorderEval(node.getLeft()));
        if(node.getRight()!=null) y = Integer.valueOf(postorderEval(node.getRight())); 
        //System.out.println("x = " + x + ", y = " + y);
        if(node.getItem().equals("+")) return x+y;
        else if (node.getItem().equals("-") && y != null) return x-y;
        else if (node.getItem().equals("-") && y == null) return -x;
        else if (node.getItem().equals("*")) return x*y;
        else if (node.getItem().equals("/")) return x/y;
        else return Integer.valueOf(node.getItem());
    }
    
    
	public static void main(String[] args){
		TreeNode l1 = new TreeNode("12");
		TreeNode l2 = new TreeNode("345");
		TreeNode s1 = new TreeNode("+",l1,l2);
		TreeNode l3 = new TreeNode("67");
		TreeNode r  = new TreeNode("+",s1,l3);
		
		Tree T = new Tree(r);
		T.preorderTraverse();
	}		
}
