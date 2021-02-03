public class Document {
    //Attributes
    public static final String[] SUFFICES = {"isierung", "fizieren", "fikation", "isieren", "gerecht", "würdig",
            "schaft", "haltig", "meter", "logie", "legen", "kunde", "ismus", "ieren", "gemäß", "fähig", "artig", "wert",
            "voll", "ling", "lich", "lein", "keit", "ität", "isch", "iren", "heit", "haft", "fach", "chen", "ung",
            "tum", "sam", "nis", "mut", "mal", "los", "ist", "ion", "ent", "end", "eln", "bar", "ant", "or", "iv", "in",
            "ig", "ie", "er", "en", "ei", "al", "ab"};

    private String title = "";
    private String language = "";
    private String summary = "";
    private Date releaseDate;
    private Author author;
    private WordCountsArray wordCounts = new WordCountsArray(0);


    //Constructor

    public Document(String title, String language, String summary, Date releaseDate, Author author, String content){
        setTitle(title);
        setAuthor(author);
        setLanguage(language);
        setSummary(summary);
        setReleaseDate(releaseDate);
        setContent(content);
    }

    //<editor-fold desc="Getter / Setter Methods">

    //Getter-Methods
    public Author getAuthor() { return this.author; }
    public String getTitle() {return this.title; }
    public String getLanguage() { return this.language; }
    public String getSummary() { return this.summary; }
    public Date getReleaseDate() { return this.releaseDate; }
    public WordCountsArray getWordCounts(){ return this.wordCounts; }

    //Setter-Methods
    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        if (title != null)
            this.title = title;
    }

    public void setLanguage(String language) {
        if (language != null)
            this.language = language;
    }

    public void setSummary(String summary) {
        if (summary != null)
            this.summary = summary;
    }

    public void setContent(String content){
        if (content != null)
            addContent(content);
    }

    public void setReleaseDate(Date releaseDate){
        if(releaseDate != null && releaseDate.getAgeInDaysAt(new Date()) >= 0)
            this.releaseDate = releaseDate;
        else
            this.releaseDate = new Date();
    }

    //</editor-fold>

    //toString-Method
    public String toString(){
        return this.title + ", " + author.toString();
    }

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

    public boolean equals(Document document){
        if (this == document){
            return true;
        }
        if (this == null){
            return false;
        }
        return this.language.equals(document.language) && this.summary.equals(document.summary)
                && this.title.equals(document.title)
                && ((this.author != null && this.author.equals(document.author))
                    || (this.author == null && document.author == null))
                && ((this.releaseDate != null && this.releaseDate.equals(document.releaseDate))
                    || (this.releaseDate == null && document.releaseDate == null))
                && ((this.wordCounts != null && this.wordCounts.equals(document.getWordCounts()))
                    || (this.wordCounts == null && document.getWordCounts() == null));
     }
}
