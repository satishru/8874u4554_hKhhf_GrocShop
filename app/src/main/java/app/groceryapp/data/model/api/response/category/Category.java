package app.groceryapp.data.model.api.response.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_description")
    @Expose
    private Object categoryDescription;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("image_full_path")
    @Expose
    private String imageFullPath;
    @SerializedName("sub_category")
    @Expose
    private List<SubCategory> subCategoryList = null;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(Object categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getImageFullPath() {
        return imageFullPath;
    }

    public void setImageFullPath(String imageFullPath) {
        this.imageFullPath = imageFullPath;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategory) {
        this.subCategoryList = subCategory;
    }
}
