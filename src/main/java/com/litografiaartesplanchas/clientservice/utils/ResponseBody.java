package com.litografiaartesplanchas.clientservice.utils;

import java.util.List;

public class ResponseBody<T> {
	private int status;
	private String message;
	private List<T> data;
	
	public ResponseBody(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseBody(int status, String message, List<T> data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public static ResponseBody<?> ok(List<?> data) {
		return new ResponseBody(200, "ok", data);
	}
	
	public static ResponseBody<?> badRequest() {
		return new ResponseBody(400, "Something way wrong");
	}
	
	public static ResponseBody<?> notFound() {
		return new ResponseBody(404, "Not Found");
	}
	
	
}
