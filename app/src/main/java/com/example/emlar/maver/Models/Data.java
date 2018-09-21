package com.example.emlar.maver.Models;

public class Data <T> {
    private int total;
    private T results;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public T getResults() {
        return results;
    }


}
