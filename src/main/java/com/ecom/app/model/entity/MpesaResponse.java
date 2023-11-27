package com.ecom.app.model.entity;

public class MpesaResponse {
private String merchantRequestID;
private String checkoutRequestID;
private int responseCode;
private String responseDescription;


public MpesaResponse() {
}
public MpesaResponse(String merchantRequestID, String checkoutRequestID, int responseCode, String responseDescription,
    String customerMessage) {
  this.merchantRequestID = merchantRequestID;
  this.checkoutRequestID = checkoutRequestID;
  this.responseCode = responseCode;
  this.responseDescription = responseDescription;
  this.customerMessage = customerMessage;
}
private String customerMessage;


public String getMerchantRequestID() {
  return merchantRequestID;
}
public void setMerchantRequestID(String merchantRequestID) {
  this.merchantRequestID = merchantRequestID;
}
public String getCheckoutRequestID() {
  return checkoutRequestID;
}
public void setCheckoutRequestID(String checkoutRequestID) {
  this.checkoutRequestID = checkoutRequestID;
}
public int getResponseCode() {
  return responseCode;
}
public void setResponseCode(int responseCode) {
  this.responseCode = responseCode;
}
public String getResponseDescription() {
  return responseDescription;
}
public void setResponseDescription(String responseDescription) {
  this.responseDescription = responseDescription;
}
public String getCustomerMessage() {
  return customerMessage;
}
public void setCustomerMessage(String customerMessage) {
  this.customerMessage = customerMessage;
}




}
