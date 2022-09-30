package Entities;

public class Booking {
    public int bookingid;

    public Booking(){}

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(String _bookingid) {
        bookingid = Integer.parseInt(_bookingid);
    }
}
