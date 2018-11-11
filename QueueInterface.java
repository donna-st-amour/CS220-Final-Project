public interface QueueInterface<String>{
   public void enqueue(String newEntry);
   public String dequeue();
   public String getFront();
   public boolean isEmpty();
}//end QueueInterface 