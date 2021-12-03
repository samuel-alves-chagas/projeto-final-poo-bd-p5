package br.inatel;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        CorretoraDB corretoraDB = new CorretoraDB();

        Corretora alianca = new Corretora("Raissa", "MG");
        corretoraDB.adicionarCorretoraAoBanco(alianca);
        alianca.setId(corretoraDB.buscaIDDeCorretora(alianca.getNome()));

        CorretorDB corretorDB = new CorretorDB();
        Corretor samuel = new Corretor("Samuel", "14548698604", "MG");
        corretorDB.adicionarCorretorAoBanco(samuel);
        samuel.id = corretorDB.buscaIDDeCorretor(samuel.CPF);

        try {
            if (samuel.solicitaVinculo(alianca) == true) {
                corretorDB.associaCorretorACorretora(samuel.id, alianca.getId());
                samuel.vinculaCorretora(alianca);
                alianca.vinculaCorretor(samuel);
            } else {
                System.out.println(
                        "O corretor " + samuel.nome
                                + " não possui abrangência para a corretora solicitada ou não pode se associar à ela");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ClienteDB clienteDB = new ClienteDB();

        Cliente benedito = new Cliente("Benedito José", "77271238620", "MG");
        clienteDB.adicionarCliente(benedito);
        benedito.setId(clienteDB.buscaIDDeCliente(benedito.nome));

        PropostaDB propostaDB = new PropostaDB();
        Proposta novaProposta = new Proposta(samuel, benedito, 51, 239.74);
        propostaDB.adicionarPropostaAoBanco(novaProposta);
        novaProposta.setNrProposta(propostaDB.buscaNrProposta(novaProposta.getTitular().CPF));
    }
}
