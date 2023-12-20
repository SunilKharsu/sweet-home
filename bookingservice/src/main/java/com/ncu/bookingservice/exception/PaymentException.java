package com.ncu.bookingservice.exception;

public class PaymentException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private int statusCode;
   private String message;

   public PaymentException(String message, int statusCode) {
      super(message);
      this.statusCode = statusCode;
      this.message = message;
   }

   public String toString() {
      return "{\"statusCode\":" + this.statusCode + ", \"message\":\"" + this.message + '"' + "}";
   }

   public int getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}
