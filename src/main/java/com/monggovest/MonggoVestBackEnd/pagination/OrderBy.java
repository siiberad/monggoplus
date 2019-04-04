package com.monggovest.MonggoVestBackEnd.pagination;


public enum OrderBy {
    HARGAMODAL("hargaModal"), PRODUCTID("productId");
    private String OrderByCode;
    private OrderBy(String orderBy) {
        this.OrderByCode = orderBy;
    }
    public String getOrderByCode() {
        return this.OrderByCode;
    }
}
