/**
 * Classe Principal que inicializa o programa
 * @author (Roberta de Siqueira) 
 * @version (1.0)
 */
import java.util.*;
import entities.Pessoa;
import entities.Aluno;
import tela.MenuPrincipal;
import util.LimpaTela;
import util.ConversaoDados;
import java.text.ParseException;;
import java.util.Date;

public class Main
{
    public static void main (String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        List<Pessoa> lista = new ArrayList<>();
        Pessoa pessoa = new Pessoa();
        Aluno aluno = new Aluno();

        String opcao=" ";
        while (!"5".equals(opcao))
        {
            MenuPrincipal.menuPrincipal();
            //recebendo a opção do menu
            System.out.print("Digite: ");
            opcao = sc.nextLine();
            if ("1".equals(opcao)) {
                char resp = 's';
                while (resp=='s' || resp=='S') {
                    LimpaTela.limpa();
                    System.out.println("::: Cadastrar Pessoa ou Aluno :::");
                    //Solicitação dos dados que serão enviados para a lista
                    System.out.println("NOME:");
                    System.out.print(">>> ");
                    String nome = sc.nextLine();

                    System.out.println("TELEFONE: (00)00000.0000");
                    System.out.print(">>> ");
                    String telefone = sc.next();

                    System.out.println("DATA NASCIMENTO: 00/00/0000");
                    System.out.print(">>> ");
                    String data = sc.next();
                    Date dataNasc = ConversaoDados.converteStringData(data);

                    System.out.print("Nota Final s/n? ");
                    char possuiNota = 'n';
                    possuiNota = sc.next().charAt(0);
                    if (possuiNota =='s' || possuiNota=='S') {
                        System.out.print("(00,0) >>> ");
                        Double nota = sc.nextDouble();
                        lista.add(new Aluno(nome, telefone, dataNasc, nota));
                    }
                    else {
                        lista.add(new Pessoa(nome, telefone, dataNasc));
                    }
                    System.out.println("Cadastrado com sucesso!!!");
                    System.out.println();
                    System.out.println("Deseja fazer um novo cadastro s/n?");
                    resp = sc.next().charAt(0);
                    sc.nextLine();
                }
            }
            else if ("2".equals(opcao)) {
                LimpaTela.limpa();
                System.out.println("::: Lista de Todos os Cadastros :::");
                System.out.println();
                for(Pessoa linha : lista){
                    System.out.println("> " + linha);
                }
                char resp = ' ';
                while (resp != 'x' && resp!='X') {
                    System.out.println();
                    System.out.println("Aperte 'x' para sair");
                    resp = sc.next().charAt(0);
                }
            }
            else if ("3".equals(opcao)) {
                char resp=' ';
                while (resp != 'x' && resp!='X') {
                    char continuar = 's';
                    while (continuar=='s' || continuar=='S') {
                        LimpaTela.limpa();
                        System.out.println("::: Atualizar um Cadastro :::");
                        for (int i=0; i<lista.size(); i++) {
                            System.out.println("# "+ i +" ::: "+ lista.get(i));
                        }
                        System.out.println();
                        System.out.print("Digite o Código do Cadastro: ");
                        int id= sc.nextInt();
                        System.out.println("Cadastro Selecionado: > " + lista.get(id));
                        Pessoa p = lista.get(id);
                        System.out.println();
                        System.out.println("Digite: 1-NOME | 2-TELEFONE | 3 - DATA DE NASC. | 4 - NOTA FINAL");
                        System.out.print("Qual dados quer alterar? ");
                        int num = sc.nextInt();
                        String dadoAtual;
                        if (num == 1){
                            System.out.print("Digite o Nome Correto: ");
                            sc.nextLine();
                            dadoAtual = sc.nextLine();
                            p.setNome(dadoAtual);
                            p.setUltimaAlteracao();
                        }
                        else if (num == 2){
                            System.out.print("Digite o TELEFONE Correto: ");
                            dadoAtual = sc.next();
                            p.setTelefone(dadoAtual);
                            p.setUltimaAlteracao();
                        }
                        else if (num == 3){
                            System.out.print("Digite a DATA NASC Correta: ");
                            Date dataAtual = ConversaoDados.converteStringData(sc.next());
                            p.setDataNasc(dataAtual);
                            p.setUltimaAlteracao();
                        } 
                        else if (num == 4){
                            System.out.print("Digite a NOTA FINAL (00,0): ");
                            Double notaAtual = sc.nextDouble();
                            if (p instanceof Aluno) {
                                Aluno novo = (Aluno)p;
                                novo.setNotaFinal(notaAtual);
                                p = (Aluno) novo;
                                p.setUltimaAlteracao();
                            }
                            else {
                                lista.remove(p);
                                Pessoa novo = new Aluno(p.getNome(), p.getTelefone(), p.getDataNasc(), notaAtual);
                                lista.add(id, novo);
                                p.setUltimaAlteracao();
                            }
                        }
                        else { 
                            System.out.println("Código Inválido"); 
                        }
                        System.out.println("> " + id +" - "+ lista.get(id));
                        System.out.println();
                        System.out.println("Digite 's' para CONTINUAR ou 'n' para SAIR: ");
                        continuar = sc.next().charAt(0);
                    }
                    System.out.println();
                    System.out.println("Aperte 'x' para sair");
                    resp = sc.next().charAt(0);
                }
            }
            else if ("4".equals(opcao)) {
                LimpaTela.limpa();
                System.out.println("::: Deletar um Cadastro :::");
                for (int i=0; i<lista.size(); i++) {
                    System.out.println("ID: "+ i +" >> "+ lista.get(i));
                }
                System.out.println();
                System.out.print("Qual o ID deseja excluir? ");
                int id = sc.nextInt();
                char resp = ' ';
                while (resp != 'x' && resp!='X'){
                    Pessoa p = lista.get(id);
                    lista.remove(p); // Remove nome encontrado
                    System.out.println();
                    System.out.println(">>> LISTA ATUALIZADA<<<");
                    for(Pessoa linha : lista){
                        System.out.println("> " + linha);
                    }
                    System.out.println();
                    System.out.println("Aperte 'x' para sair");
                    resp = sc.next().charAt(0);
                }
            }
            else if ("5".equals(opcao)) {
                System.out.println("FECHANDO SISTEMA...");
            }
            else {
                System.out.println("#ALERTA: Digite apenas opções válidas");
            }
            LimpaTela.limpa();
        }
        sc.close();
    }
}