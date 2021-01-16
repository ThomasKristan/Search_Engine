public class Document {
    //Attributes
    private String title;
    private String language;
    private String summary;
    private Date releaseDate;
    private Author author;
    private WordCountsArray wordCounts = new WordCountsArray(0);

    public static final String[] SUFFICES = {"ab", "al", "ant", "artig", "bar", "chen", "ei", "eln", "en", "end", "ent", "er", "fach", "fikation", "fizieren", "fähig",
            "gemäß", "gerecht", "haft", "haltig", "heit", "ie", "ieren", "ig", "in", "ion", "iren", "isch", "isieren", "isierung",
            "ismus", "ist", "ität", "iv", "keit", "kunde", "legen", "lein", "lich", "ling", "logie", "los", "mal", "meter", "mut",
            "nis", "or", "sam", "schaft", "tum", "ung", "voll", "wert", "würdig"};



    //Constructor

    public Document(String title, String language, String summary, Date releaseDate, Author author, String content){
        this.setTitle(title);
        this.setAuthor(author);
        this.setLanguage(language);
        this.setSummary(summary);
        this.releaseDate = releaseDate;
        this.addContent(content);
    }

    //<editor-fold desc="Getter / Setter Methods">

    //Getter-Methods
    public Author getAuthor() { return author; }
    public String getTitle() {return title; }
    public String getLanguage() { return language; }
    public String getSummary() { return summary; }
    public Date getReleaseDate() { return releaseDate; }
    public WordCountsArray getWordCounts(){ return wordCounts; }

    //Setter-Methods
    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        if (title == null) {
            this.title = "";
        } else {
            this.title = title;
        }
    }

    public void setLanguage(String language) {
        if (language == null) {
            this.language = "";
        } else {
            this.language = language;
        }
    }

    public void setSummary(String summary) {
        if (summary == null) {
            this.summary = "";
        } else {
            this.summary = summary;
        }
    }

    //</editor-fold>

    //toString-Method
    public String toString() {
        return "Document: " + this.title + " by " + this.author;
    }

    //further methods
    public int getAgeAt (Date today) {
        return this.releaseDate.getAgeInDaysAt(today);
    }

    private static String[] tokenize(String content){
        if (content == null){
            return new String[0];
        }
        //calculates the amount of words --> variable wordCount
        int wordCount = 1;     //wordCount = spaces + 1  --> wordCount initialized with 1, if content contains any words
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == ' ') {
                wordCount++;
            }
        }
        //Adds strings to to a string array
        String[] words = new String[wordCount];
        int index = 0;
        String word = "";
        for (int i = 0; i < content.length(); i++){
            //if a new word starts, the current word is stored in the string array
            if (content.charAt(i) == ' '){
                words[index] = copyString(word);
                word = "";
                index++;
            }
            else
                //variable word stores current word
                word = word + content.charAt(i);
        }
        //last word gets stored in the string array
        words[index] = word;
        return words;
    }

    private static String copyString(String str){
        String newString = "";
        for (int i=0; i<str.length(); i++){
            newString = newString + str.charAt(i);
        }
        return newString;
    }

    private static boolean sufficesEqual(String w1, String w2, int n){
        int w1Len = w1.length();
        int w2Len = w2.length();

        if ((n <= 0) || (n > w1Len) || (n > w2Len)){
            return false;
        }

        boolean isEqual = true;
        for(int i=0; i<n; i++){
            if(w1.charAt(w1Len - 1 - i) != w2.charAt(w2Len - 1 - i)){
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }

    private static String findSuffix(String word){
        if(word == null || word.equals("")){
            return "";
        }
        String suffix = "";
        String suffixHit = "";
        for(int i=0; i< SUFFICES.length; i++) {
            suffix = SUFFICES[i];
            if (sufficesEqual(word, suffix, suffix.length())){
                suffixHit = suffix;
                break;
            }
        }
        return suffixHit;
    }

    private static String cutSuffix(String word, String suffix){
        if (word == null || word.equals("") || suffix == null|| suffix.equals("")){
            return word;
        }

        if(sufficesEqual(word, suffix, suffix.length())){
            String newWord = "";
            for(int i = 0; i < (word.length() - suffix.length()); i++){
                newWord = newWord + word.charAt(i);
            }
            return newWord;
        } else {
            return word;
        }
    }

    private void addContent(String content){
        String words[] =tokenize(content);
        this.wordCounts = new WordCountsArray(0);
        for(int i=0; i<words.length; i++) {
            words[i] = cutSuffix(words[i], findSuffix(words[i]));
            this.wordCounts.add(words[i], 1);
        }
    }
}
