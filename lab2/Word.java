package lab2;

public class Word 
{
    private String value;
    private int count;

    private static int sortCriterion;

    public static int ORIGINAL = 0; 
    public static int BYNAME = 1; 
    public static int BYCOUNTS = 2; 
    
    public Word(String value, int count) {
        this.value = value;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getValue() {
        return value;
    }

    public static int getCriterion() {
        return sortCriterion;
    }
    
    public static void SetCriterion(int i) 
    {
        if (i >= 0 && i <= 2) {
            sortCriterion = i;
        }
    }

    public int compareTo(Word word) {
        if (sortCriterion == BYNAME) {
            return value.compareTo(word.value); 
        }
        else if(sortCriterion == BYCOUNTS) {
            return 
                count - word.count < 0 ? 1 :
                count - word.count > 0 ? -1 : 
                0;
        }
        else {
            return 2; 
        }
    }

    public String toString() 
    {
        int length = value.length();
        length = length > 10 ? 10 : length;

        return 
            "Word: " + 
            value.substring(0, length) + 
            "   Count: " + 
            count; 
    }
}