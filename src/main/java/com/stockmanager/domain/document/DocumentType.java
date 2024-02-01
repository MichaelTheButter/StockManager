package com.stockmanager.domain.document;

public enum DocumentType {
    GOODS_RECEIVED("GR"), DISPATCH_NOTE("DN"), INVENTORY("INV");

    private String acronym;
    DocumentType(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }
}
