/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

/**
 *
 * @author iftm
 */
public class Cidade {
    private String nome;
    private int x;
    private int y; 

    public Cidade(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Cidade() {
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
