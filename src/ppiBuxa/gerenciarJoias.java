package ppiBuxa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class gerenciarJoias {

        // ===============================
        // MANTER NO TXT
        // ===============================
static void pegarEstoqueTXT(
            String[] nome_produto,
            String[] nome_fornecedor,
            int[] cod_produto,
            int[] peso,
            int[] quantidade,
            double[] valor_bruto,
            double[] custo_total,
            double[] valor_final,
            int[] banhoEscolhido,
            String[] codigosBanho) {

        File arquivo = new File("estoque.txt");


        try {
            Scanner leitor = new Scanner(arquivo);

            if (leitor.hasNextLine()) {
                leitor.nextLine();
            }

            int i = 0;
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                if (dados.length >= 9) {
                    nome_fornecedor[i] = dados[0];
                    cod_produto[i] = Integer.parseInt(dados[1]);

                    String codigoBanhoLido = dados[2];
                    banhoEscolhido[i] = 0;
                    for (int j = 0; j < codigosBanho.length; j++) {
                        if (codigosBanho[j].equalsIgnoreCase(codigoBanhoLido)) {
                            banhoEscolhido[i] = j;
                            break;
                        }
                    }

                    nome_produto[i] = dados[3];
                    peso[i] = Integer.parseInt(dados[4]);
                    quantidade[i] = Integer.parseInt(dados[5]);
                    valor_bruto[i] = Double.parseDouble(dados[6]);
                    custo_total[i] = Double.parseDouble(dados[7]);
                    valor_final[i] = Double.parseDouble(dados[8]);

                    i++;
                }
            }
            leitor.close();

        } catch (Exception e) {
            System.out.println("Erro.");
        }
    }
    //==================
    // 2. SALVAR DADOS
    // =================
    static void salvarEstoqueTXT(
            String[] nome_produto,
            String[] nome_fornecedor,
            int[] cod_produto,
            int[] peso,
            int[] quantidade,
            double[] valor_bruto,
            double[] custo_total,
            double[] valor_final,
            int[] banhoEscolhido,
            String[] codigosBanho) {

        try {
            BufferedWriter escrever = new BufferedWriter(new FileWriter("estoque.txt"));

            escrever.write("FORNECEDOR;CODIGO;BANHO;PRODUTO;PESO;QTD;BRUTO;CUSTO;FINAL");
            escrever.newLine();

            for (int i = 0; i < nome_produto.length; i++) {
                if (nome_produto[i] != null && quantidade[i] != -1) {
                    String linha = String.format("%s;%d;%s;%s;%d;%d;%s;%s;%s",
                            nome_fornecedor[i],
                            cod_produto[i],
                            codigosBanho[banhoEscolhido[i]],
                            nome_produto[i],
                            peso[i],
                            quantidade[i],
                            String.valueOf(valor_bruto[i]),
                            String.valueOf(custo_total[i]),
                            String.valueOf(valor_final[i])
                    );

                    escrever.write(linha);
                    escrever.newLine();
                }
            }
            escrever.close();
            System.out.println("Estoque salvo e atualizado.");

        } catch (IOException e) {
            System.out.println("Erro.");
        }
    }


        // ===============================
        // MANTER NO TXT clientes
        // ===============================
