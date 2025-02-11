package com.ps.assignment.employeeManagement.dto;

public class ErrorResponse {
    
    
	private Integer status;
	
	private String message;
	
	private Long timeSTamp;
    
    private Boolean success;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer status, String message, Long timeSTamp, Boolean success) {
        this.status = status;
        this.message = message;
        this.timeSTamp = timeSTamp;
        this.success = success;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeSTamp() {
        return timeSTamp;
    }

    public void setTimeSTamp(Long timeSTamp) {
        this.timeSTamp = timeSTamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ErrorResponse [status=" + status + ", message=" + message + ", timeSTamp=" + timeSTamp + ", success="
                + success + "]";
    }

}
