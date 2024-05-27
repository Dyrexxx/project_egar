package ru.utin.project_egar.utils;

public class CreatePagesUtil {
    public static final Integer LIST_IN_PAGE = 6;

    public static int[] getListPages(int sizeDB) {
        if (sizeDB <= LIST_IN_PAGE) {
            return new int[]{1};
        }
        if (sizeDB % LIST_IN_PAGE == 0) {
            return createPagesList(sizeDB / LIST_IN_PAGE);
        } else {
            return createPagesList(sizeDB / LIST_IN_PAGE + 1);
        }
    }

    private static int[] createPagesList(int pages) {
        int[] listPages = new int[pages];
        for (int i = 0; i < pages; i++) {
            listPages[i] = i + 1;
        }
        return listPages;
    }
}
