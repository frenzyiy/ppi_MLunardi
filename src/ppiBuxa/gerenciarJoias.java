package ppiBuxa;

import java.util.Scanner;

public class gerenciarJoias {

    public static void main(String[] args) throws InterruptedException {

        Scanner estoque = new Scanner(System.in);

        // ===============================
        // VETORES DO ESTOQUE
        // ===============================
        String[] nome_produto = new String[10000];
        int[] quantidade = new int[10000];
        String[] nome_fornecedor = new String[10000];
        int[] cod_produto = new int[10000];
        int[] peso = new int[10000];
        double[] valor_bruto = new double[10000];
        double[] custo_total = new double[10000]; // banhoAtual x peso + bruto
        double[] valor_final = new double[10000]; // custo_total + % lucro
        int[] banhoEscolhido = new int[10000];

        // ===============================
        // BANHOS FIXOS
        // ===============================
        String[] codigosBanho = { "P5", "O3", "O7" };
        double[] valorBanho = { 0, 0, 0 };

        // ===============================
        // VETORES DE VENDAS
        // ===============================
        int[] codProdutoVenda = new int[10000];
        double[] valorVenda = new double[10000];
        String[] nomeCliente = new String[10000];
        String[] contatoCliente = new String[10000];

        boolean verificacao = true;

        while (verificacao) {

            System.out.println("====================================");
            System.out.println("CONTROLE DE ESTOQUE E VENDAS");
            System.out.println("1- Adicionar produto");
            System.out.println("2- Editar produto");
            System.out.println("3- Excluir produto");
            System.out.println("4- Ver estoque");
            System.out.println("5- Buscar produto");
            System.out.println("6- Atualizar BANHO");
            System.out.println("7- Registrar venda");
            System.out.println("0- Sair");
            System.out.println("====================================");
            System.out.println("Escolha a opção desejada:");
            int opcao = estoque.nextInt();
            estoque.nextLine();

            // ===============================
            // 1 — ADICIONAR PRODUTO
            // ===============================
            if (opcao == 1) {

                System.out.println("Digite o nome do fornecedor:");
                String fornecedor = estoque.nextLine();

                System.out.println("Digite o código do produto:");
                int codigo = estoque.nextInt();

                estoque.nextLine();
                System.out.println("Digite o nome do produto:");
                String nome = estoque.nextLine();

                System.out.println("Digite o peso da joia:");
                int pesoJoia = estoque.nextInt();

                System.out.println("Digite a quantidade:");
                int quant = estoque.nextInt();

                System.out.println("Digite o valor bruto:");
                double bruto = estoque.nextDouble();

                System.out.println("Digite a porcentagem de lucro desejada (ex: 20):");
                double lucro = estoque.nextDouble();
                estoque.nextLine();

                // Escolha de banho para o produto
                System.out.println("Escolha o banho para este produto:");
                for (int i = 0; i < codigosBanho.length; i++) {
                    System.out.println((i + 1) + " - " + codigosBanho[i] + " (R$ " + valorBanho[i] + ")");
                }
                System.out.print("Opção: ");
                int banho = estoque.nextInt() - 1;
                estoque.nextLine();

                for (int i = 0; i <= nome_produto.length - 1; i++) {
                    if (nome_produto[i] == null) {
                        nome_fornecedor[i] = fornecedor;
                        cod_produto[i] = codigo;
                        nome_produto[i] = nome;
                        peso[i] = pesoJoia;
                        quantidade[i] = quant;
                        valor_bruto[i] = bruto;
                        banhoEscolhido[i] = banho;

                        // Calcula custo e valor final
                        custo_total[i] = (valorBanho[banho] * pesoJoia) + bruto;
                        valor_final[i] = custo_total[i] + (custo_total[i] * (lucro / 100));

                        System.out.println("Produto adicionado na posição " + i);
                        break;
                    }
                }
            }

            // ===============================
            // 2 — EDITAR PRODUTO
            // ===============================
            if (opcao == 2) {

                System.out.println("Digite o código do produto que deseja editar:");
                int buscar = estoque.nextInt();
                estoque.nextLine();

                for (int i = 0; i <= cod_produto.length - 1; i++) {

                    if (cod_produto[i] == buscar) {

                        System.out.println("Novo fornecedor:");
                        nome_fornecedor[i] = estoque.nextLine();

                        System.out.println("Novo nome:");
                        nome_produto[i] = estoque.nextLine();

                        System.out.println("Novo peso:");
                        peso[i] = estoque.nextInt();

                        System.out.println("Nova quantidade:");
                        quantidade[i] = estoque.nextInt();

                        System.out.println("Novo valor bruto:");
                        valor_bruto[i] = estoque.nextDouble();

                        System.out.println("Porcentagem de lucro:");
                        double lucro = estoque.nextDouble();
                        estoque.nextLine();

                        // Recalcular custo e valor final usando banho existente
                        int banho = banhoEscolhido[i];
                        custo_total[i] = (valorBanho[banho] * peso[i]) + valor_bruto[i];
                        valor_final[i] = custo_total[i] + (custo_total[i] * (lucro / 100));

                        System.out.println("Produto atualizado!");
                        break;
                    }
                }
            }

            // ===============================
            // 3 — EXCLUIR PRODUTO
            // ===============================
            if (opcao == 3) {

                System.out.println("Digite o código do produto que deseja excluir:");
                int buscar = estoque.nextInt();
                estoque.nextLine();

                System.out.println("Certeza que deseja excluir? (s/n)");
                String resp = estoque.nextLine();

                if (resp.equalsIgnoreCase("s")) {
                    for (int i = 0; i <= cod_produto.length - 1; i++) {
                        if (cod_produto[i] == buscar) {
                            cod_produto[i] = 0;
                            nome_produto[i] = null;
                            nome_fornecedor[i] = null;
                            peso[i] = 0;
                            valor_bruto[i] = 0;
                            custo_total[i] = 0;
                            valor_final[i] = 0;
                            banhoEscolhido[i] = 0;
                            quantidade[i] = -1;
                            System.out.println("Produto excluído!");
                            break;
                        }
                    }
                }
            }

            // ===============================
            // 4 — LISTAR ESTOQUE
            // ===============================
            if (opcao == 4) {

                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-15s | %-12s | %-6s | %-20s | %-5s | %-10s | %-12s | %-12s |\n",
                        "FORNECEDOR", "COD PRODUTO", "BANHO", "PRODUTO", "PESO", "BRUTO", "CUSTO TOTAL", "VALOR FINAL");
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");

                for (int i = 0; i < nome_produto.length; i++) {
                    if (nome_produto[i] != null && quantidade[i] != -1) {
                        System.out.printf("| %-15s | %-12d | %-6s | %-20s | %-5d | %-10.2f | %-12.2f | %-12.2f |\n",
                                nome_fornecedor[i], cod_produto[i], codigosBanho[banhoEscolhido[i]], nome_produto[i],
                                peso[i], valor_bruto[i], custo_total[i], valor_final[i]);
                    }
                }
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                Thread.sleep(2000);
            }

            // ===============================
            // 5 — BUSCAR PRODUTO
            // ===============================
            if (opcao == 5) {

                System.out.println("Digite o código do produto para buscar:");
                int buscar = estoque.nextInt();
                estoque.nextLine();

                for (int i = 0; i <= cod_produto.length - 1; i++) {
                    if (cod_produto[i] == buscar && nome_produto[i] != null) {
                        System.out.println("Produto encontrado:");
                        System.out.printf(
                                "FORNECEDOR: %s | COD PRODUTO: %d | BANHO: %s | PRODUTO: %s | PESO: %d | BRUTO: %.2f | CUSTO TOTAL: %.2f | VALOR FINAL: %.2f\n",
                                nome_fornecedor[i], cod_produto[i], codigosBanho[banhoEscolhido[i]], nome_produto[i],
                                peso[i], valor_bruto[i], custo_total[i], valor_final[i]);
                        break;
                    }
                }
            }

            // ===============================
            // 6 — ATUALIZAR BANHOS FIXOS
            // ===============================
            if (opcao == 6) {

                System.out.println("BANHOS DISPONÍVEIS:");
                for (int i = 0; i < codigosBanho.length; i++) {
                    System.out.println((i + 1) + " - " + codigosBanho[i] + " (Valor atual: R$ " + valorBanho[i] + ")");
                }

                System.out.println(
                        "Digite o código do banho que deseja atualizar (P5 - PRATA/O3 - OURO/O7 - OURO DE GARGANTILHAS E PULSEIRAS):");
                String cod = estoque.nextLine();

                for (int i = 0; i < codigosBanho.length; i++) {
                    if (codigosBanho[i].equalsIgnoreCase(cod)) {
                        System.out.println("Digite o novo valor do banho " + codigosBanho[i] + ":");
                        valorBanho[i] = estoque.nextDouble();
                        estoque.nextLine();
                        System.out.println("Banho atualizado com sucesso!");
                        break;
                    }
                }
            }

            // ===============================
            // 7 — REGISTRAR VENDA
            // ===============================
            if (opcao == 7) {

                System.out.println("Digite o código do produto que deseja vender:");
                int codigoVenda = estoque.nextInt();
                estoque.nextLine();

                int indiceProduto = -1;
                for (int i = 0; i < cod_produto.length; i++) {
                    if (cod_produto[i] == codigoVenda && nome_produto[i] != null) {
                        indiceProduto = i;
                        break;
                    }
                }

                if (indiceProduto == -1) {
                    System.out.println("Produto não encontrado!");
                    continue;
                }

                // Escolher banho para venda
                System.out.println(
                        "Escolha o banho para esta venda (P5 - PRATA/O3 - OURO/O7 - OURO DE GARGANTILHAS E PULSEIRAS):");
                String banhoVenda = estoque.nextLine();
                double valorBanhoVenda = 0;
                for (int i = 0; i < codigosBanho.length; i++) {
                    if (codigosBanho[i].equalsIgnoreCase(banhoVenda)) {
                        valorBanhoVenda = valorBanho[i];
                        break;
                    }
                }

                System.out.println("Nome do cliente:");
                String cliente = estoque.nextLine();
                System.out.println("Contato do cliente:");
                String contato = estoque.nextLine();
                System.out.println("Porcentagem de lucro para esta venda:");
                double lucroVenda = estoque.nextDouble();
                estoque.nextLine();

                double custoVenda = valor_bruto[indiceProduto] + (valorBanhoVenda * peso[indiceProduto]);
                double valorVendaFinal = custoVenda + (custoVenda * (lucroVenda / 100));

                for (int i = 0; i < codProdutoVenda.length; i++) {
                    if (codProdutoVenda[i] == 0) {
                        codProdutoVenda[i] = codigoVenda;
                        valorVenda[i] = valorVendaFinal;
                        nomeCliente[i] = cliente;
                        contatoCliente[i] = contato;
                        System.out.println("Venda registrada! Valor final: R$" + valorVendaFinal);
                        break;
                    }
                }
            }

            // ===============================
            // 0 — SAIR
            // ===============================
            if (opcao == 0) {
                System.out.println("Sistema encerrado.");
                verificacao = false;
                estoque.close();
            }
        }
    }
}