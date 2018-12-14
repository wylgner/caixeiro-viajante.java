/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author iftm
 */
public class Populacao {

    private ArrayList<Individuo> geracao = new ArrayList();
    
    private float fitness;

    public Populacao(ArrayList<Individuo> geracao) {
        this.geracao = geracao;
        calculaMediaFitness();
    }

    public Populacao() {
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public ArrayList<Individuo> getGeracao() {
        return geracao;
    }

    public void setGeracao(ArrayList<Individuo> geracao) {
        this.geracao = geracao;
    }

    public void calculaMediaFitness() {
        float soma = 0;
        for (int i = 0; i < geracao.size(); i++) {
            // System.out.println(geracao.get(i).getAptidao());
            soma += geracao.get(i).getAptidao();
        }
        fitness = soma / geracao.size();
    }

    public void ordenaIndividuos() {
        System.out.println("APT dos Individuos na ordem de ocorrencia: ");
        for (int i = 0; i < geracao.size(); i++) {
            System.out.println("Aptdão: " + geracao.get(i).getAptidao());
        }
        System.out.println("---------------------------------------------------");
        Collections.sort(geracao);
        System.out.println("Ordenados pela melhor aptdão: ");
        for (int i = 0; i < geracao.size(); i++) {
            System.out.println("Aptdão: " + geracao.get(i).getAptidao());
        }
        System.out.println("---------------------------------------------------");
        
    }
    
    

}
