package com.leshkins.companyanalytics.analyticsservice.serde;

public class SerdeFactory {
    public static IdSerde idSerde() {
        return new IdSerde();
    }

    public static CompanySerde companySerde() {
        return new CompanySerde();
    }

    public static AddressSerde addressSerde() {return new AddressSerde();}

    public static AddressCategorySerde addressCategorySerde() {return new AddressCategorySerde();}
}
