package app.groceryapp.ui.base;

public interface BaseNavigator {
    String getStringFromId(int string_id);
    boolean isNetWorkConnected();
    void showToast(String message);
    void displayError(Throwable throwable);
    void displayErrorMessage(int errorCode, String errorMessage);
    void showLoader(boolean isShowLoader);
    void showErrorLayout(String errorTitle, String errorMessage);
}
