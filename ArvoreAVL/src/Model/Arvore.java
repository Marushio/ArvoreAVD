package Model;

import Model.No;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author DiógenesGalileu
 */
public class Arvore {
    private No raiz;
    
    public Arvore(){
        
        raiz=null;
        
    }
    
   
    
   // public int altura(No no){
//	if (no == null ){
//	    return -1;
//	}
//	else { 
//	    return 1+Math.max(altura(no.esquerdo),altura(no.direito));
//			
//	}
  //  }
     public void inserirNo(int parValor){
        raiz = novoNo(null,raiz,parValor);
    }
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
       
    public void exibirInOrder(){
        nosInOrder(raiz);
    }
    
    private void nosInOrder(No paramNo){
        if(paramNo.getNoPai()==null){
            System.out.print("no atual = "+paramNo.getValor() + " ");
        }
        if(paramNo != null){
            nosInOrder(paramNo.getNoEsquerdo());
            System.out.print("/nPai = "+paramNo.getNoPai().getValor()+"no atual = "+paramNo.getValor() + " ");
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
