package raulsvilar.desafiomundipagg.model;

import java.util.List;

public class MerchantsResponse {
    private List<Merchant> items;
    private Pagination pagination;

    public List<Merchant> getMerchants() {
        return items;
    }

    public boolean hasNext() {
        return pagination.pageNumber > pagination.pageCount;
    }

    public int getPage() {
        return pagination.pageNumber;
    }

    public int getNextPage() {
        return ++pagination.pageNumber;
    }

    private class Pagination {
        private int pageNumber;
        private int pageSize;
        private int itemCount;
        private int pageCount;
    }
}
