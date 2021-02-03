/**
 * The class Date represents a date.
 * It ensures, that the represented date is always a valid date.
 */

public class Date {
    //<editor-fold desc="Attributes">
    /**
     * the day of the date
     */
    private int day;

    /**
     * the month of the date
     */
    private int month;

    /**
     * the year of the date
     */
    private int year;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    /**
     * Constructs a date with the given values:
     * @param day   the day
     * @param month the month
     * @param year  the year
     */
    public Date ( int day, int month, int year) {
        this.day = 1;
        // We choose a month with 31 days so that setter for days won't fail
        this.month = 1;
        // We choose a leapyear so that the day setter won't fail
        this.year = 2020;
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    /**
     * Constructs a date that represents the current date
     */
    public Date () {
        this.day = Terminal.TODAYS_DAY; // liefert den Tag des Monats (1..31)
        this.month = Terminal.TODAYS_MONTH; // liefert den aktuellen Monat (1..12)
        this.year =  Terminal.TODAYS_YEAR; // liefert das aktuelle Jahr (z.B. 2010)
    }

    //</editor-fold>

    //<editor-fold desc="Getter / Setter">

    //Getter-Methods
    /**
     * @return the day of the date.
     */
    public int getDay() {return this.day;}

    /**
     * @return the month of the date.
     */
    public int getMonth() {return this.month;}

    /**
     * @return the year of the date.
     */
    public int getYear() {return this.year;}

    //Setter-Methods
    /**
     * Sets the day of this date.
     * If the specified date is < 1, the the day is set to 1. If the specified day is greater than
     * the number of days in the month of this date, the it is set to the maximum value of days
     * in the month of this date.
     * @param day the new day
     */
    public void setDay(int day){
        if(day < 1){
            this.day = 1;
        } else if(day > daysInMonth(this.month, this.year)){
            this.day = daysInMonth(this.month, this.year);
        } else {
            this.day = day;
        }
    }

    /**
     * Sets the month of the date.
     * If the specified month is < 1, the month is set to 1. If the specified month is > 12, the month
     * is set to 12. If the new month has less days than the current month, it may happen that the day
     * of this date gets invalid. In this case, the day of this date is set to the maximum value of the
     * specified month.
     * @param month the new month
     */
    public void setMonth(int month){
        if (month < 1){
            this.month = 1;
        } else if(month > 12){
            this.month = 12;
        } else {
            this.month = month;
        }
        //To avoid that the day of this date gets invalid, execute the setDay method with the current day
        this.setDay(this.day);
    }

    /**
     * Sets the year of this date.
     * If the specified year is < 1970, then the year is set to 1970. If the specfied year is > 2100,
     * the the year is set to 2100. In case the current month of this date is February, the day of this
     * date might get invalid. Then the day is se to 28.</>
     * @param year the new year
     */
    public void setYear(int year){
        if(year<1970){
            this.year = 1970;
        } else if (year > 2100){
            this.year = 2100;
        } else {
            this.year = year;
        }
        //To avoid that the day of this date gets invalid, execute the setDay method with the current day
        this.setDay(this.day);
    }

    //</editor-fold>

    /**
     * @return a string representation of this date: day/month/year
     */
    public String toString() { return this.day + "/" + this.month + "/" + this.year; }

    /**
     * @return the days of this date since 01/01/1970
     */
    private int daysSince1970() {
        int days = 0;
        for (int i=1970; i<this.year; i++){
            days += this.daysInYear(i);
        }
        for(int i=1; i<this.month;i++){
            days += this.daysInMonth(i, this.year);
        }
        days += this.day -1;

        return days;
    }

    /**
     * Returns the days between the specified date and the date represented by this instance.
     * @param today the specified date
     * @return the days
     */
    public int getAgeInDaysAt(Date today) { return today.daysSince1970() - this.daysSince1970();}

    /**
     * Returns the full years between the specified date and the date represented by this instance.
     * @param today the specified date
     * @return the full years
     */
    public int getAgeInYearsAt(Date today) {
        int age = today.year - this.year;
        //"birthday" was earlier this year
        if (today.month > this.month)
            return age;
        //"birthday" is in a later month of this year
        else if (today.month < this.month) {
            return age - 1;
        } else {
            if (today.day >= this.day)
                return age;
            else
                return age - 1;
        }
    }

    /**
     * @param year the year
     * @return the number of days in the specified year
     */
    private int daysInYear(int year){
        int days = 0;
        for(int month=1; month <= 12; month++){
            days += this.daysInMonth(month, year);
        }
        return days;
    }

    /**
     * Returns the number of days in the specified month in the specified year.
     * For a month of value 2 the returned value is either 28 or 29, depending on the specified year.
     * @param month the month
     * @param year  the year
     * @return      the number of days in the specified month in the specified year
     */
    private int daysInMonth(int month, int year){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return daysInFebruary(year);
            default:
                return -1;
        }
    }

    /**
     * Returns the number of days in the month February of the specified year.
     * This method considers leap years.
     * @param year  the year
     * @return      the number of days in February of the specified year
     */
    private int daysInFebruary(int year){
        if(year % 4 != 0){
            return 28;
        }
        if((year % 100 == 0) && (year % 400 != 0)){
            return 28;
        }
        return 29;
    }

    /**
     * @param date the other date
     * @return true, if this instance and the specified Date are equal.
     */
    public boolean equals(Date date) {
        if (this == date){
            return true;
        }
        if (this == null){
            return false;
        }
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
}
