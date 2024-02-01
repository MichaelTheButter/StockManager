package com.stockmanager.domain.document.naming;

import com.stockmanager.domain.document.Document;
import com.stockmanager.domain.document.DocumentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DefaultNamingTest {

    @Test
    public void shouldCreateName() {
        //given
        int year = LocalDate.now().getYear();
        Document document = new Document();
        document.setDocumentType(DocumentType.GOODS_RECEIVED);
        document.setId(1L);
        document.setNumber(1);
        document.setCreateDate(LocalDate.now());

        DefaultNaming naming = new DefaultNaming();

        // when
        String name = naming.createName(document);
        String expected = document.getDocumentType().getAcronym()+ "/1/" + year;
        //then
        Assertions.assertEquals(expected, name);


    }

}