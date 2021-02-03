public class WordCountsArray {
    //Attributes
    private WordCount[] wordArr;

    //Constructor
    public WordCountsArray(int initSize){
        if (initSize < 0) {
            this.wordArr = new WordCount[0];
        } else {
            this.wordArr = new WordCount[initSize];
        }
    }

    public void add (String word, int count){
        if((word != null) && word.length() != 0){
            int firstEmptyIndex = this.size();
            if(firstEmptyIndex != wordArr.length) {
                wordArr[firstEmptyIndex] =new WordCount(word, count);
            }
            WordCount[] tempArr = new WordCount[wordArr.length + 1];
            wordArr = this.copyContent(tempArr);
            wordArr[wordArr.length -1] = new WordCount(word, count);
        }
    }

    public int size(){
        for (int i = 0; i < wordArr.length; i++){
            if (wordArr[i] == null)
                return i;
        }
        return wordArr.length;
    }

    public String getWord(int index){
        if (index <= -1 || index >= this.size())
            return null;
        return wordArr[index].getWord();
    }

    public int getCount(int index){
        if (index <= -1 || index >= this.size())
            return -1;
        return wordArr[index].getWordCounter();
    }

    public void setCount(int index, int count){
        if(index >= 0 && index < this.size() && count <= 0)
            wordArr[index].setWordCounter(0);
        if(index >= 0 && index < this.size())
            wordArr[index].setWordCounter(count);
    }

    private WordCount[] copyContent(WordCount[] targetArr){
        int max = (targetArr.length - wordArr.length) >= 0 ? wordArr.length : targetArr.length;
        for(int i=0; i<wordArr.length; i++){
            targetArr[i] = wordArr[i];
        }
        return targetArr;
    }

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

}

