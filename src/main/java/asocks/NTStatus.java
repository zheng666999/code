package asocks;

/**
 * 组件状态
 */
public enum NTStatus {
	
	INVALID(-1,"非法"),
	INIT(0,"初始"),
	RUN(1,"运行"),
	STOP(2,"停止");
	
	private Integer code;
	private String desc;
	
	private NTStatus(Integer code , String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public NTStatus getDescByStatus(Integer code) {
		for(NTStatus oneNtStatus : NTStatus.values()) {
			if(oneNtStatus.code.equals(code)) {
				return oneNtStatus;
			}
		}
		return INVALID;
	}

}
