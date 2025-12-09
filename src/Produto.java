public class Produto {
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }

    @Override
    public String toString() {
        return nome + ";" + quantidade + ";" + preco;
    }

    // Converte uma linha do txt em um produto
    public static Produto fromString(String linha) {
        String[] partes = linha.split(";");
        return new Produto(
            partes[0],
            Integer.parseInt(partes[1]),
            Double.parseDouble(partes[2])
        );
    }}
