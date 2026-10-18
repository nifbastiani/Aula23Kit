package br.com.starlog.model;


/**
 * CAMADA MODEL (Modelo de Domínio)
 * Representa uma mensagem criptográfica a ser transmitida.
 * 
 * ATENÇÃO ESTUDANTE:
 * Implemente esta classe a partir do código extraído da TelaCaoticaLegada
 * para satisfazer os testes TDD de 01 a 06!
 * 
 * REGRA SAGRADA DE ARQUITETURA:
 * O Model NUNCA deve importar componentes visuais (zero imports de javax.swing.*)!
 */
public class CriptoMensagem {
    private final String remetente;
    private final String mensagem;
    private final int chaveDeslocamento;

    public CriptoMensagem(String remetente, String mensagem, int chaveDeslocamento) {
        // TODO: Etapa 2 - Implementar barreira Fail-Fast
        // 1. Validar se remetente é nulo ou vazio -> lançar IllegalArgumentException("Remetente não pode ser nulo ou vazio.");
        if(remetente == null || remetente.trim().isEmpty()){
            throw new IllegalArgumentException("Remetente não pode ser nulo ou vazio");
        }
        // 2. Validar se mensagem é nula ou vazia -> lançar IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        if(mensagem == null || mensagem.trim().isEmpty()){
            throw new IllegalArgumentException("Mesagem não pode ser nula ou vazia");
        }
        // 3. Validar se chaveDeslocamento está fora de 1..25 -> lançar IllegalArgumentException("Chave deve estar entre 1 e 25.");
        if(chaveDeslocamento < 1 || chaveDeslocamento > 25){
            throw new IllegalArgumentException("Chave deve estar entre 1 e 25.");
        }
        // 4. Normalizar remetente e mensagem (.trim().toUpperCase())
        this.remetente = remetente.trim().toUpperCase();
        this.mensagem = mensagem.trim().toUpperCase();
        this.chaveDeslocamento = chaveDeslocamento;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getChaveDeslocamento() {
        return chaveDeslocamento;
    }

    /**
     * Aplica o algoritmo da Cifra de César com deslocamento circular ('A'..'Z').
     * Preserva espaços e pontuações sem alteração.
     */
    public String cifrar() {
        // TODO: Etapa 2 - Extrair algoritmo da Cifra de César da TelaCaoticaLegada
        // Dica: Para cada caractere entre 'A' e 'Z', rotacionar circularmente:
        // char cifrado = (char) ('A' + (c - 'A' + this.chaveDeslocamento) % 26);

        StringBuilder cifrado = new StringBuilder();
                String textoLimpo = mensagem.trim().toUpperCase();
                for (int i = 0; i < textoLimpo.length(); i++) {
                    char c = textoLimpo.charAt(i);
                    if (c >= 'A' && c <= 'Z') {
                        char letraCifrada = (char) ('A' + (c - 'A' + chaveDeslocamento) % 26);
                        cifrado.append(letraCifrada);
                    } else {
                        cifrado.append(c);
                    }
                }
        
        return cifrado.toString();
    }

    /**
     * Calcula o checksum somando o valor ordinal ASCII de cada caractere da mensagem em maiúsculo.
     */
    public int calcularChecksum() {
        // TODO: Etapa 2 - Extrair cálculo de Checksum da TelaCaoticaLegada
        // Somar o código ASCII de todos os caracteres da mensagem

        int checksum = 0;
        
        // Percorre os caracteres da mensagem já normalizada
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            checksum += (int) c;
        }
        
        return checksum;
    }
}
