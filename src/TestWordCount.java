public class TestWordCount {
    public static void main(String[] args) {
        WordCount testConstructorNoWord = new WordCount("", 5);
        WordCount testConstructorNegCounter = new WordCount("Test", -5);
        WordCount testConstructorNothingRight = new WordCount("", -3);
        WordCount testObject = new WordCount("Test", 0);
        System.out.println(testObject.getWordCounter());
        System.out.println(testObject.incrementCount());
        System.out.println(testObject.incrementCount(-13));
        System.out.println(testObject.incrementCount(28));
        Date datum = new Date(1, 1, 1970);
        Date today = new Date(8, 11, 2013);
        int tage = datum.getAgeInDaysAt(today);
        System.out.println("TAGE:" + tage);
    }
}
