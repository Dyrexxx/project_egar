package ru.utin.project_egar.utils;

import ru.utin.project_egar.models.sort_models.SortModel;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLCreateUtil {

    public static String createURL(Integer page, SortModel sortModel) {
        return "redirect:/documents/index?page=" + page + "&list=" +
                CreatePagesUtil.LIST_IN_PAGE + "&" + sortModel.getUrl();

    }

    public static String createURL(Integer page, SortModel sortModel, String finded) {
        finded = URLEncoder.encode(finded, StandardCharsets.UTF_8);
        return  "redirect:/documents/index?page=" + page + "&list=" +
                CreatePagesUtil.LIST_IN_PAGE + "&" + sortModel.getUrl() + "&find=" + finded;

    }
}
