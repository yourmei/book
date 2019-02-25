package caffrey.bean;

public class ShoppingCarItem {
    private Integer itemId;

    private Integer vipId;

    private Integer bookId;

    private Integer number;

    private Boolean status;

    private String bookName;
    
    private String bookAuthor;
    
    private Integer price;
    
    private Long purchTime;
    
    public Long getPurchTime() {
		return purchTime;
	}

	public void setPurchTime(Long purchTime) {
		this.purchTime = purchTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "ShoppingCarItem [itemId=" + itemId + ", vipId=" + vipId + ", bookId=" + bookId + ", number=" + number
				+ ", status=" + status + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", price=" + price
				+ ", purchTime=" + purchTime + "]";
	}
}