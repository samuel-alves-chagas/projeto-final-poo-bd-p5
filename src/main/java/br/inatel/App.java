package br.inatel;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CorretoraDB corretoraDB = new CorretoraDB();
        CorretorDB corretorDB = new CorretorDB();
        ClienteDB clienteDB = new ClienteDB();
        PropostaDB propostaDB = new PropostaDB();

        Corretora alianca = new Corretora("Aliança BH", "MG");
        corretoraDB.adicionarCorretoraAoBanco(alianca);
        alianca.setId(corretoraDB.buscaIDDeCorretora(alianca.getNome()));

        Corretor samuel = new Corretor("Benedito", "77271238620", "MG");
        corretorDB.adicionarCorretorAoBanco(samuel);
        samuel.id = corretorDB.buscaIDDeCorretor(samuel.CPF);

        try {
            if (samuel.solicitaVinculo(alianca) == true) {
                corretorDB.associaCorretorACorretora(samuel.id, alianca.getId());
            } else {
                System.out.println(
                        "O corretor " + samuel.nome
                                + " não possui abrangência para a corretora solicitada ou não pode se associar à ela");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean running = true;
        int idCorretor;
        String cpfCliente;
        int nrProposta;

        System.out.println("");
        System.out.println("Seja bem-vindo ao sistema de venda de planos de saúde, Informe o que você deseja fazer");

        while (running) {
            System.out.println("");
            System.out.println("------------------------");
            System.out.println("Opção 1 - Criar uma proposta");
            System.out.println("Opção 2 - Buscar por uma proposta já criada");
            System.out.println("Opção 3 - Atualizar uma proposta já criada");
            System.out.println("Opção 4 - Deletar uma proposta");
            System.out.println("Opção 9 - Sair do programa");
            System.out.println("------------------------");
            System.out.println("");

            System.out.print("Digite aqui sua opção: ");
            int opcao = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch (opcao) {
                case 1:
                    System.out
                            .println("Você optou por criar uma proposta, para isso precisaremos dos seguintes dados: ");

                    Proposta proposta = new Proposta();
                    proposta.setCorretor(samuel);

                    Cliente titular = new Cliente();

                    System.out.print("Seu nome: ");
                    titular.nome = sc.nextLine();

                    System.out.print("Seu CPF: ");
                    titular.CPF = sc.nextLine();

                    System.out.print("Sua idade: ");
                    proposta.setIdade(Integer.parseInt(sc.nextLine()));

                    System.out.print("A sigla do estado onde você mora: ");
                    titular.UF = sc.nextLine().toUpperCase();

                    clienteDB.adicionarCliente(titular);
                    titular.id = clienteDB.buscaIDDeCliente(titular.nome);

                    proposta.setTitular(titular);
                    proposta.calculaValor();

                    propostaDB.adicionarPropostaAoBanco(proposta);
                    proposta.setNrProposta(propostaDB.buscaNrProposta(proposta.getTitular().CPF));

                    System.out.println("");
                    System.out.println("Sua proposta foi criada, o número dela é: " + proposta.getNrProposta());
                    System.out.println("");
                    break;
                case 2:

                    System.out.print("Para buscar por uma proposta já criada informe o número dela: ");
                    nrProposta = Integer.parseInt(sc.nextLine());

                    Proposta propostaEncontrada = propostaDB.buscaDePropostaPeloNrProposta(nrProposta);

                    idCorretor = corretorDB.buscaDeIdCorretorPeloNrProposta(propostaEncontrada.getNrProposta());
                    propostaEncontrada.setCorretor(corretorDB.buscaCorretorPorID(idCorretor));

                    cpfCliente = clienteDB.buscaDeCPFDoClientePeloNrProposta(propostaEncontrada.getNrProposta());
                    propostaEncontrada.setTitular(clienteDB.buscaDeClientePorCPF(cpfCliente));

                    System.out.println("");
                    System.out.println("As informações da proposta são: ");
                    propostaEncontrada.mostraInfo();
                    propostaEncontrada.getTitular().mostraInfo();
                    System.out.println("");
                    propostaEncontrada.getCorretor().mostraInfo();
                    break;
                case 3:
                    System.out.print("Para atualizar uma proposta já criada informe o número dela: ");
                    nrProposta = Integer.parseInt(sc.nextLine());

                    Proposta propostaEmAtualizacao = propostaDB
                            .buscaDePropostaPeloNrProposta(nrProposta);

                    idCorretor = corretorDB.buscaDeIdCorretorPeloNrProposta(propostaEmAtualizacao.getNrProposta());
                    propostaEmAtualizacao.setCorretor(corretorDB.buscaCorretorPorID(idCorretor));

                    cpfCliente = clienteDB
                            .buscaDeCPFDoClientePeloNrProposta(propostaEmAtualizacao.getNrProposta());
                    propostaEmAtualizacao.setTitular(clienteDB.buscaDeClientePorCPF(cpfCliente));

                    System.out.println("");
                    System.out.println(
                            "No momento só é possível alterar a idade do titular, qual o novo valor desejado? ");

                    int novaIdade = Integer.parseInt(sc.nextLine());

                    propostaEmAtualizacao.setIdade(novaIdade);
                    propostaEmAtualizacao.calculaValor();
                    propostaDB.atualizarPropostaNoBanco(propostaEmAtualizacao);

                    System.out.println("");
                    System.out.println(
                            "Proposta atualizada, confira a nova situação buscando pela proposta de número "
                                    + propostaEmAtualizacao.getNrProposta());

                    break;
                case 4:
                    System.out.print("Para deletar uma proposta já criada informe o número dela: ");
                    nrProposta = Integer.parseInt(sc.nextLine());
                    propostaDB.deletarProposta(nrProposta);
                    System.out.println("");
                    System.out.println("Proposta deletada!");
                    break;
                default:
                    running = false;
                    System.out.println("");
                    System.out.println("------------------------");
                    System.out.println("Programa encerrado!");
                    System.out.println("------------------------");
                    System.out.println("");
                    break;

            }
        }

        sc.close();

        // Proposta proposta2 = propostaDB.buscaDePropostaPeloNrProposta(1);

        // Atualizando proposta
        // proposta1.setIdade(52);
        // proposta1.setValor(500);
        // propostaDB.atualizarPropostaNoBanco(proposta1);

        // propostaDB.deletarProposta(proposta1);

        // int idCorretor =
        // corretorDB.buscaDeIdCorretorPeloNrProposta(proposta2.getNrProposta());
        // proposta2.setCorretor(corretorDB.buscaCorretorPorID(idCorretor));

        // String cpfCliente =
        // clienteDB.buscaDeCPFDoClientePeloNrProposta(proposta2.getNrProposta());
        // proposta2.setTitular(clienteDB.buscaDeClientePorCPF(cpfCliente));

        // proposta2.mostraInfo();
        // proposta2.getCorretor().mostraInfo();
        // proposta2.getTitular().mostraInfo();

    }
}
