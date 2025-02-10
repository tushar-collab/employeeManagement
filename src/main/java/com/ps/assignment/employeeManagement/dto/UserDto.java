package com.ps.assignment.employeeManagement.dto;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private String email;
    private String phone;
    private Integer age;
    private String university;
    private String userName;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String eyeColor;
    private String hairColor;
    private String hairType;
    private String ip;
    private String macAddress;
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String country;
    private String postalCode;
    private String addressLat;
    private String addressLong;
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;
    private String companyName;
    private String department;
    private String title;
    private String companyAddress;
    private String companyCity;
    private String companyState;
    private String companyStateCode;
    private String companyCountry;
    private String companyPostalCode;
    private String companyAddressLat;
    private String companyAddressLong;
    private String ein;
    private String ssn;
    private String userAgent;
    private String coin;
    private String wallet;
    private String network;
    private String role;

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

    public UserDto(Long id, String firstName, String lastName, String maidenName, String email, String phone,
            Integer age, String university, String userName, String password, String birthDate, String image,
            String bloodGroup, Double height, Double weight, String eyeColor, String hairColor, String hairType,
            String ip, String macAddress, String address, String city, String state, String stateCode, String country,
            String postalCode, String addressLat, String addressLong, String cardExpire, String cardNumber,
            String cardType, String currency, String iban, String companyName, String department, String title,
            String companyAddress, String companyCity, String companyState, String companyStateCode,
            String companyCountry, String companyPostalCode, String companyAddressLat, String companyAddressLong,
            String ein, String ssn, String userAgent, String coin, String wallet, String network, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.university = university;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.hairType = hairType;
        this.ip = ip;
        this.macAddress = macAddress;
        this.address = address;
        this.city = city;
        this.state = state;
        this.stateCode = stateCode;
        this.country = country;
        this.postalCode = postalCode;
        this.addressLat = addressLat;
        this.addressLong = addressLong;
        this.cardExpire = cardExpire;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.currency = currency;
        this.iban = iban;
        this.companyName = companyName;
        this.department = department;
        this.title = title;
        this.companyAddress = companyAddress;
        this.companyCity = companyCity;
        this.companyState = companyState;
        this.companyStateCode = companyStateCode;
        this.companyCountry = companyCountry;
        this.companyPostalCode = companyPostalCode;
        this.companyAddressLat = companyAddressLat;
        this.companyAddressLong = companyAddressLong;
        this.ein = ein;
        this.ssn = ssn;
        this.userAgent = userAgent;
        this.coin = coin;
        this.wallet = wallet;
        this.network = network;
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(String addressLat) {
        this.addressLat = addressLat;
    }

    public String getAddressLong() {
        return addressLong;
    }

    public void setAddressLong(String addressLong) {
        this.addressLong = addressLong;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyStateCode() {
        return companyStateCode;
    }

    public void setCompanyStateCode(String companyStateCode) {
        this.companyStateCode = companyStateCode;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyAddressLat() {
        return companyAddressLat;
    }

    public void setCompanyAddressLat(String companyAddressLat) {
        this.companyAddressLat = companyAddressLat;
    }

    public String getCompanyAddressLong() {
        return companyAddressLong;
    }

    public void setCompanyAddressLong(String companyAddressLong) {
        this.companyAddressLong = companyAddressLong;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", maidenName="
                + maidenName + ", email=" + email + ", phone=" + phone + ", age=" + age + ", university=" + university
                + ", userName=" + userName + ", password=" + password + ", birthDate=" + birthDate + ", image=" + image
                + ", bloodGroup=" + bloodGroup + ", height=" + height + ", weight=" + weight + ", eyeColor=" + eyeColor
                + ", hairColor=" + hairColor + ", hairType=" + hairType + ", ip=" + ip + ", macAddress=" + macAddress
                + ", address=" + address + ", city=" + city + ", state=" + state + ", stateCode=" + stateCode
                + ", country=" + country + ", postalCode=" + postalCode + ", addressLat=" + addressLat
                + ", addressLong=" + addressLong + ", cardExpire=" + cardExpire + ", cardNumber=" + cardNumber
                + ", cardType=" + cardType + ", currency=" + currency + ", iban=" + iban + ", companyName="
                + companyName + ", department=" + department + ", title=" + title + ", companyAddress=" + companyAddress
                + ", companyCity=" + companyCity + ", companyState=" + companyState + ", companyStateCode="
                + companyStateCode + ", companyCountry=" + companyCountry + ", companyPostalCode=" + companyPostalCode
                + ", companyAddressLat=" + companyAddressLat + ", companyAddressLong=" + companyAddressLong + ", ein="
                + ein + ", ssn=" + ssn + ", userAgent=" + userAgent + ", coin=" + coin + ", wallet=" + wallet
                + ", network=" + network + ", role=" + role + "]";
    }

    

}
