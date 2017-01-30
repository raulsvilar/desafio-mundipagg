package raulsvilar.desafiomundipagg.model;

/**
 * Created by raulsvilar on 20/01/17.
 */

public class Merchant {
    private String merchantKey;
    private String publicMerchantKey;
    private String name;
    private String documentNumber;
    private String corporateName;

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String getPublicMerchantKey() {
        return publicMerchantKey;
    }

    public void setPublicMerchantKey(String publicMerchantKey) {
        this.publicMerchantKey = publicMerchantKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }
}
