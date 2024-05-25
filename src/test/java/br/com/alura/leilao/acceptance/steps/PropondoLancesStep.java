package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PropondoLancesStep {

    private Lance lance;
    private Leilao leilao;
    private List<Lance> list;

    @Before
    public void setup() {
        this.list = new ArrayList<>();
        this.leilao = new Leilao("Tablet XPTO");
    }

    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propoe ao leilao")
    public void quando_propoe_o_lance() {
        leilao.propoe(lance);
    }

    @Entao("o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Dado("varios lance valido")
    public void varios_lance_valido() {
        Usuario usuario1 = new Usuario("fulano");
        list.add(new Lance(usuario1, BigDecimal.TEN));

        Usuario usuario2 = new Usuario("beltrano");
        list.add(new Lance(usuario2, new BigDecimal("15.0")));
        leilao = new Leilao("Tablet XPTO");
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
        list.add(new Lance(new Usuario(nomeUsuario), new BigDecimal(valor)));
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        list.forEach(lance -> leilao.propoe(lance));
    }

    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        Assert.assertEquals(this.list.size(), leilao.getLances().size());
        Assert.assertEquals(this.list.get(0).getValor(), leilao.getLances().get(0).getValor());
        Assert.assertEquals(this.list.get(1).getValor(), leilao.getLances().get(1).getValor());
    }
}
