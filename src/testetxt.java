import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class testetxt {

    // Atributos para armazenar os dados lidos do arquivo
    static String nomeUsuarioLido = "";
    static int pontuacaoLida = 0;
    static List<String> itensInventarioLidos = new java.util.ArrayList<>();

    public static void main(String[] args) {
        // 1. Dados de exemplo a serem salvos
        String nomeUsuario = "João Silva";
        int pontuacao = 1500;
        List<String> itensInventario = Arrays.asList("Espada Longa", "Poção de Vida", "Escudo de Madeira");

        // Nome do arquivo onde os dados serão salvos
        String nomeArquivo = "C:\\Users\\2025017647\\Documents\\teste.txt";

        try {
            // 2. Cria um FileWriter e um PrintWriter para escrever no arquivo
            // O 'true' no construtor de FileWriter significa que ele anexará ao arquivo (append),
            // se você quiser sobrescrever, use 'false' ou omita o parâmetro.
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // 3. Escreve os dados linha por linha
            printWriter.println("Nome do Usuário: " + nomeUsuario);
            printWriter.println("Pontuação Máxima: " + pontuacao);
            printWriter.println("--- Inventário ---");
            for (String item : itensInventario) {
                printWriter.println("- " + item);
            }

            // 4. Fecha o PrintWriter para garantir que todos os dados sejam gravados
            // e libere os recursos do sistema.
            printWriter.close();

            System.out.println("Dados salvos com sucesso em " + nomeArquivo);

            // 5. Ler os dados do arquivo e salvar nos atributos
            lerDadosDoArquivo(nomeArquivo);
            // Exemplo de uso dos atributos lidos
            System.out.println("\n--- Dados lidos do arquivo ---");
            System.out.println("Nome do Usuário: " + nomeUsuarioLido);
            System.out.println("Pontuação Máxima: " + pontuacaoLida);
            System.out.println("--- Inventário ---");
            for (String item : itensInventarioLidos) {
                System.out.println("- " + item);
            }

        } catch (IOException e) {
            // Trata possíveis erros de E/S (ex: arquivo não pode ser criado/escrito)
            System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para ler os dados do arquivo e salvar nos atributos
    private static void lerDadosDoArquivo(String nomeArquivo) {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(nomeArquivo))) {
            String linha;
            boolean lendoInventario = false;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome do Usuário:")) {
                    nomeUsuarioLido = linha.replace("Nome do Usuário:", "").trim();
                } else if (linha.startsWith("Pontuação Máxima:")) {
                    String valor = linha.replace("Pontuação Máxima:", "").trim();
                    try {
                        pontuacaoLida = Integer.parseInt(valor);
                    } catch (NumberFormatException e) {
                        pontuacaoLida = 0;
                    }
                } else if (linha.startsWith("--- Inventário ---")) {
                    lendoInventario = true;
                } else if (lendoInventario && linha.startsWith("- ")) {
                    itensInventarioLidos.add(linha.replace("- ", "").trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}