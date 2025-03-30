package com.hotelreservations.steps;

import com.hotelreservations.models.Booking;
import com.hotelreservations.models.BookingResponse;
import com.hotelreservations.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {

    ReservationService reservationService = new ReservationService();
    String authKey;
    BookingResponse bookingResponse;


    @Given("Kullanici yeni bir rezervasyon oluşturuyor.")
    public void cagriBaslangici(){
    }

    @Given("Kullanıcı rezervasyon için gereken bilgileri veriyor.")
    public void createAuth (){
    authKey = reservationService.generateToken();
    }

    @When("Kullanici otel rezervasyonu yaratıyor")
    public void createReservation(){
        bookingResponse= reservationService.createBooking();
    }


    @Then("Rezervasyon başarılı şekilde oluşturuldu")
    public void reservationAssertions(){
        Assertions.assertEquals("Test", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Cucumber" , bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(520, bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Breakfast", bookingResponse.getBooking().getAdditionalneeds());

    }

    @Then("Kullanıcı rezervazyonu iptal ediyor")
    public void cancelReservation(){
        reservationService.deleteBooking(bookingResponse.getBookingid(), authKey);

    }
}
