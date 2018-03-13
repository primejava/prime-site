package org.primejava.cms.pojo;

import java.io.Serializable;
import java.util.List;

public class AbstractFowardPojo   implements Serializable{
	private static final long serialVersionUID = 1L;
	protected List<String> destinations;
	protected String message;

	public final List<String> getDestinations() {
		return this.destinations;
	}

	public final void setDestinations(List<String> destinations) {
		this.destinations = destinations;
	}

	public final String getMessage() {
		return this.message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}
}
