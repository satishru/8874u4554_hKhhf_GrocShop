package app.groceryapp.data.model.api.response.product;import app.groceryapp.data.model.api.response.ApiError;import com.google.gson.annotations.Expose;import com.google.gson.annotations.SerializedName;public class ProductResponse extends ApiError {    @SerializedName("products")    @Expose    private Products products;    public Products getProducts() {        return products;    }    public void setProducts(Products products) {        this.products = products;    }}