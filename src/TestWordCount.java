public class TestWordCount {
    public static void main(String[] args) {
        WordCount testConstructorNoWord = new WordCount("", 5);
        WordCount testConstructorNegCounter = new WordCount("Test", -5);
        WordCount testConstructorNothingRight = new WordCount("", -3);
        WordCount testObject = new WordCount("Test", 0);
        WordCount testObject1 = new WordCount("Test", 0);
        WordCount testObject2 = new WordCount("Test", 0);
        WordCount testObject3 = new WordCount("Test", 0);
        WordCountsArray testArray = new WordCountsArray(10);
        testArray.add("This",5);
        testArray.add("is",5);
        testArray.add("a",20);
        testArray.add("very",250);
        testArray.add("interesting",1);
        testArray.add("test",22);

        System.out.println(testObject.getWordCounter());
        System.out.println(testObject.incrementCount());
        System.out.println(testObject.incrementCount(-13));
        System.out.println(testObject.incrementCount(28));
        Date datum = new Date(1, 1, 1970);
        Date today = new Date(8, 11, 2013);
        int tage = datum.getAgeInDaysAt(today);
        System.out.println("TAGE:" + tage);

        System.out.println(testArray.toString());
        testArray.sort();
        System.out.println(testArray.toString());
    }
}
