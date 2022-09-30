package Entities;

public class Booking {
    public int bookingid;

    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public Dates bookingdates;
    public String additionalneeds;

    public Booking(){}
    public Booking(int bookingid, String firstname, String lastname, int totalprice, boolean depositpaid, Dates bookingdates, String additionalneeds) {
        this.bookingid = bookingid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(String _bookingid) {
        bookingid = Integer.parseInt(_bookingid);
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public Dates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(Dates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}