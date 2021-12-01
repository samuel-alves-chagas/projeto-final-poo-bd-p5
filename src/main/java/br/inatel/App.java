package br.inatel;

public class App {
    public static void main(String[] args) {
        Corretora alianca = new Corretora("Alianca BH", "MG");
        System.out.println("Mostrando info da corretora: ");
        alianca.mostraInfo();

        Corretor samuel = new Corretor("Samuel", "14548698604", "MG");
        try {
            throw samuel.solicitaVinculo(alianca);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        samuel.mostraInfo();
        System.out.println(alianca.getNomeCorretoresVinculados());
    }
}
