package assignment1;

public class MemberAccountInfo {
	private String membername;
	private String pastCurrentReservations;
	private String borrowDate;
	private String expiryDate;
	

	public MemberAccountInfo() {
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getPastCurrentReservations() {
		return pastCurrentReservations;
	}

	public void setPastCurrentReservations(String pastCurrentReservations) {
		this.pastCurrentReservations = pastCurrentReservations;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
