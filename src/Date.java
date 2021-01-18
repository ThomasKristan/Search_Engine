public class Date {
    //Attributes
    private int day;
    private int month;
    private int year;

    //Constructors
    public Date ( int day, int month, int year) {
        this.year = year;
        if(month >= 1 && month <= 12)
            this.month = month;
        else {
            this.month = 1;
        }
        if(day >= 1 && day <= getMaxDay(month)){
            this.day = day;
        } else {
            this.day = 1;
        }
    }
    public Date () {
        this.day = Terminal.TODAYS_DAY; // liefert den Tag des Monats (1..31)
        this.month = Terminal.TODAYS_MONTH; // liefert den aktuellen Monat (1..12)
        this.year =  Terminal.TODAYS_YEAR; // liefert das aktuelle Jahr (z.B. 2010)
    }

    //Getter-Methods
    public int getDay() {return this.day;}
    public int getMonth() {return this.month;}
    public int getYear() {return this.year;}

    //Setter-Methods
    public void setDay(int day){
        if (day >= 1 && day <= getMaxDay(month))
            this.day = day;
    }

    public void setMonth(int month){
        if (month >= 1 && month <= 12){
            this.month = month;
            if (day > getMaxDay(month))
                day = getMaxDay(month);
        }
    }

    public void setYear(int year){
        if (day == 29 && month == 2 && isLeapYear(this.year) && !(isLeapYear(year))){
            day = 28;
        }
        this.year = year;
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

    private int getMaxDay(int month){
        switch (month){
            case 1: return 31;
            case 2:
                if (isLeapYear(year))
                    return 29;
                else
                    return 28;
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: return -1;
        }
    }

    private boolean isLeapYear(int year){
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}
