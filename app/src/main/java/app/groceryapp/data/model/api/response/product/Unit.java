package app.groceryapp.data.model.api.response.product;import com.google.gson.annotations.Expose;import com.google.gson.annotations.SerializedName;public class Unit {    @SerializedName("unit_id")    @Expose    private Integer unitId;    @SerializedName("unit_name")    @Expose    private String unitName;    @SerializedName("is_active")    @Expose    private Integer isActive;    public Integer getUnitId() {        return unitId;    }    public void setUnitId(Integer unitId) {        this.unitId = unitId;    }    public String getUnitName() {        return unitName;    }    public void setUnitName(String unitName) {        this.unitName = unitName;    }    public Integer getIsActive() {        return isActive;    }    public void setIsActive(Integer isActive) {        this.isActive = isActive;    }}