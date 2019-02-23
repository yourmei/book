package caffrey.bean;

public class LoginItem {
    private Integer loginId;

    private Integer vipId;

    private Long time;

    private Boolean status;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "LoginItem [loginId=" + loginId + ", vipId=" + vipId + ", time=" + time + ", status=" + status + "]";
	}
    
    
}