package com.stockmanager.core.document.naming;

import com.stockmanager.core.document.Document;

public interface NamingStrategy {
    String createName(Document document);
}
