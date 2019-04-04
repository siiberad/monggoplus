package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user")
public class UserModel implements  UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull
    private String userFullName;

    @Email(message = "user needs to be an email")
    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String userEmail;

    @NotNull
    private String userPassword;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String userBirthDate;

    @Enumerated(EnumType.ORDINAL)
    private IdCardType idCardType = getIdCardType().KTP;

    private String idCardNumber;

    private String userAddress;

    private String userProvince;

    @Column(unique = true )
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String userContact;

    private String userIncomeSource;

    private Integer userIncome;

    private boolean enabled;

    private String confirmationToken;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "userModel")
    @JsonIgnoreProperties(value = {"bankModel", "productModel"}, allowGetters = false)
    private List<TransactionModel> transactionModels = new ArrayList<>();

    public List<TransactionModel> getTransactionModels() {
        return transactionModels;
    }

    public void setTransactionModels(List<TransactionModel> transactionModels) {
        this.transactionModels = transactionModels;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public IdCardType getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(IdCardType idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserIncomeSource() {
        return userIncomeSource;
    }

    public void setUserIncomeSource(String userIncomeSource) {
        this.userIncomeSource = userIncomeSource;
    }

    public Integer getUserIncome() {
        return userIncome;
    }

    public void setUserIncome(Integer userIncome) {
        this.userIncome = userIncome;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return userPassword;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return userEmail;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirthDate='" + userBirthDate + '\'' +
                ", idCardType=" + idCardType +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userProvince='" + userProvince + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userIncomeSource='" + userIncomeSource + '\'' +
                ", userIncome=" + userIncome +
                ", enabled=" + enabled +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", transactionModels=" + transactionModels +
                '}';
    }
}
