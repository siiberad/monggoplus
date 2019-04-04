package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = false)
public class TransactionModel extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trxId;

    @Column(unique = true)
    private String trxInvoiceNum;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.Belum_Terbayar;

    @NotNull
    @Column(length = 20)
    private String noRekening;

    @NotNull
    private Integer lotTaken;

    @Lob
    @JsonIgnore
    private byte[] pdf;


    //relasi ke user
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserModel userModel;

    //relasi ke bank
    @ManyToOne
    @JoinColumn(name = "bankId")
    @JsonProperty
    private BankModel bankModel;

    //relasi ke product
    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonProperty
    private ProductModel productModel;

    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
    }

    public String getTrxInvoiceNum() {
        return trxInvoiceNum;
    }

    public void setTrxInvoiceNum(String trxInvoiceNum) {
        this.trxInvoiceNum = trxInvoiceNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public Integer getLotTaken() {
        return lotTaken;
    }

    public void setLotTaken(Integer lotTaken) {
        this.lotTaken = lotTaken;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public BankModel getBankModel() {
        return bankModel;
    }

    public void setBankModel(BankModel bankModel) {
        this.bankModel = bankModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "trxId=" + trxId +
                ", trxInvoiceNum='" + trxInvoiceNum + '\'' +
                ", status=" + status +
                ", noRekening='" + noRekening + '\'' +
                ", userModel=" + userModel +
                ", bankModel=" + bankModel +
                ", productModel=" + productModel +
                '}';
    }
}




