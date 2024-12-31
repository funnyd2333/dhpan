package com.dh.dhpan;

public class exception {
    public static class UsernameExistsException extends RuntimeException {
        public UsernameExistsException(String message) {
            super(message);
        }
    }

}
