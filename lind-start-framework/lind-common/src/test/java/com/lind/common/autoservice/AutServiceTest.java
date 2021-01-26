package com.lind.common;

import com.google.auto.service.AutoService;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class AutServiceTest {
    @Test
    public void printService() {
        DisplayFactory.getSingleton().getDisplay();
    }

    public interface Display {
        String display();
    }

    public static class DisplayFactory {
        private static DisplayFactory mDisplayFactory;

        private Iterator<Display> mIterator;

        private DisplayFactory() {
            ServiceLoader<Display> loader = ServiceLoader.load(Display.class);
            mIterator = loader.iterator();
        }

        public static DisplayFactory getSingleton() {
            if (null == mDisplayFactory) {
                synchronized (DisplayFactory.class) {
                    if (null == mDisplayFactory) {
                        mDisplayFactory = new DisplayFactory();
                    }
                }
            }
            return mDisplayFactory;
        }

        public Display getDisplay() {
            return mIterator.next();
        }

        public boolean hasNextDisplay() {
            return mIterator.hasNext();
        }
    }

    @AutoService(Display.class)
    public class ADisplay implements Display {
        @Override
        public String display() {
            return "A Display";
        }
    }

    // moduleb
    @AutoService(Display.class)
    public class BDisplay implements Display {
        @Override
        public String display() {
            return "B Display";
        }
    }
}
