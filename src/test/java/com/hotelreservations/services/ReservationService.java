package com.hotelreservations.services;

import com.hotelreservations.models.Auth;
import com.hotelreservations.models.Booking;
import com.hotelreservations.models.BookingDates;
import com.hotelreservations.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {

    //token oluştur

    public String generateToken(){

        Auth authBody = new Auth("admin" , "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");

    }


    //rezervasyon oluştur
    public BookingResponse createBooking (){
        BookingDates bookingDates = new BookingDates("2025-01-01" , "2025-02-02");
        Booking booking = new Booking("Test" ,"Cucumber" ,520 ,false , bookingDates , "Breakfast");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");
        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }



    //rezervasyon silme

    public void deleteBooking(int bookingId, String token) {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);
    }


}
