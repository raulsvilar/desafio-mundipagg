package raulsvilar.desafiomundipagg.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private String name;
    @SerializedName(value="username", alternate = "email")
    private String email;
    private String company;
    private String refreshToken;
    private String accessToken;
    private int expiresIn;
    private String customerKey;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\n" +
                "Email: %s\n" +
                "Company: %s\n" +
                "Refresh Token: %s\n" +
                "Access Token: %s\n" +
                "Expires in: %d\n" +
                "Customer Key: %s", this.getName(), this.getEmail(), this.getCompany(),
                this.getRefreshToken(), this.getAccessToken(), this.getExpiresIn(),
                this.getCustomerKey());
    }
}
