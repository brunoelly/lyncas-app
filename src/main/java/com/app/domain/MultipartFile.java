package com.app.domain;

public class MultipartFile {

    private MultipartFile file;

    public MultipartFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
