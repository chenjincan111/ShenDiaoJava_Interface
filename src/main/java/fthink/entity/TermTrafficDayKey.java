package fthink.entity;

public class TermTrafficDayKey {
    private String termNo;

    private String useDate;
    
    private String trafficType;
    
    public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo == null ? null : termNo.trim();
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate == null ? null : useDate.trim();
    }
}