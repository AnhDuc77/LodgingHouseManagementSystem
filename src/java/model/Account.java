package model;

import java.util.Date;

public class Account {

    private int accountID;
    private String email;
    private String username, password;
    private int roleId;
    private String typeOfLogin;
 
    private String fullName;
    private String province;
    private String district;

    public String getFullName() {
        return fullName;
    }
//hehehehee
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    private String ward;
    private String addressDetail;
    private boolean status;
        private String avatar;
    private String cic;
    private Date dob;
    private String phoneNumber;
    private int gender;
    public Account() {
    }
    public Account(int accountID, String email, String fullName, String province, String district, String ward, String addressDetail, String avatar, Date dob, String phoneNumber, int gender) {
        this.accountID = accountID;
        this.email = email;
        this.fullName = fullName;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.addressDetail = addressDetail;
        this.avatar = avatar;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    public Account(int accountID, String email, String username, String password, int roleId, String typeOfLogin) {
        this.accountID = accountID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }

    public Account(int accountID, String fullName, String province, String district, String ward, String addressDetail, String email, String phoneNumber, int gender, String username, String avatar, String cic, Date dob) {
        this.accountID = accountID;
        this.email = email;
        this.username = username;
    }

    public Account(String email, String username, String password, int roleId, String typeOfLogin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }
    
    public Account(String email, int roleId, String typeOfLogin) {
        this.email = email;
        this.roleId = roleId;
        this.typeOfLogin = typeOfLogin;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTypeOfLogin() {
        return typeOfLogin;
    }

    public void setTypeOfLogin(String typeOfLogin) {
        this.typeOfLogin = typeOfLogin;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