static void pegarClienteTXT(
        String[] nome_cliente, 
        String[] contato_cliente, 
        String[] email_cliente, 
        String[] empresa_cliente ) {

        File arquivo = new File("clientes.txt");


        try {
            Scanner leitor = new Scanner(arquivo);

            if (leitor.hasNextLine()) {
                leitor.nextLine();
            }

            int i = 0;
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                if (dados.length >= 4) {
                    nome_cliente[i] = dados[0];
                    contato_cliente[i] = dados[1];
                    email_cliente[i] = dados[2];
                    empresa_cliente[i] = dados[3];
                    i++;
                
                    }

                }
            
            leitor.close();

        } catch (Exception e) {
            System.out.println("Erro.");
        }
    }
    //==================
    // 2. SALVAR DADOS clientes
    // =================
    static void salvarClienteTXT(
        String[] nome_cliente, 
        String[] contato_cliente, 
        String[] email_cliente, 
        String[] empresa_cliente ) {

        try {
            BufferedWriter escrever = new BufferedWriter(new FileWriter("clientes.txt"));

            escrever.write("NOME  ;CONTATO  ;E-MAIL  ;EMPRESA");
            escrever.newLine();

            for (int i = 0; i < nome_cliente.length; i++) {
                if (nome_cliente[i] != null) {
                    String linha = String.format("%s;%s;%s;%s",
                            nome_cliente[i],
                            contato_cliente[i],
                            email_cliente[i],
                            empresa_cliente[i]
                    );

                    escrever.write(linha);
                    escrever.newLine();
                }
            }
            escrever.close();
            System.out.println("Clientes salvos com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro.");
        }
    }




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
    
        String[] nome_cliente = new String[10000];
        String[] contato_cliente = new String[10000];
        int[] parcelas_pagamento = new int[10000];
        String[] email_cliente = new String[10000];
        String[] empresa_cliente = new String[10000];
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

    
        pegarEstoqueTXT(
            nome_produto, nome_fornecedor, cod_produto, peso,
            quantidade, valor_bruto, custo_total, valor_final,
            banhoEscolhido, codigosBanho);

        pegarClienteTXT(nome_cliente, contato_cliente, email_cliente, empresa_cliente);

        boolean verificacao = true;

        while (verificacao) {

            System.out.println("====================================");
            System.out.println("CONTROLE DE ESTOQUE E VENDAS");
            System.out.println("1- Adicionar produto");
            System.out.println("2- Editar produto");
            System.out.println("3- Excluir produto");

            System.out.println("4- Ver estoque");
            System.out.println("5- Buscar produto");
            System.out.println("6- Atualizar banho");
            System.out.println("7- Registrar venda");

            System.out.println("8- Adicionar cliente");
            System.out.println("9- Editar cliente");
            System.out.println("10- Excluir cliente");
            System.out.println("11- Ver cliente");
            System.out.println("12- Buscar cliente");
        
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
                            cod_produto[i] = -1;
                            nome_produto[i] = null;
                            nome_fornecedor[i] = null;
                            peso[i] = -1;
                            valor_bruto[i] = -1;
                            custo_total[i] = -1;
                            valor_final[i] = -1;
                            banhoEscolhido[i] = -1;
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
                System.out.printf("| %-15s | %-12s | %-6s | %-20s | %-5s | %-5s | %-10s | %-12s | %-12s |\n",
                        "FORNECEDOR", "COD PRODUTO", "BANHO", "PRODUTO", "QTD", "PESO", "BRUTO", "CUSTO TOTAL", "VALOR FINAL");
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");

                for (int i = 0; i < nome_produto.length; i++) {
                    if (nome_produto[i] != null && quantidade[i] != -1) {
                    	System.out.printf("| %-15s | %-12d | %-6s | %-20s | %-5d | %-5d | %-10.2f | %-12.2f | %-12.2f |\n",
                                nome_fornecedor[i], cod_produto[i], codigosBanho[banhoEscolhido[i]], nome_produto[i],quantidade[i],
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
                if (quantidade[indiceProduto] <= 0) {
                    System.out.println("Produto sem estoque disponível!");
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
                String nomeCliente = estoque.nextLine();

                System.out.println("Empresa do cliente:");
                String empresaCliente = estoque.nextLine();

                int indiceCliente = -1;

                
                for (int i = 0; i < nome_cliente.length; i++) {
                    if (nome_cliente[i] != null &&
                        nome_cliente[i].equalsIgnoreCase(nomeCliente) &&
                        empresa_cliente[i].equalsIgnoreCase(empresaCliente)) {

                        indiceCliente = i;
                        break;
                    }
                }

                if (indiceCliente == -1) {
                    System.out.println("Cliente não encontrado. Cadastre o cliente primeiro.");
                    continue;
                }


                String contatoCliente = contato_cliente[indiceCliente];
                String emailCliente = email_cliente[indiceCliente];
                int parcelasCliente = parcelas_pagamento[indiceCliente];

                
                System.out.println("Porcentagem de lucro para esta venda:");
                double lucroVenda = estoque.nextDouble();
                estoque.nextLine();

                double custoVenda = valor_bruto[indiceProduto] + (valorBanhoVenda * peso[indiceProduto]);
                double valorVendaFinal = custoVenda + (custoVenda * (lucroVenda / 100));

                for (int i = 0; i < codProdutoVenda.length; i++) {
                    if (codProdutoVenda[i] == 0) {
                        codProdutoVenda[i] = codigoVenda;
                        valorVenda[i] = valorVendaFinal;
                        nome_cliente[i] = nomeCliente;
                        contato_cliente[i] = contatoCliente;
                        email_cliente[i] = emailCliente;
                        parcelas_pagamento[i] = parcelasCliente;
                        
                        
                        quantidade[indiceProduto] = quantidade[indiceProduto] - 1;
                        
                        System.out.println("Venda registrada! Valor final: R$" + valorVendaFinal);
                        break;
                    }
                }
            }
        
        
        //==========================================================================================================================
        
        
            // ===============================
            // 8 — ADICIONAR CLIENTE
            // ===============================
        
            if (opcao == 8) {
                int parcelasPagamento = 0;
                
                System.out.println("Digite o nome do cliente:");
                String nomeCliente = estoque.nextLine();

                System.out.println("Digite o contato do cliente:");
                String contatoCliente = estoque.nextLine();

                System.out.println("Digite o email do cliente");
                String emailCliente = estoque.nextLine();
            
                System.out.println("Digite empresa cliente");
                String empresaCliente = estoque.nextLine();

                System.out.println("O cliente vai pagar em parcelas? (sim/não)");
                String pagarParcelado = estoque.nextLine();

                if (pagarParcelado.equalsIgnoreCase("sim")) {
                    System.out.println("Digite quantas parcelas:");
                    parcelasPagamento = estoque.nextInt();
                    estoque.nextLine();
                } else {
                    parcelasPagamento = 0;
                }

                for (int i = 0; i <= nome_cliente.length - 1; i++) {
                    if (nome_cliente[i] == null) {
                    
                        nome_cliente[i] = nomeCliente;
                        contato_cliente[i] = contatoCliente;
                        parcelas_pagamento[i] = parcelasPagamento;
                        empresa_cliente[i] = empresaCliente;
                        email_cliente[i] = emailCliente;
                        System.out.println("novo Cliente cadastrado com sucesso ");
                        break;
                    }
                }
            }

            // ===============================
            // 9 — EDITAR CLIENTE
            // ===============================
            if (opcao == 9) {

            	System.out.println("Digite o nome do cliente para buscar:");
                String buscarCliente = estoque.nextLine();

                System.out.println("Digite o empresa do cliente para buscar:");
                String buscarEmpresa = estoque.nextLine();

                for (int i = 0; i <= nome_cliente.length - 1; i++) {

                    if (nome_cliente[i].equals(buscarCliente) && empresa_cliente[i].equals(buscarEmpresa)) {

                        System.out.println("Novo cliente:");
                        nome_cliente[i] = estoque.nextLine();

                        System.out.println("Novo contato:");
                        contato_cliente[i] = estoque.nextLine();

                        System.out.println("Digite o email do cliente");
                        String emailCliente = estoque.nextLine();
                    
                        System.out.println("Digite empresa cliente");
                        String empresaCliente = estoque.nextLine();
                    
                        System.out.println("Novo pagamento:");
                        parcelas_pagamento[i] = estoque.nextInt();
                    
            
                        System.out.println("Produto atualizado!");
                        break;
                    }
                }
            }

            // ===============================
            // 10 — EXCLUIR CLIENTE
            // ===============================
            if (opcao == 10) {

            	System.out.println("Digite o nome do cliente que deseja excluir:");
                String nomeClienteExcluir = estoque.nextLine();

                System.out.println("Digite a empresa do cliente que deseja excluir:");
                String empresaClienteExcluir = estoque.nextLine();
                
                System.out.println("Certeza que deseja excluir? (s/n)");
                String resp = estoque.nextLine();

                if (resp.equalsIgnoreCase("s")) {
                    for (int i = 0; i <= nome_cliente.length - 1; i++) {
                    	if (nome_cliente[i] != null &&
                                nome_cliente[i].equalsIgnoreCase(nomeClienteExcluir) &&
                                empresa_cliente[i] != null &&
                                empresa_cliente[i].equalsIgnoreCase(empresaClienteExcluir)) {


                            nome_cliente[i] = null;
                            contato_cliente[i] = null;
                            parcelas_pagamento[i] = -1;
                            email_cliente[i] = null;
                            empresa_cliente[i] = null;
                            System.out.println("Produto excluído!");
                            break;
                        }
                    }
                }
            }

            // ===============================
            // 11 — VER CLIENTES
            // ===============================
            if (opcao == 11) {

                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-20s | %-18s | %-25s | %-20s | %-10s |\n",
                        "NOME CLIENTE", "CONTATO", "EMAIL", "EMPRESA", "PARCELAS");
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");

                for (int i = 0; i < nome_cliente.length; i++) {
                    if (nome_cliente[i] != null && empresa_cliente[i] != null) {
                    	System.out.printf("| %-20s | %-18s | %-25s | %-20s | %-10s |\n",
                    	nome_cliente[i],contato_cliente[i],email_cliente[i],empresa_cliente[i],parcelas_pagamento[i]);

                    }
                }
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                Thread.sleep(2000);
            }

            // ===============================
            // 12 — BUSCAR CLIENTE
            // ===============================
            if (opcao == 12) {

                System.out.println("Digite o nome do cliente para buscar:");
                String buscarCliente = estoque.nextLine();

                System.out.println("Digite o empresa do cliente para buscar:");
                String buscarEmpresa = estoque.nextLine();
                for (int i = 0; i <= nome_cliente.length - 1; i++) {
                    if (nome_cliente[i].equals(buscarCliente) && empresa_cliente[i] != null && empresa_cliente[i].equals(buscarEmpresa)) {
                        System.out.println("cliente encontrado:");
                        System.out.printf("NOME CLIENTE: %s | CONTATO: %s | PARCELAS PAGAMENTO: %d | EMPRESA CLIENTE: %s | EMAIL: %s \n",
                        nome_cliente[i], contato_cliente[i], parcelas_pagamento[i],empresa_cliente[i],email_cliente[i]);
                        break;
                    }
                }
            }

        
            // ===============================
            // 0 — SAIR
            // ===============================
            if (opcao == 0) {
                            
            salvarEstoqueTXT(
                nome_produto, nome_fornecedor, cod_produto, peso,
                quantidade, valor_bruto, custo_total, valor_final,
                banhoEscolhido, codigosBanho );

            salvarClienteTXT(nome_cliente, contato_cliente, email_cliente, empresa_cliente);
            
                System.out.println("Sistema encerrado.");
                verificacao = false;
                estoque.close();
            }
        }
    }
}