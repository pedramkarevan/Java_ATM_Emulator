package com.atm.emulator.exception;

public class EntityNotFoundException  extends BaseException{

    public EntityNotFoundException( Long id) {
        super( id+" Not Found", ErrorCodes.ERROR_CODE_ENTITY_NOT_FOUND);
        this.id = id;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
