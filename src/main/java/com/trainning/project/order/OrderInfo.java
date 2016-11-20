package com.trainning.project.order;

public class OrderInfo {
    private String id;
    private String ordertime;
    private double total;
    private String status;
    private String recipients;
    private String address;
    private String phone;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrdertime() {
        return ordertime;
    }
    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRecipients() {
        return recipients;
    }
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "OrderInfo [id=" + id + ", ordertime=" + ordertime + ", total=" + total + ", status=" + status
                + ", recipients=" + recipients + ", address=" + address + ", phone=" + phone + "]";
    }
    
}
