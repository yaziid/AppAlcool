package com.example.yazid.myapplication;

import java.util.List;

class EnsembleEtab {

    public EnsembleEtab() {
    }

    public EnsembleEtab(String nhits) {
        this.nhits = nhits;
    }

    private String nhits;

    private List<Etab> records;

    public String getNhits() {
        return nhits;
    }

    public void setNhits(String nhits) {
        this.nhits = nhits;
    }

    public List<Etab> getRecords() {
        return records;
    }

    public void setRecords(List<Etab> records) {
        this.records = records;
    }
}
