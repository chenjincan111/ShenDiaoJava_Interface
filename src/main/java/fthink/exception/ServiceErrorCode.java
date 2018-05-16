package fthink.exception;

public enum ServiceErrorCode {
	
    SUCCESS("0000", "成功"),

    ;

    private String code;

    private String message;

    /**
     * 构造函数
     * @param code
     * @param message
     */
    ServiceErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 根据编码返回枚举值
     * @param code
     * @return
     */
    public static ServiceErrorCode getEnums(String code) {
        for (ServiceErrorCode enums : values()) {
        	if(code!=null && code.equals(enums.getCode())){
        		 return enums;
        	}
        }
        return null;
    }
}
