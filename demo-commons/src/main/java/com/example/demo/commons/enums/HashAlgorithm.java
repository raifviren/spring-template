package com.example.demo.commons.enums;

public enum HashAlgorithm {
    MD2("MD2"),
    MD5("MD5"),
    SHA224("SHA-224"),
    SHA256("SHA-256"),
    SHA512("SHA-512");

    final String value;
    HashAlgorithm(String value) {
        this.value = value;
    }
}
