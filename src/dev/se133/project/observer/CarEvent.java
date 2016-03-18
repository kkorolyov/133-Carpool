package dev.se133.project.observer;

public class CarEvent {
	private String message;
	
	public CarEvent(String m)
	{
		message = m;
	}
	
	public String getMessage()
	{
		return message;
	}

}
