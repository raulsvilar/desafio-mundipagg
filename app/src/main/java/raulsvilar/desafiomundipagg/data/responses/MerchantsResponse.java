package raulsvilar.desafiomundipagg.data.responses;

import java.util.List;

import raulsvilar.desafiomundipagg.data.models.Merchant;

public class MerchantsResponse {
    private List<Merchant> items;
    private Pagination pagination;

    public List<Merchant> getMerchants() {
        return items;
    }

    public boolean hasNext() {
        return pagination.getPageNumber() > pagination.getPageCount();
    }

    public int getPage() {
        return pagination.getPageNumber();
    }

    public int getNextPage() {
        return pagination.getPageNumber()+1;
    }

    private class Pagination {
        Options options;

        private int getPageNumber() {
            return options.pageNumber;
        }

        private int getPageCount() {
            return options.pageCount;
        }
    }

    private class Options {
        private int pageNumber;
        private int pageSize;
        private int itemCount;
        private int pageCount;
    }
}
