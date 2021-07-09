package com.example.soulmate;

public class PhrasesItem {
    String phrase;
    String ex1;
    String ex2;
    String ex3;
    String ex4;
    String ex5;

    public PhrasesItem(String phrase, String ex1, String ex2, String ex3, String ex4, String ex5) {
        this.phrase = phrase;
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
        this.ex4 = ex4;
        this.ex5 = ex5;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getEx1() {
        return ex1;
    }

    public String getEx2() {
        return ex2;
    }

    public String getEx3() {
        return ex3;
    }

    public String getEx4() {
        return ex4;
    }

    public String getEx5() {
        return ex5;
    }
}
