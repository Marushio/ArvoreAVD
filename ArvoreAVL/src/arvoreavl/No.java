package arvoreavl;

/**
 * @author DiógenesGalileu
 */
public class No {
    
    public int valor;
    public No noDireito;
    public No noEsquerdo;
    
    public No(int par_Valor, No par_noDireito, No par_noEsquerdo){
        valor=par_Valor;
        noDireito=par_noDireito;
        noEsquerdo=par_noEsquerdo;
    }
}
