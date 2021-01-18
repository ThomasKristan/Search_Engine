public class Test {
    public static void main(String[] args) {
        System.out.println("----------------WordCountsArray----------------");
        System.out.println("Constructor");
        WordCountsArray testWordCounts = new WordCountsArray(-1); //Testing negativ input
        System.out.println("empty Array".equals(testWordCounts.toString()) + " | EXPECTED: empty Array | OUTPUT: " + testWordCounts.toString());
        testWordCounts = new WordCountsArray(4); //Testing if constructor works
        System.out.println(" | [0] null | [1] null | [2] null | [3] null".equals(testWordCounts.toString()) +
                " | EXPECTED: | [0] null | [1] null | [2] null | [3] null | OUTPUT:" +
                testWordCounts.toString());

        //----------------------------add()----------------------------//
        System.out.println("\nadd()");
        testWordCounts.add("Test1", 1);
        testWordCounts.add("Test1", -6);    //negative counts -> should be 0
        testWordCounts.add("", 5);          //"" -> should not be added
        testWordCounts.add("Test4", 10);    //for testing if "" has been added
        System.out.println(" | [0] \"Test1\": 1 | [1] \"Test1\": 0 | [2] \"Test4\": 10 | [3] null | [4] null | [5] null | [6] null"
                .equals(testWordCounts.toString()) + " | EXPECTED: | [0] \"Test1\": 1 | [1] \"Test1\": 0 | [2] \"Test4\": 10 | [3] " +
                "null | [4] null | [5] null | [6] null | OUTPUT: " + testWordCounts.toString());

        //----------------------------size()----------------------------//
        System.out.println("\nsize()");
        testWordCounts = new WordCountsArray(0);    //empty array
        System.out.println((0 == testWordCounts.size()) + " | EXPECTED: 0 | OUTPUT: " + testWordCounts.size());
        testWordCounts = new WordCountsArray(5);
        testWordCounts.add("Test1", 1);
        testWordCounts.add("Test2", 37);
        System.out.println((2 == testWordCounts.size()) + " | EXPECTED: 2 | OUTPUT: " + testWordCounts.size());

        //----------------------------getWord()----------------------------//
        System.out.println("\ngetWord()");
        System.out.println("".equals(testWordCounts.getWord(-1)) + " | EXPECTED:  | OUTPUT: " + testWordCounts.getWord(-1));
        System.out.println("Test2".equals(testWordCounts.getWord(1)) + " | EXPECTED: Test2 | OUTPUT: " + testWordCounts.getWord(1));
        System.out.println("".equals(testWordCounts.getWord(4)) + " | EXPECTED:  | OUTPUT: " + testWordCounts.getWord(4));

        //----------------------------getCount()----------------------------//
        System.out.println("\ngetCount()");
        System.out.println((-1 == testWordCounts.getCount(-1)) + " | EXPECTED: -1 | OUTPUT: " + testWordCounts.getCount(-1));
        System.out.println((37 == testWordCounts.getCount(1)) + " | EXPECTED: 37 | OUTPUT: " + testWordCounts.getCount(1));
        System.out.println((-1 == testWordCounts.getCount(4)) + " | EXPECTED: -1 | OUTPUT: " + testWordCounts.getCount(4));

        //----------------------------setCount()----------------------------//
        System.out.println("\nsetCount()");
        testWordCounts = new WordCountsArray(2);
        testWordCounts.add("Test", 1);
        testWordCounts.setCount(-1, 5); //nothing should change
        System.out.println(" | [0] \"Test\": 1 | [1] null | [2] null".equals(testWordCounts.toString()) +
                " | EXPECTED: [0] \"Test\": 1 | [1] null | [2] null | OUTPUT: " + testWordCounts.toString());
        testWordCounts.setCount(0, -5); //ounter should be 0
        System.out.println(" | [0] \"Test\": 0 | [1] null | [2] null".equals(testWordCounts.toString()) +
                " | EXPECTED: [0] \"Test\": 0 | [1] null | [2] null  | OUTPUT: " + testWordCounts.toString());
        testWordCounts.setCount(0, 2342); //should work
        System.out.println(" | [0] \"Test\": 2342 | [1] null | [2] null".equals(testWordCounts.toString()) +
                " | EXPECTED: [0] \"Test\": 2342 | [1] null | [2] null | OUTPUT: " + testWordCounts.toString());
        testWordCounts.setCount(1, 1337); //nothing should change
        System.out.println(" | [0] \"Test\": 2342 | [1] null | [2] null".equals(testWordCounts.toString()) + " | EXPECTED: " +
                " | [0] \"Test\": 7353 | [1] null | OUTPUT: " + testWordCounts.toString());

        System.out.println("\n----------------Document----------------");
        System.out.println("Constructor:");
        Document testDocument = new Document(null, null, null, null, null, null);
        System.out.println("\nTitle: " + testDocument.getTitle() + "\nLanguage: " + testDocument.getLanguage() +
                "\nSummary: " + testDocument.getSummary() + "\nreleaseDate: " + testDocument.getReleaseDate().toString() +
                "\nAuthor: " + testDocument.getAuthor() + "\nContent: " + testDocument.getWordCounts().toString());
        testDocument = new Document("1,5 Millionen Pinguine in der Antarktis entdeckt",
                "German",
                "Die Entdeckung riesiger Pinguinkolonien in der Antarktis hat Wissenschafter überrascht: Auf "+
                        "den abgelegenen Danger Islands im Osten der Antarktischen Halbinsel fanden sie Kolonien von "+
                        "insgesamt 1,5 Millionen Adeliepinguinen.",
                new Date(2, 3, 2018),
                new Author("Hubert", "Patterer", new Date(1,1,1962), "Graz,"+
                        " Österreich", "patterer@kleinezeitung.at"),
                "Nur einhundertsechzig Kilometer westlich des Archipels gehe diese Art wegen der Eisschmelze zurück heißt es "+
                        "in dem am Freitag in der Zeitschrift Scientific Reports erschienenen Artikel " +
                        "Dass die kleinen Pinguine mit dem weißen Bauch dem schwarzen Kopf und den weiß umrandeten "+
                        "Augen auf mindestens neun Inseln im Weddell Meer leben war bekannt Doch nun stellte sich heraus"+
                        " dass ihre Zahl weitaus größer ist Die Wissenschafter aus den USA Frankreich und Großbritannien"+
                        " machten die Entdeckung anhand der Analyse von NASA Satellitenbildern wie Heather Lynch von der"+
                        " Universität Stony Brook in den USA sagte Am Anfang dachte ich es ist ein Fehler sagte Lynch"+
                        " Aber die hoch aufgelösten Satellitenbilder hätten dann gezeigt dass die Forscher eine " +
                        "bedeutende Entdeckung gemacht hätten");
        System.out.println("\nTitle: " + testDocument.getTitle() + "\nLanguage: " + testDocument.getLanguage() +
                "\nSummary: " + testDocument.getSummary() + "\nreleaseDate: " + testDocument.getReleaseDate().toString() +
                "\nAuthor: " + testDocument.getAuthor() + "\nContent: " + testDocument.getWordCounts().toString());
    }
}
