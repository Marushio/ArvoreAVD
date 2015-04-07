package Model;


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
        u.setNoPai(p.getNoPai());
        p.setNoEsquerdo(u.getNoDireito());
        u.setNoDireito(p);
        p.setFatorB(Math.max(altura(p.getNoEsquerdo()),altura(p.getNoDireito()))+1);
        u.setFatorB(Math.max(altura(u.getNoEsquerdo()),p.getFatorB())+1);
        p.setNoPai(u);
        return u;
    } 
     //Função de rotação simples a Esquerda, ultilizada para o equilibrio da arvore
    private No rSE(No p){        
        No u =p.getNoDireito();
        u.setNoPai(p.getNoPai());
        p.setNoDireito(u.getNoEsquerdo());
        u.setNoEsquerdo(p);
        p.setFatorB(Math.max(altura(p.getNoEsquerdo()),altura(p.getNoDireito()))+1);
        u.setFatorB(Math.max(altura(u.getNoDireito()),p.getFatorB())+1);
        p.setNoPai(u);
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
        if(raiz==null){
            System.out.println("Arvore Vazia !");
        }else
        nosInOrder(raiz);
    }
    //Função Recusiva usada na exibição In Order da arvore
    private void nosInOrder(No paramNo){
       
        if(paramNo != null){
            nosInOrder(paramNo.getNoEsquerdo());
            System.out.print(paramNo.getValor() + " ");
            nosInOrder(paramNo.getNoDireito());
        }
    }
    
    //Função que exibe a arvore de forma PreOrder
    public void exibirPreOrder(){
        if(raiz==null){
            System.out.println("Arvore Vazia !");
        }else
            nosPreOrdem(raiz);
    }
    //Função Recusiva usada na exibição Pre Order da arvore
    private void nosPreOrdem(No paramNo){
       if(paramNo != null){
           System.out.print(paramNo.getValor() + " ");
           nosPreOrdem(paramNo.getNoEsquerdo());   
           nosPreOrdem(paramNo.getNoDireito());
       }
    }
    //Função que exibe a arvore de forma PosOrder
    public void exibirPosOrder(){
        if(raiz==null){
            System.out.println("Arvore Vazia !");
        }else
            nosPosOrdem(raiz);
    }
    //Função Recusiva usada na exibição Pos Order da arvore
    private void nosPosOrdem(No paramNo){
       if(paramNo != null){            
         nosPosOrdem(paramNo.getNoEsquerdo());            
          nosPosOrdem(paramNo.getNoDireito());
          System.out.print(paramNo.getValor() + " ");
       } 
    } 
    
    //Funções de procura na arvore
    public boolean existeValor(int paramValor){
        return(reExisteValor(raiz, paramValor));
    }
    //Função Recursiva para encontrar o numero procurado na arvore
    
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
   
}
