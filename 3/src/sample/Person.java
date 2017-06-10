package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    public SimpleStringProperty Name;
    public SimpleStringProperty Phone;

    public Person(String name, String number) {
        this.Name = new SimpleStringProperty(name);
        this.Phone = new SimpleStringProperty(number);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getPhone() {
        return Phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone.set(phone);
    }
}
