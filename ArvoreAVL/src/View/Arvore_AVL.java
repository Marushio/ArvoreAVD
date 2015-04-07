
package View;

import Model.Arvore;
import java.util.Scanner;

/**
 * @author nós
 */
public class Arvore_AVL {

    
    public static void main(String[] args) {        
        int opcao = 0;
        int key;
        Arvore arvore = new Arvore();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        while(continuar == true){
            try{
                System.out.println("\n----------------Menu------------------");
                System.out.println("-Inserir um novo valor ------------- 1");
                System.out.println("-Exibir a tarvessia In Order  ------ 2");
                System.out.println("-Exibir a tarvessia Pos Order ------ 3");
                System.out.println("-Exibir a tarvessia Pré Order ------ 4");
                System.out.println("-Procurar um valor na arvore ------- 5");
                System.out.println("-Sair ------------------------------ 6");
                System.out.println("Digite uma opçao: ");
                opcao=Integer.parseInt(entrada.next());
                
                switch(opcao){
                    case 1:
                        System.out.println("Digite o valor para inserir na Arvore:"); 
                        key = Integer.parseInt(entrada.next());
                        arvore.inserirNo(key);
                        break;
                    case 2:
                        System.out.println("Arvore InOrder:");
                        arvore.exibirInOrder();
                        break;
                    case 3:
                        System.out.println("Arvore PosOrder:");
                        arvore.exibirPosOrder();
                        break;
                    case 4:
                        System.out.println("Arvore PreOrder:");
                        arvore.exibirPreOrder();
                        break;
                    case 5:  
                        System.out.println("Digite o valor a ser buscado:"); 
                        key = Integer.parseInt(entrada.next());
                        arvore.existeValor(key);
                        break;
                    case 6: 
                        continuar =false;
                        break;
                }
            }catch(NumberFormatException e){
                System.out.println("Digite um número inteiro.");        
            }    
            
        }
   
    }
}
