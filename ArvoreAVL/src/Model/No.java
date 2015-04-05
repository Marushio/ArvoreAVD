package Model;

/**
 * @author nós
 */
public class No {
    
    private int valor;
    private No noDireito;
    private No noEsquerdo;
    private No noPai;

    public No(int valor, No noPai) {
        this.valor = valor;
        this.noPai = noPai;
    }
    
    
     
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public No getNoDireito() {
        return noDireito;
    }

    public void setNoDireito(No noDireito) {
        this.noDireito = noDireito;
    }

    public No getNoEsquerdo() {
        return noEsquerdo;
    }

    public void setNoEsquerdo(No noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
    }

    public No getNoPai() {
        return noPai;
    }

    public void setNoPai(No noPai) {
        this.noPai = noPai;
    }
    
}
