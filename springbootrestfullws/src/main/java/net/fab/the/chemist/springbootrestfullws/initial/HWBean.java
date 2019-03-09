package net.fab.the.chemist.springbootrestfullws.initial;

public class HWBean {
private String message;



public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public HWBean(String message) {
	super();
	this.message = message;
}

@Override
public String toString() {
	return "HWBean [message=" + message + "]";
}

}
