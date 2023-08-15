package oa.bean;

import java.util.Objects;

public class Student {
    private int no;
    private String name;
    private String phone;
    private String address;
    private String partjob;

    public Student(int no, String name, String phone, String address, String partjob) {
        this.no = no;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.partjob = partjob;
    }

    public Student() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPartjob() {
        return partjob;
    }

    public void setPartjob(String partjob) {
        this.partjob = partjob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return no == student.no && Objects.equals(name, student.name) && Objects.equals(phone, student.phone) && Objects.equals(address, student.address) && Objects.equals(partjob, student.partjob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name, phone, address, partjob);
    }
}
