package purchaseWeb;

import assignment3.GroupPurchaseItem;

import java.io.Serializable;

public class MyGroupPurchaseItem implements Serializable{
    private String id;
    private String seller;
    private String productName;
    private String introduction;
    private double price;
    private int limit;

    public MyGroupPurchaseItem() {
    }

    public MyGroupPurchaseItem(GroupPurchaseItem groupPurchaseItem) {
        this.id = groupPurchaseItem.getId();
        this.seller = groupPurchaseItem.getSeller();
        this.productName = groupPurchaseItem.getProductName();
        this.introduction = groupPurchaseItem.getIntroduction();
        this.price = groupPurchaseItem.getPrice();
        this.limit = groupPurchaseItem.getLimit();
    }

    public GroupPurchaseItem standard(){
        GroupPurchaseItem groupPurchaseItem = new GroupPurchaseItem();
        groupPurchaseItem.setId(this.id);
        groupPurchaseItem.setSeller(this.seller);
        groupPurchaseItem.setProductName(this.productName);
        groupPurchaseItem.setIntroduction(this.introduction);
        groupPurchaseItem.setPrice(this.price);
        groupPurchaseItem.setLimit(this.limit);
        return groupPurchaseItem;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeller() {
        return this.seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}
