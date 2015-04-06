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

    // Função para inserir um novo nó
     public void inserirNo(int parValor){
        raiz = novoNo(null,raiz,parValor);
        organizarArvore();
        
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
    //metodo que percorre a arvore toda para verificar o fator de Balenceamento de cada nó
    public void organizarArvore(){
         posOrdem(raiz);
    }
    //metodo recursivo para percorrer a arvore em pos order para Calcular o balanceamento dos nós
    private void posOrdem(No paramNo){
        if(paramNo != null){            
           posOrdem(paramNo.getNoEsquerdo());            
           posOrdem(paramNo.getNoDireito());
           if(paramNo.getNoPai()==null){
               raiz=paramNo;
           }
           escolhaRot(paramNo);
        }
     }
    
    //Função que escolhe a rotação certa a se fazer
    private void escolhaRot(No no){
        if (calcFator(no) >= 2){
            if(calcFator(no.getNoEsquerdo()) >= 0){
                rSD(no);
                System.out.print("Aqui 1 ");
            }else{
                rDD(no);
                System.out.print("Aqui 2 ");
            }
            
        }
        if(calcFator(no) <= -2){
            if(calcFator(no.getNoDireito()) < 0){
                rSE(no);
                System.out.print("Aqui 3 ");
            }else{
                rDE(no);
                System.out.print("Aqui 4 ");
            }
                
           
        }
    
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
    private void rSD(No p){ 
        No u =p.getNoEsquerdo();
        System.out.print("Aqui rsd ");
        if(p.getNoPai()==null){
            raiz=u;
            System.out.print("Aqui  raiz rsd ");
        } 
        
        u.setNoPai(p.getNoPai());
        p.setNoPai(u);
        p.setNoEsquerdo(u.getNoDireito());
        u.setNoDireito(p);
        
    } 
    
     //Função de rotação simples a Esquerda, ultilizada para o equilibrio da arvore
    private void rSE(No p){        
        No u =p.getNoEsquerdo();
        if(p.getNoPai()==null){
            raiz=u;
            System.out.print("Aqui  raiz rse ");
        } 
        
        
        u.setNoPai(p.getNoPai());
        p.setNoPai(u);
        p.setNoDireito(u.getNoEsquerdo());
        u.setNoEsquerdo(p);
    
    }
     //Função de rotação Dupla a direita, ultilizada para o equilibrio da arvore
    private void rDD(No p){        
        No u =p.getNoEsquerdo();
        No v =u.getNoDireito();
        if(p.getNoPai()==null){
            raiz=v;
            System.out.print("Aqui  raiz rdd ");
        } 
        
        v.setNoPai(p.getNoPai());
        p.setNoPai(v);
        v.setNoDireito(p);
        v.setNoEsquerdo(u);
        u.setNoDireito(v.getNoEsquerdo());
        p.setNoEsquerdo(v.getNoDireito());
        u.setNoPai(v);
    }
    
     //Função de rotação Dupla a esquerda, ultilizada para o equilibrio da arvore
    private void rDE(No p){        
        No u =p.getNoEsquerdo();
        No v =u.getNoDireito();
        if(p.getNoPai()==null){
            raiz=v;
            System.out.print("Aqui  raiz rde ");
        } 
        
        v.setNoPai(p.getNoPai());
        p.setNoPai(v);
        v.setNoDireito(u);
        v.setNoEsquerdo(p);
        p.setNoDireito(v.getNoEsquerdo());
        u.setNoEsquerdo(v.getNoDireito());
        u.setNoPai(v);
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
                System.out.print("\nno atual = "+paramNo.getValor() + " Fator B = "+calcFator(paramNo)+" ");
            }else{
                System.out.print("\nno atual = "+paramNo.getValor()+" Fator B = "+calcFator(paramNo)+" Pai = "+paramNo.getNoPai().getValor()+ " ");
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
