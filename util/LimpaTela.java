package util;
/**
 * Classe que limpa a tela do terminal
 * @author (Roberta de Siqueira) 
 * @version (1.0)
 */

public class LimpaTela {
    
    public static void limpa()
    {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (Exception e){}
    }
    
}