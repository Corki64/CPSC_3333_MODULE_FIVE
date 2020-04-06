import java.util.Deque;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class LruC {

        static Deque<Integer> keys;
        static int cacheSize;
        static Map<Integer, Boolean> keyReference = new HashMap<>();

        LruC(int n)
        {
            keys = new LinkedList<>();
            keyReference = new Hashtable<>();
            cacheSize = n;
        }

        public void addPage(int pageIn)
        {
            // uses a checkPage method - indicates whether the key is located in our HashSet
            if (checkPage(pageIn)) {

                // check to see if the cache is full - if so will remove the last item from the queue
                if (keys.size() == cacheSize) {
                    int last = keys.removeLast();
                    keyReference.remove(last);
                }
            }
            else {
                // if not full it will find check to make sure the key is not already in our dictionary
                int index = 0, i = 0;
                for (Integer key : keys) {
                    if (key == pageIn) {
                        index = i;
                        break;
                    }
                    i++;
                }
                // this will clear my key value from my key queue
                keys.remove(index);
            }
            // and if not found in my 'key ring' will add the key to the top of the queue
            keys.push(pageIn);
            // will play the key, along with a value of true into
            // the dictionary because it is now located in the memory cache
            keyReference.put(pageIn, true);
        }

        // Simple method to check if a value is already located in our memory cache
        public boolean checkPage(int pageIn) {
            return !keyReference.containsKey(pageIn);
        }


    /**
     * Getter for our Dictionary.
     * @return - will return a Dictionary with key value pairs of items located in the memory cache
     */
        Dictionary<Integer, Boolean> Dictionary() {
            return (Dictionary<Integer, Boolean>) keyReference;
        }


        public void display() {
            for (Integer key : keys) System.out.print(key + " ");
    }


    public static void main(String[] args) {
        LruC test = new LruC(4);
        test.addPage(1);
        test.addPage(2);
        test.addPage(3);
        test.addPage(1);
        test.addPage(4);
        test.addPage(5);
        test.display();
        test.Dictionary();
    }

}

