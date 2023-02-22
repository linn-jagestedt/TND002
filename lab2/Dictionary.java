package lab2;
import java.util.*;

public class Dictionary 
{
    private ArrayList<Word> words;
    private ArrayList<Word> backup;
    
    public Dictionary() {
        words = new ArrayList<>();
        backup = null;
    }

    public String addString(String s) 
    {
        for (int i = 0; i < words.size(); i++) 
        {
            Word word = words.get(i);

            if (word.getValue().equals(s)) 
            {
                words.set(i, new Word(word.getValue(), word.getCount() + 1));
                return words.get(i).toString();
            }
        }

        Word word = new Word(s, 1);
        
        words.add(word);
        return word.toString();
    }   

    public String sortList(int sortCriterion) 
    {
        if (sortCriterion < 0 || sortCriterion > 2) {
            return "Invalid sort criterion";
        }

        Word.SetCriterion(sortCriterion);

        if (backup == null) {
            backup = new ArrayList<Word>(words);
        }

        if (sortCriterion == 0) {
            words = backup;
            backup = null;
            return "Dictionary order was reset";
        }

        // Bubble sort 
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = 0; j < words.size() - 1 - i; j++) {
                if (words.get(j).compareTo(words.get(j + 1)) > 0) {
                    Word temp = words.get(j);
                    words.set(j, words.get(j + 1));
                    words.set(j + 1, temp);
                }
            }
        }

        return 
            sortCriterion == 1 ? 
            "Dictionary was sorted alfabetically" : 
            "Dictionary was sorted by occurence";
    }

    public String getWordAtIndex(int index) 
    {
        if (index >= words.size()) {
            return "end";
        }
        
        return words.get(index).toString();
    }

    public String toString() 
    {
        String result = "";

        for (int i = 0; i < words.size(); i++) {
            result += words.get(i).toString() + "\n";
        }

        return result;
    }
}
