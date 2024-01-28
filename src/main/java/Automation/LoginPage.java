package Automation;

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;
    public LoginPage(Page page)
    {
        this.page = page;
    }

    String userNameInputBox = "#user-name";
    String passwordInputBox = "#password";
    String submitButton = "#login-button";

    public void login(String un, String pwd){
        page.fill(userNameInputBox, un);
        page.fill(passwordInputBox, pwd);
        page.click(submitButton);
    }
}
