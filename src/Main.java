import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * CodingGame.com "Dwarfs standing on the shoulders of giants" solution
 */
class Solution {
	
	static int result;

    public static void main(String args[]) {
    	Stack<Integer> stack = new Stack<>();
    	int x, y;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence
        Integer [][] intArray = new Integer[n*2][n*2];
        for (int i = 0; i < n; i++) {
        	x = in.nextInt(); // a relationship of influence between two people (x influences y)
        	while(in.hasNext()){
            	y = in.nextInt();
            	intArray[x][y] = 1;
            	System.err.println("intArray[x][y] " + intArray[x][y]);
        	}
        }
        
        for(int i=1; i<intArray.length-1; i++){
    		dfs(i, stack, intArray);
        }

        // The number of people involved in the longest succession of influences
        System.out.println(result);
    }

	/**
	 * Depth first algorithm implementation
	 * @param root the root node
	 * @param stack a stack containing a graph path
     * @param intArray an array representing this graph
	 */
	private static void dfs(int root, Stack<Integer> stack,
			Integer[][] intArray) {
    	stack.add(root);
    	List<Integer> childs = getChilds(root, intArray);
    	if(childs.size() == 0){
    		if(stack.size() > result){
    			System.err.println("stack.size() " + stack.size());
    			result = stack.size();
    		}
    		stack.pop();
    		return;
    	}
    	for(Integer child:childs){
    		if(intArray[root][child] == -1){
    			stack.pop();
    			continue;
    		} else {
    			intArray[root][child] = -1;
    			dfs(child, stack, intArray);
    		}
        }
	}

	/**
	 * Returns the nodes connected with a given root
	 * @param root the root node
	 * @param intArray an array representing this graph
	 * @return the childs of this node
	 */
	private static List<Integer> getChilds(int root, Integer[][] intArray) {
		List<Integer> childs = new LinkedList<>();
		for (int i = 0; i < intArray.length-1; i++) {
			if (intArray[root][i] != null) {
				childs.add(i);
			}
		}
		return childs;
	}
}