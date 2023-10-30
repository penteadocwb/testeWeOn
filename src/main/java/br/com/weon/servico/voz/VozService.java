package br.com.weon.servico.voz;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.modelo.voz.VozModel;
import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.negocio.fila.MensagemType;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;
import br.com.weon.persistencia.voz.IVozDAO;
import br.com.weon.persistencia.voz.VozImpDAO;
import br.com.weon.servico.voz.vo.VozFormulario;
import br.com.weon.servico.voz.vo.VozListagemFormulario;

@Service
public class VozService {

	private IConexao conexao;

	public VozService(Conexao conexao) {
		this.conexao = conexao;
	}

	public VozListagemFormulario listarMensagensAguardadoProcessamento() {
		VozListagemFormulario formulario = new VozListagemFormulario();

		List<BaseModel> lista = FilaSingleston.get().listarMensagemAgardandoProcessamento(MensagemType.VOZ);

		formulario.setLista(converterModelEmVo(lista));

		return formulario;
	}

	private List<VozFormulario> converterModelEmVo(List<BaseModel> lista) {
		List<VozFormulario> listaVo = new ArrayList<>();

		lista.stream().forEach(model -> {
			VozModel voz = (VozModel) model;

			listaVo.add(converterModelEmVo(voz));
		});

		return listaVo;
	}

	private VozFormulario converterModelEmVo(VozModel voz) {
		VozFormulario vo = new VozFormulario();

		vo.setIdVoz(voz.getIdVoz());
		vo.setTelefoneOrigem(voz.getTelefoneOrigem());
		vo.setTelefoneDestino(voz.getTelefoneDestino());
		vo.setDataHora(voz.getDataHora());

		return vo;
	}

	public String gravar(VozFormulario formulario) {
		String erro = validarFormulario(formulario);
		if (erro != null)
			return erro;

		IVozDAO dao = new VozImpDAO(conexao);
		VozModel voz = buscarModel(formulario.getIdVoz(), dao);
		voz = instanciarModelPeloVo(voz, formulario);

		try {
			conexao.iniciarTransacao();
			voz = dao.salvar(voz);
			conexao.comitarTransacao();

			FilaSingleston.get().inserir(voz);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();

			return "Erro ao inserir a mensagem. " + e;
		}

		return "Mensagem inserida com sucesso";
	}

	private String validarFormulario(VozFormulario formulario) {
		if (formulario == null)
			return "Formulario não preenchido";

		StringBuilder erro = new StringBuilder();

		if (formulario.getTelefoneOrigem() == null || formulario.getTelefoneOrigem().isBlank())
			erro.append("Telefone de origem não pder ser nulo ou vazio\n");
		else if (!formulario.getTelefoneOrigem().matches("\\d{11}"))
			erro.append("Telefone de origem invalido");

		if (formulario.getTelefoneDestino() == null || formulario.getTelefoneDestino().isBlank())
			erro.append("Telefone de destino não pder ser nulo ou vazio\n");
		else if (!formulario.getTelefoneDestino().matches("\\d{11}"))
			erro.append("Telefone de destino invalido");

		if (erro.toString().isBlank())
			return null;

		return erro.toString();
	}

	private VozModel buscarModel(BigInteger id, IVozDAO dao) {
		if (id == null)
			return null;

		return dao.buscarPeloId(id);
	}

	private VozModel instanciarModelPeloVo(VozModel model, VozFormulario vo) {
		if (model == null)
			model = new VozModel();

		model.setIdVoz(vo.getIdVoz());
		model.setTelefoneOrigem(vo.getTelefoneOrigem());
		model.setTelefoneDestino(vo.getTelefoneDestino());
		model.setDataHora(vo.getDataHora());

		return model;
	}

	public VozFormulario buscar(BigInteger idVoz) {
		if (idVoz == null)
			return null;

		VozModel voz = buscarPeloId(idVoz);
		if (voz == null)
			return null;

		return converterModelEmVo(voz);
	}

	private VozModel buscarPeloId(BigInteger idVoz) {
		if (idVoz == null)
			return null;

		IVozDAO dao = new VozImpDAO();

		return dao.buscarPeloId(idVoz);
	}

}
