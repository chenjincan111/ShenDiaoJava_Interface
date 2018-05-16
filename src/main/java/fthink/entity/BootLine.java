package fthink.entity;

public class BootLine {
    private String uuid;

    private String device_no;

    private String lastOffTm;

    private String lastOnTm;

    private String createTm;

    private String lastModifyTm;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getLastOffTm() {
        return lastOffTm;
    }

    public void setLastOffTm(String lastOffTm) {
        this.lastOffTm = lastOffTm == null ? null : lastOffTm.trim();
    }

    public String getLastOnTm() {
        return lastOnTm;
    }

    public void setLastOnTm(String lastOnTm) {
        this.lastOnTm = lastOnTm == null ? null : lastOnTm.trim();
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm == null ? null : createTm.trim();
    }

    public String getLastModifyTm() {
        return lastModifyTm;
    }

    public void setLastModifyTm(String lastModifyTm) {
        this.lastModifyTm = lastModifyTm == null ? null : lastModifyTm.trim();
    }
}