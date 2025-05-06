package model;

import java.util.List;

public class SearchRequest {
    public List<String> brands;
    public List<String> categories;
    public Integer priceFrom;
    public Integer priceTo;
    public String orderBy; // price or quantity
    public String order;   // asc or desc
    public List<String> getBrands() {
        return brands;
    }
    public void setBrands(List<String> brands) {
        this.brands = brands;
    }
    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public Integer getPriceFrom() {
        return priceFrom;
    }
    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }
    public Integer getPriceTo() {
        return priceTo;
    }
    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
    @Override
    public String toString() {
        return "SearchRequest [brands=" + brands + ", categories=" + categories + ", priceFrom=" + priceFrom
                + ", priceTo=" + priceTo + ", orderBy=" + orderBy + ", order=" + order + "]";
    }
    public String getOrder() {
        return order;
    }
}

