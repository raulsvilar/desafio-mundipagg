package raulsvilar.desafiomundipagg.data.models;

/**
 * Created by raulsvilar on 31/01/17.
 */

public class Buyer {
    private String Birthdate;
    private String BuyerCategory;
    private String BuyerReference;
    private String CreateDateInMerchant;
    private String DocumentNumber;
    private String DocumentType;
    private String Email;
    private String EmailType;
    private String FacebookId;
    private String Gender;
    private String HomePhone;
    private String MobilePhone;
    private String Name;
    private String PersonType;
    private String TwitterId;
    private String WorkPhone;

    public Buyer() {
        this.DocumentNumber = "00000000000";
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getBuyerCategory() {
        return BuyerCategory;
    }

    public void setBuyerCategory(String buyerCategory) {
        BuyerCategory = buyerCategory;
    }

    public String getBuyerReference() {
        return BuyerReference;
    }

    public void setBuyerReference(String buyerReference) {
        BuyerReference = buyerReference;
    }

    public String getCreateDateInMerchant() {
        return CreateDateInMerchant;
    }

    public void setCreateDateInMerchant(String createDateInMerchant) {
        CreateDateInMerchant = createDateInMerchant;
    }

    public String getDocumentNumber() {
        return DocumentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        DocumentNumber = documentNumber;
    }

    public String getDocumentType() {
        return DocumentType;
    }

    public void setDocumentType(String documentType) {
        DocumentType = documentType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmailType() {
        return EmailType;
    }

    public void setEmailType(String emailType) {
        EmailType = emailType;
    }

    public String getFacebookId() {
        return FacebookId;
    }

    public void setFacebookId(String facebookId) {
        FacebookId = facebookId;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPersonType() {
        return PersonType;
    }

    public void setPersonType(String personType) {
        PersonType = personType;
    }

    public String getTwitterId() {
        return TwitterId;
    }

    public void setTwitterId(String twitterId) {
        TwitterId = twitterId;
    }

    public String getWorkPhone() {
        return WorkPhone;
    }

    public void setWorkPhone(String workPhone) {
        WorkPhone = workPhone;
    }
}
