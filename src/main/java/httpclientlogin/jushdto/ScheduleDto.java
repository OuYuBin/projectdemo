package httpclientlogin.jushdto;


public class ScheduleDto {

	private String name;
	private boolean enabled;
	private TriggerDto trigger;
	private PushDto push;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public TriggerDto getTrigger() {
		return trigger;
	}

	public void setTrigger(TriggerDto trigger) {
		this.trigger = trigger;
	}

	public PushDto getPush() {
		return push;
	}

	public void setPush(PushDto push) {
		this.push = push;
	}

}
