package com.example.mynoesantara.models;

import com.google.gson.annotations.SerializedName;

public class Prospek {
    private int id;
    private String nama;

    @SerializedName("nama_perusahaan")
    private String namaPerusahaan;

    @SerializedName("sumber_prospek")
    private String sumberProspek;

    private String status;

    // Getter dan Setter
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNamaPerusahaan() { return namaPerusahaan; }
    public String getSumberProspek() { return sumberProspek; }
    public String getStatus() { return status; }
}