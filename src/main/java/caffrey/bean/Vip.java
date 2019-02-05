package caffrey.bean;

import java.util.Date;

public class Vip {
    private Integer vipId;

    private String vipName;

    private String vipEmail;

    private String address;

    private Date date;

    private Integer balance;

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName == null ? null : vipName.trim();
    }

    public String getVipEmail() {
        return vipEmail;
    }

    public void setVipEmail(String vipEmail) {
        this.vipEmail = vipEmail == null ? null : vipEmail.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}