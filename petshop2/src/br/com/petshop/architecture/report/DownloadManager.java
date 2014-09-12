package br.com.petshop.architecture.report;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class DownloadManager {

	public void disponibilizarArquivoDownload(final byte[] conteudo, final String nome, final boolean exibeCaixaDialogo, final String contentType) throws IOException {
		
		if(FacesContext.getCurrentInstance() == null){
			return;
		}
		
		final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		final String tipoCaixa = exibeCaixaDialogo ? "attachment" : "inline";

		response.setHeader("Content-disposition", tipoCaixa + ";filename=\"" + nome + "\";");
		response.setContentType(contentType);
		response.setContentLength(conteudo.length);
		final ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(conteudo, 0, conteudo.length);
		FacesContext.getCurrentInstance().responseComplete();
		outputStream.close();
	}
}
