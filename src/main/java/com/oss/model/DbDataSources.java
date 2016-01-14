/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oss.model;

import java.util.HashMap;
import javax.sql.DataSource;

/**
 *
 * @author g46737
 */
public class DbDataSources {
    
    HashMap<String, DataSource > dataSources = new HashMap<String, DataSource>();

    public DbDataSources() {
    }
    
}
