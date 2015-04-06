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

    //Função para inserir um novo nó
     public void inserirNo(int parValor){
        raiz = novoNo(null,raiz,parValor);
    //organizarArvore();
        
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
            parNo=balancear(parNo);
             
        }
        
        return parNo;
    }

    //Função que escolhe a rotação certa a se fazer
    private No balancear(No no){
        if(calcFator(no)==2){           
            if(calcFator(no.getNoEsquerdo()) > 0){
                no = rSD(no);          
            }else {
                no=rDD(no);
            }
        }else if(calcFator(no) == -2){
            if(calcFator(no.getNoDireito())<0){
                no=rSE(no);
            }else{
                no=rDE(no);
            }
                
        }
        
        return no;
    
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
    //Função de rotação simples a Direita, ultilizada para o equilibrio da arvore
    private No rSD(No p){ 
        No u =p.getNoEsquerdo();  
        
        p.setNoEsquerdo(u.getNoDireito());
        u.setNoDireito(p);
        p.setFatorB(Math.max(altura(p.getNoEsquerdo()),altura(p.getNoDireito()))+1);
        u.setFatorB(Math.max(altura(u.getNoEsquerdo()),p.getFatorB())+1);
        return u;
    } 
     //Função de rotação simples a Esquerda, ultilizada para o equilibrio da arvore
    private No rSE(No p){        
        No u =p.getNoDireito();
        p.setNoDireito(u.getNoEsquerdo());
        u.setNoEsquerdo(p);
        p.setFatorB(Math.max(altura(p.getNoEsquerdo()),altura(p.getNoDireito()))+1);
        u.setFatorB(Math.max(altura(u.getNoDireito()),p.getFatorB())+1);
        return u;
        
    }
    //Função de rotação Dupla a direita, ultilizada para o equilibrio da arvore
    private No rDD(No p){   
         p.setNoEsquerdo(rSE(p.getNoEsquerdo()));
         return rSD(p);
   
    }
    
     //Função de rotação Dupla a esquerda, ultilizada para o equilibrio da arvore
    private No rDE(No p){    
        p.setNoDireito(rSD(p.getNoDireito()));
        return rSE(p);
       
    }
    //Função que exibe a arvore de forma InOrder
    public void exibirInOrder(){
        System.out.print("\nno atual = "+raiz.getValor());
        nosInOrder(raiz);
    }
    //Função Recusiva usada na exibição In Order da arvore
    private void nosInOrder(No paramNo){
       
        if(paramNo != null){
            nosInOrder(paramNo.getNoEsquerdo());
            if(paramNo.getNoPai()== null){
                System.out.print("\nno atual = "+paramNo.getValor() + " Fator B = "+calcFator(paramNo)+" ");
            }else{
                System.out.print("\nno atual = "+paramNo.getValor()+" Fator B = "+calcFator(paramNo)+" Pai = "+paramNo.getNoPai().getValor()+ " ");
            }
            nosInOrder(paramNo.getNoDireito());
        }
    }
    
    
    public void exibirPreOrder(){
        nosPreOrdem(raiz);
    }
    
    private void nosPreOrdem(No paramNo){
       if(paramNo != null){
           System.out.print(paramNo.getValor() + " ");
           nosPreOrdem(paramNo.getNoEsquerdo());   
           nosPreOrdem(paramNo.getNoDireito());
       }
    }
    
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
