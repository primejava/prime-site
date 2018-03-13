package org.primejava.cms.pojo;

public class ForwardException extends RuntimeException {

	private static final long serialVersionUID = 1182012197662923608L;

	public ForwardException(Throwable e) {
		super(e);
	}

	public ForwardException(String message) {
		super(message);
	}
}
