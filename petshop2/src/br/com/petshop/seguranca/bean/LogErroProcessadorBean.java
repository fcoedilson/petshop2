package br.com.petshop.seguranca.bean;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.actions.DefaultActionRemove;
import br.com.petshop.architecture.actions.DefaultActionSave;
import br.com.petshop.architecture.actions.DefaultActionSearch;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.architecture.report.DownloadManager;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.entity.LogErroProcessador;

@ManagedBean
@ViewScoped
public class LogErroProcessadorBean extends AbstractFacadeBean<LogErroProcessador> {

	private static final long serialVersionUID = -7556482044346031087L;

	@ManagedProperty(value="#{logErroProcessadorService}")
	private BaseService<LogErroProcessador> logErroProcessadorService;
	
	@ManagedProperty(value="#{downloadManager}")
	private DownloadManager downloadManager;
	
	@Override
	protected String getPageTitle() {
		return "titulo_logErroProcessador";
	}
	
	@Override
	protected Class<LogErroProcessador> getDefaultEntityClass() {
		return LogErroProcessador.class;
	}
	
//	@Override
//	public void afterSearch(){
//		for(LogErroProcessador logErroProcessador : getSearchResult()){
//			File arq = new File(JSFUtil.getMessageFromBundle("path_erro")+"/" + logErroProcessador.getNomeArquivo() + "");
//			if(arq.exists()){
//				logErroProcessador.setFile(arq);
//			}
//		}
//	}
	
	protected void afterRemove(LogErroProcessador removedEntity) {
		File removedFile = removedEntity.getFile();
		if(removedFile != null && removedFile.exists()){
			removedFile.delete();
		}
	}
	
	//actions
	
	public ActionSearch<LogErroProcessador> searchAction() {
		return new DefaultActionSearch<LogErroProcessador>(logErroProcessadorService);
	}
	
	@Override
	protected ActionSave<LogErroProcessador> saveAction() {
		return new DefaultActionSave<LogErroProcessador>(logErroProcessadorService);
	}
	
	@Override
	public ActionRemove<LogErroProcessador> removeAction() {
		return new DefaultActionRemove<LogErroProcessador>(logErroProcessadorService);
	}

	//fim actions

	public void setDownloadManager(DownloadManager downloadManager) {
		this.downloadManager = downloadManager;
	}

	public void setLogErroProcessadorService(BaseService<LogErroProcessador> logErroProcessadorService) {
		this.logErroProcessadorService = logErroProcessadorService;
	}
}