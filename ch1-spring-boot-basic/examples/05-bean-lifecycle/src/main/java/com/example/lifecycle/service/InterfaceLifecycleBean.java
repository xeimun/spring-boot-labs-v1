package com.example.lifecycle.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InterfaceLifecycleBean implements InitializingBean, DisposableBean {
    
    public InterfaceLifecycleBean() {
        System.out.println("InterfaceLifecycleBean 생성자 호출");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InterfaceLifecycleBean InitializingBean.afterPropertiesSet() 호출");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("InterfaceLifecycleBean DisposableBean.destroy() 호출");
    }
} 