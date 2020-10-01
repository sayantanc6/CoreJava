package intertwinedbasicandoauth2ssso.util;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private final String appUrl;
    private final Locale locale;
    private final UserEmployeeModel user;
    
	public OnRegistrationCompleteEvent(Object source, String appUrl, Locale locale, UserEmployeeModel user) {
		super(source);
		this.appUrl = appUrl;
		this.locale = locale;
		this.user = user;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public UserEmployeeModel getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "OnRegistrationCompleteEvent [appUrl=" + appUrl + ", locale=" + locale + ", user=" + user + "]";
	}
    
}
