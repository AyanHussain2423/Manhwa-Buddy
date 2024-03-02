package com.example.manwhabudyy.Model;

public class model {
    String name,chapter,pdflink;

    public model() {

    }
    public model(String name, String chapter, String pdflink) {
        this.name = name;
        this.chapter = chapter;
        this.pdflink = pdflink;
    }

    public String getName() {
        return name;
    }

    public String getChapter() {
        return chapter;
    }

    public String getPdflink() {
        return pdflink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setPdflink(String pdflink) {
        this.pdflink = pdflink;
    }
}
