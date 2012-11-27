package tictactoe.utils;

/**
 * @author hugh
 */
public class Sort {
    public static Object[] sort(Object[] values, Comparator comparator){
        if (values == null || values.length==0)
            return values;
        return Heap.buildHeap(values, comparator).getElements();
    }
  
    public interface Comparator {

        /* obj1 < obj2: return -1
         * obj1 == obj2: return 0;
         * obj1 > obj2: return +1 */
        public int compare(Object obj1, Object obj2);
    }
        
   /**
    *
    * @author hugh
    * @see Modified from 
    * http://www.java-forums.org/algorithms/7606-heap-sort-java.html
    */
   public static class Heap {

           private Object[] elements;
           private int currentSize; // number of items in array
           private Comparator comparer;

           public Heap(int size, Comparator c) {
               comparer = c;
               currentSize = 0;
               elements = new Object[size];
           }

           public Object remove() { 
               Object root = elements[0];
               elements[0] = elements[--currentSize];
               heapify(0);
               return root;
           }

           public void heapify(int index) {
               int largerChild;
               Object top = elements[index]; 
               while (index < currentSize / 2) {
                   int leftChild = 2 * index + 1;
                   int rightChild = leftChild + 1;
                   // find larger child
                   if (rightChild < currentSize 
                    && comparer.compare(elements[leftChild], elements[rightChild]) < 0 )
                       largerChild = rightChild;
                   else
                       largerChild = leftChild;

                   if (comparer.compare(top, elements[largerChild]) >= 0 )
                       break;
                   elements[index] = elements[largerChild];
                   index = largerChild; 
               }
               elements[index] = top;
           }

           public void insertAt(int index, Object newNode) {
               elements[index] = newNode;
           }

           public void incrementSize() {
             currentSize++;
           }

        /**
         * @param elements
         * @param comparer
         * @return
         */
        public static Heap buildHeap(Object[] elements, Comparator comparer){
               Heap heap = new Heap(elements.length, comparer);
               int i;
               for (i = 0; i < elements.length; i++){
                   heap.insertAt(i, elements[i]);
                   heap.incrementSize();            
               }        
               for (i = elements.length / 2 - 1; i >= 0; i--)
                   heap.heapify(i);
               for (i = elements.length - 1; i >= 0; i--) {
                   Object biggestNode = heap.remove();
                   heap.insertAt(i, biggestNode);
               }
               return heap;
           }

           public String toString() {
               String msg = "";
               for (int j = 0; j < elements.length; j++)
                   msg  += elements[j].toString() + "\n";
               return msg + "\n";
           }    

           public Object[] getElements() {
               return this.elements;
           }
        }
    
}
