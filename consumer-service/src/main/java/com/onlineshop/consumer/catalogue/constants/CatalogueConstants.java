package com.onlineshop.consumer.catalogue.constants;

public interface CatalogueConstants {

    String ERROR_CODE_CATEGORY_NOT_EXIST = "CATEGORY_DOES_NOT_EXIST";

    String ERROR_CODE_CATEGORY_MISSING_IN_REQUEST = "CATEGORY_MISSING_IN_REQUEST";

    String ERROR_CODE_INVENTORY_UNAVAILABLE = "INVENTORY_AVAILABILITY_INSUFFICIENT";

    String ERROR_CODE_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    String ERROR_CODE_BAD_REQUEST = "BAD_REQUEST";

    short INVENTORY_MIN_AVAILABLE_QTY = 40;
    String CATALOGUE_SERVICE_BASE_PATH = "/products";

    String CATALOGUE_SERVICE_ADMIN_PATH = "/admin";

    String CATALOGUE_SERVICE_VIEW_PATH = "/view";
    String MQ_NAME = "test-queue";
}
