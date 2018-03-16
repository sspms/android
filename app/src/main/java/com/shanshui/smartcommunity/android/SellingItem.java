package com.shanshui.smartcommunity.android;

/**
 * Created by I336253 on 3/3/2018.
 */

public class SellingItem {
    private long id;
    private String name;
    private double price;
    private String unit;
    private int image;

    public SellingItem(long id, String name, double price, String unit, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public int getImage() {
        return image;
    }

    public static SellingItem[] mock() {
        return new SellingItem[]{
                new SellingItem(1, "青菜", 2.5, "斤", R.drawable.ic_qingcai),
                new SellingItem(2, "白菜", 1.5, "斤", R.drawable.ic_baicai),
                new SellingItem(3, "辣椒", 5.5, "斤", R.drawable.ic_lajiao),
                new SellingItem(4, "萝卜", 2.5, "斤", R.drawable.ic_luobo),
                new SellingItem(5, "荷兰豆", 13.5, "斤", R.drawable.ic_helandou),
                new SellingItem(6, "黄瓜", 3.0, "斤", R.drawable.ic_huanggua),
                new SellingItem(7, "生菜", 2.5, "斤", R.drawable.ic_shengcai),
                new SellingItem(8, "西红柿", 5.5, "斤", R.drawable.ic_xihongshi),
                new SellingItem(9, "香菇", 15.5, "斤", R.drawable.ic_xianggu)
        };
    }
}
