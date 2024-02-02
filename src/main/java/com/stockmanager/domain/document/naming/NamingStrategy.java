package com.stockmanager.domain.document.naming;

import com.stockmanager.domain.document.Document;

public interface NamingStrategy {
    String createName(Document document);
}
