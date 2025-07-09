import java.util.Comparator;
import java.util.TreeSet;

public class Maximum_stack {
    private TreeSet<int[]>  stack;
    private TreeSet<int[]> values;
    private int cnt;

   public Maximum_stack(){
       Comparator<int[]> comb = (a,b) -> {
           return a[0] == b[0] ? a[1] - b[1]:a[0] - b[0];
       };
       stack = new TreeSet<>(comb);
       values = new TreeSet<>(comb);
       cnt = 0;
   }

   public void push(int x){
        stack.add(new int[]{x,cnt});
        values.add(new int[]{cnt,x});
        cnt++;
   }

   public int pop(){
        int[] pair = stack.pollLast();
        values.remove(new int[] {pair[0],pair[1]});
        return pair[0];
   }

   public int top(){
       return  stack.last()[1];
   }

   public int peekMax(){
       return values.last()[0];
   }

   public int popMax(){
       int[] pair = values.pollLast();
       stack.remove(new int[]{pair[0],pair[1]});
       return pair[0];
   }
}
