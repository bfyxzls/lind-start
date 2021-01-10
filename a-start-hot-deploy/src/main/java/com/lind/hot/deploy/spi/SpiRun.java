package com.lind.hot.deploy.spi;

import com.lind.interfaces.HelloProvider;
import sun.misc.Service;

import java.util.Iterator;

public class SpiRun {
    public static void run() {
        Iterator<HelloProvider> providers = Service.providers(HelloProvider.class);
        while (providers.hasNext()) {
            HelloProvider next = providers.next();
            System.out.println(next.login());
        }
        System.out.println("=====================");
//        ServiceLoader<HelloProvider> load = ServiceLoader.load(HelloProvider.class);
//        Iterator<HelloProvider> loadIterator = load.iterator();
//        while (loadIterator.hasNext()) {
//            HelloProvider next = loadIterator.next();
//            System.out.println(next.getName());
//        }
    }


}
