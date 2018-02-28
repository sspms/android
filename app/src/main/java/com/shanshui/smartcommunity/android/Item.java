package com.shanshui.smartcommunity.android;

import android.view.View;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Item {
    // david
    private String orderId;
    private String issuePosition;
    private String issueCategory;
    private String issueReportDate;
    private String issueDaysPassed;
    private String issueCurrentStatus;
    private String issueDescription;

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String orderId, String issuePosition, String issueCategory, String issueReportDate,
                String issueDaysPassed, String issueCurrentStatus, String issueDescription) {
        this.orderId = orderId;
        this.issuePosition = issuePosition;
        this.issueCategory = issueCategory;
        this.issueReportDate = issueReportDate;
        this.issueDaysPassed = issueDaysPassed;
        this.issueCurrentStatus = issueCurrentStatus;
        this.issueDescription = issueDescription;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIssuePosition() {
        return issuePosition;
    }

    public void setIssuePosition(String issuePosition) {
        this.issuePosition = issuePosition;
    }

    public String getIssueCategory() {
        return issueCategory;
    }

    public void setIssueCategory(String issueCategory) {
        this.issueCategory = issueCategory;
    }

    public String getIssueReportDate() {
        return issueReportDate;
    }

    public void setIssueReportDate(String issueReportDate) {
        this.issueReportDate = issueReportDate;
    }

    public String getIssueDaysPassed() {
        return issueDaysPassed;
    }

    public void setIssueDaysPassed(String issueDaysPassed) {
        this.issueDaysPassed = issueDaysPassed;
    }

    public String getIssueCurrentStatus() {
        return issueCurrentStatus;
    }

    public void setIssueCurrentStatus(String issueCurrentStatus) {
        this.issueCurrentStatus = issueCurrentStatus;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (orderId != null ? !orderId.equals(item.orderId) : item.orderId != null) return false;
        if (issuePosition != null ? !issuePosition.equals(item.issuePosition) : item.issuePosition != null)
            return false;
        if (issueCategory != null ? !issueCategory.equals(item.issueCategory) : item.issueCategory != null)
            return false;
        if (issueReportDate != null ? !issueReportDate.equals(item.issueReportDate) : item.issueReportDate != null)
            return false;
        if (issueDaysPassed != null ? !issueDaysPassed.equals(item.issueDaysPassed) : item.issueDaysPassed != null)
            return false;
        if (issueCurrentStatus != null ? !issueCurrentStatus.equals(item.issueCurrentStatus) : item.issueCurrentStatus != null)
            return false;
        if (issueDescription != null ? !issueDescription.equals(item.issueDescription) : item.issueDescription != null)
            return false;
        return requestBtnClickListener != null ? requestBtnClickListener.equals(item.requestBtnClickListener) : item.requestBtnClickListener == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (issuePosition != null ? issuePosition.hashCode() : 0);
        result = 31 * result + (issueCategory != null ? issueCategory.hashCode() : 0);
        result = 31 * result + (issueReportDate != null ? issueReportDate.hashCode() : 0);
        result = 31 * result + (issueDaysPassed != null ? issueDaysPassed.hashCode() : 0);
        result = 31 * result + (issueCurrentStatus != null ? issueCurrentStatus.hashCode() : 0);
        result = 31 * result + (issueDescription != null ? issueDescription.hashCode() : 0);
        result = 31 * result + (requestBtnClickListener != null ? requestBtnClickListener.hashCode() : 0);
        return result;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("#14", "25栋201室", "墙", "2016.3.1", "两年前", "待评价", "公司来电问，怎么回事？都三天了还不去弄（我这不是身体不舒服嘛，弄什么弄？你要快，你叫别人去）家里的龙眼熟了，回去摘些吃吃，宽带的事，明天再去……"));
        items.add(new Item("#23", "西大门", "门", "2017.8.15", "六个月前", "待评价", "昨夜睡的晚了，起床已经9点了，起来抽烟，喝茶，吃早餐，又中午了。看看外面的太阳……唉！！宽带的事，还是明天再去吧！"));
        items.add(new Item("#63", "游乐场", "游乐设施", "2018.1.23", "一个月前", "维修中", "今天起来觉得身体不太对劲，有点累。有人拿两个主机来叫修修，唉……精神不好，你先放到墙角那里吧！宽带用户来电问，怎么还不来检查的？唉……身体不舒服，明天吧！"));
        items.add(new Item("#19", "地库", "车位", "2017.4.23", "一年前", "已评价", "昨夜喝了点酒，和一帮帅哥美女疯到半夜，今天起床晚了，头有点痛，休息一下，下午再去检查宽带，刚刚想去的，天又下雨了，唉……还是明天再去吧！"));
        items.add(new Item("#5", "30栋大门口", "门", "2015.6.26", "三年前", "已评价", "哦，还没有到这个日子……"));
        return items;

    }

}
