package com.litografiaartesplanchas.clientservice.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.litografiaartesplanchas.clientservice.model.Client;

public class Response {
	public static ResponseEntity<ResponseBody> ok(List<?> data){
		return new ResponseEntity<ResponseBody>(ResponseBody.ok(data), HttpStatus.OK);
	}
	
	public static ResponseEntity<Object> noContent() {
		return ResponseEntity.noContent().build();
	}
	
	public static ResponseEntity<ResponseBody> notFound() {
		return new ResponseEntity<ResponseBody>(ResponseBody.notFound(), HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<ResponseBody> badRequest() {
		return new ResponseEntity<ResponseBody>(ResponseBody.badRequest(), HttpStatus.BAD_REQUEST);
	}
}
