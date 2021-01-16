public class Date {
    //Attributes
    private int day;
    private int month;
    private int year;

    //Getter-Methods
    public int getDay() {return this.day;}
    public int getMonth() {return this.month;}
    public int getYear() {return this.year;}

    //Constructors
    public Date ( int day, int month, int year) {
    }
    public Date () {
        this.day = Terminal.TODAYS_DAY; // liefert den Tag des Monats (1..31)
        this.month = Terminal.TODAYS_MONTH; // liefert den aktuellen Monat (1..12)
        this.year =  Terminal.TODAYS_YEAR; // liefert das aktuelle Jahr (z.B. 2010)
    }

    //toString-Method
    public String toString() {
        return "Date: " + this.day + "." + this.month + "." + this.year;
    }

    //further methods
    private int daysSince1970() {
        return ((this.year - 1970) * 12 * 30) + (this.month * 30) + this.day;
    }

    public int getAgeInDaysAt(Date today) {
        return today.daysSince1970() - this.daysSince1970();
    }

    public int getAgeInYearsAt(Date today) {
        int ageInDays = this.getAgeInDaysAt(today);
        int ageInMonths = ageInDays / 30;
        int ageInYears = ageInMonths / 12;
        return ageInYears;
    }
}
