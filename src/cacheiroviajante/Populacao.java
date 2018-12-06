/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;

/**
 *
 * @author iftm
 */
public class Populacao {

    private ArrayList<Individuo> geracao = new ArrayList();

    public Populacao(ArrayList<Individuo> geracao) {
        this.geracao = geracao;
    }

    public Populacao() {
    }

  
    
    public ArrayList<Individuo> getGeracao() {
        return geracao;
    }

    public void setGeracao(ArrayList<Individuo> geracao) {
        this.geracao = geracao;
    }
    
    
}
