	import java.util.Vector;
	import java.util.Vector.*;

	public class VectorQueue<String> implements QueueInterface<String>{
	   private Vector<String> queue;
	   
	   public VectorQueue(){
	      queue = new Vector<String>();
	   }//end constructor
	   
	   public VectorQueue(int initialCapacity){
	      queue = new Vector<String>(initialCapacity);
	   }//end constructor
	   
	   public void enqueue(String newEntry){
	      queue.add(newEntry);
	   }//end enqueue method
	   
	   public String getFront(){
	      String front = null;
	      if(!isEmpty()){
	         front = queue.get(0);
	      }//end if
	      return front;
	   }//end getFront method
	   
	   public String get(int index, int count){
	      String item = null;
	      if(index < count){
	         item = queue.get(index);
	      }//end if
	      return item;
	   }//end get method
	   
	   public String dequeue(){
	      String front = null;
	      if(!isEmpty()){
	         front = queue.remove(0);
	      }//end if
	      return front;
	   }//end dequeue method
	   
	   public boolean isEmpty(){
	      return queue.isEmpty();
	   }//end isEmpty method
	   
	   public void clear(){
	      queue.clear();
	   }//end clear method

	   
	}//end interface
