/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.result.LoginResult;

/**
 *
 * @author bests
 */
public interface LoginResultHandler {
    void handle(LoginResult result);
}
