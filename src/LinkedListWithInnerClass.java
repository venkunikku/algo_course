import coursear_alogs.interfaces.LinekedListInterface;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListWithInnerClass implements LinekedListInterface {
    
    public LinkedListWithInnerClass(){
        
    }
    
    public class Node{
        String item;
        Node next;
    }

    @Override
    public void push(String item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String pop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public static void main(String[] args){
        LinkedListWithInnerClass stack = new LinkedListWithInnerClass();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("-")){
                StdOut.print(stack.pop());
            }else {
                stack.push(s);
            }
        }
    }
    
}
