package ru.utin.project_egar.utils;

import ru.utin.project_egar.models.documents_models.*;

public class CreateDocumentsUtil {

    public static Document createDocument(String fullNameTitle, long size) {
        String type = getTypeIsTitle(fullNameTitle);
        String title = findName(fullNameTitle, type);
        return findFormatDocument(type, title, size);
    }

    private static String findName(String name, String type) {
        return name.substring(0, name.length() - type.length());
    }

    private static Document findFormatDocument(String type, String title, long size) {
        Document[] documentsArray = DocumentsArray.DOCUMENTS_ARRAY;
        for (Document document : documentsArray) {
            if (document.getType().equals(type)) {
                document.setTitle(title);
                document.setSize(size / 1024 / 1024);  //Мб
                return document;
            }
        }
        return null;
    }

    private static String getTypeIsTitle(String fullTitle) {
        StringBuilder type = new StringBuilder();
        for (int i = fullTitle.length() - 1; i > 0; i--) {
            type.append(fullTitle.charAt(i));
            if (fullTitle.charAt(i) == '.') {
                break;
            }
        }
        return type.reverse().toString();
    }
}