package rest_d02;

import java.util.HashMap;

public class Pojo_Data {

    String name;
    String location;
    String phone;
    String courses[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourse(String[] courses) {
        this.courses = courses;
    }
}
