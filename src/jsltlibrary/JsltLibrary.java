/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsltlibrary;

import util.HashUtil;

/**
 *
 * @author bests
 */
public class JsltLibrary {

    /**
     * @param args the command line arguments
     */
    
//    Generate Hash password to be inserted in the db here
    public static void main(String[] args) {
        // TODO code application logic here
        String plainPassword = "Lan2828";
        String inputHashed = HashUtil.hashPassword(plainPassword);
        System.out.println("Hashed password: " + inputHashed);

    }
    
}
