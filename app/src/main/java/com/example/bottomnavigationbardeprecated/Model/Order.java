package com.example.bottomnavigationbardeprecated.Model;

public class Order {
    private String Id;
    private String Name;
    private String NameDriver;
    private String phoneNumBerDriver;
    private String phoneNumber;
    private double Price;
    private String Address;
    private String Information;
    int check;

    public Order(){

    }

    public Order(String id, String name, String nameDriver, String phoneNumBerDriver, String phoneNumber, double price, String address, String information, int check) {
        Id = id;
        Name = name;
        NameDriver = nameDriver;
        this.phoneNumBerDriver = phoneNumBerDriver;
        this.phoneNumber = phoneNumber;
        Price = price;
        Address = address;
        Information = information;
        this.check = check;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameDriver() {
        return NameDriver;
    }

    public void setNameDriver(String nameDriver) {
        NameDriver = nameDriver;
    }

    public String getPhoneNumBerDriver() {
        return phoneNumBerDriver;
    }

    public void setPhoneNumBerDriver(String phoneNumBerDriver) {
        this.phoneNumBerDriver = phoneNumBerDriver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
