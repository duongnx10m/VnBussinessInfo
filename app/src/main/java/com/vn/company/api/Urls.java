package com.vn.company.api;

/**
 * Created by duongnx on 1/9/17.
 */

public class Urls {

    private static final String SERVER = "https://thongtindoanhnghiep.co";
    public static final String GET_COMPANY = SERVER + "/api/company?r=20&p=%s";
    public static final String GET_CITY = SERVER + "/api/city";
    public static final String GET_DISTRICT = SERVER + "/api/city/%s/district";
    public static final String SEARCH = SERVER + "/api/company?r=20&p=%s";
    public static final String GET_COMPANY_DETAIL = SERVER + "/api/company/%s";
    public static final String GET_CITY_DETAIL = SERVER + "/api/company?r=20&p=%s&l=%s";
    public static final String GET_INDUSTRY = SERVER + "/api/industry";
    public static final String GET_INDUSTRY_DETAIL = SERVER + "/api/company?r=20&p=%s&i=%s";

    public static final String MST_CA_NHAN = "http://tracuunnt.gdt.gov.vn/tcnnt/mstcn.jsp";
    public static final String MST_CA_DN = "http://tracuunnt.gdt.gov.vn/tcnnt/mstdn.jsp";

}
