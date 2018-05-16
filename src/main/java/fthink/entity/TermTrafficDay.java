package fthink.entity;

public class TermTrafficDay extends TermTrafficDayKey {
    private String traffic;

    private String createTm;

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm == null ? null : createTm.trim();
    }
}