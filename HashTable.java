import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Using the common_file.txt, find the words that are commonly used and 
 * check for Collisions
 * 
 * @author Alexander Diep
 * @version 12/6/20
 */
public class HashTable
{
    public static final int MAX = 30;

    private String hashTable[];
    /**
     * Checks the hamlet.txt file,with the commonly used text file to see which 
     * words show up multiple times
     * 
     * @hashTable
     * @collisions
     */
    public HashTable() throws FileNotFoundException
    {
        hashTable = new String[MAX];

        //be certain array contains all nulls
        for (int i = 0; i < MAX; ++i)
            hashTable[i] = null;

        //read file of common words
        int collisions = 0;
        Scanner commonWords = new Scanner(new File("Common_words.txt"));
         
        while (commonWords.hasNext()) {
            String word = commonWords.nextLine();
            //put word into hash table, handling collisions
            int key = hash(word);
            if (hashTable[key] == null)
            {
                hashTable[key] = word;
            }
            else 
            {
                if (!hashTable[key].contains(word))
                {
                    hashTable[key] += ",";
                    hashTable[key] += word;
                    ++collisions;
                }
            }
            
                        
        }
        System.out.println ("Number of collisions building the table: " + collisions);
    }
    /**
     * Used to convert a word to a number to be used in collisions
     * 
     * @total
     * @decimal
     */
    public int hash(String word)
    {
        char ch;
        double total;

        total = 0.0;
        for (int i = 0; i < word.length(); ++i) {
            ch = word.charAt(i);
            total += (double) ch;
            total *= Math.PI;
        }
        double decimal = total - (int) total;
        int hash = (int) (MAX * decimal);
        return hash;
    }
    /**
     * linear probing used to search the hash table
     * 
     * @key
     * @hashTable
     */
    public Boolean find(String word)
    {
        //use hashing here for fast search, implement linear probing to handle collisions 
         int key = hash(word);
         if (hashTable[key] != null)
         {
             if (hashTable[key].contains(word))
             {
                 return true;
             }
         }         
         return false;        
    }
    /**
     * hash table is traversed for word output and the word count
     * 
     * @word_count
     * @hashTable
     */
    public String toString()
    {
        //traverses the hash table to output each word, preceded by a word count 
        int word_count = 0;
        for (int i = 0; i < MAX; ++i)
        {
            if (hashTable[i] != null)
            {
                String[] words = hashTable[i].split(",");
                for (int k = 0; k < words.length; ++k)
                {
                    word_count ++;
                    System.out.printf("%2d %s\n",word_count, words[k]);
                }
                
            }
            
        }
        
        
        //this hard-coded return value is here to allow this method stub to compile
        return "";
    }
}
