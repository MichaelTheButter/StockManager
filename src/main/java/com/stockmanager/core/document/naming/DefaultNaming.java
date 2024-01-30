package com.stockmanager.core.document.naming;

import com.stockmanager.core.document.Document;

public class DefaultNaming implements NamingStrategy{
    private final String SEPARATOR = "/";
    @Override
    public String createName(Document document) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                document.getDocumentType()
                .getAcronym()
        )
                .append(SEPARATOR)
                .append(document.getNumber())
                .append(SEPARATOR)
                .append(document.getCreateDate()
                .getYear()
        );
        return builder.toString();
    }
}
