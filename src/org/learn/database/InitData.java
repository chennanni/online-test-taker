package org.learn.database;

/**
 * invoke once to initialize tables in the database
 * 
 * @author North
 *
 */
public class InitData {
    public static void main(String[] args) {
    	 DB db = DB.getInstance();
    	 db.init();
    }
}
