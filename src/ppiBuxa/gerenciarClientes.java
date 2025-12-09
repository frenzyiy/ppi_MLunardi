package ppiBuxa;

import java.util.Scanner;

public class gerenciarClientes {
    public static void main(String[] args) throws InterruptedException {
        boolean verificacao = true;
        Scanner ler = new Scanner(System.in);

        while (verificacao) {
            System.out.println("====================================");
            System.out.println("GERENCIAR CLIENTES");
            System.out.println("1- Adicionar Cliente");
            System.out.println("2- Editar cliente");
            System.out.println("3- Excluir cliente");
            System.out.println("4- Ver clientes");
            System.out.println("5- Buscar cliente");
            System.out.println("0- Sair");
            System.out.println("====================================");
            System.out.println("Escolha a opção desejada:");

            int opcao = ler.nextInt();

            if (opcao == 1) {
                System.out.println("Digite o nome do cliente:");
                String nome = ler.next();
                System.out.println("Digite o CPF do cliente:");
                String cpf = ler.next();
                
            }

        }
    }
}
