import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static List<Ouvinte> listaOuvintes;
    static Scanner in =  new Scanner(System.in);

    public static void main(String[] args) {
        listaOuvintes =  new ArrayList<>();
        GerarDadosAplicacao();

        int op = -1;
        while (op != 0) {
            System.out.println("\n#################");
            System.out.println("Jogo do Saco");
            System.out.println("#################\n");
            System.out.println("1 - Imprimir lista de ouvintes");
            System.out.println("2 - Criar ouvinte");
            System.out.println("3 - Editar ouvinte");
            System.out.println("4 - Eliminar ouvinte");
            System.out.println("5 - Ver dados de um ouvinte");
            System.out.println("6 - Ver ranking de ouvintes");
            System.out.println("7 - Simular jogo");
            System.out.println("8 - Extra");
            System.out.println("0 - Sair");
            op = in.nextInt();

            switch (op) {
                case 0 -> System.out.println("A sair...");
                case 1 -> ImprimirListaOuvintesCondensada();
                case 2 -> CriarOuvinte();
                case 3 -> EditarOuvinte();
                case 4 -> EliminarOuvinte();
                case 5 -> VerDadosOuvinte();
                case 6 -> VerRankingOuvintes();
                case 7 -> SimularJogo();
                case 8 -> Extra();
            }
        }
    }

    private static void ImprimirListaOuvintes() {
        System.out.println("LISTA DE OUVINTES");
        System.out.println("----------------------------------------------------------------------------------------");
        if(listaOuvintes.size() == 0) {
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }
        for (Ouvinte ouvinte : listaOuvintes) {
            System.out.println(ouvinte);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private static void ImprimirListaOuvintesCondensada() {
        System.out.println("LISTA DE OUVINTES");
        System.out.println("----------------------------------------------------------------------------------------");
        if(listaOuvintes.size() == 0) {
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }
        for (Ouvinte ouvinte : listaOuvintes) {
            System.out.println(ouvinte.showOuvinte());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private static void CriarOuvinte() {
        int ID = 0;
        if(listaOuvintes.size() == 0) {
            ID = 1;
        } else {
            ID = listaOuvintes.get(listaOuvintes.size()-1).getId() + 1;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o nome do Ouvinte?");
        String nome = in.nextLine();

        System.out.println("Qual o contacto do Ouvinte?");
        String contacto = in.nextLine();

        listaOuvintes.add(new Ouvinte(ID, nome, contacto));
        System.out.println("O Ouvinte " + nome + " foi adicionado com sucesso.");

    }

    private static void EditarOuvinte() {

        if(listaOuvintes.size() == 0) {
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }

        ImprimirListaOuvintes();
        System.out.println("Insira o ID do ouvinte que quer editar: ");
        int id =  in.nextInt();

        for (Ouvinte ouvinte : listaOuvintes) {
            if (ouvinte.getId() == id) {
                Scanner in = new Scanner(System.in);
                System.out.println(ouvinte);

                System.out.println("Insira um novo nome: ");
                String nome = in.nextLine();

                System.out.println("Insira um novo contacto: ");
                String contacto = in.nextLine();

                ouvinte.setNome(nome);
                ouvinte.setContacto(contacto);
                System.out.println("As informações do ouvinte foram editadas com sucesso.");
                return;
            }
        }
        System.out.println("Não existe nenhum ouvinte com ID " + id + ".");
    }

    private static void EliminarOuvinte() {

        if(listaOuvintes.size() == 0) {
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }

        ImprimirListaOuvintesCondensada();
        System.out.println("Insira o ID do ouvinte que quer eliminar: ");
        int id = in.nextInt();

        for (int i = 0; i < listaOuvintes.size(); i++) {
            if(listaOuvintes.get(i).getId() ==  id) {
                System.out.println("Tem a certeza  que pretende eliminar o ouvinte com ID " + id + "?");
                System.out.println(listaOuvintes.get(i));
                System.out.println("Prima S para confirmar. Qualquer outra letra/texto irá cancelar a operação.");

                Scanner in =  new Scanner(System.in);
                String confirmacao = in.nextLine();

                if(confirmacao.equalsIgnoreCase("S")) {
                    listaOuvintes.remove(i);
                    System.out.println("O ouvinte com ID " + id + " foi removido com sucesso");
                }  else {
                    System.out.println("Operação cancelada.");
                }
                return;
            }
        }
        System.out.println("Não existe nenhum ouvinte com ID " + id + ".");
    }

    private static void VerDadosOuvinte() {
        if(listaOuvintes.size() == 0) {
            System.out.println("A lista de ouvintes  está vazia.");
            return;
        }
        ImprimirListaOuvintesCondensada();
        System.out.println("Insira o ID do ouvinte que quer consultar: ");
        int id = in.nextInt();
        for (Ouvinte ouvinte : listaOuvintes) {
            if(ouvinte.getId() ==  id) {
                System.out.println(ouvinte);
                return;
            }
        }
        System.out.println("Não existe nenhum ouvinte com ID " + id + ".");
    }

    private static void  VerRankingOuvintes() {
        List<Ouvinte> listaTemporaria = new ArrayList<>(listaOuvintes);

        //Ordena por número de vitórias descendente. Caso o número de vitórias seja igual, ordena por
        //número de participações ascendente.
        Collections.sort(listaTemporaria, new Comparator<Ouvinte>() {
            @Override
            public int compare(Ouvinte o1, Ouvinte o2) {
                int c = o2.getVitorias() - o1.getVitorias();
                if(c == 0) {
                    c = o1.getParticipacoes() - o2.getParticipacoes();
                }
                return c;
            }
        });

        System.out.println("RANKING DE OUVINTES");
        System.out.println("----------------------------------------------------------------------------------------");
        for (int i = 0; i < listaTemporaria.size(); i++) {
            System.out.println("Pos: " + (i+1) + "\t|\t" + listaTemporaria.get(i).getNome() + "\t|\tParticipações: " + listaTemporaria.get(i).getParticipacoes()
            + "\t|\tVitórias: " +  listaTemporaria.get(i).getVitorias());
        }
    }

    private static void SimularJogo() {
        if(listaOuvintes.size() == 0) {
            System.out.println("Não há  jogadores disponíveis.");
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }

        Random rnd = new Random();
        List<Ouvinte> listaJogadores = new ArrayList<>();

        // Número aleatório de jogadores
        int numeroJogadores = rnd.nextInt(1,listaOuvintes.size());

        // Criar lista de jogadores e atualizar número de vezes que cada jogador participou num jogo
        for (int i = 0; i < numeroJogadores; i++) {
            // Pos 0 da lista de ouvintes - Anabela de Malhadas - está "reservada"
            int index = rnd.nextInt(1, listaOuvintes.size());
            if(!listaJogadores.contains(listaOuvintes.get(index))) {
                listaJogadores.add(listaOuvintes.get(index));
                listaOuvintes.get(index).setParticipacoes(listaOuvintes.get(index).getParticipacoes()+1);
            } else {
                i--;
            }
        }

        // Formatar valores dos pesos - 3 casas decimais
        DecimalFormat df = new DecimalFormat("0.000");

        double pesoMin = rnd.nextInt(10,101) / 10.0;
        double pesoMax = pesoMin + 0.150;

        System.out.println("\nBom dia caros ouvintes!");
        System.out.println("A margem de ajuda de hoje é de " + df.format(pesoMin) + "kg a " + df.format(pesoMax) + "kg");
        System.out.println("Boa sorte!\n");

        // Lista de palpites dos jogadores
        List<Double> listaPalpites = new ArrayList<>();

        // Peso vencedor
        double peso = pesoMin + rnd.nextInt(0,151) / 1000.0;

        double diferenca = Double.MAX_VALUE;
        int indexVencedor = -1;

        System.out.println("LISTA DE PARTICIPANTES");
        System.out.println("----------------------------------------------------------------------------------------");
        for (int i = 0; i < listaJogadores.size(); i++) {
            double palpite = pesoMin + rnd.nextInt(0,151) / 1000.0;
            if(!listaPalpites.contains(palpite)) {
                listaPalpites.add(palpite);
                System.out.println("Jogador " + (i+1) + "\t|\t" + listaJogadores.get(i).getNome() + "\t|\tPalpite: " + df.format(palpite) + "kg.");
                if(Math.abs(palpite - peso) < diferenca) {
                    diferenca =  Math.abs(palpite - peso);
                    indexVencedor = i;
                }
            } else {
                i--;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------");

        //Atualizar número de vitória do jogador vencedor
        for (Ouvinte ouvinte : listaOuvintes) {
            if(ouvinte.getId() == listaJogadores.get(indexVencedor).getId()) {
                ouvinte.setVitorias(ouvinte.getVitorias()+1);
                break;
            }
        }

        System.out.println("\nO peso do saco é " +  df.format(peso) +  "kg.");
        System.out.println("Parabéns " + listaJogadores.get(indexVencedor).getNome() + "!");
        System.out.println("Ganhou o Jogo do Saco com um palpite de " + df.format(listaPalpites.get(indexVencedor)) + "kg.");
    }

    private static void Extra() {
        if(listaOuvintes.size() == 0) {
            System.out.println("Não há  jogadores disponíveis.");
            System.out.println("A lista de ouvintes está vazia.");
            return;
        }

        Random rnd = new Random();
        List<Ouvinte> listaJogadores = new ArrayList<>();

        // Número aleatório de jogadores
        int numeroJogadores = rnd.nextInt(1,listaOuvintes.size());

        // Criar lista de jogadores e atualizar número de vezes que cada jogador participou num jogo
        for (int i = 0; i < numeroJogadores; i++) {
            int index = rnd.nextInt(1, listaOuvintes.size());
            if(!listaJogadores.contains(listaOuvintes.get(index))) {
                listaJogadores.add(listaOuvintes.get(index));
                listaOuvintes.get(index).setParticipacoes(listaOuvintes.get(index).getParticipacoes()+1);
            } else {
                i--;
            }
        }

        // Formatar valores dos pesos - 3 casas decimais
        DecimalFormat df = new DecimalFormat("0.000");

        double pesoMin = rnd.nextInt(10,101) / 10.0;
        double pesoMax = pesoMin + 0.150;

        System.out.println("\nBom dia caros ouvintes!");
        System.out.println("A margem de ajuda de hoje é de " + df.format(pesoMin) + "kg a " + df.format(pesoMax) + "kg");
        System.out.println("Liberte a Anabela de Malhadas que há em si!");
        System.out.println("Boa sorte!\n");

        // Posição em que o utilizador vai jogar
        int posAnabela = rnd.nextInt(0, listaJogadores.size()+1);

        // Criar novo ouvinte para poder contabilizar participações, vitórias e adicionar ao ranking
        listaJogadores.add(posAnabela, listaOuvintes.get(0));
        listaOuvintes.get(0).setParticipacoes(listaOuvintes.get(0).getParticipacoes()+1);

        // Lista de palpites dos jogadores
        List<Double> listaPalpites = new ArrayList<>();

        // Peso vencedor
        double peso = pesoMin + rnd.nextInt(0,151) / 1000.0;

        double diferenca = Double.MAX_VALUE;
        int indexVencedor = -1;

        String[] frasesLocutor = {
                "Você é terrível!",
                "Eheh, gosto  tanto de a ouvir Anabela!",
                "Tem que estar entre "+ df.format(pesoMin) + "kg e " +df.format(pesoMax) + "kg... Então isso é mais de "  + df.format(pesoMin) + " e menos de "+df.format(pesoMax) +"?",
                "Complicado Anabela!",
                "Você é um ponto... :D :D :D",
                "Vai-me dar o fanico consigo Anabela! :'D :D'",
                "Anabela, você está  a fazer-me transpirar, a sério! x'D",
                "ROFL",
                "Estou-me a ficar consigo, Anabela! xD",
                "Vai-me dar o treco consigo  Anabela! xD",
                ":D :D :D",
                "xD xD",
                "Ai Jesus o que lhe deu hoje!",
        };

        System.out.println("PARTICIPANTES E PALPITES ATÉ AO MOMENTO");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("\n" + listaJogadores.get(posAnabela).getNome() +  ", é a " + (posAnabela+1) + "ª participante.\n");
        for (int i = 0; i < listaJogadores.size(); i++) {
            if(posAnabela == i) {
                System.out.println("\nQual é o seu palpite, Anabela?");
                double palpiteAnabela = in.nextDouble();
                if(!listaPalpites.contains(palpiteAnabela) && palpiteAnabela >= pesoMin && palpiteAnabela <= pesoMax) {
                    listaPalpites.add(palpiteAnabela);
                    System.out.println("\nPalpite adicionado!\n");
                    if(Math.abs(palpiteAnabela - peso) < diferenca) {
                        diferenca =  Math.abs(palpiteAnabela - peso);
                        indexVencedor = i;
                    }
                } else {
                    System.out.println(frasesLocutor[rnd.nextInt(0,frasesLocutor.length-1)]);
                    if(palpiteAnabela < pesoMin) {
                        System.out.println("Tem que aumentar para a frente mulher! :D");
                    }
                    if(palpiteAnabela > pesoMax) {
                        System.out.println("Tem que diminuir o valor Anabela! xD");
                    }
                    i--;
                }
            } else {
                double palpite = pesoMin + rnd.nextInt(0,151) / 1000.0;
                if(!listaPalpites.contains(palpite)) {
                    listaPalpites.add(palpite);
                    if(i < posAnabela) System.out.println("Jogador " + (i+1) + "\t|\t" + listaJogadores.get(i).getNome() + "\t|\tPalpite: " + df.format(palpite) + "kg.");
                    if(Math.abs(palpite - peso) < diferenca) {
                        diferenca =  Math.abs(palpite - peso);
                        indexVencedor = i;
                    }
                } else {
                    i--;
                }
            }
        }

        System.out.println("\nLISTA DE PARTICIPANTES");
        System.out.println("----------------------------------------------------------------------------------------");
        for (int i = 0; i < listaJogadores.size(); i++) {
            System.out.println("Jogador " + (i+1) + "\t|\t" + listaJogadores.get(i).getNome() + "\t|\tPalpite: " + df.format(listaPalpites.get(i)) + "kg.");
        }
        System.out.println("----------------------------------------------------------------------------------------");

        //Atualizar número de vitória do jogador vencedor
        for (Ouvinte ouvinte : listaOuvintes) {
            if(ouvinte.getId() == listaJogadores.get(indexVencedor).getId()) {
                ouvinte.setVitorias(ouvinte.getVitorias()+1);
                break;
            }
        }

        System.out.println("\nO peso do saco é " +  df.format(peso) +  "kg.");
        System.out.println("Parabéns " + listaJogadores.get(indexVencedor).getNome() + "!");
        System.out.println("Ganhou o Jogo do Saco com um palpite de " + df.format(listaPalpites.get(indexVencedor)) + "kg.");
    }

    private static void GerarDadosAplicacao() {
        listaOuvintes.add(new Ouvinte(0, "Anabela de Malhadas", "929112327", 1, 0));
        listaOuvintes.add(new Ouvinte(1, "Vitor Gabriel Almeida", "929155527", 20, 2));
        listaOuvintes.add(new Ouvinte(2, "Daniel Aragão","929355541", 10, 1));
        listaOuvintes.add(new Ouvinte(3, "Leandro Ribeiro", "921555985", 5, 0));
        listaOuvintes.add(new Ouvinte(4, "Ana Carolina Rodrigues","921555565", 13, 3));
        listaOuvintes.add(new Ouvinte(5, "Valentina Lopes","965557097", 16, 1));
        listaOuvintes.add(new Ouvinte(6, "Rafaela da Cunha","935558280", 25, 0));
        listaOuvintes.add(new Ouvinte(7, "Paulo Duarte","922555310", 30, 2));
        listaOuvintes.add(new Ouvinte(8, "Carlos Eduardo Peixoto","922555989", 23, 1));
        listaOuvintes.add(new Ouvinte(9, "Kevin Costa","935550942", 3, 0));
        listaOuvintes.add(new Ouvinte(10, "Camila Caldeira","965554938", 5, 0));
        listaOuvintes.add(new Ouvinte(11, "Luana da Mata","921555315", 1, 0));
        listaOuvintes.add(new Ouvinte(12, "Joaquim Gonçalves","922555879", 1, 1));
        listaOuvintes.add(new Ouvinte(13, "Maria Eduarda Fernandes","921555397", 7, 1));
        listaOuvintes.add(new Ouvinte(14, "Sara Xavier","915557214", 8, 0));
        listaOuvintes.add(new Ouvinte(15, "Vitória Sales","915555985", 4, 0));
        listaOuvintes.add(new Ouvinte(16, "Julia Pereira","935557669", 3, 0));
        listaOuvintes.add(new Ouvinte(17, "Catarina Costela","965555251", 11, 2));
        listaOuvintes.add(new Ouvinte(18, "Nicolas Duarte","915555958", 1, 0));
        listaOuvintes.add(new Ouvinte(19, "Vinicius Rocha","921555619", 2, 0));
        listaOuvintes.add(new Ouvinte(20, "Maria Vitória Carvalho","915550064", 18, 5));
    }
}