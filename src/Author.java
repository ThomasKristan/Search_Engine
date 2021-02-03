public class Author {
    //Attributes
    private String firstName;
    private String lastName;
    private Date birthday;
    private String residence;
    private String email;

    //Constructor
    public Author (String firstName, String lastName, Date birthday, String residence, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setResidence(residence);
        setEmail(email);
    }

    //Getter-Methods
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public Date getBirthday() {return birthday;}
    public String getResidence() {return residence;}
    public String getEmail() {return email;}

    //Setter-Methods
    public void setFirstName(String firstName) {
        if (firstName == null) {
            this.firstName = "";
        } else {
            this.firstName = firstName;
        }
    }
    public void setLastName(String lastName) {
        if (lastName == null) {
            this.lastName = "";
        } else {
            this.lastName = lastName;
        }
    }
    public void setBirthday(Date birthday) {
        if (birthday == null) {
            this.birthday = new Date(1, 1, 1999);
        } else {
            this.birthday = birthday;
        }
    }
    public void setResidence(String residence) {
        if (residence == null) {
            this.residence = "";
        } else {
                this.residence = residence;
        }
    }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            this.email = "";
        } else {
            this.email = email;
        }
    }

    //toString-Method
    public String toString() {
        return "Author: " + this.firstName + " " + this.lastName;
    }

    public String getContactInformation() {
        return "name: " + this.lastName + " " + this.firstName + Terminal.NEWLINE +
                "e-mail: " + this.email + Terminal.NEWLINE +
                "residence: " + this.residence;
    }

    public int getAgeAt(Date today) {
        return this.birthday.getAgeInYearsAt(today);
    }

    public boolean equals(Author author){
        if(author == this) {
            return true;
        }
        if(author == null){
            return false;
        }
        boolean equalName = this.toString().equals(author.toString());
        boolean equalContact = (this.email.equals(author.email) && this.residence.equals(author.residence));
        boolean equalBirthday = this.birthday.equals(author.birthday);

        return this.firstName.equals(author.firstName) && this.lastName.equals(author.lastName)
                && this.residence.equals(author.residence) && this.email.equals(author.email)
                && ((this.birthday != null && this.birthday.equals(author.birthday))
                    || (this.birthday == null && author.birthday == null));
    }
}
