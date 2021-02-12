/**
 * This class represents a set of words and their counts.
 * This class ensures that no empty words are added
 * and that the word count is always greater than or equal to 0.
 */

public class WordCountsArray {
    //<editor-fold desc="Attributes">
    /**
     * the administered WordCount objects
     */
    private WordCount[] wordArr;
    /**
     * the actual number of administered WordCount objects
     */
    private int actualSize;
    /**
     * the maximum number of administrable WordCount objects (not final)
     */
    private int maxSize;
    //</editor-fold>

    /**
     * Creates a new instance of this class
     * The created instance is able of administering at most maxSize words.
     * @param maxSize   maximum number of words that can be administered by this instance
     */
    public WordCountsArray(int maxSize){
        if (maxSize < 0) {
            this.maxSize = 0;
        } else {
            this.maxSize = maxSize;
        }
        this.actualSize = 0;
        this.wordArr = new WordCount[this.maxSize];
    }

    /**
     * Adds the specified word with the specified count to this instance.
     * If the specified word is already administered by this instance, then the
     * count of the specified word is increased by the given count.
     * If the specified word is not administered already by this instance, this
     * method creates a new wordCount instance and administeres it with its count.
     * If the specified word is null or empty nothing happens.
     * If the count is lower than 0, nothing will happen.
     * @param word      the word to be added
     * @param count     the count of the word to be added
     */
    public void add (String word, int count){
        if(word == null || word == ""){
            return;
        }
        if(count < 0){
            return;
        }
        //if word is already administered, get index of the word
        int index = getIndexOfWord(word.toLowerCase());
        //word has not been found
        if(index == -1){
            //check if end of array has been reached -> increase array size
            if(actualSize == maxSize){
                this.doubleSize();
            }
            this.wordArr[actualSize] = new WordCount(word.toLowerCase(), count);
            this.actualSize++;
        //word has been found
        } else {
            this.wordArr[index].incrementCount(count);
        }
    }

    /**
     * Doubles the number of administrable wordCount objects
     */
    private void doubleSize(){
        this.maxSize = this.maxSize * 2;
        if(this.maxSize <= 0){
            this.maxSize = 1;
        }
        WordCount[] newWordArr = new WordCount[this.maxSize];
        //copy content of old array
        for(int i=0; i < this.wordArr.length; i++){
            newWordArr[i] = this.wordArr[i];
        }
        this.wordArr = newWordArr;
    }

    /**
     * Determines whether the words administered by this instance and the words in the specified
     * wordCountsArray are equal.
     * @param wca   the wordCountsArray instance that will be compared.
     * @return      true, if the words administered by this instance and the words administered
     * in the specified wordCountsArray instance are the same AND the words are in the same
     * order. Otherwise false.
     */
    private boolean wordsEqual(WordCountsArray wca){
        //exactly the same
        if(this == wca)
            return true;
        //cannot be the same
        if((wca == null) || (this.size() != wca.size())){
            return false;
        }
        for(int i=0; i<this.size(); i++){
            if(!this.getWord(i).equals(wca.getWord(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the scalar product of this instance and the specified wordCountsArray instance.
     * If this instance an the specified instance do not administer the same words and do not have
     * the same length, the scalar product is 0.
     * @param wca   the wordCountArray instance that will be multiplied with this instance.
     * @return      the scalar product between this instance and the specified wordCountsArray instance.
     */
    private double scalarProduct(WordCountsArray wca){
        if (!wordsEqual(wca)){
            return 0;
        }
        int result = 0;
        for (int i=0; i<this.wordArr.length; i++){
            result += this.getCount(i) * wca.getCount(i);
        }
        return result;
    }

    /**
     * Sorts the WordCount objects administered by this instance.
     * After calling this method the administered WordCount objects are
     * ordered lexicographically according to the words represented by the
     * WordCount objects.
     */
    public void sort(){
        int minIndex;
        for(int i=0; i<actualSize; i++){
            minIndex = i;
            for(int j = i+1; j<actualSize; j++){
                if(wordArr[minIndex].getWord().compareTo(wordArr[j].getWord()) > 0)
                    minIndex = j;
            }
            WordCount temp = wordArr[i];
            wordArr[i] = wordArr[minIndex];
            wordArr[minIndex] = temp;
        }
    }

    /**
     * Calculates the similarity of this instance and the specified WordCountsArray.
     * @param wca   the compared WordCountsArray instance
     * @return      the similarity on a scale from 0 to 1.  if wca is null -> 0 returned
     */
    public double computeSimilarity(WordCountsArray wca){
        if(wca == null){
            return 0;
        }

        double scalarProductThis = this.scalarProduct(this);
        double scalarProductWca = wca.scalarProduct(wca);
        if(scalarProductThis != 0 && scalarProductWca != 0)
            return this.scalarProduct(wca) / Math.sqrt(scalarProductThis * scalarProductWca);
        return 0;
    }

    /**
     * @return  the number of words currently administered by this instance.
     */
    public int size(){
        for (int i = 0; i < wordArr.length; i++){
            if (wordArr[i] == null)
                return i;
        }
        return wordArr.length;
    }

    /**
     * @param index     the index
     * @return          the word at the position index of the WordCount-Array
     */
    public String getWord(int index){
        if (index <= -1 || index >= this.size())
            return null;
        return wordArr[index].getWord();
    }

    /**
     *
     * @param index     the index
     * @return          the count of the word at the position index or -1 if index is illegal
     */
    public int getCount(int index){
        if (index <= -1 || index >= this.size())
            return -1;
        return wordArr[index].getWordCounter();
    }

    /**
     * Sets the count of the word at position index of the WordCount-Array to the specified count.
     * If the specified index is illegal, nothing will happen. If the specified count is lower
     * than 0, the count is set to 0.
     * @param index     index
     * @param count     the new count of the word at position index
     */
    public void setCount(int index, int count){
        if(index >= 0 && index < this.size() && count <= 0)
            wordArr[index].setWordCounter(0);
        if(index >= 0 && index < this.size())
            wordArr[index].setWordCounter(count);
    }

    /**
     * @return the entire WordCountsArray as a readable String
     */
    public String toString(){
        String Info = "";
        if (wordArr.length == 0)
            return "empty Array";
        for (int i = 0; i < this.size(); i++)
            Info = Info + " | [" + i + "]" + " \"" + wordArr[i].getWord() + "\": " + wordArr[i].getWordCounter();
        for (int i = this.size(); i < wordArr.length; i++)
            Info = Info + " | [" + i + "]" + " null";
        return Info;
    }

    /**
     * @param wordCountsArray   the other WordCountArray
     * @return                  true, if the instance and the specified WordCountArray are equal
     */
    public boolean equals(WordCountsArray wordCountsArray){
        if(this == null || this.size() != wordCountsArray.size())
            return false;
        if(this == wordCountsArray)
            return true;
        for(int i=0; i< this.size(); i++){
            if(!this.getWord(i).equals(wordCountsArray.getWord(i)) || this.getCount(i) != wordCountsArray.getCount(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * @param word      the word of which we want to know the index
     * @return          the index of the specified word in the internal array,
     *                  or -1 if the word is not administered.
     */
    public int getIndexOfWord(String word){
        if (word == null || word.equals("")){
            return -1;
        }
        for(int i=0; i<this.size(); i++){
            if(this.wordArr[i].getWord().equals(word)) {
                return i;
            }
        }
        return -1;
    }
}

