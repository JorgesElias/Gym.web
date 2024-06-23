package Gym.comercial.demo.utils;


import lombok.Getter;

@Getter
public enum ErrorCatalog {


    CLIENTE_NOT_FOUND("ERR_CLIENTE_001", "cliente not found."),
    INVALID_CLIENTE("ERR_CLIENTE_002", "Invalid cliente parameters."),
    GENERIC_ERROR_CLIENTE("ERR_GEN_CLIENTE_001", "An unexpected error occurred."),

    MEMBRESIA_NOT_FOUND("ERR_MEMBRESIA_001", "membresia not found."),
    INVALID_MEMBRESIA("ERR_MEMBRESIA_002", "Invalid membresia parameters."),
    GENERIC_ERROR_MEMBRESIA("ERR_GEN_MEMBRESIA_001", "An unexpected error occurred."),

    GENERIC_NOT_FOUND("ERR_GENERIC_001", " not found."),
    GENERIC_BAD_REQUEST("ERR_GENERIC_002", "Invalid dueño parameters."),
    GENERIC_ERROR("ERR_GEN_GENERIC_001", "An unexpected error occurred."),

    DUENO_NOT_FOUND("ERR_DUENO_001", "dueño not found."),
    INVALID_DUENO("ERR_DUENO_002", "Invalid dueño parameters."),
    GENERIC_ERROR_DUENO("ERR_GEN_DUENO_001", "An unexpected error occurred."),;

  private final String code;
  private final String message;

    ErrorCatalog(String code, String message){
        this.code = code;
        this.message = message;
    }
}
