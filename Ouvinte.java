public class Ouvinte {
    private int id;
    private String nome;
    private String contacto;
    private int participacoes;
    private int vitorias;

    public Ouvinte() {
        id = 0;
        nome = "";
        contacto = "";
        participacoes = 0;
        vitorias = 0;
    }

    public Ouvinte(int id, String nome, String contacto) {
        this.id = id;
        this.nome = nome;
        this.contacto = contacto;
    }

    public Ouvinte(int id, String nome, String contacto, int participacoes, int vitorias) {
        this.id = id;
        this.nome = nome;
        this.contacto = contacto;
        this.participacoes = participacoes;
        this.vitorias = vitorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(int participacoes) {
        this.participacoes = participacoes;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    @Override
    public String toString() {

        return  id +
                "\t|\t" + nome +
                "\t|\t" + contacto +
                "\t|\tParticipacoes: " + participacoes +
                "\t|\tVitorias: " + vitorias;
    }

    public String showOuvinte() {
        return id + "\t|\t" + nome;
    }
}
