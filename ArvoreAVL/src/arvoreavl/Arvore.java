package arvoreavl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author DiógenesGalileu
 */
public class Arvore {
    public No raiz;
    
    public Arvore(){
        
        raiz=null;
        
    }
    
    public void inserirNo(int parValor){
        raiz = novoNo(raiz,parValor);
    }
    
    private No novoNo(No parNo, int parValor){
        if(parNo == null){
            return new No(parValor, null, null);
        }
        else{
            if(parValor > parNo.valor){
                parNo.noDireito = novoNo(parNo.noDireito, parValor);
            }
            else{
                parNo.noEsquerdo = novoNo(parNo.noEsquerdo, parValor);
            }
        }        
        return parNo;
    }
       
    public void exibirInOrder(){
        nosInOrder(raiz);
    }
    
    private void nosInOrder(No paramNo){
        if(paramNo != null){
            nosInOrder(paramNo.noEsquerdo);
            System.out.print(paramNo.valor + " ");
            nosInOrder(paramNo.noDireito);
        }
    }
    
    public void exibirPreOrder(){
        nosPreOrdem(raiz);
    }
    
    private void nosPreOrdem(No paramNo){
        if(paramNo != null){
            System.out.print(paramNo.valor + " ");
            nosPreOrdem(paramNo.noEsquerdo);   
            nosPreOrdem(paramNo.noDireito);
        }
    }
    
    public void exibirPosOrder(){
        nosPosOrdem(raiz);
    }
    
    private void nosPosOrdem(No paramNo){
        if(paramNo != null){            
            nosPosOrdem(paramNo.noEsquerdo);            
            nosPosOrdem(paramNo.noDireito);
            System.out.print(paramNo.valor + " ");
        }
    }
    
    public boolean existeValor(int paramValor){
        return reExisteValor(raiz, paramValor);
    }
    
    private boolean reExisteValor(No paramNo, int paramValor){
        if (paramNo == null){
            return false;
        }
        if (paramNo.valor == paramValor){
            return true;
        }
        if(paramValor < paramNo.valor){
            return reExisteValor(paramNo.noEsquerdo, paramValor);
        }
        else{
            return reExisteValor(paramNo.noDireito, paramValor);
        }
    }
    
    public void entradaDados(String endereço){
        Scanner entrada = null;
        try {
            entrada = new Scanner(new FileReader(endereço)).useDelimiter(",");
        }
        catch (FileNotFoundException ex) {
        }
        while(entrada.hasNext()){
            inserirNo(entrada.nextInt());
        }
               
    }
}
