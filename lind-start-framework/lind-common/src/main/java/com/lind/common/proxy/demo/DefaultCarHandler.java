package com.lind.common.proxy.demo;

 public class DefaultCarHandler implements  CarHandler {

     @Override
     public void OnAfter() {
         System.out.println("after");
     }
 }
