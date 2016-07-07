package com.mingjun.news.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mingjun on 16/7/7.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}

