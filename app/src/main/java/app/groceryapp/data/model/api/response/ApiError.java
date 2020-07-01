package app.groceryapp.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ApiError implements Serializable {
    @Expose
    @SerializedName("error_code")
    private int errorCode;

    @Expose
    @SerializedName("error_message")
    private String errorMessage;

    @Expose
    @SerializedName("error_info")
    private List<String> errorInfo;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<String> errorInfo) {
        this.errorInfo = errorInfo;
    }
}
