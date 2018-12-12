package com.example.yazid.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MelService {

    public static final String ENDPOINT = "https://opendata.lillemetropole.fr/";

    @GET("/api/records/1.0/search/")
    Call<EnsembleEtab> listRecords(@Query("dataset") String dataSet,
                                   @Query("refine.libcom") String libCom,
                                   @Query("refine.libapet") String libApet
                            );
}
