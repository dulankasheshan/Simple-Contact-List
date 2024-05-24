package com.dulanka.mycontact;

public class Item {

    String name;
    String contactNum;
    int image;
    String address;
    String email;




    //Constructor ----------------------------------
    public Item(String name, String contactNum, int image, String address, String email
    ) {
        this.name = name;
        this.contactNum = contactNum;
        this.image = image;
        this.address = address;
        this.email = email;
    }


    public Item(String name, String contactNum, String address, String email
    ) {
        this.name = name;
        this.contactNum = contactNum;
        this.address = address;
        this.email = email;
    }

    //Getter -----------------------------------------
    public String getName() {
        return name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public int getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }


    //Setter -----------------------------------------


    public void setName(String name) {
        this.name = name;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
