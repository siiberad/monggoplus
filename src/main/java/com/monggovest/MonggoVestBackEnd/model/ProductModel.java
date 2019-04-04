package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = false)
public class ProductModel extends AuditModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Size(max = 100)
    private String productName;

    @NotNull
    @Size(max = 250)
    private String productPrice;

    private Integer jumlahTernak;

    private Integer hargaModal;

    private Integer hargaJual;

    private Integer laba;

    private Integer labaPeternak;

    private Integer labaInvestor;

    private Integer contractPeriodInMonth;

    private Double returnOfInvestment;

    private Integer hargaLot;

    private Integer jumlahTotalLot;

    @Size(max = 1000)
    private String productDetails;

    private String productImage1;

    private String productImage2;

    private String productImage3;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provinceId")
    @JsonProperty
    private ProvinceModel provinceModel;

    public ProductModel() {}
    @JsonCreator
    public ProductModel(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getJumlahTernak() {
        return jumlahTernak;
    }

    public void setJumlahTernak(Integer jumlahTernak) {
        this.jumlahTernak = jumlahTernak;
    }

    public Integer getHargaModal() {
        return hargaModal;
    }

    public void setHargaModal(Integer hargaModal) {
        this.hargaModal = hargaModal;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getLaba() {
        return laba;
    }

    public void setLaba(Integer laba) {
        this.laba = laba;
    }

    public Integer getLabaPeternak() {
        return labaPeternak;
    }

    public void setLabaPeternak(Integer labaPeternak) {
        this.labaPeternak = labaPeternak;
    }

    public Integer getLabaInvestor() {
        return labaInvestor;
    }

    public void setLabaInvestor(Integer labaInvestor) {
        this.labaInvestor = labaInvestor;
    }

    public Integer getContractPeriodInMonth() {
        return contractPeriodInMonth;
    }

    public void setContractPeriodInMonth(Integer contractPeriodInMonth) {
        this.contractPeriodInMonth = contractPeriodInMonth;
    }

    public Double getReturnOfInvestment() {
        return returnOfInvestment;
    }

    public void setReturnOfInvestment(Double returnOfInvestment) {
        this.returnOfInvestment = returnOfInvestment;
    }

    public Integer getHargaLot() {
        return hargaLot;
    }

    public void setHargaLot(Integer hargaLot) {
        this.hargaLot = hargaLot;
    }

    public Integer getJumlahTotalLot() {
        return jumlahTotalLot;
    }

    public void setJumlahTotalLot(Integer jumlahTotalLot) {
        this.jumlahTotalLot = jumlahTotalLot;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(String productImage1) {
        this.productImage1 = productImage1;
    }

    public String getProductImage2() {
        return productImage2;
    }

    public void setProductImage2(String productImage2) {
        this.productImage2 = productImage2;
    }

    public String getProductImage3() {
        return productImage3;
    }

    public void setProductImage3(String productImage3) {
        this.productImage3 = productImage3;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public ProvinceModel getProvinceModel() {
        return provinceModel;
    }

    public void setProvinceModel(ProvinceModel provinceModel) {
        this.provinceModel = provinceModel;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", jumlahTernak=" + jumlahTernak +
                ", hargaModal=" + hargaModal +
                ", hargaJual=" + hargaJual +
                ", laba=" + laba +
                ", labaPeternak=" + labaPeternak +
                ", labaInvestor=" + labaInvestor +
                ", contractPeriodInMonth=" + contractPeriodInMonth +
                ", returnOfInvestment=" + returnOfInvestment +
                ", hargaLot=" + hargaLot +
                ", jumlahTotalLot=" + jumlahTotalLot +
                ", productDetails='" + productDetails + '\'' +
                ", productImage1='" + productImage1 + '\'' +
                ", productImage2='" + productImage2 + '\'' +
                ", productImage3='" + productImage3 + '\'' +
                ", provinceModel=" + provinceModel +
                '}';
    }
}



