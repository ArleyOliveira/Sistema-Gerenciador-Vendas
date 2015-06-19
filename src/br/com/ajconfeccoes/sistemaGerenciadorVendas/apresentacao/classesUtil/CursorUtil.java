/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil;

import java.awt.Cursor;

/**
 *
 * @author arley
 */
public class CursorUtil {
    
    public static Cursor getCursor(int type){
        Cursor cursor = new Cursor(type);
        return cursor;
    }
}
