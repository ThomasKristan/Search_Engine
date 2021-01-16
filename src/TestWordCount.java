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
    }
}
