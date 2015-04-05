package Model;

import Model.No;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Nós
 */
public class Arvore {
    private No raiz;
    
    public Arvore(){  
        raiz=null;     
    }
   //Função que calcula o fator de balanceamento de um nó
    private int calcFator(No no){
        
        return altura(no.getNoEsquerdo())-altura(no.getNoDireito());
        
    }
    //Função que calcula o fator a altura de um nó
    private int altura(No no){
	if (no == null ){
	    return -1;
	}
	else { 
	    return 1+Math.max(altura(no.getNoEsquerdo()),altura(no.getNoDireito()));
			
	}
    }
    // Função para inserir um novo nó
     public void inserirNo(int parValor){
        raiz = novoNo(null,raiz,parValor);
    }
     //Função recursiva, usada na inserção de um nó
    private No novoNo(No paiNo,No parNo, int parValor){
        if(parNo == null){
            return new No(parValor,paiNo);
        }
        else{
            if(parValor > parNo.getValor()){
                parNo.setNoDireito(novoNo(parNo,parNo.getNoDireito(), parValor));
            }
            else{
                parNo.setNoEsquerdo(novoNo(parNo,parNo.getNoEsquerdo(), parValor));
            }
        }        
        return parNo;
    }
    //Função de rotação simples a Direita, ultilizada para o equilibrio da arvore
    private void rSD(No p){        
        No u =p.getNoEsquerdo();
        
        u.setNoPai(p.getNoPai());
        p.setNoPai(u);
        p.setNoEsquerdo(u.getNoDireito());
        u.setNoDireito(p);
    
    } 
    
    
    
    //Função que exibe a arvore de forma InOrder
    public void exibirInOrder(){
        nosInOrder(raiz);
    }
    //Função Recusiva usada na exibição In Order da arvore
    private void nosInOrder(No paramNo){
       
        if(paramNo != null){
            nosInOrder(paramNo.getNoEsquerdo());
            if(paramNo.getNoPai()== null){
                System.out.print("\nno atual = "+paramNo.getValor() + " Fator bala = "+calcFator(paramNo)+" ");
            }else{
                System.out.print("\nPai = "+paramNo.getNoPai().getValor()+" no atual = "+paramNo.getValor() +" Fator bala = "+calcFator(paramNo)+ " ");
            }
            nosInOrder(paramNo.getNoDireito());
        }
    }
    
   // public void exibirPreOrder(){
   //     nosPreOrdem(raiz);
   // }
    
   // private void nosPreOrdem(No paramNo){
   /// //    if(paramNo != null){
    //        System.out.print(paramNo.getValor() + " ");
        //    nosPreOrdem(paramNo.getNoEsquerdo());   
        //    nosPreOrdem(paramNo.getNoDireito());
      //  }
   // }
    
   // public void exibirPosOrder(){
     //   nosPosOrdem(raiz);
  //  }
    
   // private void nosPosOrdem(No paramNo){
    //    if(paramNo != null){            
      //      nosPosOrdem(paramNo.getNoEsquerdo());            
     //       nosPosOrdem(paramNo.getNoDireito());
     //       System.out.print(paramNo.getValor() + " ");
     ///   }
   // }
//  / / 
    public boolean existeValor(int paramValor){
        return reExisteValor(raiz, paramValor);
    }
    
    private boolean reExisteValor(No paramNo, int paramValor){
        if (paramNo == null){
            return false;
        }
        if (paramNo.getValor() == paramValor){
            return true;
        }
        if(paramValor < paramNo.getValor()){
            return reExisteValor(paramNo.getNoEsquerdo(), paramValor);
        }
        else{
            return reExisteValor(paramNo.getNoDireito(), paramValor);
        }
    }
    
    public void entradaDados(){
        Scanner entrada = null;
        try {
            entrada = new Scanner(new FileReader("numeros.txt")).useDelimiter(",");
        }
        catch (FileNotFoundException ex) {
        }
        while(entrada.hasNext()){
            inserirNo(entrada.nextInt());
        }
               
    }
}
