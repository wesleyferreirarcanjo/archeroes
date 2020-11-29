package com.archeroes.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    WebSecurityConfiguration(final DataSource dataSource) {

    }
}
