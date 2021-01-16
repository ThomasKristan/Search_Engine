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

    //Further Methods
    public void add (String word, int count){
        if(word != null){
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
        int size=0;
        for(int i=0; i < wordArr.length; i++){
            if(wordArr == null){
                return i;
            }
        }
        return wordArr.length;
    }

    public String getWord(int index){
        if((index < 0)||(index > this.size())){
            return "";
        }
        return wordArr[index].getWord();
    }

    public int getCount(int index){
        if((index < 0)||(index > this.size())){
            return -1;
        }
        return wordArr[index].getWordCounter();
    }

    public void setCount(int index, int count){
        if((index >= 0)||(index <= this.size())){
            wordArr[index].setWordCounter(count);
        }
    }

    private WordCount[] copyContent(WordCount[] targetArr){
        int max = (targetArr.length - wordArr.length) >= 0 ? wordArr.length : targetArr.length;
        for(int i=0; i<wordArr.length; i++){
            targetArr[i] = wordArr[i];
        }
        return targetArr;
    }
}

