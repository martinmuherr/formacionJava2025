package com.edisa.formacion.mayo2025.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.zxing.*;

public class Codes {
    String text;

    BarcodeFormat code_format;

    public Codes(Result result){
        setText(result.getText());
        setCode_format(result.getBarcodeFormat());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BarcodeFormat getCode_format() {
        return code_format;
    }

    public void setCode_format(BarcodeFormat code_format) {
        this.code_format = code_format;
    }
}
