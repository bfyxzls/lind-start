package com.lind.interfaces;

public interface HelloProviderFactory<I extends HelloProvider> {
    I create();

    String getId();

    String getName();
}
