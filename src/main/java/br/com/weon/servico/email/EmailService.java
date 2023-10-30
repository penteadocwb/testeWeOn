package br.com.weon.servico.email;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.modelo.email.EmailModel;
import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.negocio.fila.MensagemType;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;
import br.com.weon.persistencia.email.EmailImpDAO;
import br.com.weon.persistencia.email.IEmailDAO;
import br.com.weon.servico.email.vo.EmailFormulario;
import br.com.weon.servico.email.vo.EmailListagemFormulario;

@Component
public class EmailService {

	private IConexao conexao;

	public EmailService(Conexao conexao) {
		this.conexao = conexao;
	}

	public EmailListagemFormulario listarMensagensAguardadoProcessamento() {
		EmailListagemFormulario formulario = new EmailListagemFormulario();

		List<BaseModel> lista = FilaSingleston.get().listarMensagemAgardandoProcessamento(MensagemType.EMAIL);

		formulario.setLista(converterModelEmVo(lista));

		return formulario;
	}

	private List<EmailFormulario> converterModelEmVo(List<BaseModel> lista) {
		List<EmailFormulario> listaVo = new ArrayList<>();

		lista.stream().forEach(model -> {
			EmailModel email = (EmailModel) model;

			listaVo.add(converterModelEmVo(email));
		});

		return listaVo;
	}

	private EmailFormulario converterModelEmVo(EmailModel email) {
		EmailFormulario vo = new EmailFormulario();

		vo.setIdEmail(email.getIdEmail());
		vo.setEmailOrigem(email.getEmailOrigem());
		vo.setEmailDestino(email.getEmailDestino());
		vo.setDataHora(email.getDataHora());

		return vo;
	}

	public String gravar(EmailFormulario formulario) {
		String erro = validarFormulario(formulario);
		if (erro != null)
			return erro;

		IEmailDAO dao = new EmailImpDAO(conexao);
		EmailModel email = buscarModel(formulario.getIdEmail(), dao);
		email = instanciarModelPeloVo(email, formulario);

		try {
			conexao.iniciarTransacao();
			email = dao.salvar(email);
			conexao.comitarTransacao();

			FilaSingleston.get().inserir(email);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();

			return "Erro ao inserir a mensagem. " + e;
		}

		return "Mensagem inserida com sucesso";
	}

	private String validarFormulario(EmailFormulario formulario) {
		if (formulario == null)
			return "Formulario não preenchido";

		StringBuilder erro = new StringBuilder();

		if (formulario.getEmailOrigem() == null || formulario.getEmailOrigem().isBlank())
			erro.append("Email de origem não pder ser nulo ou vazio\n");
		else if (!ehEmailValido(formulario.getEmailOrigem()))
			erro.append("Email de origem invalido");

		if (formulario.getEmailDestino() == null || formulario.getEmailDestino().isBlank())
			erro.append("Email de destino não pder ser nulo ou vazio\n");
		else if (!ehEmailValido(formulario.getEmailDestino()))
			erro.append("Email de destino invalido");

		if (erro.toString().isBlank())
			return null;

		return erro.toString();
	}

	private boolean ehEmailValido(String email) {
		return Pattern.compile("^[\\w!#$%&'*+\\/=?^`{|}~-]+(\\.[\\w!#$%&'*+\\/=?^`{|}~-]+)*@(([\\w-]+\\.)+[A-Za-z]{2,6}|\\[\\d{1,3}(\\.\\d{1,3}){3}\\])$").matcher(email).matches();
	}

	private EmailModel buscarModel(BigInteger id, IEmailDAO dao) {
		if (id == null)
			return null;

		return dao.buscarPeloId(id);
	}

	private EmailModel instanciarModelPeloVo(EmailModel model, EmailFormulario vo) {
		if (model == null)
			model = new EmailModel();

		model.setIdEmail(vo.getIdEmail());
		model.setEmailOrigem(vo.getEmailOrigem());
		model.setEmailDestino(vo.getEmailDestino());
		model.setDataHora(vo.getDataHora());

		return model;
	}

	public EmailFormulario buscar(BigInteger idEmail) {
		if (idEmail == null)
			return null;

		EmailModel email = buscarPeloId(idEmail);
		if (email == null)
			return null;

		return converterModelEmVo(email);
	}

	private EmailModel buscarPeloId(BigInteger idEmail) {
		if (idEmail == null)
			return null;

		IEmailDAO dao = new EmailImpDAO();

		return dao.buscarPeloId(idEmail);
	}

}
