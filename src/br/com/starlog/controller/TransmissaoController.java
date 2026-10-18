package br.com.starlog.controller;

import br.com.starlog.model.CriptoMensagem;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMADA CONTROLLER (O Maestro da Transmissão)
 * 
 * ATENÇÃO ESTUDANTE:
 * Implemente esta classe para orquestrar as requisições da View,
 * convertendo os tipos brutos e alimentando o Model!
 * 
 * REGRA DE ARQUITETURA:
 * O Controller manipula lógica e dados, NÃO componentes visuais (zero imports de javax.swing.*)!
 */
public class TransmissaoController {

    private final List<String> bufferTransmissoes = new ArrayList<>();

    /**
     * Orquestra a transmissão da mensagem.
     * 
     * @param remetenteRaw Remetente vindo do campo de texto da tela
     * @param msgRaw       Mensagem vinda do campo de texto da tela
     * @param chaveStr     Chave digitada como texto na tela
     * @return Pacote formatado no padrão: [REMETENTE] #CHECKSUM -> TEXTO_CIFRADO
     */
    public String processarTransmissao(String remetenteRaw, String msgRaw, String chaveStr) {
        // TODO: Etapa 3 - Orquestração no Controller
        int chave;
        try {
            // 1. Converter chaveStr para inteiro com Integer.parseInt(chaveStr.trim()).
            chave = Integer.parseInt(chaveStr.trim());

        //    Se falhar (NumberFormatException ou NullPointerException), lance
        } catch (NumberFormatException | NullPointerException e) {

        //    IllegalArgumentException("Chave deve ser um número inteiro válido.");
            throw new IllegalArgumentException("Chave deve ser um número inteiro válido");
        }

        // 2. Instanciar CriptoMensagem passando remetente, mensagem e chave convertida.
        CriptoMensagem cripto = new CriptoMensagem(remetenteRaw, msgRaw, chave);
            
        // 3. Obter o texto cifrado e o checksum do Model.
        String textoCifrado = cripto.cifrar();
        int checksum = cripto.calcularChecksum();

        // 4. Formatar o pacote: String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);
        String pacote = String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);

        // 5. Adicionar o pacote à lista bufferTransmissoes.
        bufferTransmissoes.add(pacote);

        // 6. Retornar o pacote formatado.
        return pacote;
    }

    public int getTotalTransmissoes() {
        return bufferTransmissoes.size();
    }
}
