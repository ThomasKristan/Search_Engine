import java.util.jar.Attributes;

public class WordCount {
    //Attributes
    private String word = "";
    private int wordCounter = 0;

    //Constructor
    public WordCount(String word, int wordCounter) {
        //if (word.length() == 0) <-- also possible
        if (word == null) {
            this.word = "";
        } else {
            this.word = word;
        }
        setWordCounter(wordCounter);
    }

    //<editor-fold desc="Getter / Setter Methoden">

    //Getter-Methods
    public String getWord() {
        return word;
    }

    public int getWordCounter() {
        return wordCounter;
    }

    //Setter-Methods
    public void setWordCounter(int wordCounter) {
        if (wordCounter < 0) {
            this.wordCounter = 0;
        } else {
            this.wordCounter = wordCounter;
        }
    }

    //</editor-fold

    //Further Methods
    public int incrementCount() {
        return ++wordCounter;
    }

    public int incrementCount(int n) {
        if (n > 0) {
            this.wordCounter += n;
        }
        return this.wordCounter;
    }
}