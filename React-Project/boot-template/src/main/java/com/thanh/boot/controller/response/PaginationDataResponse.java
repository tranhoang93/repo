package com.thanh.boot.controller.response;

import org.springframework.data.domain.Page;

import java.util.Collection;

public class PaginationDataResponse<T> {
    protected Pagination pagination;
    protected Collection<T> data;

    public PaginationDataResponse() {
    }

    public PaginationDataResponse(Page<T> page) {
        pagination = new Pagination();
        pagination.setSize(page.getSize());
        pagination.setPage(page.getNumber());
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        this.data = page.getContent();

    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public static class Pagination {
        private long totalRecords;
        private int totalPages;
        private int page;
        private int size;

        @Override
        public String toString() {
            return "Pagination{" +
                    "totalRecords=" + totalRecords +
                    ", totalPages=" + totalPages +
                    ", page=" + page +
                    ", size=" + size +
                    '}';
        }

        public long getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(long totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
