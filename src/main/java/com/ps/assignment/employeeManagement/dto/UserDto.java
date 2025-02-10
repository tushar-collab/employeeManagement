package com.ps.assignment.employeeManagement.dto;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private String email;
    private String phone;
    private String companyName;
    private Integer age;
    private String university;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String maidenName, String email, String phone,
            String companyName, Integer age, String university) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.age = age;
        this.university = university;
    }

    public UserDto(String firstName, String lastName, String maidenName, String email, String phone, String companyName,
            Integer age, String university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.age = age;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", maidenName="
                + maidenName + ", email=" + email + ", phone=" + phone + ", companyName=" + companyName + ", age=" + age
                + ", university=" + university + "]";
    }

}
