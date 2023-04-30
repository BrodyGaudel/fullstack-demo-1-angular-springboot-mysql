package com.brody.iaas.exceptions;

public class VmNotFoundException extends Exception{
    public VmNotFoundException(String message) {
        super(message);
    }
}
