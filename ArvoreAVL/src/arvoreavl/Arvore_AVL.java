
package arvoreavl;

import Model.Arvore;
import java.util.Scanner;

/**
 * @author DiógenesGalileu
 */
public class Arvore_AVL {

    
    public static void main(String[] args) {        
        
        Arvore arvore = new Arvore();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        
        do{
            //Pergunta o local do arquivo com os números a serem adicionados na árvore
            System.out.println("Digite o local do arquivo: ");
            String local = entrada.nextLine();
            try {
                arvore.entradaDados(local);
                continuar = true;
            } catch (Exception e) {
                System.out.println("Arquivo não encontrado.");
                continuar = false;
            }
        }while(continuar == false);
        
        //Exibe as travessias em InOrder, PosOrder, PréOrder
        System.out.println("InOrder");
        arvore.exibirInOrder();
        System.out.println("\nPosOrder");
        arvore.exibirPosOrder();
        System.out.println("\nPreOrder");
        arvore.exibirPreOrder();
        System.out.println("");
        boolean segue = false;
                
        System.out.println("Digite o valor a ser buscado.");            
        try{
            int key = entrada.nextInt();
            if(arvore.existeValor(key)==true){
                System.out.println("O valor está na árvore.");
            }
            else{
                System.out.println("O valor não está na árvore");
            }                
            segue=true;
        }            
        catch(Exception e){
            System.out.println("Digite um número inteiro.");
            segue = true;
        }
    }
}
